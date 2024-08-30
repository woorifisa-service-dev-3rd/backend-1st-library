package dev.domain.student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class StudentTest {

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("library");
    EntityManager manager = factory.createEntityManager();
    EntityTransaction transaction = manager.getTransaction();

    @Test
    void 회원가입 () {

        List<Student> students = manager.createQuery("select st from Student st where st.name = :name and st.address = :address and st.birth = : birth", Student.class)
                .setParameter("name", "황순범")
                .setParameter("address", "123123")
                .setParameter("birth", LocalDate.now())
                .getResultList();
        if (students.isEmpty()) {
            transaction.begin();

            Student student = Student.builder()
                    .name("황순범")
                    .birth(LocalDate.now())
                    .address("123123")
                    .build();

            manager.persist(student);

            transaction.commit();
        } else {
            System.out.println("이미 존재하는 회원입니다.");
        }

    }

    @Test
    void 로그인 () {

        Optional<Long> studentId = manager.createQuery("select st.id from Student st where st.name = :name and st.address = :address and st.birth = :birth", Long.class)
                .setParameter("name", "황순범")
                .setParameter("address", "123123")
                .setParameter("birth", LocalDate.now())
                .getResultStream()
                .findFirst();

        if (studentId.isPresent()) {
            long id = studentId.get();
            transaction.begin();
            Student student = manager.find(Student.class, id);
            transaction.commit();
            System.out.println( student.getName() + "님 환영합니다." );
        } else {
            System.out.println("문제가 발생했습니다.");
        }

    }



}
