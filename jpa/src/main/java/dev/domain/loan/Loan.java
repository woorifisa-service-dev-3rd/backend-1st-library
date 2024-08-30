package dev.domain.loan;

import dev.domain.student.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;

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
    private LocalDate loanDate;

    @Column(name = "loan_count")
    private int loanCount;

    @Column(name = "book_title")
    private String bookTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
