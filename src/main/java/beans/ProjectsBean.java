package beans;

import entities.Projects;
import interfaces.ProjectsFacadeLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.RowEditEvent;

@Named(value = "projectsBean")
@SessionScoped
public class ProjectsBean implements Serializable {
    @EJB
    private ProjectsFacadeLocal projectsFL;
    private List<Projects> projects;
    private String newProjectName = "", newDescription = "";
    
    @PostConstruct
    public void init() {
        projects = projectsFL.findAll();
    }
    
    public void insert() {
        try {
            Projects p = new Projects(newProjectName, newDescription);
            p.setFrameworksCollection(new ArrayList());
            p.setImagesCollection(new ArrayList());
            
            projectsFL.create(p);
            projects = projectsFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple inserted", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }
    
    public void cancelInsert() {
        newProjectName = "";
        newDescription = "";
    }
    
    public void onRowEdit(RowEditEvent<Projects> event) {
        try {
            projectsFL.edit(event.getObject());
            projects = projectsFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple updated", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }
    
    public void delete(Projects p) {
        try {
            projectsFL.remove(p);
            projects = projectsFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple deleted", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }

    public List<Projects> getProjects() {
        return projects;
    }

    public String getNewProjectName() {
        return newProjectName;
    }

    public void setNewProjectName(String newProjectName) {
        this.newProjectName = newProjectName;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }
}
