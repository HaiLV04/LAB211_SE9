package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Chức năng: Quản lý dữ liệu mảng và thuật toán tìm kiếm tuần tự (Linear Search).
 */
public class ArrayModel {

    private int[] array;

    /**
     * Khởi tạo đối tượng ArrayModel.
     */
    public ArrayModel() {
    }

    /**
     * Khởi tạo ArrayModel từ mảng có sẵn.
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
     * Chức năng: Tạo mảng số nguyên ngẫu nhiên theo kích thước chỉ định.
     * Luồng xử lý: Cấp phát mảng kích thước n và sinh giá trị ngẫu nhiên từ 0 đến n - 1.
     *
     * @param n số lượng phần tử
     */
    public void generateRandomArray(int n) {
        Random random = new Random();
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(n);
        }
    }

    /**
     * Chức năng: Tìm vị trí xuất hiện đầu tiên của giá trị bằng Linear Search.
     * Luồng xử lý: Duyệt tuần tự mảng từ đầu đến cuối, nếu gặp phần tử bằng key thì trả về chỉ số, duyệt hết không thấy trả về -1.
     *
     * @param key giá trị cần tìm
     * @return vị trí đầu tiên tìm thấy, hoặc -1 nếu không có
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
     * Chức năng: Tìm tất cả vị trí xuất hiện của giá trị trong mảng bằng Linear Search.
     * Luồng xử lý: Duyệt qua toàn bộ phần tử của mảng, gom các vị trí khớp key vào danh sách và trả về mảng kết quả.
     *
     * @param key giá trị cần tìm
     * @return mảng chứa các chỉ số tìm được, hoặc mảng rỗng nếu không tìm thấy
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
     * Lấy mảng số nguyên hiện tại.
     *
     * @return mảng số nguyên
     */
    public int[] getArray() {
        return array;
    }
}
