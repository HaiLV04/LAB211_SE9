package model;

import java.util.Arrays;

/**
 * Class MergeSort
 * 
 * Chức năng: Chứa logic thực hiện thuật toán sắp xếp trộn (Merge Sort).
 */
public class MergeSort {

    private static int stepCounter = 1;

    public static void mergeSort(int[] array, int left, int right) {
        if (left >= right) {
            return; 
        }

        int mid = left + (right - left) / 2;

        System.out.printf("Bước %d [CHIA MẢNG] Phân đoạn từ chỉ số %d đến %d | Chia đôi tại vị trí giữa = %d\n",
                stepCounter++, left, right, mid);
        System.out.println("   -> Phân vùng con bên Trái: chỉ số " + left + " đến " + mid);
        System.out.println("   -> Phân vùng con bên Phải: chỉ số " + (mid + 1) + " đến " + right);

        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);

        merge(array, left, mid, right);
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = array[mid + 1 + j];
        }

        System.out.printf("Bước %d [TRỘN MẢNG] Đang gộp hai vùng con đã sắp xếp vào mảng gốc từ %d đến %d\n",
                stepCounter++, left, right);
        System.out.println("   * Mảng con tạm TRÁI L[] : " + Arrays.toString(L));
        System.out.println("   * Mảng con tạm PHẢI R[] : " + Arrays.toString(R));
        
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }

        System.out.println("   => Trạng thái mảng gốc hiện tại: " + Arrays.toString(array));
        System.out.println("----------------------------------------------------------------");
    }
}
