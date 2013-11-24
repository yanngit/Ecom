package component;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.ResponseWriter;

@FacesComponent(value = "CocktailThumbnail")
public class CocktailThumbnail extends UIComponentBase {

    public String getName() {
        return (String) getStateHelper().eval("name");
    }

    public String getPrice() {
        return (String) getStateHelper().eval("price");
    }

    public String getPhotoURI() {
        return (String) getStateHelper().eval("urlPhoto");
    }

    @Override
    public String getFamily() {
        return "CocktailThumbnail";
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        
        writer.write("<li class=\"span2 thumbnail\">\n" +
"                                <div class=\"filling-thumb\">\n" +
"                                    <img src=\"resources/img/" + getPhotoURI() + "\"\n"+
"                                         alt=\"Cocktail " + getName() + "\"/>\n" +
"                                </div>\n" +
"                                <div class=\"caption\">\n" +
"                                    <h5>\n" +
"                                        " + getName() + "\n" +
"                                    </h5>\n" +
"                                    <button type=\"button\" class=\"btn btn-block\">\n" +
"                                        Détails\n" +
"                                    </button>\n" +
"                                    <button type=\"button\" class=\"btn btn-success btn-block\">\n" +
"                                        Commander\n" +
"                                    </button>\n" +
"                                </div>\n" +
"                            </li>");
    }
}