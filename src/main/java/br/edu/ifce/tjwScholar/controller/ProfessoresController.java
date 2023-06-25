package br.edu.ifce.tjwScholar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import br.edu.ifce.tjwScholar.exception.EstudanteMatriculaJaCadastradaException;
import br.edu.ifce.tjwScholar.exception.ImpossivelExcluirEntidadeException;
import br.edu.ifce.tjwScholar.model.Professor;
import br.edu.ifce.tjwScholar.service.ProfessorService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/professor")
public class ProfessoresController {

	@Autowired
	private ProfessorService professorService;

	@GetMapping("/cadastrar")
	public ModelAndView cadastrar(Professor professor) {
		return new ModelAndView("professor/CadastroProfessor");
	}

	@PostMapping({ "/cadastrar", "{professor:\\d+}" })
	public ModelAndView salvar(@Valid Professor professor, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return cadastrar(professor);
		}

		Professor professorCadastradu = professor;
		try {
			professorCadastradu = professorService.salvar(professor);
		} catch (EstudanteMatriculaJaCadastradaException exception) {
			result.rejectValue("matricula", exception.getMessage(), exception.getMessage());
			return cadastrar(professor);
		}

		attributes.addFlashAttribute("mensagem", "Cadastro de professor realizado com sucesso!");
		return new ModelAndView("redirect:/professor/" + professorCadastradu.getId());
	}

	@GetMapping("/{professor}")
	public ModelAndView editar(Professor professor) {
		ModelAndView mv = cadastrar(professor);
		mv.addObject(professor);
		return mv;
	}
	
	@GetMapping
	public ModelAndView pesquisar() {
		ModelAndView mv = new ModelAndView("professor/ListaProfessores");
		mv.addObject("professores", professorService.buscarTodos());
		return mv;
	}
	
	@DeleteMapping("/{professor}")
	public @ResponseBody ResponseEntity<?> excluir(Professor professor) {
		try {
			professorService.excluir(professor);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Professor> pesquisar(String nome) {
		validarTamanhoNome(nome);
		return professorService.buscarPorNomeInicial(nome);
	}

	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
}
