/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import org.jahap.business.base.*;
import javax.persistence.Query;

/**
 *
 * @author russ
 */
public class DatabaseOperations {
     int currentRecordNumber=0;
     int numberOfLastRecord;
     Query query_AllDbRecords;
    boolean newEmptyRecordCreated=false;
     boolean tabelIsEmpty=true; 
    boolean tabelIsInit=false; // Set Tabel iniated - List is connected
    
    
          void setNewEmptyRecordCreadted() {
        this.newEmptyRecordCreated = true;
        this.tabelIsEmpty=false;
    }
    
    void setNewEmptyRecordSaved(){
        this.newEmptyRecordCreated = false;
    }
    
    
    
    
    
}
