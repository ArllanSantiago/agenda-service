package asc.com.agenda.api.controller;

import asc.com.agenda.api.mapper.AgendaMapper;
import asc.com.agenda.api.request.AgendaRequest;
import asc.com.agenda.api.response.AgendaResponse;
import asc.com.agenda.domain.entity.Agenda;
import asc.com.agenda.domain.service.AgendaService;
import asc.com.agenda.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Agenda", description = "Cadastro de Agenda")
public class AgendaController {
    private final AgendaService agendaService;
    private final AgendaMapper agendaMapper;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Salvar",description = "Adiciona uma agenda")
    @ApiResponses(value={
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Paciente do Agendamento não encontrado")
    })
    public AgendaResponse salvar(@Valid @RequestBody AgendaRequest request){
        log.info("Tentando Salvar a Agenda {}",request);
        final Agenda agenda = agendaService.salvar(agendaMapper.toAgenda(request));
        return agendaMapper.toAgendaResponse(agenda);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar Todos", description = "Listar agendamentos realizados")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "busca realizada com sucesso")
    })
    public List<AgendaResponse> listarTodos(){
        log.info("Tentando Listar todos os agendamentos");
        return agendaService.todos().stream()
                .map(agenda -> agendaMapper.toAgendaResponse(agenda))
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    @Operation(summary = "Buscar por Identificador", description = "Buscar Agendamento pelo campo id")
    @ApiResponses(value={
            @ApiResponse(responseCode = "302", description = "Agendamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public AgendaResponse buscarPorId(@PathVariable Long id){
        log.info("Tentando buscar um agendamento por id");
        final Optional<Agenda> optAgenda = agendaService.buscarPorId(id);
        if (optAgenda.isEmpty()) {
            throw new NotFoundException("Agendamento não encontrado");
        }
        return agendaMapper.toAgendaResponse(optAgenda.get());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar", description = "Deleta parciente pelo campo id")

    @ApiResponses(value={
            @ApiResponse(responseCode = "204", description = "Agendamento excluido")
    })
    public void delete(@PathVariable Long id){
        agendaService.deletar(id);
    }
}
