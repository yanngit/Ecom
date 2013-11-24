package component;

import com.sun.faces.taglib.html_basic.CommandButtonTag;
import java.io.IOException;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "CocktailVignette")
public class CocktailVignette extends UIComponentBase implements NamingContainer {
    
    public String getID() {
        return (String) getStateHelper().eval("ID");
    }
    
    public void setID(String ID) {
        getStateHelper().put("ID", ID);
    }
    
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
        super.encodeBegin(context);
        ResponseWriter writer = context.getResponseWriter();
        String id = getIdPrefix();
        String[] tab = getName().split("\\s+");
        for (String s : tab) {
            id += "_" + s;
        }
        writer.write("<div id=\"" + id + "\" class=\"span2 cocktail-vignette\">");
        writer.write("<img src=\"resources/img/" + getUrlPhoto() + "\" alt=\"Cocktail " + getName() + "\" longdesc=\"chart.html\" /><br />");
        writer.write("<h2 class=\"cocktail-vignette-name\">" + getName() + "</h2>");
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeBegin(context);
        ResponseWriter writer = context.getResponseWriter();
        writer.write("</div>");
    }
}