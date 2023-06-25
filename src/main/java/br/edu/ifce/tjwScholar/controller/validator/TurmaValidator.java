package br.edu.ifce.tjwScholar.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.edu.ifce.tjwScholar.model.Turma;

@Component
public class TurmaValidator implements Validator {

	@Override
	public boolean supports(Class<?> cls) {
		return Turma.class.isAssignableFrom(cls);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "professor.id", "", "Selecione um professor");
		ValidationUtils.rejectIfEmpty(errors, "nomeDisciplina", "", "Disciplina é um campo obrigatório");
	}
}
