public class Student {
  String firstName;
  String midInit;
  String lastName;
  int grade;
  boolean IEP;


  public Student(String firstName, String midInit, String lastName, int grade, boolean IEP) {
    this.firstName = firstName;
    this.midInit = midInit;
    this.lastName = lastName;
    this.grade = grade;
    this.IEP = IEP;
  }

  public void print() {
    System.out.println("Name (First Middle. Last): " + firstName + " " + midInit + ". " + lastName);
    System.out.println("Grade: " + grade);
    System.out.println("IEP: " + IEP);
  }
  
}
