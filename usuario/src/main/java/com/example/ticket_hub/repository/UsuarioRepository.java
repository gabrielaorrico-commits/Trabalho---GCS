package com.example.ticket_hub.repository;

import com.example.ticket_hub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca um usuário pelo nome de login
    // Optional porque o usuário pode não existir (login inválido)
    Optional<Usuario> findByUsuario(String usuario);
}
