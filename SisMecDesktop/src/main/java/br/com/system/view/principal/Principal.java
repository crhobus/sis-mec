package br.com.system.view.principal;

import br.com.system.controller.action.Dispatcher;
import br.com.system.controller.action.Session;
import br.com.system.controller.agendaContato.AgendaContatoAction;
import br.com.system.controller.cliente.ClienteAction;
import br.com.system.controller.propriedades.PropriedadesAction;
import br.com.system.controller.usuario.UsuarioAction;
import br.com.system.view.agendaContato.CadasAgendaContato;
import br.com.system.view.agendaContato.ConsAgendaContato;
import br.com.system.view.cliente.CadasCliente;
import br.com.system.view.cliente.ConsCliente;
import br.com.system.view.components.WJFrame;
import br.com.system.view.components.WPanelImage;
import br.com.system.view.propriedades.PropriedadesSis;
import br.com.system.view.usuario.CadasUsuario;
import br.com.system.view.usuario.ConsUsuario;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author crhobus
 */
public class Principal extends WJFrame implements ActionListener {

    private final UsuarioAction usuarioAction;
    private JMenuItem miOS;
    private JMenuItem miSair;
    private JMenuItem miCadasUsuario;
    private JMenuItem miConsUsuario;
    private JMenuItem miCadasAgendaContato;
    private JMenuItem miConsAgendaContato;
    private JMenuItem miCadasCliente;
    private JMenuItem miConsCliente;
    private JMenuItem miPropriedades;
    private JMenuItem miVersao;

