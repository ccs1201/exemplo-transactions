package br.com.ccs.exemplotransactions.services;

import br.com.ccs.exemplotransactions.entities.Comentario;
import br.com.ccs.exemplotransactions.repositories.ComentarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository repository;

    @Transactional
    public Comentario save(Comentario comentario) {
        return repository.save(comentario);
    }

    @Transactional(readOnly = true)
    public Comentario findbyId(int id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Comentário not found"));
    }

    @Transactional
    public List<Comentario> saveAll(List<Comentario> comentarios) {
        return repository.saveAll(comentarios);
    }
}
