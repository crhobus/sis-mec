package br.com.system.view.components.table;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author crhobus
 */
public class WJTableLineNumber extends JTable {

    private final JTable table;

    public WJTableLineNumber(JTable table) {
        super();
        this.table = table;
        activation();
    }

    private void activation() {
        setAutoCreateColumnsFromModel(false);
        setModel(table.getModel());
        setSelectionModel(table.getSelectionModel());
        setAutoscrolls(false);

        addColumn(new TableColumn());
        getColumnModel().getColumn(0).setCellRenderer(table.getTableHeader().getDefaultRenderer());
        getColumnModel().getColumn(0).setPreferredWidth(30);
        setPreferredScrollableViewportSize(getPreferredSize());

        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(new WTableCellRenderer());
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int column) {
        return row + 1;
    }

    @Override
    public int getRowHeight(int row) {
        return table.getRowHeight();
    }
}
