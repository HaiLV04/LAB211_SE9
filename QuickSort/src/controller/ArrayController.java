package controller;

import model.ArrayModel;
import view.ArrayView;

/**
 * Chức năng: Điều khiển luồng của chương trình Quick Sort.
 */
public class ArrayController {

    private final ArrayModel arrayModel;
    private final ArrayView arrayView;

    /**
     * Chức năng (Làm gì): Khởi tạo controller với model và view.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo đối tượng ArrayModel.
     * 2. Khởi tạo đối tượng ArrayView.
     */
    public ArrayController() {
        arrayModel = new ArrayModel();
        arrayView = new ArrayView();
    }

    /**
     * Chức năng (Làm gì): Chạy luồng chương trình chính của Quick Sort.
     * Luồng xử lý (Làm như thế nào):
     * 1. Yêu cầu người dùng nhập kích thước mảng (số nguyên dương).
     * 2. Sinh mảng số nguyên ngẫu nhiên theo kích thước.
     * 3. Hiển thị mảng chưa sắp xếp ra màn hình console.
     * 4. Thực hiện thuật toán sắp xếp nhanh (Quick Sort).
     * 5. Hiển thị mảng đã sắp xếp ra màn hình console.
     */
    public void run() {
        int size = arrayView.inputPositiveInteger("Enter number of array: ");

        arrayModel.generateRandomArray(size);

        arrayView.displayArray("Unsorted array: ", arrayModel.getArray());

        arrayModel.quickSort();

        arrayView.displayArray("Sorted array: ", arrayModel.getArray());
    }
}
