package dev.ralphgonzales.spendlens.shared.validation.contract;

public interface BusinessValidator<R, E> {
    void createValidate(R request);
    void updateValidate(R request, E existing);
}
