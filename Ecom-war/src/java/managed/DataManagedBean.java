/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import entity.AddressEntity;
import entity.BeverageEntity;
import entity.ClientAccountEntity;
import entity.CocktailEntity;
import entity.DecorationEntity;
import entity.OrderEntity;
import exceptions.EcomException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import pojo.CocktailFlavorEnum;
import pojo.Deliverable;
import pojo.OrderStateEnum;
import session.interfaces.ClientFacadeRemoteItf;

/**
 *
 * @author yann
 */
@ManagedBean(name = "dataManagedBean")
@SessionScoped
public class DataManagedBean implements Serializable {

    /* Main facade to interact with Change to the application */
    @EJB
    private ClientFacadeRemoteItf client;
    /* Save the cocktail we want to see details */
    private CocktailEntity currentCocktail = null;
    private AddressEntity entireAddress = null;
    private OrderEntity order = null;
    private ClientAccountEntity account = null;
    private boolean displayOrders = false;
    private boolean displayAddresses = false;
    private MessageDigest md = null;
    private String qty = "1";
    private String currentCocktailAlcoholLetter = null;
    private String currentCocktailSoftLetter = null;
    /*Liste des alcools et stockage d'une map pour les checkboxes de la recherche*/
    private List<BeverageEntity> listAlcohol = new ArrayList<BeverageEntity>();
    private Map<BeverageEntity, Boolean> selectedAlcoolId = new HashMap<BeverageEntity, Boolean>();
    /*Liste des gouts et stockage d'une map pour les checkboxes de la recherche*/
    private List<pojo.CocktailFlavorEnum> listFlavor = new ArrayList<CocktailFlavorEnum>();
    private Map<String, Boolean> selectedFlavorsString = new HashMap<String, Boolean>();
    /*Résultat de la recherche*/
    private List<CocktailEntity> resultSearch = new ArrayList<CocktailEntity>();
    
    public List<CocktailEntity> getResultSearch() {
        return resultSearch;
    }
    
    public void setResultSearch(List<CocktailEntity> resultSearch) {
        this.resultSearch = resultSearch;
    }
    
    public boolean isSearchAvailable() {
        return resultSearch.size() > 0;
    }
    
    public void searchCocktails() {
        resultSearch.clear();
        List<BeverageEntity> selected = new ArrayList<BeverageEntity>();
        for (Map.Entry<BeverageEntity, Boolean> e : selectedAlcoolId.entrySet()) {
            if (e.getValue()) {
                selected.add(e.getKey());
            }
        }
        if (!selected.isEmpty()) {
            resultSearch = client.getCocktailsForBeverage(selected.get(0));
        }
    }
    
    public void setselectedAlcoolId(Map<BeverageEntity, Boolean> map) {
        selectedAlcoolId = map;
    }
    
    public Map<BeverageEntity, Boolean> getselectedAlcoolId() {
        return selectedAlcoolId;
    }
    
    public Map<String, Boolean> getselectedFlavorsString() {
        return selectedFlavorsString;
    }
    
    public List<pojo.CocktailFlavorEnum> getListFlavors() {
        if (listFlavor.isEmpty()) {
            listFlavor.add(pojo.CocktailFlavorEnum.BITTER);
            listFlavor.add(pojo.CocktailFlavorEnum.FRUITY);
        }
        return listFlavor;
    }
    
    public List<BeverageEntity> getListAlcohol() {
        if (listAlcohol.isEmpty()) {
            listAlcohol = client.getAllBeveragesWithAlcohol();
        }
        return listAlcohol;
    }
    
    public String getCurrentCocktailAlcoholLetter() {
        return currentCocktailAlcoholLetter;
    }
    
    public void setCurrentCocktailAlcoholLetter(String currentCocktailAlcoholLetter) {
        this.currentCocktailAlcoholLetter = currentCocktailAlcoholLetter;
    }
    
    public String displayCocktailAlcoholFirstLetter(String c) {
        this.currentCocktailAlcoholLetter = c;
        return "cocktailsAlcoholFirstLetter.xhtml?faces-redirect=true";
    }
    
    public List<CocktailEntity> getListCocktailsWithAlcoholByFirstLetter(String l) {
        return client.getCocktailsWithAlcoholByFirstLetter(l.charAt(0));
    }
    
    public String getCurrentCocktailSoftLetter() {
        return currentCocktailSoftLetter;
    }
    
    public void setCurrentCocktailSoftLetter(String currentCocktailAlcoholLetter) {
        this.currentCocktailSoftLetter = currentCocktailAlcoholLetter;
    }
    
