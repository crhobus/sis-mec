package br.com.system.view.components;

import br.com.system.view.components.events.WActionCancelarListener;
import br.com.system.view.components.events.WActionCarregarListener;
import br.com.system.view.components.events.WActionConsultarListener;
import br.com.system.view.components.events.WActionDesfazerListener;
import br.com.system.view.components.events.WActionExcluirListener;
import br.com.system.view.components.events.WActionFiltrarListener;
import br.com.system.view.components.events.WActionNovoListener;
import br.com.system.view.components.events.WActionOkListener;
import br.com.system.view.components.events.WActionSalvarListener;
import br.com.system.view.components.events.WType;
import javax.swing.JButton;
import javax.swing.event.EventListenerList;

/**
 *
 * @author crhobus
 */
public class WButtonsConfiguration {

    private final WJComponents components;
    private final EventListenerList listenerList;
    private JButton btnConsultar;
    private JButton btnSalvar;
    private JButton btnDesfazer;
    private JButton btnNovo;
    private JButton btnExcluir;
    private JButton btnOK;
    private JButton btnCancelar;
    private JButton btnFiltrar;
    private JButton btnCarregar;
    private boolean permissaoConsultar = true;
    private boolean permissaoSalvar = true;
    private boolean permissaoDesfazer = true;
    private boolean permissaoNovo = true;
    private boolean permissaoExcluir = true;
    private boolean permissaoCarregar = true;

    public WButtonsConfiguration(WJComponents components) {
        this.components = components;
        this.listenerList = new EventListenerList();
    }

    public EventListenerList getEventListenerList() {
        return listenerList;
    }

    private WJComponents getWComponents() {
        return components;
    }

    private JButton getJButtonType(WType type, int x, int y) {
        switch (type) {
            case CONSULT:
                return getWComponents().getJButton(type, "Consultar", "images/Consultar.png", "Consultar registros", x, y);
            case SAVE:
                return getWComponents().getJButton(type, "Salvar", "images/Salvar.png", "Salvar registro", x, y);
            case UNDO:
                return getWComponents().getJButton(type, "Desfazer", "images/Desfazer.png", "Desfazer edição reistro", x, y);
            case NEW:
                return getWComponents().getJButton(type, "Novo", "images/Novo.png", "Cadastrar novo registro", x, y);
            case DELETE:
                return getWComponents().getJButton(type, "Excluir", "images/Excluir.png", "Excluir registro", x, y);
            case OK:
                return getWComponents().getJButton(type, "OK", "images/OK.png", "OK", x, y);
            case CANCEL:
                return getWComponents().getJButton(type, "Cancelar", "images/Cancelar.png", "Cancelar", x, y);
            case FILTER:
                return getWComponents().getJButton(type, "Filtrar", "images/Filtrar.png", "Filtrar", x, y);
            case UPLOAD:
                return getWComponents().getJButton(type, "Carregar", "images/Upload.png", "Carregar", x, y);
            default:
                return null;
        }
    }

    public void createBtnConsultar(int x, int y) {
        btnConsultar = getJButtonType(WType.CONSULT, x, y);
    }

    public void createBtnSalvar(int x, int y) {
        btnSalvar = getJButtonType(WType.SAVE, x, y);
    }

    public void createBtnDesfazer(int x, int y) {
        btnDesfazer = getJButtonType(WType.UNDO, x, y);
    }

    public void createBtnNovo(int x, int y) {
        btnNovo = getJButtonType(WType.NEW, x, y);
    }

    public void createBtnExcluir(int x, int y) {
        btnExcluir = getJButtonType(WType.DELETE, x, y);
    }

    public void createBtnOK(int x, int y) {
        btnOK = getJButtonType(WType.OK, x, y);
    }

    public void createBtnCancelar(int x, int y) {
        btnCancelar = getJButtonType(WType.CANCEL, x, y);
    }

    public void createBtnFiltrar(int x, int y) {
        btnFiltrar = getJButtonType(WType.FILTER, x, y);
    }

    public void createBtnCarregar(int x, int y) {
        btnCarregar = getJButtonType(WType.UPLOAD, x, y);
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }

