package dev.ralphgonzales.spendlens.bank.service;

import dev.ralphgonzales.spendlens.bank.dto.BankRequestDto;
import dev.ralphgonzales.spendlens.bank.dto.BankResponseDto;
import dev.ralphgonzales.spendlens.bank.repository.BankRepository;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService{

    private final BankRepository bankRepository;

    @Override
    public BankResponseDto create(BankRequestDto dto) {
        return null;
    }

    @Override
    public BankResponseDto update(BankRequestDto dto, Long id) {
        return null;
    }

    @Override
    public BankResponseDto getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PaginatedResponse<BankResponseDto> findAll(Pageable pageable) {
        return null;
    }

    @Cacheable(value = "banks", key = "'allActiveBanks'")
    @Override
    public Set<Long> getAllActiveBanks(){
        return bankRepository.findIdsByActiveTrue();
    }
}
