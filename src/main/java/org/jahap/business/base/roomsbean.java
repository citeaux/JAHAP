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



package org.jahap.business.base;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.jahap.entities.JahapDatabaseConnector;
import org.jahap.entities.base.Cat;
import org.jahap.entities.base.Location;
import org.jahap.entities.base.Rooms;

/**
 *
 * @author russ
 */
public class roomsbean extends DatabaseOperations  implements rooms_i {
   static Logger log = Logger.getLogger(roomsbean.class.getName());
   JahapDatabaseConnector dbhook;
    private static List<Rooms> allrecordlist; 
    public roomsbean(){
       long testg;
        dbhook = JahapDatabaseConnector.getConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rooms t ORDER BY t.id");
            List<Rooms>allroomslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allroomslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rooms t ORDER BY t.id");
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
         
         log.debug("Function entry RefreshAllRecords");
        try {
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rooms t ORDER BY t.id");
            allrecordlist = query_AllDbRecords.getResultList();
            numberOfLastRecord=allrecordlist.size()-1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
         log.debug("Function exit RefreshAllRecords");
    } 
    
   public void createNewEmptyRecord() {
                    log.debug("Function entry createNewEmptyRecord");
          if(tabelIsEmpty==true){
            allrecordlist = new ArrayList<Rooms>();
            numberOfLastRecord++;
            currentRecordNumber=numberOfLastRecord;
            
        }
        
        if(tabelIsEmpty==false){
            RefreshAllRecords();
            numberOfLastRecord++;
        }
        
               Rooms emptyacc = new Rooms();
        
       
        allrecordlist.add(emptyacc);
        currentRecordNumber=numberOfLastRecord;
       
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected
          log.debug("Function exit createNewEmptyRecord");
        
    }
   
   public List<Rooms>SearchForRooms(String searchstring){
       return allrecordlist;
   }
   
   public void setRoomsinListclean(List<Rooms>rooms){
       log.debug("Function entry setRoomsinListclean ");
       int i,p;
       for(i=0;i<=allrecordlist.size()-1;i++){
           for(p=0;p<=rooms.size()-1;p++){
           
           if(allrecordlist.get(i).getId()==rooms.get(p).getId()){
                      allrecordlist.get(i).setClean(true);
                      currentRecordNumber=i;
                      saveRecord();
                }
           }
       }  
       log.debug("Function exit setRoomsinListclean");
     }
   
   
    public void setRoomsinListdirty(List<Rooms>rooms){
        log.debug("Function entry setRoomsinListdirty");
       int i,p;
       for(i=0;i<=allrecordlist.size()-1;i++){
           for(p=0;p<=rooms.size()-1;p++){
           
           if(allrecordlist.get(i).getId()==rooms.get(p).getId()){
                      allrecordlist.get(i).setClean(false);
                      currentRecordNumber=i;
                      saveRecord();
                }
           }
       }   
        log.debug("Function exit setRoomsinListdirty");
     }
    
    
    
    
    public void setRoomsinListNotunderMaintenance(List<Rooms>rooms){
       log.debug("Function entry setRoomsinListNotunderMaintenance ");
       int i,p;
       for(i=0;i<=allrecordlist.size()-1;i++){
           for(p=0;p<=rooms.size()-1;p++){
           
           if(allrecordlist.get(i).getId()==rooms.get(p).getId()){
                      allrecordlist.get(i).setNo_maintenance(true);
                      currentRecordNumber=i;
                      saveRecord();
                }
           }
       }  
       log.debug("Function exit setRoomsinListNotunderMaintenance");
     }
   
   
    public void setRoomsinListunderMaintenance(List<Rooms>rooms){
        log.debug("Function entry setRoomsinListunderMaintenance");
       int i,p;
       for(i=0;i<=allrecordlist.size()-1;i++){
           for(p=0;p<=rooms.size()-1;p++){
           
           if(allrecordlist.get(i).getId()==rooms.get(p).getId()){
                      allrecordlist.get(i).setNo_maintenance(false);
                      currentRecordNumber=i;
                      saveRecord();
                }
           }
       }   
        log.debug("Function exit setRoomsinListunderMaintenance");
     }
    
   
   private void saveNewRecord(){
       log.debug("Function entry saveNewRecord");
          
        if ( newEmptyRecordCreated==true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            System.out.printf(dbhook.getEntity().getProperties().toString());
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            allrecordlist.clear();
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rooms t ORDER BY t.id"); // Refresh list
            allrecordlist= query_AllDbRecords.getResultList();
            //currentRecordNumber++;
            }
            catch (Exception e){
                  log.error("SaveNewRecord " );
                     e.printStackTrace();
            }
        }
        }
   
    public void  setDataRecordId(Long id){
        int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber=inl;
        } catch (Exception e) {
            e.printStackTrace();  
            
        }
       
   }
    
    public Rooms  getDataRecord(Long id){
        int inl=-1;
        
        try {
            do {
                
                
                
                inl++;
            } while (allrecordlist.get(inl).getId() != id && allrecordlist.size() - 1 > inl);
            currentRecordNumber = inl;
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return allrecordlist.get(currentRecordNumber);
   }
   
   
   
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
   
       private void saveOldRecord(){
       log.debug("Function entry saveOldRecord");
        if(newEmptyRecordCreated==false){
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().find(Rooms.class,allrecordlist.get(currentRecordNumber).getId() );
           dbhook.getEntity().merge(allrecordlist.get(currentRecordNumber));
            
            dbhook.getEntity().getTransaction().commit();
        }
        
           log.debug("Function exit saveOldRecord");
    } 
  
   
   public void quitDBaccess(){
       dbhook.getEntity().close();
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
   
        public void jumpToFirstRecord(){
        currentRecordNumber=0;
    }
    
    /**
     *
     */
    public void jumpToLastRecord(){
        currentRecordNumber=numberOfLastRecord;
    }
    
     public List<Rooms>getCurrentRoom(){
        List<Rooms>hh=new ArrayList<Rooms>();
        hh.add(allrecordlist.get(currentRecordNumber));
        return hh;
    
    }
     
    

          //--------------------- Getters & Setters --------------- 

    public String getName() {
        
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return "";
    }

    public Cat getCategory() {
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCategory();
        return null;
    }

    public String getCode() {
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCode();
        return "";
    }

    public void setCategory(Cat category) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
        
            allrecordlist.get(currentRecordNumber).setCategory(category);
    }

    public void setCode(String code) {
         if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
            allrecordlist.get(currentRecordNumber).setCode(code);
    }

    public void setName(String name) {
         if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();   
        
            allrecordlist.get(currentRecordNumber).setName(name);
    }

   

    @Override
    public Location getLocation() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).getLocation();
       return null;
    }

    @Override
    public boolean isNo_maintenance() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).isNo_maintenance();
       return false;
    }

    @Override
    public boolean isClean() {
        if( tabelIsEmpty!=true)   
         return allrecordlist.get(currentRecordNumber).isClean();
       return false;
    }

   

    @Override
    public void setLocaton(Location location) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
       
        allrecordlist.get(currentRecordNumber).setLocation(location);
    }

    @Override
    public void setClean(boolean clean) {
        if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
         
        allrecordlist.get(currentRecordNumber).setClean(clean);
    }

    @Override
    public void setNo_maintenance(boolean no_maintenance) {
       if (tabelIsInit==false|| tabelIsEmpty==true) createNewEmptyRecord();
         
         
        allrecordlist.get(currentRecordNumber).setNo_maintenance(no_maintenance);
    }
    
}
