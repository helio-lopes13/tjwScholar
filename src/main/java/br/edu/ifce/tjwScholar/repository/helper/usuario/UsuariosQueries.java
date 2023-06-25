package br.edu.ifce.tjwScholar.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import br.edu.ifce.tjwScholar.model.Usuario;

public interface UsuariosQueries {

	public Optional<Usuario> porEmail(String email);
	
	public List<String> permissoes(Usuario usuario);

}
