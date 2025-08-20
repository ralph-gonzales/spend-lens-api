package dev.ralphgonzales.spendlens.shared.boot;

import dev.ralphgonzales.spendlens.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LookupWarmup implements ApplicationRunner {

    private final BankService bankService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        bankService.getAllActiveBanks();
    }
}
