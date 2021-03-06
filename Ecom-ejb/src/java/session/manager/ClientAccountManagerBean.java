package session.manager;

import entity.ClientAccountEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

/**
 *
 * @author Alexis BRENON <brenon.alexis@gmail.com>
 */
@Stateless
public class ClientAccountManagerBean extends AbstractEntityManager<ClientAccountEntity> {

    @PersistenceContext(name = "Ecom_PU")
    private EntityManager em;

    public ClientAccountManagerBean() {
        super(ClientAccountEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ClientAccountEntity getAccountByAuthentification(String login, String password) {
        List<ClientAccountEntity> res = em.createNamedQuery("getAccountByAuthentification").setParameter("login",login).setParameter("password",password).getResultList();
        if(res.size() > 0){
            return res.get(0);
        }
        return null;
    }
}
