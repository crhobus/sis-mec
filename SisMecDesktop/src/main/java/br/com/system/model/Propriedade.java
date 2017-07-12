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
@Table(name = "propriedade")
@TableGenerator(name = "propriedade_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "propriedadeseq", valueColumnName = "valor", allocationSize = 1)
public class Propriedade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "propriedade_seq", strategy = GenerationType.TABLE)
    @Column(name = "cd_propriedade")
    private long cdPropriedade;
    @Column(name = "nm_propriedade", unique = true, length = 80, nullable = false)
    private String nmPropriedade;
    @Column(name = "vl_propriedade_str", length = 255)
    private String vlPropriedadeStr;
    @Column(name = "vl_propriedade_data", length = 256)
    private byte[] vlPropriedadeData;
    @Column(name = "dt_criacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCriacao;
    @Column(name = "dt_atualizacao", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtAtualizacao;

    public long getCdPropriedade() {
        return cdPropriedade;
    }

    public void setCdPropriedade(long cdPropriedade) {
        this.cdPropriedade = cdPropriedade;
    }

    public String getNmPropriedade() {
        return nmPropriedade;
    }

    public void setNmPropriedade(String nmPropriedade) {
        this.nmPropriedade = nmPropriedade;
    }

    public String getVlPropriedadeStr() {
        return vlPropriedadeStr;
    }

    public void setVlPropriedadeStr(String vlPropriedadeStr) {
        this.vlPropriedadeStr = vlPropriedadeStr;
    }

    public byte[] getVlPropriedadeData() {
        return vlPropriedadeData;
    }

    public void setVlPropriedadeData(byte[] vlPropriedadeData) {
        this.vlPropriedadeData = vlPropriedadeData;
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
        hash = 67 * hash + (int) (this.cdPropriedade ^ (this.cdPropriedade >>> 32));
        hash = 67 * hash + Objects.hashCode(this.nmPropriedade);
        hash = 67 * hash + Objects.hashCode(this.vlPropriedadeStr);
        hash = 67 * hash + Arrays.hashCode(this.vlPropriedadeData);
        hash = 67 * hash + Objects.hashCode(this.dtCriacao);
        hash = 67 * hash + Objects.hashCode(this.dtAtualizacao);
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
        final Propriedade other = (Propriedade) obj;
        if (this.cdPropriedade != other.cdPropriedade) {
            return false;
        }
        if (!Objects.equals(this.nmPropriedade, other.nmPropriedade)) {
            return false;
        }
        if (!Objects.equals(this.vlPropriedadeStr, other.vlPropriedadeStr)) {
            return false;
        }
        if (!Arrays.equals(this.vlPropriedadeData, other.vlPropriedadeData)) {
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
