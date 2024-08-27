package dev.service.cloud.domain.loan;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LoanInfo {
    private String bookTitle;
    private LocalDate loanDate;

    public LoanInfo(String bookTitle, LocalDate loanDate) {
        this.bookTitle = bookTitle;
        this.loanDate = loanDate;
    }
}
