package io.codelex.validation;

import io.codelex.question.Question;

public class ChoiceValidator implements Validator {

    @Override
    public boolean validate(String input, Question question) {
        return question.getChoices().contains(input);
    }
}
