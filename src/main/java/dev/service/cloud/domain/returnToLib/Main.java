package dev.service.cloud.domain.returnToLib;

import dev.service.cloud.global.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

@Slf4j
public class Main {

    static ReturnDao dao = new ReturnDao();
    static ArrayList<ReturnBookDto> dtoArrayList ;

    public static void main(String[] args) throws IOException {
        //아이디받아야함
        dtoArrayList = dao.showLoanBook(studentId, libraryId);
        doLoan(studentId, libraryId);
    }


    static void showLoanList() {
        System.out.println("현재 대출 내역 :\n");
        System.out.println("|     책 이름       |    대출 날짜     |");
        System.out.println("---------------------------------------");

        if (dtoArrayList != null) {
            for(int i = 0 ; i < dtoArrayList.size(); i++){
                System.out.println("| "+ dtoArrayList.get(i).getBookTitle() + " | " +dtoArrayList.get(i).getLoanDate() +" |");
            }
        }

    }

    static void doLoan(int studentId, int libraryId) throws IOException {
        while(true) {
            showLoanList();

            System.out.println("\n반납하실 책 이름을 입력해주세요 (반납을 끝내시려면 '끝'이라 입력해주세요) : ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String returnBookTitle = br.readLine();

            if(returnBookTitle.equals("끝"))
                break;
            else {
                ReturnBookDto dto = dao.checkLoanDateAndReturn(studentId,returnBookTitle,libraryId);  //아이디수정

                //기한내에 반납한 경우
                if(!dto.isLateCheck()) {
                    System.out.println("반납되었습니다.");
                }
                else {
                    int lateFee = 100;
                    System.out.println(dto.getLateDay()+" 일 동안 연체되었습니다. 연체로 인해 내야할 금액은 "+ lateFee*dto.getLateDay() +"입니다.");
                }

                Iterator<ReturnBookDto> iterator = dtoArrayList.iterator();
                while (iterator.hasNext()) {
                    ReturnBookDto mydto = iterator.next();
                    if (mydto.getBookTitle().equals(returnBookTitle)) {
                        iterator.remove();
                    }
                }

                dao.deleteLoanAndUpdateStock(studentId,returnBookTitle,libraryId);  //아이디수정
            }


        }
    }
}