package dev.service.cloud.domain.card;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public class CardConsole {
    public void Play(Long studentId){
        CardConfig config = new CardConfig();
        CardService cardService = config.cardService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("도서관에 등록하시겠습니까??");
        System.out.println("(1) 예  (2) 아니오");
        int answer = scanner.nextInt();

        if(answer == 2) return;

        List<String> libraryNames = cardService.showLibraryName(studentId);

        if(!libraryNames.isEmpty()){
            String formatData = formatLibraryNames(libraryNames);
            System.out.println(String.format("현재 %s에 등록되어 있습니다. 추가로 등록하시겠습니까?", formatData));
            System.out.println("(1) 예  (2) 아니오");
            answer = scanner.nextInt(); if(answer == 2) return;
        }


        List<CardService.ShowSelectLibrary> showSelectLibraries = cardService.showLibraryWithoutStudent(studentId);
        System.out.println("어느 도서관에 등록하시겠습니까?");
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger index = new AtomicInteger(1);

        showSelectLibraries.forEach(item -> {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(" ");  // 각 항목 사이에 공백 추가
            }
            stringBuilder.append(String.format("[%d] %s 도서관", index.getAndIncrement(), item.getLibraryDAO().getName()));
        });

        System.out.println(stringBuilder.toString());
        answer = scanner.nextInt();

        int finalAnswer = answer;
        showSelectLibraries.forEach(item -> {
            if(item.getNumber() == finalAnswer) {
                cardService.registerCard(studentId, item.getLibraryDAO().getId());
                System.out.println(String.format("%s 도서관을 선택하였습니다.", item.getLibraryDAO().getName()));
                System.out.println(String.format("책 대여는 %s 도서관에서만 가능합니다.", item.getLibraryDAO().getName()));
                return;
            }
        });
    }

    private String formatLibraryNames(List<String> libraryNames) {
        return libraryNames.stream()
                .map(name -> name + " 도서관")
                .collect(Collectors.joining(", "));
    }

}
