package macro;

import java.util.List;

import macro.dbo.Salary;
import macro.utils.CustomCSVReader;

public class Client {
  public static void main(String[] args) {
    String workingDir = System.getProperty("user.dir");
    String pathToCsv = workingDir + "/db/salaries.csv";
    CustomCSVReader<Salary> reader = new CustomCSVReader<>(pathToCsv);

    try {
      List<Salary> salaries = reader.read(Salary.class);

      for (Salary salary: salaries) {
          System.out.println(salary.toString());
      }
    } catch (Exception e) {
      System.out.println("Error reading CSV file: " + e.getMessage());
    }
  }
}
