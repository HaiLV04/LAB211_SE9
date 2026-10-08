package main;

import controller.Controller;

/**
 * Chức năng: Lớp chính chứa hàm main để chạy chương trình quản lý công nhân.
 */
public class Main {

    /**
     * Chức năng: Khởi tạo mặc định cho lớp Main.
     */
    public Main() {
    }

    /**
     * Chức năng: Điểm vào (entry point) khởi chạy chương trình.
     * Luồng xử lý:
     * 1. Khởi tạo đối tượng Controller.
     * 2. Kích hoạt controller qua phương thức run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        Controller control = new Controller();
        control.run();
    }
}
