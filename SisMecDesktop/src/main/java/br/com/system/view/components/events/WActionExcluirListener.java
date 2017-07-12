package br.com.system.view.components.events;

import br.com.system.view.components.AbortException;
import java.util.EventListener;

/**
 *
 * @author crhobus
 */
public interface WActionExcluirListener extends EventListener {

    public void onExcluir() throws AbortException;
}
