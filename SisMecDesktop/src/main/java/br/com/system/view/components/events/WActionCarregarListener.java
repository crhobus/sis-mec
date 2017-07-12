package br.com.system.view.components.events;

import br.com.system.view.components.AbortException;
import java.util.EventListener;

/**
 *
 * @author crhobus
 */
public interface WActionCarregarListener extends EventListener {

    public void onCarregar() throws AbortException;
}
