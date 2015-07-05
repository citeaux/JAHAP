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

package org.jahap;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.jahap.gui.LogonGuiController;
import org.jahap.i18n.Resourcen;
import org.jahap.i18n.ResourcenManager;

public class MainApp  extends Application implements MainEventListener{
   static Logger log = Logger.getLogger(MainApp.class.getName());
    //private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private MainEventResult mEv;
    public static void main(String[] args)  throws Exception {
        
        launch(args);
        
    }
    ResourcenManager jk; 
    public void idinfo(MainEvent e) {
	    log.debug("Function entry idinfo ");
        // ResourcenManager
	    Resourcen kk= new Resourcen();
	    jk=kk.getResourcenManager();
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
      if(e.isIsSuccessFull()==true){ 
           Stage stage = new Stage();
        String  fxmlFile = "/fxml/Maingui.fxml";
	
	URL fxmlURL = classLoader.getResource("fxml/Maingui.fxml");
        //log.debug("Loading FXML for main view from: {}", fxmlFile);
         FXMLLoader loader = new FXMLLoader(fxmlURL,jk.getFxResourceBundle("fxml.i18n.Maingui"));
        AnchorPane page = null;

          try {
              page = (AnchorPane) loader.load();
              //log.debug("Showing JFX scene");
          } catch (IOException ex) {
              java.util.logging.Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
          }
         
         
         Scene scene = new Scene(page);

        
        stage.setScene(scene);
        stage.show();
	      log.debug("Function exit idinfo");
      }
    }
    
    public void start(Stage stage) throws Exception {
	    log.debug("Function entry start");
        mEv = new MainEventResult();
        mEv.addIDListener(this);
        //log.info("Starting Hello JavaFX and Maven demonstration application");
       
       String   fxmlFile = "fxml/LogonGui.fxml";
        //log.debug("Loading FXML for main view from: {}", fxmlFile);
        FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource(fxmlFile));
	log.debug("after loader");
	
	
        Parent rootNode = (Parent) loader.load();

        //log.debug("Showing JFX scene");
        Scene scene = new Scene(rootNode);
         
         LogonGuiController controller= loader.<LogonGuiController>getController();
       controller.init(mEv);
        
        stage.setScene(scene);
        
     stage.show();
        
       log.debug("Function exit start");
        
    }
}
