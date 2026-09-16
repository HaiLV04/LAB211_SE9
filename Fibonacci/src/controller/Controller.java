package controller;

import model.Fibonacci;
import view.View;

public class Controller {
    private Fibonacci model;
    private View view;
    private int sequenceLength;

    public Controller(Fibonacci model, View view, int sequenceLength) {
        this.model = model;
        this.view = view;
        this.sequenceLength = sequenceLength;
    }

    public void run() {
        view.displayTitle();
        for (int i = 0; i < sequenceLength; i++) {
            int value = model.getFibonacci(i);
            view.displayFibonacci(value, i == sequenceLength - 1);
        }
        
        // Tests
        view.displayMessage("\nPosition of each element:");
        for (int i = 0; i < sequenceLength; i++) {
            view.displayMessage("Index " + i + ": " + model.getFibonacci(i));
        }

        System.out.print("[");
        for (int i = 0; i < sequenceLength; i++) {
            System.out.print("F(" + i + ")=" + model.getFibonacci(i));
            if (i < sequenceLength - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        
        view.displayMessage("F(0) = " + model.getFibonacci(0));
        view.displayMessage("F(1) = " + model.getFibonacci(1));
        view.displayMessage("F(" + (sequenceLength - 1) + ") = " + model.getFibonacci(sequenceLength - 1));
    }
}
