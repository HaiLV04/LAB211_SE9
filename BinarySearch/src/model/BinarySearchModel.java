package model;

/**
 * Chức năng: Kế thừa ArrayModel từ thư viện BubbleSort và cài đặt thuật toán tìm kiếm nhị phân (Binary Search).
 */
public class BinarySearchModel extends ArrayModel {

    /**
     * Khởi tạo đối tượng BinarySearchModel.
     */
    public BinarySearchModel() {
        super();
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
}
