package pAssign09;

import java.io.*;

public class EmployeeDataTest {

	public static void main(String[] args) {
		try(
				DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("src/pAssign09/employeeData.dat")));
			) {
			
			while (input.available() > 0) {
				String employee = input.readUTF();
				double salary = input.readDouble();
				double years = input.readDouble();
				System.out.printf("%s, %.2f, %.2f%n", employee, salary, years);
			}
				
			} catch (Exception e) {
				System.out.println("An error occurred while reading the file.");
			}
		
		try(
				ObjectInputStream input = new ObjectInputStream
						(new FileInputStream("src/pAssign09/employeeDataProcessed.dat"))
				
				){
			
			for(int i = 0; i < 20; i++) {	
				System.out.print(input.readObject());
			}			
		} catch (Exception eo) {
			eo.printStackTrace();
			System.out.println("An error occurred while reading objects from the file.");
		}

	}

}
