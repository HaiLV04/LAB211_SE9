package main;

import controller.TPBank;

/**
 * Chức năng: Điểm khởi chạy của chương trình mô phỏng đăng nhập TPBank.
 */
public class Main {

    /**
     * Hàm khởi tạo mặc định.
     */
    public Main() {
    }

    /**
     * Chức năng: Khởi chạy chương trình TPBank.
     * Luồng xử lý: Tạo đối tượng TPBank và gọi hàm run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        TPBank tpBank = new TPBank();
        tpBank.run();
    }
}
