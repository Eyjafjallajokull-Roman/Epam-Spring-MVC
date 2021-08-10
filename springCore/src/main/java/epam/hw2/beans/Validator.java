package epam.hw2.beans;

public interface Validator {
    void validateBefore() throws RuntimeException;

    void validateAfter();
}
