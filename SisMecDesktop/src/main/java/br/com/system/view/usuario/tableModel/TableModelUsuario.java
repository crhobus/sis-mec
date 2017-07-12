package br.com.system.view.usuario.tableModel;

import br.com.system.controller.usuario.UsuarioAction;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author crhobus
 */
public class TableModelUsuario extends AbstractTableModel {

    private final UsuarioAction usuarioAction;

    public TableModelUsuario(UsuarioAction usuarioAction) {
        this.usuarioAction = usuarioAction;
        this.usuarioAction.listarUsuarios();
    }

    @Override
    public int getRowCount() {
        return usuarioAction.getQtUsuariosCadastrados();
    }

    @Override
    public int getColumnCount() {
        return usuarioAction.getQtColunasTabela();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return usuarioAction.getRegistrosTableModel(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nome";
            case 1:
                return "Usuário";
            case 2:
                return "Situação";
            case 3:
                return "Permissão";
            case 4:
                return "Celular";
            default:
                return "Email";
        }
    }
}
