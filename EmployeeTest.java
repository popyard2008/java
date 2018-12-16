import java.io.*;
public class EmployeeTest {

   public static void main(String args[]) {
      /* Create two objects using constructor */
      Employee empOne = new Employee("James Smith");
      Employee empTwo = new Employee("Mary Anne");

      // Invoking methods for each object created
      empOne.empAge(26);
      empOne.empDesignation("Senior Software Engineer");
      empOne.empSalary(1000);
      empOne.printEmployee();

      empTwo.empAge(21);
      empTwo.empDesignation("Software Engineer");
      empTwo.empSalary(500);
      empTwo.printEmployee();
   }

   public static class Employee {
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
}