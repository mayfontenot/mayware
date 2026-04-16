package beans;

import entities.Images;
import interfaces.ImagesFacadeLocal;
import interfaces.ProjectsFacadeLocal;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.util.Callbacks.SerializableSupplier;

@Named(value = "imagesBean")
@SessionScoped
public class imagesBean implements Serializable {
    @EJB
    private ImagesFacadeLocal imagesFL;
    @EJB
    private ProjectsFacadeLocal projectsFL;
    private List<Images> images;
    private String newProjectName;
    private UploadedFile newImage;
    
    @PostConstruct
    public void init() {
        images = imagesFL.findAll();
    }
    
    public void insert() {
        try {
            Images i = new Images(0, newImage.getContent());
            i.setProjectName(projectsFL.find(newProjectName));
            
            imagesFL.create(i);
            images = imagesFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple inserted", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }
    
    public void cancelInsert() {
        newProjectName = "";
        newImage = null;
    }
    
    public void onRowEdit(RowEditEvent<Images> event) {
        try {
            imagesFL.edit(event.getObject());
            images = imagesFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple updated", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }
    
    public void delete(Images i) {
        try {
            imagesFL.remove(i);
            images = imagesFL.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Tuple deleted", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
    }
    
    public StreamedContent getStreamedContent(byte[] ar) {
        return DefaultStreamedContent.builder()
            .stream(new SerializableSupplier<InputStream>() {
                @Override
                public InputStream get() {
                    return new ByteArrayInputStream(ar);
                }
            })
        .build();
    }

    public List<Images> getImages() {
        return images;
    }

    public String getNewProjectName() {
        return newProjectName;
    }

    public void setNewProjectName(String newProjectName) {
        this.newProjectName = newProjectName;
    }

    public UploadedFile getNewImage() {
        return newImage;
    }

    public void setNewImage(UploadedFile newImage) {
        this.newImage = newImage;
    }
}
