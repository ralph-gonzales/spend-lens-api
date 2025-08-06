package dev.ralphgonzales.spendlens.shared.service;

import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface CrudService<D> {
    D create(D dto);
    D update(D dto);
    D getById(Long id);
    void delete(Long id);
    PaginatedResponse<D> findAll(Pageable pageable);
}
