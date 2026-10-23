package model;

import java.util.Random;

/**
 * Chức năng: Quản lý dữ liệu mảng và thuật toán sắp xếp nổi bọt (Bubble Sort).
 */
public class ArrayModel {

    protected int[] array;

    /**
     * Khởi tạo đối tượng ArrayModel.
     */
    public ArrayModel() {
    }

    /**
     * Chức năng: Tạo mảng số nguyên ngẫu nhiên theo kích thước chỉ định.
     * Luồng xử lý: Cấp phát mảng kích thước n và sinh giá trị ngẫu nhiên trong khoảng [-n, n].
     *
     * @param n số lượng phần tử
     */
    public void generateRandomArray(int n) {
        Random random = new Random();
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(2 * n + 1) - n;
        }
    }

    /**
     * Chức năng: Sắp xếp mảng tăng dần bằng thuật toán Bubble Sort.
     * Luồng xử lý: So sánh từng cặp phần tử liền kề và đổi chỗ nếu sai thứ tự, dừng sớm nếu không còn hoán đổi nào.
     */
    public void bubbleSort() {
        if (array == null || array.length < 2) {
            return;
        }

        boolean swapped;
        int length = array.length;

        for (int i = 0; i < length - 1; i++) {
            swapped = false;
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Chức năng: Hoán đổi giá trị hai phần tử trong mảng.
     * Luồng xử lý: Dùng biến tạm để đổi chỗ hai phần tử tại firstIndex và secondIndex.
     *
     * @param firstIndex chỉ số phần tử thứ nhất
     * @param secondIndex chỉ số phần tử thứ hai
     */
    protected void swap(int firstIndex, int secondIndex) {
        int temporaryValue = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temporaryValue;
    }

    /**
     * Lấy mảng số nguyên hiện tại.
     *
     * @return mảng số nguyên
     */
    public int[] getArray() {
        return array;
    }
}
