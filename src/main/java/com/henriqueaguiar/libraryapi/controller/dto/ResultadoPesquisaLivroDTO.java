package com.henriqueaguiar.libraryapi.controller.dto;

import com.henriqueaguiar.libraryapi.model.Autor;
import com.henriqueaguiar.libraryapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaLivroDTO(UUID id, String titulo, LocalDate dataPublicacao, GeneroLivro genero, BigDecimal preco, AutorDTO autor) {


}
