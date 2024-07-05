package br.com.ccs.exemplotransactions;

import br.com.ccs.exemplotransactions.entities.Comentario;
import br.com.ccs.exemplotransactions.entities.Post;
import br.com.ccs.exemplotransactions.repositories.RegistroAtividadeRepository;
import br.com.ccs.exemplotransactions.services.CcsNotFoundException;
import br.com.ccs.exemplotransactions.services.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TesteTransacao {

    @Autowired
    private PostService service;
    @Autowired
    private RegistroAtividadeRepository atividadeRepository;

    private Post post;
    private Comentario comentario;

    @BeforeEach
    void setup() {
        atividadeRepository.deleteAll();
        post = new Post();
        post.setId(1);
        comentario = new Comentario();
        post.setComentarios(List.of(comentario));
    }

    @Test
    void testeTransacaoSucesso() {
        post = service.salvarPostComComentario(post, false);
        assertNotNull(post);
        assertFalse(post.getComentarios().isEmpty());

        var registros = atividadeRepository.findAll();
        assertFalse(registros.isEmpty());
        System.out.println(">>>>>>>>>>> testeTransacaoSucesso: " + registros);
    }

    @Test
    void testeTransacaoRegistrarAtivadeComTransacionamentoErrado() {
        AtomicInteger id = new AtomicInteger();
        assertThrows(RuntimeException.class, () -> {
            post = service.salvarPostComComentario(post, true);
            assertNotNull(post);
            assertFalse(post.getComentarios().isEmpty());
            id.set(post.getId());
        });

        assertThrows(CcsNotFoundException.class, () -> service.findById(id.get()));

        var registros = atividadeRepository.findAll();
        assertFalse(registros.isEmpty());
        System.out.println(">>>>>>>>>>> testeTransacaoRegistrarAtivadeComTransacionamentoErrado: " + registros);
    }

    @Test
    void testeTransacaoRegistrarAtivadeComTransacionamentoCorreto() {
        AtomicInteger id = new AtomicInteger();
        assertThrows(RuntimeException.class, () -> {
            post = service.salvarPostComComentarioComTransacionamentoCorreto(post, true);
            assertNotNull(post);
            assertFalse(post.getComentarios().isEmpty());
            id.set(post.getId());
        });

        assertThrows(CcsNotFoundException.class, () -> service.findById(id.get()));

        var registros = atividadeRepository.findAll();
        assertFalse(registros.isEmpty());
        System.out.println(">>>>>>>>>>> testeTransacaoRegistrarAtivadeComTransacionamentoCorreto: " + registros);
    }
}
