package dev.service.cloud.domain.card;

public class CardConfig {
    public CardRepository cardRepository(){
        return new CardRepository();
    }

    public CardService cardService(){
        return new CardService(cardRepository());
    }
}
