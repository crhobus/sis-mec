package br.com.system.view.components.comboBox;

import br.com.system.controller.action.comboBox.ComboBoxOption;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author crhobus
 */
public class WComboBoxCellRenderer extends DefaultListCellRenderer {

    private final boolean required;

    public WComboBoxCellRenderer(boolean required) {
        this.required = required;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (required) {
            c.setBackground(new Color(178, 223, 238));
        }

        if (value instanceof ComboBoxOption) {
            ComboBoxOption option = (ComboBoxOption) value;
            setText(option.getDescricao());
        }

        return this;
    }

    public boolean isRequired() {
        return required;
    }
}
