package session.manager;

import entity.CocktailEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;
import pojo.Deliverable;

@Stateless
public class ProposalManagerBean extends AbstractEntityManager<CocktailEntity> {

    private int MARGE = 10;
    @PersistenceContext(name = "Ecom_PU")
    private EntityManager em;
    @EJB
    private BeverageManagerBean beverageManager;
    @EJB
    private DecorationManagerBean decorationManager;

    /*Default constructor for the CocktailManagerBean*/
    public ProposalManagerBean() {
        super(CocktailEntity.class);
    }

    @Override
    public CocktailEntity create(CocktailEntity cocktail) {
        boolean available = true;
        String name = cocktail.getName();
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        cocktail.setName(name);
        List<Deliverable> list = cocktail.getDeliverables();
        float price = MARGE;
        for (Deliverable d : list) {
            price += d.getPrice();
            if (d.getQuantity() <= 0) {
                available = false;
            }
        }
        cocktail.setPrice(price);
        cocktail.setAvailable(available);
        cocktail.setStatePublication(false);
        em.merge(cocktail);
        return cocktail;
    }

    /*Get the entity manager used by the CocktailManagerBean. Used by the abstract Manager only.*/
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<CocktailEntity> findProposal() {
        return em.createNamedQuery("findProposal").getResultList();
    }
}
