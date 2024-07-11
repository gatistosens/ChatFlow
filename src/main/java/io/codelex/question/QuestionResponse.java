package io.codelex.question;

public class QuestionResponse {
    private String output;
    private String error;

    public QuestionResponse(String output, String error) {
        this.output = output;
        this.error = error;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        if (output != null && !output.isEmpty()) {
            return output;
        } else if (error != null && !error.isEmpty()) {
            return error;
        }
        return "";
    }
}
