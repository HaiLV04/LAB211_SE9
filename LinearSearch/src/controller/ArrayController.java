package controller;

import model.ArrayModel;
import view.ArrayView;

/**
 * Chức năng: Điều khiển luồng chương trình tìm kiếm tuần tự (Linear Search).
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
     * Chức năng: Chạy luồng chương trình chính của Linear Search.
     * Luồng xử lý: Nhập kích thước, sinh mảng ngẫu nhiên, hiển thị mảng, nhập giá trị cần tìm và hiển thị các vị trí tìm thấy.
     */
    public void run() {
        int size = arrayView.inputPositiveInteger("Enter number of array: ");

        arrayModel.generateRandomArray(size);

        arrayView.displayArray("The array: ", arrayModel.getArray());

        int searchValue = arrayView.inputInteger("Enter search value: ");

        int[] foundIndices = arrayModel.findAllIndex(searchValue);
        arrayView.displaySearchResult(searchValue, foundIndices);
    }
}
