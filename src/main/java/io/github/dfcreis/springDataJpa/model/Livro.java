package io.github.dfcreis.springDataJpa.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livros")
@Data
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name= "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate data_publicacao;

    @Enumerated(EnumType.STRING)
    @Column(name= "genero", nullable = false)
    private GeneroLivro genero;

    @Column(name= "preco",precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne//muitos para um (Um autor pode ter varios livros)
    @JoinColumn(name= "id_autor")
    private Autor autor;


}
