package com.henriqueaguiar.libraryapi.repository;

import com.henriqueaguiar.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

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
}
