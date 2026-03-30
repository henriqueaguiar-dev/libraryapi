package com.henriqueaguiar.libraryapi.validator;

import com.henriqueaguiar.libraryapi.exceptions.CampoInvalidoException;
import com.henriqueaguiar.libraryapi.exceptions.RegistroDuplicadoException;
import com.henriqueaguiar.libraryapi.model.Livro;
import com.henriqueaguiar.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidator {

    private static final int ANO_EXIGENCIA_PRECO = 2020;

    private final LivroRepository repository;

    public void validar(Livro livro){
        if(existeLivroComIsbn(livro)){
            throw new RegistroDuplicadoException("ISBN já cadastrado");
        }

        if(isPrecoObrigatorioNulo(livro)){
            throw new CampoInvalidoException("preco", "Para livros com ano de publicação a partir do ano 2020, o preço é obrigatório");
        }
    }

    private boolean isPrecoObrigatorioNulo(Livro livro) {
        return livro.getPreco() == null && livro.getDataPublicacao().getYear() >= ANO_EXIGENCIA_PRECO;
    }

    private boolean existeLivroComIsbn(Livro livro) {
        Optional<Livro>  livroEcontrado = repository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null){
            return livroEcontrado.isPresent();
        }

        return livroEcontrado
                .map(Livro::getId)
                .stream()
                .anyMatch(id -> !id.equals(livro.getId()));
    }

}
