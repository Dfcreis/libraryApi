package io.github.dfcreis.springDataJpa.repository;

import io.github.dfcreis.springDataJpa.model.Autor;
import io.github.dfcreis.springDataJpa.model.GeneroLivro;
import io.github.dfcreis.springDataJpa.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepositoryTest
 */

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    // Query method

    //SELECT * FROM livros WHERE autor = 'autor'
    List<Livro> findByAutor(Autor autor);

    //SELECT * FROM livros WHERE titulo = 'titulo'
    List<Livro> findByTitulo(String titulo);

    //SELECT * FROM livros WHERE isbn = 'isbn'
    List<Livro> findByIsbn(String isbn);

    //SELECT * FROM livros WHERE titulo = 'titulo' AND preco = preco
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //SELECT * FROM livros WHERE titulo = 'titulo' OR isbn = 'isbn'
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    //JPQL -> referencia as entidades e as propriedades
    //SELECT l.* FROM livros as l ORDER BY l.titulo
    @Query("select l from Livro as l order by l.titulo")
    List<Livro> listarTodos();

    @Query("select a from Livro l join l.autor a")
    List<Autor> buscarAutorUsandoQuery();

    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivro();

    @Query("""
    select l.genero
    from Livro l
    join l.autor a
    where a.nacionalidade ='Brasileiro'
    order by l.genero
""")
    List<String> listarGenerosAutorBrasileiros();


    //Named parameters -> parametros nomeados
    @Query("SELECT l FROM Livro l WHERE l.genero = :genero ORDER BY :paramOrdenacao")
    List<Livro> findByGenero(@Param("genero") GeneroLivro genero,
                             @Param("paramOrdenacao")String nomePropriedade);


    //Possitional parameters
    @Query("SELECT l FROM Livro l WHERE l.genero = ?1 ORDER BY ?2")
    List<Livro> findByGeneroPositionalParameters(GeneroLivro genero, String nomePropriedade);

    //Delete
    @Modifying
    @Transactional
    @Query("DELETE FROM Livro WHERE genero = ?1")
    void deleteLivroGenero(GeneroLivro genero);

    //Update
    @Modifying
    @Transactional
    @Query("UPDATE Livro l SET l.titulo = :titulo, l.preco = :preco WHERE id = :id")
    void atualizarLivro(@Param("titulo") String titulo,
                        @Param("preco") BigDecimal preco,
                        @Param("id") UUID id);




}
