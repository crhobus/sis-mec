package br.com.system.controller.propriedades;

import br.com.system.controller.security.SisMecSecurity;
import br.com.system.model.Propriedade;
import br.com.system.persistence.dao.propriedades.PropriedadesDAO;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.SecretKey;

/**
 *
 * @author crhobus
 */
public class PropriedadesAction {

    private final PropriedadesDAO propriedadesDAO;

    public PropriedadesAction() {
        this.propriedadesDAO = new PropriedadesDAO();
    }

    public void salvar(String accountName, String userEmail, String passwordEmail, String hostName,
            String smtpPort, String ieSSLOnConnect, String secretKey) throws Exception {
        if (secretKey.trim().length() < 16) {
            throw new Exception("A chave de criptografia do sistema deve ter no mínimo 16 caracteres!");
        }

        SisMecSecurity security = new SisMecSecurity();
        boolean novaChaveSecretaDigitada = false;
        SecretKey k;
        if (propriedadesDAO.isExisteChaveSecretaCadastrada("secretKey")) {
            k = security.getSecretKey(propriedadesDAO.getPropriedade("secretKey").getVlPropriedadeData());
            if (!"****************".equals(secretKey)) {
                verificaChaveCriptografiaValida(secretKey);
                SecretKey kDigitada = security.getSecretKey(secretKey);
                if (!Arrays.equals(k.getEncoded(), kDigitada.getEncoded())) {
                    k = kDigitada;
                    novaChaveSecretaDigitada = true;
                }
            }
        } else {
            verificaChaveCriptografiaValida(secretKey);
            k = security.getSecretKey(secretKey);
            novaChaveSecretaDigitada = true;
        }

        if (novaChaveSecretaDigitada
                && "****************".equals(passwordEmail)) {
            throw new Exception("Você digitou uma nova chave de criptografia do sistema!\n"
                    + "Você deverá digitar novamente a senha da conta de email.");
        }

        addPropriedade("accountName", accountName);
        addPropriedade("userEmail", userEmail);
        if (!"****************".equals(passwordEmail)) {
            addPropriedade("passwordEmail", security.encryptDataSymmetric(k, passwordEmail));
        }
        addPropriedade("hostName", hostName);
        addPropriedade("smtpPort", smtpPort);
        addPropriedade("ieSSLOnConnect", ieSSLOnConnect);
        addPropriedade("secretKey", k.getEncoded());
    }

    private void addPropriedade(String nome, Object propriedade) throws Exception {
        Propriedade p = propriedadesDAO.getPropriedade(nome);

        if (p == null) {
            p = new Propriedade();
            p.setNmPropriedade(nome);
            p.setDtCriacao(new Date());
            p.setDtAtualizacao(new Date());
        }

        if (propriedade instanceof byte[]) {
            p.setVlPropriedadeData((byte[]) propriedade);
        } else {
            p.setVlPropriedadeStr((String) propriedade);
        }

        if (p.getCdPropriedade() > 0L) {
            propriedadesDAO.update(p);
        } else {
            propriedadesDAO.insert(p);
        }
    }

    private void verificaChaveCriptografiaValida(String secretKey) throws Exception {
        boolean possuiDigitos = false;
        boolean possuiLetras = false;
        char[] arrayChar = secretKey.toCharArray();
        for (char c : arrayChar) {
            if (Character.isDigit(c)) {
                possuiDigitos = true;
            }
            if (Character.isLetter(c)) {
                possuiLetras = true;
            }
        }
        if (!possuiDigitos
                || !possuiLetras) {
            throw new Exception("A chave de criptografia do sistema digitada é inválida!\n"
                    + "Por favor, digite outra chave válida contendo letras e números");
        }
    }

    public Map<String, Object> getPropriedades() throws Exception {
        List<Propriedade> propriedades = propriedadesDAO.getPropriedades();

        Map<String, Object> propriedadesMap = new HashMap<>();
        for (Propriedade p : propriedades) {
            if (p.getVlPropriedadeStr() != null) {
                propriedadesMap.put(p.getNmPropriedade(), p.getVlPropriedadeStr());
            }
            if (p.getVlPropriedadeData() != null) {
                propriedadesMap.put(p.getNmPropriedade(), p.getVlPropriedadeData());
            }
        }
        return propriedadesMap;
    }
}
