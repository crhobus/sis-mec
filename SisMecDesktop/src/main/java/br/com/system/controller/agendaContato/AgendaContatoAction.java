package br.com.system.controller.agendaContato;

import br.com.system.controller.action.radioButton.ControlRadioButtonType;
import br.com.system.controller.action.radioButton.RadioButtonType;
import br.com.system.model.AgendaContato;
import br.com.system.persistence.dao.agendaContato.AgendaContatoDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author crhobus
 */
public class AgendaContatoAction {

    private final AgendaContatoDAO agendaContatoDAO;
    private final ControlRadioButtonType controlTipoContato;
    private AgendaContato agendaContato;
    private List<AgendaContato> agendaContatos;

    public AgendaContatoAction() {
        this.agendaContatoDAO = new AgendaContatoDAO();
        this.controlTipoContato = new ControlRadioButtonType();
        this.controlTipoContato.addType(new RadioButtonType(0, "C", "Comercial"));
        this.controlTipoContato.addType(new RadioButtonType(1, "P", "Particular"));
        novo();
    }

    public void novo() {
        agendaContato = new AgendaContato();
    }

    public void salvar(String contato, String tipo, String endereco, int numero, String bairro,
            String referencia, String cep, String cidade, String uf, String email, String telefone1,
            String telefone2, String celular1, String celular2, byte[] imagem, String anotacoes) throws Exception {
        if (agendaContato.getCdContato() == 0L) {
            agendaContato.setDtCriacao(new Date());
        }
        agendaContato.setNmContato(contato);
        agendaContato.setIeTipo(tipo);
        agendaContato.setDsEndereco(endereco);
        agendaContato.setNrEndereco(numero);
        agendaContato.setNmBairro(bairro);
        agendaContato.setDsReferencia(referencia);
        agendaContato.setNrCep(cep);
        agendaContato.setNmCidade(cidade);
        agendaContato.setDsUF(uf);
        agendaContato.setDsEmail(email);
        agendaContato.setNrTelefone1(telefone1);
        agendaContato.setNrTelefone2(telefone2);
        agendaContato.setNrCelular1(celular1);
        agendaContato.setNrCelular2(celular2);
        agendaContato.setImFoto(imagem);
        agendaContato.setDsAnotacoes(anotacoes);
        agendaContato.setDtAtualizacao(new Date());

        if (agendaContato.getCdContato() > 0L) {
            agendaContatoDAO.update(agendaContato);
        } else {
            agendaContatoDAO.insert(agendaContato);
        }
    }

    public void excluir() throws Exception {
        agendaContatoDAO.delete(agendaContato.getCdContato());
        novo();
    }

    public AgendaContato getAgendaContato() {
        return agendaContato;
    }

    public void listarAgendaContatos() {
        try {
            agendaContatos = agendaContatoDAO.getAgendaContatos();
        } catch (Exception ex) {
            agendaContatos = new ArrayList<>();
        }
    }

    public void listarAgendaContatos(int indiceColuna, String filtro) {
        try {
            agendaContatos = agendaContatoDAO.getAgendaContatos(indiceColuna, filtro, getControlTipoContato());
        } catch (Exception ex) {
            agendaContatos = new ArrayList<>();
        }
    }

    public void limparListaAgendaContatos() {
        agendaContatos = null;
    }

    public int getQtContatosCadastrados() {
        if (agendaContatos != null
                && !agendaContatos.isEmpty()) {
            return agendaContatos.size();
        }
        return 0;
    }

    public int getQtColunasTabela() {
        return 13;
    }

    public Object getRegistrosTableModel(int linha, int coluna) {
        AgendaContato c = agendaContatos.get(linha);
        switch (coluna) {
            case 0:
                return c.getNmContato();
            case 1:
                return c.getNrTelefone1();
            case 2:
                return c.getNrTelefone2();
            case 3:
                return c.getNrCelular1();
            case 4:
                return c.getNrCelular2();
            case 5:
                return c.getDsEmail();
            case 6:
                return getControlTipoContato().getTypeV(c.getIeTipo()).getDescricao();
            case 7:
                return c.getDsEndereco();
            case 8:
                return c.getNmBairro();
            case 9:
                return c.getNrEndereco();
            case 10:
                return c.getNrCep();
            case 11:
                return c.getNmCidade();
            default:
                return c.getDsUF();
        }
    }

    public void obterAgendaContatoLista(int indice) {
        agendaContato = agendaContatos.get(indice);
    }

    public ControlRadioButtonType getControlTipoContato() {
        return controlTipoContato;
    }
}
