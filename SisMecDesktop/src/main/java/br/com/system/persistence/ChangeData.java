package br.com.system.persistence;

import javax.persistence.EntityManager;

/**
 *
 * @author crhobus
 */
public class ChangeData<T> {

    private final JPAUtils utils;
    private OperationType operationType;
    private Class<T> type;
    private T obj;
    private long pk;

    public static class Builder<T> {

        //required
        private final OperationType operationType;
        //optional
        private Class<T> type;
        private T obj;
        private long pk;

        public Builder(OperationType operationType) {
            this.operationType = operationType;
        }

        public Builder classType(Class<T> type) {
            this.type = type;
            return this;
        }

        public Builder obj(T obj) {
            this.obj = obj;
            return this;
        }

        public Builder pk(long pk) {
            this.pk = pk;
            return this;
        }

        public ChangeData build() {
            return new ChangeData(this);
        }
    }

    private ChangeData(Builder builder) {
        this.utils = JPAUtils.getInstance();
        this.operationType = builder.operationType;
        this.type = builder.type;
        this.obj = (T) builder.obj;
        this.pk = builder.pk;
    }

    public void change() throws Exception {
        EntityManager manager = utils.getEntityManager();
        try {
            manager.getTransaction().begin();
            switch (operationType) {
                case INSERT:
                    manager.persist(obj);
                    break;
                case UPDATE:
                    manager.merge(obj);
                    break;
                case DELETE:
                    obj = manager.find(type, pk);
                    manager.remove(obj);
                    break;
            }
            manager.getTransaction().commit();
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw ex;
        } finally {
            utils.closeEntityManager(manager);
        }
    }
}
