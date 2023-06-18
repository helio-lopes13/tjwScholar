package br.edu.ifce.tjwScholar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/turma")
public class TurmasController {

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok("From the Class of 2013... Contestant #502... Veronica DuPlatt...");
	}
}
