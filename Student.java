/**
 * Student.java is class of student, it has
 * properties: first name, middle initial, last name
 * grade and IEP
 *
 * @author Liam Csiffary
 * @version 1.1
 * @since 2022-05-06
 */

public class Student {
  String firstName;
  String midInit;
  String lastName;
  int grade;
  boolean IEP;

  // constructor
  public Student(String firstName, String midInit, String lastName, int grade, boolean IEP) {
    this.firstName = firstName;
    this.midInit = midInit;
    this.lastName = lastName;
    this.grade = grade;
    this.IEP = IEP;
  }

  // student printer
  public void print() {
    System.out.println("Name (First Middle. Last): " + firstName + " " + midInit + ". " + lastName);
    System.out.println("Grade: " + grade);
    System.out.println("IEP: " + IEP);
  }

  public void editFirst(String firstName) {
    this.firstName = firstName;
  }

  public void editMiddle(String midInit) {
    this.midInit = midInit;
  }

  public void editLast(String lastName) {
    this.lastName = lastName;
  }

  public void editGrade(int grade) {
    this.grade = grade;
  }

  public void editIEP(boolean IEP) {
    this.IEP = IEP;
  }
  
}
