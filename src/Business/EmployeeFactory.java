/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author apple
 */
public class EmployeeFactory {
    
    public IEmployee CreateEmployee (String EmployeeType)
    {
        if (EmployeeType==null) {
            return null;
        }
        if ("Student".equalsIgnoreCase(EmployeeType)) {
            return new Student();
        }
        else if ("Staff".equalsIgnoreCase(EmployeeType)) {
            return new Staff();
        }
        else if ("Teacher".equalsIgnoreCase(EmployeeType)) {
            return new Teacher();
        }
        else if ("Volunteer".equalsIgnoreCase(EmployeeType)) {
            return new Volunteer();
        }
        else if ("Executive".equalsIgnoreCase(EmployeeType)) {
            return new Executive();
        }
        return null;
    }
    
}
