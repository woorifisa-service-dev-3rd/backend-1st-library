package dev.kang;

import dev.domain.library.Library;
import dev.domain.library.CardRepository;
import dev.domain.loancard.LoanCard;
import dev.domain.student.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KangTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");
    EntityManager manager;
    EntityTransaction transaction;

    @BeforeEach
    public void setUp() {
        manager = factory.createEntityManager();
        transaction = manager.getTransaction();
        transaction.begin();  // 트랜잭션 시작
    }

    @AfterEach
    public void tearDown() {
        if (!transaction.isActive()) {
            transaction.begin();
        }

        manager.createNativeQuery("DELETE FROM Loan").executeUpdate();
        manager.createNativeQuery("DELETE FROM LoanCard").executeUpdate();
        manager.createNativeQuery("DELETE FROM Stock").executeUpdate();
        manager.createNativeQuery("DELETE FROM Library").executeUpdate();
        manager.createNativeQuery("DELETE FROM Student").executeUpdate();

        transaction.commit();

        if (manager.isOpen()) {
            manager.close();
        }
    }

    @Test
    @DisplayName("유저가 등록하지 않는 도서관 불러오기")
    public void 유저가_등록하지_않는_도서관_불러오기() {
        CardRepository cardRepository = new CardRepository();
        Student student = Student.builder().address("test_address").name("test_name").build();
        Library library1 = Library.builder().name("woori").maxLoan(10).dueDate(LocalDate.now()).build();
        Library library2 = Library.builder().name("hana").maxLoan(10).dueDate(LocalDate.now()).build();
        LoanCard loanCard1 = LoanCard.builder().library(library1).student(student).build();

        manager.persist(student);
        manager.persist(library1);
        manager.persist(library2);
        manager.persist(loanCard1);
        transaction.commit();

        Student findStudent = manager.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class)
                .setParameter("name", student.getName())
                .getSingleResult();

        List<Library> libraries = cardRepository.findLibrariesWithoutStudent(findStudent.getId());

        assertEquals(libraries.size(), 1);
    }


    @Test
    @DisplayName("유저가 도서관 등록하기")
    public void 유저가_도서관_등록하기() {
        CardRepository cardRepository = new CardRepository();
        Student student = Student.builder().address("test_address").name("test_name").build();
        Library library1 = Library.builder().name("woori").maxLoan(10).dueDate(LocalDate.now()).build();
        Library library2 = Library.builder().name("hana").maxLoan(10).dueDate(LocalDate.now()).build();
        LoanCard loanCard1 = LoanCard.builder().library(library1).student(student).build();

        manager.persist(student);
        manager.persist(library1);
        manager.persist(library2);
        manager.persist(loanCard1);
        transaction.commit();

        transaction.begin();

        Student findStudent = manager.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class)
                .setParameter("name", student.getName())
                .getSingleResult();

        List<Library> libraries = cardRepository.findLibrariesWithoutStudent(findStudent.getId());

        LoanCard registerLoanCard = LoanCard.builder().library(libraries.get(0)).student(student).build();
        manager.persist(registerLoanCard);
        transaction.commit();

        transaction.begin();

        //위에서 등록했으니 이제 0으로 나와야 함
        libraries = cardRepository.findLibrariesWithoutStudent(findStudent.getId());

        assertEquals(libraries.size(), 0);
    }


    @Test
    @DisplayName("유저가 도서관 등록하기")
    public void 유저가_등록한_도서관_조회하기() {
        CardRepository cardRepository = new CardRepository();
        Student student = Student.builder().address("test_address").name("test_name").build();
        Library library1 = Library.builder().name("woori").maxLoan(10).dueDate(LocalDate.now()).build();
        Library library2 = Library.builder().name("hana").maxLoan(10).dueDate(LocalDate.now()).build();
        LoanCard loanCard1 = LoanCard.builder().library(library1).student(student).build();
        LoanCard loanCard2 = LoanCard.builder().library(library2).student(student).build();

        manager.persist(student);
        manager.persist(library1);
        manager.persist(library2);
        manager.persist(loanCard1);
        manager.persist(loanCard2);
        transaction.commit();

        Student findStudent = manager.createQuery("SELECT s FROM Student s WHERE s.name = :name", Student.class)
                .setParameter("name", student.getName())
                .getSingleResult();

        List<Library> libraries = cardRepository.findAll(findStudent.getId());

        assertEquals(libraries.size(), 2);
    }
}
