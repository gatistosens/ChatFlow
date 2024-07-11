package io.codelex.validation;

import io.codelex.question.Question;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements Validator {

    private final DateTimeFormatter formatter;

    public DateValidator() {
        this.formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    @Override
    public boolean validate(String input, Question question) {
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
