package io.codelex.validation;

import io.codelex.question.Question;

public class TextValidator implements Validator {

    @Override
    public boolean validate(String input, Question question) {
        return input != null && !input.isEmpty();
    }
}
