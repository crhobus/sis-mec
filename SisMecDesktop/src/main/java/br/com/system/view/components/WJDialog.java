package br.com.system.view.components;

import br.com.system.controller.action.comboBox.ComboBoxOption;
import br.com.system.view.components.comboBox.WComboBoxCellRenderer;
import br.com.system.view.components.events.WActionCancelarListener;
import br.com.system.view.components.events.WActionCarregarListener;
import br.com.system.view.components.events.WActionConsultarListener;
import br.com.system.view.components.events.WActionDesfazerListener;
import br.com.system.view.components.events.WActionExcluirListener;
import br.com.system.view.components.events.WActionFiltrarListener;
import br.com.system.view.components.events.WActionNovoListener;
import br.com.system.view.components.events.WActionOkListener;
import br.com.system.view.components.events.WActionSalvarListener;
import br.com.system.view.components.events.WChangedListener;
import br.com.system.view.components.events.WEvent;
import br.com.system.view.components.events.WHandlerListener;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author crhobus
 */
public abstract class WJDialog extends JDialog implements WChangedListener, WHandlerListener {

    private final WJComponents components;
    private final WButtonsConfiguration buttonsConfiguration;
    private final int width;
    private final int height;
    private JPanel frame;
    private final FrameType frameType;
    private boolean activeButtonUpload;
    private boolean activeButtonNew = true;
    private boolean activeButtonDelete = true;
    private boolean activeButtonConsult = true;

    public enum FrameType {
        CADASTRO, CONSULTA, GERAL;
    }

    public WJDialog(Window window, String title, int width, int height, FrameType type) {
        super(window, title);
        this.width = width;
        this.height = height;
        this.frameType = type;
        this.components = new WJComponents();
        this.buttonsConfiguration = new WButtonsConfiguration(components);
        addListeners();
        initComponents();
    }

