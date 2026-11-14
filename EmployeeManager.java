// File Name: EmployeeManager.java

import java.io.*;
import java.util.*;

public class EmployeeManager {

    public static void main(String[] args) {

        // Check arguments
        if (args.length != 1) {
            System.out.println("Invalid number of arguments.");
            return;
        }

        if (args[0].equals("l")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();

                for (String employee : employees) {
                    System.out.println(employee);
                }
            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].equals("s")) {
            System.out.println("Loading data ...");

            try {
                String line = readEmployeeLine();
                String[] employees = line.split(",");

                System.out.println(line);

                Random random = new Random();
                int index = random.nextInt(employees.length);

                System.out.println(employees[index]);
            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");

            try {
                String name = args[0].substring(1);
                appendEmployee(name);
            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();
                boolean found = false;
                String searchName = args[0].substring(1);

                for (int i = 0; i < employees.length && !found; i++) {
                    if (employees[i].equals(searchName)) {
                        System.out.println("Employee found!");
                        found = true;
                    }
                }
            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");

            try {
                String line = readEmployeeLine();
                char[] characters = line.toCharArray();
                boolean inWord = false;
                int wordCount = 0;

                for (char character : characters) {
                    if (character == ' ') {
                        if (!inWord) {
                            wordCount++;
                            inWord = true;
                        } else {
                            inWord = false;
                        }
                    }
                }

                System.out.println(
                        wordCount + " word(s) found " + characters.length);
            } catch (Exception e) {
            }

            System.out.println("Data Loaded.");

        } else if (args[0].contains("u")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();
                String name = args[0].substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].equals(name)) {
                        employees[i] = "Updated";
                    }
                }

                writeEmployees(employees);
            } catch (Exception e) {
            }

            System.out.println("Data Updated.");

        } else if (args[0].contains("d")) {
            System.out.println("Loading data ...");

            try {
                String[] employees = readEmployees();
                String name = args[0].substring(1);

                List<String> employeeList =
                        new ArrayList<>(Arrays.asList(employees));

                employeeList.remove(name);

                writeEmployees(employeeList.toArray(new String[0]));
            } catch (Exception e) {
            }

            System.out.println("Data Deleted.");
        }
    }

    private static String[] readEmployees() throws IOException {
        return readEmployeeLine().split(",");
    }

    private static String readEmployeeLine() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.FILE_NAME)));

        String line = reader.readLine();
        reader.close();

        return line;
    }

    private static void writeEmployees(String[] employees) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME));

        writer.write(String.join(",", employees));
        writer.close();
    }

    private static void appendEmployee(String name) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.FILE_NAME, true));

        writer.write(", " + name);
        writer.close();
    }
}
