package io.codelex.validation;

import io.codelex.question.Question;

public interface Validator {

    boolean validate(String input, Question question);

}
