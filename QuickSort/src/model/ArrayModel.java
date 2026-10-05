package model;

import java.util.Random;

/**
 * Chức năng: Xử lý dữ liệu mảng và thuật toán Quick Sort.
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
     * Chức năng: Bắt đầu sắp xếp mảng theo thứ tự tăng dần bằng thuật toán Quick Sort.
     * Luồng xử lý:
     * 1. Kiểm tra nếu mảng null hoặc có ít hơn 2 phần tử ({@code n <= 1}) thì dừng lại, mảng giữ nguyên.
     * 2. Gọi hàm đệ quy quickSort với khoảng từ chỉ số 0 đến array.length - 1.
     */
    public void quickSort() {
        if (array == null || array.length < 2) {
            return;
        }
        quickSort(0, array.length - 1);
    }

    /**
     * Chức năng: Hàm đệ quy thực hiện thuật toán Quick Sort trên đoạn [left, right].
     * Luồng xử lý:
     * 1. Phân chia mảng thành 2 nửa qua hàm partition(left, right) dựa vào Pivot.
     * 2. Đệ quy (Recursion): Gọi đệ quy sắp xếp nửa bên trái [left, index - 1] nếu {@code left < index - 1}.
     * 3. Đệ quy (Recursion): Gọi đệ quy sắp xếp nửa bên phải [index, right] nếu {@code index < right}.
     *
     * @param left chỉ số bắt đầu đoạn cần sắp xếp
     * @param right chỉ số kết thúc đoạn cần sắp xếp
     */
    private void quickSort(int left, int right) {
        // 2. Phân chia (Partition)
        int index = partition(left, right);

        // 3. Đệ quy (Recursion) sắp xếp nửa bên trái
        if (left < index - 1) {
            quickSort(left, index - 1);
        }

        // 3. Đệ quy (Recursion) sắp xếp nửa bên phải
        if (index < right) {
            quickSort(index, right);
        }
    }

    /**
     * Chức năng: Phân hoạch đoạn mảng [left, right] dựa trên giá trị chốt (Pivot ở giữa).
     * Luồng xử lý:
     * 1. Chọn điểm chốt (Pivot): Chọn phần tử ở giữa mảng làm Pivot.
     * 2. Thiết lập 2 con trỏ: i bắt đầu từ left (chạy sang phải), j bắt đầu từ right (chạy sang trái).
     * 3. Con trỏ i tiến lên cho đến khi gặp phần tử &ge; Pivot.
     * 4. Con trỏ j lùi lại cho đến khi gặp phần tử &le; Pivot.
     * 5. Nếu {@code i <= j}, hoán đổi (swap) vị trí của hai phần tử tại i và j, sau đó tăng i và giảm j.
     * 6. Lặp lại đến khi hai con trỏ vượt qua nhau ({@code i > j}). Trả về chỉ số i để đệ quy 2 nửa.
     *
     * @param left chỉ số bắt đầu
     * @param right chỉ số kết thúc
     * @return chỉ số i phân chia hai phân vùng
     */
    private int partition(int left, int right) {
        // 1. Chọn điểm chốt (Pivot): Chọn một phần tử ở giữa mảng làm Pivot để chia mảng thành hai phần
        int pivot = array[left + (right - left) / 2];

        // 2. Phân chia (Partition): Thiết lập 2 con trỏ i (bắt đầu) và j (kết thúc)
        int i = left;
        int j = right;

        // Lặp lại đến khi hai con trỏ vượt qua nhau (i > j)
        while (i <= j) {
            // Con trỏ i tiến lên cho đến khi gặp phần tử >= Pivot
            while (array[i] < pivot) {
                i++;
            }

            // Con trỏ j lùi lại cho đến khi gặp phần tử <= Pivot
            while (array[j] > pivot) {
                j--;
            }

            // Nếu i <= j, tiến hành hoán đổi (swap) vị trí của hai phần tử tại i và j, sau đó tăng i và giảm j
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        // Trả về chỉ số i (nửa trái <= Pivot, nửa phải >= Pivot)
        return i;
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
