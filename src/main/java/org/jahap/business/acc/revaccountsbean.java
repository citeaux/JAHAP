/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Revaccounts;
import org.jahap.entities.Revenue;

/**
 *
 * @author russ
 */
public class revaccountsbean extends DatabaseOperations implements revaccounts_i{
    
     private static List<Revaccounts> allrecordlist;
    JahapDatabaseConnector dbhook;
        public revaccountsbean(){
        long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revaccounts t");
            List<Revaccounts>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Revaccounts t");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          System.out.println("=========>dbconnection");
           // If the table is yet empty, init List 
        
    }
    
    
     private void RefreshAllRecords(){
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revaccounts t");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }  
     public Revaccounts SearchForRevAccount(long id){
    
             for(Revaccounts reva:allrecordlist){
                  if(reva.getId()==id){
                          return reva;
                  }
             }
         return null;
        
       
    }
        
        
    
    
    public void createNewEmptyRecord() {
        if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Revaccounts>();
            numberOfLastRecord++;
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
                Revaccounts emptyacc = new Revaccounts();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; //
    }
    

    public void nextRecordBackward() {
        if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
    }

    public void nextRecordForeward() {
        if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
    }

    public void saveRecord() {
         if (newEmptyRecordCreated=true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          
      }
      if (newEmptyRecordCreated=false){
          saveOldRecord();
      }
    }

   

     private void saveNewRecord(){
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
           
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revaccounts t"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
     } 
    
     private void saveOldRecord(){
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Revaccounts.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
     
       public void quitDBaccess(){
       dbhook.getEntity().close();
   } 
    
       
   // ####################### Getter and Setters ############ 
       
    public Revaccounts getRevAccount(long id){
           
        if( tabelIsEmpty!=true) 
             for(Revaccounts nh:allrecordlist){
                  if (nh.getId()==id){
                      return nh;
                  }
             }
               
              
        return null;
    }   
       
    
    public String getName() {
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return null;
    }

    public BigInteger getRevaccnumber() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevaccnumber();
        return null;
    }

    public Collection<Revenue> getRevenueCollection() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevenueCollection();
        return null;
    }

    public void setName(String name) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setName(name);
    }

    public void setRevaccnumber(BigInteger revaccnumber) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRevaccnumber(revaccnumber);
    }

    public void setRevenueCollection(Collection<Revenue> revenueCollection) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Long getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
