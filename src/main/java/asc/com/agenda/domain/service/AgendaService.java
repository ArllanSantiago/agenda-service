package asc.com.agenda.domain.service;

import asc.com.agenda.domain.entity.Agenda;
import asc.com.agenda.domain.entity.Paciente;
import asc.com.agenda.domain.repository.AgendaRepository;
import asc.com.agenda.domain.repository.PacienteRepository;
import asc.com.agenda.domain.service.model.IComumService;
import asc.com.agenda.exception.CustomException;
import asc.com.agenda.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AgendaService implements IComumService<Agenda> {
    private final AgendaRepository agendaRepository;
    private final PacienteRepository pacienteRepository;

    @Override
    public List<Agenda> todos() {

        return agendaRepository.findAll();
    }

    @Override
    public Optional<Agenda> buscarPorId(Long id) {
        return agendaRepository.findById(id);
    }


    @Override
    public Agenda salvar(Agenda obj) {
        //TODO: Validações
        //1: Se o Paciente existe
        final Optional<Paciente> optPaciente = pacienteRepository.findByCpf(obj.getPaciente().getCpf());

        if (optPaciente.isEmpty()) {
            throw new NotFoundException("Paciente não encontrado");
        }
        //2: Se o Horario estar disponiviel
        final Optional<Agenda> optAgenda = agendaRepository.findByHorario(obj.getHorario());
        if (optAgenda.isPresent()) {
            throw new CustomException("Horário indisponível", HttpStatus.NOT_ACCEPTABLE);
        }
        obj.atribuirParciente(optPaciente.get());
        obj.atribuirDataCriacao(now());
        return agendaRepository.save(obj);
    }

    @Override
    public Agenda alterar(Long id, Agenda obj) {
        //TODO: Validações
        //1: Se o Id da Agente existe
        final Optional<Agenda> optAgenda = agendaRepository.findById(id);

        if (optAgenda.isEmpty()) {
            throw new NotFoundException("Agenda não encontrada");
        }
        obj.atribuirId(id);

        return this.salvar(obj);
    }

    @Override
    public void deletar(Long id) {
        agendaRepository.deleteById(id);
    }
}
