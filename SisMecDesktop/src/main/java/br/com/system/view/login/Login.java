package br.com.system.view.login;

import br.com.system.controller.action.Dispatcher;
import br.com.system.controller.usuario.ChangePasswordException;
import br.com.system.controller.usuario.UsuarioAction;
import br.com.system.view.components.WJFrame;
import br.com.system.view.components.events.WActionCancelarListener;
import br.com.system.view.components.events.WActionOkListener;
import br.com.system.view.principal.Principal;
import br.com.system.view.usuario.AlterarSenhaUsuario;
import br.com.system.view.usuario.CadasUsuario;
import br.com.system.view.usuario.RecuperacaoSenhaUsuario;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author crhobus
 */
public class Login extends WJFrame implements WActionOkListener, WActionCancelarListener {

    private final UsuarioAction usuarioAction;
    private JTextField tfUsuario;
    private JPasswordField pfSenha;

    public Login() {
        super("Login - Sistema Mecânica", 400, 300);
        usuarioAction = new UsuarioAction();
        initComponents();
    }

    private void initComponents() {
        createNewFrame(10, 10, 375, 255);

        addComponentFrame(getWComponents().getJLabel("Usuário", 40, 55, 50));

        tfUsuario = getWComponents().getJTextField(false, 85, 53, 200);
        addComponentFrame(tfUsuario);

        addComponentFrame(getWComponents().getJLabel("Senha", 40, 95, 50));

        pfSenha = getWComponents().getJPasswordField(false, 85, 93, 200);
        addComponentFrame(pfSenha);

        getWButtonsConfiguration().createBtnOK(95, 145);
        addComponentFrame(getWButtonsConfiguration().getBtnOK());

        getWButtonsConfiguration().createBtnCancelar(196, 145);
        addComponentFrame(getWButtonsConfiguration().getBtnCancelar());

        try {
            if (!usuarioAction.possuiUsuarioCadstrado()) {
                JLabel lbPrimeiroAcesso = new JLabel();
                lbPrimeiroAcesso.setText("<html><u>Primeiro acesso</u></html>");
                lbPrimeiroAcesso.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        openCadasUsuario();
                    }
                });
                lbPrimeiroAcesso.setBounds(40, 195, 120, 25);
                lbPrimeiroAcesso.setFont(new Font("Arial", Font.BOLD, 14));
                lbPrimeiroAcesso.setForeground(new Color(0, 0, 255));
                lbPrimeiroAcesso.setCursor(new Cursor(Cursor.HAND_CURSOR));
                addComponentFrame(lbPrimeiroAcesso);
            } else {
                JLabel lbEsqueceuSenha = new JLabel();
                lbEsqueceuSenha.setText("<html><u>Esqueceu sua senha?</u></html>");
                lbEsqueceuSenha.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        openRecuperacaoSenhaUsuario();
                    }
                });
                lbEsqueceuSenha.setBounds(40, 195, 140, 25);
                lbEsqueceuSenha.setFont(new Font("Arial", Font.BOLD, 12));
                lbEsqueceuSenha.setForeground(new Color(0, 0, 255));
                lbEsqueceuSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
                addComponentFrame(lbEsqueceuSenha);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                Dispatcher.getInstance().dispatchEventClose();
            }
        });

        getWButtonsConfiguration().addWActionOkListener(this);
        getWButtonsConfiguration().addWActionCancelarListener(this);
    }

    @Override
    public void defaultInitialize() {
        tfUsuario.setText("crhobus");
        pfSenha.setText("123456");
    }

    private void openCadasUsuario() {
        CadasUsuario cadasUsuario = new CadasUsuario(this, usuarioAction);
        cadasUsuario.setRebootSystem(true);
        cadasUsuario.activation();
    }

    private void openRecuperacaoSenhaUsuario() {
        if ("".equals(tfUsuario.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Informe o usuário!", "Info", JOptionPane.INFORMATION_MESSAGE);
            tfUsuario.grabFocus();
        } else {
            RecuperacaoSenhaUsuario recuperacaoSenhaUsuario = new RecuperacaoSenhaUsuario(this, usuarioAction, tfUsuario.getText().trim());
            recuperacaoSenhaUsuario.activation();
            pfSenha.setText("");
            pfSenha.grabFocus();
        }
    }

    @Override
    public void onOk() {
        try {
            usuarioAction.validaUsuario(tfUsuario.getText(), new String(pfSenha.getPassword()));
            this.dispose();
            Principal p = new Principal(usuarioAction);
            p.activation();
        } catch (ChangePasswordException ex) {
            JOptionPane.showMessageDialog(this, "A sua senha deve ser alterada!", "Info", JOptionPane.INFORMATION_MESSAGE);
            AlterarSenhaUsuario alterarSenhaUsuario = new AlterarSenhaUsuario(this, usuarioAction, ex.getUsuario());
            alterarSenhaUsuario.activation();
            pfSenha.setText("");
            pfSenha.grabFocus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void onCancelar() {
        Dispatcher.getInstance().dispatchEventClose();
        System.exit(0);
    }
}
