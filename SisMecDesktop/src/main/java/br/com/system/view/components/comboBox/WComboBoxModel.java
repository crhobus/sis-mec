package br.com.system.view.components.comboBox;

import br.com.system.controller.action.comboBox.ComboBoxOption;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * @author crhobus
 */
public class WComboBoxModel extends AbstractListModel<Object> implements ComboBoxModel<Object> {

    private final List<ComboBoxOption> options;
    private ComboBoxOption option;

    public WComboBoxModel(List<ComboBoxOption> options) {
        this.options = options;
        setSelectedItem(options.get(0));
    }

    @Override
    public int getSize() {
        return options.size();
    }

    @Override
    public Object getElementAt(int index) {
        return options.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        option = (ComboBoxOption) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return option;
    }
}
