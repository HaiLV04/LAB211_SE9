package model;

import view.View;

/**
 * Class QuickSort
 */
public class QuickSort {
    
    private static int step = 1;

    private static int quickSort(int[] array, int left, int right) {
        int pivot = array[left + (right - left) / 2];
        int i = left;
        int j = right;

        System.out.printf("Bước %d [Phân vùng: từ chỉ số %d đến %d | Giá trị Pivot = %d]:\n",
                step++, left, right, pivot);

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            if (i <= j) {
                if (i < j && array[i] != array[j]) {
                    System.out.printf("   -> Swap array[%d](%d) và array[%d](%d)\n",
                            i, array[i], j, array[j]);
                }
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        
        System.out.print("   => Trạng thái mảng sau lần phân vùng này: ");
        View.displayArray(array);

        return i; 
    }

    public static void sort(int[] array, int left, int right) {
        if (array == null || array.length <= 1) {
            return;
        }
        
        int indexPivot = quickSort(array, left, right);
        
        if (left < indexPivot - 1) {
            sort(array, left, indexPivot - 1);
        }
        if (indexPivot < right) {
            sort(array, indexPivot, right);
        }
    }
}
