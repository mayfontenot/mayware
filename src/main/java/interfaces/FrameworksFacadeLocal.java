/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.Frameworks;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author azurei
 */
@Local
public interface FrameworksFacadeLocal {

    void create(Frameworks frameworks);

    void edit(Frameworks frameworks);

    void remove(Frameworks frameworks);

    Frameworks find(Object id);

    List<Frameworks> findAll();

    List<Frameworks> findRange(int[] range);

    int count();
    
}
