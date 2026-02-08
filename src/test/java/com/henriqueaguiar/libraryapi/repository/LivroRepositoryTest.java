package com.henriqueaguiar.libraryapi.repository;

import com.henriqueaguiar.libraryapi.model.Autor;
import com.henriqueaguiar.libraryapi.model.GeneroLivro;
import com.henriqueaguiar.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTeste() {
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository.findById(UUID.fromString("d01ebbf6-6146-416d-83d4-1258b27807a3")).orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarAutorELivroTeste() {
        Livro livro = new Livro();
        livro.setIsbn("9066666887-8asdsad");
        livro.setPreco(BigDecimal.valueOf(200));
        livro.setGenero(GeneroLivro.FANTASIA);
        livro.setTitulo("Harry Potter");
        livro.setDataPublicacao(LocalDate.of(2000, 10, 5));

        Autor autor = new Autor();
        autor.setNome("Yasmin Mendes");
        autor.setNacionalidade("Florida");
        autor.setDataNascimento(LocalDate.of(1996, 7, 20));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);

        System.out.println("Salvo com sucesso: " + livro + autor );
    }

    @Test
    void salvarCascadeTeste() {
        Livro livro = new Livro();
        livro.setIsbn("90887-8asdsad");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("New Book");
        livro.setDataPublicacao(LocalDate.of(1200, 1, 30));

        Autor autor = new Autor();
        autor.setNome("Fernanda");
        autor.setNacionalidade("Japan");
        autor.setDataNascimento(LocalDate.of(1982, 10, 19));

        livro.setAutor(autor);

        livroRepository.save(livro);

        System.out.println("Salvo com sucesso: " + livro + autor );
    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("e215dee6-e7b1-4f0d-9d33-89d4126a6946");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("60856689-7ecb-404f-9616-f6277d916c73");
        Autor fernanda = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(fernanda);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("48412f80-62a4-45ba-9f3d-1ad602b40e81");
        livroRepository.deleteById(id);
    }

    @Test
    void BuscarLivroTeste(){
        UUID id = UUID.fromString("e215dee6-e7b1-4f0d-9d33-89d4126a6946");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());

    }

}