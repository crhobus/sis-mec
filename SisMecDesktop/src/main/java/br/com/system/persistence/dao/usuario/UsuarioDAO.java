package br.com.system.persistence.dao.usuario;

import br.com.system.controller.action.comboBox.ComboBoxOption;
import br.com.system.controller.action.comboBox.ControlComboBoxOption;
import br.com.system.model.Usuario;
import br.com.system.persistence.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author crhobus
 */
public class UsuarioDAO {

    private final Service<Usuario> serviceUsuario;

    public UsuarioDAO() {
        this.serviceUsuario = new Service<>(Usuario.class);
    }

    public void insert(Usuario usuario) throws Exception {
        serviceUsuario.insert(usuario);
    }

    public void update(Usuario usuario) throws Exception {
        serviceUsuario.update(usuario);
    }

    public void delete(long cdUsuario) throws Exception {
        serviceUsuario.delete(cdUsuario);
    }

    public Usuario getUsuario(String nmUsuario) throws Exception {
        Map parameters = new HashMap();
        parameters.put("nmUsuario", nmUsuario);
        return serviceUsuario.getSingleResultDataQuery("select u from Usuario as u where u.nmUsuario = :nmUsuario", parameters);
    }

    public int getQtUsuariosCadastrados() throws Exception {
        return ((Number) serviceUsuario.getSingleResultDataQueryType("select count(u) from Usuario as u", new HashMap<>())).intValue();
    }

    public boolean isExisteUsuario(String nmUsuario) throws Exception {
        Map parameters = new HashMap();
        parameters.put("nmUsuario", nmUsuario);
        return ((Number) serviceUsuario.getSingleResultDataQueryType("select count(u) from Usuario as u where u.nmUsuario = :nmUsuario", parameters)).intValue() > 0;
    }

    public List<Usuario> getUsuarios() throws Exception {
        return serviceUsuario.getListResultDataQuery("select u from Usuario as u order by u.nmPessoa", new HashMap<>());
    }

    public List<Usuario> getUsuarios(int indiceColuna, String filtro, ControlComboBoxOption controlPermissoes) throws Exception {
        filtro = filtro.toLowerCase();
        Map parameters = new HashMap();
        switch (indiceColuna) {
            case 0:
                parameters.put("nmPessoa", ("%" + filtro + "%"));
                return serviceUsuario.getListResultDataQuery("select u from Usuario as u where lower(u.nmPessoa) like :nmPessoa order by u.nmPessoa", parameters);
            case 1:
                parameters.put("nmUsuario", ("%" + filtro + "%"));
                return serviceUsuario.getListResultDataQuery("select u from Usuario as u where lower(u.nmUsuario) like :nmUsuario order by u.nmPessoa", parameters);
            case 2:
                parameters.put("ieSituacao", ("%" + filtro + "%"));
                return serviceUsuario.getListResultDataQuery("select u from Usuario as u where lower(u.ieSituacao) like :ieSituacao order by u.nmPessoa", parameters);
            case 3:
                if ("".equals(filtro)) {
                    return getUsuarios();
                }
                ComboBoxOption permissao = controlPermissoes.getOptionD(filtro);
                if (permissao == null) {
                    return new ArrayList<>();
                }
                parameters.put("nrPermissao", Integer.parseInt(permissao.getChave()));
                return serviceUsuario.getListResultDataQuery("select u from Usuario as u where u.nrPermissao = :nrPermissao order by u.nmPessoa", parameters);
            case 4:
                parameters.put("nrCelular", ("%" + filtro + "%"));
                return serviceUsuario.getListResultDataQuery("select u from Usuario as u where u.nrCelular like :nrCelular order by u.nmPessoa", parameters);
            case 5:
                parameters.put("dsEmail", ("%" + filtro + "%"));
                return serviceUsuario.getListResultDataQuery("select u from Usuario as u where lower(u.dsEmail) like :dsEmail order by u.nmPessoa", parameters);
            default:
                return new ArrayList<>();
        }
    }
}
