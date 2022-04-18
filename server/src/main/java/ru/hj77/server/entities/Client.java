package ru.hj77.server.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "clients")
@NoArgsConstructor
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_client;

    private String name;

    private String surname;

    private String patronymic;

    private Date date_of_birth;

    @OneToMany(mappedBy="id_client",fetch=FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<Card> cards;
}
