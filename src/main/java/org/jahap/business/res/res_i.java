/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.res;
import org.jahap.business.base.DatabaseOperations_i;
import org.jahap.entities.*;
/**
 *
 * @author russ
 */
public interface res_i extends DatabaseOperations_i, res_ie {
    
    public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
}
