package asc.com.agenda.domain.service;

import asc.com.agenda.domain.entity.Agenda;
import asc.com.agenda.domain.repository.AgendaRepository;
import asc.com.agenda.domain.service.model.IComumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public final class AgendaService implements IComumService<Agenda> {
    private final AgendaRepository agendaRepository;


    @Override
    public List<Agenda> todos() {
        return agendaRepository.findAll();
    }

    @Override
    public Agenda buscarPorId(Long id) {
        return agendaRepository.findById(id).orElse(Agenda.builder().build());
    }

    public Optional<Agenda> buscarPorIdOp(Long id) {
        return agendaRepository.findById(id);
    }

    @Override
    public Agenda salvar(Agenda obj) {
        return agendaRepository.save(obj);
    }

    @Override
    public void deletar(Long id) {
        agendaRepository.deleteById(id);
    }
}
