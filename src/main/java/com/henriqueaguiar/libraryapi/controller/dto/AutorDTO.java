package com.henriqueaguiar.libraryapi.controller.dto;


import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "campo obrigatorio")
        @Size(min = 2, max = 100, message = "campo fora do tamanho padrão")
        String nome,
        @NotNull(message = "campo obrigatorio")
        @Past(message = "não pode ser uma data futura")
        LocalDate dataNascimento,
        @NotBlank(message = "campo obrigatorio")
        @Size(min = 2, max = 50, message = "Campo fora do tamanho patrão")
        String nacionalidade) {

}
