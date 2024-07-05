package br.com.ccs.exemplotransactions.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private int id;
    private String titulo;
    private String conteudo;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "post")
    private List<Comentario> comentarios;
    @CreationTimestamp
    private LocalDateTime criacao;
    @UpdateTimestamp
    private LocalDateTime atualizacao;
}
