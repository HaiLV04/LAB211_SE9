package main;

import controller.ArrayController;

/**
 * Chức năng: Lớp chính để bắt đầu chương trình Linear Search.
 */
public class Main {

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp Main.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng Main mới.
     */
    public Main() {
    }

    /**
     * Chức năng (Làm gì): Bắt đầu chương trình tìm kiếm tuần tự.
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
