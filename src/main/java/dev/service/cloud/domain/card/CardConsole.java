package dev.service.cloud.domain.card;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class CardConsole {
    public void play(Long studentId) {
        CardConfig config = new CardConfig();
        CardService cardService = config.cardService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("도서관에 등록하시겠습니까??");
            System.out.println("(1) 예  (2) 아니오");
            int answer = scanner.nextInt();

            if (answer == 2) return;
            if (answer != 1) {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                continue;
            }
            break;
        }

        List<String> libraryNames = cardService.showLibraryName(studentId);

        if (!libraryNames.isEmpty()) {
            String formatData = formatLibraryNames(libraryNames);
            while (true) {
                System.out.println(String.format("현재 %s에 등록되어 있습니다. 추가로 등록하시겠습니까?", formatData));
                System.out.println("(1) 예  (2) 아니오");
                int answer = scanner.nextInt();

                if (answer == 2) return;
                if (answer != 1) {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    continue;
                }
                break;
            }
        }

        List<CardService.ShowSelectLibrary> showSelectLibraries = cardService.showLibraryWithoutStudent(studentId);
        System.out.println("어느 도서관에 등록하시겠습니까?");
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);

        showSelectLibraries.forEach(item -> {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(String.format("[%d] %s 도서관", index.getAndIncrement(), item.getLibraryDAO().getName()));
        });

        System.out.println(stringBuilder.toString());

        while (true) {
            int answer = scanner.nextInt();

            boolean isValidChoice = showSelectLibraries.stream()
                    .anyMatch(item -> item.getNumber() == answer);

            if (!isValidChoice) {
                System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
                continue;
            }

            showSelectLibraries.stream()
                    .filter(item -> item.getNumber() == answer)
                    .findFirst()
                    .ifPresent(item -> {
                        cardService.registerCard(studentId, item.getLibraryDAO().getId());
                        String libraryName = item.getLibraryDAO().getName();
                        System.out.println(String.format("%s 도서관을 선택하였습니다.", libraryName));
                        System.out.println(String.format("책 대여는 %s 도서관에서만 가능합니다.", libraryName));
                    });
            break;
        }
    }


    private String formatLibraryNames(List<String> libraryNames) {
        return libraryNames.stream()
                .map(name -> name + " 도서관")
                .collect(Collectors.joining(", "));
    }

}
