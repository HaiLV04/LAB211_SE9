package controller;

import model.Matrix;
import view.Validator;

/**
 * Chức năng: Lớp hỗ trợ nhập dữ liệu cho ma trận từ người dùng.
 * Luồng tương tác: Lớp này tương tác với lớp Validator để lấy dữ liệu đầu vào hợp lệ và tạo ra đối tượng Matrix.
 */
public class MatrixInputer {

    private Matrix matrix;

    /**
     * Chức năng: Khởi tạo đối tượng MatrixInputer.
     * Luồng xử lý: 
     * 1. Khởi tạo đối tượng rỗng.
     */
    public MatrixInputer() {
    }

    /**
     * Chức năng: Nhập các phần tử của ma trận với kích thước xác định.
     * Luồng xử lý: 
     * 1. Tạo mảng 2 chiều với số hàng và số cột tương ứng.
     * 2. Lặp qua từng phần tử để yêu cầu nhập liệu thông qua Validator.
     * 3. Tạo đối tượng Matrix từ mảng vừa nhập, xử lý ngoại lệ nếu có.
     * 4. Trả về đối tượng Matrix.
     * @param messInfor Thông báo hiển thị khi nhập liệu
     * @param row Số hàng của ma trận
     * @param col Số cột của ma trận
     * @return Đối tượng Matrix chứa dữ liệu đã nhập
     */
    public Matrix input(String messInfor, int row, int col) {
        int data[][] = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                data[i][j] = Validator.getInt(messInfor + String.format("[%d][%d]: ", i + 1, j + 1),
                        "Error range!", "Value of matrix is digit", Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
        try {
            matrix = new Matrix(data);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return matrix;
    }
}

