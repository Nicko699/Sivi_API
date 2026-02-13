package org.team.sivi.Service.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.CategoriaMapper;
import org.team.sivi.Model.Categoria;
import org.team.sivi.Repository.CategoriaRepository;
import org.team.sivi.Specifications.CategoriaSpecifications;

import java.time.LocalDateTime;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public Page<CategoriaListaResponseDto> filtrarCategorias(String nombre, Boolean activo, Pageable pageable) throws NotFoundException {

        Specification<Categoria>spec=Specification.allOf(CategoriaSpecifications.nombreLike(nombre)
                .and(CategoriaSpecifications.activoEqual(activo)));

            Page<Categoria>listaCategorias=categoriaRepository.findAll(spec,pageable);

            if (listaCategorias.isEmpty()){

                throw  new NotFoundException("No se encontraron resultados");
            }

        return listaCategorias.map(categoriaMapper::categoriaToCategoriaListaResponseDto);
    }

    @Transactional
    @Override
    public CategoriaCrearResponseDto crearCategoria(CategoriaCrearRequestDto dto) throws BadRequestException {

        if (categoriaRepository.existsByNombreIgnoreCase(dto.getNombre())) {
            throw new BadRequestException("El nombre de la categoría '" + dto.getNombre() + "' ya existe.");
        }

        Categoria categoria= categoriaMapper.categoriaCrearRequestDtoToCategoria(dto);

        categoria.setActivo(true);
        categoria.setFechaCreacion(LocalDateTime.now());
        categoria.setFechaActualizacion(LocalDateTime.now());

        Categoria categoriaGuardado = categoriaRepository.save(categoria);

        return categoriaMapper.categoriaToCategoriaCrearResponseDto(categoriaGuardado);
    }

    @Transactional
    @Override
    public void editarCategoria(Long id, CategoriaEditarRequestDto dto) throws NotFoundException, BadRequestException {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La categoría no existe"));


        if (Boolean.TRUE.equals(categoria.getActivo()) && Boolean.FALSE.equals(dto.getActivo()) && !categoria.getListaProductos().isEmpty()) {

            throw new BadRequestException("No se puede desactivar la marca por que aún tiene productos asociados a ella");

        }

        categoriaMapper.categoriaEditarRequestDtoToCategoria(dto,categoria);

        categoria.setFechaActualizacion(LocalDateTime.now());

        categoriaRepository.save(categoria);

    }

    @Transactional
    @Override
    public void eliminarCategoria(Long id) throws NotFoundException, BadRequestException {

        Categoria categoriaEncontrada=categoriaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("No se ha encontrado la categoria en el sistema"));

        if (Boolean.TRUE.equals(categoriaEncontrada.getActivo()) && !categoriaEncontrada.getListaProductos().isEmpty()){

            throw new BadRequestException("No se puede eliminar esta categoría ya que aún posee productos asociados");
        }

        categoriaRepository.deleteById(id);

    }

}
