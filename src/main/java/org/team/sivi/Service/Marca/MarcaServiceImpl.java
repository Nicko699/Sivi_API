package org.team.sivi.Service.Marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.MarcaDto.MarcaCrearRequestDto;
import org.team.sivi.Dto.MarcaDto.MarcaCrearResponseDto;
import org.team.sivi.Dto.MarcaDto.MarcaEditarRequestDto;
import org.team.sivi.Dto.MarcaDto.MarcaListarResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.MarcaMapper;
import org.team.sivi.Model.Marca;
import org.team.sivi.Repository.MarcaRepository;
import org.team.sivi.Specifications.MarcaSpecifications;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements  MarcaService{

    private final MarcaMapper marcaMapper;
    private final MarcaRepository marcaRepository;

    public MarcaServiceImpl(MarcaMapper marcaMapper, MarcaRepository marcaRepository) {
        this.marcaMapper = marcaMapper;
        this.marcaRepository = marcaRepository;
    }

    @Transactional
    @Override
    public MarcaCrearResponseDto crearMarca(MarcaCrearRequestDto crearRequestDto) {

        Marca marca=marcaMapper.marcaCrearRequestDtoToMarca(crearRequestDto);

        marca.setActivo(true);
        marca.setFechaCreacion(LocalDateTime.now());
        marca.setFechaActualizacion(marca.getFechaCreacion());

        Marca marcaGuardada=marcaRepository.save(marca);

        return marcaMapper.marcaToMarcaCrearResponseDto(marcaGuardada);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MarcaListarResponseDto> filtrarMarca(String nombre, Boolean activo, Pageable pageable) throws NotFoundException {

        Specification<Marca>spec=Specification.allOf(MarcaSpecifications.likeNombre(nombre)
                .and(MarcaSpecifications.activoEqual(activo)));

        Page<Marca>listaMarcas=marcaRepository.findAll(spec,pageable);

        if (listaMarcas.isEmpty()){
            throw new NotFoundException("No se encontraron resultados");
        }

        return listaMarcas.map(marcaMapper::marcaToMarcaListaResponseDto);
    }

    @Transactional
    @Override
    public void editarMarca(Long id, MarcaEditarRequestDto editarRequestDto) throws NotFoundException, BadRequestException {

        Optional<Marca>marca=marcaRepository.findById(id);

        if (marca.isPresent()){
            Marca marcaGet=marca.get();

            if (Boolean.TRUE.equals(marcaGet.getActivo()) && Boolean.FALSE.equals(editarRequestDto.getActivo()) && !marcaGet.getListaProductos().isEmpty()) {

                throw new BadRequestException("No se puede desactivar la marca por que aún tiene productos asociados");

            }
                marcaMapper.marcaEditarRequestDtoToMarca(editarRequestDto, marcaGet);

                marcaGet.setFechaActualizacion(LocalDateTime.now());

                marcaRepository.save(marcaGet);
        }
        else {
            throw  new NotFoundException("No se ha encontrado la marca con el id:" +id);
        }

    }

    @Transactional
    @Override
    public void eliminarMarca(Long id) throws NotFoundException, BadRequestException {

       Optional<Marca> marca=marcaRepository.findById(id);

       if (marca.isPresent()){
           Marca marcaGet=marca.get();

           if (marcaGet.getActivo() && !marcaGet.getListaProductos().isEmpty()){

               throw new BadRequestException("No se puede eliminar la marca por que aún tiene productos asociados");
           }

           marcaRepository.deleteById(id);

       }
       else {
           throw new NotFoundException("La marca no se ha encontrado con el id:"+id);
       }
    }

}
