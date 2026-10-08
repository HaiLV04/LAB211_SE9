package main;

import controller.Controller;

/**
 * Chức năng: Điểm khởi chạy của chương trình quản lý sinh viên.
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
