package controller;

import model.BinarySearch;
import view.Validator;
import java.util.Arrays;

public class Controller {

    public void execute() {
        int number = Validator.getInt("Enter number of array: ",
                "Number must be >0", "Invalid!", 1, Integer.MAX_VALUE);
        BinarySearch array = null;
        try {
            array = new BinarySearch(number);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        if (array != null) {
            array.display();
            int key = Validator.getInt("Enter search value: ",
                    "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
            
            int[] indexes = array.binarySearchAll(key);
            if (indexes.length == 0) {
                System.out.println("Can not found");
            } else {
                System.out.println("Found " + key + " at index: " + Arrays.toString(indexes));
            }
        }
    }
}
