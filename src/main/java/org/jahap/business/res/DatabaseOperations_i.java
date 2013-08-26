/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.res;

import javax.persistence.Query;

/**
 *
 * @author russ
 */
public interface DatabaseOperations_i {
    
   

    void createNewEmptyRecord();

    void nextRecordBackward();

    void nextRecordForeward();

    void saveRecord();
    
}
