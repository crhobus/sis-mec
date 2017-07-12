package br.com.system.view.components.events;

import br.com.system.view.components.AbortException;
import java.util.EventListener;

/**
 *
 * @author crhobus
 */
public interface WActionSalvarListener extends EventListener {

    public void onSalvar() throws AbortException;
}
