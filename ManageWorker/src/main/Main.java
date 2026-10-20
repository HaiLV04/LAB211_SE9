package main;

import controller.Controller;

/**
 * Chức năng: Điểm khởi chạy của chương trình quản lý công nhân.
 */
public class Main {

    /**
     * Hàm khởi tạo mặc định.
     */
    public Main() {
    }

    /**
     * Chức năng: Khởi chạy chương trình quản lý công nhân.
     * Luồng xử lý: Tạo đối tượng Controller và gọi hàm run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        Controller control = new Controller();
        control.run();
    }
}
