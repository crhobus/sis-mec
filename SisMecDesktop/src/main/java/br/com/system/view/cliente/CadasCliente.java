package br.com.system.view.cliente;

import br.com.system.controller.action.comboBox.ComboBoxOption;
import br.com.system.controller.cliente.ClienteAction;
import br.com.system.model.Cliente;
import br.com.system.view.components.AbortException;
import br.com.system.view.components.FunctionsRadioButtonGroup;
import br.com.system.view.components.WJDialog;
import br.com.system.view.components.events.WActionConsultarListener;
import br.com.system.view.components.events.WActionDesfazerListener;
import br.com.system.view.components.events.WActionExcluirListener;
import br.com.system.view.components.events.WActionNovoListener;
import br.com.system.view.components.events.WActionSalvarListener;
import br.com.system.view.components.events.WComponentType;
import br.com.system.view.components.events.WEvent;
import java.awt.EventQueue;
import java.awt.Window;
import java.text.SimpleDateFormat;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

/**
 *
 * @author crhobus
 */
public class CadasCliente extends WJDialog implements WActionSalvarListener, WActionDesfazerListener, WActionNovoListener, WActionExcluirListener, WActionConsultarListener {

    private final ClienteAction clienteAction;
    private JTextField tfNomeCliente;
    private JTextField tfEndereco;
    private JTextField tfNumero;
    private JTextField tfComplemento;
    private JTextField tfBairro;
    private JTextField tfReferencia;
    private JTextField tfCidade;
    private JTextField tfUF;
    private JTextField tfEmail;
    private JFormattedTextField ftfCpf;
    private JFormattedTextField ftfRg;
    private JFormattedTextField ftfDtNascimento;
    private JFormattedTextField ftfCnpj;
    private JFormattedTextField ftfIe;
    private JFormattedTextField ftfCep;
    private JFormattedTextField ftfTelefone;
    private JFormattedTextField ftfCelular;
    private JComboBox cbSexo;
    private ButtonGroup rbTipoPessoa;

    public CadasCliente(Window window, ClienteAction clienteAction) {
        super(window, "Cliente - Sistema Mecânica", 580, 590, FrameType.CADASTRO);
        this.clienteAction = clienteAction;
        initComponents();
    }

