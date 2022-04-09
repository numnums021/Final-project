package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hj77.server.exception.NoSuchDataException;
import ru.hj77.server.repository.CardRepository;


@AllArgsConstructor
@Service
public class SecurityService {
    CardRepository cardRepository;

    public boolean cardIsAuth(Long cardId, int pin){
        return true;
//        return cardRepository.findById(cardId)
//                .orElseThrow(NoSuchDataException::new)
//                .getPin() == pin;
    }

}
