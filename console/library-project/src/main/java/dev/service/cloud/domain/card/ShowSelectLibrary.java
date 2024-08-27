package dev.service.cloud.domain.card;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ShowSelectLibrary {
    int number;
    LibraryDAO libraryDAO;
}
