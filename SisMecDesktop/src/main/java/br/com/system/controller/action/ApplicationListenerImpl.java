package br.com.system.controller.action;

import br.com.system.persistence.JPAUtils;

/**
 *
 * @author crhobus
 */
public class ApplicationListenerImpl implements ApplicationListener {

    @Override
    public void initializeApplication() {
        JPAUtils.getInstance();
    }

    @Override
    public void closeApplication() {
        JPAUtils.getInstance().closeEntityManagerFactory();
    }
}
