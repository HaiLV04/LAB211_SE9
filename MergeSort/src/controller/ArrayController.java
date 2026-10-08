package controller;

import model.ArrayModel;
import view.ArrayView;

/**
 * Chức năng: Điều khiển luồng của chương trình Merge Sort.
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
     * 1. Yêu cầu người dùng nhập kích thước mảng.
     * 2. Sinh mảng số nguyên ngẫu nhiên.
     * 3. Hiển thị mảng chưa sắp xếp.
     * 4. Thực hiện thuật toán sắp xếp trộn (Merge Sort).
     * 5. Hiển thị mảng đã sắp xếp.
     */
    public void run() {
        int size = arrayView.inputPositiveInteger("Enter number of array: ");

        arrayModel.generateRandomArray(size);

        arrayView.displayArray("Unsorted array: ", arrayModel.getArray());

        arrayModel.mergeSort();

        arrayView.displayArray("Sorted array: ", arrayModel.getArray());
    }
}
