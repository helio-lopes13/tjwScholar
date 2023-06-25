package br.edu.ifce.tjwScholar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifce.tjwScholar.exception.EstudanteMatriculaJaCadastradaException;
import br.edu.ifce.tjwScholar.model.Estudante;
import br.edu.ifce.tjwScholar.repository.Estudantes;

@Service
public class EstudanteService {
	
	@Autowired
	private Estudantes estudantes;
	
	@Transactional
	public Estudante salvar(Estudante estudante) {
		Optional<Estudante> estudanteOptional = estudantes.findByMatricula(estudante.getMatricula());
		
		if (estudanteOptional.isPresent() && !estudanteOptional.get().equals(estudante)) {
			throw new EstudanteMatriculaJaCadastradaException("Matrícula já está cadastrada!");
		}
		estudante = estudantes.saveAndFlush(estudante);
		
		return estudante;
	}
}
