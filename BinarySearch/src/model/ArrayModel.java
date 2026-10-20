package model;

import java.util.Random;

/**
 * Chức năng: Quản lý dữ liệu mảng và thuật toán tìm kiếm nhị phân (Binary Search).
 */
public class ArrayModel {

    private int[] array;

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
     * Luồng xử lý: So sánh từng cặp phần tử liền kề và đổi chỗ nếu đứng sai thứ tự, dừng sớm nếu mảng đã sắp xếp xong.
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
     * Chức năng: Tìm kiếm một phần tử trong mảng bằng thuật toán Binary Search.
     * Luồng xử lý: Thu hẹp khoảng tìm kiếm [left, right] dựa vào phần tử giữa mid, trả về vị trí tìm thấy hoặc -1 nếu không có.
     *
     * @param key giá trị cần tìm
     * @return chỉ số phần tử tìm thấy, hoặc -1 nếu không tìm thấy
     */
    public int binarySearch(int key) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == key) {
                return mid;
            } else if (key < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    /**
     * Chức năng: Tìm vị trí xuất hiện đầu tiên của giá trị trong mảng đã sắp xếp.
     * Luồng xử lý: Dùng Binary Search, khi tìm thấy ghi nhận vị trí và tiếp tục tìm về nửa bên trái.
     *
     * @param key giá trị cần tìm
     * @return chỉ số đầu tiên tìm thấy, hoặc -1 nếu không tìm thấy
     */
    public int findFirstIndex(int key) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        int firstIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == key) {
                firstIndex = mid;
                right = mid - 1;
            } else if (key < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return firstIndex;
    }

    /**
     * Chức năng: Tìm vị trí xuất hiện cuối cùng của giá trị trong mảng đã sắp xếp.
     * Luồng xử lý: Dùng Binary Search, khi tìm thấy ghi nhận vị trí và tiếp tục tìm về nửa bên phải.
     *
     * @param key giá trị cần tìm
     * @return chỉ số cuối cùng tìm thấy, hoặc -1 nếu không tìm thấy
     */
    public int findLastIndex(int key) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int left = 0;
        int right = array.length - 1;
        int lastIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == key) {
                lastIndex = mid;
                left = mid + 1;
            } else if (key < array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return lastIndex;
    }

    /**
     * Chức năng: Tìm tất cả vị trí xuất hiện của giá trị trong mảng đã sắp xếp.
     * Luồng xử lý: Tìm chỉ số đầu tiên và cuối cùng bằng Binary Search, sau đó trích xuất toàn bộ dãy chỉ số liên tục giữa hai vị trí đó.
     *
     * @param key giá trị cần tìm
     * @return mảng chứa các chỉ số tìm được, hoặc mảng rỗng nếu không thấy
     */
    public int[] findAllIndex(int key) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        int firstIndex = findFirstIndex(key);
        if (firstIndex == -1) {
            return new int[0];
        }

        int lastIndex = findLastIndex(key);
        int totalElements = lastIndex - firstIndex + 1;
        int[] foundIndices = new int[totalElements];

        for (int i = 0; i < totalElements; i++) {
            foundIndices[i] = firstIndex + i;
        }

        return foundIndices;
    }

    /**
     * Chức năng: Hoán đổi giá trị hai phần tử trong mảng.
     * Luồng xử lý: Dùng biến tạm để đổi chỗ hai phần tử tại firstIndex và secondIndex.
     *
     * @param firstIndex chỉ số phần tử thứ nhất
     * @param secondIndex chỉ số phần tử thứ hai
     */
    private void swap(int firstIndex, int secondIndex) {
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
