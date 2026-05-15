package br.ufms.facom.tickethub.repository;

import br.ufms.facom.tickethub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}