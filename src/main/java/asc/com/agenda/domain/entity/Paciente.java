package asc.com.agenda.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PACIENTE")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    private String endereco;
}
