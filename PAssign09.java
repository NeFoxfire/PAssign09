/**
 * File: PAssign09.java
 * Class: CSCI 1302
 * Author: Gordon Chicowlas
 * Created On: April 24, 2022
 * Last Modified: April 27, 2022
 * Description: Create a program to read in a data file containing employee information, establishes an 
 * 					Employee class to process the employees information and organize it and then writes 
 * 					the processed employee as an object to a new data file.
 */
package pAssign09;

import java.io.*;
import java.util.ArrayList;

public class PAssign09 {
	public static void main(String[] args) {
		//array list for organizing the employee objects that are created
		ArrayList<Employee> employeeList = new ArrayList<>();
		
		//input process for reading information from employee.dat and creating employee objects
		try(
				DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("src/pAssign09/employeeData.dat")));
			) {
			
			while (input.available() > 0) {
				String employee = input.readUTF();
				double salary = input.readDouble();
				double years = input.readDouble();
				
				employeeList.add(new Employee(employee, salary, years));	
			}
			
		} catch (Exception er) {
			System.out.println("An error occurred while reading the file.");
		}
		
		//processes Employee objects and sums the totals of all salaries before and after raises for comparison
		double salarySum = 0;
		double processedSum = 0;
		
		for (int i = 0; i < employeeList.size(); i++) {
			salarySum += employeeList.get(i).getSalary();
			employeeList.get(i).processRaise();
			processedSum += employeeList.get(i).getSalary();
		}
		
		System.out.printf("Total Salary Before: $%,.2f%nTotal Salary After: $%,.2f", salarySum, processedSum);
		
		//writes the new Employee objects to a new data file
		try (
				ObjectOutputStream output = (new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("src/pAssign09/employeeDataProcessed.dat"))));		
			){
			
			for(int i = 0; i <employeeList.size(); i++) {
				output.writeObject((Employee)employeeList.get(i));
			}
			
		} catch(Exception ex) {
			System.out.print("An error occurred while writing the objects to a file.");
		}
	}
	
}

//Employee class for processing and organizing employee information
class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6782293550221408943L;
	private String employee;
	private double salary;
	private double years;
	
	
	//convenience constructor and constructor containing primary variables
	public Employee() {
		setEmployee("default");
		setSalary(0);
		setYearsOfService(0);
	}
	
	public Employee(String employee, double salary, double years) {
		this();
		setEmployee(employee);
		setSalary(salary);
		setYearsOfService(years);
	}
	
	//mutators and accessors for variables
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setYearsOfService(double years) {
		this.years = years;
	}
	
	public String getEmployee() {
		return employee;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public double getYearsOfService() {
		return years;
	}
	
	//method for calculating wages of employess based on salary and years of service
	public void processRaise() {
		if(salary <= 30000) {
			if(years <= 2) {
				setSalary(salary + salary * 0.03);
			} else {
				setSalary(salary + salary * 0.025);
			}
		} else if (salary > 30000 && salary <= 60000){
			if(years <= 5) {
				setSalary(salary + salary * 0.0225);
			} else {
				setSalary(salary + salary * 0.02);
			}
		} else if (salary > 60000 && salary <= 80000) {
			if (years <= 5) {
				setSalary(salary + salary * 0.0175);
			} else {
				setSalary(salary + salary * 0.015);
			}
		} else {
			if (years <= 5) {
				setSalary(salary + salary * 0.0125);
			} else {
				setSalary(salary + salary * 0.01);
			}
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s, %,.2f, %.2f%n", employee, salary, years);
	}
	
}
