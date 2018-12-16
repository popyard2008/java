// import java.io.*
public class Employee {
	String name;
	int age;
	String designation;
	double salary;
	public Employee(String name) {
		this.name = name;
	}

	public void empAge(int empAge){
		age = empAge;
	}

	public void empSalary(double empSalary) {
		salary = empSalary;
	}

	public void empDesignation(String empDesign) {
		designation = empDesign;
	}

	public void printEmployee() {
		System.out.println("Name: " + name);
		System.out.println("Age: " + age);
		System.out.println("Designation: " + designation);
		System.out.println("Salary:" + salary);
	}
}

/*
import java.io.*;
public class Employee {

   String name;
   int age;
   String designation;
   double salary;

   // This is the constructor of the class Employee
   public Employee(String name) {
      this.name = name;
   }

   // Assign the age of the Employee  to the variable age.
   public void empAge(int empAge) {
      age = empAge;
   }

   //Assign the designation to the variable designation.
   public void empDesignation(String empDesig) {
      designation = empDesig;
   }

   //Assign the salary to the variable	salary.
   public void empSalary(double empSalary) {
      salary = empSalary;
   }

   // Print the Employee details 
   public void printEmployee() {
      System.out.println("Name:"+ name );
      System.out.println("Age:" + age );
      System.out.println("Designation:" + designation );
      System.out.println("Salary:" + salary);
  }
}

*/
/*
public class Employee implements java.io.Serializable {
   public String name;
   public String address;
   public transient int SSN;
   public int number;
   
   public void mailCheck() {
      System.out.println("Mailing a check to " + name + " " + address);
   }
}
*/