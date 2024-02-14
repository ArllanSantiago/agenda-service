package asc.com.agenda.api.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponse {
    private long id;
    private String nome;
    private String sobrenome;
    private String cpf;

}
