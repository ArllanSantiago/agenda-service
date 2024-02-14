package asc.com.agenda.domain.service;


import asc.com.agenda.domain.entity.Paciente;
import asc.com.agenda.exception.CustomException;
import asc.com.agenda.domain.repository.PacienteRepository;
import asc.com.agenda.domain.service.model.IComumService;
import asc.com.agenda.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PacienteService implements IComumService<Paciente> {
    private final PacienteRepository pacienteRepository;

    @Override
    public List<Paciente> todos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente salvar(Paciente obj) {
        final Optional<Paciente> optPaciente = pacienteRepository.findByCpf(obj.getCpf());

        if (optPaciente.isPresent()){
            if(optPaciente.get().getId() !=  obj.getId()){
                throw new CustomException(
                        String.format("CPF %s já cadastrado", optPaciente.get().getCpf())
                        ,HttpStatus.UNPROCESSABLE_ENTITY
                );
            }
        }

        return pacienteRepository.save(obj);
    }

    @Override
    public Paciente alterar(Long id, Paciente obj) {
        final Optional<Paciente> paciente = this.buscarPorId(id);

        if (paciente.isEmpty()){
            throw  new NotFoundException(String.format("Paciente com id: %s não encontrado",id));
        }
        obj.atribuirId(id);
        return this.salvar(obj);
    }

    @Override
    public void deletar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
