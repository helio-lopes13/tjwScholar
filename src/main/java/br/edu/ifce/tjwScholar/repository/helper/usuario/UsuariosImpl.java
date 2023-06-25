package br.edu.ifce.tjwScholar.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import br.edu.ifce.tjwScholar.model.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class UsuariosImpl implements UsuariosQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Optional<Usuario> porEmail(String email) {
		return manager.createQuery("from Usuario where lower(email) = lower(:email)", Usuario.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

	@Override
	public List<String> permissoes(Usuario usuario) {
		return manager.createQuery(
				"select distinct p.nome from Usuario u inner join u.permissoes p where u = :usuario", String.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}
}
