package dev.ralphgonzales.spendlens.shared.service;

import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface CrudService<T,R> {
    R create(T requestDto);
    R update(T requestDto, Long id);
    R getById(Long id);
    void delete(Long id);
    PaginatedResponse<R> findAll(Pageable pageable);
}
