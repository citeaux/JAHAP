/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.base;
import java.util.ArrayList;
import java.util.List;

import org.jahap.entities.*;

/**
 *
 * @author russ
 */
public class roomsbean extends DatabaseOperations  implements rooms_i {

   JahapDatabaseConnector dbhook;
    private static List<Rooms> allrecordlist; 
    public roomsbean(){
       long testg;
        dbhook = new JahapDatabaseConnector();
         
         
        try {
           
            query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rooms t");
            List<Rooms>allroomslist= query_AllDbRecords.getResultList();
            numberOfLastRecord= allroomslist.size()-1;
        } catch (Exception e) {
            numberOfLastRecord=-1;
        }
        
        query_AllDbRecords = dbhook.getEntity().createQuery("select t from Rooms t");
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
    
    
    
   public void createNewEmptyRecord() {
                   if(numberOfLastRecord==-1){
            allrecordlist = new ArrayList();
            numberOfLastRecord++;
        }
        
         if(numberOfLastRecord>-1){
             numberOfLastRecord++;
         }
        Rooms emptyroom = new Rooms();
        
       
        allrecordlist.add(emptyroom);
        currentRecordNumber=numberOfLastRecord;
        setNewEmptyRecordCreadted();
        tabelIsInit=true; // Set Tabel iniated - List is connected     
        
    }
   
   public List<Rooms>SearchForRooms(String searchstring){
       return allrecordlist;
   }
   
   private void saveNewRecord(){
        if ( newEmptyRecordCreated=true){
            try{
            dbhook.getEntity().getTransaction().begin();
            dbhook.getEntity().persist(allrecordlist.get(currentRecordNumber));
            dbhook.getEntity().getTransaction().commit();
            newEmptyRecordCreated=false;
            }
            catch (Exception e){
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
            dbhook.getEntity().find(Rooms.class,allrecordlist.get(currentRecordNumber).getId() );
            
            
            dbhook.getEntity().getTransaction().commit();
        }
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
    
     
    

          //--------------------- Getters & Setters --------------- 

    public String getName() {
        
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getName();
        return "";
    }

    public String getCategory() {
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCategory();
        return "";
    }

    public String getCode() {
          if( tabelIsEmpty!=true) 
              return allrecordlist.get(currentRecordNumber).getCode();
        return "";
    }

    public void setCategory(String category) {
       if(tabelIsInit==false|| tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true){createNewEmptyRecord();
            }        
            allrecordlist.get(currentRecordNumber).setCategory(category);
    }

    public void setCode(String code) {
        if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true){createNewEmptyRecord();
            }    
            allrecordlist.get(currentRecordNumber).setCode(code);
    }

    public void setName(String name) {
        if(tabelIsInit==false || tabelIsEmpty!=true)
            if(newEmptyRecordCreated!=true){createNewEmptyRecord();
            }    
        
            allrecordlist.get(currentRecordNumber).setName(name);
    }
    
}
