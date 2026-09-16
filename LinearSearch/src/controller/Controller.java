package controller;

import model.Array;
import view.Validator;
import java.util.Arrays;

public class Controller {
    public void execute() {
        int number = Validator.getInt("Enter number of array: ",
                "Number must be >0", "Invalid!", 1, Integer.MAX_VALUE);
        Array array = null;
        try {
            array = new Array(number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (array != null) {
            array.display();
        }
        
        int key = Validator.getInt("Enter search value: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        if (array != null) {
            int index2[] = array.findAllIndex(key);
            if (index2.length == 0) {
                System.out.println("Can not found");
            } else {
                System.out.println("Found " + key + " at index: " + Arrays.toString(index2));
            }
        }
    }
}
