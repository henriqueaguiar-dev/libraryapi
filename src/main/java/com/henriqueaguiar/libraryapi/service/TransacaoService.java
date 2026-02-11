package com.henriqueaguiar.libraryapi.service;

import com.henriqueaguiar.libraryapi.model.Autor;
import com.henriqueaguiar.libraryapi.model.GeneroLivro;
import com.henriqueaguiar.libraryapi.model.Livro;
import com.henriqueaguiar.libraryapi.repository.AutorRepository;
import com.henriqueaguiar.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar (){

        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Victoria");
        autor.setNacionalidade("England");
        autor.setDataNascimento(LocalDate.of(1900, 10, 19));

        autorRepository.save(autor);

        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("966587-8asdsad");
        livro.setPreco(BigDecimal.valueOf(150));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Vivendo na America");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 30));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if (autor.getNome().equals("Victoria")) {
            throw new RuntimeException("Rollback!");
        }
    }
}
