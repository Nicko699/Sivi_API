package org.team.sivi.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.team.sivi.Dto.RolResponseDto;
import org.team.sivi.Dto.UsuarioDto.UsuarioCrearCuentaRequestDto;
import org.team.sivi.Dto.UsuarioDto.UsuarioCrearCuentaResponseDto;
import org.team.sivi.Dto.UsuarioDto.UsuarioEditarRequestDto;
import org.team.sivi.Dto.UsuarioDto.UsuarioListaResponseDto;
import org.team.sivi.Model.Rol;
import org.team.sivi.Model.Usuario;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "listaRol",ignore = true)
   Usuario usuarioCrearCuentaRequestDtoToUsuario(UsuarioCrearCuentaRequestDto usuarioCrearCuentaRequestDto);

    UsuarioCrearCuentaResponseDto usuarioToUsuarioCrearCuentaResponseDto(Usuario usuario);

    UsuarioListaResponseDto usuarioToUsuarioListaResponseDto(Usuario usuario);

    Usuario usuarioEditarRequestDtoToUsuario(UsuarioEditarRequestDto usuarioEditarRequestDto,@MappingTarget Usuario usuario);


    }