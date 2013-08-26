/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.entities;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author russ
 * 
 * - Hier muss ein Singelton einfügt werden, so dass nur eine Instanz des DB objekt gebildet werden kann.
 */
public final class JahapDatabaseConnector {

   
     private static final String PERSISTENCE_UNIT_NAME = "jahap";
  private static EntityManagerFactory factory;
    private static EntityManager EntManager;
    private static JahapDatabaseConnector databaseConnector;

    public JahapDatabaseConnector() {
        factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntManager=factory.createEntityManager();
    }
    
    
  
    
    
   
    public EntityManager getEntity(){
        
        return EntManager;
       
    }
    
}
