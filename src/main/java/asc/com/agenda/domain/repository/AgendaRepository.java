package asc.com.agenda.domain.repository;

import asc.com.agenda.domain.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    Optional<Agenda> findByHorario(LocalDateTime horario);
}
