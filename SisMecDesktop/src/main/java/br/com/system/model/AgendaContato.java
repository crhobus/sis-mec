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
@Table(name = "agenda_contato")
@TableGenerator(name = "agenda_contato_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "agendacontatoseq", valueColumnName = "valor", allocationSize = 1)
public class AgendaContato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "agenda_contato_seq", strategy = GenerationType.TABLE)
    @Column(name = "cd_contato")
    private long cdContato;
    @Column(name = "nm_contato", length = 255, nullable = false)
    private String nmContato;
    @Column(name = "nr_telefone1", length = 13)
    private String nrTelefone1;
    @Column(name = "nr_telefone2", length = 13)
    private String nrTelefone2;
    @Column(name = "nr_celular1", length = 14)
    private String nrCelular1;
    @Column(name = "nr_celular2", length = 14)
    private String nrCelular2;
    @Column(name = "ds_email", length = 200)
    private String dsEmail;
    @Column(name = "ie_tipo", length = 1, nullable = false)
    private String ieTipo;
    @Column(name = "im_foto", length = 10485760)
    private byte[] imFoto;
    @Column(name = "ds_endereco", length = 255, nullable = false)
    private String dsEndereco;
    @Column(name = "ds_referencia", length = 255)
    private String dsReferencia;
    @Column(name = "nm_bairro", length = 120, nullable = false)
    private String nmBairro;
    @Column(name = "nr_endereco", nullable = false)
    private int nrEndereco;
    @Column(name = "nr_cep", length = 9)
    private String nrCep;
    @Column(name = "nm_cidade", length = 100, nullable = false)
    private String nmCidade;
    @Column(name = "ds_uf", length = 2, nullable = false)
    private String dsUF;
    @Column(name = "ds_anotacoes", length = 4000)
    private String dsAnotacoes;
    @Column(name = "dt_criacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;

    public long getCdContato() {
        return cdContato;
    }

    public void setCdContato(long cdContato) {
        this.cdContato = cdContato;
    }

    public String getNmContato() {
        return nmContato;
    }

    public void setNmContato(String nmContato) {
        this.nmContato = nmContato;
    }

    public String getNrTelefone1() {
        return nrTelefone1;
    }

    public void setNrTelefone1(String nrTelefone1) {
        this.nrTelefone1 = nrTelefone1;
    }

    public String getNrTelefone2() {
        return nrTelefone2;
    }

    public void setNrTelefone2(String nrTelefone2) {
        this.nrTelefone2 = nrTelefone2;
    }

    public String getNrCelular1() {
        return nrCelular1;
    }

    public void setNrCelular1(String nrCelular1) {
        this.nrCelular1 = nrCelular1;
    }

    public String getNrCelular2() {
        return nrCelular2;
    }

    public void setNrCelular2(String nrCelular2) {
        this.nrCelular2 = nrCelular2;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getIeTipo() {
        return ieTipo;
    }

    public void setIeTipo(String ieTipo) {
        this.ieTipo = ieTipo;
    }

    public byte[] getImFoto() {
        return imFoto;
    }

    public void setImFoto(byte[] imFoto) {
        this.imFoto = imFoto;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getDsReferencia() {
        return dsReferencia;
    }

    public void setDsReferencia(String dsReferencia) {
        this.dsReferencia = dsReferencia;
    }

    public String getNmBairro() {
        return nmBairro;
    }

    public void setNmBairro(String nmBairro) {
        this.nmBairro = nmBairro;
    }

    public int getNrEndereco() {
        return nrEndereco;
    }

    public void setNrEndereco(int nrEndereco) {
        this.nrEndereco = nrEndereco;
    }

    public String getNrCep() {
        return nrCep;
    }

    public void setNrCep(String nrCep) {
        this.nrCep = nrCep;
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    public String getDsUF() {
        return dsUF;
    }

    public void setDsUF(String dsUF) {
        this.dsUF = dsUF;
    }

    public String getDsAnotacoes() {
        return dsAnotacoes;
    }

    public void setDsAnotacoes(String dsAnotacoes) {
        this.dsAnotacoes = dsAnotacoes;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (this.cdContato ^ (this.cdContato >>> 32));
        hash = 17 * hash + Objects.hashCode(this.nmContato);
        hash = 17 * hash + Objects.hashCode(this.nrTelefone1);
        hash = 17 * hash + Objects.hashCode(this.nrTelefone2);
        hash = 17 * hash + Objects.hashCode(this.nrCelular1);
        hash = 17 * hash + Objects.hashCode(this.nrCelular2);
        hash = 17 * hash + Objects.hashCode(this.dsEmail);
        hash = 17 * hash + Objects.hashCode(this.ieTipo);
        hash = 17 * hash + Arrays.hashCode(this.imFoto);
        hash = 17 * hash + Objects.hashCode(this.dsEndereco);
        hash = 17 * hash + Objects.hashCode(this.dsReferencia);
        hash = 17 * hash + Objects.hashCode(this.nmBairro);
        hash = 17 * hash + this.nrEndereco;
        hash = 17 * hash + Objects.hashCode(this.nrCep);
        hash = 17 * hash + Objects.hashCode(this.nmCidade);
        hash = 17 * hash + Objects.hashCode(this.dsUF);
        hash = 17 * hash + Objects.hashCode(this.dsAnotacoes);
        hash = 17 * hash + Objects.hashCode(this.dtCriacao);
        hash = 17 * hash + Objects.hashCode(this.dtAtualizacao);
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
        final AgendaContato other = (AgendaContato) obj;
        if (this.cdContato != other.cdContato) {
            return false;
        }
        if (this.nrEndereco != other.nrEndereco) {
            return false;
        }
        if (!Objects.equals(this.nmContato, other.nmContato)) {
            return false;
        }
        if (!Objects.equals(this.nrTelefone1, other.nrTelefone1)) {
            return false;
        }
        if (!Objects.equals(this.nrTelefone2, other.nrTelefone2)) {
            return false;
        }
        if (!Objects.equals(this.nrCelular1, other.nrCelular1)) {
            return false;
        }
        if (!Objects.equals(this.nrCelular2, other.nrCelular2)) {
            return false;
        }
        if (!Objects.equals(this.dsEmail, other.dsEmail)) {
            return false;
        }
        if (!Objects.equals(this.ieTipo, other.ieTipo)) {
            return false;
        }
        if (!Objects.equals(this.dsEndereco, other.dsEndereco)) {
            return false;
        }
        if (!Objects.equals(this.dsReferencia, other.dsReferencia)) {
            return false;
        }
        if (!Objects.equals(this.nmBairro, other.nmBairro)) {
            return false;
        }
        if (!Objects.equals(this.nrCep, other.nrCep)) {
            return false;
        }
        if (!Objects.equals(this.nmCidade, other.nmCidade)) {
            return false;
        }
        if (!Objects.equals(this.dsUF, other.dsUF)) {
            return false;
        }
        if (!Objects.equals(this.dsAnotacoes, other.dsAnotacoes)) {
            return false;
        }
        if (!Arrays.equals(this.imFoto, other.imFoto)) {
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
