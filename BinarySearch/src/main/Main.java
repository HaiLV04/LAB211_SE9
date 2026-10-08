package main;

import controller.ArrayController;

/**
 * Chức năng: Điểm khởi chạy của chương trình Binary Search.
 */
public class Main {

    /**
     * Chức năng: Khởi tạo mặc định cho lớp Main.
     */
    public Main() {
    }

    /**
     * Chức năng: Bắt đầu chương trình.
     * Luồng xử lý:
     * 1. Khởi tạo ArrayController.
     * 2. Gọi phương thức run() để thực thi chương trình.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        ArrayController arrayController = new ArrayController();
        arrayController.run();
    }
}
