package asc.com.agenda.domain.entity;

import asc.com.agenda.exception.CustomException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Date;

import static java.time.LocalDateTime.now;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
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


    public void atribuirParciente(Paciente paciente){
        if (this.paciente.getId() > 0){
            throw new CustomException("Para atribuir um Paciente, " +
                    "o campo paciente.id deve estar vazio", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.paciente  = paciente;
    }

    public void atribuirId(Long id) {
        if(this.getId() > 0){
            throw new CustomException("Para atribuir um identificado, o campo id " +
                    "deve estar vazio", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.id = id;
    }

    public void atribuirDataCriacao(LocalDateTime dateCriacao) {
        if (dateCriacao == null){
            dateCriacao = now();
        }

        this.dataCriacao = dateCriacao;

    }
}
