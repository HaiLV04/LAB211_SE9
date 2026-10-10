package main;

import controller.Controller;

/**
 * Chức năng: Điểm khởi chạy của chương trình quản lý tác vụ (Task Management).
 */
public class Main {

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp Main.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng Main mới.
     */
    public Main() {
    }

    /**
     * Chức năng (Làm gì): Bắt đầu chương trình quản lý tác vụ.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo đối tượng Controller.
     * 2. Gọi phương thức run() để thực thi chương trình.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        Controller control = new Controller();
        control.run();
    }
}
