package controller;

import model.QuickSort;
import view.View;

public class Controller {
    public void run() {
        int n = View.inputInteger("Enter number of array: ", true);
        int[] array = View.generateRandom(n);
        
        System.out.print("Unsorted array: ");
        View.displayArray(array);
        
        QuickSort.sort(array, 0, array.length - 1);
        
        System.out.print("Sorted array: ");
        View.displayArray(array);
    }
}
