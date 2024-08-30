package dev.domain.library;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;


@Transactional
public class CardRepository {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");
    EntityManager manager = factory.createEntityManager();
    public List<Library> findAll(long studentId) {
        return manager.createQuery("SELECT lc.library FROM LoanCard lc WHERE lc.student.id = :studentId", Library.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    public List<Library> findLibrariesWithoutStudent(Long studentId) {
        return manager.createQuery("SELECT l FROM Library l WHERE l.id NOT IN (" +
                        "SELECT lc.library.id FROM LoanCard lc WHERE lc.student.id = :studentId)", Library.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }
}
