package main;

import controller.MatrixController;

/**
 * Chức năng: Điểm khởi chạy của chương trình tính toán ma trận.
 */
public class Main {

    /**
     * Hàm khởi tạo mặc định.
     */
    public Main() {
    }

    /**
     * Chức năng: Khởi chạy chương trình máy tính ma trận.
     * Luồng xử lý: Tạo đối tượng MatrixController và gọi hàm run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        MatrixController controller = new MatrixController();
        controller.run();
    }
}
