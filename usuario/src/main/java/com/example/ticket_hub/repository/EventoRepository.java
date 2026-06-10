// repository é a camada de acesso a dados, onde ficam as interfaces que estendem JpaRepository para realizar operações de CRUD no banco de dados
package com.example.ticket_hub.repository;

import com.example.ticket_hub.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT e FROM Evento e WHERE LOWER(e.titulo) LIKE LOWER(:termo) OR LOWER(e.local) LIKE LOWER(:termo)")
    List<Evento> buscar(@Param("termo") String termo);
}
