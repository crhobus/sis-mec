package br.com.system.view.components;

import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 *
 * @author crhobus
 */
public class FunctionsRadioButtonGroup {

    public static void setSelectedIndex(int index, ButtonGroup group) {
        Enumeration<AbstractButton> elements = group.getElements();
        JRadioButton radioButton;
        int i = 0;
        while (elements.hasMoreElements()) {
            radioButton = (JRadioButton) elements.nextElement();
            if (i == index) {
                group.setSelected(radioButton.getModel(), true);
                break;
            }
            i++;
        }
    }

    public static int getSelectedIndex(ButtonGroup group) {
        Enumeration<AbstractButton> elements = group.getElements();
        JRadioButton radioButton;
        int index = 0;
        while (elements.hasMoreElements()) {
            radioButton = (JRadioButton) elements.nextElement();
            if (radioButton.getModel() == group.getSelection()) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
