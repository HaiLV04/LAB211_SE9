package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Chức năng: Xử lý dữ liệu mảng và thuật toán Linear Search.
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
     * Chức năng (Làm gì): Khởi tạo đối tượng ArrayModel từ một mảng có sẵn.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra mảng đầu vào không được null.
     * 2. Gán mảng đầu vào cho thuộc tính của lớp.
     *
     * @param array mảng đầu vào
     * @throws Exception nếu mảng đầu vào là null
     */
    public ArrayModel(int[] array) throws Exception {
        if (array == null) {
            throw new Exception("Array can not null!");
        }
        this.array = array;
    }

    /**
     * Chức năng (Làm gì): Tạo một mảng số nguyên ngẫu nhiên dựa trên kích thước đầu vào.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo đối tượng Random.
     * 2. Khởi tạo mảng với kích thước chỉ định.
     * 3. Tạo các giá trị ngẫu nhiên từ 0 đến n - 1 cho từng phần tử của mảng.
     *
     * @param n số lượng phần tử trong mảng
     */
    public void generateRandomArray(int n) {
        Random random = new Random();
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n);
        }
    }

    /**
     * Chức năng (Làm gì): Sắp xếp mảng theo thứ tự tăng dần bằng thuật toán Bubble Sort.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra nếu mảng null hoặc có ít hơn 2 phần tử thì dừng lại.
     * 2. Lặp lại việc so sánh các cặp phần tử kề nhau.
     * 3. Hoán đổi nếu phần tử đứng trước lớn hơn phần tử đứng sau.
     * 4. Dừng sớm nếu không còn cặp nào cần hoán đổi (cờ swapped).
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
     * Chức năng (Làm gì): Thực hiện tìm kiếm tuần tự (Linear Search) để tìm vị trí xuất hiện đầu tiên của giá trị cần tìm.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra mảng null hoặc rỗng, trả về -1.
     * 2. Duyệt qua từng phần tử trong mảng bằng vòng lặp.
     * 3. So sánh phần tử hiện tại với giá trị cần tìm (key).
     * 4. Nếu khớp, lập tức trả về chỉ số hiện tại.
     * 5. Nếu duyệt hết mảng mà không tìm thấy, trả về -1.
     *
     * @param key giá trị cần tìm kiếm
     * @return vị trí đầu tiên chứa key, trả về -1 nếu không tìm thấy
     */
    public int linearSearch(int key) {
        if (array == null || array.length == 0) {
            return -1;
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Chức năng (Làm gì): Tìm tất cả vị trí xuất hiện của giá trị cần tìm trong mảng bằng Linear Search.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo danh sách ArrayList để lưu các chỉ số tìm được.
     * 2. Duyệt qua toàn bộ phần tử của mảng.
     * 3. Nếu phần tử tại vị trí đang xét bằng với key, thêm vị trí đó vào danh sách.
     * 4. Chuyển đổi danh sách các chỉ số từ ArrayList sang mảng số nguyên int[].
     * 5. Trả về mảng số nguyên chứa các chỉ số, hoặc mảng rỗng nếu không tìm thấy.
     *
     * @param key giá trị cần tìm kiếm
     * @return mảng chứa tất cả chỉ số (index) tìm được, trả về mảng rỗng nếu không tìm thấy
     */
    public int[] findAllIndex(int key) {
        if (array == null || array.length == 0) {
            return new int[0];
        }

        ArrayList<Integer> indexList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                indexList.add(i);
            }
        }

        int[] indices = new int[indexList.size()];
        for (int i = 0; i < indexList.size(); i++) {
            indices[i] = indexList.get(i);
        }
        return indices;
    }

    /**
     * Chức năng (Làm gì): Lấy mảng số nguyên hiện tại.
     * Luồng xử lý (Làm như thế nào): Trả về tham chiếu đến mảng số nguyên hiện tại của đối tượng.
     *
     * @return mảng số nguyên hiện tại
     */
    public int[] getArray() {
        return array;
    }
}
