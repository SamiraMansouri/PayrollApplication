/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author apple
 */
public interface IEmployee {
     enum  PayCategory {
        Salary,
        HourlyFullTime,
        HourlyPartTime,
        SalariedAndBonus,
        Voluntry 
    }
  
     enum  Titles {
        Student,
        Teacher,
        Staff,
        Executive,
        
    }
     
      public List<Payment> getPayments() ;

    public void setPayments(List<Payment> payments) ;
    
    public float getHours();

    public void setHours(float Hours) ;

    public float getPerHour() ;

    public void setPerHour(float PerHour) ;
     
    public double getEmployeeID() ;

    public void setEmployeeID(double EmployeeID) ;

    public String getFirstName() ;

    public void setFirstName(String FirstName) ;

    public String getLastName();

    public void setLastName(String LastName);

    public String getAddress() ;

    public void setAddress(String Address);

    public String getPhone() ;

    public void setPhone(String Phone);

    public float getSalaryWage() ;

    public void setSalaryWage(float SalaryWage);

    public float getBonus() ;

    public void setBonus(float Bonus);

    public String getPayCategory() ;

    public void setPayCategory(String payCategory) ;

    public String getTitle() ;

    public void setTitle(String Title);

    public Date getBonusDate() ;

    public void setBonusDate(Date BonusDate);

    public float getTotal();

    public void setTotal(float Total) ;

    public Date getPayDate() ;

    public void setPayDate(Date PayDate);

    public void CalculateSalary(float hours,Date FromDate,Date Todate);
    
    public void Add(double EmployeeID,String FirstName,String LastName,String Address,
                    String Phone,float  SalaryWage,float Bonus,String payCategory,String Title,
                    Date BonusDate);
    
}