    private void initComponents() {
        createNewFrame(10, 10, 555, 545);

        addComponentFrame(getWComponents().getJLabel("Nome", 20, 20, 80));

        tfNomeCliente = getWComponents().getJTextField(true, 20, 38, 230);
        addComponentFrame(tfNomeCliente);

        String[] tipoPessoa = new String[clienteAction.getControlTipoPessoa().getTypes().size()];
        for (int i = 0; i < clienteAction.getControlTipoPessoa().getTypes().size(); i++) {
            tipoPessoa[i] = clienteAction.getControlTipoPessoa().getTypes().get(i).getDescricao();
        }

        rbTipoPessoa = getWComponents().getJRadioButton("Tipo de pessoa", tipoPessoa, 0, getComponentFrame(), 258, 20, 170, 49);

        getWButtonsConfiguration().createBtnConsultar(433, 37);
        addComponentFrame(getWButtonsConfiguration().getBtnConsultar());

        addComponentFrame(getWComponents().getJLabel("CPF", 20, 80, 80));

        ftfCpf = getWComponents().getJFormattedTextField(false, "###.###.###-##", 20, 98, 160);
        addComponentFrame(ftfCpf);

        addComponentFrame(getWComponents().getJLabel("RG", 190, 80, 80));

        ftfRg = getWComponents().getJFormattedTextField(false, "###.###.###", 190, 98, 160);
        addComponentFrame(ftfRg);

        addComponentFrame(getWComponents().getJLabel("Sexo", 360, 80, 80));

        cbSexo = getWComponents().getJComboBox(false, clienteAction.getControlSexo().getOptions(), 360, 98, 162);
        addComponentFrame(cbSexo);

        addComponentFrame(getWComponents().getJLabel("Data Nascimento", 20, 140, 100));

        ftfDtNascimento = getWComponents().getJFormattedTextField(false, "##/##/####", 20, 158, 160);
        addComponentFrame(ftfDtNascimento);

        addComponentFrame(getWComponents().getJLabel("CNPJ", 190, 140, 100));

        ftfCnpj = getWComponents().getJFormattedTextField(false, "##.###.###/####-##", 190, 158, 160);
        addComponentFrame(ftfCnpj);

        addComponentFrame(getWComponents().getJLabel("IE", 360, 140, 100));

        ftfIe = getWComponents().getJFormattedTextField(false, "###.###.###", 360, 158, 160);
        addComponentFrame(ftfIe);

        addComponentFrame(getWComponents().getJLabel("Endereço", 20, 200, 80));

        tfEndereco = getWComponents().getJTextField(true, 20, 218, 390);
        addComponentFrame(tfEndereco);

        addComponentFrame(getWComponents().getJLabel("Número", 420, 200, 80));

        tfNumero = getWComponents().getJTextFieldInputNumber(true, 420, 218, 102);
        addComponentFrame(tfNumero);

        addComponentFrame(getWComponents().getJLabel("Complemento", 20, 260, 80));

        tfComplemento = getWComponents().getJTextField(true, 20, 278, 100);
        addComponentFrame(tfComplemento);

        addComponentFrame(getWComponents().getJLabel("Bairro", 130, 260, 80));

        tfBairro = getWComponents().getJTextField(true, 130, 278, 190);
        addComponentFrame(tfBairro);

        addComponentFrame(getWComponents().getJLabel("Referência", 330, 260, 80));

        tfReferencia = getWComponents().getJTextField(false, 330, 278, 192);
        addComponentFrame(tfReferencia);

        addComponentFrame(getWComponents().getJLabel("Cep", 20, 320, 80));

        ftfCep = getWComponents().getJFormattedTextField(false, "#####-###", 20, 338, 125);
        addComponentFrame(ftfCep);

        addComponentFrame(getWComponents().getJLabel("Cidade", 155, 320, 80));

        tfCidade = getWComponents().getJTextField(true, 155, 338, 240);
        addComponentFrame(tfCidade);

        addComponentFrame(getWComponents().getJLabel("UF", 405, 320, 80));

        tfUF = getWComponents().getJTextField(true, 405, 338, 117);
        addComponentFrame(tfUF);

        addComponentFrame(getWComponents().getJLabel("Email", 20, 380, 50));

        tfEmail = getWComponents().getJTextField(false, 20, 398, 230);
        addComponentFrame(tfEmail);

        addComponentFrame(getWComponents().getJLabel("Telefone", 260, 380, 80));

        ftfTelefone = getWComponents().getJFormattedTextField(false, "(##)####-####", 260, 398, 125);
        addComponentFrame(ftfTelefone);

        addComponentFrame(getWComponents().getJLabel("Celular", 395, 380, 80));

        ftfCelular = getWComponents().getJFormattedTextField(false, "(##)#####-####", 395, 398, 127);
        addComponentFrame(ftfCelular);

        addComponentFrame(getWComponents().getJSeparator(450, 552));

        getWButtonsConfiguration().createBtnSalvar(75, 480);
        addComponentFrame(getWButtonsConfiguration().getBtnSalvar());

        getWButtonsConfiguration().createBtnDesfazer(178, 480);
        addComponentFrame(getWButtonsConfiguration().getBtnDesfazer());

        getWButtonsConfiguration().createBtnNovo(281, 480);
        addComponentFrame(getWButtonsConfiguration().getBtnNovo());

        getWButtonsConfiguration().createBtnExcluir(384, 480);
        addComponentFrame(getWButtonsConfiguration().getBtnExcluir());

        getWButtonsConfiguration().addWActionSalvarListener(this);
        getWButtonsConfiguration().addWActionDesfazerListener(this);
        getWButtonsConfiguration().addWActionNovoListener(this);
        getWButtonsConfiguration().addWActionExcluirListener(this);
        getWButtonsConfiguration().addWActionConsultarListener(this);
    }

    @Override
    public void defaultInitialize() {
        onChangedTipoPessoa();
        setFocusDefaultField();
    }

