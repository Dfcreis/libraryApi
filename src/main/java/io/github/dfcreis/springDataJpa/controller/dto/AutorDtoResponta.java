package io.github.dfcreis.springDataJpa.controller.dto;

import io.github.dfcreis.springDataJpa.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDtoResponta(
        UUID id,
        String nome,
        LocalDate dataNascimento,
        String nacionalidade
) {

    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setId(this.id);
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;
    }
}
