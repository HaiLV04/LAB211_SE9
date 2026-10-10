package main;

import controller.TPBank;

/**
 * Chức năng: Lớp chính chứa phương thức khởi chạy ứng dụng TPBank.
 * Luồng tương tác: Gọi đến lớp TPBank để bắt đầu chương trình.
 */
public class Main {

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp Main.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng Main mới.
     */
    public Main() {
    }

    /**
     * Chức năng (Làm gì): Điểm khởi chạy của chương trình TPBank.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo đối tượng TPBank.
     * 2. Gọi phương thức run() để kích hoạt ứng dụng.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        TPBank tpBank = new TPBank();
        tpBank.run();
    }
}
