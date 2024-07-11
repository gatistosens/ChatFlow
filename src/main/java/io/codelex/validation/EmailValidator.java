package io.codelex.validation;

import io.codelex.question.Question;

import java.util.regex.Pattern;

public class EmailValidator implements Validator {

    private static final Pattern pattern = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean validate(String input, Question question) {
        return input != null && pattern.matcher(input).matches();
    }
}
