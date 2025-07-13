package dev.ralphgonzales.spendlens.shared.service;

import java.util.List;

public interface CrudService<D> {
    D create(D dto);
    D update(D dto);
    void delete(D dto);
    List<D> findAll();
}
