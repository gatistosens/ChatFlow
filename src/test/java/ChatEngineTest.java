import io.codelex.Answers;
import io.codelex.ChatEngine;
import io.codelex.question.Question;
import io.codelex.question.QuestionType;
import io.codelex.validation.ChoiceValidator;
import io.codelex.validation.DateValidator;
import io.codelex.validation.EmailValidator;
import io.codelex.validation.TextValidator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChatEngineTest {

    TextValidator textValidator = new TextValidator();
    EmailValidator emailValidator = new EmailValidator();
    DateValidator dateValidator = new DateValidator();
    ChoiceValidator choiceValidator = new ChoiceValidator();


    @Test
    void shouldDisplayGreetingMessage() {
        Question question = new Question("Hi there, I'm Jeff 👋", QuestionType.NO_INPUT);
        Answers answer = new Answers();
        ChatEngine chatEngine = new ChatEngine(new MockInputProvider(), question, answer);
        String result = chatEngine.askQuestion();

        assertEquals("🤖: Hi there, I'm Jeff 👋", result);
    }

    @Test
    void shouldSaveUserInputWhenProvided() {
        Question question = new Question("What is your first name?", textValidator, QuestionType.TEXT, "firstName");
        MockInputProvider mockInputProvider = new MockInputProvider("Gatis");
        Answers answer = new Answers();
        ChatEngine chatEngine = new ChatEngine(mockInputProvider, question, answer);
        chatEngine.askQuestion();
        chatEngine.handleUserInput();

        assertEquals("Gatis", answer.getAnswer("firstName"));
    }

    @Test
    void shouldReplacePlaceholdersInQuestionText() {
        Question question = new Question("Nice to meet you, {firstName} {lastName}!", QuestionType.NO_INPUT);
        Answers answer = new Answers();
        answer.addAnswer("firstName", "Gatis");
        answer.addAnswer("lastName", "Tosens");
        ChatEngine chatEngine = new ChatEngine(new MockInputProvider(), question, answer);
        String result = chatEngine.askQuestion();

        assertEquals("🤖: Nice to meet you, Gatis Tosens!", result);
    }

    @Test
    void shouldNotAcceptInvalidAnswerAndPromptAgain() {
        Question question = new Question("What is your first name?", textValidator, QuestionType.TEXT, "firstName");
        MockInputProvider mockInputProvider = new MockInputProvider("");
        Answers answer = new Answers();
        ChatEngine chatEngine = new ChatEngine(mockInputProvider, question, answer);
        chatEngine.askQuestion();
        String result = chatEngine.handleUserInput().toString();

        assertEquals("🤖: Invalid input, try again!\n", result);
    }

    @Test
    void shouldValidateEmailAddresses() {
        assertTrue(emailValidator.validate("test@gmail.com", null));
        assertTrue(emailValidator.validate("test.test@gmail.com", null));
        assertTrue(emailValidator.validate(".test@gmail.com", null));
        assertFalse(emailValidator.validate("test", null));
        assertFalse(emailValidator.validate("test@", null));
        assertFalse(emailValidator.validate("test@gmail", null));
        assertFalse(emailValidator.validate("@gmail.com", null));
    }

    @Test
    void shouldPromptForEmailAddressWithProvidedFirstName() {
        Question emailQuestion = new Question("{firstName}, what's your email address?", emailValidator, QuestionType.TEXT, "email");
        MockInputProvider mockInputProvider = new MockInputProvider("gt@gmail.com");
        Answers answer = new Answers();
        answer.addAnswer("firstName", "Gatis");
        ChatEngine chatEngine = new ChatEngine(mockInputProvider, emailQuestion, answer);
        chatEngine.askQuestion();
        String result = chatEngine.handleUserInput().toString();

        assertEquals("\n👧: gt@gmail.com", result);
    }

    @Test
    void shouldValidateDates() {
        assertTrue(dateValidator.validate("20.01.1984", null));
        assertTrue(dateValidator.validate("23.12.1931", null));
        assertFalse(dateValidator.validate("35.02.1930", null));
        assertFalse(dateValidator.validate("2023.13.01", null));
        assertFalse(dateValidator.validate("01/01/2023", null));
    }

    @Test
    void shouldReturnAnswerMap() {
        MockInputProvider mockInputProvider = new MockInputProvider("Gatis", "Tos", "gt@gmail.com");
        Answers answer = new Answers();
        List<Question> questions = List.of(
                new Question("What is your first name?", textValidator, QuestionType.TEXT, "firstName"),
                new Question("And what is your last name?", textValidator, QuestionType.TEXT, "lastName"),
                new Question("{firstName}, what's your email address?", emailValidator, QuestionType.TEXT, "email")
        );
        for (Question question : questions) {
            ChatEngine chatEngine = new ChatEngine(mockInputProvider, question, answer);
            chatEngine.askQuestion();
            chatEngine.handleUserInput();
        }

        Map<String, String> answers = answer.getAllAnswers();

        assertEquals(3, answers.size());
        assertEquals("Gatis", answers.get("firstName"));
        assertEquals("Tos", answers.get("lastName"));
        assertEquals("gt@gmail.com", answers.get("email"));
    }

    @Test
    void shouldHandleSingleChoiceInput() {
        Question question = new Question("And what do you need the money for?", choiceValidator, QuestionType.SINGLE_CHOICE, "purpose", List.of("Home", "Car", "Holidays", "Big Event"));
        MockInputProvider mockInputProvider = new MockInputProvider("Home");
        Answers answer = new Answers();
        ChatEngine chatEngine = new ChatEngine(mockInputProvider, question, answer);
        chatEngine.askQuestion();
        chatEngine.handleUserInput();

        assertEquals("Home", answer.getAnswer("purpose"));
        assertEquals("🤖: And what do you need the money for?\n👧: [Home, Car, Holidays, Big Event]", chatEngine.askQuestion());
    }

    @Test
    void shouldNotAcceptInvalidChoiceAndPromptAgain() {
        Question question = new Question("And what do you need the money for?", choiceValidator, QuestionType.SINGLE_CHOICE, "purpose", List.of("Home", "Car", "Holidays", "Big Event"));
        MockInputProvider mockInputProvider = new MockInputProvider("wrong input");
        Answers answer = new Answers();
        ChatEngine chatEngine = new ChatEngine(mockInputProvider, question, answer);
        chatEngine.askQuestion();
        String result = chatEngine.handleUserInput().toString();

        assertEquals("🤖: Invalid input, try again!\n", result);
    }
}
