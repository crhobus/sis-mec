package br.com.system.persistence.dao.propriedades;

import br.com.system.model.Propriedade;
import br.com.system.persistence.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author crhobus
 */
public class PropriedadesDAO {

    private final Service<Propriedade> servicePropriedade;

    public PropriedadesDAO() {
        this.servicePropriedade = new Service<>(Propriedade.class);
    }

    public void insert(Propriedade propriedade) throws Exception {
        servicePropriedade.insert(propriedade);
    }

    public void update(Propriedade propriedade) throws Exception {
        servicePropriedade.update(propriedade);
    }

    public Propriedade getPropriedade(String nmPropriedade) throws Exception {
        Map parameters = new HashMap();
        parameters.put("nmPropriedade", nmPropriedade);
        return servicePropriedade.getSingleResultDataQuery("select p from Propriedade as p where p.nmPropriedade = :nmPropriedade", parameters);
    }

    public boolean isExisteChaveSecretaCadastrada(String nmPropriedade) throws Exception {
        Map parameters = new HashMap();
        parameters.put("nmPropriedade", nmPropriedade);
        return ((Number) servicePropriedade.getSingleResultDataQueryType("select count(p) from Propriedade as p where p.nmPropriedade = :nmPropriedade", parameters)).intValue() > 0;
    }

    public List<Propriedade> getPropriedades() throws Exception {
        return servicePropriedade.getListResultDataQuery("select p from Propriedade as p", new HashMap<>());
    }
}
