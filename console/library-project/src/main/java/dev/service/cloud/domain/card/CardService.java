package dev.service.cloud.domain.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    void registerCard(Long studentId, Long libraryId) {
        cardRepository.save(studentId, libraryId);
    }

    List<String> showLibraryName(Long studentId) {
        return cardRepository.findLibraryNameByStudentId(studentId);
    }

    List<ShowSelectLibrary> showLibraryWithoutStudent(Long studentId) {
        List<ShowSelectLibrary> selectLibraries = new ArrayList<>();
        List<LibraryDAO> libraryDAOS = cardRepository.findLibrariesWithoutStudent(studentId);
        IntStream.range(0, libraryDAOS.size())
                .forEach(i -> selectLibraries.add(new ShowSelectLibrary(i + 1, libraryDAOS.get(i))));

       return selectLibraries;
    }
}
