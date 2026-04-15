/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author azurei
 */
@Entity
@Table(name = "frameworks")
@NamedQueries({
    @NamedQuery(name = "Frameworks.findAll", query = "SELECT f FROM Frameworks f"),
    @NamedQuery(name = "Frameworks.findById", query = "SELECT f FROM Frameworks f WHERE f.id = :id"),
    @NamedQuery(name = "Frameworks.findByFrameworkName", query = "SELECT f FROM Frameworks f WHERE f.frameworkName = :frameworkName")})
public class Frameworks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "framework_name")
    private String frameworkName;
    @JoinColumn(name = "project_name", referencedColumnName = "project_name")
    @ManyToOne(optional = false)
    private Projects projectName;

    public Frameworks() {
    }

    public Frameworks(Integer id) {
        this.id = id;
    }

    public Frameworks(Integer id, String frameworkName) {
        this.id = id;
        this.frameworkName = frameworkName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrameworkName() {
        return frameworkName;
    }

    public void setFrameworkName(String frameworkName) {
        this.frameworkName = frameworkName;
    }

    public Projects getProjectName() {
        return projectName;
    }

    public void setProjectName(Projects projectName) {
        this.projectName = projectName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Frameworks)) {
            return false;
        }
        Frameworks other = (Frameworks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Frameworks[ id=" + id + " ]";
    }
    
}
