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
import java.util.List;
import java.util.UUID;

/**
 * @see LivroRepository
 */

@SpringBootTest
public class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarLivroTest() {

        Livro livro = new Livro();
        livro.setTitulo("Pequeno Principe");
        livro.setIsbn("979-10-39574-20-4");
        livro.setPreco(BigDecimal.valueOf(75));
        livro.setData_publicacao(LocalDate.of(2025,5,24));
        livro.setGenero(GeneroLivro.FANTASIA);

        Autor autor = new Autor();
        autor.setNome("joao");
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

    @Test
    void deletar() {
        UUID idParaDeletar = UUID.fromString("756e81ce-dfbf-47d8-847b-f5a5d8a548ee");
        repository.deleteById(idParaDeletar);

    }

    @Test
    @Transactional
    void buscarLivroCascadeTest() {
         UUID id = UUID.fromString("aeb37f29-2a59-4bd4-832d-7161b2ef8e62");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());

    }

    @Test
    void buscarLivroPorTitulo(){
        List<Livro> listLivro = repository.findByTitulo("A princesa e o sapo");
        listLivro.forEach(System.out::println);

    }
    @Test
    void buscarLivroPorIsbn(){
        List<Livro> listLivro = repository.findByIsbn("4567-1234");
        listLivro.forEach(System.out::println);
    }
    @Test
    void burcarLivroPorTituloEPreco() {
        List<Livro> livroTituloPreco = repository.findByTituloAndPreco("A princesa e o sapo", BigDecimal.valueOf(250));
        livroTituloPreco.forEach(System.out::println);
    }

    @Test
    void buscarLivrosComQueryJPQL(){
        List<Livro> resultados = repository.listarTodos();
        resultados.forEach(System.out::println);

    }

    @Test
    void buscarAutorPorQuery(){
        List<Autor> resultados = repository.buscarAutorUsandoQuery();
        resultados.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros(){
        List<String> strings = repository.listarGenerosAutorBrasileiros();
        strings.forEach(System.out::println);
    }

    @Test
    void listarDiferentesNomesLivros(){
        List<String> list = repository.listarNomesDiferentesLivro();
        list.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest(){
        List<Livro> dataPublicacao = repository.findByGenero(GeneroLivro.MISTERIO, "data_publicacao");
        dataPublicacao.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroPositionalParameters(){
        List<Livro> list = repository.findByGeneroPositionalParameters(GeneroLivro.ROMANCE, "data_publicacao");
        list.forEach(System.out::println);
    }

    @Test
    void deletarLivro(){
        repository.deleteLivroGenero(GeneroLivro.FANTASIA);
    }

    @Test
    void updateLivro(){
        repository.atualizarLivro("Livro Updade",BigDecimal.valueOf(300),UUID.fromString("d28e3189-c5bd-488d-859a-de6a4d8d5d68"));
    }

}
