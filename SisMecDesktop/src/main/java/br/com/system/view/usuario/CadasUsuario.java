package br.com.system.view.usuario;

import br.com.system.controller.action.comboBox.ComboBoxOption;
import br.com.system.controller.usuario.UsuarioAction;
import br.com.system.model.Usuario;
import br.com.system.view.components.AbortException;
import br.com.system.view.components.WJDialog;
import br.com.system.view.components.events.WActionConsultarListener;
import br.com.system.view.components.events.WActionDesfazerListener;
import br.com.system.view.components.events.WActionExcluirListener;
import br.com.system.view.components.events.WActionNovoListener;
import br.com.system.view.components.events.WActionSalvarListener;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author crhobus
 */
public class CadasUsuario extends WJDialog implements WActionSalvarListener, WActionDesfazerListener, WActionNovoListener, WActionExcluirListener, WActionConsultarListener {

    private final UsuarioAction usuarioAction;
    private boolean rebootSystem;
    private JTextField tfNome;
    private JTextField tfUsuario;
    private JTextField tfEmail;
    private JComboBox cbPermissao;
    private JPasswordField pfSenha;
    private JPasswordField pfConfirmaSenha;
    private JFormattedTextField ftfCelular;
    private JCheckBox cbSituacao;

    public CadasUsuario(Window window, UsuarioAction usuarioAction) {
        super(window, "Usuário - Sistema Mecânica", 450, 450, FrameType.CADASTRO);
        this.usuarioAction = usuarioAction;
        this.usuarioAction.novo();
        initComponents();
    }

