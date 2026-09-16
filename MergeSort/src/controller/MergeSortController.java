package controller;

import model.MergeSort;
import view.View;

public class MergeSortController {
    public void run() {
        int n = View.inputPositiveNumber();
        int[] array = View.generateRandom(n);
        
        System.out.print("Unsorted array: ");
        View.displayArray(array);
        
        MergeSort.mergeSort(array, 0, array.length - 1);
        
        System.out.print("Sorted array: ");
        View.displayArray(array);
    }
}
