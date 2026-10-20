package main;

import controller.ArrayController;

/**
 * Chức năng: Điểm khởi chạy của chương trình Bubble Sort.
 */
public class Main {

    /**
     * Hàm khởi tạo mặc định.
     */
    public Main() {
    }

    /**
     * Chức năng: Khởi chạy chương trình sắp xếp nổi bọt.
     * Luồng xử lý: Tạo đối tượng ArrayController và gọi hàm run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        ArrayController arrayController = new ArrayController();
        arrayController.run();
    }
}
