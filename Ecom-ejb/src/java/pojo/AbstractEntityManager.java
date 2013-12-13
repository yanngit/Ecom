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
        System.out.println("Persisting : " + entity.toString());
        getEntityManager().persist(entity);
        System.out.println(" Persisted : " + entity.toString());
        return entity;
    }

    public T edit(T entity) {
        System.out.println("Merging : " + entity.toString());
        entity = getEntityManager().merge(entity);
        System.out.println(" Merged : " + entity.toString());
        return entity;
    }

    public void remove(T entity) {
        System.out.println("Removing : " + entity.toString());
        getEntityManager().remove(getEntityManager().merge(entity));
        System.out.println(" Removed : " + entity.toString());
    }

    public void flush() {
        getEntityManager().flush();
    }
}
