package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hj77.server.exception.NoSuchDataException;
import ru.hj77.server.entity.Card;
import ru.hj77.server.repository.CardRepository;


@AllArgsConstructor
@Service
public class CardService {

    private CardRepository repository;

    public double getBalance(Long cardId) {
        return repository.findById(cardId)
                .orElseThrow(NoSuchDataException::new)
                .getBalance();
    }

    public double withdrawMoneyFromTheCard(Long cardId, double money){
        Card card = repository.findById(cardId)
                .orElseThrow(NoSuchDataException::new);

        card.setBalance(card.getBalance() + money);
        repository.save(card);

        return card.getBalance();
    }

    public double depositMoneyFromTheCard(Long cardId, double money) {
        Card card = repository.findById(cardId)
                .orElseThrow(NoSuchDataException::new);

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
