package br.com.system.view.components.events;

import java.util.EventListener;

/**
 *
 * @author crhobus
 */
public interface WChangedListener extends EventListener {

    public void onChanged(WEvent evt);
}
