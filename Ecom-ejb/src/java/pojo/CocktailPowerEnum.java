package pojo;

/**
 *
 * @author alexis
 */
public enum CocktailPowerEnum {
    SOFT("Doux"),
    MEDIUM("Moyen"),
    STRONG("Corsé");
    
    private String powerName;

    private CocktailPowerEnum(String powerName) {
        this.powerName = powerName;
    }

    @Override
    public String toString() {
        return powerName;
    }
    
    
}
