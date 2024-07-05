package br.com.ccs.exemplotransactions.repositories;

import br.com.ccs.exemplotransactions.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}