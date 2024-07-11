import io.codelex.input.InputProvider;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class MockInputProvider implements InputProvider {
    private final Queue<String> inputs = new LinkedList<>();

    public MockInputProvider(String... inputs) {
        Collections.addAll(this.inputs, inputs);
    }

    @Override
    public String getInput() {
        return inputs.poll();
    }
}
