package br.com.system.view.components.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author crhobus
 */
public class WTableCellRenderer extends LabelGradient implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setSelected(isSelected);
        setText(value != null ? value.toString() : null);
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
        if (isSelected) {
            setForeground(Color.RED.darker());
        } else {
            setForeground(Color.BLACK.darker());
        }
        return this;
    }
}
