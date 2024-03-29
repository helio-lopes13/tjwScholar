package br.edu.ifce.tjwScholar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifce.tjwScholar.exception.EstudanteMatriculaJaCadastradaException;
import br.edu.ifce.tjwScholar.exception.ImpossivelExcluirEntidadeException;
import br.edu.ifce.tjwScholar.model.Professor;
import br.edu.ifce.tjwScholar.repository.Professores;
import jakarta.persistence.PersistenceException;

@Service
public class ProfessorService {
	
	@Autowired
	private Professores professores;
	
	public List<Professor> buscarTodos() {
		return professores.findAll();
	}
	
	@Transactional
	public Professor salvar(Professor professor) {
		Optional<Professor> professorOptional = professores.findByMatricula(professor.getMatricula());
		
		if (professorOptional.isPresent() && !professorOptional.get().equals(professor)) {
			throw new EstudanteMatriculaJaCadastradaException("Matrícula já está cadastrada!");
		}
		professor = professores.saveAndFlush(professor);
		
		return professor;
	}
	
	@Transactional
	public void excluir(Professor professor) {
		try {
			professores.delete(professor);
			professores.flush();
		} catch (PersistenceException exception) {
			throw new ImpossivelExcluirEntidadeException("Não foi possível excluir este registro de professor!");
		}
	}
	
	public List<Professor> buscarPorNomeInicial(String nome) {
		return professores.findByNomeStartingWithIgnoreCase(nome);
	}
}
