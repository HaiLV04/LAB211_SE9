package main;

import controller.ArrayController;

/**
 * Chức năng: Điểm khởi chạy của chương trình Merge Sort.
 */
public class Main {

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp Main.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng Main mới.
     */
    public Main() {
    }

    /**
     * Chức năng (Làm gì): Bắt đầu chương trình sắp xếp trộn.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo đối tượng ArrayController.
     * 2. Gọi phương thức run() để thực thi chương trình.
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        ArrayController arrayController = new ArrayController();
        arrayController.run();
    }
}
