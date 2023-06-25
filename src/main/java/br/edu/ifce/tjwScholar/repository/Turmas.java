package br.edu.ifce.tjwScholar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.tjwScholar.model.Turma;

public interface Turmas extends JpaRepository<Turma, Long> {

	Optional<Turma> findByNomeDisciplinaIgnoreCase(String nomeDisciplina);

}
