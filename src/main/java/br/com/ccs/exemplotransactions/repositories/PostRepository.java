package br.com.ccs.exemplotransactions.repositories;

import br.com.ccs.exemplotransactions.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}