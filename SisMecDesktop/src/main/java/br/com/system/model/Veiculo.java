package br.com.system.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author crhobus
 */
@Entity
@Table(name = "veiculo")
@TableGenerator(name = "veiculo_seq", table = "sequencias", pkColumnName = "chave", pkColumnValue = "veiculoseq", valueColumnName = "valor", allocationSize = 1)
public class Veiculo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "veiculo_seq", strategy = GenerationType.TABLE)
    @Column(name = "cd_veiculo")
    private long cdVeiculo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cd_cliente", nullable = false)
    private Cliente cliente;
    @Column(name = "ds_modelo", length = 60, nullable = false)
    private String dsModelo;
    @Column(name = "ds_marca", length = 25, nullable = false)
    private String dsMarca;
    @Column(name = "an_fabricacao", nullable = false)
    private int anoFabricacao;
}
