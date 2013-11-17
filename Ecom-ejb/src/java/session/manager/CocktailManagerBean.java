package session.manager;

import entity.BeverageEntity;
import entity.CocktailEntity;
import exceptions.EcomException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;
import pojo.Deliverable;


@Stateless
public class CocktailManagerBean extends AbstractEntityManager<CocktailEntity> {
    @PersistenceContext (name="Ecom_PU")
    private EntityManager em;
    @EJB
    private BeverageManagerBean beverageManager;
    @EJB
    private DecorationManagerBean decorationManager;

    /*Default constructor for the CocktailManagerBean*/
    public CocktailManagerBean() {
        super(CocktailEntity.class);
    }
    
    /*Get the entity manager used by the CocktailManagerBean. Used by the abstract Manager only.*/
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /*Return the list of all unavailable cocktails.*/
    public List<CocktailEntity> getUnavailableCocktails(){
        return em.createNamedQuery("findUnavailableCocktails").getResultList();
    }
    
    /*Return the list of all available cocktails.*/
    public List<CocktailEntity> getAvailableCocktails(){
        return em.createNamedQuery("findAvailableCocktails").getResultList();
    }
    
    /*Get the availability of a specific cocktail. True is returned if the cocktail is available, false otherwise.*/
    public boolean getAvailabilityByCocktailId(Long id){
        List<CocktailEntity> list = getAvailableCocktails();
        for(CocktailEntity c : list){
            if(c.getID().equals(id)){
                return true;
            }
        }
        return false;
    }
    
    /*Decrease the quantity of components of the cocktail by a certain number, the number of cocktails added to a 
     * client cart.*/
    public void decreaseQuantityOfCocktail(Long id, int quantity) throws EcomException {
        List<Deliverable> list = find(id).getDeliverables();
        /*Parcourir la liste des deliverable et décrémenter tous les deliverable de la quantite quantity*/
        for(Deliverable d : list){
            if(d instanceof BeverageEntity){
                beverageManager.decreaseQuantityOfBeverage(id,quantity);
            } else {
                decorationManager.decreaseQuantityOfDecoration(id,quantity);
            }
        }
    }
}
