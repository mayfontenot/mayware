package beans;

import entities.Frameworks;
import interfaces.FrameworksFacadeLocal;
import interfaces.ProjectsFacadeLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import org.primefaces.event.RowEditEvent;

@Named(value = "frameworksBean")
@SessionScoped
public class FrameworksBean implements Serializable {
    @EJB
    private FrameworksFacadeLocal frameworksFL;
    @EJB
    private ProjectsFacadeLocal projectsFL;
    private List<Frameworks> frameworks;
    private String newProjectName, newFrameworkName = "";
    
    @PostConstruct
    public void init() {
        frameworks = frameworksFL.findAll();
    }
    
    public void insert() {
        try {
            Frameworks f = new Frameworks(0, newFrameworkName);
            f.setProjectName(projectsFL.find(newProjectName));
            
            frameworksFL.create(f);
            frameworks = frameworksFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple inserted", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }
    
    public void cancelInsert() {
        newProjectName = "";
        newFrameworkName = "";
    }
    
    public void onRowEdit(RowEditEvent<Frameworks> event) {
        try {
            frameworksFL.edit(event.getObject());
            frameworks = frameworksFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple updated", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }
    
    public void delete(Frameworks f) {
        try {
            frameworksFL.remove(f);
            frameworks = frameworksFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple deleted", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }

    public List<Frameworks> getFrameworks() {
        return frameworks;
    }

    public String getNewProjectName() {
        return newProjectName;
    }

    public void setNewProjectName(String newProjectName) {
        this.newProjectName = newProjectName;
    }

    public String getNewFrameworkName() {
        return newFrameworkName;
    }

    public void setNewFrameworkName(String newFrameworkName) {
        this.newFrameworkName = newFrameworkName;
    }
}