    public Principal(UsuarioAction usuarioAction) {
        super("Sistema Mecânica", 1200, 720);
        this.usuarioAction = usuarioAction;
        this.usuarioAction.novo();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JMenu mnSistema = new JMenu("Sistema");

        miOS = new JMenuItem("Ordem de Serviço");
        miOS.addActionListener(this);
        mnSistema.add(miOS);

        miPropriedades = new JMenuItem("Propriedades");
        miPropriedades.addActionListener(this);
        mnSistema.add(miPropriedades);

        miSair = new JMenuItem("Sair");
        miSair.addActionListener(this);
        mnSistema.add(miSair);

        JMenu mnCadastros = new JMenu("Cadastros");

        miCadasUsuario = new JMenuItem("Usuario");
        miCadasUsuario.addActionListener(this);
        mnCadastros.add(miCadasUsuario);

        miCadasCliente = new JMenuItem("Cliente");
        miCadasCliente.addActionListener(this);
        mnCadastros.add(miCadasCliente);

        miCadasAgendaContato = new JMenuItem("Agenda de Contato");
        miCadasAgendaContato.addActionListener(this);
        mnCadastros.add(miCadasAgendaContato);

        JMenu mnConsultas = new JMenu("Consultas");

        miConsUsuario = new JMenuItem("Usuarios");
        miConsUsuario.addActionListener(this);
        mnConsultas.add(miConsUsuario);

        miConsCliente = new JMenuItem("Clientes");
        miConsCliente.addActionListener(this);
        mnConsultas.add(miConsCliente);

        miConsAgendaContato = new JMenuItem("Agenda de Contatos");
        miConsAgendaContato.addActionListener(this);
        mnConsultas.add(miConsAgendaContato);

        JMenu mnUtilitarios = new JMenu("Utilitários");

        miVersao = new JMenuItem("Versão");
        miVersao.addActionListener(this);
        mnUtilitarios.add(miVersao);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(mnSistema);
        menuBar.add(mnCadastros);
        menuBar.add(mnConsultas);
        menuBar.add(mnUtilitarios);
        setJMenuBar(menuBar);

        WPanelImage panelImage = new WPanelImage("images/Fundo.png");
        panelImage.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelImage.setBorder(BorderFactory.createTitledBorder(""));
        add(panelImage, BorderLayout.CENTER);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelUsuario.setBorder(BorderFactory.createTitledBorder(""));
        panelUsuario.setPreferredSize(new Dimension(310, 40));

        JLabel lbNmPessoa = new JLabel("Nome: " + Session.getInstance().getUsuarioLogado().getNmPessoa());
        lbNmPessoa.setPreferredSize(new Dimension(150, 14));
        panelUsuario.add(lbNmPessoa);

        JLabel lbPermissao = new JLabel("Permissão: " + usuarioAction.getControlPermissoes().getOptionC(Integer.toString(Session.getInstance().getUsuarioLogado().getNrPermissao())).getDescricao());
        panelUsuario.add(lbPermissao);

        JPanel panelHorario = new JPanel();
        panelHorario.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelHorario.setBorder(BorderFactory.createTitledBorder(""));

        JLabel lbHorarioSistema = new JLabel("");
        lbHorarioSistema.setFont(new Font("Arial", Font.BOLD, 15));
        panelHorario.add(lbHorarioSistema);

        ThreadHoraSistema thread = new ThreadHoraSistema(lbHorarioSistema);
        thread.start();

        JPanel panelPersistencia = new JPanel();
        panelPersistencia.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelPersistencia.setBorder(BorderFactory.createTitledBorder(""));
        panelPersistencia.setPreferredSize(new Dimension(350, 40));

        JLabel lbPersistencia = new JLabel("Persistência de dados");
        panelPersistencia.add(lbPersistencia);

        JTextField tfPersistencia = new JTextField("Banco de Dados: MySQL 5.7.17.0");
        tfPersistencia.setEditable(false);
        tfPersistencia.setBackground(Color.WHITE);
        tfPersistencia.setPreferredSize(new Dimension(180, 22));
        panelPersistencia.add(tfPersistencia);

        JPanel panelBarraStatus = new JPanel();
        panelBarraStatus.setBorder(BorderFactory.createTitledBorder(""));
        panelBarraStatus.setLayout(new BorderLayout());
        panelBarraStatus.add(panelUsuario, BorderLayout.WEST);
        panelBarraStatus.add(panelHorario, BorderLayout.CENTER);
        panelBarraStatus.add(panelPersistencia, BorderLayout.EAST);
        add(panelBarraStatus, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evento) {
                close();
            }
        });
    }

    @Override
    public void defaultInitialize() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 600));
        setResizable(true);
    }

    private void close() {
        if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair do sistema", "Sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                Dispatcher.getInstance().dispatchEventClose();
                System.exit(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro de conexão", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openCadasUsuario() {
        CadasUsuario cadasUsuario = new CadasUsuario(this, usuarioAction);
        cadasUsuario.activation();
    }

    private void openConsUsuario() {
        ConsUsuario consUsuario = new ConsUsuario(this, usuarioAction);
        consUsuario.activation();
    }

    private void openCadasAgendaContato() {
        CadasAgendaContato cadasAgendaContato = new CadasAgendaContato(this, new AgendaContatoAction());
        cadasAgendaContato.activation();
    }

    private void openConsAgendaContato() {
        ConsAgendaContato consAgendaContato = new ConsAgendaContato(this, new AgendaContatoAction());
        consAgendaContato.activation();
    }

    private void openPropriedadesSistema() {
        PropriedadesSis propriedadesSis = new PropriedadesSis(this, new PropriedadesAction());
        propriedadesSis.activation();
    }

    private void openCadasCliente() {
        CadasCliente cadasCliente = new CadasCliente(this, new ClienteAction());
        cadasCliente.activation();
    }

    private void openConsCliente() {
        ConsCliente consCliente = new ConsCliente(this, new ClienteAction());
        consCliente.activation();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == miSair) {
            close();
        } else if (evt.getSource() == miCadasUsuario) {
            openCadasUsuario();
        } else if (evt.getSource() == miConsUsuario) {
            openConsUsuario();
        } else if (evt.getSource() == miCadasAgendaContato) {
            openCadasAgendaContato();
        } else if (evt.getSource() == miConsAgendaContato) {
            openConsAgendaContato();
        } else if (evt.getSource() == miPropriedades) {
            openPropriedadesSistema();
        } else if (evt.getSource() == miCadasCliente) {
            openCadasCliente();
        } else if (evt.getSource() == miConsCliente) {
            openConsCliente();
        }
    }
}
