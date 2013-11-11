package pojo;

/**
 *
 * @author alexis
 * @TODO Internationalize enum
 */
public enum CocktailFlavorEnum {
    FRUITY("Fruité"),
    BITTER("Amer");

    private String flavorName;
    
    private CocktailFlavorEnum(String flavorName) {
        this.flavorName = flavorName;
    }

    @Override
    public String toString() {
        return flavorName;
    }    
}
