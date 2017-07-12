package br.com.system.view.components;

import br.com.system.controller.propriedades.PropriedadesAction;
import br.com.system.controller.security.SisMecSecurity;
import java.util.Map;
import javax.crypto.SecretKey;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author crhobus
 */
public class WEmail {

    private final PropriedadesAction propriedadesAction;

    public WEmail() {
        this.propriedadesAction = new PropriedadesAction();
    }

    public void send(String to, String subject, String msg) throws Exception {
        Map<String, Object> propriedadesMap = propriedadesAction.getPropriedades();
        try {
            if (!propriedadesMap.isEmpty()) {

                SisMecSecurity security = new SisMecSecurity();
                SecretKey secretKey = security.getSecretKey((byte[]) propriedadesMap.get("secretKey"));

                Email email = new SimpleEmail();
                email.setHostName((String) propriedadesMap.get("hostName"));//Servidor SMTP para envio do e-mail
                email.setSmtpPort(Integer.parseInt((String) propriedadesMap.get("smtpPort")));//Porta SMTP
                email.setAuthenticator(new DefaultAuthenticator((String) propriedadesMap.get("userEmail"),
                        security.decryptDataSymmetric(secretKey, (byte[]) propriedadesMap.get("passwordEmail"))));//Autenticação conta
                email.setSSLOnConnect("S".equalsIgnoreCase((String) propriedadesMap.get("ieSSLOnConnect")));
                email.setFrom((String) propriedadesMap.get("userEmail"), (String) propriedadesMap.get("accountName"));//Remetente
                email.setSubject(subject);//Assunto
                email.setMsg(msg);//Conteúdo do email
                email.addTo(to);//Destinatário
                email.send();//Envia o email

            } else {
                throw new Exception("Não foi possível enviar o email!\n"
                        + "Verifique as propriedades cadastradas no sistema se estão de acordo com o servidor de email");
            }
        } catch (Exception ex) {
            throw new Exception("Não foi possível enviar o email!\n"
                    + "Verifique as propriedades cadastradas no sistema se estão de acordo com o servidor de email");
        }
    }
}
