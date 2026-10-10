package controller;

import model.ArrayModel;
import view.ArrayView;

/**
 * Chức năng: Điều khiển luồng của chương trình Binary Search.
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
     * Chức năng (Làm gì): Chạy luồng chương trình chính của Binary Search.
     * Luồng xử lý (Làm như thế nào):
     * 1. Yêu cầu người dùng nhập kích thước mảng (số nguyên dương).
     * 2. Sinh mảng ngẫu nhiên theo kích thước.
     * 3. Hiển thị mảng chưa sắp xếp.
     * 4. Sắp xếp mảng theo thứ tự tăng dần bằng thuật toán Bubble Sort.
     * 5. Hiển thị mảng đã sắp xếp.
     * 6. Yêu cầu người dùng nhập giá trị cần tìm kiếm (số nguyên).
     * 7. Thực hiện thuật toán tìm kiếm nhị phân (Binary Search).
     * 8. Hiển thị kết quả tìm kiếm ra màn hình console.
     */
    public void run() {
        int size = arrayView.inputPositiveInteger("Enter number of array: ");

        arrayModel.generateRandomArray(size);

        arrayView.displayArray("Unsorted array: ", arrayModel.getArray());

        arrayModel.bubbleSort();

        arrayView.displayArray("Sorted array: ", arrayModel.getArray());

        int searchValue = arrayView.inputInteger("Enter search value: ");

        int foundIndex = arrayModel.binarySearch(searchValue);
        arrayView.displaySearchResult(searchValue, foundIndex);
    }
}
