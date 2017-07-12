package br.com.system.view.components.events;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

/**
 *
 * @author crhobus
 */
public class WClickListener extends MouseAdapter implements ActionListener {

    private final static int clickInterval = (Integer) Toolkit.getDefaultToolkit().getDesktopProperty("awt.multiClickInterval");
    private MouseEvent lastEvent;
    private Timer timer;

    public WClickListener() {
        this(clickInterval);
    }

    public WClickListener(int delay) {
        timer = new Timer(delay, this);
    }

    public void onSingleClick(MouseEvent evt) {}

    public void onDoubleClick(MouseEvent evt) {}

    @Override
    public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() > 2) {
            return;
        }

        lastEvent = evt;

        if (timer.isRunning()) {
            timer.stop();
            onDoubleClick(lastEvent);
        } else {
            timer.restart();
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        timer.stop();
        onSingleClick(lastEvent);
    }
}
