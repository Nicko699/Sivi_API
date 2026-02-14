package org.team.sivi.Service.Venta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.VentaDto.*;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.VentaMapper;
import org.team.sivi.Model.*;
import org.team.sivi.Repository.ProductoRepository;
import org.team.sivi.Repository.UsuarioRepository;
import org.team.sivi.Repository.VentaRepository;
import org.team.sivi.Service.Factura.FacturaService;
import org.team.sivi.Specifications.VentaSpecifications;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class VentaServiceImpl implements VentaService {

     private final UsuarioRepository usuarioRepository;
     private final ProductoRepository productoRepository;
     private final DescontarStockLote descontarStockLote;
     private final VentaRepository ventaRepository;
     private final VentaMapper ventaMapper;
     private final FacturaService facturaService;


    public VentaServiceImpl(UsuarioRepository usuarioRepository, ProductoRepository productoRepository, DescontarStockLote descontarStockLote, VentaRepository ventaRepository, VentaMapper ventaMapper, FacturaService facturaService) {
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.descontarStockLote = descontarStockLote;
        this.ventaRepository = ventaRepository;
        this.ventaMapper = ventaMapper;
        this.facturaService = facturaService;
    }

    @Transactional
    @Override
    public VentaCrearResponseDto crearVenta(VentaCrearRequestDto ventaCrearRequestDto) throws NotFoundException, BadRequestException {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        String correo=authentication.getName();

        //Encontramos el usuario que esta realizando la venta en la bd por correo
        //y si no lo encuentra, lanzamos una excepcion
        Usuario usuarioEncontrado=usuarioRepository.findUsuarioByCorreoAndSoftDeleteFalse(correo)
                .orElseThrow(()->new NotFoundException("El usuario no se encuentra registrado en el sistema"));

        List<String>listaCodigoBarras=new ArrayList<>();

       //Recorremos los detalles de venta que vienen del dto y obtenemos su codigo de barras
        //y lo guardamos en una lista
        for (DetalleVentaCrearRequestDto detalleVenta:ventaCrearRequestDto.getListaDetalleVenta()){

            String codigoBarra=detalleVenta.getCodigoBarrasProducto();

            listaCodigoBarras.add(codigoBarra);
        }
        //buscamos en la bd todos los productos seleccionados para venderse
        List<Producto>listaProductos=productoRepository.findAllByCodigoBarrasInAndSoftDeleteFalse(listaCodigoBarras);

        Map<String,Producto>productoMap=new HashMap<>();

       //Asignamos los productos encontrados de la bd a un map con clave, valor
        for (Producto producto:listaProductos){

            productoMap.put(producto.getCodigoBarras(),producto);

        }

        Venta venta=new Venta();

        List<DetalleVenta>listaDetalles=new ArrayList<>();

        BigDecimal totalCosto=BigDecimal.ZERO;
        BigDecimal subTotal;
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal nuevaCantidadProducto;
       //De nuevo recorremos los detalles de venta y comparamos el codigo de barras con el del map,
        //que son lo de los productos de la bd
        for (DetalleVentaCrearRequestDto detalleVentaDto :ventaCrearRequestDto.getListaDetalleVenta()){

            Producto producto=productoMap.get(detalleVentaDto.getCodigoBarrasProducto());

           //Si el producto es null, lanzamos la excepcion
            if (producto==null) {

                throw new NotFoundException("Producto no encontrado con el código de barras:"+detalleVentaDto.getCodigoBarrasProducto());
            }
           //Si el stock actual del producto es menor a la cantidad que se va a comprar ,lanzamos una excepcion de stock insuficiente
            if (producto.getStockTotal().compareTo(detalleVentaDto.getCantidad())<0){

                throw new BadRequestException("Stock insuficiente para realizar la venta");
            }

            //Realizamos operaciones precio venta*cantidad de compra
            subTotal=producto.getPrecioVenta().multiply(detalleVentaDto.getCantidad());
            //asignamos todos los subtotales al total de la venta
            total=total.add(subTotal);
            //Calculamos la nueva cantidad total del producto
            nuevaCantidadProducto=producto.getStockTotal().subtract(detalleVentaDto.getCantidad());


            //Llamamos al metodo descontar lote y le pasamos la cantidad a vender
             ResultadoLoteVentaResponseDto lotesUsadosToVender=descontarStockLote.descontarStockLotes(detalleVentaDto.getCantidad());

             totalCosto=totalCosto.add(lotesUsadosToVender.getCostoTotal());

            DetalleVenta detalleVenta=new DetalleVenta();

            detalleVenta.setCodigoBarras(producto.getCodigoBarras());
            detalleVenta.setNombreProducto(producto.getNombre());
            detalleVenta.setCantidad(detalleVentaDto.getCantidad());
            detalleVenta.setPrecioUnitario(producto.getPrecioVenta());
            detalleVenta.setUnidadBase(producto.getUnidadBase().toString());
            detalleVenta.setNombreMarca(producto.getMarca().getNombre());
            detalleVenta.setNombreCategoria(producto.getCategoria().getNombre());
            detalleVenta.setSubTotal(subTotal);
            detalleVenta.setFechaRegistro(LocalDateTime.now());
            detalleVenta.setProducto(producto);
            detalleVenta.setListaLotes(lotesUsadosToVender.getLotesUsados());
            detalleVenta.setVenta(venta);

            producto.setStockTotal(nuevaCantidadProducto);
            //Si el stock total es menor al stock minimo alerta, marcamos como stock bajo
            if (producto.getStockTotal().compareTo(producto.getStockMinimoAlerta())<=0){
                producto.setBajoStock(true);
            }

            listaDetalles.add(detalleVenta);

            }


        //Creamos el codigo para venta
        String codigoVenta="VNT-" + UUID.randomUUID().toString().substring(0,8).toUpperCase();
     //Si el descuento total es mayor a 0, calculamos el descuento
        if (ventaCrearRequestDto.getDescuentoTotal().compareTo(BigDecimal.ZERO)>0) {
            total = total.multiply(BigDecimal.ONE.subtract(ventaCrearRequestDto.getDescuentoTotal()
                    .divide(new BigDecimal("100"), 2, RoundingMode.UP))).setScale(2, RoundingMode.UP);
        }

        StringJoiner joiner=new StringJoiner(",");
       //Recorremos los roles y lo asignamos a un solo string
        for (Rol rol:usuarioEncontrado.getListaRol()){

          joiner.add(rol.getNombre());

        }

            String roles=joiner.toString();

        BigDecimal totalGanancia=BigDecimal.ZERO;

        totalGanancia=totalGanancia.add(total.subtract(totalCosto));

        venta.setCodigoVenta(codigoVenta);
        venta.setMontoTotal(total);
        venta.setDescuentoTotal(ventaCrearRequestDto.getDescuentoTotal());
        venta.setNombreUsuario(usuarioEncontrado.getNombre());
        venta.setEmail(usuarioEncontrado.getCorreo());
        venta.setRolUsuario(roles);
        venta.setMetodoPago(ventaCrearRequestDto.getMetodoPago());
        venta.setEstado(ventaCrearRequestDto.getEstado());
        venta.setDineroRecibido(ventaCrearRequestDto.getDineroRecibido());
        venta.setCambio(ventaCrearRequestDto.getCambio());
        venta.setGananciaVenta(totalGanancia);
        venta.setListaDetallesVenta(listaDetalles);
        venta.setFechaVenta(LocalDateTime.now());
        venta.setUsuario(usuarioEncontrado);


        return ventaMapper.ventaToVentaCrearResponseDto(ventaRepository.save(venta));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<VentaListarAdminResponseDto> filtrarVentasAdmin(String codigoVenta, String estado, String nombreUsuario, LocalDate desde, LocalDate hasta, Pageable pageable) {

        Specification<Venta> spec=Specification.allOf(VentaSpecifications.codigoVentaLike(codigoVenta)
                .and(VentaSpecifications.estadoEquals(estado)
                        .and(VentaSpecifications.usuarioEquals(nombreUsuario)
                                .and(VentaSpecifications.fechaEntre(desde,hasta)))));

        Page<Venta>listaVentas=ventaRepository.findAll(spec,pageable);

        return listaVentas.map(ventaMapper::ventaToVentaListarAdminResponseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<VentaListarVendResponseDto> filtrarVentasVend(String codigoVenta, String estado, LocalDate desde, LocalDate hasta, Pageable pageable) {

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        Specification<Venta> spec = Specification.allOf(
             VentaSpecifications.correoUsuarioEquals(authentication.getName()));

        if (codigoVenta != null && !codigoVenta.trim().isEmpty()) {
            spec = spec.and(VentaSpecifications.codigoVentaLike(codigoVenta));
        }

        if (estado != null && !estado.trim().isEmpty()) {
            spec = spec.and(VentaSpecifications.estadoEquals(estado));
        }

        if (desde != null && hasta != null) {
            spec = spec.and(VentaSpecifications.fechaEntre(desde, hasta));
        }


        Page<Venta>listaVentas=ventaRepository.findAll(spec,pageable);

        return listaVentas.map(ventaMapper::ventaToVentaListarVendResponseDto);

    }

    @Transactional
    @Override
    public void cambiarEstado(Long id, VentaCambiarEstadoRequestDto dto) throws NotFoundException, BadRequestException {

        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta no encontrada en el sistema"));

        String estadoActual = venta.getEstado();
        String nuevoEstado = dto.getEstado();

        if ("Pagado".equalsIgnoreCase(estadoActual) && "Pendiente".equalsIgnoreCase(nuevoEstado)) {
            throw new BadRequestException("No se puede cambiar una venta pagada a pendiente.");
        }

        if ("Pendiente".equalsIgnoreCase(estadoActual) && "Pagado".equalsIgnoreCase(nuevoEstado)) {
            venta.setEstado("Pagado");
            ventaRepository.save(venta);
        }
    }

}


