/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.jahap.business.acc.DatabaseOperations;
import org.jahap.business.base.ratesbean;
import org.jahap.entities.AccountPosition;
import org.jahap.entities.Accounts;
import org.jahap.entities.Address;
import org.jahap.entities.Bill;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Occ;
import org.jahap.entities.Rates;
import org.jahap.entities.Res;
import org.jahap.entities.Revenue;
import org.eclipse.persistence.internal.libraries.antlr.runtime.tree.DOTTreeGenerator;

/**
 *
 * @author russ
 */
public class accountsbean extends DatabaseOperations implements accounts_i{

    
    JahapDatabaseConnector dbhook;
    private static List<Accounts> allrecordlist;
   
    
    /**
     * Constructor
     */
    public accountsbean(){
        long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Accounts t");
            List<Accounts>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Accounts t");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          System.out.println("=========>dbconnection Accounts");
           // If the table is yet empty, init List 
        
         
        
    }
    
          /**
     *
     * @param searchstring
     * @return
     */
    public List<Accounts>SearchForOcc(String searchstring){
    
        return allrecordlist;
    }
    
    
    
    /**
     *
     */
    public void createNewEmptyRecord() {
         if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Accounts>();
            numberOfLastRecord++;
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        /*
         if(numberOfLastRecord==-1){
            
            numberOfLastRecord++;
            
        }
        
         if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         } */
        Accounts emptyacc = new Accounts();
        
       
        allrecordlist.add(emptyacc);
        this.currentRecordNumber=numberOfLastRecord;
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

     private void RefreshAllRecords(){
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Accounts t");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
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

    
     private void saveOldRecord(){
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Accounts.class,allrecordlist.get(this.currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
    } 
    
    
     private void saveNewRecord(){
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().persist(allrecordlist.get(this.currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Accounts t"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
     } 
     
     
     
     
      /**
     *
     */
    public void quitDBaccess(){
       dbhook.getEntity().close();
   } 
     
     
     
    // ########################## Position handling ###############
    
    void addPosition(Rates r,double amount,String positioname){
      accountspositionbean test=new accountspositionbean();
      revenuebean rev=new revenuebean();

      rev.createNewEmptyRecord(); 
      rev.setRevaccount(r.getRevaccount());
      rev.setAmount(amount);
      
      test.createNewEmptyRecord();
      test.setAccount(allrecordlist.get(this.currentRecordNumber));
      test.setAmount(amount);
      if (amount==0){
            test.setAmount(r.getPrice());
            rev.setAmount(r.getPrice());
      }
      test.setPositionname(positioname);
      if (positioname==""){
            test.setPositionname(r.getName());      
            
      }
      
      
      test.setRate(r);
      test.saveRecord();
      
      rev.setAccountposition(test.getLastPosition());
      rev.saveRecord();
      
      
     
      
    }
    
    
    
    void  adjustPosition(AccountPosition ToAdjustPosition,AccountPosition AdjustLikePosition ){
         accountspositionbean test=new accountspositionbean();
      revenuebean rev=new revenuebean();
         
       Collection<Revenue> btrw=new ArrayList<Revenue>(); 
        revaccountsbean jj=new revaccountsbean();
             
      
      
      if (test.searchForAccPosition(ToAdjustPosition)!=null){
          
              if (AdjustLikePosition.getAccount()!=null) {
                   test.setAccount(AdjustLikePosition.getAccount());
              }
              
              if (AdjustLikePosition.getAmount()!=0) {
              test.setAmount(AdjustLikePosition.getAmount());
               }
              
            
              test.setDebit(AdjustLikePosition.getDebit());
              
             if (AdjustLikePosition.getPositionname()!="") {
              test.setPositionname(AdjustLikePosition.getPositionname());
              }
              if (AdjustLikePosition.getRate()!=null) {
              test.setRate(AdjustLikePosition.getRate());
               }
              
              if (AdjustLikePosition.getRatedate()!=null) {
                  test.setRatedate(AdjustLikePosition.getRatedate());
              }
              
              //searches in Rev collection of accpos 
              for(Iterator<Revenue> i=test.getRevenueCollection().iterator();i.hasNext();){
                  
                  if(rev.selectRevPos(i.next().getId())==true){
                      
                  
                   if (AdjustLikePosition.getAmount()!=0) {
                      
                      rev.setAmount(AdjustLikePosition.getAmount());
                  }
                   
                   try {
                    
                      rev.setDebit(AdjustLikePosition.getDebit());
                  } catch (Exception e) {
                  }
                  
                   if((AdjustLikePosition.getRate()!=null)){
                           if (AdjustLikePosition.getRate().getRevaccount()!=0){
                             
                              rev.setRevaccount(jj.SearchForRevAccount(AdjustLikePosition.getRate().getRevaccount()));
                           }
                   }
                   
                  } 
              }
              rev.saveRecord();
              test.saveRecord();
              
             
      }
      
    
      
    }
    
    
     //DEV : Last Edit
        //DEV : Copy und Move Operation einbauen
       
    
    
    /**
     *
     * @param fromAccountNr
     * @param accountPosNr
     */
    public void copyPositionsIntoThisAccount(long fromAccountNr,long accountPosNr[]) {
           
         
           for(int k=0;k<allrecordlist.size()-1;){  //search for the Account
                  if(allrecordlist.get(k).getId()==fromAccountNr){
                      
                       AccountPosition kj[]=allrecordlist.get(k).getAccountPositionCollection().toArray(new AccountPosition[allrecordlist.get(k).getAccountPositionCollection().size()]);
                           for(int l=0;l<accountPosNr.length;){ // go through all pos to replicate
                                   for(int ul=0;ul<kj.length;){
                                       if(kj[ul].getId()==accountPosNr[l]){
                                             kj[ul].setAccount(allrecordlist.get(currentRecordNumber));
                                              copyPos(kj[ul]);
                                       }
                                       ul++;
                                   }
                               
                               
                               
                             l++;  
                           }
                                   
                          break; // If the account has been found     
                      
                        } 
                  k++;   
           }
        
        saveRecord();
        
    }
    
    private AccountPosition copyPos(AccountPosition getit){
          revenuebean rev=new revenuebean();

      accountspositionbean ulo= new accountspositionbean();
        ulo.createNewEmptyRecord();
           
           ulo.setAccount(getit.getAccount());
           ulo.setAmount(getit.getAmount());
           ulo.setBilled(getit.getBilled());
           ulo.setDebit(getit.getDebit());
           ulo.setPositionname(getit.getPositionname());
           ulo.setRate(getit.getRate());
           ulo.setRatedate(getit.getRatedate());
        
          
          // Create new REVEntree for this
          rev.createNewEmptyRecord();    
          rev.setRevaccount(getit.getRate().getRevaccount());
          rev.setAmount(ulo.getAmount());
          rev.setAccountposition(ulo.getLastPosition());
          rev.setDebit(ulo.getDebit());
          rev.saveRecord();
          ulo.saveRecord();
          return ulo.getLastPosition();
    }
    
    
     private AccountPosition movePos(AccountPosition getit){
          revenuebean rev=new revenuebean();

      accountspositionbean ulo= new accountspositionbean();
        ulo.createNewEmptyRecord();
           
           ulo.setAccount(getit.getAccount());
           ulo.setAmount(getit.getAmount());
           ulo.setBilled(getit.getBilled());
           ulo.setDebit(getit.getDebit());
           ulo.setPositionname(getit.getPositionname());
           ulo.setRate(getit.getRate());
           ulo.setRatedate(getit.getRatedate());
        
          
          // Create new REVEntree for this
          rev.createNewEmptyRecord();    
          rev.setRevaccount(getit.getRate().getRevaccount());
          rev.setAmount(ulo.getAmount());
          rev.setAccountposition(ulo.getLastPosition());
          if(ulo.getDebit()==true)
                  rev.setDebit(false);               
          else
                 rev.setDebit(true);
          
          
          rev.saveRecord();
          // Debit or Credit Position
          
          
          
          
          
          
          ulo.saveRecord();
          AccountPosition cambio=ulo.searchForAccPosition(getit);
          if(ulo.getDebit()==true)
                  ulo.setDebit(false);               
          else
              ulo.setDebit(true);
          ulo.saveRecord();
          
          return ulo.getLastPosition();
    }
    
    
    /**
     *
     * @param numberOfPos
     */
    public void replicatePosition(long numberOfPos[]){
        
        
        AccountPosition uiu[]= allrecordlist.get(currentRecordNumber).getAccountPositionCollection().toArray(new AccountPosition[allrecordlist.get(currentRecordNumber).getAccountPositionCollection().size()]);
        for (int i = 0; i < numberOfPos.length; ) {
            long l = numberOfPos[i];
                for(int o=0;o<uiu.length; ){
                        if(numberOfPos[i]==uiu[o].getId()){
                            //allrecordlist.get(currentRecordNumber).getAccountPositionCollection().add(
                            copyPos(uiu[o]);
                                                   
                        }  
                    
                    o++;
                }
            i++;
        }
        
        //saveRecord();
    }
    
    /**
     *
     * @param fromAccountNr
     * @param positionsToMove
     */
    public void movePositionsIntoThisAccount(long fromAccountNr, long positionsToMove[] ){
        for(int k=0;k<allrecordlist.size()-1;){  //search for the Account
                  if(allrecordlist.get(k).getId()==fromAccountNr){
                      
                       AccountPosition kj[]=allrecordlist.get(k).getAccountPositionCollection().toArray(new AccountPosition[allrecordlist.get(k).getAccountPositionCollection().size()]);
                           
                           for(int l=0;l<positionsToMove.length;){ // go through all pos to replicate
                                   for(int ul=0;ul<kj.length;){
                                       if(kj[ul].getId()==positionsToMove[l]){
                                           
                                             movePos(kj[ul]);   // Copy Pos, and inver debit property of source                                        
                                             
                                       }
                                       ul++;
                                   }
                               
                               
                               
                             l++;  
                           }
                                   
                          break; // If the account has been found     
                      
                        } 
                  k++;   
           }
        
        saveRecord();
           
        
        
    }
    
    
    
    
    // ###################### misc ##############
    
    /**
     *
     * @return
     */
    public double calcBalance(){
        Collection pos; 
       
   
        //hl[]=new Object[allrecordlist.get(currentRecordNumber).getAccountPositionCollection().size()];
        AccountPosition hl[]=allrecordlist.get(currentRecordNumber).getAccountPositionCollection().toArray(new AccountPosition[allrecordlist.get(currentRecordNumber).getAccountPositionCollection().size()]);
        
        
        double balance=0;
        for(int i=0;i<hl.length; ){
            if(hl[i].getDebit()==false){    
                       balance+= hl[i].getAmount();
                       
                         
            }
            if(hl[i].getDebit()==true){    
                       balance-= hl[i].getAmount();
                       
                         
            }
            i++;
        }
        
        
        return balance;
    }
    
  
    /**
     *
     */
    public void moveToFirstRecord(){
      currentRecordNumber=0;
      
  }
  
 
   
    
  
  
    // ############### Billing   #################
    
    
    void createBill(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    void copyPositonToBill(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    void markPositionAsBilled(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    //################ Reporting ###############
    
    
    void getPrintedAccountReport(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    void getReportDataAccount(){
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    // #########################  Getters and Setter   ########################
    /**
     *
     * @return
     */
    public Collection<AccountPosition> getAccountPositionCollection() {
        
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAccountPositionCollection();
        return null;
        
    }

    /**
     *
     * @return
     */
    public Integer getBalance() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getBalance();
        return null;
    }

    

    /**
     *
     * @return
     */
    public Serializable getCheckin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @return
     */
    public String getCheckindate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCheckindate();
        return null;
    }

    /**
     *
     * @return
     */
    public boolean getCheckout() {
        if( tabelIsEmpty!=true) {
              return allrecordlist.get(currentRecordNumber).getCheckout();
        }
        return false;
    }

    /**
     *
     * @return
     */
    public String getCheckoutdate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCheckoutdate();
        return null;
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
    public Res getReservation() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getReservation();
        return null;
    }

    /**
     *
     * @param accountPositionCollection
     */
    public void setAccountPositionCollection(Collection<AccountPosition> accountPositionCollection) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setAccountPositionCollection(accountPositionCollection);
    }

    /**
     *
     * @param balance
     */
    public void setBalance(Integer balance) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setBalance(balance);
    }

    /**
     *
     * @param bill
     */
    public void setBill(Bill bill) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     *
     * @param checkin
     */
    public void setCheckin(Serializable checkin) {
        
    }

    /**
     *
     * @param checkindate
     */
    public void setCheckindate(String checkindate) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCheckindate(checkindate);
    }

   
    /**
     *
     * @param checkoutdate
     */
    public void setCheckoutdate(String checkoutdate) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCheckindate(checkoutdate);
    }

    /**
     *
     * @param reservation
     */
    public void setReservation(Res reservation) {
        if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setReservation(reservation);
    }

    /**
     *
     * @param checkout
     */
    public void setCheckout(boolean checkout) {
         if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCheckout(checkout);
    }

    

   
    
}
