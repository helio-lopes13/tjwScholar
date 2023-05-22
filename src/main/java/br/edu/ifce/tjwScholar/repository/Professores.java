package br.edu.ifce.tjwScholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.tjwScholar.model.Professor;

public interface Professores extends JpaRepository<Professor, Long> {

}
