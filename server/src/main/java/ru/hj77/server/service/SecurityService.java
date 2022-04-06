package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hj77.server.config.CustomAuthenticationProvider;
import ru.hj77.server.exception.NoSuchDataException;
import ru.hj77.server.repository.CardRepository;


@AllArgsConstructor
@Service
public class SecurityService {
    CardRepository cardRepository;

    public boolean cardIsAuth(Long cardId, int pin){
//        CustomAuthenticationProvider auth = new CustomAuthenticationProvider();

        return cardRepository.findById(cardId)
                .orElseThrow(NoSuchDataException::new)
                .getPin() == pin;
    }

}