    private void setFocusDefaultField() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                tfNomeCliente.grabFocus();
            }
        });
    }

    @Override
    public boolean isHasDataObject() {
        return clienteAction.getCliente().getCdCliente() > 0;
    }

    private void onChangedTipoPessoa() {
        String valor = clienteAction.getControlTipoPessoa().getTypeI(FunctionsRadioButtonGroup.getSelectedIndex(rbTipoPessoa)).getValor();
        ftfCpf.setEnabled("F".equalsIgnoreCase(valor));
        ftfRg.setEnabled("F".equalsIgnoreCase(valor));
        cbSexo.setEnabled("F".equalsIgnoreCase(valor));
        ftfDtNascimento.setEnabled("F".equalsIgnoreCase(valor));
        ftfCnpj.setEnabled("J".equalsIgnoreCase(valor));
        ftfIe.setEnabled("J".equalsIgnoreCase(valor));

        ftfCpf.setText("");
        ftfRg.setText("");
        cbSexo.setSelectedIndex(0);
        ftfDtNascimento.setText("");
        ftfCnpj.setText("");
        ftfIe.setText("");
    }

    private void limparFrame() {
        tfNomeCliente.setText("");
        FunctionsRadioButtonGroup.setSelectedIndex(0, rbTipoPessoa);
        ftfCpf.setText("");
        ftfRg.setText("");
        cbSexo.setSelectedIndex(0);
        ftfDtNascimento.setText("");
        ftfCnpj.setText("");
        ftfIe.setText("");
        tfEndereco.setText("");
        tfNumero.setText("");
        tfComplemento.setText("");
        tfBairro.setText("");
        tfReferencia.setText("");
        ftfCep.setText("");
        tfCidade.setText("");
        tfUF.setText("");
        tfEmail.setText("");
        ftfTelefone.setText("");
        ftfCelular.setText("");
    }

    private void setClienteFrame() {
        Cliente cliente = clienteAction.getCliente();
        if (cliente.getCdCliente() > 0) {
            tfNomeCliente.setText(cliente.getNmCliente());
            FunctionsRadioButtonGroup.setSelectedIndex(clienteAction.getControlTipoPessoa().getTypeV(cliente.getIeTipoPessoa()).getIndex(), rbTipoPessoa);
            ftfCpf.setText(cliente.getNrCPF());
            ftfRg.setText(cliente.getNrRG());
            if (cliente.getIeSexo() != null) {
                cbSexo.setSelectedItem(clienteAction.getControlSexo().getOptionC(cliente.getIeSexo()));
            }
            if (cliente.getDtNascimento() != null) {
                ftfDtNascimento.setText(new SimpleDateFormat("dd/MM/yyyy").format(cliente.getDtNascimento()));
            }
            ftfCnpj.setText(cliente.getNrCNPJ());
            ftfIe.setText(cliente.getNrIE());
            tfEndereco.setText(cliente.getDsEndereco());
            tfNumero.setText(Integer.toString(cliente.getNrEndereco()));
            tfComplemento.setText(cliente.getDsComplemento());
            tfBairro.setText(cliente.getNmBairro());
            tfReferencia.setText(cliente.getDsReferencia());
            ftfCep.setText(cliente.getNrCep());
            tfCidade.setText(cliente.getNmCidade());
            tfUF.setText(cliente.getDsUF());
            tfEmail.setText(cliente.getDsEmail());
            ftfTelefone.setText(cliente.getNrTelefone());
            ftfCelular.setText(cliente.getNrCelular());
        }
    }

    @Override
    public void onSalvar() throws AbortException {
        try {
            String sexo = ((ComboBoxOption) cbSexo.getSelectedItem()).getChave();
            clienteAction.salvar(tfNomeCliente.getText(),
                    clienteAction.getControlTipoPessoa().getTypeI(FunctionsRadioButtonGroup.getSelectedIndex(rbTipoPessoa)).getValor(),
                    "   .   .   -  ".equals(ftfCpf.getText()) ? null : ftfCpf.getText(),
                    "   .   .   ".equals(ftfRg.getText()) ? null : ftfRg.getText(),
                    "0".equals(sexo) ? null : sexo,
                    "  /  /    ".equals(ftfDtNascimento.getText()) ? null : new SimpleDateFormat("dd/MM/yyyy").parse(ftfDtNascimento.getText()),
                    "  .   .   /    -  ".equals(ftfCnpj.getText()) ? null : ftfCnpj.getText(),
                    "   .   .   ".equals(ftfIe.getText()) ? null : ftfIe.getText(),
                    tfEndereco.getText(),
                    Integer.parseInt(tfNumero.getText()),
                    tfComplemento.getText(),
                    tfBairro.getText(),
                    "".equals(tfReferencia.getText()) ? null : tfReferencia.getText(),
                    "     -   ".equals(ftfCep.getText()) ? null : ftfCep.getText(),
                    tfCidade.getText(),
                    tfUF.getText(),
                    "".equals(tfEmail.getText()) ? null : tfEmail.getText(),
                    "(  )    -    ".equals(ftfTelefone.getText()) ? null : ftfTelefone.getText(),
                    "(  )     -    ".equals(ftfCelular.getText()) ? null : ftfCelular.getText());
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
    }

    @Override
    public void onDesfazer() {
        limparFrame();
        setClienteFrame();
        setFocusDefaultField();
    }

    @Override
    public void onNovo() {
        clienteAction.novo();
        limparFrame();
        setFocusDefaultField();
    }

    @Override
    public void onExcluir() throws AbortException {
        try {
            clienteAction.excluir();
            limparFrame();
            setFocusDefaultField();
        } catch (Exception ex) {
            throw new AbortException(ex.getMessage());
        }
    }

    @Override
    public void onConsultar() {
        ConsCliente consCliente = new ConsCliente(this, clienteAction);
        consCliente.activation();
        if (clienteAction.getCliente() != null) {
            limparFrame();
            setClienteFrame();
            clienteAction.limparListaClientes();
            setFocusDefaultField();
        }
    }

    @Override
    public void onChanged(WEvent evt) {
        super.onChanged(evt);
        if (WComponentType.RB.equals(evt.getComponentType())) {
            onChangedTipoPessoa();
        }
    }
}
