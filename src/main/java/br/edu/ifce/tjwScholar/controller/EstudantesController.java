package br.edu.ifce.tjwScholar.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estudante")
public class EstudantesController {

	@GetMapping
	public ResponseEntity<?> listar() {
		return ResponseEntity.ok("Hey... Hey..... I wanna be famous......");
	}
}
