package model;

/**
 * Chức năng: Kế thừa ArrayModel từ thư viện BubbleSort và cài đặt thuật toán Quick Sort.
 */
public class QuickSortModel extends ArrayModel {

    private int stepCounter;

    /**
     * Khởi tạo đối tượng QuickSortModel.
     */
    public QuickSortModel() {
        super();
        stepCounter = 1;
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
                swap(i, j); // Tái sử dụng hàm swap kế thừa từ ArrayModel (BubbleSort)
                i++;
                j--;
            }
        }

        System.out.println("   => Trang thai mang sau lan phan vung nay: " + java.util.Arrays.toString(array));
        System.out.println("----------------------------------------------------------------");

        return i;
    }
}
