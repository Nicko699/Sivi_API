package org.team.sivi.Service.Lote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.LoteDto.LoteCrearRequestDto;
import org.team.sivi.Dto.LoteDto.LoteCrearResponseDto;
import org.team.sivi.Dto.LoteDto.LoteEditarRequestDto;
import org.team.sivi.Dto.LoteDto.LoteListarResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.LoteMapper;
import org.team.sivi.Model.Lote;
import org.team.sivi.Model.Producto;
import org.team.sivi.Repository.LoteRepository;
import org.team.sivi.Repository.ProductoRepository;
import org.team.sivi.Specifications.LoteSpecifications;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoteServiceImpl implements LoteService {

    private final ProductoRepository productoRepository;
    private final LoteMapper loteMapper;
    private final LoteRepository loteRepository;

    public LoteServiceImpl(ProductoRepository productoRepository, LoteMapper loteMapper, LoteRepository loteRepository) {
        this.productoRepository = productoRepository;
        this.loteMapper = loteMapper;
        this.loteRepository = loteRepository;
    }

    @Transactional
    @Override
    public LoteCrearResponseDto crearLote(LoteCrearRequestDto loteCrearRequestDto) throws NotFoundException, BadRequestException {

        Producto producto=productoRepository.findById(loteCrearRequestDto.getProductoId())
                .orElseThrow(()-> new NotFoundException("El producto no existe en el sistema"));

        if (!producto.getActivo() || producto.getSoftDelete()){

            throw new BadRequestException("El producto está inactivo o eliminado");
        }

        Lote lote=loteMapper.loteCrearRequestDtoToLote(loteCrearRequestDto);

        String codigoLote= "LOT-" +UUID.randomUUID().toString().substring(0,8).toUpperCase();

        lote.setCodigoLote(codigoLote);
        lote.setCantidadActual(lote.getCantidadInicial());
        lote.setSoftDelete(false);
        lote.setFechaCompra(LocalDateTime.now());
        lote.setProducto(producto);

       BigDecimal stockTotal=producto.getStockTotal().add(lote.getCantidadInicial());

       producto.setStockTotal(stockTotal);

       producto.setBajoStock(producto.getStockTotal().compareTo(producto.getStockMinimoAlerta()) < 0);

        //Si el lote viene con una cantidad igual a 0 queda agotado true, si no queda false
        lote.setAgotado(lote.getCantidadInicial().compareTo(new BigDecimal("0.0")) == 0);

        loteRepository.save(lote);
        productoRepository.save(producto);

        return loteMapper.loteToLoteCrearResponseDto(lote);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<LoteListarResponseDto> filtrarLotes(String search, Boolean agotado, Pageable pageable) throws NotFoundException {

        Specification<Lote>spec=Specification.allOf(LoteSpecifications.noEliminados()
                .and(LoteSpecifications.buscarPorCodigo(search).and(LoteSpecifications.agotadoEqual(agotado))));

         Page<Lote>listaLotes=loteRepository.findAll(spec,pageable);

         if (listaLotes.isEmpty()){
             throw new NotFoundException("No se encontraron resultados");
         }

        return listaLotes.map(loteMapper::loteToLoteListarResponseDto);
    }

    @Transactional
    @Override
    public void editarLote(Long id, LoteEditarRequestDto loteEditarRequestDto) throws NotFoundException, BadRequestException {

        Lote lote=loteRepository.findById(id).orElseThrow(()->new NotFoundException("El lote no existe en el sistema"));

        if (lote.getCantidadActual().compareTo(lote.getCantidadInicial())<0){

            throw new BadRequestException("No se puede editar el lote, ya que se ha usado para realizar ventas");

        }

        BigDecimal cantidadInicialAnterior= lote.getCantidadInicial();

       loteMapper.loteEditarRequestDtoToLote(loteEditarRequestDto,lote);

       lote.setCantidadActual(lote.getCantidadInicial());

       lote.setAgotado(lote.getCantidadInicial().compareTo(new BigDecimal("0.0")) == 0);

       Producto producto=lote.getProducto();

       BigDecimal stockParcial=producto.getStockTotal().subtract(cantidadInicialAnterior);

       BigDecimal stockTotal=stockParcial.add(lote.getCantidadInicial());

       producto.setStockTotal(stockTotal);

       producto.setBajoStock(producto.getStockTotal().compareTo(producto.getStockMinimoAlerta()) < 0);

       loteRepository.save(lote);
       productoRepository.save(producto);

    }

    @Transactional
    @Override
    public void eliminarLote(Long id) throws NotFoundException, BadRequestException {

        Lote lote=loteRepository.findById(id).orElseThrow(()->new NotFoundException("El lote no existe en el sistema"));

        Producto producto=lote.getProducto();

        if (lote.getAgotado()){

            lote.setSoftDelete(true);

            BigDecimal stockParcial=producto.getStockTotal().subtract(lote.getCantidadInicial());

            producto.setStockTotal(stockParcial);

            producto.setBajoStock(producto.getStockTotal().compareTo(producto.getStockMinimoAlerta()) < 0);

            productoRepository.save(producto);
            loteRepository.save(lote);
        }

        else if (lote.getCantidadInicial().compareTo(lote.getCantidadActual())>0){
            throw new BadRequestException("No se puede eliminar el lote, ya que se ha usado para realizar ventas");
        }
        else {
            BigDecimal stockParcial=producto.getStockTotal().subtract(lote.getCantidadInicial());

            producto.setStockTotal(stockParcial);

            producto.setBajoStock(producto.getStockTotal().compareTo(producto.getStockMinimoAlerta()) < 0);
            productoRepository.save(producto);
            loteRepository.deleteById(lote.getId());
        }


    }


}
