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
        return (String) getStateHelper().eval("idPrefix");
    }

    public void setIdPrefix(String idPrefix) {
        getStateHelper().put("idPrefix", idPrefix);
    }

    public String getName() {
        return (String) getStateHelper().eval("name");
    }

    public void setName(String name) {
        getStateHelper().put("name", name);
    }

    public String getPrice() {
        return (String) getStateHelper().eval("price");
    }

    public void setPrice(String price) {
        getStateHelper().put("price", price);
    }

    public String getUrlPhoto() {
        return (String) getStateHelper().eval("urlPhoto");
    }

    public void setUrlPhoto(String urlPhoto) {
        getStateHelper().put("urlPhoto", urlPhoto);
    }

    @Override
    public String getFamily() {
        return "CocktailVignette";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.write("<div id=\"" + getIdPrefix() + getName() + "\" class=\"cocktail-vignette\">");
        writer.write("<h2>" + getName() + "</h2>");
        writer.write("<h3>" + getPrice() + "</h3>");
        writer.write("</div>");
    }
}