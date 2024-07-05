package br.com.ccs.exemplotransactions.entities;

import br.com.ccs.exemplotransactions.enuns.EnumTipoRegistro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Integer id;
    @Enumerated(EnumType.STRING)
    private EnumTipoRegistro tipoRegistro;
    private String descricao;
    @Column(columnDefinition = "CLOB")
    private String trace;
}