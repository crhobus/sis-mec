package br.com.system.view.usuario;

import br.com.system.controller.security.SisMecSecurity;
import br.com.system.controller.usuario.UsuarioAction;
import br.com.system.model.Usuario;
import br.com.system.view.components.AbortException;
import br.com.system.view.components.WJDialog;
import br.com.system.view.components.events.WActionSalvarListener;
import java.awt.Window;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author crhobus
 */
public class AlterarSenhaUsuario extends WJDialog implements WActionSalvarListener {

    private final UsuarioAction usuarioAction;
    private JPasswordField pfSenhaAtual;
    private JPasswordField pfSenhaNova;
    private JPasswordField pfConfirmaNovaSenha;
    private final Usuario usuario;

    public AlterarSenhaUsuario(Window window, UsuarioAction usuarioAction, Usuario usuario) {
        super(window, "Login - Sistema Mecânica", 300, 350, FrameType.GERAL);
        this.usuarioAction = usuarioAction;
        this.usuario = usuario;
        initComponents();
    }

    private void initComponents() {
        createNewFrame(10, 10, 275, 305);

        addComponentFrame(getWComponents().getJLabel("Senha atual", 20, 20, 70));

        pfSenhaAtual = getWComponents().getJPasswordField(true, 20, 38, 180);
        addComponentFrame(pfSenhaAtual);

        addComponentFrame(getWComponents().getJLabel("Senha nova", 20, 80, 70));

        pfSenhaNova = getWComponents().getJPasswordField(true, 20, 98, 180);
        addComponentFrame(pfSenhaNova);

        addComponentFrame(getWComponents().getJLabel("Confirma nova senha", 20, 140, 120));

        pfConfirmaNovaSenha = getWComponents().getJPasswordField(true, 20, 158, 180);
        addComponentFrame(pfConfirmaNovaSenha);

        addComponentFrame(getWComponents().getJSeparator(208, 272));

        getWButtonsConfiguration().createBtnSalvar(90, 238);
        addComponentFrame(getWButtonsConfiguration().getBtnSalvar());

        getWButtonsConfiguration().addWActionSalvarListener(this);
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
    public void onSalvar() throws AbortException {
        try {
            if (!Arrays.equals(new SisMecSecurity().generateHash(new String(pfSenhaAtual.getPassword())), usuario.getDsSenha())) {
                throw new AbortException("Senha atual inválida!\n"
                        + "Por favor, entre com a senha atual correta.");
            }
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
        if (Arrays.equals(pfSenhaAtual.getPassword(), pfSenhaNova.getPassword())) {
            throw new AbortException("A nova senha deve ser diferente da senha atual!");
        }
        if (!Arrays.equals(pfSenhaNova.getPassword(), pfConfirmaNovaSenha.getPassword())) {
            throw new AbortException("Senha não confirmada!\n"
                    + "Por favor, confirme a nova senha.");
        }
        if (pfSenhaNova.getPassword().length < 6) {
            throw new AbortException("A nova senha deve ter no mínimo 6 caracteres!");
        }
        usuarioAction.setUsuario(usuario);
        try {
            usuarioAction.salvar(usuario.getNmPessoa(),
                    usuario.getNmUsuario(),
                    usuario.getNrPermissao(),
                    new String(pfSenhaNova.getPassword()),
                    usuario.getDsEmail(),
                    usuario.getNrCelular(),
                    usuario.getIeSituacao(),
                    true);
            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso.\n"
                    + "Você deverá logar no sistema com a nova senha!", "Info", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
    }
}
