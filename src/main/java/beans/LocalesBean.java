package beans;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

@Named(value = "localesBean")
@SessionScoped
public class LocalesBean implements Serializable {
    private List<Locale> locales = new ArrayList();
    private Locale locale;
    
    public LocalesBean() {
        
        Iterator<Locale> localeIt = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();
        
        while(localeIt.hasNext())
            locales.add(localeIt.next());
    }
    
    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        
        if(locales.contains(locale))
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
    
    public void setLocale(String locale) {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale = new Locale(locale));
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public Locale getLocale() {
        return locale;
    }
}
