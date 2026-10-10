package main;

import controller.Controller;

/**
 * Chức năng: Lớp chính chứa hàm main để chạy chương trình quản lý công nhân.
 */
public class Main {

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp Main.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng Main mới.
     */
    public Main() {
    }

    /**
     * Chức năng (Làm gì): Điểm vào (entry point) khởi chạy chương trình quản lý công nhân.
     * Luồng xử lý (Làm như thế nào):
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
