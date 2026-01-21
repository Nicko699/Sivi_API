package org.team.sivi.Service.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.CategoriaMapper;
import org.team.sivi.Model.Categoria;
import org.team.sivi.Repository.CategoriaRepository;
import org.team.sivi.Repository.ProductoRepository;
import org.team.sivi.Specifications.CategoriaSpecifications;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository,
                                ProductoRepository productoRepository,
                                CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public Page<CategoriaListaResponseDto> filtrarCategorias(String nombre, Boolean activo, Pageable pageable) throws NotFoundException {
        Specification<Categoria> spec = Specification.allOf(CategoriaSpecifications.nombreLike(nombre)
                .and(CategoriaSpecifications.activoEqual(activo)));

        Page<Categoria> listaCategorias = categoriaRepository.findAll(spec, pageable);

        if (listaCategorias.isEmpty()){
            throw new NotFoundException("No se encontraron resultados");
        }

        return listaCategorias.map(categoriaMapper::categoriaToCategoriaListaResponseDto);
    }

    // --- TAREA JULIANA: CREAR ---
    @Override
    public CategoriaResponseDto crearCategoria(CategoriaCrearRequestDto dto) throws BadRequestException { // <--- SOLUCIÓN: Agregado throws
        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new BadRequestException("El nombre de la categoría '" + dto.getNombre() + "' ya existe.");
        }

        Categoria entidad = categoriaMapper.categoriaCrearRequestDtoToCategoria(dto);
        entidad.setActivo(true);

        Categoria guardado = categoriaRepository.save(entidad);
        return categoriaMapper.categoriaToCategoriaResponseDto(guardado);
    }

    // --- TAREA JULIANA: ELIMINAR ---
    @Override
    public void eliminarCategoria(Long id) throws NotFoundException, BadRequestException { // <--- SOLUCIÓN: Agregado throws
        if (!categoriaRepository.existsById(id)) {
            throw new NotFoundException("La categoría con ID " + id + " no existe.");
        }

        if (productoRepository.existsByCategoriaId(id)) {
            throw new BadRequestException("No se puede eliminar la categoría porque tiene productos asociados.");
        }

        categoriaRepository.deleteById(id);
    }
}