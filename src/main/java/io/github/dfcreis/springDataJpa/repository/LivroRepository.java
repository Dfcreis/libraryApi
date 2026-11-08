package io.github.dfcreis.springDataJpa.repository;

import io.github.dfcreis.springDataJpa.model.Autor;
import io.github.dfcreis.springDataJpa.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

}
