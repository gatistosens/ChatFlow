package io.codelex.question;

import io.codelex.validation.Validator;

import java.util.List;

public class Question {
    private String template;
    private Validator validator;
    private QuestionType questionType;
    private String responseKey;
    private List<String> choices;

    public Question(String template, Validator validator, QuestionType questionType, String responseKey) {
        this.template = template;
        this.validator = validator;
        this.questionType = questionType;
        this.responseKey = responseKey;
    }

    public Question(String template, Validator validator, QuestionType questionType, String responseKey, List<String> choices) {
        this.template = template;
        this.validator = validator;
        this.questionType = questionType;
        this.responseKey = responseKey;
        this.choices = choices;
    }

    public Question(String template, QuestionType questionType) {
        this.template = template;
        this.questionType = questionType;
    }

    public boolean isValid(String userInput) {
        return validator.validate(userInput, this);
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public String getResponseKey() {
        return responseKey;
    }

    public void setResponseKey(String responseKey) {
        this.responseKey = responseKey;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }


}
