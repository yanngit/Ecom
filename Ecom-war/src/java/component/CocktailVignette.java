package component;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "CocktailVignette")
public class CocktailVignette extends UIComponentBase {

    private String idPrefix;
    private String name;
    private String price;
    private String urlPhoto;

    public String getIdPrefix() {
        return idPrefix;
    }

    public void setIdPrefix(String idPrefix) {
        this.idPrefix = idPrefix;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    @Override
    public String getFamily() {
        return "CocktailVignette";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.write("<div id=\""+idPrefix+name+"\" class=\"cocktail-vignette\">");
        writer.write("<h2>"+name+"</h2>");
        writer.write("<h3>"+price+"</h3>");
        writer.write("</div>");
    }
}