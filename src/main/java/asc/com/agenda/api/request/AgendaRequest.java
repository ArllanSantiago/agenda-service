package asc.com.agenda.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaRequest {
    @NotBlank(message = "descricao é campo obrigatório")
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Future
    @NotNull(message = "horario é campo obrigatório")
    private LocalDateTime horario;
    @NotBlank(message = "pacienteCpf é campo obrigatório")
    private String pacienteCpf;
}
