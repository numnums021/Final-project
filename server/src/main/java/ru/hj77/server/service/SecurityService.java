package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hj77.server.exception.NoSuchDataException;
import ru.hj77.server.repository.CardRepository;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class SecurityService {

    CardRepository cardRepository;

    public boolean cardIsAuth(Long cardId, int pin){
        String exc = "Данная карта не обслуживается. " +
                "Пожалуйста, обратитесь в тех. поддержку. Карта №";
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException(exc + cardId))
                .getPin() == pin;
    }

}
