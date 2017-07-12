package br.com.system.view.agendaContato;

import br.com.system.controller.agendaContato.AgendaContatoAction;
import br.com.system.model.AgendaContato;
import br.com.system.view.components.AbortException;
import br.com.system.view.components.FunctionsRadioButtonGroup;
import br.com.system.view.components.WJDialog;
import br.com.system.view.components.WPanelImage;
import br.com.system.view.components.events.WActionCarregarListener;
import br.com.system.view.components.events.WActionConsultarListener;
import br.com.system.view.components.events.WActionDesfazerListener;
import br.com.system.view.components.events.WActionExcluirListener;
import br.com.system.view.components.events.WActionNovoListener;
import br.com.system.view.components.events.WActionSalvarListener;
import br.com.system.view.components.events.WClickListener;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author crhobus
 */
public class CadasAgendaContato extends WJDialog implements WActionSalvarListener, WActionDesfazerListener, WActionNovoListener, WActionExcluirListener, WActionConsultarListener, WActionCarregarListener {

    private final AgendaContatoAction agendaContatoAction;
    private JTextField tfNomeContato;
    private JTextField tfEndereco;
    private JTextField tfNumero;
    private JTextField tfBairro;
    private JTextField tfReferencia;
    private JTextField tfCidade;
    private JTextField tfUF;
    private JTextField tfEmail;
    private JFormattedTextField ftfCep;
    private JFormattedTextField ftfTelefone1;
    private JFormattedTextField ftfTelefone2;
    private JFormattedTextField ftfCelular1;
    private JFormattedTextField ftfCelular2;
    private JTextArea taAnotacoes;
    private ButtonGroup rbTipo;
    private JPanel pnImagem;
    private byte[] imagem;

    public CadasAgendaContato(Window window, AgendaContatoAction agendaContatoAction) {
        super(window, "Agenda de contato - Sistema Mecânica", 580, 700, FrameType.CADASTRO);
        this.agendaContatoAction = agendaContatoAction;
        initComponents();
    }

    private void initComponents() {
        createNewFrame(10, 10, 555, 655);

        addComponentFrame(getWComponents().getJLabel("Nome contato", 20, 20, 80));

        tfNomeContato = getWComponents().getJTextField(true, 20, 38, 230);
        addComponentFrame(tfNomeContato);

        String[] tipos = new String[agendaContatoAction.getControlTipoContato().getTypes().size()];
        for (int i = 0; i < agendaContatoAction.getControlTipoContato().getTypes().size(); i++) {
            tipos[i] = agendaContatoAction.getControlTipoContato().getTypes().get(i).getDescricao();
        }

        rbTipo = getWComponents().getJRadioButton("Tipo", tipos, 0, getComponentFrame(), 258, 20, 170, 49);

        getWButtonsConfiguration().createBtnConsultar(433, 37);
        addComponentFrame(getWButtonsConfiguration().getBtnConsultar());

        addComponentFrame(getWComponents().getJLabel("Endereço", 20, 80, 80));

        tfEndereco = getWComponents().getJTextField(true, 20, 98, 390);
        addComponentFrame(tfEndereco);

        addComponentFrame(getWComponents().getJLabel("Número", 420, 80, 80));

        tfNumero = getWComponents().getJTextFieldInputNumber(true, 420, 98, 102);
        addComponentFrame(tfNumero);

        addComponentFrame(getWComponents().getJLabel("Bairro", 20, 140, 80));

        tfBairro = getWComponents().getJTextField(true, 20, 158, 270);
        addComponentFrame(tfBairro);

        addComponentFrame(getWComponents().getJLabel("Referência", 300, 140, 80));

        tfReferencia = getWComponents().getJTextField(false, 300, 158, 222);
        addComponentFrame(tfReferencia);

        addComponentFrame(getWComponents().getJLabel("Cep", 20, 200, 80));

        ftfCep = getWComponents().getJFormattedTextField(false, "#####-###", 20, 218, 125);
        addComponentFrame(ftfCep);

        addComponentFrame(getWComponents().getJLabel("Cidade", 155, 200, 80));

        tfCidade = getWComponents().getJTextField(true, 155, 218, 240);
        addComponentFrame(tfCidade);

        addComponentFrame(getWComponents().getJLabel("UF", 405, 200, 80));

        tfUF = getWComponents().getJTextField(true, 405, 218, 117);
        addComponentFrame(tfUF);

        addComponentFrame(getWComponents().getJLabel("Email", 20, 260, 50));

        tfEmail = getWComponents().getJTextField(false, 20, 278, 230);
        addComponentFrame(tfEmail);

        addComponentFrame(getWComponents().getJLabel("Telefone 1", 260, 260, 80));

        ftfTelefone1 = getWComponents().getJFormattedTextField(false, "(##)####-####", 260, 278, 125);
        addComponentFrame(ftfTelefone1);

        addComponentFrame(getWComponents().getJLabel("Telefone 2", 395, 260, 80));

        ftfTelefone2 = getWComponents().getJFormattedTextField(false, "(##)####-####", 395, 278, 127);
        addComponentFrame(ftfTelefone2);

        addComponentFrame(getWComponents().getJLabel("Celular 1", 20, 320, 80));

        ftfCelular1 = getWComponents().getJFormattedTextField(false, "(##)#####-####", 20, 338, 125);
        addComponentFrame(ftfCelular1);

        addComponentFrame(getWComponents().getJLabel("Celular 2", 155, 320, 80));

        ftfCelular2 = getWComponents().getJFormattedTextField(false, "(##)#####-####", 155, 338, 125);
        addComponentFrame(ftfCelular2);

        addComponentFrame(getWComponents().getJLabel("Carregar Imagem", 290, 345, 100));

        setActiveButtonUpload(true);
        getWButtonsConfiguration().createBtnCarregar(380, 337);
        addComponentFrame(getWButtonsConfiguration().getBtnCarregar());

        pnImagem = getWComponents().getJPanelImage(new JPanel(), 20, 380, 180, 180);
        addComponentFrame(pnImagem);

        addComponentFrame(getWComponents().getJLabel("Anotações", 210, 380, 80));

        taAnotacoes = getWComponents().getJTextArea(false, getComponentFrame(), 210, 398, 312, 161);

        addComponentFrame(getWComponents().getJSeparator(578, 552));

        getWButtonsConfiguration().createBtnSalvar(75, 600);
        addComponentFrame(getWButtonsConfiguration().getBtnSalvar());

        getWButtonsConfiguration().createBtnDesfazer(178, 600);
        addComponentFrame(getWButtonsConfiguration().getBtnDesfazer());

        getWButtonsConfiguration().createBtnNovo(281, 600);
        addComponentFrame(getWButtonsConfiguration().getBtnNovo());

        getWButtonsConfiguration().createBtnExcluir(384, 600);
        addComponentFrame(getWButtonsConfiguration().getBtnExcluir());

        getWButtonsConfiguration().addWActionSalvarListener(this);
        getWButtonsConfiguration().addWActionDesfazerListener(this);
        getWButtonsConfiguration().addWActionNovoListener(this);
        getWButtonsConfiguration().addWActionExcluirListener(this);
        getWButtonsConfiguration().addWActionConsultarListener(this);
        getWButtonsConfiguration().addWActionCarregarListener(this);
    }

