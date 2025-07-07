package dev.ralphgonzales.spendlens;

import org.springframework.boot.SpringApplication;

public class TestSpendLensApplication {

    public static void main(String[] args) {
        SpringApplication.from(SpendLensApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
