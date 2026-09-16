package model;

import java.util.Arrays;
import java.util.Random;

/**
 * Chức năng: Cung cấp các phương thức khởi tạo mảng ngẫu nhiên và thực hiện thuật toán tìm kiếm nhị phân (Binary Search).
 * Luồng tương tác:
 * - Khởi tạo mảng số nguyên ngẫu nhiên hoặc từ mảng có sẵn.
 * - Hiển thị mảng đã sắp xếp.
 * - Cung cấp hàm tìm kiếm nhị phân để lấy một hoặc tất cả các vị trí của phần tử cần tìm.
 */
public class BinarySearch {

    private int[] array;

    /**
     * Chức năng: Hàm khởi tạo đối tượng, đồng thời tạo mảng với kích thước yêu cầu và sinh giá trị ngẫu nhiên.
     * Luồng xử lý:
     * 1. Kiểm tra kích thước mảng truyền vào, ném ngoại lệ nếu nhỏ hơn hoặc bằng 0.
     * 2. Khởi tạo mảng với kích thước đã cho.
     * 3. Sử dụng Random để sinh giá trị ngẫu nhiên cho từng phần tử của mảng.
     *
     * @param number kích thước mảng
     * @throws Exception nếu number <= 0
     */
    public BinarySearch(int number) throws Exception {
        if (number <= 0) {
            throw new Exception("Number of array must be >0");
        }
        array = new int[number];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(number * 2 + 1) - number;
        }
    }

    /**
     * Chức năng: Hàm khởi tạo đối tượng với một mảng có sẵn.
     * Luồng xử lý:
     * 1. Kiểm tra mảng đầu vào có null hay không.
     * 2. Gán mảng đầu vào cho thuộc tính của đối tượng.
     *
     * @param array mảng đầu vào
     * @throws Exception nếu mảng null
     */
    public BinarySearch(int[] array) throws Exception {
        if (array == null) {
            throw new Exception("Array can not null!");
        }
        this.array = array;
    }

    /**
     * Chức năng: Sắp xếp và hiển thị mảng ra màn hình.
     * Luồng xử lý:
     * 1. Sử dụng Arrays.sort để sắp xếp mảng theo thứ tự tăng dần.
     * 2. In mảng đã sắp xếp ra màn hình.
     */
    public void display() {
        Arrays.sort(array);
        System.out.println("Sorted array: " + Arrays.toString(array));
    }

    /**
     * Chức năng: Tìm kiếm giá trị bằng thuật toán Binary Search.
     * Luồng xử lý:
     * 1. Khởi tạo biến left ở đầu mảng và right ở cuối mảng.
     * 2. Lặp điều kiện khi left <= right để kiểm tra phần tử ở giữa (mid).
     * 3. Thay đổi left hoặc right dựa vào việc so sánh giá trị mid và giá trị cần tìm.
     * 4. Trả về vị trí (mid) nếu tìm thấy, hoặc -1 nếu không có phần tử thỏa mãn.
     *
     * @param key giá trị cần tìm
     * @return vị trí của key nếu tìm thấy, ngược lại trả về -1
     */
    public int binarySearch(int key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] < key) {
                left = mid + 1;
            } else if (array[mid] > key) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Chức năng: Tìm tất cả các vị trí của giá trị cần tìm trong mảng (nếu mảng có phần tử trùng lặp).
     * Luồng xử lý:
     * 1. Gọi hàm binarySearch để tìm một vị trí khớp giá trị. Nếu không thấy trả về mảng rỗng.
     * 2. Mở rộng tìm kiếm sang biên trái để tìm các phần tử trùng lặp.
     * 3. Mở rộng tìm kiếm sang biên phải để tìm các phần tử trùng lặp.
     * 4. Lưu tất cả các vị trí tìm được vào một mảng kết quả và trả về.
     *
     * @param key giá trị cần tìm
     * @return mảng chứa tất cả các vị trí của key, mảng rỗng nếu không tìm thấy
     */
    public int[] binarySearchAll(int key) {
        int index = binarySearch(key);
        if (index == -1) {
            return new int[0];
        }

        int left = index;
        while (left - 1 >= 0 && array[left - 1] == key) {
            left--;
        }

        int right = index;
        while (right + 1 < array.length && array[right + 1] == key) {
            right++;
        }

        int[] result = new int[right - left + 1];
        int idx = 0;
        for (int i = left; i <= right; i++) {
            result[idx++] = i;
        }

        return result;
    }
}
