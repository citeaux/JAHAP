/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.res;

import org.jahap.business.base.DatabaseOperations_i;
import org.jahap.entities.occ_ie;

/**
 *
 * @author russ
 */
public interface occ_i extends occ_ie, DatabaseOperations_i {
    
    
     public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
}
