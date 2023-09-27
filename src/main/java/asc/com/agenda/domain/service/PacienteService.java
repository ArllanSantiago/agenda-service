package asc.com.agenda.domain.service;


import asc.com.agenda.domain.entity.Paciente;
import asc.com.agenda.domain.exception.ComumException;
import asc.com.agenda.domain.repository.PacienteRepository;
import asc.com.agenda.domain.service.model.IComumService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PacienteService implements IComumService<Paciente> {
    private final PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> todos() {
        List<Paciente> a = pacienteRepository.findAll();
        return a;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElse(Paciente.builder().build());
    }

    @Override
    public Paciente salvar(Paciente obj) {
        final Optional<Paciente> optPaciente = pacienteRepository.findByCpf(obj.getCpf());

        if (optPaciente.isPresent()){
            if(optPaciente.get().getId() !=  obj.getId()){
                throw new ComumException(String.format("CPF {} já cadastrado",optPaciente.get().getCpf().toString()));
            }
        }

        return pacienteRepository.save(obj);
    }

    @Override
    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
