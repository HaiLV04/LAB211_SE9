package controller;

import model.ArrayModel;
import view.ArrayView;

/**
 * Chức năng: Điều khiển luồng của chương trình Linear Search.
 */
public class ArrayController {

    private final ArrayModel arrayModel;
    private final ArrayView arrayView;

    /**
     * Chức năng: Khởi tạo model và view.
     * Luồng xử lý:
     * 1. Khởi tạo ArrayModel.
     * 2. Khởi tạo ArrayView.
     */
    public ArrayController() {
        arrayModel = new ArrayModel();
        arrayView = new ArrayView();
    }

    /**
     * Chức năng: Chạy luồng chương trình chính.
     * Luồng tương tác:
     * 1. Yêu cầu người dùng nhập kích thước mảng (số nguyên dương).
     * 2. Sinh mảng ngẫu nhiên theo kích thước.
     * 3. Hiển thị mảng chưa sắp xếp.
     * 4. Sắp xếp mảng theo thứ tự tăng dần.
     * 5. Hiển thị mảng đã sắp xếp.
     * 6. Sau khi đã quan sát mảng, yêu cầu người dùng nhập giá trị cần tìm kiếm (số nguyên).
     * 7. Thực hiện thuật toán tìm kiếm tuần tự (Linear Search) tìm tất cả vị trí.
     * 8. Hiển thị kết quả tìm kiếm ra màn hình.
     */
    public void run() {
        int size = arrayView.inputPositiveInteger("Enter number of array: ");

        arrayModel.generateRandomArray(size);

        arrayView.displayArray("Unsorted array: ", arrayModel.getArray());

        arrayModel.bubbleSort();

        arrayView.displayArray("Sorted array: ", arrayModel.getArray());

        int searchValue = arrayView.inputInteger("Enter search value: ");

        int[] foundIndices = arrayModel.findAllIndex(searchValue);
        arrayView.displaySearchResult(searchValue, foundIndices);
    }
}
