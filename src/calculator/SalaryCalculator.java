package calculator;
import java.io.*;
import java.util.*;

public class SalaryCalculator {

  List<Employee> employees = new ArrayList<Employee>();

  // This method reads in the CSV file and calls the processEmployee method.
  public void read(File csvFile){
  try {
      Scanner myReader = new Scanner(csvFile);
      while (myReader.hasNextLine()) {
        employees.add(processEmployee(myReader.nextLine()));
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred reading the CSV file.");
      e.printStackTrace();
    }
  }

  // This method creates and returns new Employee objects based on the contents of the CSV file.
  public Employee processEmployee(String employeeData){
      String[] data = employeeData.split("\\|"); // Split on pipe delimitor.
      Employee employee = new Employee();
      employee.name = data[0]; // Assign name.
      employee.rate = Double.parseDouble(data[1]); // Assign rate.
      employee.hours = Double.parseDouble(data[2]); // Assign hours.
      employee.role = data[3]; // Assign role.
      return employee;
  }



  // This method prints out all employees in the order they appear in the CSV file and their respective salaries.
  public void all(){

    Double salary = 0.0; // Declare salary.

    // Iterate through employees list.
    for (Employee employee : employees){

      // Calculate yearly salary based on employee role.
      switch(employee.role){
        case "FULL TIME": 
          salary = (employee.hours * 52.00) * employee.rate; // Full time salary is (hours * weeks in year) * rate.
          if(salary > 50000.00){ salary = 50000.00; } // Full time salary is capped at $50,000.
          break;
        
        case "PART TIME":
          salary = (employee.hours * 52.00) * employee.rate; // Part time salary is (hours * weeks in year) * rate and has no cap.
          break;

        case "CONTRACT":
          salary = ((employee.hours * 52.00) * employee.rate) + 10000.00; // Contract salary is $10,000 + ((hours * weeks in year) * rate) and has no cap.
          break;
        
        default: break;
      }
      
      // Print name:salary and format salary to serparte digit groups with a comma and round two decimal places.
      System.out.println(employee.name+": $"+String.format("%,.2f", salary));
    }
  }

  // This method prints out all employees (grouped by role) and their respective salaries.
  public void role(){

    Double salary = 0.0; // Declare salary.

    Map<String, Double> fullTime = new HashMap<String, Double>(); // Map for full time employee data.
    Map<String, Double> partTime = new HashMap<String, Double>(); // Map for part time employee data.
    Map<String, Double> contract = new HashMap<String, Double>(); // Map for contract employee data.
    
    // Iterate through employees list.
    for (Employee employee : employees){

      // Calculate yearly salary based on employee role.
      switch(employee.role){

        case "FULL TIME": 
          salary = (employee.hours * 52.00) * employee.rate; // Full time salary is (hours * weeks in year) * rate.
          if(salary > 50000.00){ salary = 50000.00; } // Full time salary is capped at $50,000.
          fullTime.put(employee.name, salary); // Add to fullTime map.
          break;
  
        case "PART TIME":
          salary = (employee.hours * 52.00) * employee.rate; // Part time salary is (hours * weeks in year) * rate and has no cap.
          partTime.put(employee.name, salary); // Add to partTime map.
          break;

        case "CONTRACT":
          salary = ((employee.hours * 52.00) * employee.rate) + 10000.00; // Contract salary is $10,000 + ((hours * weeks in year) * rate) and has no cap.
          contract.put(employee.name, salary); // Add to contract map.
          break;
        
        default: break;
      }
    }

    // Print full time group.
    System.out.println("===== FULL TIME =====");
    for (Map.Entry<String, Double> entry : fullTime.entrySet()) {
      System.out.println(entry.getKey()+": $"+String.format("%,.2f", entry.getValue()));
    }

    // Print part time group.
    System.out.println("\n===== PART TIME =====");
    for (Map.Entry<String, Double> entry : partTime.entrySet()){
      System.out.println(entry.getKey()+": $"+String.format("%,.2f", entry.getValue()));
    }

    // Print contract group.
    System.out.println("\n===== CONTRACT =====");
    for (Map.Entry<String, Double> entry : contract.entrySet()){
      System.out.println(entry.getKey()+": $"+String.format("%,.2f", entry.getValue()));
    }
  }
}