    private void initComponents() {
        createNewFrame(10, 10, 425, 405);

        addComponentFrame(getWComponents().getJLabel("Nome completo", 20, 20, 80));

        tfNome = getWComponents().getJTextField(true, 20, 38, 280);
        addComponentFrame(tfNome);
        tfNome.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent evt) {
                if (usuarioAction.getUsuario().getCdUsuario() == 0l) {
                    criarNmUsuario();
                }
            }
        });

        getWButtonsConfiguration().createBtnConsultar(306, 37);
        addComponentFrame(getWButtonsConfiguration().getBtnConsultar());

        addComponentFrame(getWComponents().getJLabel("Usuário", 20, 80, 50));

        tfUsuario = getWComponents().getJTextField(true, 20, 98, 180);
        addComponentFrame(tfUsuario);

        addComponentFrame(getWComponents().getJLabel("Permissão", 210, 80, 50));

        cbPermissao = getWComponents().getJComboBox(true, usuarioAction.getControlPermissoes().getOptions(), 210, 98, 185);
        addComponentFrame(cbPermissao);

        addComponentFrame(getWComponents().getJLabel("Senha", 20, 140, 50));

        pfSenha = getWComponents().getJPasswordField(true, 20, 158, 180);
        addComponentFrame(pfSenha);

        addComponentFrame(getWComponents().getJLabel("Confirma senha", 210, 140, 80));

        pfConfirmaSenha = getWComponents().getJPasswordField(true, 210, 158, 185);
        addComponentFrame(pfConfirmaSenha);

        addComponentFrame(getWComponents().getJLabel("Email", 20, 200, 50));

        tfEmail = getWComponents().getJTextField(false, 20, 218, 250);
        addComponentFrame(tfEmail);

        addComponentFrame(getWComponents().getJLabel("Celular", 280, 200, 50));

        ftfCelular = getWComponents().getJFormattedTextField(false, "(##)#####-####", 280, 218, 115);
        addComponentFrame(ftfCelular);

        cbSituacao = getWComponents().getJCheckBox("Situação", true, 20, 260, 100);
        addComponentFrame(cbSituacao);

        addComponentFrame(getWComponents().getJSeparator(310, 422));

        getWButtonsConfiguration().createBtnSalvar(10, 340);
        addComponentFrame(getWButtonsConfiguration().getBtnSalvar());

        getWButtonsConfiguration().createBtnDesfazer(113, 340);
        addComponentFrame(getWButtonsConfiguration().getBtnDesfazer());

        getWButtonsConfiguration().createBtnNovo(218, 340);
        addComponentFrame(getWButtonsConfiguration().getBtnNovo());

        getWButtonsConfiguration().createBtnExcluir(323, 340);
        addComponentFrame(getWButtonsConfiguration().getBtnExcluir());

        getWButtonsConfiguration().addWActionSalvarListener(this);
        getWButtonsConfiguration().addWActionDesfazerListener(this);
        getWButtonsConfiguration().addWActionNovoListener(this);
        getWButtonsConfiguration().addWActionExcluirListener(this);
        getWButtonsConfiguration().addWActionConsultarListener(this);
    }

    @Override
    public void defaultInitialize() {
        getWButtonsConfiguration().setPermissaoExcluir(false);
        if (isRebootSystem()) {
            getWButtonsConfiguration().getBtnSalvar().setEnabled(true);
        }
        setFocusDefaultField();
    }

    private void setFocusDefaultField() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                tfNome.grabFocus();
            }
        });
    }

    @Override
    public boolean isHasDataObject() {
        return usuarioAction.getUsuario().getCdUsuario() > 0;
    }

    public boolean isRebootSystem() {
        return rebootSystem;
    }

    public void setRebootSystem(boolean rebootSystem) {
        this.rebootSystem = rebootSystem;
        rebootSystem();
    }

    private void criarNmUsuario() {
        String nome = tfNome.getText();
        nome = nome.toLowerCase();
        String usuario = "";
        String[] nomeSeparado = nome.split(" ");
        for (int i = 0; i < nomeSeparado.length; i++) {
            if (i == nomeSeparado.length - 1) {
                usuario += nomeSeparado[i];
            } else {
                usuario += nomeSeparado[i].substring(0, 1);
            }
        }
        if ("".equals(usuario)) {
            usuario = nome;
        }
        if (!usuario.equalsIgnoreCase(tfUsuario.getText())) {
            tfUsuario.setText(usuario);
        }
    }

    private void rebootSystem() {
        getWButtonsConfiguration().setPermissaoConsultar(!isRebootSystem());
        getWButtonsConfiguration().setPermissaoDesfazer(!isRebootSystem());
        getWButtonsConfiguration().setPermissaoNovo(!isRebootSystem());

        cbPermissao.setEnabled(!isRebootSystem());
        cbSituacao.setEnabled(!isRebootSystem());
        cbSituacao.setSelected(isRebootSystem());

        if (isRebootSystem()) {
            cbPermissao.setSelectedIndex(1);
        }
    }

    private void limparFrame() {
        tfNome.setText("");
        tfUsuario.setText("");
        cbPermissao.setSelectedIndex(0);
        pfSenha.setText("");
        pfConfirmaSenha.setText("");
        tfEmail.setText("");
        ftfCelular.setText("");
        cbSituacao.setSelected(false);
    }

    private void setUsuarioFrame() {
        Usuario usuario = usuarioAction.getUsuario();
        if (usuario.getCdUsuario() > 0) {
            tfNome.setText(usuario.getNmPessoa());
            tfUsuario.setText(usuario.getNmUsuario());
            cbPermissao.setSelectedItem(usuarioAction.getControlPermissoes().getOptionC(Integer.toString(usuario.getNrPermissao())));
            tfEmail.setText(usuario.getDsEmail());
            ftfCelular.setText(usuario.getNrCelular());
            cbSituacao.setSelected("A".equalsIgnoreCase(usuario.getIeSituacao()));
        }
    }

    @Override
    public void onSalvar() throws AbortException {
        if (!Arrays.equals(pfSenha.getPassword(), pfConfirmaSenha.getPassword())) {
            throw new AbortException("Senha não confirmada!\n"
                    + "Por favor, confirme a senha.");
        }
        if (pfSenha.getPassword().length < 6) {
            throw new AbortException("A senha deve ter no mínimo 6 caracteres!");
        }
        try {
            usuarioAction.salvar(tfNome.getText(),
                    tfUsuario.getText(),
                    Integer.parseInt(((ComboBoxOption) cbPermissao.getSelectedItem()).getChave()),
                    new String(pfSenha.getPassword()),
                    "".equals(tfEmail.getText()) ? null : tfEmail.getText(),
                    "(  )     -    ".equals(ftfCelular.getText()) ? null : ftfCelular.getText(),
                    (cbSituacao.isSelected() ? "A" : "I"),
                    false);
            if (isRebootSystem()) {
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso.\n"
                        + "Você deverá logar novamente no sistema!\n"
                        + "O sistema será encerrado.", "Info", JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
    }

    @Override
    public void onDesfazer() {
        limparFrame();
        setUsuarioFrame();
        setFocusDefaultField();
    }

    @Override
    public void onNovo() {
        usuarioAction.novo();
        limparFrame();
        cbSituacao.setSelected(true);
        setFocusDefaultField();
    }

    @Override
    public void onExcluir() throws AbortException {
        try {
            usuarioAction.excluir();
            limparFrame();
            setFocusDefaultField();
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
    }

    @Override
    public void onConsultar() {
        ConsUsuario consUsuario = new ConsUsuario(this, usuarioAction);
        consUsuario.activation();
        if (usuarioAction.getUsuario() != null) {
            limparFrame();
            setUsuarioFrame();
            usuarioAction.limparListaUsuarios();
            setFocusDefaultField();
        }
    }
}
