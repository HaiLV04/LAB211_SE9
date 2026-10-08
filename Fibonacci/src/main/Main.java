package main;

import controller.Controller;

/**
 * Chức năng: Lớp chính để bắt đầu chương trình Fibonacci.
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
     * 1. Khởi tạo Controller.
     * 2. Chạy controller qua phương thức run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
