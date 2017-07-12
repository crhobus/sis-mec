package br.com.system.view.components.events;

import java.util.EventListener;

/**
 *
 * @author crhobus
 */
public interface WFilterTypeListener extends EventListener {

    public void onFilterType(String ColumnName, int columnFilter);
}
