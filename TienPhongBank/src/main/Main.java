package main;

import controller.TPBank;

/**
 * Chức năng: Lớp chính chứa phương thức khởi chạy ứng dụng TPBank.
 * Luồng tương tác: Gọi đến lớp TPBank để bắt đầu chương trình.
 */
public class Main {

    /**
     * Chức năng: Khởi tạo mặc định cho lớp Main.
     */
    public Main() {
    }

    /**
     * Chức năng: Phương thức main để chạy chương trình.
     * Luồng xử lý:
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
