package io.github.dfcreis.springDataJpa.controller;

import io.github.dfcreis.springDataJpa.controller.dto.AutorDTO;
import io.github.dfcreis.springDataJpa.controller.dto.AutorDtoResponta;
import io.github.dfcreis.springDataJpa.model.Autor;
import io.github.dfcreis.springDataJpa.service.AutorServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/autores")
//http://localhost:8080/autores
public class AutorController {

    private final AutorServices services;

    public AutorController(AutorServices services) {
        this.services = services;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor) {

        var autorEntidade = autor.mapearParaAutor();
        services.salvar(autorEntidade);

        URI location =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AutorDtoResponta> obterDetalhes(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = services.obterPorId(idAutor);

        if (autorOptional.isPresent()) {

            Autor autor = autorOptional.get();

            AutorDtoResponta dto = new AutorDtoResponta(autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();

    }




}
