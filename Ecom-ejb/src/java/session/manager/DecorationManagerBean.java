package session.manager;

import entity.CocktailEntity;
import entity.DecorationEntity;
import exceptions.EcomException;
import java.util.List;
import java.util.ListIterator;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pojo.AbstractEntityManager;

@Stateless
public class DecorationManagerBean extends AbstractEntityManager<DecorationEntity> {

    @EJB
    private CocktailManagerBean cocktailManagerBean;
    @PersistenceContext(name = "Ecom_PU")
    private EntityManager em;

    public DecorationManagerBean() {
        super(DecorationEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public void decreaseQuantityOfDecoration(Long id, int quantity) throws EcomException {
        DecorationEntity decoration = find(id);
        int newQuantity = decoration.getQuantity() - quantity;
        if (newQuantity < 0) {
            throw new EcomException("The quantity of the decoration [" + id + "] can't be negative");
        }
        decoration.setQuantity(newQuantity);
        this.edit(decoration);
    }

    public void increaseQuantityOfDecoration(Long id, int quantity) {
        DecorationEntity decoration = find(id);
        int newQuantity = decoration.getQuantity() + quantity;
        decoration.setQuantity(newQuantity);
        this.edit(decoration);
    }

    @Override
    public void remove(DecorationEntity deco) {
        deco = this.find(deco.getID());
        /* Remove all cocktails that need this decoration */
        List<CocktailEntity> cocktails = deco.getCocktails();
        for (int i = 0; i < cocktails.size(); i++) {
            cocktailManagerBean.remove(cocktails.get(i));
        }
        em.remove(deco);
    }
}
