package dev.service.cloud.domain.returnToLib;


import java.sql.Date;

public class ReturnBookDto {

    private String bookTitle;
    private Date loanDate;
    private boolean lateCheck;
    private long lateDay;

    public ReturnBookDto() {
    }

    public ReturnBookDto(int lateDay, boolean lateCheck,Date loanDate) {
        this.lateDay = lateDay;
        this.lateCheck = lateCheck;
        this.loanDate = loanDate;
    }

    public long getLateDay() {
        return lateDay;
    }

    public void setLateDay(long lateDay) {
        this.lateDay = lateDay;
    }

    public boolean isLateCheck() {
        return lateCheck;
    }

    public void setLateCheck(boolean lateCheck) {
        this.lateCheck = lateCheck;
    }

    public ReturnBookDto(String bookTitle, Date loanDate) {
        this.bookTitle = bookTitle;
        this.loanDate = loanDate;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }
}
