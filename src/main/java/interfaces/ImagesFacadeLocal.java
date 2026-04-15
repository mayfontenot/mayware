/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.Images;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author azurei
 */
@Local
public interface ImagesFacadeLocal {

    void create(Images images);

    void edit(Images images);

    void remove(Images images);

    Images find(Object id);

    List<Images> findAll();

    List<Images> findRange(int[] range);

    int count();
    
}
