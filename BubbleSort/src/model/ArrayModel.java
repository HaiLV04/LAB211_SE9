package model;

import java.util.Random;

/**
 * Chức năng: Xử lý dữ liệu mảng và thuật toán Bubble Sort.
 */
public class ArrayModel {

    private int[] array;

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp ArrayModel.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng ArrayModel mới với thuộc tính array chưa được cấp phát.
     */
    public ArrayModel() {
    }

    /**
     * Chức năng (Làm gì): Tạo một mảng số nguyên ngẫu nhiên dựa trên kích thước đầu vào.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo đối tượng Random.
     * 2. Khởi tạo mảng với kích thước chỉ định n.
     * 3. Tạo các giá trị ngẫu nhiên trong khoảng [-n, n] cho từng phần tử của mảng.
     *
     * @param n số lượng phần tử trong mảng
     */
    public void generateRandomArray(int n) {
        Random random = new Random();
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(2 * n + 1) - n;
        }
    }

    /**
     * Chức năng (Làm gì): Sắp xếp mảng theo thứ tự tăng dần sử dụng thuật toán Bubble Sort.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra nếu mảng null hoặc có ít hơn 2 phần tử thì dừng lại.
     * 2. Lặp qua mảng với biến cờ swapped kiểm tra hoán đổi.
     * 3. So sánh các phần tử kề nhau và hoán đổi đưa giá trị lớn hơn về bên phải.
     * 4. Dừng thuật toán sớm nếu trong một lượt duyệt không xảy ra bất kỳ hoán đổi nào.
     */
    public void bubbleSort() {
        if (array == null || array.length < 2) {
            return;
        }

        boolean swapped;
        int length = array.length;

        // Lặp lại việc so sánh các phần tử kề nhau cho đến khi không còn hoán đổi
        for (int i = 0; i < length - 1; i++) {
            swapped = false;

            // So sánh các phần tử kề nhau và đưa giá trị lớn hơn về bên phải
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1);
                    swapped = true;
                }
            }

            // Dừng sớm nếu mảng đã được sắp xếp
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Chức năng (Làm gì): Hoán đổi hai phần tử trong mảng.
     * Luồng xử lý (Làm như thế nào):
     * 1. Lưu giá trị phần tử thứ nhất vào biến tạm thời.
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
     * Chức năng (Làm gì): Lấy mảng số nguyên hiện tại.
     * Luồng xử lý (Làm như thế nào): Trả về tham chiếu đến mảng số nguyên hiện tại.
     *
     * @return mảng số nguyên hiện tại
     */
    public int[] getArray() {
        return array;
    }
}
