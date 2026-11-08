package io.github.dfcreis.springDataJpa.repository;

import io.github.dfcreis.springDataJpa.model.Autor;
import io.github.dfcreis.springDataJpa.model.GeneroLivro;
import io.github.dfcreis.springDataJpa.model.Livro;
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
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2026, 10,31));

        Autor altorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + altorSalvo );
    }

    @Test
    public void atualizarTest() {

        var id = UUID.fromString("cdc10836-b006-409f-b21c-78dfe94a792c");
        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do autor: " + autorEncontrado);


            autorEncontrado.setNome("Daniel");
            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTodosTest() {

        List<Autor> list = autorRepository.findAll();
        list.forEach(System.out::println);
    }

    @Test
    public void countTest(){

        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletarPorIdTest() {

        var id = UUID.fromString("85269ba5-adf2-4872-8afe-b3ee30e58962");
        autorRepository.deleteById(id);
    }

    @Test
    public void deletarTest() {

        var id = UUID.fromString("cdc10836-b006-409f-b21c-78dfe94a792c");
        Autor maria = autorRepository.findById(id).get();
        autorRepository.delete(maria);
    }

    @Test
    void salvarAutorComLivroTest(){
        Autor autor = new Autor();
        autor.setNome("Carlos");
        autor.setNacionalidade("Argentino");
        autor.setDataNascimento(LocalDate.of(1889,3,8));

        Livro livro1 = new Livro();
        livro1.setIsbn("1234-4566");
        livro1.setPreco(BigDecimal.valueOf(250));
        livro1.setGenero(GeneroLivro.ROMANCE);
        livro1.setTitulo("Rapunzel");
        livro1.setData_publicacao(LocalDate.of(1894,4,11));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("4567-1234");
        livro2.setPreco(BigDecimal.valueOf(490));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("A casa ao lado");
        livro2.setData_publicacao(LocalDate.of(1895,4,11));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        autorRepository.save(autor);
        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    @Transactional
    void listarLivrosAutor(){
        var id = UUID.fromString("829b904d-eab2-4d7b-bfd6-4678c2aadfb1");
        var autor = autorRepository.findById(id).get();

        List<Livro> listLivros = livroRepository.findByAutor(autor);
        autor.setLivros(listLivros);
        autor.getLivros().forEach(System.out::println);
    }




}
