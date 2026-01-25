package org.team.sivi.Service.Producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.team.sivi.Dto.LoteDto.LoteProductoListarResponseDto;
import org.team.sivi.Dto.ProductoDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.ProductoMapper;
import org.team.sivi.Model.Categoria;
import org.team.sivi.Model.Enum.ProductoTipoVenta;
import org.team.sivi.Model.Enum.ProductoUnidadBase;
import org.team.sivi.Model.Lote;
import org.team.sivi.Model.Marca;
import org.team.sivi.Model.Producto;
import org.team.sivi.Repository.CategoriaRepository;
import org.team.sivi.Repository.LoteRepository;
import org.team.sivi.Repository.MarcaRepository;
import org.team.sivi.Repository.ProductoRepository;
import org.team.sivi.Specifications.ProductoSpecifications;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;
    private final LoteRepository loteRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper, CategoriaRepository categoriaRepository, MarcaRepository marcaRepository, LoteRepository loteRepository) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
        this.categoriaRepository = categoriaRepository;
        this.marcaRepository = marcaRepository;
        this.loteRepository = loteRepository;
    }

    @Transactional
    @Override
    public ProductoCrearResponseDto crearProducto(ProductoCrearRequestDto productoCrearRequestDto) throws NotFoundException, BadRequestException {

        Producto producto = productoMapper.productoCrearRequestDtoToProducto(productoCrearRequestDto);
        //Verificar si el codigo de barras llega null o vacio y si llega verificar si existe otro producto en la bd
        //no eliminado por softdelete con el mismo codigo de barras
        if (StringUtils.hasText(producto.getCodigoBarras())) {
            if (productoRepository.existsByCodigoBarrasAndSoftDeleteFalse(producto.getCodigoBarras())) {

                throw new BadRequestException("Ya existe un producto registrado en el sistema con el mismo código de barras");
            }
        }

        //Comprobamos que la categoria exista en el sistema
        Categoria categoria = categoriaRepository.findById(productoCrearRequestDto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("La categoría no existe en el sistema"));
        //Verificamos que la categoria este activa
        if (categoria.getActivo()) {
            producto.setCategoria(categoria);
        } else {
            throw new BadRequestException("No se puede crear un nuevo producto con la categoría inactiva");
        }

        //Comprobamos que la marca exista en el sistema
        Marca marca = marcaRepository.findById(productoCrearRequestDto.getMarcaId())
                .orElseThrow(() -> new NotFoundException("La marca no existe en el sistema"));

        //Verificamos que la marca este activa
      if (marca.getActivo()){
          producto.setMarca(marca);
      }
      else {
          throw new BadRequestException("No se puede crear un nuevo producto con la marca inactiva");
      }

        //Si el producto no tiene codigo de barras creamos uno automatico
        if (!StringUtils.hasText(producto.getCodigoBarras())){
            String codigoBarras="PTD-"+ UUID.randomUUID().toString().substring(0,8).toUpperCase();
            producto.setCodigoBarras(codigoBarras);
        }
        //Si tipo de venta es unidad, cambiamos unidad base a unidad
        // si tipo de venta es peso, cambiamos a kilogramos
        if (ProductoTipoVenta.UNIDAD.equals(producto.getTipoVenta())){

            producto.setUnidadBase(ProductoUnidadBase.UNIDAD);
        }
        else {
            producto.setUnidadBase(ProductoUnidadBase.KILOGRAMO);
        }

      producto.setStockTotal(new BigDecimal("0.00"));
      producto.setSoftDelete(false);
      producto.setActivo(true);
      producto.setFechaCreacion(LocalDateTime.now());
      producto.setFechaActualizacion(LocalDateTime.now());

      producto.setBajoStock(producto.getStockTotal().compareTo(producto.getStockMinimoAlerta()) < 0);

      productoRepository.save(producto);

      return productoMapper.productoToProductoCrearResponseDto(producto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductoListarAdminResponseDto> filtrarProductosAdmin(String search, Boolean activo, Long categoriaId, Pageable pageable) throws NotFoundException {

        Specification<Producto>spec=Specification.allOf(ProductoSpecifications.noEliminados()
                .and(ProductoSpecifications.likeNombreOCodigo(search).and(ProductoSpecifications.conCategoria(categoriaId)
                        .and(ProductoSpecifications.activoEqual(activo)))));

        Page<Producto>productosFiltrados=productoRepository.findAll(spec,pageable);

        if (productosFiltrados.isEmpty()){
            throw new NotFoundException("No se han encontraron resultados");
        }

        return productosFiltrados.map(productoMapper::productoToProductoListarAdminResponseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductoListarVendResponseDto> filtrarProductosVend(String search, Long categoriaId, Pageable pageable) throws NotFoundException {

        Specification<Producto>spec=Specification.allOf(ProductoSpecifications.noEliminados().and(ProductoSpecifications.likeNombreOCodigo(search))
                .and(ProductoSpecifications.conCategoria(categoriaId)));

        Page<Producto>productosFiltrados=productoRepository.findAll(spec,pageable);

        if (productosFiltrados.isEmpty()){
            throw new NotFoundException("No se encontraron resultados");
        }

        return productosFiltrados.map(productoMapper::productoToProductoListarVendResponseDto);
    }

    @Transactional
    @Override
    public void editarProducto(Long id, ProductoEditarRequestDto productoEditarRequestDto) throws NotFoundException, BadRequestException {

        // Buscar el producto existente
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("El producto no existe en el sistema"));

        // solo bloquear si intenta desactivar y hay lotes con stock
        if (producto.getActivo() && !productoEditarRequestDto.getActivo()) {
            for (Lote lote : producto.getListaLotes()) {
                if (!lote.getAgotado()) {
                    throw new BadRequestException("No puedes desactivar el producto mientras tenga lotes con stock disponible");
                }
            }
        }

        // Mapear los cambios del DTO al producto
        Producto productoEditado = productoMapper.productoEditarRequestDtoToProducto(productoEditarRequestDto, producto);

        // Actualizamos categoría
        Categoria categoria = categoriaRepository.findById(productoEditarRequestDto.getCategoriaId())
                .orElseThrow(() -> new NotFoundException("La categoría no existe en el sistema"));

        productoEditado.setCategoria(categoria);

        //  Actualizamos marca
        Marca marca = marcaRepository.findById(productoEditarRequestDto.getMarcaId())
                .orElseThrow(() -> new NotFoundException("La marca no existe en el sistema"));
        productoEditado.setMarca(marca);

        // Configuramos la unidad según tipo de venta
        if (ProductoTipoVenta.UNIDAD.equals(productoEditado.getTipoVenta())) {

            productoEditado.setUnidadBase(ProductoUnidadBase.UNIDAD);
        } else {
            productoEditado.setUnidadBase(ProductoUnidadBase.KILOGRAMO);
        }

        // Guardamos el producto editado
        productoRepository.save(productoEditado);
    }


    @Transactional
    @Override
    public void eliminarProducto(Long id) throws NotFoundException,BadRequestException {

        Producto producto=productoRepository.findById(id).orElseThrow(()->
                new NotFoundException("El producto no existe en el sistema"));


        List<Lote>listaLotes=loteRepository.findByProductoAndAgotadoFalse(producto);

        if (listaLotes.isEmpty()){

            producto.setSoftDelete(true);

        }
        else {
            throw new BadRequestException( "No se puede eliminar el producto, aún posee " + listaLotes.size() + " lotes sin agotar");
        }


    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductoAlertarResponseDto> alertarProductoBajoStock(String search, Pageable pageable) throws NotFoundException {

        Specification<Producto>spec=Specification.allOf(ProductoSpecifications.noEliminados().and(ProductoSpecifications.bajoStockTrue())
                .and(ProductoSpecifications.likeNombreOCodigo(search)));

        Page<Producto>listaProductos=productoRepository.findAll(spec,pageable);


        return listaProductos.map(productoMapper::productoToProductoAlertaResponseDto);
    }

    //Metodo para filtrar productos activos, para cuando el front cree un nuevo lote al momento de elegir al producto asociar,
    //los muestre
    @Transactional(readOnly = true)
    @Override
    public Page<LoteProductoListarResponseDto> filtrarProductosLotes(String search, Pageable pageable) {

        Specification<Producto>spec=Specification.allOf(ProductoSpecifications.noEliminados()
                .and(ProductoSpecifications.soloActivos().and(ProductoSpecifications.likeNombreOCodigo(search))));

        Page< Producto> listaProductos=productoRepository.findAll(spec,pageable);

      return listaProductos.map(productoMapper::productoToLoteProductoListarResponseDto);
    }


}
