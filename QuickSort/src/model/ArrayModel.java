package model;

import java.util.Random;

/**
 * Chức năng: Quản lý dữ liệu mảng và thuật toán sắp xếp nhanh (Quick Sort).
 */
public class ArrayModel {

    private int[] array;
    private int stepCounter;

    /**
     * Khởi tạo đối tượng ArrayModel.
     */
    public ArrayModel() {
        stepCounter = 1;
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
     * Chức năng: Bắt đầu sắp xếp mảng theo thứ tự tăng dần bằng Quick Sort.
     * Luồng xử lý: Đặt lại bộ đếm bước và gọi đệ quy quickSort trên toàn bộ phạm vi [0, length - 1].
     */
    public void quickSort() {
        if (array == null || array.length < 2) {
            return;
        }
        stepCounter = 1;
        quickSort(0, array.length - 1);
    }

    /**
     * Chức năng: Đệ quy chia để trị thực hiện thuật toán Quick Sort.
     * Luồng xử lý: Phân hoạch đoạn [left, right] theo pivot, sau đó đệ quy sắp xếp hai nửa trái và phải.
     *
     * @param left chỉ số bắt đầu
     * @param right chỉ số kết thúc
     */
    private void quickSort(int left, int right) {
        int index = partition(left, right);

        if (left < index - 1) {
            quickSort(left, index - 1);
        }
        if (index < right) {
            quickSort(index, right);
        }
    }

    /**
     * Chức năng: Phân hoạch đoạn mảng [left, right] dựa vào phần tử chốt (Pivot ở giữa).
     * Luồng xử lý: Dùng hai con trỏ i và j duyệt từ hai đầu, đổi chỗ các phần tử đứng sai phía so với pivot, trả về vị trí chia mảng.
     *
     * @param left chỉ số bắt đầu
     * @param right chỉ số kết thúc
     * @return chỉ số phân chia hai phân vùng
     */
    private int partition(int left, int right) {
        int pivot = array[left + (right - left) / 2];
        int i = left;
        int j = right;

        System.out.printf("Buoc %d [Phan vung: tu chi so %d den %d | Gia tri Pivot = %d]:\n",
                stepCounter++, left, right, pivot);

        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }

            if (i <= j) {
                if (i < j && array[i] != array[j]) {
                    System.out.printf("   -> Hoan doi array[%d](%d) va array[%d](%d)\n",
                            i, array[i], j, array[j]);
                }
                swap(i, j);
                i++;
                j--;
            }
        }

        System.out.println("   => Trang thai mang sau lan phan vung nay: " + java.util.Arrays.toString(array));
        System.out.println("----------------------------------------------------------------");

        return i;
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
