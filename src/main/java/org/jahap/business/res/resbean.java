/*
 * The MIT License
 *
 * Copyright 2014 Sebastian Russ <citeaux at https://github.com/citeaux/JAHAP>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */



package org.jahap.business.res;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Address;
import org.jahap.entities.res.Res;

/**
 *
 * @author russ
 */
public class resbean extends DatabaseOperations implements res_i {
   static Logger log = Logger.getLogger(resbean.class.getName());
    JahapDatabaseConnector dbhook;
    private static List<Res> allrecordlist;

    
     /**
     * <h3>Constructor</h3> 
     * establishes the connection to the database <p> 
     * gets all reservation records available
     */
    public resbean(){
        log.debug("Function entry constructor");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Res t ORDER BY t.id");
            List<Res>allreslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allreslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Res t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
        
        
        
        log.debug("Function exit constructor");
        
    }
    
    
    
    
    /**
     *
     * @return
     */
    public  long getNewResNumber(){
        log.debug("Function entry getNewResNumber");
        
        // DEV: Number Area has to be implemented
        long resno=0;
        int gg=0;
        try {
            gg=allrecordlist.size()-1;
            resno = allrecordlist.get(gg).getId();
        } catch (Exception e) {
            resno=resno+1;
        }
        
        
        resno=resno+1;
        log.debug("Function exit get NewResNumber:" + String.valueOf(resno));
        return resno;
    }
    
    
    
    
    /**
     *
     */
    public void createNewEmptyRecord() {
        log.debug("Function entry createNewEmptyRecord");
        if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         }
        
        if(numberOfLastRecord==-1){
            allrecordlist = new ArrayList();
            numberOfLastRecord++;
        }
        
         
        Res emptyroom = new Res();
        
       
        allrecordlist.add(emptyroom);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected 
        
        log.debug("Function exit createNewEmptyRecord");
    }

      /**
     *
     * @param searchstring
     * @return
     */
    public List<Res>SearchForReservations(String searchstring){
        log.debug("Function entry List<Res>SearchForReservations");
       return allrecordlist;
   }
    
    public Res GetCurrentRes(){
        log.debug("Function entry GetCurrentRes");
        return allrecordlist.get(currentRecordNumber);
    }
   
     private void saveNewRecord(){
         log.debug("Function entry saveNewRecord");
        if ( newEmptyRecordCreated==true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().persist(allrecordlist.get(currentRecordNumber));
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Res t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            
            }
            catch (Exception e){
                  e.printStackTrace();   
            }
        }
         log.debug("Function exit saveNewRecord");
        }
      
      
     /**
     *
     * @param id
     * @return
     */
    public Res getDataRecord(long id){
        log.debug("Function entry getDataRecord");
         int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
            System.out.println(currentRecordNumber);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        log.debug("Function exit getDataRecord");
        return allrecordlist.get(currentRecordNumber);
       
        
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
     * @param id
     */
    public void  setDataRecordId(Long id){
        int inl=0;
       
        
        for(Res k:allrecordlist){
            
            if(k.getId().equals(id)){
                log.debug("Function entry " + inl );
                currentRecordNumber=inl;
                break;
            }
            inl++;  
        }
        
      
       
   }
    
    public Res getLastRecord(){
             return  allrecordlist.get(allrecordlist.size()-1);
        
    }
    
     private void RefreshAllRecords(){
         log.debug("Function entry RefreshAllRecords ");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Res t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
         log.debug("Function exit RefreshAllRecords");
    }
    
    
    /**
     *
     */
    public void saveRecord() {
        log.debug("Function entry saveRecord");
          if (newEmptyRecordCreated==true){
          saveNewRecord();
          setNewEmptyRecordSaved();
          RefreshAllRecords();
          
      }
      if (newEmptyRecordCreated==false){
          saveOldRecord();
      }
      
        log.debug("Function exit saveRecord");
      
    }

   
    
     /**
     *
     */
    public void removeCurrentRecord(){
            log.debug("Function entry removeCurrentRecord");
           
           currentRecordNumber=allrecordlist.size()-1;
           dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().remove(allrecordlist.get(currentRecordNumber));
            dbhook.getEntity().getTransaction().commit();
           RefreshAllRecords();
           log.debug("Function entry removeCurrentRecord");
           
     }
   
   private void saveOldRecord(){
       log.debug("Function entry saveOldRecord ");
         if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
          dbhook.getEntity().find(Res.class,allrecordlist.get(currentRecordNumber).getId() );
             dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
         log.debug("Function exit saveOldRecord");
    } 
    
    /**
     *
     */
    public void quitDBaccess(){
       dbhook.getEntity().close();
   }   
       
    
    
    //------------------------------------------ Getters and Setters -----------------------------
    /**
     *
     * @return
     */
    public Address getAddresses() {
        Address test=null; 
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getAddressid();
        return test;
    }

    /**
     *
     * @return
     */
    public Date getArrivaldate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getArrivaldate();
        return null;
    }

    /**
     *
     * @return
     */
    public Date getArrivaltime() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getArrivaltime();
        return null;
    }

    /**
     *
     * @return
     */
    public Date getDeparturedate() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getDeparturedate();
        return null;
    }

    /**
     *
     * @return
     */
    public Date getDeparturetime() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getDeparturetime();
        return null;
    }

    /**
     *
     * @return
     */
    public String getResno() {
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getResno();
        
        return "";
    }
    
 
    
    /**
     *
     * @return
     */
    public long getID(){
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return 0;
    }
    

    /**
     *
     * @param addresses
     */
    public void setAddresses(Address addresses) {
        if(tabelIsInit==false|| tabelIsEmpty==true){
            createNewEmptyRecord();
        }
            allrecordlist.get(currentRecordNumber).setAddressid(addresses);
    }

    /**
     *
     * @param String arrivaldate
     */
    public void setArrivaldate(Date arrivaldate) {
        if(tabelIsInit==false|| tabelIsEmpty==true){
            createNewEmptyRecord();
        } 
            allrecordlist.get(currentRecordNumber).setArrivaldate(arrivaldate);
    }

    /**
     *
     * @param String arrivaltime
     */
    public void setArrivaltime(Date arrivaltime) {
        if(tabelIsInit==false|| tabelIsEmpty==true){
            createNewEmptyRecord();
        }
            allrecordlist.get(currentRecordNumber).setArrivaltime(arrivaltime);
    }

    /**
     *
     * @param String departuredate
     */
    public void setDeparturedate(Date departuredate) {
        if(tabelIsInit==false|| tabelIsEmpty==true){
            createNewEmptyRecord();
          }
            allrecordlist.get(currentRecordNumber).setDeparturedate(departuredate);
    }

    /**
     *
     * @param String departuretime
     */
    public void setDeparturetime(Date departuretime) {
         if(tabelIsInit==false|| tabelIsEmpty==true){
            createNewEmptyRecord();
         }
            allrecordlist.get(currentRecordNumber).setDeparturetime(departuretime);
    }

    /**
     *
     * @param resno
     */
    public void setResno(String resno) {
        if(tabelIsInit==false|| tabelIsEmpty==true){
            createNewEmptyRecord();
        }
            allrecordlist.get(currentRecordNumber).setResno(resno);
    }
    
    

    /**
     *
     */
    public void setResno() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
    
   
    
}
