package model;

import java.util.Random;

/**
 * Chức năng: Quản lý dữ liệu mảng và thuật toán sắp xếp trộn (Merge Sort).
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
     * Chức năng: Bắt đầu sắp xếp mảng theo thứ tự tăng dần bằng Merge Sort.
     * Luồng xử lý: Đặt lại bộ đếm bước và gọi đệ quy mergeSort trên toàn bộ phạm vi [0, length - 1].
     */
    public void mergeSort() {
        if (array == null || array.length < 2) {
            return;
        }
        stepCounter = 1;
        mergeSort(0, array.length - 1);
    }

    /**
     * Chức năng: Đệ quy chia đôi mảng để sắp xếp từng nửa mảng con.
     * Luồng xử lý: Tìm điểm giữa mid, đệ quy sắp xếp nửa trái [left, mid] và nửa phải [mid + 1, right], sau đó trộn hai nửa lại bằng merge().
     *
     * @param left chỉ số bắt đầu
     * @param right chỉ số kết thúc
     */
    private void mergeSort(int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        System.out.printf("Buoc %d [CHIA MANG] Phan doan tu chi so %d den %d | Chia doi tai vi tri giua = %d\n",
                stepCounter++, left, right, mid);
        System.out.println("   -> Phan vung con ben Trai: chi so " + left + " den " + mid);
        System.out.println("   -> Phan vung con ben Phai: chi so " + (mid + 1) + " den " + right);

        mergeSort(left, mid);
        mergeSort(mid + 1, right);
        merge(left, mid, right);
    }

    /**
     * Chức năng: Trộn hai mảng con đã sắp xếp [left, mid] và [mid + 1, right] vào mảng gốc.
     * Luồng xử lý: Sao chép dữ liệu ra hai mảng tạm, so sánh đưa phần tử nhỏ hơn vào mảng gốc và sao chép nốt các phần tử còn lại.
     *
     * @param left chỉ số bắt đầu phân đoạn trái
     * @param mid chỉ số kết thúc phân đoạn trái
     * @param right chỉ số kết thúc phân đoạn phải
     */
    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        System.out.printf("Buoc %d [TRON MANG] Dang gop hai vung con da sap xep vao mang goc tu %d den %d\n",
                stepCounter++, left, right);
        System.out.println("   * Mang con tam TRAI L[] : " + java.util.Arrays.toString(leftArray));
        System.out.println("   * Mang con tam PHAI R[] : " + java.util.Arrays.toString(rightArray));

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }

        System.out.println("   => Trang thai mang goc hien tai: " + java.util.Arrays.toString(array));
        System.out.println("----------------------------------------------------------------");
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
