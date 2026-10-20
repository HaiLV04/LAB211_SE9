package controller;

import model.ArrayModel;
import view.ArrayView;

/**
 * Chức năng: Điều khiển luồng chương trình sắp xếp trộn (Merge Sort).
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
     * Chức năng: Chạy luồng chương trình chính của Merge Sort.
     * Luồng xử lý: Nhập kích thước mảng, sinh mảng ngẫu nhiên, hiển thị mảng trước và sau khi sắp xếp Merge Sort.
     */
    public void run() {
        int size = arrayView.inputPositiveInteger("Enter number of array: ");

        arrayModel.generateRandomArray(size);

        arrayView.displayArray("Unsorted array: ", arrayModel.getArray());

        arrayModel.mergeSort();

        arrayView.displayArray("Sorted array: ", arrayModel.getArray());
    }
}
