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
        autor.setNome("Josefa");
        autor.setNacionalidade("United States");
        autor.setDataNascimento(LocalDate.of(1900, 10, 19));

        autorRepository.save(autor);

        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("966587-83345");
        livro.setPreco(BigDecimal.valueOf(30));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Good Dream");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 30));

        livro.setAutor(autor);

        livroRepository.save(livro);

    }
}
