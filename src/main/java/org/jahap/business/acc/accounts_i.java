/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import org.jahap.business.base.DatabaseOperations_i;
import org.jahap.entities.accounts_ie;

/**
 *
 * @author russ
 */
public interface accounts_i extends DatabaseOperations_i, accounts_ie{
  
    public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
    
    
}
