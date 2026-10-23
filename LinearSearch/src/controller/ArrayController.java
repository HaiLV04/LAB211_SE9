package controller;

import model.LinearSearchModel;
import view.LinearSearchView;

/**
 * Chức năng: Điều khiển luồng chương trình tìm kiếm tuần tự (Linear Search).
 */
public class ArrayController {

    private final LinearSearchModel arrayModel;
    private final LinearSearchView arrayView;

    /**
     * Khởi tạo controller với model và view.
     */
    public ArrayController() {
        arrayModel = new LinearSearchModel();
        arrayView = new LinearSearchView();
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
