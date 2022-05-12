import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * StudentGenerator.java is a program which generates students
 * students from a txt file and allows the user to edit their information.
 *
 * @author Liam Csiffary
 * @version 1.1
 * @since 2022-05-06
 */

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

  // gets the student to edit from the user
  public static int getStudent(List<Student> studentList) {
    // in case thers more than 1 student with the same first name
    List<Integer> numStudents = new ArrayList<Integer>();
    Scanner sc = new Scanner(System.in);

    // gets the student to edit
    while (true) {
      System.out.println("Which student would you like to edit/delete?");

      for (int i = 0; i < studentList.size(); i++) {
        if (studentList.size() < i) {
          System.out.println(studentList.get(i).firstName + ", or ");
        } else {
          System.out.println(studentList.get(i).firstName);
        }
      }

      String userInput = sc.nextLine().toLowerCase();

      // checks to see if the users input matches one of the students
      for (int i = 0; i < studentList.size(); i++) {
        if (userInput.equals((studentList.get(i).firstName).toLowerCase())) {
          numStudents.add(i);
        }
      }

      // if there is 1 student with that first name
      if (numStudents.size() == 1) {
        return numStudents.get(0);

        // if there is more than one student with that first name
      } else if (numStudents.size() > 1) {
        System.out.println("There is more than 1 student by that first name.");
        System.out.println("Which student would you like to edit? Please input their last name.");

        // prints the options to the user
        for (int i = 0; i < numStudents.size(); i++) {
          studentList.get(numStudents.get(i)).print();
        }
        // gets the students last name from the user
        userInput = sc.nextLine().toLowerCase();

        // if the users input matches one of the students last names then
        // return the index of the student to edit
        for (int i = 0; i < numStudents.size(); i++) {
          if (userInput.equals((studentList.get(numStudents.get(i)).lastName).toLowerCase())) {
            return numStudents.get(i);
          }
        }
      }
    }
  }

  // main function
  public static void main(String[] args) {
    // define vars
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

    // generates the students from the array sent back from the txt file
    for (int i = 0; i < students.length; i += 5) {
      // in case there are blank lines
      while (students[i].equals("")) {
        i++;
      }
      // if there is no middle name, indicated in the txt file by an underscore
      if (students[i + 1].equals("_")) {
        student = new Student(students[i], "N.M.N", students[i + 2],
            Integer.parseInt(students[i + 3]), Boolean.parseBoolean(students[i + 4]));

      } else {
        student = new Student(students[i],
            String.valueOf(students[i + 1].charAt(0)).toUpperCase(),
            students[i + 2], Integer.parseInt(students[i + 3]),
            Boolean.parseBoolean(students[i + 4]));

      }
      studentList.add(student);
    }

    // prints all the students
    for (int i = 0; i < studentList.size(); i++) {
      studentList.get(i).print();
    }

    // students editor
    while (true) {
      System.out.println("Would you like to edit these students? (y/n) or view students? (view)");
      System.out.println("Input 'new' to create a student and 'delete' to remove one");
      userInput = scanner.nextLine().toLowerCase();

      if (userInput.equals("y")) {
        int whichStu;

        // gets the student to edit, moved to a funtion as it got pretty complicated
        whichStu = getStudent(studentList);
        System.out.println("You are editing " + studentList.get(whichStu).firstName 
            + " " + studentList.get(whichStu).lastName);

        // gets the property to edit
        while (true) {
          System.out.println("\nWhich property would you like to edit?");
          System.out.println("First, middle, last,");
          System.out.println("grade, or IEP.\n");

          userInput = scanner.nextLine().toLowerCase();

          // change the first name to the user input
          if (userInput.equals("first")) {
            System.out.println("Please input new first name, be sure to capitalize.");
            userInput = scanner.nextLine();
            studentList.get(whichStu).editFirst(userInput);
            break;

            // change the middle name, or simply remove it
          } else if (userInput.equals("middle")) {
            System.out.println("Please input new middle name. Input none for no middle name.");
            userInput = scanner.nextLine();

            if (userInput.equals("none") || userInput.equals("None")) {
              studentList.get(whichStu).editMiddle("N.M.N");

            } else {
              // gets the first letter of the middle name and
              // capitalizes it if not already capitalized
              studentList.get(whichStu).editMiddle(
                  String.valueOf(userInput.charAt(0)).toUpperCase());

            }
            break;

            // change last name to users input
          } else if (userInput.equals("last")) {
            System.out.println("Please input new last name, be sure to capitalize.");
            userInput = scanner.nextLine();
            studentList.get(whichStu).editLast(userInput);
            break;

            // chane grade of the student
          } else if (userInput.equals("grade")) {
            int userInputInt;
            while (true) {
              System.out.println("Please input new grade");
              userInput = scanner.nextLine();

              try {
                userInputInt = Integer.parseInt(userInput);
                if (userInputInt > 0) {
                  break;
                } else {
                  System.out.println("Please input a natural number");
                }
              } catch (NumberFormatException e) {
                continue;
              }
            }

            studentList.get(whichStu).editGrade(userInputInt);
            break;

            // gets true of false for the IEP of the student
          } else if (userInput.equals("iep")) {
            boolean IEPBool;
            System.out.println("Please input true/false");
            while (true) {
              userInput = scanner.nextLine();

              try {
                // will only return true if user input is true
                // otherwise it will return false
                IEPBool = Boolean.parseBoolean(userInput);
                System.out.println(IEPBool);
                break;
              } catch (Exception e) {
                System.out.println("Please input true/false");
              }
            }

            studentList.get(whichStu).editIEP(IEPBool);
            break;

          } else {
            System.out.println("Not a valid input");
          }
        }

        // if the user does not want to make changes
      } else if (userInput.equals("n")) {
        System.out.println("You are not editing any students");
        break;

        // displays all students and their information
      } else if (userInput.equals("view")) {
        for (int i = 0; i < studentList.size(); i++) {
          studentList.get(i).print();
        }

        // student creator
      } else if (userInput.equals("new") || userInput.equals("create")) {
        System.out.println("Please input: first, middle, last, grade, and IEP");
        System.out.println("Be sure to capitalize, input (none) for no middle name");
        System.out.println("Input a natural number for grade, and true or false for IEP");

        // gets all necessary inputs from the user, then creates a student object
        // it then adds this student to the list of other students
        System.out.print("\nFirst: ");
        String first;
        first = scanner.nextLine();

        System.out.print("\nMiddle: ");
        String middle = scanner.nextLine();

        if (middle.equals("none") || middle.equals("None")) {
          middle = "N.M.N";
        } else {
          middle = middle.substring(0, 1);
          middle.toUpperCase();
        }

        System.out.print("\nLast: ");
        String last = scanner.nextLine();
        int grade;
        while (true) {
        
          System.out.print("\nGrade: ");
          String gradeStr = scanner.nextLine();
          try {
            grade = Integer.parseInt(gradeStr);
            break;
          } catch (NumberFormatException e) {
            System.out.println("Please input a natural number for grade");
          }
        }

        boolean IEPBool;
        while (true) {
          System.out.print("\nIEP: ");
          String IEPStr = scanner.nextLine();

          try {
            IEPBool = Boolean.parseBoolean(IEPStr);
            break;
          } catch (NumberFormatException e) {
            System.out.println("Please input either 'true' or 'false' for IEP");
          }
        }

        student = new Student(first, middle, last, grade, IEPBool);
        studentList.add(student);

        // deletes the requested student from the list of students
      } else if (userInput.equals("delete") || userInput.equals("remove")) {
        int stuDelete = getStudent(studentList);

        studentList.remove(stuDelete);

      } else {
        System.out.println("Please enter either 'y' or 'n'");
      }
    }
    scanner.close();
  }
}
