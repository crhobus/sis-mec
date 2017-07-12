package br.com.system.controller.action;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author crhobus
 */
public class Dispatcher {

    private static Dispatcher instance;
    private Set<ApplicationListener> listeners;

    private Dispatcher() {
        this.listeners = new HashSet<ApplicationListener>();
    }

    public static Dispatcher getInstance() {
        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

    public void addListener(ApplicationListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(ApplicationListener listener) {
        this.listeners.remove(listener);
    }

    public void dispatchEventInitialize() {
        Iterator<ApplicationListener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            ApplicationListener listener = (ApplicationListener) iterator.next();
            listener.initializeApplication();
        }
    }

    public void dispatchEventClose() {
        Iterator<ApplicationListener> iterator = listeners.iterator();
        while (iterator.hasNext()) {
            ApplicationListener listener = (ApplicationListener) iterator.next();
            listener.closeApplication();
        }
    }
}
