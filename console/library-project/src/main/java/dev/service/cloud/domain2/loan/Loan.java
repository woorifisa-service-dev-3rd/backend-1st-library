package dev.service.cloud.domain2.loan;

import dev.service.cloud.domain2.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "loan_date")
    @CreationTimestamp
    private Date loanDate;

    @Column(name = "loan_count")
    private int loanCount;

    @Column(name = "book_title")
    private String bookTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
