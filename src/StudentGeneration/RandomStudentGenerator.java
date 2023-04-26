package StudentGeneration;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alper
 */
public class RandomStudentGenerator {

    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        List<String> maleNames = new ArrayList<>();
        List<String> femaleNames = new ArrayList<>();
        List<String> lastNames = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        readFile(maleNames, "malenames.txt");
        readFile(femaleNames, "femalenames.txt");
        readFile(lastNames, "lastnames.txt");
        readFile(dates, "dates.txt");
        List<Student> students = new ArrayList<>(2300);
        for (int i = 0; i < 2300; i++) {
            students.add(generateStudent(maleNames, femaleNames, lastNames, dates));
        }
        for (Student student : students) {
            writeStudentToFile(student);
        }
    }

    private static String generateName(List<String> NameCandidates) {
        Random rnd = new Random();
        int rndIndex = rnd.nextInt(NameCandidates.size());
        return NameCandidates.get(rndIndex);
    }

    private static void readFile(List<String> originalList, String fileName) throws FileNotFoundException {
        List<String> candidateList = new ArrayList<>(1000);
        Scanner fileInput = new Scanner(new File(fileName));
        while (fileInput.hasNextLine()) {
            candidateList.add(fileInput.nextLine());
        }
        originalList.addAll(candidateList);
    }

    private static String generateDepartmentOrNation(int size) {
        List<Integer> deptIDs = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            deptIDs.add(i + 1);
        }
        Random rnd = new Random();
        int rndIndex = rnd.nextInt(deptIDs.size());
        return deptIDs.get(rndIndex).toString();
    }

    private static Student generateStudent(List<String> maleNames, List<String> femaleNames, List<String> lastNames, List<String> dates) throws ParseException {
        Random rnd = new Random();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        boolean coinFlip = rnd.nextBoolean();
        String name;
        String surname;
        char gender;
        Date birthDay;
        String Nationality;
        String Department;

        if (coinFlip) {
            // Gender is male
            name = generateName(maleNames);
            gender = 'M';

        } else {
            // gender female
            name = generateName(femaleNames);
            gender = 'F';
        }
        surname = generateName(lastNames);
        birthDay = format.parse(generateName(dates));
        Nationality = generateDepartmentOrNation(80);
        Department = generateDepartmentOrNation(14);
        return new Student(name, surname, gender, birthDay, Nationality, Department);
    }
    
    private static void writeStudentToFile(Student student) throws IOException{
     SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
     String writeFormat = student.getStudentID()
             +"-"+student.getName()
             +"-"+student.getSurname()
             +"-"+student.getGender()
             +"-"+format.format(student.getBirthDay())
             +"-"+student.getNationality()
             +"-"+student.getDepartment();
     FileWriter fileWriter = new FileWriter("students.txt", true);
     PrintWriter printWriter = new PrintWriter(fileWriter);
     printWriter.println(writeFormat);
     printWriter.close();
    }
    
}

