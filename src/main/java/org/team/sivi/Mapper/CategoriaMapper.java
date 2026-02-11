package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.Query;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Dto.ReporteDto.TotalVentasDiaDto;
import org.team.sivi.Model.Categoria;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    //Metodo para listar
       CategoriaListaResponseDto categoriaToCategoriaListaResponseDto(Categoria categoria);

    // Convertir DTO de creación a la Entidad
       Categoria categoriaCrearRequestDtoToCategoria(CategoriaCrearRequestDto dto);

    // Convertir Entidad a DTO de respuesta
       CategoriaResponseDto categoriaToCategoriaResponseDto(Categoria entidad);

    // Suma ventas entre dos fechas (inicio y fin)
    @Query("SELECT new org.team.sivi.Dto.ReporteDto.TotalVentasDiaDto(SUM(v.montoTotal), COUNT(v)) " +
            "FROM Venta v WHERE v.fechaVenta BETWEEN :inicio AND :fin")
    TotalVentasDiaDto sumarVentasEntreFechas(LocalDateTime inicio, LocalDateTime fin);
}

