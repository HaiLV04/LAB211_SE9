package main;

import controller.MatrixController;

/**
 * Chức năng: Lớp chính chứa điểm vào của chương trình tính toán ma trận.
 */
public class Main {

    /**
     * Chức năng: Khởi tạo mặc định cho lớp Main.
     */
    public Main() {
    }

    /**
     * Chức năng: Bắt đầu chương trình máy tính ma trận.
     * Luồng xử lý:
     * 1. Khởi tạo đối tượng MatrixController.
     * 2. Kích hoạt controller qua phương thức run().
     *
     * @param args tham số dòng lệnh
     */
    public static void main(String[] args) {
        MatrixController controller = new MatrixController();
        controller.run();
    }
}
