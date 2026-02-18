package org.team.sivi.Service.Venta;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.VentaDto.ResultadoLoteVentaResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Model.Lote;
import org.team.sivi.Repository.LoteRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class DescontarStockLote {

private final LoteRepository loteRepository;

    public DescontarStockLote(LoteRepository loteRepository)  {
        this.loteRepository = loteRepository;
    }

    @Transactional
    public ResultadoLoteVentaResponseDto descontarStockLotes(BigDecimal cantidadVender,String codigoBarras) throws NotFoundException {

        //lotes restar stock
        List<Lote> listaLotes = loteRepository.findByProducto_CodigoBarrasAndAgotadoFalseOrderByFechaCompraDesc(codigoBarras);

        if (listaLotes.isEmpty()) {
            throw new NotFoundException("No hay lotes disponibles");
        }

        List<Lote> lotesUsadosVender = new ArrayList<>();

         BigDecimal subTotalCosto =BigDecimal.ZERO;

        //Recorremos la lista de lotes
        for (Lote lote : listaLotes) {

            //Si la cantidad a vender es menor o igual a 0 nos salimos del bucle
            if (cantidadVender.compareTo(BigDecimal.ZERO) <= 0) {

                break; // ya se vendió all
            }
            //Obtenemos la cantidad actual del lote en la bd
            BigDecimal stockLote = lote.getCantidadActual();

            //Verificamos si la cantidad del lote actual alcanza para la cantidad que se vende
            if (stockLote.compareTo(cantidadVender) >= 0) {
                //Si alcanza, restamos la cantidad del lote con la de vender y setteamos la nueva cantidad del lote
                lote.setCantidadActual(stockLote.subtract(cantidadVender));

                subTotalCosto = subTotalCosto.add(lote.getPrecioCompraUnitario().multiply(cantidadVender));

                //Si la nueva cantidad quedó en 0, marcamos lote como agotado
                if (lote.getCantidadActual().compareTo(BigDecimal.ZERO) == 0) {
                    lote.setAgotado(true);
                }
                //y cambiamos la cantidad a vender en 0 por que ya se restó
                cantidadVender = BigDecimal.ZERO;

            }

            //si el lote no alcanza
            //Restamos la cantidad de vender con la del lote
            //setteamos la cantidad del lote a 0
            //y lo marcamos como agotado
            else {
                subTotalCosto = subTotalCosto.add(lote.getPrecioCompraUnitario().multiply(stockLote));
                cantidadVender = cantidadVender.subtract(stockLote);
                lote.setCantidadActual(BigDecimal.ZERO);
                lote.setAgotado(true);

            }
            //Guardamos el lote en la bd
            loteRepository.save(lote);
            lotesUsadosVender.add(lote);

        }
        //Retornamos los lotes que se usaron durante la venta
        return new ResultadoLoteVentaResponseDto(lotesUsadosVender,subTotalCosto);
        }
    }

