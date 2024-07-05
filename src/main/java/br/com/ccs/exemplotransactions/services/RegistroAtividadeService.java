package br.com.ccs.exemplotransactions.services;

import br.com.ccs.exemplotransactions.entities.Post;
import br.com.ccs.exemplotransactions.entities.RegistroAtividade;
import br.com.ccs.exemplotransactions.enuns.EnumTipoRegistro;
import br.com.ccs.exemplotransactions.repositories.RegistroAtividadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class RegistroAtividadeService {

    private final RegistroAtividadeRepository repository;

    @Transactional
    public RegistroAtividade save(RegistroAtividade registroAtividade) {
        return repository.save(registroAtividade);
    }

    @Transactional(readOnly = true)
    public RegistroAtividade find(Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Registro Atividade not found"));
    }

    @Transactional
    public void registrarSucessoOuErro(Post post, String msg, Exception e) {
        RegistroAtividade registroAtividade = new RegistroAtividade();
        if (e == null) {
            registroAtividade.setTipoRegistro(EnumTipoRegistro.SUCESSO);
        } else {
            registroAtividade.setTipoRegistro(EnumTipoRegistro.ERRO);
            registroAtividade.setTrace(Arrays.toString(e.getStackTrace()));
        }
        registroAtividade.setDescricao(msg);
        save(registroAtividade);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarSucessoOuErroComTranasionamentoCorreto(Post post, String msg, Exception e) {
        RegistroAtividade registroAtividade = new RegistroAtividade();
        if (e == null) {
            registroAtividade.setTipoRegistro(EnumTipoRegistro.SUCESSO);
        } else {
            registroAtividade.setTipoRegistro(EnumTipoRegistro.ERRO);
            registroAtividade.setTrace(Arrays.toString(e.getStackTrace()));
        }
        registroAtividade.setDescricao(msg);
        save(registroAtividade);
    }
}
