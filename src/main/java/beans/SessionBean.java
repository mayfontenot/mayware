package beans;

import entities.Users;
import interfaces.UsersFacadeLocal;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.HexFormat;

@Named(value = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    @EJB
    private UsersFacadeLocal usersFL;
    private String username, password;
    private int level;
    private boolean loggedIn = false;

    public String login() {
        try {
            String hashedPassword = HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(password.getBytes("UTF-8")));

            for (Users user : usersFL.findAll())
                if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(hashedPassword))
                {
                    level = user.getLevel();
                    loggedIn = true;
                    return "admin.xhtml?faces-redirect=true";
                }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Username or password is invalid", ""));
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Database Error", e.getMessage()));
        }
        
        return "";
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
