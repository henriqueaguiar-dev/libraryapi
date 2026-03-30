package com.henriqueaguiar.libraryapi.controller.mappers;

import com.henriqueaguiar.libraryapi.controller.dto.AutorDTO;
import com.henriqueaguiar.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source = "nome", target = "nome")
    Autor toEntity(AutorDTO dto);

    AutorDTO toDto(Autor autor);
}
