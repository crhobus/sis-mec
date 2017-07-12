package br.com.system.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.plaf.UIResource;

/**
 *
 * @author crhobus
 */
public class WCheckBoxIcon implements Icon, UIResource, Serializable {

    private final boolean required;

    public WCheckBoxIcon(boolean required) {
        this.required = required;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        JCheckBox cb = (JCheckBox) c;
        ButtonModel model = cb.getModel();
        int controlSize = getControlSize();

        if (model.isEnabled()) {

            if (required) {
                g.setColor(new Color(178, 223, 238));
            } else {
                g.setColor(new Color(255, 255, 255));
            }
            g.fillRect(x, y, controlSize - 1, controlSize - 1);

            g.setColor(new Color(0, 0, 0));

        } else {
            g.setColor(new Color(190, 190, 190));
            g.drawRect(x, y, controlSize - 1, controlSize - 1);
        }

        if (model.isSelected()) {
            drawCheck(c, g, x, y);
        }

        g.setColor(Color.GRAY);
        g.drawRect(x, y, controlSize - 1, controlSize - 1);
    }

    private void drawCheck(Component c, Graphics g, int x, int y) {
        int controlSize = getControlSize();
        g.fillRect(x + 3, y + 5, 2, controlSize - 8);
        g.drawLine(x + (controlSize - 4), y + 3, x + 5, y + (controlSize - 6));
        g.drawLine(x + (controlSize - 4), y + 4, x + 5, y + (controlSize - 5));
    }

    private int getControlSize() {
        return 15;
    }

    @Override
    public int getIconWidth() {
        return getControlSize();
    }

    @Override
    public int getIconHeight() {
        return getControlSize();
    }
}
