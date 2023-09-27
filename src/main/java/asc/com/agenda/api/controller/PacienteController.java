package asc.com.agenda.api.controller;


import asc.com.agenda.domain.entity.Paciente;
import asc.com.agenda.domain.service.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
@RequiredArgsConstructor
@Slf4j

public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Paciente> salvar(@RequestBody final Paciente pacienteRequest){
        log.info("Tentando Salvar Parciente {}",pacienteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.salvar(pacienteRequest));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Paciente> listarTodos(){
        return pacienteService.todos();
    }
}
