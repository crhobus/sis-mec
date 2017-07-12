package br.com.system.persistence;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author crhobus
 */
public class Service<T> {

    private final Class<T> type;
    private final JPAUtils utils;

    public Service(Class<T> type) {
        this.type = type;
        this.utils = JPAUtils.getInstance();
    }

    public void insert(T obj) throws Exception {
        ChangeData changeData = new ChangeData.Builder(OperationType.INSERT).obj(obj).build();
        changeData.change();
    }

    public void update(T obj) throws Exception {
        ChangeData changeData = new ChangeData.Builder(OperationType.UPDATE).obj(obj).build();
        changeData.change();
    }

    public void delete(long pk) throws Exception {
        ChangeData changeData = new ChangeData.Builder(OperationType.DELETE).classType(type).pk(pk).build();
        changeData.change();
    }

    public T getDataPK(long pk) throws Exception {
        EntityManager manager = utils.getEntityManager();
        try {
            return manager.find(type, pk);
        } finally {
            utils.closeEntityManager(manager);
        }
    }

    public Object getSingleResultDataQueryType(String sql, Map<String, Object> parameters) throws Exception {
        EntityManager manager = utils.getEntityManager();
        try {
            TypedQuery<Object> query = manager.createQuery(sql, Object.class);
            parameters.keySet().forEach(key -> {
                query.setParameter(key, parameters.get(key));
            });
            try {
                return query.getSingleResult();
            } catch (NoResultException ex) {
                return null;
            }
        } finally {
            utils.closeEntityManager(manager);
        }
    }

    public T getSingleResultDataQuery(String sql, Map<String, Object> parameters) throws Exception {
        EntityManager manager = utils.getEntityManager();
        try {
            TypedQuery<T> query = manager.createQuery(sql, type);
            parameters.keySet().forEach(key -> {
                query.setParameter(key, parameters.get(key));
            });
            try {
                return query.getSingleResult();
            } catch (NoResultException ex) {
                return null;
            }
        } finally {
            utils.closeEntityManager(manager);
        }
    }

    public List<T> getListResultDataQuery(String sql, Map<String, Object> parameters) throws Exception {
        EntityManager manager = utils.getEntityManager();
        try {
            TypedQuery<T> query = manager.createQuery(sql, type);
            parameters.keySet().forEach(key -> {
                query.setParameter(key, parameters.get(key));
            });
            return query.getResultList();
        } finally {
            utils.closeEntityManager(manager);
        }
    }
}
