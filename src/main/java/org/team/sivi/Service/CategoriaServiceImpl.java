package org.team.sivi.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.team.sivi.Dto.CategoriaDto.CategoriaCrearRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaEditarRequestDto;
import org.team.sivi.Dto.CategoriaDto.CategoriaResponseDto;
import org.team.sivi.Exception.BadRequestException;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.CategoriaMapper;
import org.team.sivi.Model.Categoria;
import org.team.sivi.Repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    @Transactional
    public CategoriaResponseDto crear(CategoriaCrearRequestDto dto) throws BadRequestException {
        // Regla 1: Nombre único
        if (categoriaRepository.existsByNombre(dto.nombre())) {
            throw new BadRequestException("Ya existe una categoría con el nombre: " + dto.nombre());
        }

        Categoria categoria = categoriaMapper.requestDtoToCategoria(dto);
        return categoriaMapper.categoriaToResponseDto(categoriaRepository.save(categoria));
    }
    
    @Override
    @Transactional
    public CategoriaResponseDto editar(Long id, CategoriaEditarRequestDto dto) throws BadRequestException, NotFoundException {
        Categoria categoria = categoriaRepository.findById(id)
        		.orElseThrow(() -> new NotFoundException("La categoría no existe"));
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());
        categoriaRepository.save(categoria);
        return categoriaMapper.categoriaToResponseDto(categoria);
    }


    @Override
    @Transactional
    public void eliminar(Long id) throws NotFoundException, BadRequestException {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("La categoría no existe"));

        // Regla 2: No eliminar si tiene productos (Solo si ya existe la relación en el Model)
        // Por ahora, como el manual dice que la tabla productos no existe aún,
        // dejamos este espacio para cuando Jeymy termine Productos.

        categoriaRepository.delete(categoria);
    }
}