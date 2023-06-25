package br.edu.ifce.tjwScholar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.tjwScholar.model.Estudante;

public interface Estudantes extends JpaRepository<Estudante, Long> {

	Optional<Estudante> findByMatricula(String matricula);
}
