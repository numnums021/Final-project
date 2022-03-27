package ru.hj77.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "cards")
@NoArgsConstructor
@Getter
@Setter
//@IdClass(CardsPK.class)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_card;

    private int pinCode;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private Client id_client;
}
