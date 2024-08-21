package dev.service.cloud.domain.request;

import java.util.Scanner;

public class RequestConsole {

    RequestDAO requestDAO = new RequestDAO();

    public void makingRequest(long library_id) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("도서관에 신청하고 싶은 도서 이름을 입력해주세요.");
        String book_title = scanner.nextLine();
        if (requestDAO.isStock(3, book_title) != -1) {
            System.out.println("이미 존재하는 도서입니다.");
        } else {
            requestDAO.saveRequest(3, book_title);
        }
    }
}
