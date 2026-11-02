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

        Autor autor = new Autor();
        autor.setNome("Carlos");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2026, 10,31));

        autorRepository.save(autor);

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

    @Test
    void atualizarAutorLivro(){

        UUID idLivro = UUID.fromString("756e81ce-dfbf-47d8-847b-f5a5d8a548ee");
        Livro livroParaAtualizar = repository.findById(idLivro).orElse(null);
        livroParaAtualizar.setTitulo("IT a coisa");

        UUID idAutor = UUID.fromString("8fd7bfa2-bac6-4b34-a245-ca39a386e4db");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        repository.save(livroParaAtualizar);


    }




}
