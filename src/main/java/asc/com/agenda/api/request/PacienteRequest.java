package asc.com.agenda.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {
    @NotBlank(message = "Nome é campo obrigatório")
    private String nome;
    @NotBlank(message = "Sobrenome é campo obrigatório")
    private String sobrenome;
    @NotBlank(message = "Cpf é campo obrigatório")
    private String cpf;
    private String email;
    private String endereco;
}
