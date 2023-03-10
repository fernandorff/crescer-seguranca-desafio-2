package br.com.cwi.crescer.desafiosec2.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCompleto;

    private String telefone;

    private String email;

    private String senha;

    private String fotoUrl;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    private Boolean ativo;

    private String codigoSenha;

    private LocalDateTime expiracaoCodigo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);
    }
}
