package dev.service.cloud.domain.loan;

public class Main {
    public static void main(String[] args) {
        LoanConsole loanConsole = new LoanConsole(new LoanDAO());
        loanConsole.execute(1L);
    }
}
