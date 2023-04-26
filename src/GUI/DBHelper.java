/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author alper
 */
public class DBHelper {

    public static String database_URL = "jdbc:ucanaccess://StudentRegistration.accdb";
    Connection connection;
    HashMap<String, String> countries;
    HashMap<String, String> depts;

    private void connectToDB() {
        try {
            connection = DriverManager.getConnection(database_URL);
            System.out.println("Connected Successfully!");
        } catch (SQLException ex) {
            System.err.println("Can not find database file " + database_URL + "!");
        }
    }

    private void closeDBConnection() {
        try {
            connection.close();
            System.out.println("Connected closed Successfully!");
        } catch (SQLException ex) {
            System.err.println("Can not find database file " + database_URL + "!");
        }
    }

    public List<Student> loadStudents(int limit) throws SQLException, ParseException {

        List<Student> studentsList = new ArrayList<>();
        connectToDB();
        String sql = "";
        if (limit > 0 && limit < 2500) {
            sql = "SELECT TOP " + limit + " * FROM Student";
        } else {
            sql = "SELECT * FROM Student";
        }
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        while (result.next()) {
            String id = result.getString("ID");
            String name = result.getString("Name");
            String lastname = result.getString("Lastname");
            String gender = result.getString("Gender");
            String bdate = result.getString("BirthDate");
            String nation = result.getString("NationalityID");
            String dept = result.getString("DepartmentID");
            studentsList.add(new Student(id, name, lastname, gender.charAt(0), new SimpleDateFormat("yyyy-MM-dd").parse(bdate), nation, dept));
        }
        sql = "SELECT * FROM Country";
        statement = connection.createStatement();
        result = statement.executeQuery(sql);
        countries = new HashMap<>();
        while (result.next()) {
            String id = result.getString("ID");
            String name = result.getString("CountryName");
            countries.put(id, name);
        }
        sql = "SELECT * FROM Department";
        statement = connection.createStatement();
        result = statement.executeQuery(sql);
        depts = new HashMap<>();
        while (result.next()) {
            String id = result.getString("ID");
            String name = result.getString("DepartmentName");
            depts.put(id, name);
        }
        closeDBConnection();
        for (int i = 0; i < studentsList.size(); i++) {
            Student x = studentsList.get(i);
            x.setDepartment(depts.get(x.getDepartment()));
            x.setNationality(countries.get(x.getNationality()));
        }

        return studentsList;
    }

    public boolean writeToDatabase(String ID, String name, String surname,
            String gender, LocalDate time, String nation, String dept) throws SQLException {

        connectToDB();
        String sql = "INSERT INTO Student (ID,Name,Lastname,Gender,BirthDate,NationalityID,DepartmentID)"
                + " VALUES (?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, ID);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, surname);
        preparedStatement.setString(4, gender);
        preparedStatement.setDate(5, java.sql.Date.valueOf(time));
        preparedStatement.setString(6, nation);
        preparedStatement.setString(7, dept);

        int row = preparedStatement.executeUpdate();
        closeDBConnection();
        if (row > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteStudent(String StudentID) throws SQLException {
        connectToDB();
        String sql = "DELETE FROM Student WHERE ID=" + StudentID;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int row = preparedStatement.executeUpdate();
        closeDBConnection();
        if (row > 0) {
            return true;
        } else {
            return false;
        }
    }
}
