package main;

import controller.TPBank;

/**
 * Chức năng: Lớp chính chứa phương thức khởi chạy ứng dụng.
 * Luồng tương tác: Gọi đến lớp TPBank để bắt đầu chương trình.
 */
public class Main {
    /**
     * Chức năng: Phương thức main để chạy chương trình.
     * Luồng xử lý 1: Gọi phương thức start() của lớp TPBank.
     */
    public static void main(String[] args) {
        TPBank.start();       
    }
}
