package br.edu.ifce.tjwScholar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifce.tjwScholar.model.Estudante;

@Controller
@RequestMapping("/estudante")
public class EstudantesController {

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Estudante estudante) {
		ModelAndView modelView = new ModelAndView("estudante/CadastroEstudante");
		
		return modelView;
	}
}
