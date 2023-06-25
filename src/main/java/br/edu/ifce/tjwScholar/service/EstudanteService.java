package br.edu.ifce.tjwScholar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifce.tjwScholar.exception.EstudanteMatriculaJaCadastradaException;
import br.edu.ifce.tjwScholar.exception.ImpossivelExcluirEntidadeException;
import br.edu.ifce.tjwScholar.model.Estudante;
import br.edu.ifce.tjwScholar.repository.Estudantes;
import jakarta.persistence.PersistenceException;

@Service
public class EstudanteService {
	
	@Autowired
	private Estudantes estudantes;
	
	public List<Estudante> buscarTodos() {
		return estudantes.findAll();
	}
	
	@Transactional
	public Estudante salvar(Estudante estudante) {
		Optional<Estudante> estudanteOptional = estudantes.findByMatricula(estudante.getMatricula());
		
		if (estudanteOptional.isPresent() && !estudanteOptional.get().equals(estudante)) {
			throw new EstudanteMatriculaJaCadastradaException("Matrícula já está cadastrada!");
		}
		estudante = estudantes.saveAndFlush(estudante);
		
		return estudante;
	}
	
	@Transactional
	public void excluir(Estudante estudante) {
		try {
			estudantes.delete(estudante);
			estudantes.flush();
		} catch (PersistenceException exception) {
			throw new ImpossivelExcluirEntidadeException("Não foi possível excluir este registro de professor!");
		}
	}
}
