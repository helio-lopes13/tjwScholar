package br.edu.ifce.tjwScholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifce.tjwScholar.controller.validator.TurmaValidator;
import br.edu.ifce.tjwScholar.exception.EstudanteMatriculaJaCadastradaException;
import br.edu.ifce.tjwScholar.exception.ImpossivelExcluirEntidadeException;
import br.edu.ifce.tjwScholar.model.Turma;
import br.edu.ifce.tjwScholar.service.TurmaService;

@Controller
@RequestMapping("/turma")
public class TurmasController {

	@Autowired
	private TurmaService turmaService;

	@Autowired
	private TurmaValidator turmaValidator;

	@InitBinder("turma")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(turmaValidator);
	}

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Turma turma) {
		return new ModelAndView("turma/CadastroTurma");
	}

	@PostMapping({ "/cadastrar", "{turma:\\d+}" })
	public ModelAndView salvar(Turma turma, BindingResult result, RedirectAttributes attributes) {
		turmaValidator.validate(turma, result);
		if (result.hasErrors()) {
			return cadastrar(turma);
		}

		Turma turmaCadastrada = turma;
		try {
			turmaCadastrada = turmaService.salvar(turma);
		} catch (EstudanteMatriculaJaCadastradaException exception) {
			result.rejectValue("matricula", exception.getMessage(), exception.getMessage());
			return cadastrar(turma);
		}

		attributes.addFlashAttribute("mensagem", "Cadastro de turma realizado com sucesso!");
		return new ModelAndView("redirect:/turma/" + turmaCadastrada.getId());
	}

	@GetMapping("/{turma}")
	public ModelAndView editar(Turma turma) {
		ModelAndView mv = cadastrar(turma);
		mv.addObject(turma);
		return mv;
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("turma/ListaTurmas");
		mv.addObject("turmas", turmaService.buscarTodos());
		return mv;
	}
	
	@DeleteMapping("/{turma}")
	public @ResponseBody ResponseEntity<?> excluir(Turma turma) {
		try {
			turmaService.excluir(turma);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
}
