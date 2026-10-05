package main;

import controller.ArrayController;

/**
 * Chức năng: Lớp chính để bắt đầu chương trình Bubble Sort.
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
     * 2. Chạy controller.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        ArrayController arrayController = new ArrayController();
        arrayController.run();
    }
}