    public String displayCocktailVirginFirstLetter(String c) {
        this.currentCocktailSoftLetter = c;
        return "cocktailsVirginFirstLetter.xhtml?faces-redirect=true";
    }
    
    public List<CocktailEntity> getListCocktailsWithoutAlcoholByFirstLetter(String l) {
        return client.getCocktailsWithoutAlcoholByFirstLetter(l.charAt(0));
    }
    
    public String getQty() {
        return qty;
    }
    
    public void setQty(String quantity) {
        this.qty = quantity;
    }
    
    public DataManagedBean() {
        super();
        try {
            md = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(DataManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getLogin() {
        return account.getLogin();
    }
    
    public void createAccount(String login, String password, String firstName, String lastName, String street, String postalCode, String city) {
        /*Création de l'adresse*/
        entireAddress = new AddressEntity();
        entireAddress.setFirst_name(firstName);
        entireAddress.setSurname(lastName);
        entireAddress.setStreet(street);
        entireAddress.setPostal_code(postalCode);
        entireAddress.setCity(city);
        entireAddress.setCountry("France");
        entireAddress.setOrders(null);
        client.addAddress(entireAddress);
        /*Création du compte et association du compte à l'adresse*/
        md.reset();
        account = new ClientAccountEntity();
        account.setLogin(login);
        byte[] encoded = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < encoded.length; i++) {
            sb.append(Integer.toString((encoded[i] & 0xff) + 0x100, 16).substring(1));
        }
        account.setPassword(sb.toString());
        account.setDelivery_address(entireAddress);
        client.addClient(account);
    }
    
    public String connect(String login, String password) {
        if (account == null) {
            md.reset();
            byte[] encoded = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < encoded.length; i++) {
                sb.append(Integer.toString((encoded[i] & 0xff) + 0x100, 16).substring(1));
            }
            account = client.connect(login, sb.toString());
            if (account == null) {
                return "Connexion.xhtml?faces-redirect=true";
            }
        }
        return "Account.xhtml?faces-redirect=true";
    }
    
    public String disconnect() {
        if (account != null) {
            account = null;
        }
        return "index.xhtml?faces-redirect=true";
    }
    
    public boolean isConnected() {
        return account != null;
    }
    
    public AddressEntity getAccountAddress() {
        return account.getDelivery_address();
    }

    /*récupérer le nb de cocktail de type cocktail dans le caddie*/
    public String getQuantityForCocktailInCart(CocktailEntity cocktail) {
        return client.getQuantityForCocktail(cocktail);
    }

    /* Navigate to the cocktailDetails.xhtml page and record the cocktail we
     * want to watch.
     */
    public String displayCocktailDetails(CocktailEntity cocktail) {
        this.currentCocktail = getCocktailFull(cocktail);
        return "cocktailDetails.xhtml?faces-redirect=true";
    }

    /* All getters */
    public CocktailEntity getCurrentCocktail() {
        return currentCocktail;
    }
    
    public CocktailEntity getCocktail(Long id) throws Exception {
        return client.getCocktail(id);
    }
    
    public CocktailEntity getCocktailFull(CocktailEntity cocktail) {
        if (cocktail != null) {
            return client.getCocktailFull(cocktail);
        } else {
            return null;
        }
        
    }
    
    public List<DecorationEntity> getCocktailDecorations(Long id) {
        return client.getCocktailDecorations(id);
    }
    
    public List<BeverageEntity> getCocktailBeverages(CocktailEntity cocktail) {
        return client.getCocktailBeverages(cocktail);
    }
    
    public List<Deliverable> getCocktailDeliverables(CocktailEntity cocktail) {
        return cocktail.getDeliverables();
    }
    
    public List<CocktailEntity> getListCocktails() {
        return client.getAllCocktails();
    }
    
    public List<CocktailEntity> getListAvailableCocktails() {
        return client.getAvailableCocktails();
    }
    
    public List<CocktailEntity> getListUnavailableCocktails() {
        return client.getUnavailableCocktails();
    }
    
    public List<BeverageEntity> getListAvailableBeverages() {
        return client.getAvailableBeverages();
    }
    
    public List<BeverageEntity> getListUnavailableBeverages() {
        return client.getUnavailableBeverages();
    }
    
    public List<CocktailEntity> getCartContent() {
        return client.getCartContent();
    }
    
    public Integer getCartLength() {
        return client.getCartSize();
    }
    
    public Float getCartPrice() {
        return client.getCartPrice();
    }
    
    public List<CocktailEntity> getListMostPopularCocktails() {
        return client.getMostPopularCocktails();
    }
    
    public List<CocktailEntity> getListNewestCocktails() {
        return client.getNewestCocktails();
    }
    
    public List<CocktailEntity> getListCocktailsWithAlcohol() {
        return client.getCocktailsWithAlcohol();
    }
    
    public List<CocktailEntity> getListCocktailsWithoutAlcohol() {
        return client.getCocktailsWithoutAlcohol();
    }

    /* Setters, symbolizing an action */
    public void addArticleToCart(CocktailEntity cocktail) throws EcomException {
        if (qty.equals("")) {
            qty = "1";
        }
        client.addArticleToCart(cocktail.getID(), Integer.parseInt(qty));
        qty = "1";
    }
    
    public void removeArticle(CocktailEntity cocktail) throws EcomException {
        System.out.println("Dans le DMB ..........................................................................");
        client.removeArticle(cocktail);
    }
    
    public List<CocktailEntity> listCocktailsByFirstLetter(char letter) {
        return client.getCocktailsByFirstLetter(letter);
    }
    
    public List<CocktailEntity> listCocktailsByName(String name) {
        return client.getCocktailsByName(name);
    }

    /* Nice function, but doesn't respect convention name, or application
     * architecture... */
    public List<List<CocktailEntity>> listAllCocktailsByFirstLetter() {
        List<List<CocktailEntity>> list = new ArrayList<>();
        for (char ch : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            if (client.getCocktailsByFirstLetter(ch).size() > 0) {
                list.add(client.getCocktailsByFirstLetter(ch));
            }
        }
        return list;
    }
    
    public List<List<CocktailEntity>> listAllVirginCocktailsByFirstLetter() {
        List<List<CocktailEntity>> list = new ArrayList<>();
        for (char ch : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            if (client.getCocktailsWithoutAlcoholByFirstLetter(ch).size() > 0) {
                list.add(client.getCocktailsWithoutAlcoholByFirstLetter(ch));
            }
        }
        return list;
    }
    
    public List<List<CocktailEntity>> listAllCocktailsWithAlcoholByFirstLetter() {
        List<List<CocktailEntity>> list = new ArrayList<>();
        for (char ch : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            if (client.getCocktailsWithAlcoholByFirstLetter(ch).size() > 0) {
                list.add(client.getCocktailsWithAlcoholByFirstLetter(ch));
            }
        }
        return list;
    }

    //ajouter par bach
    public void creatOrder(String firstName, String lastName, String street, String postalCode, String city) {
        //System.out.println(city);
        order = new OrderEntity();
        List<OrderEntity> listOrder = new ArrayList<>();
        List<AddressEntity> listAddress = new ArrayList<>();
        entireAddress = new AddressEntity();
        entireAddress.setFirst_name(firstName);
        entireAddress.setSurname(lastName);
        entireAddress.setStreet(street);
        entireAddress.setPostal_code(postalCode);
        entireAddress.setCity(city);
        entireAddress.setCountry("France");
        entireAddress.setOrders(null);
        // Persistance de l'addresse saiasie et
        //Récuperation de l'addresse persistée
        AddressEntity tempA = client.addAddress(entireAddress);
        listAddress.add(tempA);//client.getAddress(tempA.getId()));

        order.setCocktails(client.getCartContent());
        order.setStatus(OrderStateEnum.PAYED);
        order.setAddresses(listAddress);

        //Persistance de la commande
        OrderEntity tempO = client.addOrder(order);
        listOrder.add(tempO);//client.getOrder(tempO.getId()));

        client.getAddress(tempA.getId()).setOrders(listOrder);
        client.clearCart();
        //return client.getAddress(tempA.getId());
    }
    
    public void setDisplayOrders(boolean b) {
        displayOrders = b;
        displayAddresses = false;
    }
    
    public boolean getDisplayOrders() {
        return displayOrders;
    }
    
    public void setDisplayAddresses(boolean b) {
        displayAddresses = b;
        displayOrders = false;
    }
    
    public boolean getDisplayAddresses() {
        return displayAddresses;
    }
    
    public List<OrderEntity> getOrdersOfAccount() {
        if (account != null) {
            return client.getOrdersOfAccount(account);
        }
        return null;
    }
    
    public void modifyAddress(String firstName, String lastName, String street, String postalCode, String city) {
        if (account != null) {
            AddressEntity address = account.getDelivery_address();
            address.setFirst_name(firstName);
            address.setSurname(lastName);
            address.setStreet(street);
            address.setPostal_code(postalCode);
            address.setCity(city);
            /*MANQUE COUNTRY*/
            client.modifyAddress(address);
        }
    }
}
