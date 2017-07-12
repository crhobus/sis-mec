package br.com.system.view.components.table;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 *
 * @author crhobus
 */
public class LabelGradient extends JLabel {

    private boolean selected;

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            super.paintComponent(g);
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        Color c1 = new Color(241, 237, 228);
        Color c2 = new Color(212, 208, 200);

        GradientPaint gp;
        if (isSelected()) {
            gp = new GradientPaint(
                    0, 0, c2,
                    0, h, c1);
        } else {
            gp = new GradientPaint(
                    0, 0, c1,
                    0, h, c2);
        }
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
