package br.com.system.main;

import br.com.system.controller.action.ApplicationListenerImpl;
import br.com.system.controller.action.Dispatcher;
import br.com.system.view.login.Login;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author crhobus
 */
public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O sistema encontrou um problema ao iniciar a aplicação.\n"
                    + "Entre em contato com o administrador do sistema reportando o problema", "Info", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        Dispatcher.getInstance().addListener(new ApplicationListenerImpl());
        Dispatcher.getInstance().dispatchEventInitialize();
        Login login = new Login();
        login.activation();
    }
}
