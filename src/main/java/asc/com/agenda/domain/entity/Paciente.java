package asc.com.agenda.domain.entity;

import asc.com.agenda.exception.CustomException;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpStatus;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
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

    public void atribuirId(Long id){
        if (this.getId() > 0){
            throw new CustomException("Para atribuir um identificado, o campo id deve estar vazio", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.id = id;
    }
}
