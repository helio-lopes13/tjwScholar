package br.edu.ifce.tjwScholar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifce.tjwScholar.exception.ImpossivelExcluirEntidadeException;
import br.edu.ifce.tjwScholar.exception.TurmaJaCadastradaException;
import br.edu.ifce.tjwScholar.model.Turma;
import br.edu.ifce.tjwScholar.repository.Turmas;
import jakarta.persistence.PersistenceException;

@Service
public class TurmaService {
	
	@Autowired
	private Turmas turmas;
	
	public List<Turma> buscarTodos() {
		return turmas.findAll();
	}
	
	@Transactional
	public Turma salvar(Turma turma) {
		Optional<Turma> turmaOptional = turmas.findByNomeDisciplinaIgnoreCase(turma.getNomeDisciplina());
		
		if (turmaOptional.isPresent() && !turmaOptional.get().equals(turma)) {
			throw new TurmaJaCadastradaException("Matrícula já está cadastrada!");
		}
		turma = turmas.saveAndFlush(turma);
		
		return turma;
	}
	
	@Transactional
	public void excluir(Turma turma) {
		try {
			turmas.delete(turma);
			turmas.flush();
		} catch (PersistenceException exception) {
			throw new ImpossivelExcluirEntidadeException("Não foi possível excluir este registro de professor!");
		}
	}
}
