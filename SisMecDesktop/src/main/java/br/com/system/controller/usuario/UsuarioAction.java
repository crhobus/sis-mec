package br.com.system.controller.usuario;

import br.com.system.controller.action.Session;
import br.com.system.controller.action.comboBox.ComboBoxOption;
import br.com.system.controller.action.comboBox.ControlComboBoxOption;
import br.com.system.controller.security.SisMecSecurity;
import br.com.system.model.Usuario;
import br.com.system.persistence.dao.usuario.UsuarioDAO;
import br.com.system.view.components.WEmail;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author crhobus
 */
public class UsuarioAction {

    private final UsuarioDAO usuarioDAO;
    private final ControlComboBoxOption controlPermissoes;
    private Usuario usuario;
    private List<Usuario> usuarios;

    public UsuarioAction() {
        this.usuarioDAO = new UsuarioDAO();
        this.controlPermissoes = new ControlComboBoxOption();
        this.controlPermissoes.addOption(new ComboBoxOption("1", "Administrador"));
        this.controlPermissoes.addOption(new ComboBoxOption("2", "Consulta"));
        novo();
    }

    public void validaUsuario(String nmUsuario, String dsSenha) throws ChangePasswordException, Exception {
        if ("".equals(nmUsuario.trim())) {
            throw new Exception("Informe o usuário!");
        }
        if ("".equals(dsSenha.trim())) {
            throw new Exception("Informe a senha!");
        }
        Usuario u = usuarioDAO.getUsuario(nmUsuario);
        if (u == null) {
            throw new Exception("Usuário/Senha inválido.");
        }
        byte[] senha = new SisMecSecurity().generateHash(dsSenha);
        if (Arrays.equals(u.getDsSenha(), senha)
                && "A".equalsIgnoreCase(u.getIeSituacao())
                && u.getNrTentativas() <= 3) {
            if (u.getNrTentativas() == -1) {
                throw new ChangePasswordException(u);
            }
            u.setNrTentativas(0);
            usuarioDAO.update(u);
            Session.getInstance().setUsuarioLogado(u);
        } else {
            if (u.getNrTentativas() != -1) {
                u.setNrTentativas(u.getNrTentativas() + 1);
                usuarioDAO.update(u);
            }
            if (u.getNrTentativas() > 3) {
                throw new Exception("Usuário bloqueado.\n"
                        + "Entre em contato com o administrador do sistema!");
            }
            throw new Exception("Usuário/Senha inválido.");
        }
    }

    public boolean possuiUsuarioCadstrado() throws Exception {
        try {
            return usuarioDAO.getQtUsuariosCadastrados() > 0;
        } catch (Exception ex) {
            throw new Exception("O sistema encontrou um problema ao iniciar a aplicação.\n"
                    + "Entre em contato com o administrador do sistema reportando o problema!\n"
                    + "Erro ao consultar usuários ativos no sistema.");
        }
    }

    public void enviarEmailRecuperacaoSenha(String nmUsuario, String email) throws Exception {
        if ("".equals(email.trim())) {
            throw new Exception("Informe o email que foi cadastrado para este usuário!");
        }
        Usuario u = usuarioDAO.getUsuario(nmUsuario);
        if (u == null) {
            throw new Exception("Usuário informado não existe no sistema");
        }
        if (!email.equals(u.getDsEmail())) {
            throw new Exception("O email informado não pertence ao usuário informado na tela de login!");
        }
        String senha = getNovaSenha();
        u.setDsSenha(new SisMecSecurity().generateHash(senha));
        WEmail wEmail = new WEmail();
        wEmail.send(email,
                "Email de recuperação de senha do usuário",
                "Nova senha gerada pelo sistema\n"
                + "Entre no sistema com a nova senha\n\n"
                + "Senha: " + senha
                + "\n\nSistema Mecânica\n");
        u.setNrTentativas(-1);
        usuarioDAO.update(u);
    }

    private String getNovaSenha() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(50, random).toString(32);
    }

    public void novo() {
        usuario = new Usuario();
    }

    public void salvar(String nomePessoa, String nmUsuario, int permissao, String senha, String email,
            String celular, String situacao, boolean changePassword) throws Exception {
        if (usuario.getCdUsuario() > 0) {
            if (!usuario.getNmPessoa().equalsIgnoreCase(nomePessoa)) {
                throw new Exception("Nome da pessoa não pode ser alterado!");
            }
            if (!usuario.getNmUsuario().equalsIgnoreCase(nmUsuario)) {
                throw new Exception("Nome de usuário não pode ser alterado!");
            }
            Usuario u = Session.getInstance().getUsuarioLogado();
            if (u != null
                    && !usuario.getNmUsuario().equalsIgnoreCase(u.getNmUsuario())) {
                usuario.setNrTentativas(-1);
            }
            if (changePassword) {
                usuario.setNrTentativas(0);
            }
        } else {
            if (usuarioDAO.isExisteUsuario(nmUsuario)) {
                throw new Exception("Este nome de usuário já existe no sistema!\n"
                        + "Por favor, entre com outro nome de usuário.");
            }
            usuario.setNmPessoa(nomePessoa);
            usuario.setNmUsuario(nmUsuario);
            usuario.setDtCriacao(new Date());
            usuario.setNrTentativas(0);
        }
        usuario.setDsSenha(new SisMecSecurity().generateHash(senha));
        usuario.setNrPermissao(permissao);
        usuario.setIeSituacao(situacao);
        usuario.setNrCelular(celular);
        usuario.setDsEmail(email);
        usuario.setDtAtualizacao(new Date());

        if (usuario.getCdUsuario() > 0) {
            usuarioDAO.update(usuario);
        } else {
            usuarioDAO.insert(usuario);
        }
    }

    public void excluir() throws Exception {
        usuarioDAO.delete(usuario.getCdUsuario());
        novo();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void listarUsuarios() {
        try {
            usuarios = usuarioDAO.getUsuarios();
        } catch (Exception ex) {
            usuarios = new ArrayList<>();
        }
    }

    public void listarUsuarios(int indiceColuna, String filtro) {
        try {
            usuarios = usuarioDAO.getUsuarios(indiceColuna, filtro, getControlPermissoes());
        } catch (Exception ex) {
            usuarios = new ArrayList<>();
        }
    }

    public void limparListaUsuarios() {
        usuarios = null;
    }

    public int getQtUsuariosCadastrados() {
        if (usuarios != null
                && !usuarios.isEmpty()) {
            return usuarios.size();
        }
        return 0;
    }

    public int getQtColunasTabela() {
        return 6;
    }

    public Object getRegistrosTableModel(int linha, int coluna) {
        Usuario u = usuarios.get(linha);
        switch (coluna) {
            case 0:
                return u.getNmPessoa();
            case 1:
                return u.getNmUsuario();
            case 2:
                return u.getIeSituacao();
            case 3:
                return getControlPermissoes().getOptionC(Integer.toString(u.getNrPermissao())).getDescricao();
            case 4:
                return u.getNrCelular();
            default:
                return u.getDsEmail();
        }
    }

    public void obterUsuarioLista(int indice) {
        usuario = usuarios.get(indice);
    }

    public ControlComboBoxOption getControlPermissoes() {
        return controlPermissoes;
    }
}
