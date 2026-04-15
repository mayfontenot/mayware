/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.Projects;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author azurei
 */
@Local
public interface ProjectsFacadeLocal {

    void create(Projects projects);

    void edit(Projects projects);

    void remove(Projects projects);

    Projects find(Object id);

    List<Projects> findAll();

    List<Projects> findRange(int[] range);

    int count();
    
}
