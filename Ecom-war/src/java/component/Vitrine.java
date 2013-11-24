package component;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "Vitrine")
public class Vitrine extends UIComponentBase implements NamingContainer {
      
    public Integer getSize() {
        return (Integer) getStateHelper().eval("size");
    }

    public void setSize(int size) {
        getStateHelper().put("size", new Integer(size));
    }
    
    public String getIdDiv() {
        return (String) getStateHelper().eval("id");
    }

    public void setIdDiv(String id) {
        getStateHelper().put("id", id);
    }
    
     public String getTitle() {
        return (String) getStateHelper().eval("title");
    }

    public void setTitle(String title) {
        getStateHelper().put("title", title);
    }

    @Override
    public String getFamily() {
        return "Vitrine";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        super.encodeBegin(context);
        ResponseWriter writer = context.getResponseWriter();
        String span;
        if(getSize() < 6){
            span = "span"+(getSize()*2+1);
        } else {
            span = "span12";
        }    
        writer.write("<div id=\""+getIdDiv()+"\" class=\""+span+" cocktail-vitrine\">");
        writer.write("<div class=\"vitrine-titre\"><p>"+getTitle()+"</p></div>");
    }
    
    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        super.encodeEnd(context);
        ResponseWriter writer = context.getResponseWriter();
        writer.write("</div>");
    }
}