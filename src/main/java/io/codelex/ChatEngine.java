package io.codelex;

import io.codelex.input.InputProvider;
import io.codelex.question.Question;
import io.codelex.question.QuestionResponse;
import io.codelex.question.QuestionType;

import java.util.Map;

public class ChatEngine {

    private final InputProvider inputProvider;
    private final Question question;
    private final Answers answer;

    public ChatEngine(InputProvider inputProvider, Question question, Answers answer) {
        this.inputProvider = inputProvider;
        this.question = question;
        this.answer = answer;
    }

    public String askQuestion() {
        StringBuilder questionText = new StringBuilder("🤖: " + formatQuestionText(question.getTemplate(), answer.getAllAnswers()));

        if (question.getQuestionType() == QuestionType.SINGLE_CHOICE) {
            questionText.append("\n👧: ").append(question.getChoices());
        } else if (question.getQuestionType() != QuestionType.NO_INPUT) {
            questionText.append("\n👧:");
        }

        return questionText.toString();
    }


    public QuestionResponse handleUserInput() {
        if (question.getQuestionType() == QuestionType.NO_INPUT) {
            return new QuestionResponse(null, null);
        } else {
            String userInput = inputProvider.getInput();
            if (question.isValid(userInput)) {
                answer.addAnswer(question.getResponseKey(), userInput);
                return new QuestionResponse("\n👧: " + userInput, null);
            } else {
                return new QuestionResponse(null, "🤖: Invalid input, try again!\n");
            }
        }
    }


    private String formatQuestionText(String template, Map<String, String> values) {
        String formattedTemplate = template;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            formattedTemplate = formattedTemplate.replace(placeholder, entry.getValue());
        }
        return formattedTemplate;
    }
}
