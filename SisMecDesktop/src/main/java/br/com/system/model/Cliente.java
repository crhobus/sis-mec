package br.com.system.model;

import java.io.Serializable;
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
@Table(name = "cliente")
@TableGenerator(name = "cliente_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "clienteseq", valueColumnName = "valor", allocationSize = 1)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "cliente_seq", strategy = GenerationType.TABLE)
    @Column(name = "cd_cliente")
    private long cdCliente;
    @Column(name = "nm_cliente", length = 255, nullable = false)
    private String nmCliente;
    @Column(name = "ie_tipo_pessoa", length = 1, nullable = false)
    private String ieTipoPessoa;
    @Column(name = "ie_sexo", length = 1)
    private String ieSexo;
    @Column(name = "dt_nascimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtNascimento;
    @Column(name = "nr_cpf", unique = true, length = 14)
    private String nrCPF;
    @Column(name = "nr_rg", unique = true, length = 11)
    private String nrRG;
    @Column(name = "nr_cnpj", unique = true, length = 18)
    private String nrCNPJ;
    @Column(name = "nr_ie", unique = true, length = 11)
    private String nrIE;
    @Column(name = "nr_telefone", length = 13)
    private String nrTelefone;
    @Column(name = "nr_celular", length = 14)
    private String nrCelular;
    @Column(name = "ds_email", length = 200)
    private String dsEmail;
    @Column(name = "ds_endereco", length = 255, nullable = false)
    private String dsEndereco;
    @Column(name = "ds_complemento", length = 25, nullable = false)
    private String dsComplemento;
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
    @Column(name = "dt_criacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;

    public long getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(long cdCliente) {
        this.cdCliente = cdCliente;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getIeTipoPessoa() {
        return ieTipoPessoa;
    }

    public void setIeTipoPessoa(String ieTipoPessoa) {
        this.ieTipoPessoa = ieTipoPessoa;
    }

    public String getIeSexo() {
        return ieSexo;
    }

    public void setIeSexo(String ieSexo) {
        this.ieSexo = ieSexo;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNrCPF() {
        return nrCPF;
    }

    public void setNrCPF(String nrCPF) {
        this.nrCPF = nrCPF;
    }

    public String getNrRG() {
        return nrRG;
    }

    public void setNrRG(String nrRG) {
        this.nrRG = nrRG;
    }

    public String getNrCNPJ() {
        return nrCNPJ;
    }

    public void setNrCNPJ(String nrCNPJ) {
        this.nrCNPJ = nrCNPJ;
    }

    public String getNrIE() {
        return nrIE;
    }

    public void setNrIE(String nrIE) {
        this.nrIE = nrIE;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
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

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getDsComplemento() {
        return dsComplemento;
    }

    public void setDsComplemento(String dsComplemento) {
        this.dsComplemento = dsComplemento;
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
        hash = 17 * hash + (int) (this.cdCliente ^ (this.cdCliente >>> 32));
        hash = 17 * hash + Objects.hashCode(this.nmCliente);
        hash = 17 * hash + Objects.hashCode(this.ieTipoPessoa);
        hash = 17 * hash + Objects.hashCode(this.ieSexo);
        hash = 17 * hash + Objects.hashCode(this.dtNascimento);
        hash = 17 * hash + Objects.hashCode(this.nrCPF);
        hash = 17 * hash + Objects.hashCode(this.nrRG);
        hash = 17 * hash + Objects.hashCode(this.nrCNPJ);
        hash = 17 * hash + Objects.hashCode(this.nrIE);
        hash = 17 * hash + Objects.hashCode(this.nrTelefone);
        hash = 17 * hash + Objects.hashCode(this.nrCelular);
        hash = 17 * hash + Objects.hashCode(this.dsEmail);
        hash = 17 * hash + Objects.hashCode(this.dsEndereco);
        hash = 17 * hash + Objects.hashCode(this.dsComplemento);
        hash = 17 * hash + Objects.hashCode(this.dsReferencia);
        hash = 17 * hash + Objects.hashCode(this.nmBairro);
        hash = 17 * hash + this.nrEndereco;
        hash = 17 * hash + Objects.hashCode(this.nrCep);
        hash = 17 * hash + Objects.hashCode(this.nmCidade);
        hash = 17 * hash + Objects.hashCode(this.dsUF);
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
        final Cliente other = (Cliente) obj;
        if (this.cdCliente != other.cdCliente) {
            return false;
        }
        if (this.nrEndereco != other.nrEndereco) {
            return false;
        }
        if (!Objects.equals(this.nmCliente, other.nmCliente)) {
            return false;
        }
        if (!Objects.equals(this.ieTipoPessoa, other.ieTipoPessoa)) {
            return false;
        }
        if (!Objects.equals(this.ieSexo, other.ieSexo)) {
            return false;
        }
        if (!Objects.equals(this.nrCPF, other.nrCPF)) {
            return false;
        }
        if (!Objects.equals(this.nrRG, other.nrRG)) {
            return false;
        }
        if (!Objects.equals(this.nrCNPJ, other.nrCNPJ)) {
            return false;
        }
        if (!Objects.equals(this.nrIE, other.nrIE)) {
            return false;
        }
        if (!Objects.equals(this.nrTelefone, other.nrTelefone)) {
            return false;
        }
        if (!Objects.equals(this.nrCelular, other.nrCelular)) {
            return false;
        }
        if (!Objects.equals(this.dsEmail, other.dsEmail)) {
            return false;
        }
        if (!Objects.equals(this.dsEndereco, other.dsEndereco)) {
            return false;
        }
        if (!Objects.equals(this.dsComplemento, other.dsComplemento)) {
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
        if (!Objects.equals(this.dtNascimento, other.dtNascimento)) {
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
