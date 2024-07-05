package br.com.ccs.exemplotransactions.repositories;

import br.com.ccs.exemplotransactions.entities.RegistroAtividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroAtividadeRepository extends JpaRepository<RegistroAtividade, Integer> {
}