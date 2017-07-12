package br.com.system.view.usuario;

import br.com.system.controller.usuario.UsuarioAction;
import br.com.system.view.components.WJDialog;
import br.com.system.view.components.events.WActionCancelarListener;
import br.com.system.view.components.events.WActionFiltrarListener;
import br.com.system.view.components.events.WActionOkListener;
import br.com.system.view.components.events.WFilterTypeListener;
import br.com.system.view.components.events.WShowRecordListener;
import br.com.system.view.usuario.tableModel.TableModelUsuario;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author crhobus
 */
public class ConsUsuario extends WJDialog implements WActionFiltrarListener, WActionOkListener, WActionCancelarListener, WShowRecordListener, WFilterTypeListener {

    private final UsuarioAction usuarioAction;
    private JTextField tfFiltro;
    private JLabel lbFiltro;
    private JTable tabela;
    private final TableModelUsuario tableModel;
    private int columnFilter;
    private final Window window;

    public ConsUsuario(Window window, UsuarioAction usuarioAction) {
        super(window, "Usuário - Sistema Mecânica", 745, 540, FrameType.CONSULTA);
        this.window = window;
        this.usuarioAction = usuarioAction;
        this.tableModel = new TableModelUsuario(this.usuarioAction);
        initComponents();
    }

    private void initComponents() {
        createNewFrame(10, 10, 720, 495);

        lbFiltro = getWComponents().getJLabel("Nome", 20, 20, 100);
        addComponentFrame(lbFiltro);

        tfFiltro = getWComponents().getJTextField(false, 20, 38, 250);
        addComponentFrame(tfFiltro);

        getWButtonsConfiguration().createBtnFiltrar(280, 37);
        addComponentFrame(getWButtonsConfiguration().getBtnFiltrar());

        tabela = getWComponents().getJTable(tableModel, new int[]{250, 150, 120, 120, 100, 180});
        addComponentFrame(getWComponents().getJScrollPaneTable(tabela, 20, 80, 680, 360));

        getWButtonsConfiguration().createBtnOK(245, 452);
        addComponentFrame(getWButtonsConfiguration().getBtnOK());

        getWButtonsConfiguration().createBtnCancelar(385, 452);
        addComponentFrame(getWButtonsConfiguration().getBtnCancelar());

        getWComponents().addWShowRecordListener(this);
        getWComponents().addWFilterTypeListener(this);
        getWButtonsConfiguration().addWActionFiltrarListener(this);
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
    public void onFiltrar() {
        usuarioAction.listarUsuarios(columnFilter, tfFiltro.getText());
        tableModel.fireTableDataChanged();
        if (tabela.getRowCount() > 0) {
            tabela.addRowSelectionInterval(0, 0);
        }
    }

    @Override
    public void onOk() {
        if (window instanceof JDialog) {
            usuarioAction.obterUsuarioLista(tabela.getSelectedRow());
        }
        this.dispose();
    }

    @Override
    public void onCancelar() {
        this.dispose();
    }

    @Override
    public void onShowRecord(int index) {
        if (window instanceof JDialog) {
            usuarioAction.obterUsuarioLista(index);
            this.dispose();
        }
    }

    @Override
    public void onFilterType(String ColumnName, int columnFilter) {
        this.columnFilter = columnFilter;
        lbFiltro.setText(ColumnName);
    }
}