    @Override
    public void defaultInitialize() {
        setFocusDefaultField();
    }

    private void setFocusDefaultField() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                tfNomeContato.grabFocus();
            }
        });
    }

    @Override
    public boolean isHasDataObject() {
        return agendaContatoAction.getAgendaContato().getCdContato() > 0;
    }

    private void loadImagePanel(boolean load) {
        getComponentFrame().remove(pnImagem);
        pnImagem = getWComponents().getJPanelImage(load ? new WPanelImage(imagem) : new JPanel(), 20, 380, 180, 180);
        if (load) {
            pnImagem.addMouseListener(new WClickListener() {

                @Override
                public void onSingleClick(MouseEvent evt) {
                    if (imagem.length > 0) {
                        FileOutputStream outputStream = null;
                        File file = null;
                        try {
                            String tempSystemWriteImage = System.getProperty("java.io.tmpdir");
                            tempSystemWriteImage += "Image.png";
                            file = new File(tempSystemWriteImage);
                            outputStream = new FileOutputStream(file);
                            outputStream.write(imagem);
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Houve um problema ao processar a imagem.\n"
                                    + "Informações sobre o erro: " + ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
                        } finally {
                            try {
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                            } catch (IOException ex) {}
                        }
                        try {
                            if (file != null) {
                                Desktop.getDesktop().open(file);
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Houve um problema ao abrir a imagem.\n"
                                    + "Informações sobre o erro: " + ex.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                @Override
                public void onDoubleClick(MouseEvent evt) {
                    if (JOptionPane.showConfirmDialog(null, "Deseja realmente remover a imagem?", "Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        imagem = null;
                        loadImagePanel(false);
                        onChanged(null);
                    }
                }

            });
        }
        addComponentFrame(pnImagem);
        this.validate();
        this.repaint();
    }

    private void limparFrame() {
        tfNomeContato.setText("");
        FunctionsRadioButtonGroup.setSelectedIndex(0, rbTipo);
        tfEndereco.setText("");
        tfNumero.setText("");
        tfBairro.setText("");
        tfReferencia.setText("");
        ftfCep.setText("");
        tfCidade.setText("");
        tfUF.setText("");
        tfEmail.setText("");
        ftfTelefone1.setText("");
        ftfTelefone2.setText("");
        ftfCelular1.setText("");
        ftfCelular2.setText("");
        loadImagePanel(false);
        imagem = null;
        taAnotacoes.setText("");
    }

    private void setAgendaContatoFrame() {
        AgendaContato agendaContato = agendaContatoAction.getAgendaContato();
        if (agendaContato.getCdContato() > 0) {
            tfNomeContato.setText(agendaContato.getNmContato());
            FunctionsRadioButtonGroup.setSelectedIndex(agendaContatoAction.getControlTipoContato().getTypeV(agendaContato.getIeTipo()).getIndex(), rbTipo);
            tfEndereco.setText(agendaContato.getDsEndereco());
            tfNumero.setText(Integer.toString(agendaContato.getNrEndereco()));
            tfBairro.setText(agendaContato.getNmBairro());
            tfReferencia.setText(agendaContato.getDsReferencia());
            ftfCep.setText(agendaContato.getNrCep());
            tfCidade.setText(agendaContato.getNmCidade());
            tfUF.setText(agendaContato.getDsUF());
            tfEmail.setText(agendaContato.getDsEmail());
            ftfTelefone1.setText(agendaContato.getNrTelefone1());
            ftfTelefone2.setText(agendaContato.getNrTelefone2());
            ftfCelular1.setText(agendaContato.getNrCelular1());
            ftfCelular2.setText(agendaContato.getNrCelular2());
            imagem = agendaContato.getImFoto();
            if (imagem != null) {
                loadImagePanel(true);
            }
            taAnotacoes.setText(agendaContato.getDsAnotacoes());
        }
    }

    @Override
    public void onSalvar() throws AbortException {
        try {
            agendaContatoAction.salvar(tfNomeContato.getText(),
                    agendaContatoAction.getControlTipoContato().getTypeI(FunctionsRadioButtonGroup.getSelectedIndex(rbTipo)).getValor(),
                    tfEndereco.getText(),
                    Integer.parseInt(tfNumero.getText()),
                    tfBairro.getText(),
                    "".equals(tfReferencia.getText()) ? null : tfReferencia.getText(),
                    "     -   ".equals(ftfCep.getText()) ? null : ftfCep.getText(),
                    tfCidade.getText(),
                    tfUF.getText(),
                    "".equals(tfEmail.getText()) ? null : tfEmail.getText(),
                    "(  )    -    ".equals(ftfTelefone1.getText()) ? null : ftfTelefone1.getText(),
                    "(  )    -    ".equals(ftfTelefone2.getText()) ? null : ftfTelefone2.getText(),
                    "(  )     -    ".equals(ftfCelular1.getText()) ? null : ftfCelular1.getText(),
                    "(  )     -    ".equals(ftfCelular2.getText()) ? null : ftfCelular2.getText(),
                    imagem,
                    "".equals(taAnotacoes.getText()) ? null : taAnotacoes.getText());
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
    }

    @Override
    public void onDesfazer() {
        limparFrame();
        setAgendaContatoFrame();
        setFocusDefaultField();
    }

    @Override
    public void onNovo() {
        agendaContatoAction.novo();
        limparFrame();
        setFocusDefaultField();
    }

    @Override
    public void onExcluir() throws AbortException {
        try {
            agendaContatoAction.excluir();
            limparFrame();
            setFocusDefaultField();
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
    }

    @Override
    public void onConsultar() {
        ConsAgendaContato consAgendaContato = new ConsAgendaContato(this, agendaContatoAction);
        consAgendaContato.activation();
        if (agendaContatoAction.getAgendaContato() != null) {
            limparFrame();
            setAgendaContatoFrame();
            agendaContatoAction.limparListaAgendaContatos();
            setFocusDefaultField();
        }
    }

    @Override
    public void onCarregar() throws AbortException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory()
                        || file.getAbsolutePath().endsWith(".jpg")
                        || file.getAbsolutePath().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "Imagens (*.jpg;*.png)";
            }

        });
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File img = fileChooser.getSelectedFile();
            if (img != null) {
                if (img.length() > 10485760) {
                    throw new AbortException("O tamanho da imagem selecionada deverá ter no máximo 10MB!");
                }
                FileInputStream inputStream = null;
                try {
                    imagem = new byte[(int) img.length()];
                    inputStream = new FileInputStream(img);
                    inputStream.read(imagem);
                    loadImagePanel(true);
                    onChanged(null);
                } catch (IOException ex) {
                    throw new AbortException("Houve um problema ao carregar a imagem.\n"
                            + "Informações sobre o erro: " + ex.getMessage());
                } finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException ex) {}
                    }
                }
            }
        }
    }
}
