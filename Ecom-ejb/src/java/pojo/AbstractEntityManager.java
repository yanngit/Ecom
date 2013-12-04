package pojo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractEntityManager<T> {

    abstract protected EntityManager getEntityManager();
    private Class<T> entityClass;

    public AbstractEntityManager(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T find(Long id) {
        return (T) getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public T create(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    public T edit(T entity) {
        return getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }
}
