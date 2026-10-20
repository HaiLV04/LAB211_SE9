package main;

import controller.Controller;

/**
 * Chức năng: Điểm khởi chạy của chương trình Fibonacci.
 */
public class Main {

    /**
     * Hàm khởi tạo mặc định.
     */
    public Main() {
    }

    /**
     * Chức năng: Khởi chạy chương trình tính toán dãy Fibonacci.
     * Luồng xử lý: Tạo đối tượng Controller và gọi hàm run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
