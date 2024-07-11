package io.codelex;

import io.codelex.input.ConsoleInputProvider;
import io.codelex.question.Question;
import io.codelex.question.QuestionResponse;
import io.codelex.question.QuestionType;
import io.codelex.validation.ChoiceValidator;
import io.codelex.validation.DateValidator;
import io.codelex.validation.EmailValidator;
import io.codelex.validation.TextValidator;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleInputProvider consoleInputProvider = new ConsoleInputProvider();
        Answers answer = new Answers();

        List<Question> questions = Arrays.asList(
                new Question("Hi there, I'm Jeff 👋", QuestionType.NO_INPUT),
                new Question("Your new best friend for finding great loan offers!", QuestionType.NO_INPUT),
                new Question("First things first - let's get your account set up 🛠️", QuestionType.NO_INPUT),
                new Question("What is your first name?", new TextValidator(), QuestionType.TEXT, "firstName"),
                new Question("And what is your last name?", new TextValidator(), QuestionType.TEXT, "lastName"),
                new Question("{firstName}, what's your email address?", new EmailValidator(), QuestionType.TEXT, "email"),
                new Question("Fantastic. We are 70% done with the setup!\nYour age is another important value for finding the best offers. Please enter your date of birth 📅", new DateValidator(), QuestionType.TEXT, "birthDate"),
                new Question("And what do you need the money for?", new ChoiceValidator(), QuestionType.SINGLE_CHOICE, "purpose", Arrays.asList("Home", "Car", "Holidays", "Big Event"))
        );

        for (Question question : questions) {
            ChatEngine chatEngine = new ChatEngine(consoleInputProvider, question, answer);
            System.out.println(chatEngine.askQuestion());

            QuestionResponse response;
            do {
                response = chatEngine.handleUserInput();
                if (response.getError() != null) {
                    System.out.println(response.getError());
                }
            } while (response.getError() != null);

        }

        System.out.println("All answers: " + answer.getAllAnswers());
    }
}
