package com.henriqueaguiar.libraryapi.repository;

import com.henriqueaguiar.libraryapi.model.Autor;
import com.henriqueaguiar.libraryapi.model.GeneroLivro;
import com.henriqueaguiar.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    // Query Method

    // select * from livro where id id_autor = id
    List<Livro> findByAutor(Autor autor);

    // select * from titulo = ?
    List<Livro> findByTitulo (String titulo);

    // select * from livro where isbn = ?
    List<Livro> findByIsbn (String isbn);

    // select * from titulo = ? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    // select * from titulo = ? or isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    // select * from livro where data_publicacao between ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate dataInicio, LocalDate dataFim);

    // JPQL -> referencia as entidades e as propriedades
    // select l.* from livro as l order by l.titulo
    @Query("select l from Livro as l order by l.titulo, l.preco")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    @Query("select a from Livro l join l.autor a")
    List<Autor> listarAutoresDosLivros();

    // select distinct l.* from livro l
    @Query("select distinct l.titulo from Livro l")
    List<String>listarNomesDiferentesLivros();

    @Query("""
        select l.genero
        from Livro l
        join l.autor a
        where a.nacionalidade = 'Florida'
        order by l.genero
    """)
    List<String> listarNacionalidadeDiferentesLivros();

    @Modifying
    @Transactional
    @Query("delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro genero);

    @Modifying
    @Transactional
    @Query("update Livro set dataPublicacao = ?1")
    void updateDataPublicacao(LocalDate dataInicio);
}
