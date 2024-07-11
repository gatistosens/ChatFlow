package io.codelex;

import java.util.HashMap;
import java.util.Map;

public class Answers {
    private final Map<String, String> answers = new HashMap<>();

    public void addAnswer(String key, String value) {
        answers.put(key, value);
    }

    public String getAnswer(String key) {
        return answers.get(key);
    }

    public Map<String, String> getAllAnswers() {
        return new HashMap<>(answers);
    }
}
