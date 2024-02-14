package asc.com.agenda.domain.service.model;

import java.util.List;
import java.util.Optional;

public interface IComumService<T> {
    public List<T> todos();
    Optional<T> buscarPorId(final Long id);
    T salvar (final T obj);
    T alterar (final Long id,final T obj);
    void deletar (final Long id);
}
