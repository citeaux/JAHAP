package org.jahap.business.res;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import org.jahap.business.base.Hotelbean;
import org.jahap.business.base.roomsbean;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Rooms;
import org.jahap.entities.res.Housekeepingblock;
import org.jahap.entities.res.Occ;
import org.jahap.entities.views.Housekeeping;


/*
 * The MIT License
 *
 * Copyright 2014 Open Jahap Community.
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


/**
 *
 * @author russ
 */


public class HousekeepingBean  implements housekeeping_i {

 JahapDatabaseConnector dbhook;
	


    int currentRecordNumber=0;
     int numberOfLastRecord;
     Query query_AllDbRecords;
    boolean newEmptyRecordCreated=false;
     boolean tabelIsEmpty=true; 
     private occbean occbean;
     private roomsbean rbean;
    boolean tabelIsInit=false; // Set Tabel iniated - List is connected
    private static List<Housekeepingblock> allrecordlist;
     static Logger log = Logger.getLogger(HousekeepingBean.class.getName());

    /**
     *
     */
    public HousekeepingBean(){
       
         log.debug("Function entry HousekeepingBean");
        long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         occbean = new occbean();
         rbean=new roomsbean();
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Housekeepingblock t ORDER BY t.id");
            List<HousekeepingBean>alladdresseslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= alladdresseslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=0;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from  Housekeepingblock t ORDER BY t.id");
            allrecordlist= query_AllDbRecords.getResultList();
        
        try {
            testg=allrecordlist.get(currentRecordNumber).getId();
            tabelIsEmpty=false;
            tabelIsInit=true;
        } catch (Exception e) {
              tabelIsEmpty=true;
        }
    
          
        log.debug("Function entry billbean");    
        
    }
    
    public List<Housekeeping>getHousekeepingOverview(){
	   Query queryView;
	   List<Housekeeping>allrooms=null;
	   int lines=0;
	   try {
           
            queryView = dbhook.getEntity().createQuery("select t from Housekeeping t ORDER BY t.code");
            allrooms= queryView.getResultList();
	    lines=allrooms.size()-1;
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
	   if(lines<0){
		   return null;
	   }    
        }
	return allrooms;  
    }
    
   
    public void jumpToFirstRecord(){
        currentRecordNumber=0;
    }    
     public void jumpToLastRecord(){
        currentRecordNumber=numberOfLastRecord;
    }

     public List<Occ>getHousekeepingblocksForThisRoom(long roomid){
	     
	     
	     return null;     
     }
     
    public List<Housekeepingblock>SearchForHousekeepingBlock(String searchstring){
        
         log.debug("Function entry SearchForHousekeepingBlock");
      
         
        
        log.debug("Function exit SearchForHousekeepingBlock ");
        return allrecordlist;
    }  
    
    
   
