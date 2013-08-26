/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import org.jahap.entities.accountsposition_ie;

/**
 *
 * @author russ
 */
public interface accountsposition_i extends DatabaseOperations_i,accountsposition_ie {
    
    
    public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
}
