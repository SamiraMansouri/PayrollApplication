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

/**
 *
 * @author apple
 */
public class Student implements IEmployee {
    
    private double EmployeeID;
    private String FirstName;
    private String LastName;
    private String Address;
    private String Phone;
    private float SalaryWage;
    private float Bonus;
    private String payCategory;
    private String Title;
    private Date BonusDate;
    private float Total;
    private Date PayDate;
    
    private float Hours;
    private float PerHour;

    
    
    List<Payment> payments=new ArrayList<Payment>();

    
    public Student()
            {
                setTotal(0);
            }
    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
    
    
    public double getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(double EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public float getSalaryWage() {
        return SalaryWage;
    }

    public void setSalaryWage(float SalaryWage) {
        this.SalaryWage = SalaryWage;
    }

    public float getBonus() {
        return Bonus;
    }

    public void setBonus(float Bonus) {
        this.Bonus = Bonus;
    }

    public String getPayCategory() {
        return payCategory;
    }

    public void setPayCategory(String payCategory) {
        this.payCategory = payCategory;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public Date getBonusDate() {
        return BonusDate;
    }

    public void setBonusDate(Date BonusDate) {
        this.BonusDate = BonusDate;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public Date getPayDate() {
        return PayDate;
    }

    public void setPayDate(Date PayDate) {
        this.PayDate = PayDate;
    }

    public float getHours() {
        return Hours;
    }

    public void setHours(float Hours) {
        this.Hours = Hours;
    }

    public float getPerHour() {
        return PerHour;
    }

    public void setPerHour(float PerHour) {
        this.PerHour = PerHour;
    }

     @Override
       public void CalculateSalary(float hours,Date FromDate,Date Todate)
    {
        Payment payment=new Payment();
        Date toDay=new Date();
        setPayDate(toDay);
        payment.setID(payments.size()+1);
        payment.setFromDate(FromDate);
        payment.setToDate(Todate);
        payment.setPaymentDate(toDay);
        payment.setPerHour(getPerHour());
        
        
        setHours(getHours()+hours);
        payment.Hours=hours;
        payment.PerHour=getSalaryWage();
        
        long Days=Todate.getDate()-FromDate.getDate();
        float TotalPaymentEmount=0;
        
        TotalPaymentEmount=getSalaryWage()*getHours();
        
        float ExtraHours=(hours -((Days/7)*20));
        if (ExtraHours>0) {
            TotalPaymentEmount=(Float)(ExtraHours*(float)1.5*SalaryWage);
        }
        
        payment.setAmount(TotalPaymentEmount);
        setTotal(getTotal()+ TotalPaymentEmount);
        float x=getTotal();
        payments.add(payment);
    }
    
        @Override
           public void Add(double EmployeeID,String FirstName,String LastName,String Address,
                        String Phone,float  SalaryWage,float Bonus,String payCategory,String Title,
                        Date BonusDate)
        {
            setEmployeeID(EmployeeID);
            setFirstName(FirstName);
            setLastName(LastName);
            setAddress(Address);
            setPhone(Phone);
            setSalaryWage(SalaryWage);
            setBonus(Bonus);
            setPayCategory(payCategory);
            setTitle(Title);
            setBonusDate(BonusDate);
        }
}