    private void initComponents() {
        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void activation() {
        if (frameType == FrameType.CADASTRO) {
            initStateButtons();
        }
        defaultInitialize();
        setModal(true);
        setVisible(true);
    }

    public abstract void defaultInitialize();

    public abstract boolean isHasDataObject();

    private void initStateButtons() {
        getWButtonsConfiguration().getBtnSalvar().setEnabled(false);
        getWButtonsConfiguration().getBtnDesfazer().setEnabled(false);
        if (isActiveButtonNew()) {
            getWButtonsConfiguration().getBtnNovo().setEnabled(getWButtonsConfiguration().isPermissaoNovo());
        }
        if (isActiveButtonDelete()) {
            getWButtonsConfiguration().getBtnExcluir().setEnabled(getWButtonsConfiguration().isPermissaoExcluir() && isHasDataObject());
        }
        if (isActiveButtonConsult()) {
            getWButtonsConfiguration().getBtnConsultar().setEnabled(getWButtonsConfiguration().isPermissaoConsultar());
        }
        if (isActiveButtonUpload()) {
            getWButtonsConfiguration().getBtnCarregar().setEnabled(getWButtonsConfiguration().isPermissaoCarregar());
        }
    }

    private void addListeners() {
        this.getWComponents().addWChangedListener(this);
        this.getWComponents().addWHandlerListener(this);
    }

    public void setEditingOrInsertionFrameMode(boolean flag) {
        getWButtonsConfiguration().getBtnSalvar().setEnabled(flag);
        getWButtonsConfiguration().getBtnDesfazer().setEnabled(flag);
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

    public JPanel getComponentFrame() {
        return frame;
    }

    public boolean isActiveButtonUpload() {
        return activeButtonUpload;
    }

    public void setActiveButtonUpload(boolean activeButtonUpload) {
        this.activeButtonUpload = activeButtonUpload;
    }

    public boolean isActiveButtonNew() {
        return activeButtonNew;
    }

    public void setActiveButtonNew(boolean activeButtonNew) {
        this.activeButtonNew = activeButtonNew;
    }

    public boolean isActiveButtonDelete() {
        return activeButtonDelete;
    }

    public void setActiveButtonDelete(boolean activeButtonDelete) {
        this.activeButtonDelete = activeButtonDelete;
    }

    public boolean isActiveButtonConsult() {
        return activeButtonConsult;
    }

    public void setActiveButtonConsult(boolean activeButtonConsult) {
        this.activeButtonConsult = activeButtonConsult;
    }

    private void salvar() {
        Color corCampoObrigatorio = new Color(178, 223, 238);
        boolean camposObrigatoriosPreenchidos = true;
        for (Component component : frame.getComponents()) {
            if (component instanceof JComboBox) {
                JComboBox comboBox = (JComboBox) component;
                if (((WComboBoxCellRenderer) comboBox.getRenderer()).isRequired()
                        && "0".equals(((ComboBoxOption) comboBox.getSelectedItem()).getChave())) {
                    camposObrigatoriosPreenchidos = false;
                    break;
                }
            } else if (component instanceof JTextComponent) {
                JTextComponent textComponent = (JTextComponent) component;
                if (textComponent.getBackground().equals(corCampoObrigatorio)
                        && "".equals(textComponent.getText())) {
                    camposObrigatoriosPreenchidos = false;
                    break;
                }
            }
        }
        if (!camposObrigatoriosPreenchidos) {
            JOptionPane.showMessageDialog(this, "Todos os campos obrigatórios devem ser preenchidos", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        try {
            for (WActionSalvarListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionSalvarListener.class)) {
                listener.onSalvar();
            }
        } catch (AbortException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (frameType != FrameType.GERAL) {
            getWButtonsConfiguration().getBtnSalvar().setEnabled(false);
            getWButtonsConfiguration().getBtnDesfazer().setEnabled(false);
            if (isActiveButtonNew()) {
                getWButtonsConfiguration().getBtnNovo().setEnabled(getWButtonsConfiguration().isPermissaoNovo());
            }
            if (isActiveButtonDelete()) {
                getWButtonsConfiguration().getBtnExcluir().setEnabled(getWButtonsConfiguration().isPermissaoExcluir());
            }
            if (isActiveButtonConsult()) {
                getWButtonsConfiguration().getBtnConsultar().setEnabled(getWButtonsConfiguration().isPermissaoConsultar());
            }
            if (isActiveButtonUpload()) {
                getWButtonsConfiguration().getBtnCarregar().setEnabled(getWButtonsConfiguration().isPermissaoCarregar());
            }
        }
        this.validate();
        this.repaint();
    }

    private void desfazer() {
        for (WActionDesfazerListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionDesfazerListener.class)) {
            listener.onDesfazer();
        }
        getWButtonsConfiguration().getBtnSalvar().setEnabled(false);
        getWButtonsConfiguration().getBtnDesfazer().setEnabled(false);
        if (isActiveButtonNew()) {
            getWButtonsConfiguration().getBtnNovo().setEnabled(getWButtonsConfiguration().isPermissaoNovo());
        }
        if (isActiveButtonDelete()) {
            getWButtonsConfiguration().getBtnExcluir().setEnabled(getWButtonsConfiguration().isPermissaoExcluir() && isHasDataObject());
        }
        if (isActiveButtonConsult()) {
            getWButtonsConfiguration().getBtnConsultar().setEnabled(getWButtonsConfiguration().isPermissaoConsultar());
        }
        if (isActiveButtonUpload()) {
            getWButtonsConfiguration().getBtnCarregar().setEnabled(getWButtonsConfiguration().isPermissaoCarregar());
        }
        this.validate();
        this.repaint();
    }

    private void novo() {
        for (WActionNovoListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionNovoListener.class)) {
            listener.onNovo();
        }
        getWButtonsConfiguration().getBtnSalvar().setEnabled(getWButtonsConfiguration().isPermissaoSalvar());
        getWButtonsConfiguration().getBtnDesfazer().setEnabled(getWButtonsConfiguration().isPermissaoDesfazer());
        if (isActiveButtonNew()) {
            getWButtonsConfiguration().getBtnNovo().setEnabled(false);
        }
        if (isActiveButtonDelete()) {
            getWButtonsConfiguration().getBtnExcluir().setEnabled(false);
        }
        if (isActiveButtonConsult()) {
            getWButtonsConfiguration().getBtnConsultar().setEnabled(false);
        }
        if (isActiveButtonUpload()) {
            getWButtonsConfiguration().getBtnCarregar().setEnabled(getWButtonsConfiguration().isPermissaoCarregar());
        }
        this.validate();
        this.repaint();
    }

    private void excluir() {
        if (JOptionPane.showConfirmDialog(this, "Deseja realmente excluir o registro do sistema", "Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                for (WActionExcluirListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionExcluirListener.class)) {
                    listener.onExcluir();
                }
            } catch (AbortException ex) {
                if (ex.getMessage() != null
                        && !"".equals(ex.getMessage())) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                return;
            }
            getWButtonsConfiguration().getBtnSalvar().setEnabled(false);
            getWButtonsConfiguration().getBtnDesfazer().setEnabled(false);
            if (isActiveButtonNew()) {
                getWButtonsConfiguration().getBtnNovo().setEnabled(getWButtonsConfiguration().isPermissaoNovo());
            }
            if (isActiveButtonDelete()) {
                getWButtonsConfiguration().getBtnExcluir().setEnabled(false);
            }
            if (isActiveButtonConsult()) {
                getWButtonsConfiguration().getBtnConsultar().setEnabled(getWButtonsConfiguration().isPermissaoConsultar());
            }
            if (isActiveButtonUpload()) {
                getWButtonsConfiguration().getBtnCarregar().setEnabled(getWButtonsConfiguration().isPermissaoCarregar());
            }
            this.validate();
            this.repaint();
        }
    }

    private void consultar() {
        for (WActionConsultarListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionConsultarListener.class)) {
            listener.onConsultar();
        }
        initStateButtons();
        this.validate();
        this.repaint();
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

    private void filtrar() {
        for (WActionFiltrarListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionFiltrarListener.class)) {
            listener.onFiltrar();
        }
    }

