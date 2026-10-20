package controller;

import model.Matrix;
import view.MatrixView;

/**
 * Chức năng: Điều khiển luồng chương trình tính toán ma trận (Matrix Calculator).
 */
public class MatrixController {

    private final MatrixView view;

    /**
     * Khởi tạo MatrixController với MatrixView.
     */
    public MatrixController() {
        this.view = new MatrixView();
    }

    /**
     * Chức năng: Điều khiển luồng thực thi chính với vòng lặp menu.
     * Luồng xử lý: Hiển thị menu, nhận lựa chọn từ người dùng và điều hướng thực hiện Cộng, Trừ, Nhân hoặc Thoát.
     */
    public void run() {
        while (true) {
            int choice = view.getMenuChoice();
            switch (choice) {
                case 1:
                    handleAddition();
                    break;
                case 2:
                    handleSubtraction();
                    break;
                case 3:
                    handleMultiplication();
                    break;
                case 4:
                    return;
            }
        }
    }

    /**
     * Chức năng: Xử lý chức năng cộng hai ma trận.
     * Luồng xử lý: Nhập ma trận 1, khóa kích thước ma trận 2 bằng ma trận 1, thực hiện phép cộng và hiển thị kết quả.
     */
    private void handleAddition() {
        view.displayMessage("---------Addition---------");
        int rows1 = view.inputDimension("Enter Row Matrix 1: ", 1, Integer.MAX_VALUE);
        int cols1 = view.inputDimension("Enter Column Matrix 1: ", 1, Integer.MAX_VALUE);
        Matrix matrix1 = view.inputMatrix("Enter matrix1", rows1, cols1);

        int rows2 = view.inputDimension("Enter Row Matrix 2: ", rows1, rows1);
        int cols2 = view.inputDimension("Enter Column Matrix 2: ", cols1, cols1);
        Matrix matrix2 = view.inputMatrix("Enter matrix2", rows2, cols2);

        try {
            Matrix result = matrix1.add(matrix2);
            view.displayResult(matrix1, "+", matrix2, result);
        } catch (Exception e) {
            view.displayMessage(e.getMessage());
        }
    }

    /**
     * Chức năng: Xử lý chức năng trừ hai ma trận.
     * Luồng xử lý: Nhập ma trận 1, khóa kích thước ma trận 2 bằng ma trận 1, thực hiện phép trừ và hiển thị kết quả.
     */
    private void handleSubtraction() {
        view.displayMessage("---------Subtraction---------");
        int rows1 = view.inputDimension("Enter Row Matrix 1: ", 1, Integer.MAX_VALUE);
        int cols1 = view.inputDimension("Enter Column Matrix 1: ", 1, Integer.MAX_VALUE);
        Matrix matrix1 = view.inputMatrix("Enter matrix1", rows1, cols1);

        int rows2 = view.inputDimension("Enter Row Matrix 2: ", rows1, rows1);
        int cols2 = view.inputDimension("Enter Column Matrix 2: ", cols1, cols1);
        Matrix matrix2 = view.inputMatrix("Enter matrix2", rows2, cols2);

        try {
            Matrix result = matrix1.subtract(matrix2);
            view.displayResult(matrix1, "-", matrix2, result);
        } catch (Exception e) {
            view.displayMessage(e.getMessage());
        }
    }

    /**
     * Chức năng: Xử lý chức năng nhân hai ma trận.
     * Luồng xử lý: Nhập ma trận 1, khóa số hàng ma trận 2 bằng số cột ma trận 1, thực hiện phép nhân và hiển thị kết quả.
     */
    private void handleMultiplication() {
        view.displayMessage("---------Multiplication---------");
        int rows1 = view.inputDimension("Enter Row Matrix 1: ", 1, Integer.MAX_VALUE);
        int cols1 = view.inputDimension("Enter Column Matrix 1: ", 1, Integer.MAX_VALUE);
        Matrix matrix1 = view.inputMatrix("Enter matrix1", rows1, cols1);

        int rows2 = view.inputDimension("Enter Row Matrix 2: ", cols1, cols1);
        int cols2 = view.inputDimension("Enter Column Matrix 2: ", 1, Integer.MAX_VALUE);
        Matrix matrix2 = view.inputMatrix("Enter matrix2", rows2, cols2);

        try {
            Matrix result = matrix1.multiply(matrix2);
            view.displayResult(matrix1, "*", matrix2, result);
        } catch (Exception e) {
            view.displayMessage(e.getMessage());
        }
    }
}
