package br.com.system.view.cliente.tableModel;

import br.com.system.controller.cliente.ClienteAction;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author crhobus
 */
public class TableModelCliente extends AbstractTableModel {

    private final ClienteAction clienteAction;

    public TableModelCliente(ClienteAction clienteAction) {
        this.clienteAction = clienteAction;
        this.clienteAction.listarClientes();
    }

    @Override
    public int getRowCount() {
        return clienteAction.getQtClientesCadastrados();
    }

    @Override
    public int getColumnCount() {
        return clienteAction.getQtColunasTabela();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return clienteAction.getRegistrosTableModel(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Cliente";
            case 1:
                return "Tipo de pessoa";
            case 2:
                return "CPF";
            case 3:
                return "CNPJ";
            case 4:
                return "Telefone";
            case 5:
                return "Celular";
            case 6:
                return "Endereço";
            case 7:
                return "Bairro";
            case 8:
                return "Número";
            case 9:
                return "Complemento";
            case 10:
                return "Cep";
            case 11:
                return "Cidade";
            default:
                return "UF";
        }
    }
}
