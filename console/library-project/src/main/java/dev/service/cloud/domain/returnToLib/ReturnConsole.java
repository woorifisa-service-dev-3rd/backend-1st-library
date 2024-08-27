package dev.service.cloud.domain.returnToLib;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@RequiredArgsConstructor
public class ReturnConsole {

    private final ReturnDao dao;
    ArrayList<ReturnBookDto> dtoArrayList;

    public void excute(long memberId, long libraryId) {
        dtoArrayList = dao.showLoanBook(memberId, libraryId);
        doLoan(memberId, libraryId);
    }

    void showLoanList() {
        System.out.println("현재 대출 내역 :\n");
        System.out.println("|     책 이름       |    대출 날짜     |");
        System.out.println("---------------------------------------");

        if (dtoArrayList != null) {
            for (int i = 0; i < dtoArrayList.size(); i++) {
                System.out.println("| " + dtoArrayList.get(i).getBookTitle() + " | " + dtoArrayList.get(i).getLoanDate() + " |");
            }
        }
    }

    void doLoan(long studentId, long libraryId) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showLoanList();

            System.out.println("\n반납하실 책 이름을 입력해주세요 (반납을 끝내시려면 '끝'이라 입력해주세요) : ");
            String returnBookTitle = scanner.nextLine();

            if (returnBookTitle.equals("끝"))
                break;
            else {
                ReturnBookDto dto = dao.checkLoanDateAndReturn(studentId, returnBookTitle, libraryId);

                //기한내에 반납한 경우
                if (!dto.isLateCheck()) {
                    System.out.println("반납되었습니다.");
                } else {
                    int lateFee = 100;
                    System.out.println(dto.getLateDay() + " 일 동안 연체되었습니다. 연체로 인해 내야할 금액은 " + lateFee * dto.getLateDay() + "입니다.");
                }

                Iterator<ReturnBookDto> iterator = dtoArrayList.iterator();
                while (iterator.hasNext()) {
                    ReturnBookDto mydto = iterator.next();
                    if (mydto.getBookTitle().equals(returnBookTitle)) {
                        iterator.remove();
                    }
                }

                dao.deleteLoanAndUpdateStock(studentId, returnBookTitle, libraryId);
            }
        }
    }
}
