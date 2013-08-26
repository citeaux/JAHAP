/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.text.html.HTMLDocument;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Accounts;
import org.jahap.entities.Address;
import org.jahap.entities.BillPos;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Rates;
import org.jahap.entities.Revenue;
import org.jahap.entities.accountsposition_ie;

/**
 *
 * @author russ
 */
public class accountspositionbean extends DatabaseOperations implements accountsposition_i{

    JahapDatabaseConnector dbhook;
    private static List<AccountPosition> allrecordlist;
    
    /**
     * Constructor
     */
    public accountspositionbean(){
        long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from AccountPosition t");
            List<AccountPosition>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  AccountPosition t");
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from AccountPosition t");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
     /**
     *
     * @param searchstring
     * @return
     */
    public List<AccountPosition>SearchForAccPos(String searchstring){
    
        return allrecordlist;
    }
    
    /**
     *
     */
    public void createNewEmptyRecord() {
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<AccountPosition>();
            numberOfLastRecord++;
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
                AccountPosition emptyacc = new AccountPosition();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
    }

    /**
     *
     */
    public void nextRecordBackward() {
        if (currentRecordNumber>0) {
            currentRecordNumber--;
        }
    }

    /**
     *
     */
    public void nextRecordForeward() {
        if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }
    }

    /**
     *
     */
    public void saveRecord() {
        if (newEmptyRecordCreated=true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          
      }
      if (newEmptyRecordCreated=false){
          saveOldRecord();
      }
    }
    
    /**
     *
     * @return
     */
    public AccountPosition getLastPosition(){
             if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber);
        return null;
    }
    
    
    
    /**
     *
     * @param accpos for search
     * @return Accountposition 
     * 
     * If the accpos includes a record id  which exits in the DB table this record
     * will be returned.
     * 
     * If the ID is unknown, this function searches for Rate,Posname,Ratedate and Amount.
     * If there is no record which can be exactly detected by this combination - null will be returned
     * 
     * Sets focus of bean on the found record.
     */
    public AccountPosition searchForAccPosition(AccountPosition accpos){
          int iAmount=0;  
          int iRate=0;
          int iPosname=0;
          int iRateDate=0;
          ArrayList<AccountPosition> erma=new ArrayList<AccountPosition>();
          AccountPosition ola=new AccountPosition();
          int number=0;
          for(AccountPosition ikk:allrecordlist){
                if (accpos.getId()==ikk.getId()){
                    currentRecordNumber=number;
                    return ikk;
                  }
                   number++;                 
                }
          
          // if the Positon can be identify by one of this attributes
          
          // Amount
          number=0;
          for(AccountPosition ikk:allrecordlist){
                         if(ikk.getAmount()==accpos.getAmount()){
                           iAmount++;
                           erma.add(ikk);
                           ola=ikk;
                           number++;
                         }      
                    }
            
                if(iAmount==1){
                    currentRecordNumber=number;
                    return ola;
                }
            
                
                // RAte
                number=0;
                 for(AccountPosition ikc:erma){
                              
                              
                              if(ikc.getRate()==accpos.getRate()){
                                   erma.remove(ikc);
                               }
                             
                             if(ikc.getRate()==accpos.getRate()){
                                 iRate++;
                                 ola=ikc;
                               }
                            
                      number++;
                  }
                            
                      if(iRate==1){
                                 currentRecordNumber=number;
                                 return ola;
                              }  
                         
                 // Posname        
                         number=0;      
                        for(AccountPosition iku:erma){
                                    
                                  if(iku.getPositionname()==accpos.getPositionname()){
                                   erma.remove(iku);
                               }
                             
                             if(iku.getPositionname()==accpos.getPositionname()){
                                 iPosname++;
                                 ola=iku;
                               }
                            
                                }
                                    
                         
                         
                         if (iPosname==1){
                                currentRecordNumber=number;
                               return ola;   
                         }
                         
                         
                    // ratedate     
                         
                         number++;
                       for(AccountPosition iku:erma){
                                    
                                  if(iku.getRatedate()==accpos.getRatedate()){
                                   erma.remove(iku);
                               }
                             
                             if(iku.getRatedate()==accpos.getRatedate()){
                                 iRateDate++;
                                 ola=iku;
                               }
                            
                             }
                                    
                         
                         
                         if (iRateDate==1){
                              int ij=0;
                                for(AccountPosition ikk:allrecordlist){
                                      if(ola.getId()==allrecordlist.get(ij).getId()){
                                          currentRecordNumber=ij;
                                        return ola;     
                                      }
                                    ij++;
                                }
                               
                               
                               
                         }
                         
                    
            
            
            
                
            
            
       return null; 
    }
    
    
    
     private void saveNewRecord(){
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
           
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from AccountPosition t"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
     } 
    
    
     private void saveOldRecord(){
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(AccountPosition.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
    
    
      /**
     *
     */
    public void quitDBaccess(){
       dbhook.getEntity().close();
   } 
     
       // ########################## Position handling ############### 
      
      
    /**
     *
     * @return
     */
    public Accounts getAccount() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAccount();
        return null;
    }

    /**
     *
     * @return
     */
    public double getAmount() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAmount();
        return 0;
    }

    /**
     *
     * @return
     */
    public BigInteger getArticle() {
         if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getArticle();
        return null;
    }

    /**
     *
     * @return
     */
    public Collection<BillPos> getBillPosCollection() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getBillPosCollection();
        return null;
    }

    /**
     *
     * @return
     */
    public boolean getBilled() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getBilled();
        return false;
    }

    /**
     *
     * @return
     */
    public boolean getDebit() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getDebit();
        return false;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return null;
    }

    /**
     *
     * @return
     */
    public String getPositionname() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getPositionname();
        return null;
    }

    /**
     *
     * @return
     */
    public Rates getRate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRate();
        return null;
    }

    /**
     *
     * @return
     */
    public Date getRatedate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRatedate();
        return null;
    }

    /**
     *
     * @return
     */
    public Collection<Revenue> getRevenueCollection() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRevenueCollection();
        return null;
    }

    /**
     *
     * @param account
     */
    public void setAccount(Accounts account) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAccount(account);
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAmount(amount);
    }

   

    /**
     *
     * @param billPosCollection
     */
    public void setBillPosCollection(Collection<BillPos> billPosCollection) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBillPosCollection(billPosCollection);
    }

    /**
     *
     * @param billed
     */
    public void setBilled(boolean billed) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBilled(billed);
    }

    /**
     *
     * @param debit
     */
    public void setDebit(boolean debit) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setDebit(debit);
    }

    /**
     *
     * @param positionname
     */
    public void setPositionname(String positionname) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setPositionname(positionname);
    }

    /**
     *
     * @param rate
     */
    public void setRate(Rates rate) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRate(rate);
    }

    /**
     *
     * @param ratedate
     */
    public void setRatedate(Date ratedate) {
          if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRatedate(ratedate);
    }

    
    
}
