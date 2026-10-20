package model;

/**
 * Chức năng: Định nghĩa cấu trúc và các phép toán cơ bản trên ma trận (cộng, trừ, nhân).
 */
public class Matrix {

    private int rows;
    private int cols;
    private int[][] data;

    /**
     * Khởi tạo ma trận với số hàng và số cột chỉ định.
     *
     * @param rows số hàng
     * @param cols số cột
     * @throws Exception nếu số hàng hoặc số cột <= 0
     */
    public Matrix(int rows, int cols) throws Exception {
        if (rows > 0 && cols > 0) {
            this.rows = rows;
            this.cols = cols;
            data = new int[rows][cols];
        } else {
            throw new Exception("row and col must be >0");
        }
    }

    /**
     * Khởi tạo ma trận từ mảng hai chiều.
     *
     * @param data mảng 2 chiều chứa dữ liệu
     * @throws Exception nếu mảng dữ liệu là null
     */
    public Matrix(int[][] data) throws Exception {
        if (data == null) {
            throw new Exception("Data array not null or empty");
        }
        this.rows = data.length;
        this.cols = data[0].length;
        this.data = data;
    }

    /**
     * Chức năng: Cộng ma trận hiện tại với một ma trận khác.
     * Luồng xử lý: Kiểm tra cùng kích thước (rows, cols), cộng từng phần tử tương ứng và trả về ma trận kết quả.
     *
     * @param other ma trận cần cộng
     * @return ma trận tổng
     * @throws Exception nếu hai ma trận không cùng kích thước
     */
    public Matrix add(Matrix other) throws Exception {
        if (rows != other.rows || cols != other.cols) {
            throw new Exception("Rows and cols two matrix must be same");
        }
        int dataResult[][] = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dataResult[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return new Matrix(dataResult);
    }

    /**
     * Chức năng: Trừ ma trận hiện tại với một ma trận khác.
     * Luồng xử lý: Kiểm tra cùng kích thước (rows, cols), trừ từng phần tử tương ứng và trả về ma trận kết quả.
     *
     * @param other ma trận cần trừ
     * @return ma trận hiệu
     * @throws Exception nếu hai ma trận không cùng kích thước
     */
    public Matrix subtract(Matrix other) throws Exception {
        if (rows != other.rows || cols != other.cols) {
            throw new Exception("Rows and cols two matrix must be same");
        }
        int dataResult[][] = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dataResult[i][j] = this.data[i][j] - other.data[i][j];
            }
        }
        return new Matrix(dataResult);
    }

    /**
     * Chức năng: Nhân ma trận hiện tại với một ma trận khác.
     * Luồng xử lý: Kiểm tra số cột ma trận 1 bằng số hàng ma trận 2, tính tích từng hàng với từng cột và trả về ma trận kết quả.
     *
     * @param other ma trận cần nhân
     * @return ma trận tích
     * @throws Exception nếu số cột ma trận 1 khác số hàng ma trận 2
     */
    public Matrix multiply(Matrix other) throws Exception {
        if (cols != other.rows) {
            throw new Exception("Cols of matrix 1 must be equal rows of matrix 2");
        }
        int dataResult[][] = new int[rows][other.cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                for (int k = 0; k < cols; k++) {
                    dataResult[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return new Matrix(dataResult);
    }

    /**
     * Chức năng: Định dạng ma trận thành chuỗi hiển thị.
     * Luồng xử lý: Duyệt từng hàng, ghép từng phần tử theo mẫu [giá trị] và thêm ký tự xuống dòng.
     *
     * @return chuỗi biểu diễn ma trận
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(String.format("[%d]", data[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
