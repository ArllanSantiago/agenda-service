package asc.com.agenda.api.mapper;

import asc.com.agenda.api.request.AgendaRequest;
import asc.com.agenda.api.response.AgendaResponse;
import asc.com.agenda.api.response.PacienteResponse;
import asc.com.agenda.domain.entity.Agenda;
import asc.com.agenda.domain.entity.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendaMapper {
    private final ModelMapper mapper;

    public Agenda toAgenda(AgendaRequest request){
        return Agenda.builder()
                .descricao(request.getDescricao())
                .horario(request.getHorario())
                .paciente(Paciente.builder()
                        .cpf(request.getPacienteCpf())
                        .build())
                .build();
    }

    public AgendaResponse toAgendaResponse(Agenda agenda){

        final PacienteResponse mapPacienteResponse = mapper.map(agenda.getPaciente(), PacienteResponse.class);
        final AgendaResponse mapAgendaResponse = mapper.map(agenda, AgendaResponse.class);
        mapAgendaResponse.setPacienteResponse( mapPacienteResponse);
        return mapAgendaResponse;
    }
}
