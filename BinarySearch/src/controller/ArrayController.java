package controller;

import model.BinarySearchModel;
import view.BinarySearchView;

/**
 * Chức năng: Điều khiển luồng chương trình tìm kiếm nhị phân (Binary Search).
 */
public class ArrayController {

    private final BinarySearchModel arrayModel;
    private final BinarySearchView arrayView;

    /**
     * Khởi tạo controller với model và view.
     */
    public ArrayController() {
        arrayModel = new BinarySearchModel();
        arrayView = new BinarySearchView();
    }

    /**
     * Chức năng: Chạy luồng chương trình chính của Binary Search.
     * Luồng xử lý: Nhập kích thước mảng, sinh mảng ngẫu nhiên, sắp xếp Bubble Sort (thư viện), hiển thị mảng trước/sau khi sắp xếp, nhập giá trị cần tìm và hiển thị các vị trí tìm thấy.
     */
    public void run() {
        int size = arrayView.inputPositiveInteger("Enter number of array: ");

        arrayModel.generateRandomArray(size);

        arrayView.displayArray("Unsorted array: ", arrayModel.getArray());

        arrayModel.bubbleSort(); // Gọi phương thức bubbleSort() kế thừa trực tiếp từ thư viện BubbleSort

        arrayView.displayArray("Sorted array: ", arrayModel.getArray());

        int searchValue = arrayView.inputInteger("Enter search value: ");

        int[] foundIndices = arrayModel.findAllIndex(searchValue);
        arrayView.displaySearchResult(searchValue, foundIndices);
    }
}
