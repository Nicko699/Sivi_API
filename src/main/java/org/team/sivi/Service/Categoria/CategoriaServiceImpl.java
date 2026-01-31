package org.team.sivi.Service.Categoria;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
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
    public CategoriaResponseDto crearCategoria(CategoriaCrearRequestDto dto) throws BadRequestException {
        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new BadRequestException("El nombre de la categoría '" + dto.getNombre() + "' ya existe.");
        }

        Categoria entidad = categoriaMapper.categoriaCrearRequestDtoToCategoria(dto);
        entidad.setActivo(true);
        entidad.setFechaCreacion(LocalDateTime.now());
        entidad.setFechaActualizacion(LocalDateTime.now());

        Categoria guardado = categoriaRepository.save(entidad);
        return categoriaMapper.categoriaToCategoriaResponseDto(guardado);
    }

    // --- TAREA JEYMY: EDITAR  ---
    @Override
    public CategoriaResponseDto editar(Long id, CategoriaEditarRequestDto dto) throws NotFoundException, BadRequestException {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La categoría no existe"));

        // Validamos que el nuevo nombre no lo tenga otra categoría (tu mejora de hoy)
        if (categoriaRepository.existsByNombreAndIdNot(dto.getNombre(), id)) {
            throw new BadRequestException("El nombre '" + dto.getNombre() + "' ya pertenece a otra categoría.");
        }

        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria.setActivo(dto.getActivo());
        categoria.setFechaActualizacion(LocalDateTime.now());

        Categoria guardada = categoriaRepository.save(categoria);
        return categoriaMapper.categoriaToCategoriaResponseDto(guardada);
    }

    // --- TAREA JULIANA: ELIMINAR ---
    @Override
    public void eliminarCategoria(Long id) throws NotFoundException, BadRequestException {
        if (!categoriaRepository.existsById(id)) {
            throw new NotFoundException("La categoría con ID " + id + " no existe.");
        }

        if (productoRepository.existsByCategoriaId(id)) {
            throw new BadRequestException("No se puede eliminar la categoría porque tiene productos asociados.");
        }

        categoriaRepository.deleteById(id);
    }
}