package model;

import java.util.Random;

/**
 * Chức năng: Xử lý dữ liệu mảng và thuật toán Binary Search.
 */
public class ArrayModel {

    private int[] array;

    /**
     * Chức năng: Khởi tạo mặc định cho lớp ArrayModel.
     */
    public ArrayModel() {
    }

    /**
     * Chức năng: Tạo một mảng số nguyên ngẫu nhiên dựa trên kích thước đầu vào.
     * Luồng xử lý: 
     * 1. Khởi tạo đối tượng Random. 
     * 2. Khởi tạo mảng với kích thước chỉ định. 
     * 3. Tạo các giá trị ngẫu nhiên cho từng phần tử của mảng.
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
     * Chức năng: Tìm kiếm giá trị trong mảng đã sắp xếp bằng thuật toán Binary Search.
     * Luồng xử lý:
     * 1. Khởi tạo 2 con trỏ left = 0 và right = array.length - 1.
     * 2. Lặp lại khi {@code left <= right}:
     *    a. Tính chỉ số giữa: mid = left + (right - left) / 2.
     *    b. Nếu array[mid] == key: trả về chỉ số mid.
     *    c. Nếu {@code key < array[mid]}: tiếp tục tìm kiếm ở nửa trái (right = mid - 1).
     *    d. Nếu {@code key > array[mid]}: tiếp tục tìm kiếm ở nửa phải (left = mid + 1).
     * 3. Khi {@code left > right} mà không tìm thấy: trả về -1.
     *
     * @param key giá trị cần tìm kiếm
     * @return chỉ mục của phần tử nếu tìm thấy, ngược lại trả về -1
     */
    public int binarySearch(int key) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 1. Nếu giá trị cần tìm khớp với phần tử giữa, trả về chỉ mục của phần tử đó
            if (array[mid] == key) {
                return mid;
            }
            // 2. Nếu giá trị cần tìm nhỏ hơn phần tử giữa, tiếp tục tìm ở nửa bên trái
            else if (key < array[mid]) {
                right = mid - 1;
            }
            // 3. Nếu giá trị cần tìm lớn hơn phần tử giữa, tiếp tục tìm ở nửa bên phải
            else {
                left = mid + 1;
            }
        }

        // Không tìm thấy phần tử trong mảng (left > right)
        return -1;
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
