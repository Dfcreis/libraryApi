package io.github.dfcreis.springDataJpa.repository;

import io.github.dfcreis.springDataJpa.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
