package component;

import entity.CocktailEntity;
import java.io.IOException;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "Vitrine")
public class Vitrine extends UIComponentBase {
      
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
    
    public List<CocktailEntity> getItemList() {
        return (List<CocktailEntity>)getStateHelper().get("list");
    }

    public void setItemList(List<CocktailEntity> list) {
       getStateHelper().put("list", list);
    }

    @Override
    public String getFamily() {
        return "Vitrine";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        writer.write("<div id=\"" + getIdDiv()+"\" class=\"cocktail-vitrine\">");
        writer.write("<h2>" + getTitle()+ "</h2>");
        List<CocktailEntity> list = (List<CocktailEntity>)getStateHelper().get("list");
        for(CocktailEntity c : list){
            writer.write("<t:cocktailVignette idPrefix=\\\"newCocktails#{item.name}\\\" name=\\\"#{item.name}\\\" price=\\\"#{item.price}\\\" urlPhoto=\\\"#{item.photoURI}\\\"/> ");
        }
        writer.write("</div>");
    }
}