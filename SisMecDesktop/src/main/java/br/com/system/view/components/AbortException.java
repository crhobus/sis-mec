package br.com.system.view.components;

/**
 *
 * @author crhobus
 */
public class AbortException extends Exception {

    public AbortException() {
        super();
    }

    public AbortException(String message) {
        super(message);
    }

    public AbortException(String message, Throwable cause) {
        super(message, cause);
    }
}
