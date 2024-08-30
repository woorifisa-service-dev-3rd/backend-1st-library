package dev.service.cloud;

import dev.service.cloud.domain.card.CardConsole;
import dev.service.cloud.domain.loan.LoanConsole;
import dev.service.cloud.domain.loan.LoanDAO;
import dev.service.cloud.domain.request.RequestConsole;
import dev.service.cloud.domain.returnToLib.ReturnConsole;
import dev.service.cloud.domain.returnToLib.ReturnDao;
import dev.service.cloud.domain.student.StudentConsole;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;

@Slf4j
public class Main {
    public static void main(String[] args) {
        withJar(args[0]);
//        withOutJar();
    }

    static void withOutJar() {
        // 학생 등록
        StudentConsole studentConsole = new StudentConsole();
        long memberId = studentConsole.isMaking();

        // 도서 대출 카드 생성
        CardConsole cardConsole = new CardConsole();
        cardConsole.play(memberId);

        // 대출
        LoanConsole loanConsole = new LoanConsole(new LoanDAO());
        long library_id = loanConsole.execute(memberId);

        // 반납
        ReturnConsole returnConsole = new ReturnConsole(new ReturnDao());
        returnConsole.excute(memberId, library_id);

        // 도서 신청
        RequestConsole requestConsole = new RequestConsole();
        requestConsole.makingRequest(library_id);
    }

    static void withJar(String ymlPath) {
        // 학생 등록
        DBUtil.ymlPath = ymlPath;
        // 학생 등록
        StudentConsole studentConsole = new StudentConsole();
        long memberId = studentConsole.isMaking();

        // 도서 대출 카드 생성
        CardConsole cardConsole = new CardConsole();
        cardConsole.play(memberId);

        // 대출
        LoanConsole loanConsole = new LoanConsole(new LoanDAO());
        long library_id = loanConsole.execute(memberId);

        // 반납
        ReturnConsole returnConsole = new ReturnConsole(new ReturnDao());
        returnConsole.excute(memberId, library_id);

        // 도서 신청
        RequestConsole requestConsole = new RequestConsole();
        requestConsole.makingRequest(library_id);
    }
}