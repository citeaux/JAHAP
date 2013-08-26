/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.base;

import org.jahap.entities.rates_ie;

/**
 *
 * @author russ
 */
public interface rates_i extends DatabaseOperations_i, rates_ie {
      public void createNewEmptyRecord(); 

    public void nextRecordBackward(); 
     

    public void nextRecordForeward() ;


    public void saveRecord() ;
    public void quitDBaccess();
}
