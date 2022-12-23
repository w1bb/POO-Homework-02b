import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import execution.interpreters.Interpreter;
import fileio.Input;

import java.io.File;
import java.io.IOException;

public final class Main {
    private Main() {
    }

    /**
     * This is the main entrypoint of the algorithm
     * @param args args[0] should contain the input filename
     * @throws IOException when there is a problem with the input / output file
     */
    public static void main(final String[] args) throws IOException {
        String inputFilename = args[0];
        if (inputFilename.contains("basic_2.json")) {
            System.out.println("Working on current test...");
            solveInput(inputFilename, "results.out");
        }
    }

    /**
     * This method solves the problem for a given input file and outputs the result in a separate
     * file.
     * @param inputFilename the input filename (or path)
     * @param outputFilename the output filename (or path)
     * @throws IOException when there is a problem with the input / output file
     */
    public static void solveInput(final String inputFilename,
                                  final String outputFilename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(inputFilename), Input.class);

        // Entrypoint
        Interpreter interpreter = new Interpreter(inputData);
        ArrayNode output = interpreter.runActions();

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outputFilename), output);
        objectWriter.writeValue(new File(outputFilename + ".txt"), output);
    }
}
