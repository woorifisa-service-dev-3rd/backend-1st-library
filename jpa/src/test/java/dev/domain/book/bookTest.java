package dev.domain.book;

import dev.domain.library.Library;
import dev.domain.stock.Stock;
import dev.domain.student.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class bookTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();
    @Test
    void 도서등록 () {

        Library lib = Library.builder()
                .name("감자도서관")
                .maxLoan(5)
                .dueDate(LocalDate.now())
                .build();

        List<Stock> books = manager.createQuery("select stock from Stock stock where stock.bookTitle = :bookTitle and stock.library.id = :library_id", Stock.class)
                .setParameter("bookTitle", "감자도리")
                .setParameter("library_id", 1)
                .getResultList();

        if(books.isEmpty()) {
            transaction.begin();

            manager.persist(lib);

            Stock stock = Stock.builder()
                    .bookTitle("감자도리의모험")
                    .library(lib)
                    .build();
            
            manager.persist(stock);

            transaction.commit();
        }

    }

}
