package br.com.system.controller.action;

import br.com.system.model.Usuario;

/**
 *
 * @author crhobus
 */
public class Session {

    private static Session instance;
    private Usuario usuarioLogado;

    private Session() {}

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }
}
