package controller;

import model.ArrayModel;
import view.ArrayView;

/**
 * Chức năng: Điều khiển luồng chương trình sắp xếp nổi bọt (Bubble Sort).
 */
public class ArrayController {

    private final ArrayModel arrayModel;
    private final ArrayView arrayView;

    /**
     * Khởi tạo controller với model và view.
     */
    public ArrayController() {
        arrayModel = new ArrayModel();
        arrayView = new ArrayView();
    }

    /**
     * Chức năng: Chạy luồng chương trình chính của Bubble Sort.
     * Luồng xử lý: Nhập kích thước mảng, sinh mảng ngẫu nhiên, hiển thị mảng trước và sau khi sắp xếp Bubble Sort.
     */
    public void run() {
        int size = arrayView.inputPositiveInteger("Enter number of array: ");

        arrayModel.generateRandomArray(size);

        arrayView.displayArray("Unsorted array: ", arrayModel.getArray());

        arrayModel.bubbleSort();

        arrayView.displayArray("Sorted array: ", arrayModel.getArray());
    }
}
