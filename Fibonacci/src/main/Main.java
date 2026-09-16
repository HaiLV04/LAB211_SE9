package main;

import model.Fibonacci;
import view.View;
import controller.Controller;

public class Main {
    public static void main(String[] args) {
        int sequenceLength = 45;
        Fibonacci model = new Fibonacci(sequenceLength);
        View view = new View();
        Controller controller = new Controller(model, view, sequenceLength);
        controller.run();
    }
}
