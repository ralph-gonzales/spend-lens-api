package dev.ralphgonzales.spendlens.bank.service;

import dev.ralphgonzales.spendlens.bank.dto.BankDto;
import dev.ralphgonzales.spendlens.bank.mapper.BankMapper;
import dev.ralphgonzales.spendlens.bank.repository.BankRepository;
import dev.ralphgonzales.spendlens.shared.dto.PaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService{

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Override
    public BankDto create(BankDto dto) {
        return null;
    }

    @Override
    public BankDto update(BankDto dto) {
        return null;
    }

    @Override
    public BankDto getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PaginatedResponse<BankDto> findAll(Pageable pageable) {
        return null;
    }

    @Cacheable(value = "banks", key = "'allActiveBanks'")
    @Override
    public List<BankDto> getAllActiveBanks(){
        return bankMapper.toDtos(bankRepository.findByActiveTrue());
    }
}
