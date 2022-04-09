package ru.hj77.server.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.hj77.server.entity.Card;
import ru.hj77.server.repository.CardRepository;

import java.util.Collection;
import java.util.Optional;


@AllArgsConstructor
@Service
public class SecurityService {//implements UserDetailsService {
    CardRepository cardRepository;

    public boolean cardIsAuth(Long cardId, int pin){
        return true;
//        return cardRepository.findById(cardId)
//                .orElseThrow(NoSuchDataException::new)
//                .getPin() == pin;
    }

    public Card findByCardId(String username){
        Long cardId = Long.parseLong(username);
        return cardRepository.findById(cardId)
                .orElseThrow(RuntimeException::new);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Card card = findByCardId(username);
//        return new User(String.valueOf(card.getId_card()),
//                card.getPin(),
//                null);
//    }

}
