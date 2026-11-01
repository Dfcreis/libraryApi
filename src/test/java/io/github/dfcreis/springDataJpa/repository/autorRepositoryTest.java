package io.github.dfcreis.springDataJpa.repository;

import io.github.dfcreis.springDataJpa.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class autorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

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

}
