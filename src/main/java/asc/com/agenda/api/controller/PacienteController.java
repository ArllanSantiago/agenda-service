package asc.com.agenda.api.controller;


import asc.com.agenda.api.mapper.PacienteMapper;
import asc.com.agenda.api.request.PacienteRequest;
import asc.com.agenda.api.response.PacienteResponse;
import asc.com.agenda.domain.entity.Paciente;

import asc.com.agenda.exception.NotFoundException;
import asc.com.agenda.domain.service.PacienteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
@Slf4j
public class PacienteController {

    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PacienteResponse> salvar(@RequestBody final PacienteRequest pacienteRequest){
        log.info("Tentando Salvar Parciente {}",pacienteRequest);
        final Paciente pacienteSalvo = pacienteService.salvar(pacienteMapper.toPaciente(pacienteRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteMapper.toPacienteResponse(pacienteSalvo));
        //return pacienteMapper.toPacienteResponse(pacienteSalvo);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<PacienteResponse> listarTodos(){
        log.info("Tentando Listar Todos os Parcientes ");
        return pacienteService.todos().stream()
                .map(paciente -> pacienteMapper.toPacienteResponse(paciente))
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public PacienteResponse buscarPorId(@PathVariable final Long id){
        log.info(String.format("Tentando buscar o Parciente de ID: %s",id));
        Optional<Paciente> optPaciente = pacienteService.buscarPorId(id);
        if (optPaciente.isEmpty()) {
            throw new NotFoundException(String.format("Id %s não encontrado",id));
        }

        return pacienteMapper.toPacienteResponse(optPaciente.get());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PacienteResponse alterar(@Valid @PathVariable Long id, @Valid @RequestBody final PacienteRequest pacienteRequest){
        log.info(String.format("Tentando alterar o Parciente: %s - %s",pacienteRequest.getNome(), pacienteRequest.getCpf()));
        final Paciente paciente = pacienteMapper.toPaciente(pacienteRequest);
        final Paciente pacienteSalvo = pacienteService.alterar(id,paciente);
        return pacienteMapper.toPacienteResponse(pacienteSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable final Long id){
        log.info(String.format("Tentando deletar o Parciente de ID: %s",id));
        pacienteService.deletar(id);
    }
}
