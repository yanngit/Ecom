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
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pojo.CocktailFlavorEnum;
import pojo.CocktailPowerEnum;
import pojo.Deliverable;
import pojo.OrderStateEnum;
import session.interfaces.ClientFacadeRemoteItf;
import java.util.*; 
import javax.mail.*;
import javax.mail.internet.*;
import javax.naming.*;
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
    private AddressEntity address = null;
    private OrderEntity order = null;
    private ClientAccountEntity account = null;
    private boolean displayOrders = false;
    private boolean displayAddresses = false;
    private MessageDigest md = null;
    private String qty = "1";
    private String currentCocktailAlcoholLetter = null;
    private String currentCocktailSoftLetter = null;
    private String dayOfBirth = "JJ";
    private String monthOfBirth = "MM";
    private String yearOfBirth = "AAAA";
    /*Passer a false pour la prod*/
    private boolean userIsMajor = true;
    /*Liste des alcools et stockage d'une map pour les checkboxes de la recherche*/
    private List<BeverageEntity> listAlcohols = new ArrayList<>();
    private Map<BeverageEntity, Boolean> selectedAlcoolsMap = new HashMap<>();
    /*Liste des boissons non alcoolisées et stockage d'une map pour les checkboxes de la recherche*/
    private List<BeverageEntity> listVirgins = new ArrayList<>();
    private Map<BeverageEntity, Boolean> selectedVirginsMap = new HashMap<>();
    /*Liste des gouts et stockage d'une map pour les checkboxes de la recherche*/
    private List<CocktailFlavorEnum> listFlavors = new ArrayList<>();
    private Map<CocktailFlavorEnum, Boolean> selectedFlavorsMap = new HashMap<>();
    /*Liste des puissance et stockage d'une map pour les checkboxes de la recherche*/
    private List<CocktailPowerEnum> listPowers = new ArrayList<>();
    private Map<CocktailPowerEnum, Boolean> selectedPowersMap = new HashMap<>();
    /*Résultat de la recherche*/
    private List<CocktailEntity> resultSearch = new ArrayList<>();

    public boolean isUserIsMajor() {
        return userIsMajor;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public void checkMajorUser() throws IOException {
        if (!userIsMajor) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("ageChecking.xhtml?faces-redirect=true");
        }
    }

    public String checkAge() {
        String result;
        GregorianCalendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setLenient(false);
        try {
            if (new Integer(yearOfBirth) < GregorianCalendar.getInstance().get(GregorianCalendar.YEAR) - 150) {
                yearOfBirth = "AAAA";
                throw new NumberFormatException();
            }
            dateOfBirth.set(new Integer(yearOfBirth),
                    new Integer(monthOfBirth) - 1,
                    new Integer(dayOfBirth));
            dateOfBirth.getTime();
        } catch (NumberFormatException e) {
            return "ageChecking.xhtml?faces-redirect=true";
        } catch (IllegalArgumentException e) {
            if (e.getMessage().equalsIgnoreCase("DAY_OF_MONTH")) {
                dayOfBirth = "JJ";
            } else if (e.getMessage().contains("MONTH")) {
                monthOfBirth = "MM";
            }
            return "ageChecking.xhtml?faces-redirect=true";
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        Long dayElapsed = currentDate.getTimeInMillis() / (1000 * 3600 * 24)
                - dateOfBirth.getTimeInMillis() / (1000 * 3600 * 24);
        int yearElapsed = (int) (dayElapsed / 365.25);

        if (yearElapsed < 18) {
            result = "tooYoung.xhtml";
        } else {
            userIsMajor = true;
            result = "index.xhtml";
        }
        return result;
    }

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
        boolean isInit = false;
        /*Netoyage des résultats précédents*/
        resultSearch.clear();
        /*Liste des boissons selectionnées*/
        List<BeverageEntity> selectedBeverages = new ArrayList<>();
        List<CocktailFlavorEnum> selectedFlavor = new ArrayList<>();
        List<CocktailPowerEnum> selectedPower = new ArrayList<>();
        /*Récupération des alcools selectionnes*/
        for (Map.Entry<BeverageEntity, Boolean> e : selectedAlcoolsMap.entrySet()) {
            if (e.getValue()) {
                selectedBeverages.add(e.getKey());
            }
        }
        /*Récupération des diluants selectionnés*/
        for (Map.Entry<BeverageEntity, Boolean> e : selectedVirginsMap.entrySet()) {
            if (e.getValue()) {
                selectedBeverages.add(e.getKey());
            }
        }

        for (Map.Entry<CocktailFlavorEnum, Boolean> e : selectedFlavorsMap.entrySet()) {
            if (e.getValue()) {
                selectedFlavor.add(e.getKey());
            }
        }

        for (Map.Entry<CocktailPowerEnum, Boolean> e : selectedPowersMap.entrySet()) {
            if (e.getValue()) {
                selectedPower.add(e.getKey());
            }
        }

        /*Intersection des résltats*/
        if (!selectedBeverages.isEmpty()) {
            for (BeverageEntity b : selectedBeverages) {
                if (resultSearch.isEmpty() && !isInit) {
                    resultSearch = client.getCocktailsForBeverage(b);
                    isInit = true;
                    
                } else {
                    resultSearch.retainAll(client.getCocktailsForBeverage(b));
                }
            }
        }
        
        if (!selectedFlavor.isEmpty() && !isInit) {
            for (CocktailFlavorEnum f : selectedFlavor) {
                if (resultSearch.isEmpty()) {
                    resultSearch = client.getCocktailsByFlavor(f);
                    isInit = true;
                } else {
                    resultSearch.retainAll(client.getCocktailsByFlavor(f));
                }
            }
        }

        if (!selectedPower.isEmpty() && !isInit) {
            for (CocktailPowerEnum p : selectedPower) {
                if (resultSearch.isEmpty()) {
                    resultSearch = client.getCocktailsByPower(p);
                    isInit = true;
                } else {
                    resultSearch.retainAll(client.getCocktailsByPower(p));
                }
            }
        }
    }
    
    public void resetResearch(){
        selectedAlcoolsMap.clear();
        selectedFlavorsMap.clear();
        selectedPowersMap.clear();
        selectedVirginsMap.clear();
    }
    
    public void afficherListeCocktails(List<CocktailEntity> list){
        System.out.println("Affichage Liste : ");
        for(CocktailEntity c : list){
            System.out.println(c.getName());
        }
    }

    public void setselectedVirginsMap(Map<BeverageEntity, Boolean> map) {
        selectedVirginsMap = map;
    }

    public void setselectedAlcoolsMap(Map<BeverageEntity, Boolean> map) {
        selectedAlcoolsMap = map;
    }

    public void setselectedFlavorsMap(Map<CocktailFlavorEnum, Boolean> map) {
        selectedFlavorsMap = map;
    }

    public void setselectedPowersMap(Map<CocktailPowerEnum, Boolean> map) {
        selectedPowersMap = map;
    }

    public Map<BeverageEntity, Boolean> getselectedVirginsMap() {
        return selectedVirginsMap;
    }

    public Map<BeverageEntity, Boolean> getselectedAlcoolsMap() {
        return selectedAlcoolsMap;
    }

    public Map<CocktailFlavorEnum, Boolean> getselectedFlavorsMap() {
        return selectedFlavorsMap;
    }

    public Map<CocktailPowerEnum, Boolean> getselectedPowersMap() {
        return selectedPowersMap;
    }

    public List<CocktailFlavorEnum> getListFlavors() {
        if (listFlavors.isEmpty()) {
            listFlavors.add(CocktailFlavorEnum.BITTER);
            listFlavors.add(CocktailFlavorEnum.FRUITY);
        }
        return listFlavors;
    }

    public List<CocktailPowerEnum> getListPowers() {
        if (listPowers.isEmpty()) {
            listPowers.add(CocktailPowerEnum.SOFT);
            listPowers.add(CocktailPowerEnum.MEDIUM);
            listPowers.add(CocktailPowerEnum.STRONG);
        }
        return listPowers;
    }

    public List<BeverageEntity> getListVirgins() {
        if (listVirgins.isEmpty()) {
            listVirgins = client.getAllBeveragesWithoutAlcohol();
        }
        return listVirgins;
    }

    public List<BeverageEntity> getListAlcohols() {
        if (listAlcohols.isEmpty()) {
            listAlcohols = client.getAllBeveragesWithAlcohol();
        }
        return listAlcohols;
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
        address = new AddressEntity();
        address.setFirst_name(firstName);
        address.setSurname(lastName);
        address.setStreet(street);
        address.setPostal_code(postalCode);
        address.setCity(city);
        address.setCountry("France");
        address.setOrders(null);
        client.addAddress(address);
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
        account.setDelivery_address(address);
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
        if (canBeInteger(qty)) {
            client.addArticleToCart(cocktail.getID(), Integer.parseInt(qty));
            qty = "1";
        }
    }

    public void removeArticle(CocktailEntity cocktail) throws EcomException {
        System.out.println("Dans le DMB ..........................................................................");
        if (qty.equals("")) {
            qty = "1";
        }
        client.removeArticle(cocktail, Integer.parseInt(qty));
        qty = "1";
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
        address = new AddressEntity();
        address.setFirst_name(firstName);
        address.setSurname(lastName);
        address.setStreet(street);
        address.setPostal_code(postalCode);
        address.setCity(city);
        address.setCountry("France");
        address.setOrders(null);
        // Persistance de l'addresse saiasie et
        //Récuperation de l'addresse persistée
        Long id = client.checkAddress(address);
        AddressEntity tempA;
        if (id == null) {
            tempA = client.addAddress(address);
        } else {
            tempA = client.getAddress(id);
        }
        listAddress.add(tempA);
        order.setCocktails(client.getCartContent());
        order.setStatus(OrderStateEnum.PAYED);
        order.setAddresses(listAddress);

        //Persistance de la commande
        OrderEntity tempO = client.addOrder(order);
        listOrder.add(tempO);//client.getOrder(tempO.getId()));

        client.getAddress(tempA.getId()).setOrders(listOrder);
        order = tempO;
        address = tempA;
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

    private boolean canBeInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getOrderId() {
        return order.getId();
    }
    
    public void contactUs( String clientMail ,String msgSubject, String msgTxt ) throws MessagingException {
        try {
            msgTxt = msgTxt + "\n sent by" + clientMail;
            InitialContext ic = new InitialContext();
            String snName = "mail/EmailMe";
            Session session = (Session)ic.lookup(snName);
            Message msg = new MimeMessage(session);
            msg.setSubject(msgSubject);
            msg.setSentDate(new Date());
            msg.setFrom();
            String Admin = "bachar.nejem@gmail.com";
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Admin, false));
            MimeBodyPart mbp = new MimeBodyPart();
            mbp.setText(msgTxt);
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbp);
            msg.setContent(mp);
            Transport.send(msg);
            
        } catch (NamingException ex) {
            Logger.getLogger(DataManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    

    }
}
