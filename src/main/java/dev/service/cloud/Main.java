package dev.service.cloud;

import dev.service.cloud.domain.card.CardConsole;
import dev.service.cloud.domain.loan.LoanConsole;
import dev.service.cloud.domain.loan.LoanDAO;
import dev.service.cloud.domain.student.StudentConsole;
import dev.service.cloud.global.DBUtil;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // 학생 등록
        StudentConsole studentConsole = new StudentConsole();
        studentConsole.isMaking();
        long memberId = 1L;
        // 도서 대출 카드 생성
        CardConsole cardConsole = new CardConsole();
        cardConsole.play(memberId);
        // 대출
        LoanConsole loanConsole = new LoanConsole(new LoanDAO());
        loanConsole.execute(memberId);
        // 반납

        // 도서 신청

    }
}