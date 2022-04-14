package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hj77.server.exception.NoSuchDataException;
import ru.hj77.server.entity.Card;
import ru.hj77.server.repository.CardRepository;



@AllArgsConstructor
@Service
public class CardService {

    private final String NOT_FOUND_EXCEPTION = "Ошибка при выполнении запроса. Обратитесь в тех. поддержку";

    private CardRepository repository;

    public double getBalance(Long cardId) {
        return repository.findById(cardId)
                .orElseThrow(() -> new NoSuchDataException(NOT_FOUND_EXCEPTION + cardId))
                .getBalance();
    }

    public double withdrawMoneyFromTheCard(Long cardId, double money){
        Card card = repository.findById(cardId)
                .orElseThrow(() -> new NoSuchDataException(NOT_FOUND_EXCEPTION + cardId));

        card.setBalance(card.getBalance() + money);
        repository.save(card);

        return card.getBalance();
    }

    public double depositMoneyFromTheCard(Long cardId, double money) {
        Card card = repository.findById(cardId)
                .orElseThrow(() -> new NoSuchDataException(NOT_FOUND_EXCEPTION + cardId));

        double actualBalanceCard = card.getBalance();

        if  (actualBalanceCard - money < 0.0 )
            throw new NoSuchDataException("На Вашем счёте недостаточно средств.");
        else {
            card.setBalance(card.getBalance() - money);
            repository.save(card);
        }

        return card.getBalance();
    }


}
