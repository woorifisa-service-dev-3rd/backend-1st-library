package dev.service.cloud.domain.student;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class StudentConsole {
    StudentDAO studentDAO = new StudentDAO();

    public void isMaking() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("도서증을 등록하시겠습니까?");
        System.out.println("(1) 예 (2) 아니오");
        int N = 0;

        while (N != 1 && N != 2) {
            N = scanner.nextInt(); // 입력에서 숫자를 받음
            scanner.nextLine(); // 개행 문자 소비
            switch (N) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    findStudentId();
                    break;
                default:
                    System.out.println("잘못된 입력입니다 다시 입력해주세요.");
            }
        }
//        scanner.close();
    }

    public long findStudentId() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long studentId = -1;
        do {
            System.out.println("로그인 할 유저의 정보를 입력해주세요.");
            try {
                System.out.print("이름: ");
                String name = scanner.nextLine();
                System.out.print("생년월일 (yyyy-MM-dd): ");
                Date birth = Date.valueOf(scanner.nextLine());
                System.out.print("주소: ");
                String address = scanner.next();

                studentId = studentDAO.loginStudent(name, birth, address);
                if (studentId == -1) {
                    System.out.println("잘못된 유저 정보입니다.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (studentId == -1);

//        scanner.close();
        return studentId;
    }

    public void registerStudent() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long studentId = -1;
        String name;
        Date birth;
        String address;
        try {
            do {
                System.out.println("등록 하실 이름을 입력해주세요.");
                System.out.print("이름 : ");
                name = scanner.nextLine();
                System.out.println("생년월일을 입력해주세요 (yyyy-MM-dd).");
                System.out.print("생년월일 : ");
                birth = Date.valueOf(scanner.nextLine());
                System.out.println("주소를 입력해주세요.");
                System.out.print("주소 : ");
                address = scanner.nextLine();
            } while (name == null || name.isEmpty() || birth == null || address == null || address.isEmpty());

            studentId = studentDAO.loginStudent(name, birth, address);
            if (studentId != -1) {
                System.out.println("이미 존재하는 유저입니다.");
            } else {
                studentDAO.saveStudent(name, birth, address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
