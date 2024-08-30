package dev.domain.loan;

import dev.domain.student.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    @Test
    @DisplayName("학생생성")
    public void 학생생성() {
        transaction.begin();

        Student student = Student.builder()
                .name("student")
                .birth(LocalDate.now())
                .address("도봉구")
                .build();

        manager.persist(student);

        transaction.commit();
    }
}