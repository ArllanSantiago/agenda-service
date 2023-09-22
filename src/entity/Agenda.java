package asc.com.agenda.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Builder
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "AGENDA")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    @Column(name = "data_hora")
    private LocalDateTime horario;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne
    private Paciente paciente;
}