    private void carregar() {
        try {
            for (WActionCarregarListener listener : getWButtonsConfiguration().getEventListenerList().getListeners(WActionCarregarListener.class)) {
                listener.onCarregar();
            }
        } catch (AbortException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void onChanged(WEvent evt) {
        if (frameType == FrameType.CADASTRO) {
            getWButtonsConfiguration().getBtnSalvar().setEnabled(getWButtonsConfiguration().isPermissaoSalvar());
            getWButtonsConfiguration().getBtnDesfazer().setEnabled(getWButtonsConfiguration().isPermissaoDesfazer());
            if (isActiveButtonNew()) {
                getWButtonsConfiguration().getBtnNovo().setEnabled(false);
            }
            if (isActiveButtonDelete()) {
                getWButtonsConfiguration().getBtnExcluir().setEnabled(false);
            }
            if (isActiveButtonConsult()) {
                getWButtonsConfiguration().getBtnConsultar().setEnabled(false);
            }
            if (isActiveButtonUpload()) {
                getWButtonsConfiguration().getBtnCarregar().setEnabled(getWButtonsConfiguration().isPermissaoCarregar());
            }
        }
    }

    @Override
    public void onHandler(WEvent evt) {
        switch (evt.getType()) {
            case SAVE:
                salvar();
                break;
            case UNDO:
                desfazer();
                break;
            case NEW:
                novo();
                break;
            case DELETE:
                excluir();
                break;
            case CONSULT:
                consultar();
                break;
            case OK:
                ok();
                break;
            case CANCEL:
                cancelar();
                break;
            case FILTER:
                filtrar();
                break;
            case UPLOAD:
                carregar();
                break;
        }
    }
}
