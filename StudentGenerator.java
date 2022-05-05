import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentGenerator {

  // generates the array of inputs from a text file
  public static String[] arrayGenerator(File file) {

    // defining vars
    List<String> originalListString = new ArrayList<String>();
    System.out.println(file);
    Scanner scStudent;
    String[] tempArr = new String[0];
    
    try {
      scStudent = new Scanner(file);
    } catch (FileNotFoundException e) {
      return tempArr;
    }

    // pass the path to the file as a parameter
    while (scStudent.hasNextLine()) {
      originalListString.add(scStudent.nextLine());
    }

    // for testing purposes
    System.out.println(originalListString);

    // create array of length
    String[] array = new String[originalListString.size()];

    // fill the array
    for (int i = 0; i < array.length; i++) {
      array[i] = originalListString.get(i);
    }

    scStudent.close();

    return array;
  }
  
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    List<Student> studentList = new ArrayList<Student>();
    Student student;
    String userInput;


    // gets users relative path
    String path = System.getProperty("user.dir");
    File file = new File(path + "/students.txt");

    // gets the array of testCases from the ArrayGenerator
    String[] students = arrayGenerator(file);
    System.out.println("\n");

    for (int i = 0; i < students.length; i += 5) {
      if (students[i].equals("")) {
        i++;
      }
      if (students[i + 1].equals("_")) {
        student = new Student(students[i], "N.M.N", students[
          i + 2], Integer.parseInt(students[i + 3]), Boolean.parseBoolean(students[i + 4]));

      } else {
        student = new Student(students[i], students[i + 1], students[
          i + 2], Integer.parseInt(students[i + 3]), Boolean.parseBoolean(students[i + 4]));

      }
      studentList.add(student);
    }

    for (int i = 0; i < studentList.size(); i++) {
      studentList.get(i).print();
    }

    while (true) {
      System.out.println("Would you like to edit these students? (y/n)");
      userInput = scanner.nextLine().toLowerCase();
      if (userInput.equals("y")) {
        while (true) {
          System.out.println("Which student would you like to edit?");

          for (int i = 0; i < studentList.size(); i++) {
            if (studentList.size() < i) {
              System.out.println(studentList.get(i).firstName + ", or ");
            } else {
              System.out.println(studentList.get(i).firstName);
            }
          }

          userInput = scanner.nextLine().toLowerCase();

          boolean isStudent = false;
          for (int i = 0; i < studentList.size(); i++) {
            if (userInput.equals((studentList.get(i).firstName).toLowerCase())) {
              System.out.println("You are editing " + studentList.get(i).firstName);
              isStudent = true;
              break;
            }
          }
          if (isStudent) {
            break;
          }
        }

        break;
      } else if (userInput.equals("n")) {


        break;
      } else {
        System.out.println("Please enter either y/n");
      }
    }


    scanner.close();

  }
}
