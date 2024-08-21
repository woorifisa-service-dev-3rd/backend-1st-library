package dev.service.cloud.domain.loan;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class LoanConsole {

    private final LoanDAO loanDAO;
    private Scanner sc = new Scanner(System.in);

    void execute(long memberId) {
        System.out.println("도서관 목록 :");
        System.out.println(loanDAO.findLibraryList());

        System.out.println("어느 도서관에 입장하시겠습니까?\n");
        long libraryId = sc.nextInt();
        sc.nextLine();
        System.out.println(loanDAO.findBookListByLibraryName(libraryId));

        String libraryName = loanDAO.findLibraryById(libraryId);
        long maxLoan = loanDAO.findMaxLoan(libraryId);
        List<String> borrowedBooks = new ArrayList<>();

        while (true) {
            System.out.println("대출할 도서 목록을 작성해주세요 (도서이름 ,로 구분): ");
            String books = sc.nextLine();
            List<String> bookList = Arrays.stream(books.split(",")).map(String::trim).toList();

            if (bookList.size() > maxLoan) {
                System.out.println(libraryName + " 도서관의 1회 대출 가능 권수는 " + maxLoan + "권 입니다.");
                continue;
            }

            boolean allBooksAvailable = true;
            for (String book : bookList) {
                if (!loanDAO.checkStockAvailability(book, libraryId)) {
                    System.out.println(book + "의 재고가 없습니다.");
                    allBooksAvailable = false;
                    break;
                }
            }

            if (allBooksAvailable) {
                borrowedBooks = bookList;
                break;
            }
        }

        if (!loanDAO.checkLoanCardExist(memberId, libraryId)) {
            System.out.println(libraryName + " 도서관의 대출카드가 존재하지 않습니다. " + libraryName + " 도서관 대출카드를 생성해주세요 ");
            System.exit(0);
        }

        int dueDate = loanDAO.findDutDateByLibraryId(libraryId);
        System.out.println("대출되었습니다. 반납 기한은 대출일로 부터 " + dueDate + "일입니다.");

        for (String book : borrowedBooks) {
            loanDAO.createLoan(book, memberId, libraryId);
            loanDAO.decreaseStock(book, libraryId);
        }

        // 대출 정보 출력 (직전에 빌린 것만)
        System.out.println("\n대출한 도서 목록:");
        System.out.println("| 책 이름 | 대출 날짜 |");
        System.out.println("---------------------------------");
        for (String book : borrowedBooks) {
            System.out.printf("| %-20s | %s |\n", book, LocalDate.now());
        }

        sc.close();
    }
}
