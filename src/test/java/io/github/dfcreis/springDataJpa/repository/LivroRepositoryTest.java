package io.github.dfcreis.springDataJpa.repository;

import io.github.dfcreis.springDataJpa.model.Autor;
import io.github.dfcreis.springDataJpa.model.GeneroLivro;
import io.github.dfcreis.springDataJpa.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarLivroTest() {

        Livro livro = new Livro();
        livro.setTitulo("31 dias para te amar");
        livro.setIsbn("979-10-39574-20-4");
        livro.setPreco(BigDecimal.valueOf(75));
        livro.setData_publicacao(LocalDate.of(2025,5,24));
        livro.setGenero(GeneroLivro.ROMANCE);

       Autor autor = autorRepository
               .findById(UUID.fromString("8fd7bfa2-bac6-4b34-a245-ca39a386e4db"))
               .orElse(null);

        livro.setAutor(autor);

        repository.save(livro);


    }

    @Test
    public void salvarCascadeTeste () {
        Livro livro = new Livro();
        livro.setTitulo("31 dias para te amar");
        livro.setIsbn("979-10-39574-20-4");
        livro.setPreco(BigDecimal.valueOf(75));
        livro.setData_publicacao(LocalDate.of(2025,5,24));
        livro.setGenero(GeneroLivro.ROMANCE);

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2026, 10,31));

        livro.setAutor(autor);

        repository.save(livro);
    }




}
