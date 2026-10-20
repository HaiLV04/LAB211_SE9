package controller;

import model.ArrayModel;
import view.ArrayView;

/**
 * Chức năng: Điều khiển luồng chương trình tìm kiếm nhị phân (Binary Search).
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
     * Chức năng: Chạy luồng chương trình chính của Binary Search.
     * Luồng xử lý: Nhập kích thước mảng, sinh mảng ngẫu nhiên, sắp xếp Bubble Sort, hiển thị mảng trước/sau khi sắp xếp, nhập giá trị cần tìm và hiển thị các vị trí tìm thấy.
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
