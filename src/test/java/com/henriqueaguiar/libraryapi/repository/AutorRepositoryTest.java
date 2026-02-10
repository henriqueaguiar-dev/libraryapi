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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Henrique");
        autor.setNacionalidade("Italy");
        autor.setDataNascimento(LocalDate.of(1982, 10, 16));

        var autorSalvo = autorRepository.save(autor);

        System.out.println("Registro salvo com sucesso!" + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("7c3e5821-882c-45aa-a390-a3c3571e2f35");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Registro atualizado com sucesso!");
            System.out.println(possivelAutor.get());

            autorEncontrado.setNome("Carlos");
            autorEncontrado.setDataNascimento(LocalDate.of(2020, 10, 3));
            autorEncontrado.setNacionalidade("United States");

            autorRepository.save(autorEncontrado);
        }

    }

    @Test
    public void listarTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("7c3e5821-882c-45aa-a390-a3c3571e2f35");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("3ea8f5d2-b3cc-4772-b2c2-35ddc1fcec74");
        var Henrique = autorRepository.findById(id).get();
        autorRepository.delete(Henrique);
    }

    @Test
    public void salvarAutorTest(){
        Autor autor = new Autor();
        autor.setNome("Leiah");
        autor.setNacionalidade("American");
        autor.setDataNascimento(LocalDate.of(1980, 1, 5));

        Livro livro2 = new Livro();
        livro2.setIsbn("90666fddf87-8asdsad");
        livro2.setPreco(BigDecimal.valueOf(530));
        livro2.setGenero(GeneroLivro.BIOGRAFIA);
        livro2.setTitulo("Henrique no EUA");
        livro2.setDataPublicacao(LocalDate.of(2000, 10, 5));
        livro2.setAutor(autor);

        Livro livro = new Livro();
        livro.setIsbn("dd333-8asdsad");
        livro.setPreco(BigDecimal.valueOf(83));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Descoberta do Horizonte");
        livro.setDataPublicacao(LocalDate.of(2005, 10, 5));
        livro.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);

        livroRepository.saveAll(autor.getLivros());

    }

    @Test
    @Transactional
    public void listarLivrosAutor(){
        var id = UUID.fromString("6aeec187-9561-4f39-a0bc-e3d1fdcdf10f");
        var autor = autorRepository.findById(id).get();

        // buscar os livro do autor
        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }

    @Test
    public void PesquisaPorTiluloTeste(){
        List<Livro> lista = livroRepository.findByTitulo("Harry Potter");
        lista.forEach(System.out::println);
    }

    @Test
    public void PesquisaPorISBNTeste(){
        List<Livro> lista = livroRepository.findByIsbn("dd333-8asdsad");
        lista.forEach(System.out::println);
    }

    @Test
    public void PesquisaPorTituloEPrecoTeste(){
        var preco = BigDecimal.valueOf(83);
        var titulo = "Descoberta do Horizonte";

        List<Livro> lista = livroRepository.findByIsbn(preco + titulo);
        lista.forEach(System.out::println);
    }

}
