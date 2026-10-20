package view;

import model.Matrix;

/**
 * Chức năng: Xử lý giao diện đầu vào và đầu ra cho chương trình tính toán ma trận.
 */
public class MatrixView {

    /**
     * Khởi tạo đối tượng MatrixView.
     */
    public MatrixView() {
    }

    /**
     * Chức năng: Hiển thị menu chức năng và nhận lựa chọn từ người dùng.
     * Luồng xử lý: Hiển thị danh sách lựa chọn và dùng Validator để nhận số nguyên từ 1 đến 4.
     *
     * @return lựa chọn từ 1 đến 4
     */
    public int getMenuChoice() {
        return Validator.getInt("==========Calculator program============\n"
                + "1. Addition Matrix\n"
                + "2. Subtraction Matrix\n"
                + "3. Multiplication Matrix\n"
                + "4. Quit\n"
                + "Enter your choice: ", "Just be 1 -> 4", "Please enter integer number", 1, 4);
    }

    /**
     * Chức năng: Nhập số hàng hoặc số cột của ma trận.
     * Luồng xử lý: Hiển thị thông báo và sử dụng Validator để kiểm tra giá trị trong khoảng [min, max].
     *
     * @param message thông báo hiển thị
     * @param min giá trị nhỏ nhất
     * @param max giá trị lớn nhất
     * @return kích thước hợp lệ
     */
    public int inputDimension(String message, int min, int max) {
        String errorRange = (min == max) ? ("Just be = " + min) : "Just be >0 ";
        return Validator.getInt(message, errorRange, "Please enter integer number!", min, max);
    }

    /**
     * Chức năng: Nhập các phần tử của ma trận từ bàn phím.
     * Luồng xử lý: Khởi tạo mảng 2 chiều kích thước rows x cols, lặp qua từng phần tử để yêu cầu nhập giá trị.
     *
     * @param matrixLabel nhãn hiển thị ma trận
     * @param rows số hàng
     * @param cols số cột
     * @return đối tượng Matrix đã nhập đầy đủ dữ liệu
     */
    public Matrix inputMatrix(String matrixLabel, int rows, int cols) {
        int[][] data = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Validator.getInt(matrixLabel + String.format("[%d][%d]: ", i + 1, j + 1),
                        "Error range!", "Value of matrix is digit", Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
        try {
            return new Matrix(data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /**
     * Chức năng: Hiển thị biểu thức và kết quả tính toán giữa hai ma trận.
     * Luồng xử lý: In ma trận 1, toán tử (+, -, *), ma trận 2, dấu = và ma trận kết quả.
     *
     * @param matrix1 ma trận thứ nhất
     * @param operator toán tử phép tính
     * @param matrix2 ma trận thứ hai
     * @param result ma trận kết quả
     */
    public void displayResult(Matrix matrix1, String operator, Matrix matrix2, Matrix result) {
        System.out.println("-------------Result-------------");
        System.out.print(matrix1.toString());
        System.out.println(operator);
        System.out.print(matrix2.toString());
        System.out.println("=");
        System.out.println(result.toString());
    }

    /**
     * Chức năng: Hiển thị thông báo ra màn hình console.
     * Luồng xử lý: In thông điệp bằng System.out.println.
     *
     * @param message thông điệp cần in
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
