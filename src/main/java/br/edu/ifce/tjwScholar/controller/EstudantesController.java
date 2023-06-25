package br.edu.ifce.tjwScholar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifce.tjwScholar.exception.EstudanteMatriculaJaCadastradaException;
import br.edu.ifce.tjwScholar.model.Estudante;
import br.edu.ifce.tjwScholar.service.EstudanteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/estudante")
public class EstudantesController {

	@Autowired
	private EstudanteService estudanteService;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Estudante estudante) {
		ModelAndView modelView = new ModelAndView("estudante/CadastroEstudante");

		return modelView;
	}

	@PostMapping({ "/cadastrar", "{estudante:\\d+}" })
	public ModelAndView salvar(@Valid Estudante estudante, BindingResult result,
			RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastrar(estudante);
		}

		Estudante estudanteCadastradu = estudante;
		try {
			estudanteCadastradu = estudanteService.salvar(estudante);
		} catch (EstudanteMatriculaJaCadastradaException exception) {
			result.rejectValue("matricula", exception.getMessage(), exception.getMessage());
			return cadastrar(estudante);
		}

		attributes.addFlashAttribute("mensagem", "Cadastro de estudante realizado com sucesso!");
		return new ModelAndView("redirect:/estudante/" + estudanteCadastradu.getId());
	}
	
	@GetMapping("/{estudante}")
	public ModelAndView editar(Estudante estudante) {
		ModelAndView mv = cadastrar(estudante);
		mv.addObject(estudante);
		return mv;
	}
}
