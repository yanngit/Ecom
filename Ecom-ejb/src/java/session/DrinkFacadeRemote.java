package session;

import entity.Drink;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author yann
 */
@Remote
public interface DrinkFacadeRemote {
    public void create(Drink drink);
    public void edit(Drink drink);
    public void remove(Drink drink);
    public Drink find(Object id);
    public List<Drink> findAllRemote();
    public String toString();
}
