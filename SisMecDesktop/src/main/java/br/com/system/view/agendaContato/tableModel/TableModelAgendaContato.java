package br.com.system.view.agendaContato.tableModel;

import br.com.system.controller.agendaContato.AgendaContatoAction;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author crhobus
 */
public class TableModelAgendaContato extends AbstractTableModel {

    private final AgendaContatoAction agendaContatoAction;

    public TableModelAgendaContato(AgendaContatoAction agendaContatoAction) {
        this.agendaContatoAction = agendaContatoAction;
        this.agendaContatoAction.listarAgendaContatos();
    }

    @Override
    public int getRowCount() {
        return agendaContatoAction.getQtContatosCadastrados();
    }

    @Override
    public int getColumnCount() {
        return agendaContatoAction.getQtColunasTabela();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return agendaContatoAction.getRegistrosTableModel(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Contato";
            case 1:
                return "Telefone 1";
            case 2:
                return "Telefone 2";
            case 3:
                return "Celular 1";
            case 4:
                return "Celular 2";
            case 5:
                return "Email";
            case 6:
                return "Tipo";
            case 7:
                return "Endereço";
            case 8:
                return "Bairro";
            case 9:
                return "Número";
            case 10:
                return "Cep";
            case 11:
                return "Cidade";
            default:
                return "UF";
        }
    }
}
