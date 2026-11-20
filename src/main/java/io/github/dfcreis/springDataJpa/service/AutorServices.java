package io.github.dfcreis.springDataJpa.service;

import io.github.dfcreis.springDataJpa.model.Autor;
import io.github.dfcreis.springDataJpa.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorServices {

    private final AutorRepository repository;

    public AutorServices(AutorRepository repository) {
        this.repository = repository;
    }

    public Autor salvar(Autor autor){
        return repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id){
        return repository.findById(id);
    }


}
