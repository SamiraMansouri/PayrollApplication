/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apple
 */
public class StudentTest {
    
    public StudentTest() {
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
     * Test of getPayments method, of class Student.
     */
    @Test
    public void testGetPayments() {
        System.out.println("getPayments");
        Student instance = new Student();
        Payment e=new Payment();
        List<Payment> expResult = new ArrayList<Payment>();
        expResult.add(e);
        instance.payments.add(e);
        
        List<Payment> result = instance.getPayments();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPayments method, of class Student.
     */
    @Test
    public void testSetPayments() {
        System.out.println("setPayments");
        List<Payment> payments = null;
        Student instance = new Student();
        instance.setPayments(payments);
    }

    /**
     * Test of getEmployeeID method, of class Student.
     */
    @Test
    public void testGetEmployeeID() {
        System.out.println("getEmployeeID");
        Student instance = new Student();
        instance.setEmployeeID(10);
        double expResult = 10.0;
        double result = instance.getEmployeeID();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setEmployeeID method, of class Student.
     */
    @Test
    public void testSetEmployeeID() {
        System.out.println("setEmployeeID");
        double EmployeeID = 10.0;
        Student instance = new Student();
        instance.setEmployeeID(EmployeeID);
        assertEquals(instance.getEmployeeID(), EmployeeID,0.0);
    }

    /**
     * Test of getFirstName method, of class Student.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Student instance = new Student();
        instance.setFirstName("samira");
        String expResult = "samira";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Student.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String FirstName = "samira";
        Student instance = new Student();
        instance.setFirstName(FirstName);
        assertEquals(instance.getFirstName(), FirstName);
    }

    /**
     * Test of getLastName method, of class Student.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Student instance = new Student();
        instance.setLastName("Mansouri");
        String expResult = "Mansouri";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class Student.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String LastName = "Mansouri";
        Student instance = new Student();
        instance.setLastName(LastName);
        assertEquals(instance.getLastName(), LastName);
    }

    /**
     * Test of getAddress method, of class Student.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Student instance = new Student();
        instance.setAddress("111 Norway street");
        String expResult = "111 Norway street";
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAddress method, of class Student.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String Address = "111 Norway street";
        Student instance = new Student();
        instance.setAddress(Address);
        assertEquals(instance.getAddress(), Address);
    }

    /**
     * Test of getPhone method, of class Student.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        Student instance = new Student();
        instance.setPhone("6176500744");
        String expResult = "6176500744";
        String result = instance.getPhone();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPhone method, of class Student.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String Phone = "6176500744";
        Student instance = new Student();
        instance.setPhone(Phone);
        assertEquals(instance.getPhone(), Phone);
    }

    /**
     * Test of getSalaryWage method, of class Student.
     */
    @Test
    public void testGetSalaryWage() {
        System.out.println("getSalaryWage");
        Student instance = new Student();
        instance.setSalaryWage(10);
        float expResult = 10.0F;
        float result = instance.getSalaryWage();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setSalaryWage method, of class Student.
     */
    @Test
    public void testSetSalaryWage() {
        System.out.println("setSalaryWage");
        float SalaryWage = 10.0F;
        Student instance = new Student();
        instance.setSalaryWage(SalaryWage);
        assertEquals(instance.getSalaryWage(), SalaryWage,0.0);
    }

    /**
     * Test of getBonus method, of class Student.
     */
    @Test
    public void testGetBonus() {
        System.out.println("getBonus");
        Student instance = new Student();
        instance.setBonus(10);
        float expResult = 10.0F;
        float result = instance.getBonus();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setBonus method, of class Student.
     */
    @Test
    public void testSetBonus() {
        System.out.println("setBonus");
        float Bonus = 10.0F;
        Student instance = new Student();
        instance.setBonus(Bonus);
        assertEquals(instance.getBonus(), Bonus,0.0);
    }

    /**
     * Test of getPayCategory method, of class Student.
     */
    @Test
    public void testGetPayCategory() {
        System.out.println("getPayCategory");
        Student instance = new Student();
        instance.setPayCategory("Salary");
        String expResult = "Salary";
        String result = instance.getPayCategory();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPayCategory method, of class Student.
     */
    @Test
    public void testSetPayCategory() {
        System.out.println("setPayCategory");
        String payCategory = "";
        Student instance = new Student();
        instance.setPayCategory(payCategory);
        assertEquals(instance.getPayCategory(), payCategory);
    }

    /**
     * Test of getTitle method, of class Student.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Student instance = new Student();
        instance.setTitle("Student");
        String expResult = "Student";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Student.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String Title = "";
        Student instance = new Student();
        instance.setTitle(Title);
        assertEquals(instance.getTitle(), Title);
    }

    /**
     * Test of getBonusDate method, of class Student.
     */
    @Test
    public void testGetBonusDate() {
        System.out.println("getBonusDate");
        Student instance = new Student();
        
        Date expResult = null;
        Date result = instance.getBonusDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBonusDate method, of class Student.
     */
    @Test
    public void testSetBonusDate() {
        System.out.println("setBonusDate");
        Date BonusDate = null;
        Student instance = new Student();
        instance.setBonusDate(BonusDate);
        assertEquals(instance.getBonusDate(), BonusDate);
    }

    /**
     * Test of getTotal method, of class Student.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        Student instance = new Student();
        instance.setTotal(100);
        float expResult = 100.0F;
        float result = instance.getTotal();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTotal method, of class Student.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        float Total = 100.0F;
        Student instance = new Student();
        instance.setTotal(Total);
        assertEquals(instance.getTotal(), Total,0.0);
    }

    /**
     * Test of getPayDate method, of class Student.
     */
    @Test
    public void testGetPayDate() {
        System.out.println("getPayDate");
        Student instance = new Student();
        Date expResult = null;
        Date result = instance.getPayDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPayDate method, of class Student.
     */
    @Test
    public void testSetPayDate() {
        System.out.println("setPayDate");
        Date PayDate = null;
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
       
        try {
            
             PayDate =simpleDateFormat.parse("11/01/2015 21:30");
        }
        catch(Exception e)
        {
            
        }
        Student instance = new Student();
        instance.setPayDate(PayDate);
        assertEquals(instance.getPayDate(), PayDate);
    }

    /**
     * Test of getHours method, of class Student.
     */
    @Test
    public void testGetHours() {
        System.out.println("getHours");
        Student instance = new Student();
        instance.setHours(10.0F);
        float expResult = 10.0F;
        float result = instance.getHours();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setHours method, of class Student.
     */
    @Test
    public void testSetHours() {
        System.out.println("setHours");
        float Hours = 20.0F;
        Student instance = new Student();
        instance.setHours(Hours);
        assertEquals(instance.getHours(), Hours,0.0);
    }

    /**
     * Test of getPerHour method, of class Student.
     */
    @Test
    public void testGetPerHour() {
        System.out.println("getPerHour");
        Student instance = new Student();
        instance.setPerHour(30.0F);
        float expResult = 30.0F;
        float result = instance.getPerHour();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPerHour method, of class Student.
     */
    @Test
    public void testSetPerHour() {
        System.out.println("setPerHour");
        float PerHour = 220.0F;
        Student instance = new Student();
        instance.setPerHour(PerHour);
        assertEquals(instance.getPerHour(), PerHour,0.0);
    }

    /**
     * Test of CalculateSalary method, of class Student.
     */
    @Test
    public void testCalculateSalary() {
        System.out.println("CalculateSalary");
        float hours = 10.0F;
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date FromDate =null;
        Date Todate =null;
        try {
            
             FromDate =simpleDateFormat.parse("11/01/2015");
             Todate = simpleDateFormat.parse("11/13/2015 ");
        }
        catch(Exception e)
        {
            
        }
        Student instance = new Student();
        instance.setSalaryWage(21);
        instance.CalculateSalary(hours, FromDate, Todate);
        
        assertEquals(instance.getPayments().get(0).getAmount(), 210.0,0.0);
        
        Payment payment=instance.getPayments().get(0);
        assertEquals(payment.Bonus, 0.0,0.0);
        assertEquals(payment.FromDate, FromDate);
        assertEquals(payment.ToDate, Todate);
        assertEquals(payment.Hours, 10,0.0);
        assertEquals(payment.ID, 1,0.0);
  //      assertEquals(payment.PaymentDate, new Date());
        assertEquals(payment.Hours,10,0.0);
        assertEquals(payment.PerHour,21,0.0);
        assertEquals(payment.amount, 210,0.0);
        
    }

    /**
     * Test of Add method, of class Student.
     */
    @Test
    public void testAdd() {
        System.out.println("Add");
        double EmployeeID = 1.0;
        String FirstName = "Samira ";
        String LastName = "Mansouri";
        String Address = "111Norway Street";
        String Phone = "6176500744";
        float SalaryWage = 1000.0F;
        float Bonus = 10.0F;
        String payCategory = "Salary";
        String Title = "Executive";
        Date BonusDate =new Date();
        Student instance = new Student();
        instance.Add(EmployeeID, FirstName, LastName, Address, Phone, SalaryWage, Bonus, payCategory, Title, BonusDate);
        
        assertEquals(instance.getEmployeeID(), EmployeeID,0.0);
        assertEquals(instance.getFirstName(), FirstName);
        assertEquals(instance.getLastName(), LastName);
        assertEquals(instance.getAddress(), Address);
        assertEquals(instance.getPhone(), Phone);
        assertEquals(instance.getSalaryWage(), SalaryWage,0.0);
        assertEquals(instance.getBonus(), Bonus,0.0);
        assertEquals(instance.getPayCategory(), payCategory);
        assertEquals(instance.getTitle(), Title);
        
       Student st=new Student();
       Student t=new Student();
        assertSame(st, t);
        
    }
    
}
