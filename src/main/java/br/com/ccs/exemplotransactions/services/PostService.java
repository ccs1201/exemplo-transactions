package br.com.ccs.exemplotransactions.services;

import br.com.ccs.exemplotransactions.entities.Post;
import br.com.ccs.exemplotransactions.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ComentarioService service;
    private final RegistroAtividadeService registroAtividadeService;

    @Transactional(readOnly = true)
    public Post findById(Integer id) {
        return postRepository.findById(id).orElseThrow(() -> new CcsNotFoundException("Post Not found"));
    }

    @Transactional
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post salvarPostComComentario(Post post, boolean lancarErro) {
        Exception exception = null;
        var msg = "Post criado com sucesso...";
        try {
            post = postRepository.save(post);
            service.saveAll(post.getComentarios());
            if (lancarErro) {
                msg = "Erro ao cadastrar post";
                throw new RuntimeException("Mandou Lancar erro");
            }
        } catch (Exception ex) {
            exception = ex;
            throw ex;
        } finally {
            registroAtividadeService.registrarSucessoOuErro(post, msg, exception);
        }
        return post;
    }

    @Transactional
    public Post salvarPostComComentarioComTransacionamentoCorreto(Post post, boolean lancarErro) {
        Exception exception = null;
        var msg = "Post criado com sucesso...";
        try {
            post = postRepository.save(post);
            service.saveAll(post.getComentarios());
            if (lancarErro) {
                msg = "Erro ao cadastrar post, Mandou Lancar erro";
                throw new RuntimeException("Mandou Lancar erro");
            }
        } catch (Exception ex) {
            exception = ex;
            throw ex;
        } finally {
            registroAtividadeService.registrarSucessoOuErroComTranasionamentoCorreto(post, msg, exception);
        }
        return post;
    }
}
