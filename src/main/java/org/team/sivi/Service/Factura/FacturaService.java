package org.team.sivi.Service.Factura;

import net.sf.jasperreports.engine.JRException;
import org.team.sivi.Dto.FacturaDto.FacturaCrearRequestDto;
import org.team.sivi.Exception.NotFoundException;

public interface FacturaService {

    public byte[] generarFactura(FacturaCrearRequestDto facturaCrearRequestDto) throws JRException, NotFoundException;
}
