package br.com.system.controller.usuario;

import br.com.system.model.Usuario;

/**
 *
 * @author crhobus
 */
public class ChangePasswordException extends Exception {

    private final Usuario usuario;

    public ChangePasswordException(Usuario usuario) {
        super();
        this.usuario = usuario;
    }

    public ChangePasswordException(Usuario usuario, String message) {
        super(message);
        this.usuario = usuario;
    }

    public ChangePasswordException(Usuario usuario, String message, Throwable cause) {
        super(message, cause);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
