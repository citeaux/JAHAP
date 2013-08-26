/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import java.util.ArrayList;
import java.util.List;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Revaccounts;
import org.jahap.entities.Revenue;

/**
 *
 * @author russ
 */
public class revenuebean extends DatabaseOperations  implements revenue_i {

    JahapDatabaseConnector dbhook;
    private static List<Revenue> allrecordlist; 
    
    public revenuebean(){
       long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revenue t");
            List<Revenue>allroomslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allroomslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revenue t");
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revenue t");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }  public List<Revenue>SearchForRev(String searchstring){
    
        return allrecordlist;
    }
    
    public boolean selectRevPos(long id){
        int i=0;
        do {
             
            if(allrecordlist.get(i).getId() ==id){
              currentRecordNumber=i;       
              return true;
            }  
            i++;
          }while(allrecordlist.get(i).getId()!=id);
        return false;
    }
    
    
    public void createNewEmptyRecord() {
        if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Revenue>();
            numberOfLastRecord++;
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
                Revenue emptyacc = new Revenue();
        
       
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
    
    private void saveNewRecord(){
        revaccountsbean revAcc=new revaccountsbean(); 
        
        if (revAcc.SearchForRevAccount(allrecordlist.get(currentRecordNumber).getRevaccount().getId())!=null){
                     allrecordlist.get(currentRecordNumber).setRevaccount(revAcc.SearchForRevAccount(allrecordlist.get(currentRecordNumber).getRevaccount().getId()));            
        }
        
        
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
           
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Revenue t"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
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
    
     private void saveOldRecord(){
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Revaccounts.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 

    public void quitDBaccess() {
        dbhook.getEntity().close();
    }

    
       // ####################### Getter and Setters ############   
    
    public AccountPosition getAccountposition() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAccountposition();
        return null;
    }

    public boolean getDebit() {
       if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getDebit();
        return false;
    }

    public Long getId() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

    public Revaccounts getRevaccount() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevaccount();
        return null;
    }

    public void setAccountposition(AccountPosition accountposition) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAccountposition(accountposition);
    }

    public void setDebit(boolean debit) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setDebit(debit);
    }

    public void setRevaccount(Revaccounts revaccount) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRevaccount(revaccount);
    }
    
    public void setRevaccount(long revaccid){
           revaccountsbean rac=new revaccountsbean();
            if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRevaccount(rac.getRevAccount(revaccid));
           
           
    }

    public double getAmount() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAmount();
        return 0;
    }

    public void setAmount(double amount) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAmount(amount);
    }
    
}
