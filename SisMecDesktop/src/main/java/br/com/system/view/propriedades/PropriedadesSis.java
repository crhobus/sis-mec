package br.com.system.view.propriedades;

import br.com.system.controller.propriedades.PropriedadesAction;
import br.com.system.view.components.AbortException;
import br.com.system.view.components.WJDialog;
import br.com.system.view.components.events.WActionDesfazerListener;
import br.com.system.view.components.events.WActionSalvarListener;
import java.awt.EventQueue;
import java.awt.Window;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author crhobus
 */
public class PropriedadesSis extends WJDialog implements WActionSalvarListener, WActionDesfazerListener {

    private final PropriedadesAction propriedadesAction;
    private JTextField tfHostName;
    private JTextField tfSmtpPort;
    private JTextField tfUserEmail;
    private JTextField tfAccountName;
    private JPasswordField pfPasswordEmail;
    private JPasswordField pfKeySystem;
    private JCheckBox cbSSLOnConnect;

    public PropriedadesSis(Window window, PropriedadesAction propriedadesAction) {
        super(window, "Propriedades - Sistema Mecânica", 555, 390, FrameType.CADASTRO);
        this.propriedadesAction = propriedadesAction;
        initComponents();
    }

    private void initComponents() {
        createNewFrame(10, 10, 530, 345);

        addComponentFrame(getWComponents().getJLabel("Nome da conta", 20, 20, 80));

        tfAccountName = getWComponents().getJTextField(true, 20, 38, 250);
        addComponentFrame(tfAccountName);

        addComponentFrame(getWComponents().getJLabel("Email", 280, 20, 80));

        tfUserEmail = getWComponents().getJTextField(true, 280, 38, 220);
        addComponentFrame(tfUserEmail);

        addComponentFrame(getWComponents().getJLabel("Senha conta email", 20, 80, 100));

        pfPasswordEmail = getWComponents().getJPasswordField(true, 20, 98, 140);
        addComponentFrame(pfPasswordEmail);

        addComponentFrame(getWComponents().getJLabel("Servidor SMTP", 170, 80, 100));

        tfHostName = getWComponents().getJTextField(true, 170, 98, 190);
        addComponentFrame(tfHostName);

        addComponentFrame(getWComponents().getJLabel("Porta servidor SMTP", 370, 80, 100));

        tfSmtpPort = getWComponents().getJTextFieldInputNumber(true, 370, 98, 130);
        addComponentFrame(tfSmtpPort);

        cbSSLOnConnect = getWComponents().getJCheckBox("Servidor de saída SMTP requer autenticação (SSL)", true, 20, 140, 270);
        addComponentFrame(cbSSLOnConnect);

        addComponentFrame(getWComponents().getJLabel("Chave de criptografia do sistema", 20, 180, 160));

        pfKeySystem = getWComponents().getJPasswordField(true, 20, 198, 170);
        addComponentFrame(pfKeySystem);

        addComponentFrame(getWComponents().getJSeparator(250, 527));

        getWButtonsConfiguration().createBtnSalvar(168, 280);
        addComponentFrame(getWButtonsConfiguration().getBtnSalvar());

        getWButtonsConfiguration().createBtnDesfazer(273, 280);
        addComponentFrame(getWButtonsConfiguration().getBtnDesfazer());

        setActiveButtonNew(false);
        setActiveButtonDelete(false);
        setActiveButtonConsult(false);

        getWButtonsConfiguration().addWActionSalvarListener(this);
        getWButtonsConfiguration().addWActionDesfazerListener(this);
    }

    @Override
    public void defaultInitialize() {
        setPropriedadesFrame();
        setEditingOrInsertionFrameMode(false);
        setFocusDefaultField();
    }

    private void setFocusDefaultField() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                tfAccountName.grabFocus();
            }
        });
    }

    @Override
    public boolean isHasDataObject() {
        return false;
    }

    private void limparFrame() {
        tfAccountName.setText("");
        tfUserEmail.setText("");
        pfPasswordEmail.setText("");
        tfHostName.setText("");
        tfSmtpPort.setText("");
        cbSSLOnConnect.setSelected(false);
        pfKeySystem.setText("");
    }

    private void setPropriedadesFrame() {
        try {
            Map<String, Object> propriedadesMap = propriedadesAction.getPropriedades();
            if (!propriedadesMap.isEmpty()) {
                tfAccountName.setText((String) propriedadesMap.get("accountName"));
                tfUserEmail.setText((String) propriedadesMap.get("userEmail"));
                pfPasswordEmail.setText("****************");
                tfHostName.setText((String) propriedadesMap.get("hostName"));
                tfSmtpPort.setText((String) propriedadesMap.get("smtpPort"));
                cbSSLOnConnect.setSelected("S".equalsIgnoreCase((String) propriedadesMap.get("ieSSLOnConnect")));
                pfKeySystem.setText("****************");
            }
        } catch (Exception ex) {
            limparFrame();
        }
    }

    @Override
    public void onSalvar() throws AbortException {
        try {
            propriedadesAction.salvar(tfAccountName.getText(),
                    tfUserEmail.getText(),
                    new String(pfPasswordEmail.getPassword()),
                    tfHostName.getText(),
                    tfSmtpPort.getText(),
                    (cbSSLOnConnect.isSelected() ? "S" : "N"),
                    new String(pfKeySystem.getPassword()));
            pfPasswordEmail.setText("****************");
            pfKeySystem.setText("****************");
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
    }

    @Override
    public void onDesfazer() {
        limparFrame();
        setPropriedadesFrame();
        setFocusDefaultField();
    }
}
