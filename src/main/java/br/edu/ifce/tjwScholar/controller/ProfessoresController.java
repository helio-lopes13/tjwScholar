package br.edu.ifce.tjwScholar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/professor")
public class ProfessoresController {

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok("You can like the life your living, you can live the life you like...");
	}
}
