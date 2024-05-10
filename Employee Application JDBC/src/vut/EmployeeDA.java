
package vut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 222176415 Themba Ntimane 
 */
public abstract class EmployeeDA {

    private static ArrayList<EmployeePD> arListEmployee = new ArrayList<>();
    private static ArrayList<String> empNumbers = new ArrayList<>();

    private static Connection con;
    private static PreparedStatement ps;
    private static Statement st;
    private static ResultSet rs;

    public static void initializeConnection() {
        final String USERNAME = "root";
        final String PASSWORD = "";
        final String URL = "jdbc:mysql://localhost/employeesdb";
        final String DRIVER = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("DataBase driver not found\n" + e.getMessage());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getSQLState());
        }

    }

    public static void addEmployee(EmployeePD employeeObj) {
        String qry = "INSERT INTO tblemployee VALUES(?,?,?)";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(2, employeeObj.getName());
            ps.setString(1, employeeObj.getEmployeeNumber());
            ps.setDouble(3, employeeObj.getSalary());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(employeeObj.getName() + " Not Added\n" + e.getMessage());
        }
    }

    public static ArrayList<EmployeePD> getAll() {

        arListEmployee.clear();
        String query = "SELECT * FROM tblemployee";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                arListEmployee.add(new EmployeePD(rs.getString(2), rs.getString(1), rs.getDouble(3)));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Data retrived.\n" + e.getMessage());
        }

        return arListEmployee;
    }

    public static void IncreaseSalaries(double perc) {
        String qry = "UPDATE tblemployee SET Salary = Salary + (Salary *('" + perc / 100 + "'))";
        try {
            st = con.createStatement();
            st.executeUpdate(qry);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static void removeEmployee(String employeeNumber) {
        String qry = "DELETE FROM tblemployee WHERE Employee_Number = ?";
        try {
            ps = con.prepareStatement(qry);
            ps.setString(1, employeeNumber);
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    
    //..............................................................................

    public static ArrayList<String> getEmpNumbers() {
        empNumbers.clear();
        String query = "SELECT Employee_Number FROM tblemployee";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                empNumbers.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("No Data retrieved.\n" + e.getMessage());
        }
        return empNumbers;
    }
    
    public static EmployeePD getEmployeeObj(String empNo) {
        EmployeePD employee = null;
        String query = "SELECT * FROM tblemployee WHERE Employee_Number = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, empNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                employee = new EmployeePD(rs.getString(2), rs.getString(1), rs.getDouble(3));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Employee with number " + empNo + " not found.\n" + e.getMessage());
        }
        return employee;
    }
    
    public static double calcAverageSalary() {
        double avgSalary = 0.0;
        String query = "SELECT AVG(Salary) FROM tblemployee";
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                avgSalary = rs.getDouble(1);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("Failed to calculate average salary.\n" + e.getMessage());
        }
        return avgSalary;
    }

}
