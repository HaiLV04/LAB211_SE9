package main;

import controller.ArrayController;

/**
 * Chức năng: Điểm khởi chạy của chương trình Linear Search.
 */
public class Main {

    /**
     * Hàm khởi tạo mặc định.
     */
    public Main() {
    }

    /**
     * Chức năng: Khởi chạy chương trình tìm kiếm tuần tự.
     * Luồng xử lý: Tạo đối tượng ArrayController và gọi hàm run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        ArrayController arrayController = new ArrayController();
        arrayController.run();
    }
}
