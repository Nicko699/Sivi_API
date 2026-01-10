package org.team.sivi.Service.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.team.sivi.Dto.CategoriaDto.CategoriaListaResponseDto;
import org.team.sivi.Exception.NotFoundException;
import org.team.sivi.Mapper.CategoriaMapper;
import org.team.sivi.Model.Categoria;
import org.team.sivi.Repository.CategoriaRepository;
import org.team.sivi.Specifications.CategoriaSpecifications;

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

}
