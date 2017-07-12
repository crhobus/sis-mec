package br.com.system.controller.cliente;

import br.com.system.controller.action.comboBox.ComboBoxOption;
import br.com.system.controller.action.comboBox.ControlComboBoxOption;
import br.com.system.controller.action.radioButton.ControlRadioButtonType;
import br.com.system.controller.action.radioButton.RadioButtonType;
import br.com.system.model.Cliente;
import br.com.system.persistence.dao.cliente.ClienteDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author crhobus
 */
public class ClienteAction {

    private final ClienteDAO clienteDAO;
    private final ControlRadioButtonType controlTipoPessoa;
    private final ControlComboBoxOption controlSexo;
    private Cliente cliente;
    private List<Cliente> clientes;

    public ClienteAction() {
        this.clienteDAO = new ClienteDAO();
        this.controlTipoPessoa = new ControlRadioButtonType();
        this.controlTipoPessoa.addType(new RadioButtonType(0, "F", "Física"));
        this.controlTipoPessoa.addType(new RadioButtonType(1, "J", "Jurídica"));
        this.controlSexo = new ControlComboBoxOption();
        this.controlSexo.addOption(new ComboBoxOption("M", "Masculino"));
        this.controlSexo.addOption(new ComboBoxOption("F", "Feminino"));
        novo();
    }

    public void novo() {
        cliente = new Cliente();
    }

    public void salvar(String nome, String tipoPessoa, String cpf, String rg, String sexo,
            Date dtNascimento, String cnpj, String ie, String endereco, int numero, String complemento,
            String bairro, String referencia, String cep, String cidade, String uf, String email,
            String telefone, String celular) throws Exception {
        if (telefone == null
                && celular == null) {
            throw new Exception("O cliente deve possuir um telefone ou celular cadastrado!");
        }
        if (cliente.getCdCliente() == 0L) {
            cliente.setDtCriacao(new Date());
        }
        cliente.setNmCliente(nome);
        cliente.setIeTipoPessoa(tipoPessoa);
        cliente.setIeSexo(sexo);
        cliente.setDtNascimento(dtNascimento);
        cliente.setNrCPF(cpf);
        cliente.setNrRG(rg);
        cliente.setNrCNPJ(cnpj);
        cliente.setNrIE(ie);
        cliente.setNrTelefone(telefone);
        cliente.setNrCelular(celular);
        cliente.setDsEmail(email);
        cliente.setDsEndereco(endereco);
        cliente.setDsComplemento(complemento);
        cliente.setDsReferencia(referencia);
        cliente.setNmBairro(bairro);
        cliente.setNrEndereco(numero);
        cliente.setNrCep(cep);
        cliente.setNmCidade(cidade);
        cliente.setDsUF(uf);
        cliente.setDtAtualizacao(new Date());

        if (cliente.getCdCliente() > 0L) {
            clienteDAO.update(cliente);
        } else {
            clienteDAO.insert(cliente);
        }
    }

    public void excluir() throws Exception {
        clienteDAO.delete(cliente.getCdCliente());
        novo();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void listarClientes() {
        try {
            clientes = clienteDAO.getClientes();
        } catch (Exception ex) {
            clientes = new ArrayList<>();
        }
    }

    public void listarClientes(int indiceColuna, String filtro) {
        try {
            clientes = clienteDAO.getClientes(indiceColuna, filtro, getControlTipoPessoa());
        } catch (Exception ex) {
            clientes = new ArrayList<>();
        }
    }

    public void limparListaClientes() {
        clientes = null;
    }

    public int getQtClientesCadastrados() {
        if (clientes != null
                && !clientes.isEmpty()) {
            return clientes.size();
        }
        return 0;
    }

    public int getQtColunasTabela() {
        return 13;
    }

    public Object getRegistrosTableModel(int linha, int coluna) {
        Cliente c = clientes.get(linha);
        switch (coluna) {
            case 0:
                return c.getNmCliente();
            case 1:
                return getControlTipoPessoa().getTypeV(c.getIeTipoPessoa()).getDescricao();
            case 2:
                return c.getNrCPF();
            case 3:
                return c.getNrCNPJ();
            case 4:
                return c.getNrTelefone();
            case 5:
                return c.getNrCelular();
            case 6:
                return c.getDsEndereco();
            case 7:
                return c.getNmBairro();
            case 8:
                return c.getNrEndereco();
            case 9:
                return c.getDsComplemento();
            case 10:
                return c.getNrCep();
            case 11:
                return c.getNmCidade();
            default:
                return c.getDsUF();
        }
    }

    public void obterClienteLista(int indice) {
        cliente = clientes.get(indice);
    }

    public ControlRadioButtonType getControlTipoPessoa() {
        return controlTipoPessoa;
    }

    public ControlComboBoxOption getControlSexo() {
        return controlSexo;
    }
}
