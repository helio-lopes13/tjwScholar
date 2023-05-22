package br.edu.ifce.tjwScholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.tjwScholar.model.Estudante;

public interface Estudantes extends JpaRepository<Estudante, Long> {

}
