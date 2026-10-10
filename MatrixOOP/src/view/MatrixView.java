package view;

import model.Matrix;

/**
 * Chức năng: Xử lý giao diện đầu vào và đầu ra cho chương trình tính toán ma trận.
 */
public class MatrixView {

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp MatrixView.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng MatrixView mới.
     */
    public MatrixView() {
    }

    /**
     * Chức năng (Làm gì): Hiển thị menu chức năng và nhận lựa chọn từ người dùng.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị danh sách các chức năng (Cộng, Trừ, Nhân, Thoát).
     * 2. Sử dụng Validator để nhận số nguyên hợp lệ trong khoảng 1 đến 4.
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
     * Chức năng (Làm gì): Nhập số hàng hoặc số cột của ma trận.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập kích thước.
     * 2. Sử dụng Validator để kiểm tra và nhận giá trị trong khoảng [min, max].
     *
     * @param message thông báo hiển thị
     * @param min giá trị nhỏ nhất cho phép
     * @param max giá trị lớn nhất cho phép
     * @return kích thước hợp lệ
     */
    public int inputDimension(String message, int min, int max) {
        String errorRange = (min == max) ? ("Just be = " + min) : "Just be >0 ";
        return Validator.getInt(message, errorRange, "Please enter integer number!", min, max);
    }

    /**
     * Chức năng (Làm gì): Nhập các phần tử của một ma trận từ bàn phím.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo mảng hai chiều với số hàng và số cột tương ứng.
     * 2. Lặp qua từng vị trí [i][j] để yêu cầu người dùng nhập giá trị.
     * 3. Khởi tạo và trả về đối tượng Matrix từ mảng hai chiều.
     *
     * @param matrixLabel tên nhãn ma trận (ví dụ "Enter matrix1")
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
     * Chức năng (Làm gì): Hiển thị biểu thức và kết quả tính toán giữa hai ma trận.
     * Luồng xử lý (Làm như thế nào):
     * 1. In tiêu đề kết quả.
     * 2. In chuỗi ma trận thứ nhất.
     * 3. In toán tử (+, -, *).
     * 4. In chuỗi ma trận thứ hai.
     * 5. In dấu "=".
     * 6. In chuỗi ma trận kết quả.
     *
     * @param matrix1 ma trận thứ nhất
     * @param operator toán tử biểu diễn phép tính ("+", "-", "*")
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
     * Chức năng (Làm gì): Hiển thị thông báo hoặc tiêu đề ra console.
     * Luồng xử lý (Làm như thế nào): In thông điệp ra console bằng System.out.println.
     *
     * @param message thông điệp cần in
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
