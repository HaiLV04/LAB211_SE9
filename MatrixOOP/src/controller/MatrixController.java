package controller;

import model.Matrix;
import view.MatrixView;

/**
 * Chức năng: Điều khiển luồng chương trình tính toán ma trận (Matrix Calculator).
 */
public class MatrixController {

    private final MatrixView view;

    /**
     * Chức năng: Khởi tạo controller cho chương trình Matrix.
     * Luồng xử lý:
     * 1. Khởi tạo đối tượng MatrixView.
     */
    public MatrixController() {
        this.view = new MatrixView();
    }

    /**
     * Chức năng: Chạy luồng chương trình chính với vòng lặp menu.
     * Luồng tương tác:
     * 1. Hiển thị menu chức năng (Cộng, Trừ, Nhân, Thoát) và nhận lựa chọn.
     * 2. Tùy theo lựa chọn để thực hiện:
     *    - Case 1: Cộng hai ma trận có cùng kích thước.
     *    - Case 2: Trừ hai ma trận có cùng kích thước.
     *    - Case 3: Nhân hai ma trận (số cột ma trận 1 = số hàng ma trận 2).
     *    - Case 4: Thoát chương trình.
     * 3. Lặp lại cho đến khi người dùng chọn Thoát.
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
     * Luồng xử lý:
     * 1. Nhập số hàng và cột của ma trận 1.
     * 2. Nhập ma trận 1.
     * 3. Khóa số hàng và cột ma trận 2 bằng ma trận 1 và nhập ma trận 2.
     * 4. Gọi phương thức add() trên ma trận 1.
     * 5. Hiển thị kết quả qua view.
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
     * Luồng xử lý:
     * 1. Nhập số hàng và cột của ma trận 1.
     * 2. Nhập ma trận 1.
     * 3. Khóa số hàng và cột ma trận 2 bằng ma trận 1 và nhập ma trận 2.
     * 4. Gọi phương thức subtract() trên ma trận 1.
     * 5. Hiển thị kết quả qua view.
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
     * Luồng xử lý:
     * 1. Nhập số hàng và cột của ma trận 1.
     * 2. Nhập ma trận 1.
     * 3. Khóa số hàng ma trận 2 bằng số cột ma trận 1, cho phép nhập số cột ma trận 2 tự do (> 0).
     * 4. Nhập ma trận 2.
     * 5. Gọi phương thức multiply() trên ma trận 1.
     * 6. Hiển thị kết quả qua view.
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
