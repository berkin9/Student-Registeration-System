package GUI;


import StudentGeneration.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alper
 */
public class Student {

    private String StudentID;
    private String name;
    private String surname;
    private char gender;
    private Date birthDay;
    private String Nationality;
    private String Department;

    public Student(String StudentID, String name, String surname, char gender, Date birthDay, String Nationality, String Department) {
        this.StudentID = StudentID;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthDay = birthDay;
        this.Nationality = Nationality;
        this.Department = Department;
    }

    public String getStudentID() {
        return StudentID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public char getGender() {
        return gender;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getDepartment() {
        return Department;
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }
    
    

    @Override
    public String toString() {
        return "Student{" + "StudentID=" + StudentID + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", birthDay=" + birthDay + ", Nationality=" + Nationality + ", Department=" + Department + '}';
    }
    
    String [] convertToArray(){
      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      String [] str = new String[7];
      str[0] = this.StudentID;
      str[1] = this.name;
      str[2] = this.surname;
      str[3] = this.gender+"";
      str[4] = format.format(birthDay);
      str[5] = this.Nationality;
      str[6] = this.Department;
      return str;
    }
    

}
