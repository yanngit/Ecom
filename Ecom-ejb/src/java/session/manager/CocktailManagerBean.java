package session.manager;

import entity.BeverageEntity;
import entity.CocktailEntity;

import exceptions.EcomException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Lob;
import javax.persistence.PersistenceContext;
import org.apache.jasper.tagplugins.jstl.ForEach;
import pojo.AbstractEntityManager;
import pojo.Deliverable;


@Stateless
public class CocktailManagerBean extends AbstractEntityManager<CocktailEntity> {
    private int MARGE = 10;
    
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
    
    @Override
    public void create(CocktailEntity cocktail){
        boolean available = true;
        String name =  cocktail.getName();
        name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        cocktail.setName(name);
        List<Deliverable> list = cocktail.getDeliverables();
        float price = MARGE;
        for(Deliverable d : list){
            price += d.getPrice();
            if(d.getQuantity() <= 0){
                available = false;
            }
        }
        cocktail.setPrice(price);
        cocktail.setAvailable(available);
        em.persist(cocktail);
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
    public List<CocktailEntity> getAvailableCocktails() {
        return em.createNamedQuery("findAvailableCocktails").getResultList();

    }
    
    /*Get the availability of a specific cocktail. True is returned if the cocktail is available, false otherwise.*/
    public boolean getAvailabilityByCocktailId(Long id) {
        CocktailEntity c = find(id);
        return c.getAvailable();
    }
    
    /*Decrease the quantity of components of the cocktail by a certain number, the number of cocktails added to a 
     * client cart.*/
    public void decreaseQuantityOfCocktail(Long id, int quantity) throws EcomException {
        List<Deliverable> list = find(id).getDeliverables();
        /*Parcourir la liste des deliverable et décrémenter tous les deliverable de la quantite quantity*/
        for(Deliverable d : list){
            if(d instanceof BeverageEntity){
                beverageManager.decreaseQuantityOfBeverage(d.getID(),quantity);
            } else {
                decorationManager.decreaseQuantityOfDecoration(d.getID(),quantity);
            }
            d = beverageManager.find(d.getID());
            /*Vérifications des cocktails pour mettre à jour la disponibilité*/
            if(d.getQuantity() == 0){
                List<CocktailEntity> listCocktails = d.getCocktails();
                for(CocktailEntity c : listCocktails){
                    c.setAvailable(false);
                    edit(c);
                }          
            }
        }
    }
    
    public void increaseQuantityOfCocktail(Long id, int quantity) {
        List<Deliverable> list = find(id).getDeliverables();
        /*Parcourir la liste des deliverable et incrémenter tous les deliverable de la quantite quantity*/
        for(Deliverable d : list){
            if(d instanceof BeverageEntity){
                beverageManager.increaseQuantityOfBeverage(d.getID(),quantity);
            } else {
                decorationManager.increaseQuantityOfDecoration(d.getID(),quantity);
            }
            /*Vérifications des cocktails pour mettre à jour la disponibilité*/
            if(quantity > 0){
                List<CocktailEntity> listCocktails = d.getCocktails();
                for(CocktailEntity c : listCocktails){
                    if(!c.getAvailable()) {
                        List<Deliverable> listDeliverable = c.getDeliverables();
                        boolean available = true;
                        for(Deliverable de : listDeliverable){
                            if(de.getQuantity() <= 0){
                                available = false;
                                break;
                            }
                        }
                        c.setAvailable(available);
                        edit(c);
                    }
                } 
            }
        }
    }
    
    public List<CocktailEntity> getMostPopularCocktails() {
        return em.createNamedQuery("getPopularCocktails")
                .setMaxResults(3)
                .getResultList();
    }
    
    public List<CocktailEntity> getNewestCocktails() {
        return em.createNamedQuery("getNewestCocktails")
                .setMaxResults(5)
                .getResultList();
    }
    
    public List<CocktailEntity> getVirginCocktails() {
        return em.createNamedQuery("getCocktailsByVirginDetail").setParameter("num",true).getResultList();
    }
    
    public List<CocktailEntity> getCocktailsWithAlcohol() {
        return em.createNamedQuery("getCocktailsByVirginDetail").setParameter("num", false).getResultList();
    }
    
    public List<CocktailEntity> getCocktailsByFirstLetter(char letter){
        return em.createNamedQuery("getCocktailsByExp").setParameter("exp", letter+"%").getResultList();
    }
    
    public List<CocktailEntity> getVirginCocktailsByFirstLetter(char letter){
        return em.createNamedQuery("getCocktailsByExpAndVirginDetail").setParameter("num", true).setParameter("exp", letter+"%").getResultList();
    }
    
    public List<CocktailEntity> getCocktailsWithAlcoholByFirstLetter(char letter){
        return em.createNamedQuery("getCocktailsByExpAndVirginDetail").setParameter("num", false).setParameter("exp", letter+"%").getResultList();
    } 
    
    public List<CocktailEntity> getCocktailsByName(String name){
        return em.createNamedQuery("getCocktailsByExp").setParameter("exp", "%"+name+"%").getResultList();
    }

}
