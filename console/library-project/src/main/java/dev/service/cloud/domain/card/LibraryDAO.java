package dev.service.cloud.domain.card;

import lombok.Getter;


@Getter
public class LibraryDAO {
    private Long id;
    private String name;

    private LibraryDAO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static LibraryDAO of(Long id, String name) {
        return new LibraryDAO(id, name);
    }
}
