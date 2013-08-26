package org.jahap.business.res;


import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jahap.business.res.DatabaseOperations;
import org.jahap.entities.Address;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.Occ;
import org.jahap.entities.Res;
import org.jahap.entities.Rooms;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author russ
 */
public class occbean extends  DatabaseOperations implements occ_i{

    JahapDatabaseConnector dbhook;
    private static List<Occ> allrecordlist;
    
    
    /**
     *
     */
    public occbean(){
        long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Ooc t");
            List<Occ>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occ t");
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
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Occ t");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    
    //----------------------- Search Function  ------------
        /**
     *
     * @param searchstring
     * @return
     */
    public List<Occ>SearchForOcc(String searchstring){
    
        return allrecordlist;
    }
    
        //-----------------------------------------------
    
    /**
     *
     */
    public void createNewEmptyRecord() {
        
        if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Occ>();
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
        Occ emptyocc = new Occ();
        
       
        allrecordlist.add(emptyocc);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
        
    }
    
   // ####################### Validation ######################     
        private boolean validateOverlappAtEnd(int depdate){
            return true;
        }
    
        private boolean validateOverlappAtBegin(int depdate){
            return true;
        }
        
        private boolean validateOverlappInMid(int depdate){
            return true;
        }
        
        private  List<Occ>validateRoom(List<Occ>vrooms){
            int cont=0;
            
            do{
              // Remove every record which is after resbooking            
               if(allrecordlist.get(currentRecordNumber).getArrivaldate().before(vrooms.get(cont).getArrivaldate())){
                         if(allrecordlist.get(currentRecordNumber).getDeparturedate().before(vrooms.get(cont).getArrivaldate())){
                                vrooms.remove(cont);
                                cont=0;
                         }
                    }
            
             if(allrecordlist.get(currentRecordNumber).getArrivaldate().after(vrooms.get(cont).getDeparturedate())){
                         if(allrecordlist.get(currentRecordNumber).getDeparturedate().after(vrooms.get(cont).getDeparturedate())){
                                vrooms.remove(cont);
                                cont=0;
                         }
                    }   
             
             
             if(allrecordlist.get(currentRecordNumber).getArrivaldate().equals(vrooms.get(cont).getDeparturedate())){
                            if(allrecordlist.get(currentRecordNumber).getArrivaltime().after(vrooms.get(cont).getDeparturetime())){
                                 vrooms.remove(cont);
                                cont=0; 
                            }
                 }
             
             
             if(allrecordlist.get(currentRecordNumber).getDeparturedate().equals(vrooms.get(cont).getArrivaldate())){
                            if(allrecordlist.get(currentRecordNumber).getDeparturetime().after(vrooms.get(cont).getArrivaltime())){
                                 vrooms.remove(cont);
                                cont=0; 
                            }
                 }
             
             
             
               cont++;
               
               
              }while(cont<vrooms.size());
            
            return vrooms;
              
        }
            
       
        /**
     *
     * @return
     */
    public List<String>CheckForOverlappingReservations(){
             List<Occ>vrooms=new ArrayList<Occ>();
            List<String>overlapping=new ArrayList<String>();
            int count=0;
            if(!tabelIsEmpty){
                    do{
                        if(allrecordlist.get(count).getId()!=null){
                                if(allrecordlist.get(count).getRoom().getCode()==allrecordlist.get(currentRecordNumber).getRoom().getCode()){
                                      vrooms.add(allrecordlist.get(count));
                                }
                        }
                        count++;
                    }while(count<allrecordlist.size());
                 if(!vrooms.isEmpty())
                    validateRoom(vrooms);
                 
            }
               
            
               
            
             if(!vrooms.isEmpty() ){
                 
               if((vrooms.size()==1) ||(tabelIsEmpty) ){
                  return null;   
                }
                 
             int col=0;
             do{
                
                overlapping.add(vrooms.get(col).getRes().getResno());
                col++;  
             }while(col<vrooms.size());
             RefreshAllRecords();
            return overlapping;
        }
             
             return null;
            
        }
        
 //  ###########################   Rec Ops ##############       
        
        private List<String>saveNewRecord(){
            List<Occ>vrooms=new ArrayList<Occ>();
            List<String>overlapping=new ArrayList<String>();
            int count=0;
            if(!tabelIsEmpty){
                    do{
                       if(allrecordlist.get(count).getId()!=null){ 
                                if(allrecordlist.get(count).getRoom().getCode()==allrecordlist.get(currentRecordNumber).getRoom().getCode()){
                                      vrooms.add(allrecordlist.get(count));
                                }
                       }
                        count++;
                    }while(count<allrecordlist.size());

                     if(!vrooms.isEmpty())
                    validateRoom(vrooms);
            }
            
        if((vrooms.size()==0 || vrooms.size()==1) ||(tabelIsEmpty) ){
           
               //------------------------------

                if ( newEmptyRecordCreated=true){
                    try{
                    dbhook.getEntity().getTransaction().begin();
                    dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
                    dbhook.getEntity().getTransaction().commit();
                    
                    newEmptyRecordCreated=false;
                    tabelIsEmpty=false;
                    }
                    catch (Exception e){
                          newEmptyRecordCreated=false;
                          allrecordlist.remove(currentRecordNumber);
                          RefreshAllRecords(); // Reloads List with all Records

                    }
                }
                 return null;
           }
        
        if(vrooms.size()!=0){
             int col=0;
             do{
                overlapping.add(vrooms.get(col).getRes().getResno());
                col++;
             }while(col<vrooms.size());
              RefreshAllRecords();
            return overlapping;
        }
           RefreshAllRecords();
        return overlapping;
        }
    
      private void saveOldRecord(){
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Occ.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
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
    @Override public void saveRecord(){}
    /**
     *
     * @param test
     * @return
     */
    public List<String>saveRecord(boolean test){
         List<String>hh=new ArrayList<String>();
        
         if (newEmptyRecordCreated==true){
            if(!tabelIsEmpty){    // Validate Date only if there are Records
                  hh=saveNewRecord();
                  
                 try {
                    if(hh!=null){
                         if (!hh.isEmpty()) {
                            if (hh.size() == 1) {                            

                                setNewEmptyRecordSaved();
                            }
                            if (hh.size() > 2) {                            
                                RefreshAllRecords(); // Reload Data from Database
                                return hh;

                            }
                        }
                        if (hh.isEmpty()) {
                            setNewEmptyRecordSaved();
                            return null;
                        }
                    }
                } catch (Exception e) {
                       e.printStackTrace();  
                }
                  }
                  
                  
                  
            }
            
            if(tabelIsEmpty){    // Just create a Record if Table is empty
                 saveNewRecord();
                        setNewEmptyRecordSaved();
            }
          
      if (newEmptyRecordCreated==false){
          saveOldRecord();    // Validation for old Record is needed
      }
      
      return null;
    }
    
      /**
     *
     * @return
     */
    public Long getId(){
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return (long) 0;
   }
    
       
       /**
     *
     * @param id
     */
    public void  setDataRecordId(Long id){
        int inl=-1;
        
           try {
               do {
                   
                   currentRecordNumber = inl;
                   
                   inl++;
               } while (allrecordlist.get(currentRecordNumber).getId() != id && allrecordlist.size() - 1 > inl);
               currentRecordNumber = inl;
           } catch (Exception e) {
               e.printStackTrace();   
           }
   }
       
    /**
     *
     * @param id
     * @return
     */
    public Occ getDataRecord(long id){
       int inl=-1;
        
        try {
           do {
               
               
               
               inl++;
           } while (allrecordlist.get(currentRecordNumber).getId() != id && allrecordlist.size() - 1 > inl);
           currentRecordNumber = inl;
       } catch (Exception e) {
           e.printStackTrace();   
       }
      return  allrecordlist.get(currentRecordNumber);
   }    
       
   // ------------------------------------- Getters --------------------- 


    /**
     *
     * @return
     */
    public Date getArrivaltime() {
         // if( tabelIsEmpty!=true) 
              // return allrecordlist.get(currentRecordNumber).getArrivaltime();
        return null;
    }

 

    /**
     *
     * @return
     */
    public Date getDeparturetime() {
        //if( tabelIsEmpty!=true) 
              //return allrecordlist.get(currentRecordNumber).getDeparturetime();
        return null;
    }

   

    /**
     *
     * @return
     */
    public Res getRes() {
        Res test=null;
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRes();
        return test;
    }

    /**
     *
     * @return
     */
    public Address getResguest() {
         Address test=null; 
        //if( tabelIsEmpty!=true) 
              //return allrecordlist.get(currentRecordNumber).getAddress();
        return test;
    }

    /**
     *
     * @return
     */
    public Rooms getRoom() {
         Rooms test=null; 
        if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getRoom();
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
    public Date getDeparturedate() {
        if( tabelIsEmpty!=true)  
        return allrecordlist.get(currentRecordNumber).getDeparturedate();
       return null;
    }
    


    
   

 
   
  

    /**
     *
     * @param res
     */
    public void setRes(Res res) {
        if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRes(res);
    }

    /**
     *
     * @param resguest
     */
    public void setResguest(Address resguest) {
        if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        
           // allrecordlist.get(currentRecordNumber).setResguest(resguest);
    }

    /**
     *
     * @param room
     */
    public void setRoom(Rooms room) {
        if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setRoom(room);
    }

   
    /**
     *
     * @param arrivaldate
     */
    @Override public void setArrivaldate(Date arrivaldate){}
    /**
     *
     * @param arrivaldate
     */
    public void setArrivaldate(String arrivaldate) {
            Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
        try {
            dt=df.parse(arrivaldate);
        } catch (ParseException ex) {
            Logger.getLogger(occbean.class.getName()).log(Level.SEVERE, null, ex);
        }
           if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setArrivaldate(dt);
            setArrivaltime("12:01");
    }

    /**
     *
     * @param departuredate
     */
    @Override public void setDeparturedate(Date departuredate){}
    /**
     *
     * @param departuredate
     */
    public void setDeparturedate(String departuredate) {
         Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy" );
        try {
            dt=df.parse(departuredate);
        } catch (ParseException ex) {
            Logger.getLogger(occbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         if (tabelIsInit==false || tabelIsEmpty!=true)
                if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setDeparturedate(dt);
            setDeparturetime("12:00");
    }

    /**
     *
     * @param arrivaltime
     */
    @Override public void setArrivaltime(Date arrivaltime) {}
    /**
     *
     * @param arrivaltime
     */
    public void setArrivaltime(String arrivaltime){
        Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "HH:mm" );
        try {
            dt=df.parse(arrivaltime);
        } catch (ParseException ex) {
            Logger.getLogger(occbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setArrivaltime(dt);
        
    }

    /**
     *
     * @param departuretime
     */
    @Override public void setDeparturetime(Date departuretime) {}
    /**
     *
     * @param departuretime
     */
    public void setDeparturetime(String departuretime) {
        Date dt = new Date();
            SimpleDateFormat df = new SimpleDateFormat( "HH:mm" );
        try {
            dt=df.parse(departuretime);
        } catch (ParseException ex) {
            Logger.getLogger(occbean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true)createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setDeparturetime(dt);
    }
    
}
