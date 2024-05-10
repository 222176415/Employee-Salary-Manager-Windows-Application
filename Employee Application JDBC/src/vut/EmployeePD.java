
package vut;

import java.util.ArrayList;

/**
 *
 * @author 222176415 Themba Ntimane 
 */
public class EmployeePD {

    private String name;
    private String employeeNumber;
    private double salary;

    public EmployeePD() {
        this("aaaaa", "000000", 10000.0);
    }

    public EmployeePD(String name, String employeeNumber, double salary) {
        setName(name);
        setEmployeeNumber(employeeNumber);
        setSalary(salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 5) {
            throw new IllegalArgumentException("Oops!, Name must be at least 5 characters.");
        } else {
            this.name = name;
        }
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        if (employeeNumber.length() != 6) {
            throw new IllegalArgumentException("Oops!, The employee number must be 6 characters.");
        } else {
            this.employeeNumber = employeeNumber;
        }
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 10000) {
            throw new IllegalArgumentException("Oops!, Salary must be R10000 or more.");
        } else {
            this.salary = salary;
        }
    }

    @Override
    public String toString() {
        return employeeNumber + "\t" + name + "\t" + "R" + salary + "\n";
    }

    public void initializeConnection() {
        EmployeeDA.initializeConnection();
    }

    public void addEmployee(EmployeePD employeeObj) {
        EmployeeDA.addEmployee(employeeObj);
    }

    public ArrayList<EmployeePD> getAll() {
        return EmployeeDA.getAll();
    }
    
    public void IncreaseSalaries(double perc){
        EmployeeDA.IncreaseSalaries(perc);
    }

    public void removeEmployee(String employeeNumber) {
        EmployeeDA.removeEmployee(employeeNumber);
    }
    
    
    //.........................................................
      public ArrayList<String> getEmpNumbers() {
        return EmployeeDA.getEmpNumbers();
    }

    public EmployeePD getEmployeeObj(String empNo) {
        return EmployeeDA.getEmployeeObj(empNo);
    }

    public double calcAverageSalary() {
        return EmployeeDA.calcAverageSalary();
    }

}