    public void adjustHousekeepingBlock(LocalDate from, LocalDate to, long roomid, String comment,long blockid){
	    
	    
	    
    }
    
    
    public String newHouskeepingBlock(LocalDate from, LocalDate to, long roomid, String comment){
	    log.debug("Function entry newHouskeepingBlock");
	    Hotelbean hotelbean=new Hotelbean();
	    createNewEmptyRecord();
	    this.setComment(comment);
	    occbean.createNewEmptyRecord();
	    log.trace(from);
	    log.trace(to);
	    
	    occbean.setArrivaldate(Date.from(from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
	    log.trace("set Departuredate");
	    occbean.setDeparturedate(Date.from(to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
	    log.trace("setRoom");
	    occbean.setRoom(rbean.getDataRecord(roomid));
	    log.trace("init overlaplist");
	    occbean.setGuest(hotelbean.getHotelAdress());  // the guestadress in the occtable must not be null
	    List<String>overlaps=new ArrayList<String>();
	    log.trace("get overlap");
	    overlaps=occbean.CheckForOverlappingReservations();
	    if(overlaps==null){
		    log.debug("overlaps== null");
		    this.saveRecord();
		    occbean.setHousekeepingblock(this.getLastPosition());
		    overlaps=occbean.saveRecord(true);
		    
	    }else if(overlaps!=null){
		    log.debug("overlaps!=null");
		    String Test=overlaps.get(1);
		    if(overlaps.size()>=1){
                            
                            int i;
                            for (i=0;i==overlaps.size();i++){
                               Test=Test+ ", " +overlaps.get(i);
                            }
		    }
		    log.debug("Room blockt at reservation: " + Test);
		    return "Room blockt at reservation: " + Test; 
	    }
	    
	    
	   return ""; 
    }
    
    public String newHouskeepingBlock(LocalDate from, LocalDate to, Rooms room, String comment){
	       createNewEmptyRecord();
	    this.setComment(comment);
	    occbean.createNewEmptyRecord();
	    occbean.setArrivaldate(Date.from(from.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
	    occbean.setDeparturedate(Date.from(to.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
	    occbean.setRoom(room);
	    List<String>overlaps=new ArrayList<String>();
	    overlaps=occbean.CheckForOverlappingReservations();
	    if(overlaps==null){
		    this.saveRecord();
		    occbean.setHousekeepingblock(this.getLastPosition());
		    overlaps=occbean.saveRecord(true);
		    
	    }else if(overlaps!=null){
		    String Test=overlaps.get(1);
		    if(overlaps.size()>=1){
                            
                            int i;
                            for (i=0;i==overlaps.size();i++){
                               Test=Test+ ", " +overlaps.get(i);
                            }
		    }
		    return "Room blockt bei reservation: " + Test; 
	    }
	    
	    
	   return "";
    }
    
    
    
       public void createNewEmptyRecord() {
          
          log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Housekeepingblock>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               Housekeepingblock emptyacc = new Housekeepingblock();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
       
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
    }
     
     
    
    
          void setNewEmptyRecordCreadted() {
        this.newEmptyRecordCreated = true;
        this.tabelIsEmpty=false;
    }
    
    void setNewEmptyRecordSaved(){
        this.newEmptyRecordCreated = false;
    }
     
     
    

    @Override
    public void nextRecordBackward() {
         log.debug("Function entry nextRecordBackward");
        
       if (currentRecordNumber>0) {
            currentRecordNumber--;
        }  
        log.debug("Function exit nextRecordBackward");
    }

    @Override
    public void nextRecordForeward() {
        log.debug("Function entry nextRecordForeward");
        
       if (currentRecordNumber<numberOfLastRecord) {
            currentRecordNumber++;
        }    
       
        log.debug("Function exit nextRecordForeward ");
    }

    @Override
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
        log.debug("Function exit saveRecord ");
    }

    private void RefreshAllRecords(){
         
         log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Housekeepingblock t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    }
    
    
     public Housekeepingblock getDataRecord(long id){
        if(id==0)return null;
        log.debug("Function entry getDataRecord");
       int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
            return null;
        }
        
        log.debug("Function exit getDataRecord " + String.valueOf(currentRecordNumber) );
        return allrecordlist.get(currentRecordNumber);
        
   }
    
    public Housekeepingblock getLastPosition(){
          log.debug("Function entry getLastPosition(");
             if( tabelIsEmpty!=true){ 
                 log.debug("Function exit getLastPosition");   
              return allrecordlist.get(currentRecordNumber);
             }
             log.debug("Function exit getLastPosition with Null");
        return null;
    }
     
    
     private void saveNewRecord(){
          log.debug("Function entry saveNewRecord");
          
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Housekeepingblock t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
     }
    
     
     

    public void quitDBaccess() {
       log.debug("Function entry quitDBaccess");
       dbhook.getEntity().close();
       
           log.debug("Function exit quitDBaccess");
    }

     private void saveOldRecord(){
           log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated=false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().refresh(dbhook.getEntity().find(Housekeepingblock.class,allrecordlist.get(currentRecordNumber).getId() ));
            
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    }

	@Override
	public String getComment() {
		if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getComment();
        return "";
	}

	@Override
	public Long getId() {
		if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getId();
        return null;
	}

	@Override
	public String getName() {
		if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return "";
		
		
	}

	@Override
	public void setComment(String comment) {
		if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setComment(comment);
		
		
	}

	@Override
	public void setName(String name) {
		if(tabelIsInit==false|| tabelIsEmpty==true)
            createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setName(name);
		
		
	}
    
    
    
    
    
    




}
