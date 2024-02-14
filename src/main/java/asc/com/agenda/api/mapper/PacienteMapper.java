package asc.com.agenda.api.mapper;

import asc.com.agenda.api.request.PacienteRequest;
import asc.com.agenda.api.response.PacienteResponse;
import asc.com.agenda.configuration.ModelMapperConfig;
import asc.com.agenda.domain.entity.Paciente;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteMapper {
    private final ModelMapper mapper;
    public Paciente toPaciente(PacienteRequest pacienteRequest){
        //return mapper.map(pacienteRequest,Paciente.class);

        // Caso a Classe de destino tenho o Set privativo
        return Paciente.builder()
                .email(pacienteRequest.getEmail())
                .endereco(pacienteRequest.getEndereco())
                .nome(pacienteRequest.getNome())
                .sobrenome(pacienteRequest.getSobrenome())
                .cpf(pacienteRequest.getCpf())
                .build();


    }
    public  PacienteResponse toPacienteResponse(Paciente paciente){
        return mapper.map(paciente, PacienteResponse.class);
    }
}
