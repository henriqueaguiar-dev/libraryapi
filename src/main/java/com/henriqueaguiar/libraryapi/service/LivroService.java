package com.henriqueaguiar.libraryapi.service;

import com.henriqueaguiar.libraryapi.model.GeneroLivro;
import com.henriqueaguiar.libraryapi.model.Livro;
import com.henriqueaguiar.libraryapi.repository.LivroRepository;
import com.henriqueaguiar.libraryapi.repository.specs.LivroSpecs;
import com.henriqueaguiar.libraryapi.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.henriqueaguiar.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroValidator validator;

    public Livro salvar(Livro livro) {
        validator.validar(livro);
       return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro){
        livroRepository.delete(livro);
    }

    public Page<Livro> pesquisa(
            String isbn, String nomeAutor, String titulo, GeneroLivro genero, Integer anoPublicacao, Integer pagina, Integer tamanhoPagina){

        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if(isbn != null){
            specs = specs.and(isbnEqual(isbn));
        }

        if(titulo != null){
            specs = specs.and(tituloLike(titulo));
        }

        if(genero != null){
            specs = specs.and(generoEqual(genero));
        }

        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        if(nomeAutor !=null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        Pageable pagerequest = PageRequest.of(pagina, tamanhoPagina);

        return livroRepository.findAll(specs, pagerequest);
    }

    public void atualizar(Livro livro) {
        if(livro.getAutor().getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o livro já esteja salvo na base.");
        }
        validator.validar(livro);
        livroRepository.save(livro);
    }
}
