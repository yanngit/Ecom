package session.manager;

import entity.DecorationEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

/**
 *
 * @author Alexis BRENON <brenon.alexis@gmail.com>
 */
@Stateless (name="decorationManager", mappedName="session/manager/decorationManager")
public class DecorationManagerBean extends AbstractEntityManager<DecorationEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;

    public DecorationManagerBean() {
        super(DecorationEntity.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
