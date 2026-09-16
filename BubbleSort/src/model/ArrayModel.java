package model;

import java.util.Random;

/**
 * Chức năng: Xử lý dữ liệu mảng và thuật toán Bubble Sort.
 */
public class ArrayModel {

    private int[] array;

    /**
     * Chức năng: Tạo một mảng số nguyên ngẫu nhiên dựa trên kích thước đầu vào.
     * Luồng xử lý:
     * 1. Khởi tạo đối tượng Random.
     * 2. Khởi tạo mảng với kích thước chỉ định.
     * 3. Tạo các giá trị ngẫu nhiên cho từng phần tử của mảng.
     *
     * @param size số lượng phần tử trong mảng
     */
    public void generateRandomArray(int size) {
        Random random = new Random();
        array = new int[size];

        // Generate random values for each array element.
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(size);
        }
    }

    /**
     * Chức năng: Sắp xếp mảng theo thứ tự tăng dần sử dụng thuật toán Bubble Sort.
     * Luồng xử lý:
     * 1. Kiểm tra nếu mảng null hoặc có ít hơn 2 phần tử thì dừng lại.
     * 2. Lặp lại việc so sánh các phần tử kề nhau cho đến khi không cần hoán đổi.
     * 3. So sánh các phần tử kề nhau và di chuyển giá trị lớn hơn sang phải.
     * 4. Dừng sớm nếu mảng đã được sắp xếp.
     */
    public void bubbleSort() {
        if (array == null || array.length < 2) {
            return;
        }

        boolean swapped;
        int length = array.length;

        // Repeat comparing adjacent elements until no swap is needed.
        for (int i = 0; i < length - 1; i++) {
            swapped = false;

            // Compare adjacent elements and move larger value to the right.
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                    swapped = true;
                }
            }

            // Stop early if the array is already sorted.
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Chức năng: Hoán đổi hai phần tử trong mảng.
     * Luồng xử lý:
     * 1. Lưu giá trị phần tử thứ nhất vào biến tạm.
     * 2. Gán giá trị phần tử thứ hai cho phần tử thứ nhất.
     * 3. Gán giá trị biến tạm cho phần tử thứ hai.
     *
     * @param firstIndex chỉ số của phần tử thứ nhất
     * @param secondIndex chỉ số của phần tử thứ hai
     */
    private void swap(int firstIndex, int secondIndex) {
        int temporaryValue = array[firstIndex];

        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temporaryValue;
    }

    /**
     * Chức năng: Lấy mảng hiện tại.
     *
     * @return mảng số nguyên hiện tại
     */
    public int[] getArray() {
        return array;
    }
}
