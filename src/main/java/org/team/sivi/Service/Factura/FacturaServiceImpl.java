package org.team.sivi.Service.Factura;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.FacturaDto.DetalleFacturaDto;
import org.team.sivi.Dto.FacturaDto.FacturaCrearRequestDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Model.DetalleVenta;
import org.team.sivi.Model.Venta;
import org.team.sivi.Repository.VentaRepository;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FacturaServiceImpl implements FacturaService {

    private final VentaRepository ventaRepository;

    public FacturaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Transactional
    @Override
    public byte[] generarFactura(FacturaCrearRequestDto facturaCrearRequestDto) throws JRException, NotFoundException {

        Venta venta=ventaRepository.findByCodigoVenta(facturaCrearRequestDto.getCodigoVenta())
                .orElseThrow(()->new NotFoundException("No se ha encontrado la venta en el sistema"));

        // cargamos el archivo .jasper desde resources
        InputStream  archivoJasper = getClass().getResourceAsStream("/reportes/Ticket19.jasper");

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(archivoJasper);


        Map<String,Object>parametros=new HashMap<>();

        LocalDate fecha= venta.getFechaVenta().toLocalDate();
        LocalTime hora= venta.getFechaVenta().toLocalTime();

        parametros.put("nombreTienda","Tienda la mejor");
        parametros.put("numeroFactura",venta.getCodigoVenta());
        parametros.put("fecha",fecha.toString());
        parametros.put("hora",hora.toString());
        parametros.put("vendedor",venta.getUsuario().getNombre());
        parametros.put("metodoPago",venta.getMetodoPago());
        parametros.put("descuento",venta.getDescuentoTotal());
        parametros.put("dineroRecibido", facturaCrearRequestDto.getDineroRecibido());
        parametros.put("cambio",facturaCrearRequestDto.getDineroDevuelto());

        List<DetalleFacturaDto> listaDetalleFacturaDto =new ArrayList<>();


        for (DetalleVenta detalleVenta:venta.getListaDetallesVenta()){

            if (detalleVenta.getUnidadBase().equalsIgnoreCase("KILOGRAMO")){
                detalleVenta.setUnidadBase("Kg");
            }

            if (detalleVenta.getUnidadBase().equalsIgnoreCase("UNIDAD")){
                detalleVenta.setUnidadBase("Und");
            }

            DetalleFacturaDto detalleFacturaDto=new DetalleFacturaDto(detalleVenta.getCodigoBarras()
                    ,detalleVenta.getNombreProducto(),detalleVenta.getCantidad(),detalleVenta.getUnidadBase()
                    ,detalleVenta.getPrecioUnitario());

            listaDetalleFacturaDto.add(detalleFacturaDto);


        }

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaDetalleFacturaDto);

        JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

       return JasperExportManager.exportReportToPdf(print);

    }


}
