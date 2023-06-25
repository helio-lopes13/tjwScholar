package br.edu.ifce.tjwScholar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.tjwScholar.model.Professor;

public interface Professores extends JpaRepository<Professor, Long> {
	List<Professor> findByNomeStartingWithIgnoreCase(String nome);

	Optional<Professor> findByMatricula(String matricula);
}
