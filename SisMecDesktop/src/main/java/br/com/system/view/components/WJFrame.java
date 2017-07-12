package br.com.system.view.components;

import br.com.system.view.components.events.WActionCancelarListener;
import br.com.system.view.components.events.WActionOkListener;
import br.com.system.view.components.events.WEvent;
import br.com.system.view.components.events.WHandlerListener;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author crhobus
 */
public abstract class WJFrame extends JFrame implements WHandlerListener {

    private final WJComponents components;
    private final WButtonsConfiguration buttonsConfiguration;
    private final int width;
    private final int height;
    private JPanel frame;

    public WJFrame(String title, int width, int height) {
        super(title);
        this.width = width;
        this.height = height;
        this.components = new WJComponents();
        this.buttonsConfiguration = new WButtonsConfiguration(components);
        addListeners();
        initComponents();
    }

    private void initComponents() {
        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
    }

    public void activation() {
        defaultInitialize();
        setVisible(true);
    }

    public abstract void defaultInitialize();

    private void addListeners() {
        this.getWComponents().addWHandlerListener(this);
    }

    public WJComponents getWComponents() {
        return components;
    }

    public WButtonsConfiguration getWButtonsConfiguration() {
        return buttonsConfiguration;
    }

    public void createNewFrame(int x, int y, int width, int height) {
        frame = getWComponents().getJPanel(x, y, width, height);
        this.add(frame);
    }

    public void addComponentFrame(Component component) {
        frame.add(component);
    }

    private void ok() {
        for (WActionOkListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionOkListener.class)) {
            listener.onOk();
        }
    }

    private void cancelar() {
        for (WActionCancelarListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionCancelarListener.class)) {
            listener.onCancelar();
        }
    }

    @Override
    public void onHandler(WEvent evt) {
        switch (evt.getType()) {
            case OK:
                ok();
                break;
            case CANCEL:
                cancelar();
                break;
        }
    }
}
