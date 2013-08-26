/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jahap.business.acc;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.jahap.entities.Accounts;
import org.jahap.entities.BillPos;
import org.jahap.entities.Rates;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author russ
 */
public class accountspositionbeanTest {
    
    accountspositionbean instance;
    
    
    public accountspositionbeanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of SearchForOcc method, of class accountspositionbean.
     */
    
 

    /**
     * Test of createNewEmptyRecord method, of class accountspositionbean.
     */
    @Test
    public void testCreateNewEmptyRecord() {
        System.out.println("createNewEmptyRecord");
        instance = new accountspositionbean();
        instance.createNewEmptyRecord();
        
        instance.setAmount(10.00);
        instance.setBilled(true);
        instance.setDebit(false);
        instance.setPositionname("Logis");
        instance.saveRecord();
        instance.quitDBaccess();
        // TODO review the generated test code and remove the default call to fail.
       
    }

    
    @Test
    public void testaddPosition(){
         System.out.println("addPosition");
        
    }
    
    
    
    /**
     * Test of nextRecordBackward method, of class accountspositionbean.
     */
    
    public void testNextRecordBackward() {
        System.out.println("nextRecordBackward");
        accountspositionbean instance = new accountspositionbean();
        instance.nextRecordBackward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextRecordForeward method, of class accountspositionbean.
     */
   
    public void testNextRecordForeward() {
        System.out.println("nextRecordForeward");
        accountspositionbean instance = new accountspositionbean();
        instance.nextRecordForeward();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveRecord method, of class accountspositionbean.
     */
    
    public void testSaveRecord() {
        System.out.println("saveRecord");
        accountspositionbean instance = new accountspositionbean();
        instance.saveRecord();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quitDBaccess method, of class accountspositionbean.
     */
  
    public void testQuitDBaccess() {
        System.out.println("quitDBaccess");
        accountspositionbean instance = new accountspositionbean();
        instance.quitDBaccess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccount method, of class accountspositionbean.
     */
    
    public void testGetAccount() {
        System.out.println("getAccount");
        accountspositionbean instance = new accountspositionbean();
        Accounts expResult = null;
        Accounts result = instance.getAccount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAmount method, of class accountspositionbean.
     */
    
    public void testGetAmount() {
        System.out.println("getAmount");
        accountspositionbean instance = new accountspositionbean();
        double expResult = 0.0;
        double result = instance.getAmount();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArticle method, of class accountspositionbean.
     */
    
    public void testGetArticle() {
        System.out.println("getArticle");
        accountspositionbean instance = new accountspositionbean();
        BigInteger expResult = null;
        BigInteger result = instance.getArticle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBillPosCollection method, of class accountspositionbean.
     */

    public void testGetBillPosCollection() {
        System.out.println("getBillPosCollection");
        accountspositionbean instance = new accountspositionbean();
        Collection expResult = null;
        Collection result = instance.getBillPosCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBilled method, of class accountspositionbean.
     */
    
    public void testGetBilled() {
        System.out.println("getBilled");
        accountspositionbean instance = new accountspositionbean();
        boolean expResult = false;
        boolean result = instance.getBilled();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDebit method, of class accountspositionbean.
     */
    
    public void testGetDebit() {
        System.out.println("getDebit");
        accountspositionbean instance = new accountspositionbean();
        boolean expResult = false;
        boolean result = instance.getDebit();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class accountspositionbean.
     */
   
    public void testGetId() {
        System.out.println("getId");
        accountspositionbean instance = new accountspositionbean();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPositionname method, of class accountspositionbean.
     */
   
    public void testGetPositionname() {
        System.out.println("getPositionname");
        accountspositionbean instance = new accountspositionbean();
        String expResult = "";
        String result = instance.getPositionname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRate method, of class accountspositionbean.
     */
   
    public void testGetRate() {
        System.out.println("getRate");
        accountspositionbean instance = new accountspositionbean();
        Rates expResult = null;
        Rates result = instance.getRate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRatedate method, of class accountspositionbean.
     */
   
    public void testGetRatedate() {
        System.out.println("getRatedate");
        accountspositionbean instance = new accountspositionbean();
        Date expResult = null;
        Date result = instance.getRatedate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRevenueCollection method, of class accountspositionbean.
     */
    
    public void testGetRevenueCollection() {
        System.out.println("getRevenueCollection");
        accountspositionbean instance = new accountspositionbean();
        Collection expResult = null;
        Collection result = instance.getRevenueCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccount method, of class accountspositionbean.
     */
    
    public void testSetAccount() {
        System.out.println("setAccount");
        Accounts account = null;
        accountspositionbean instance = new accountspositionbean();
        instance.setAccount(account);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAmount method, of class accountspositionbean.
     */
   
    public void testSetAmount() {
        System.out.println("setAmount");
        double amount = 0.0;
        accountspositionbean instance = new accountspositionbean();
        instance.setAmount(amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArticle method, of class accountspositionbean.
     */
    
   

    /**
     * Test of setBillPosCollection method, of class accountspositionbean.
     */
    
    public void testSetBillPosCollection() {
        System.out.println("setBillPosCollection");
        Collection<BillPos> billPosCollection = null;
        accountspositionbean instance = new accountspositionbean();
        instance.setBillPosCollection(billPosCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBilled method, of class accountspositionbean.
     */
    
    public void testSetBilled() {
        System.out.println("setBilled");
        boolean billed = false;
        accountspositionbean instance = new accountspositionbean();
        instance.setBilled(billed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDebit method, of class accountspositionbean.
     */
   
    public void testSetDebit() {
        System.out.println("setDebit");
        boolean debit = false;
        accountspositionbean instance = new accountspositionbean();
        instance.setDebit(debit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPositionname method, of class accountspositionbean.
     */
   
    public void testSetPositionname() {
        System.out.println("setPositionname");
        String positionname = "";
        accountspositionbean instance = new accountspositionbean();
        instance.setPositionname(positionname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRate method, of class accountspositionbean.
     */
    
    public void testSetRate() {
        System.out.println("setRate");
        Rates rate = null;
        accountspositionbean instance = new accountspositionbean();
        instance.setRate(rate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRatedate method, of class accountspositionbean.
     */
    
    public void testSetRatedate() {
        System.out.println("setRatedate");
        Date ratedate = null;
        accountspositionbean instance = new accountspositionbean();
        instance.setRatedate(ratedate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
