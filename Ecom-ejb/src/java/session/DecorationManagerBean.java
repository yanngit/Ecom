package session;

import entity.DecorationEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.EntityManagerItf;

/**
 *
 * @author Alexis BRENON <brenon.alexis@gmail.com>
 */
@Stateless
@LocalBean
public class DecorationManagerBean implements EntityManagerItf<DecorationEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;
    
    @Override
    public DecorationEntity find(Object id) {
        return em.find(DecorationEntity.class, id);
    }

    @Override
    public List<DecorationEntity> findAll() {
        return em.createQuery("SELECT d FROM DECORATION d").getResultList();
    }

    @Override
    public void create(DecorationEntity entity) {
        em.persist(entity);
    }

    @Override
    public void edit(DecorationEntity entity) {
        em.merge(entity);
    }

    @Override
    public void remove(DecorationEntity entity) {
        em.remove(entity);
    }

}
