package StudentGeneration;


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
    private static int STUDENT_ID_COUNTER = 1;
    private String name;
    private String surname;
    private char gender;
    private Date birthDay;
    private String Nationality;
    private String Department;

    public Student(String name, String surname, char gender, Date birthDay, String Nationality, String Department) {
        this.StudentID = STUDENT_ID_COUNTER + "";
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.birthDay = birthDay;
        this.Nationality = Nationality;
        this.Department = Department;
        STUDENT_ID_COUNTER++;
    }

    public String getStudentID() {
        return StudentID;
    }

    public static int getSTUDENT_ID_COUNTER() {
        return STUDENT_ID_COUNTER;
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

    @Override
    public String toString() {
        return "Student{" + "StudentID=" + StudentID + ", name=" + name + ", surname=" + surname + ", gender=" + gender + ", birthDay=" + birthDay + ", Nationality=" + Nationality + ", Department=" + Department + '}';
    }

    public void setNationality(String Nationality) {
        this.Nationality = Nationality;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }
    
    

}
