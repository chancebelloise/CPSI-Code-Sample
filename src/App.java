import java.io.*;

import calculator.SalaryCalculator;

public class App {
    public static void main(String[] args) throws Exception {
        
        SalaryCalculator calculator = new SalaryCalculator(); // Create new instance of SalaryCalculator.
        int flag = 0; // Create basic integer flag.

        // Parse arguments.
        for (int i = 0; i < args.length; i++) {
            
            // Handle -f [filename] argument.
            if (args[i].toString().equals("-f")){

                // Check to see if filename has .csv extension and pass to SalaryCalculator.
                if(args[i+1].matches(".*\\.csv")){
                    flag++; // Increment flag to 1.
                    File csvFile = new File(args[i+1]);
                    calculator.read(csvFile);
                } 
                
                // If not CSV file, print error and break loop.
                else {
                    System.out.println("ERROR - File must be CSV.");
                    break;
                }
            }

            // Handle -d ["all" or "role"] argument.
            if (args[i].toString().equals("-d") && flag == 1){
                flag++; // Inrement flag to 2.
                switch(args[i+1].toString()){
                 case "all": calculator.all(); break;
                 case "role": calculator.role(); break;
                 default: System.out.println("ERROR: -d argument must be followed by either \"all\" or \"role\".");
                    break;
                }
            }

            // If flag is greater than two, invalid arguments were passed. Print error and break loop.
            if (flag > 2){
                System.out.println("ERROR: Invalid arguments.");
                break;
            }
        }
    }
}
