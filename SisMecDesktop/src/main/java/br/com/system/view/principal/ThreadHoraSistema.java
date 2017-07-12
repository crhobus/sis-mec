package br.com.system.view.principal;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author crhobus
 */
public class ThreadHoraSistema extends Thread {

    private final SimpleDateFormat formatDateHora;
    private final JLabel lbHorario;

    public ThreadHoraSistema(JLabel lbHorario) {
        this.lbHorario = lbHorario;
        formatDateHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
                lbHorario.setText(formatDateHora.format(new Date()));
            } catch (InterruptedException ex) {}
        }
    }
}
