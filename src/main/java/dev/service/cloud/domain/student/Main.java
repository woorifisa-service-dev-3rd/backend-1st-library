package dev.service.cloud.domain.student;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StudentDAO studentDAO = new StudentDAO();
        StudentConsole studentConsole = new StudentConsole();

        List<Student> allStudents = studentDAO.selectAll();

//        studentDAO.selectAll();
        studentConsole.isMaking();

    }
}
