package br.com.system.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author crhobus
 */
@Entity
@Table(name = "usuario")
@TableGenerator(name = "usuario_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "usuarioseq", valueColumnName = "valor", allocationSize = 1)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "usuario_seq", strategy = GenerationType.TABLE)
    @Column(name = "cd_usuario")
    private long cdUsuario;
    @Column(name = "nm_usuario", unique = true, length = 30, nullable = false)
    private String nmUsuario;
    @Column(name = "ds_senha", length = 256, nullable = false)
    private byte[] dsSenha;
    @Column(name = "nr_permissao", nullable = false)
    private int nrPermissao;
    @Column(name = "nm_pessoa", length = 255, nullable = false)
    private String nmPessoa;
    @Column(name = "ie_situacao", length = 1, nullable = false)
    private String ieSituacao;
    @Column(name = "nr_celular", length = 14)
    private String nrCelular;
    @Column(name = "ds_email", length = 200)
    private String dsEmail;
    @Column(name = "dt_criacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;
    @Column(name = "nr_tentativas", nullable = false)
    private int nrTentativas;

    public long getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(long cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public byte[] getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(byte[] dsSenha) {
        this.dsSenha = dsSenha;
    }

    public int getNrPermissao() {
        return nrPermissao;
    }

    public void setNrPermissao(int nrPermissao) {
        this.nrPermissao = nrPermissao;
    }

    public String getNmPessoa() {
        return nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public String getIeSituacao() {
        return ieSituacao;
    }

    public void setIeSituacao(String ieSituacao) {
        this.ieSituacao = ieSituacao;
    }

    public String getNrCelular() {
        return nrCelular;
    }

    public void setNrCelular(String nrCelular) {
        this.nrCelular = nrCelular;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public Date getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public Date getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(Date dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public int getNrTentativas() {
        return nrTentativas;
    }

    public void setNrTentativas(int nrTentativas) {
        this.nrTentativas = nrTentativas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (this.cdUsuario ^ (this.cdUsuario >>> 32));
        hash = 71 * hash + Objects.hashCode(this.nmUsuario);
        hash = 71 * hash + Arrays.hashCode(this.dsSenha);
        hash = 71 * hash + this.nrPermissao;
        hash = 71 * hash + Objects.hashCode(this.nmPessoa);
        hash = 71 * hash + Objects.hashCode(this.ieSituacao);
        hash = 71 * hash + Objects.hashCode(this.nrCelular);
        hash = 71 * hash + Objects.hashCode(this.dsEmail);
        hash = 71 * hash + Objects.hashCode(this.dtCriacao);
        hash = 71 * hash + Objects.hashCode(this.dtAtualizacao);
        hash = 71 * hash + this.nrTentativas;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.cdUsuario != other.cdUsuario) {
            return false;
        }
        if (this.nrPermissao != other.nrPermissao) {
            return false;
        }
        if (this.nrTentativas != other.nrTentativas) {
            return false;
        }
        if (!Objects.equals(this.nmUsuario, other.nmUsuario)) {
            return false;
        }
        if (!Objects.equals(this.nmPessoa, other.nmPessoa)) {
            return false;
        }
        if (!Objects.equals(this.ieSituacao, other.ieSituacao)) {
            return false;
        }
        if (!Objects.equals(this.nrCelular, other.nrCelular)) {
            return false;
        }
        if (!Objects.equals(this.dsEmail, other.dsEmail)) {
            return false;
        }
        if (!Arrays.equals(this.dsSenha, other.dsSenha)) {
            return false;
        }
        if (!Objects.equals(this.dtCriacao, other.dtCriacao)) {
            return false;
        }
        if (!Objects.equals(this.dtAtualizacao, other.dtAtualizacao)) {
            return false;
        }
        return true;
    }
}
