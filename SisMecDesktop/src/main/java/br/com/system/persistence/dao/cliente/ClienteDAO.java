package br.com.system.persistence.dao.cliente;

import br.com.system.controller.action.radioButton.ControlRadioButtonType;
import br.com.system.controller.action.radioButton.RadioButtonType;
import br.com.system.model.Cliente;
import br.com.system.persistence.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author crhobus
 */
public class ClienteDAO {

    private final Service<Cliente> serviceCliente;

    public ClienteDAO() {
        this.serviceCliente = new Service<>(Cliente.class);
    }

    public void insert(Cliente cliente) throws Exception {
        serviceCliente.insert(cliente);
    }

    public void update(Cliente cliente) throws Exception {
        serviceCliente.update(cliente);
    }

    public void delete(long cdCliente) throws Exception {
        serviceCliente.delete(cdCliente);
    }

    public List<Cliente> getClientes() throws Exception {
        return serviceCliente.getListResultDataQuery("select c from Cliente as c order by c.nmCliente", new HashMap<>());
    }

    public List<Cliente> getClientes(int indiceColuna, String filtro, ControlRadioButtonType controlTipoPessoa) throws Exception {
        filtro = filtro.toLowerCase();
        Map parameters = new HashMap();
        switch (indiceColuna) {
            case 0:
                parameters.put("nmCliente", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where lower(c.nmCliente) like :nmCliente order by c.nmCliente", parameters);
            case 1:
                if ("".equals(filtro)) {
                    return getClientes();
                }
                RadioButtonType tipoPessoa = controlTipoPessoa.getTypeD(filtro);
                if (tipoPessoa == null) {
                    return new ArrayList<>();
                }
                parameters.put("ieTipoPessoa", tipoPessoa.getValor());
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where c.ieTipoPessoa = :ieTipoPessoa order by c.nmCliente", parameters);
            case 2:
                parameters.put("nrCPF", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where c.nrCPF like :nrCPF order by c.nmCliente", parameters);
            case 3:
                parameters.put("nrCNPJ", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where c.nrCNPJ like :nrCNPJ order by c.nmCliente", parameters);
            case 4:
                parameters.put("nrTelefone", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where c.nrTelefone like :nrTelefone order by c.nmCliente", parameters);
            case 5:
                parameters.put("nrCelular", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where c.nrCelular like :nrCelular order by c.nmCliente", parameters);
            case 6:
                parameters.put("dsEndereco", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where lower(c.dsEndereco) like :dsEndereco order by c.nmCliente", parameters);
            case 7:
                parameters.put("nmBairro", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where lower(c.nmBairro) like :nmBairro order by c.nmCliente", parameters);
            case 8:
                if ("".equals(filtro)) {
                    return getClientes();
                }
                parameters.put("nrEndereco", Integer.parseInt(filtro));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where c.nrEndereco = :nrEndereco order by c.nmCliente", parameters);
            case 9:
                parameters.put("dsComplemento", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where lower(c.dsComplemento) like :dsComplemento order by c.nmCliente", parameters);
            case 10:
                parameters.put("nrCep", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where c.nrCep like :nrCep order by c.nmCliente", parameters);
            case 11:
                parameters.put("nmCidade", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where lower(c.nmCidade) like :nmCidade order by c.nmCliente", parameters);
            default:
                parameters.put("dsUF", ("%" + filtro + "%"));
                return serviceCliente.getListResultDataQuery("select c from Cliente as c where lower(c.dsUF) like :dsUF order by c.nmCliente", parameters);
        }
    }
}
