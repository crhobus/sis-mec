package br.com.system.view.usuario;

import br.com.system.controller.usuario.UsuarioAction;
import br.com.system.view.components.WJDialog;
import br.com.system.view.components.events.WActionCancelarListener;
import br.com.system.view.components.events.WActionOkListener;
import java.awt.Window;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author crhobus
 */
public class RecuperacaoSenhaUsuario extends WJDialog implements WActionOkListener, WActionCancelarListener {

    private final UsuarioAction usuarioAction;
    private final String nmUsuario;
    private JTextField tfEmail;

    public RecuperacaoSenhaUsuario(Window window, UsuarioAction usuarioAction, String nmUsuario) {
        super(window, "Login - Sistema Mecânica", 340, 210, FrameType.GERAL);
        this.usuarioAction = usuarioAction;
        this.nmUsuario = nmUsuario;
        initComponents();
    }

    private void initComponents() {
        createNewFrame(10, 10, 315, 165);

        addComponentFrame(getWComponents().getJLabel("Email", 20, 20, 50));

        tfEmail = getWComponents().getJTextField(false, 20, 38, 265);
        addComponentFrame(tfEmail);

        addComponentFrame(getWComponents().getJSeparator(88, 312));

        getWButtonsConfiguration().createBtnOK(55, 108);
        addComponentFrame(getWButtonsConfiguration().getBtnOK());

        getWButtonsConfiguration().createBtnCancelar(165, 108);
        addComponentFrame(getWButtonsConfiguration().getBtnCancelar());

        getWButtonsConfiguration().addWActionOkListener(this);
        getWButtonsConfiguration().addWActionCancelarListener(this);
    }

    @Override
    public void defaultInitialize() {
        //
    }

    @Override
    public boolean isHasDataObject() {
        return false;
    }

    @Override
    public void onOk() {
        try {
            usuarioAction.enviarEmailRecuperacaoSenha(nmUsuario, tfEmail.getText());
            JOptionPane.showMessageDialog(this, "Email enviado com sucesso.\n"
                    + "Entre no sistema com a nova senha enviada por email", "Info", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void onCancelar() {
        this.dispose();
    }
}