    public JButton getBtnDesfazer() {
        return btnDesfazer;
    }

    public JButton getBtnNovo() {
        return btnNovo;
    }

    public JButton getBtnExcluir() {
        return btnExcluir;
    }

    public JButton getBtnOK() {
        return btnOK;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }

    public JButton getBtnCarregar() {
        return btnCarregar;
    }

    public void addWActionSalvarListener(WActionSalvarListener listener) {
        listenerList.add(WActionSalvarListener.class, listener);
    }

    public void removeWActionSalvarListener(WActionSalvarListener listener) {
        listenerList.remove(WActionSalvarListener.class, listener);
    }

    public void addWActionDesfazerListener(WActionDesfazerListener listener) {
        listenerList.add(WActionDesfazerListener.class, listener);
    }

    public void removeWActionDesfazerListener(WActionDesfazerListener listener) {
        listenerList.remove(WActionDesfazerListener.class, listener);
    }

    public void addWActionNovoListener(WActionNovoListener listener) {
        listenerList.add(WActionNovoListener.class, listener);
    }

    public void removeWActionNovoListener(WActionNovoListener listener) {
        listenerList.remove(WActionNovoListener.class, listener);
    }

    public void addWActionExcluirListener(WActionExcluirListener listener) {
        listenerList.add(WActionExcluirListener.class, listener);
    }

    public void removeWActionExcluirListener(WActionExcluirListener listener) {
        listenerList.remove(WActionExcluirListener.class, listener);
    }

    public void addWActionConsultarListener(WActionConsultarListener listener) {
        listenerList.add(WActionConsultarListener.class, listener);
    }

    public void removeWActionConsultarListener(WActionConsultarListener listener) {
        listenerList.remove(WActionConsultarListener.class, listener);
    }

    public void addWActionOkListener(WActionOkListener listener) {
        listenerList.add(WActionOkListener.class, listener);
    }

    public void removeWActionOkListener(WActionOkListener listener) {
        listenerList.remove(WActionOkListener.class, listener);
    }

    public void addWActionCancelarListener(WActionCancelarListener listener) {
        listenerList.add(WActionCancelarListener.class, listener);
    }

    public void removeWActionCancelarListener(WActionCancelarListener listener) {
        listenerList.remove(WActionCancelarListener.class, listener);
    }

    public void addWActionFiltrarListener(WActionFiltrarListener listener) {
        listenerList.add(WActionFiltrarListener.class, listener);
    }

    public void removeWActionFiltrarListener(WActionFiltrarListener listener) {
        listenerList.remove(WActionFiltrarListener.class, listener);
    }

    public void addWActionCarregarListener(WActionCarregarListener listener) {
        listenerList.add(WActionCarregarListener.class, listener);
    }

    public void removeWActionCarregarListener(WActionCarregarListener listener) {
        listenerList.remove(WActionCarregarListener.class, listener);
    }

    public boolean isPermissaoConsultar() {
        return permissaoConsultar;
    }

    public void setPermissaoConsultar(boolean permissaoConsultar) {
        this.permissaoConsultar = permissaoConsultar;
    }

    public boolean isPermissaoSalvar() {
        return permissaoSalvar;
    }

    public void setPermissaoSalvar(boolean permissaoSalvar) {
        this.permissaoSalvar = permissaoSalvar;
    }

    public boolean isPermissaoDesfazer() {
        return permissaoDesfazer;
    }

    public void setPermissaoDesfazer(boolean permissaoDesfazer) {
        this.permissaoDesfazer = permissaoDesfazer;
    }

    public boolean isPermissaoNovo() {
        return permissaoNovo;
    }

    public void setPermissaoNovo(boolean permissaoNovo) {
        this.permissaoNovo = permissaoNovo;
    }

    public boolean isPermissaoExcluir() {
        return permissaoExcluir;
    }

    public void setPermissaoExcluir(boolean permissaoExcluir) {
        this.permissaoExcluir = permissaoExcluir;
    }

    public boolean isPermissaoCarregar() {
        return permissaoCarregar;
    }

    public void setPermissaoCarregar(boolean permissaoCarregar) {
        this.permissaoCarregar = permissaoCarregar;
    }
}
