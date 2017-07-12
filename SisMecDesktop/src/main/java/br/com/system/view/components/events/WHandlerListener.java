package br.com.system.view.components.events;

import java.util.EventListener;

/**
 *
 * @author crhobus
 */
public interface WHandlerListener extends EventListener {

    public void onHandler(WEvent evt);
}
