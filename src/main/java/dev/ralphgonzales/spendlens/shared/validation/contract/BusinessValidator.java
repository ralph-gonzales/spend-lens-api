package dev.ralphgonzales.spendlens.shared.validation.contract;

public interface BusinessValidator<T> {
    void createValidate(T target);
    void updateValidate(T target);
}
