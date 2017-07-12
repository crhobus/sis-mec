package br.com.system.persistence.dao.agendaContato;

import br.com.system.controller.action.radioButton.ControlRadioButtonType;
import br.com.system.controller.action.radioButton.RadioButtonType;
import br.com.system.model.AgendaContato;
import br.com.system.persistence.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author crhobus
 */
public class AgendaContatoDAO {

    private final Service<AgendaContato> serviceAgendaContato;

    public AgendaContatoDAO() {
        this.serviceAgendaContato = new Service<>(AgendaContato.class);
    }

    public void insert(AgendaContato agendaContato) throws Exception {
        serviceAgendaContato.insert(agendaContato);
    }

    public void update(AgendaContato agendaContato) throws Exception {
        serviceAgendaContato.update(agendaContato);
    }

    public void delete(long cdContato) throws Exception {
        serviceAgendaContato.delete(cdContato);
    }

    public List<AgendaContato> getAgendaContatos() throws Exception {
        return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a order by a.nmContato", new HashMap<>());
    }

    public List<AgendaContato> getAgendaContatos(int indiceColuna, String filtro, ControlRadioButtonType controlTipoContato) throws Exception {
        filtro = filtro.toLowerCase();
        Map parameters = new HashMap();
        switch (indiceColuna) {
            case 0:
                parameters.put("nmContato", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where lower(a.nmContato) like :nmContato order by a.nmContato", parameters);
            case 1:
                parameters.put("nrTelefone1", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where a.nrTelefone1 like :nrTelefone1 order by a.nmContato", parameters);
            case 2:
                parameters.put("nrTelefone2", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where a.nrTelefone2 like :nrTelefone2 order by a.nmContato", parameters);
            case 3:
                parameters.put("nrCelular1", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where a.nrCelular1 like :nrCelular1 order by a.nmContato", parameters);
            case 4:
                parameters.put("nrCelular2", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where a.nrCelular2 like :nrCelular2 order by a.nmContato", parameters);
            case 5:
                parameters.put("dsEmail", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where lower(a.dsEmail) like :dsEmail order by a.nmContato", parameters);
            case 6:
                if ("".equals(filtro)) {
                    return getAgendaContatos();
                }
                RadioButtonType tipoContato = controlTipoContato.getTypeD(filtro);
                if (tipoContato == null) {
                    return new ArrayList<>();
                }
                parameters.put("ieTipo", tipoContato.getValor());
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where a.ieTipo = :ieTipo order by a.nmContato", parameters);
            case 7:
                parameters.put("dsEndereco", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where lower(a.dsEndereco) like :dsEndereco order by a.nmContato", parameters);
            case 8:
                parameters.put("nmBairro", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where lower(a.nmBairro) like :nmBairro order by a.nmContato", parameters);
            case 9:
                if ("".equals(filtro)) {
                    return getAgendaContatos();
                }
                parameters.put("nrEndereco", Integer.parseInt(filtro));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where a.nrEndereco = :nrEndereco order by a.nmContato", parameters);
            case 10:
                parameters.put("nrCep", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where a.nrCep like :nrCep order by a.nmContato", parameters);
            case 11:
                parameters.put("nmCidade", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where lower(a.nmCidade) like :nmCidade order by a.nmContato", parameters);
            default:
                parameters.put("dsUF", ("%" + filtro + "%"));
                return serviceAgendaContato.getListResultDataQuery("select a from AgendaContato as a where lower(a.dsUF) like :dsUF order by a.nmContato", parameters);
        }
    }
}
