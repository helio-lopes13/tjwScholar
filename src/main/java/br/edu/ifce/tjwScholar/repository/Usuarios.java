package br.edu.ifce.tjwScholar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifce.tjwScholar.model.Usuario;
import br.edu.ifce.tjwScholar.repository.helper.usuario.UsuariosQueries;

public interface Usuarios extends JpaRepository<Usuario, Long>, UsuariosQueries {

}
