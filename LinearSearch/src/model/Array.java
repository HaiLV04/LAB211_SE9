package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Chức năng: Lưu trữ mảng số nguyên và cung cấp các phương thức thao tác trên mảng như hiển thị, tìm kiếm (tuần tự).
 * Luồng tương tác:
 * 1. Được khởi tạo từ Main để tạo ra mảng ngẫu nhiên hoặc mảng có sẵn.
 * 2. Cung cấp phương thức hiển thị mảng cho Main gọi.
 * 3. Trả về kết quả tìm kiếm (các chỉ số) cho Main xử lý hiển thị.
 */
public class Array {
    // Mảng số nguyên lưu dữ liệu
    private int[] array;

    /**
     * Chức năng: Khởi tạo một mảng có kích thước cho trước và sinh ngẫu nhiên các phần tử trong mảng.
     * Luồng xử lý:
     * 1. Kiểm tra số lượng phần tử đầu vào. Nếu nhỏ hơn hoặc bằng 0, ném ra ngoại lệ.
     * 2. Khởi tạo mảng số nguyên với số phần tử được chỉ định.
     * 3. Sử dụng vòng lặp để gán giá trị ngẫu nhiên cho từng phần tử của mảng.
     *
     * @param number số phần tử của mảng
     * @throws Exception nếu số phần tử nhỏ hơn hoặc bằng 0
     */
    public Array(int number) throws Exception {
        // Kiểm tra số phần tử hợp lệ
        if (number <= 0) {
            throw new Exception("Number of array must be >0");
        }
        // Khởi tạo mảng với số phần tử được chỉ định
        array = new int[number];
        Random random = new Random();
        // Sinh giá trị ngẫu nhiên cho từng phần tử
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(number);
        }
    }

    /**
     * Chức năng: Khởi tạo đối tượng Array từ một mảng có sẵn.
     * Luồng xử lý:
     * 1. Kiểm tra mảng đầu vào có hợp lệ hay không (khác null).
     * 2. Nếu null, ném ra ngoại lệ.
     * 3. Nếu hợp lệ, gán mảng đầu vào cho thuộc tính của lớp.
     *
     * @param array mảng đầu vào
     * @throws Exception nếu mảng đầu vào là null
     */
    public Array(int[] array) throws Exception {
        // Kiểm tra mảng đầu vào không được null
        if (array == null) {
            throw new Exception("Array can not null!");
        }
        this.array = array;
    }

    /**
     * Chức năng: Hiển thị toàn bộ các phần tử của mảng ra màn hình.
     * Luồng xử lý:
     * 1. Sử dụng Arrays.toString để chuyển đổi mảng thành chuỗi.
     * 2. In chuỗi biểu diễn mảng ra console.
     */
    public void display() {
        System.out.println("The array: " + Arrays.toString(array));
    }

    /**
     * Chức năng: Thực hiện tìm kiếm tuần tự (Linear Search) để tìm vị trí xuất hiện đầu tiên của giá trị cần tìm.
     * Luồng xử lý:
     * 1. Duyệt qua từng phần tử trong mảng bằng vòng lặp.
     * 2. So sánh phần tử hiện tại với giá trị cần tìm (key).
     * 3. Nếu khớp, lập tức trả về chỉ số hiện tại.
     * 4. Nếu duyệt hết mảng mà không tìm thấy, trả về -1.
     *
     * @param key giá trị cần tìm kiếm
     * @return vị trí đầu tiên chứa key, trả về -1 nếu không tìm thấy
     */
    public int linearSearch(int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return i;
            }
        }
        // Không tìm thấy giá trị cần tìm
        return -1;
    }

    /**
     * Chức năng: Tìm tất cả vị trí xuất hiện của giá trị cần tìm trong mảng.
     * Luồng xử lý:
     * 1. Khởi tạo một danh sách (ArrayList) để lưu các chỉ số tìm được.
     * 2. Duyệt qua toàn bộ phần tử của mảng.
     * 3. Nếu phần tử tại vị trí đang xét bằng với key, thêm vị trí đó vào danh sách.
     * 4. Chuyển đổi danh sách các chỉ số từ ArrayList sang mảng số nguyên (int[]).
     * 5. Trả về mảng số nguyên chứa các chỉ số, hoặc mảng rỗng nếu không tìm thấy.
     *
     * @param key giá trị cần tìm kiếm
     * @return mảng chứa tất cả chỉ số (index) tìm được, trả về mảng rỗng nếu không tìm thấy
     */
    public int[] findAllIndex(int key) {
        ArrayList<Integer> indexList = new ArrayList<>();
        // Lưu tất cả vị trí có giá trị bằng key
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                indexList.add(i);
            }
        }
        // Chuyển ArrayList thành mảng int[]
        int[] indices = new int[indexList.size()];
        for (int i = 0; i < indexList.size(); i++) {
            indices[i] = indexList.get(i);
        }
        return indices;
    }
}
