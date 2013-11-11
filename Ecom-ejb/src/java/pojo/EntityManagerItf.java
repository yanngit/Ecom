package pojo;

import java.util.List;

/**
 *
 * @author alexis
 */
public interface EntityManagerItf<T> {
    public T find(Object id);
    public List<T> findAll();
    public void create(T entity);
    public void edit(T entity);
    public void remove(T entity);
}
