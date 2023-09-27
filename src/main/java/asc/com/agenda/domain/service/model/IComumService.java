package asc.com.agenda.domain.service.model;

import java.util.List;

public interface IComumService<T> {
    public List<T> todos();
    T buscarPorId(final Long id);
    T salvar (final T obj);
    void deletar (final Long id);
}
