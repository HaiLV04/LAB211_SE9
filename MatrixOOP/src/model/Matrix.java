package model;

/**
 * Chức năng: Định nghĩa cấu trúc và các phép toán của một ma trận.
 * Luồng tương tác: Lớp này chứa dữ liệu của ma trận và thực hiện các phép toán (cộng, trừ, nhân) giữa các đối tượng Matrix.
 */
public class Matrix {

    private int rows;
    private int cols;
    private int[][] data;

    /**
     * Chức năng (Làm gì): Khởi tạo ma trận rỗng với số hàng và số cột cho trước.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra số hàng và số cột phải lớn hơn 0. Nếu không, ném ngoại lệ.
     * 2. Gán giá trị rows, cols và khởi tạo mảng data.
     *
     * @param rows Số hàng của ma trận
     * @param cols Số cột của ma trận
     * @throws Exception Nếu số hàng hoặc cột không hợp lệ
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
     * Chức năng (Làm gì): Khởi tạo ma trận từ mảng 2 chiều có sẵn.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra mảng đầu vào không được null, nếu null ném ngoại lệ.
     * 2. Gán giá trị rows, cols và lưu mảng data.
     *
     * @param data Mảng 2 chiều chứa dữ liệu ma trận
     * @throws Exception Nếu mảng dữ liệu null
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
     * Chức năng (Làm gì): Cộng ma trận hiện tại với một ma trận khác.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra kích thước hai ma trận có khớp nhau hay không, nếu không ném ngoại lệ.
     * 2. Khởi tạo mảng kết quả.
     * 3. Duyệt từng phần tử để thực hiện phép cộng tương ứng.
     * 4. Trả về đối tượng Matrix mới chứa kết quả.
     *
     * @param other Ma trận cần cộng
     * @return Đối tượng Matrix là tổng của hai ma trận
     * @throws Exception Nếu kích thước hai ma trận không hợp lệ
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
        Matrix result = new Matrix(dataResult);
        return result;
    }

    /**
     * Chức năng (Làm gì): Trừ ma trận hiện tại với một ma trận khác.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra kích thước hai ma trận có khớp nhau hay không, nếu không ném ngoại lệ.
     * 2. Khởi tạo mảng kết quả.
     * 3. Duyệt từng phần tử để thực hiện phép trừ tương ứng.
     * 4. Trả về đối tượng Matrix mới chứa kết quả.
     *
     * @param other Ma trận cần trừ
     * @return Đối tượng Matrix là hiệu của hai ma trận
     * @throws Exception Nếu kích thước hai ma trận không hợp lệ
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
        Matrix result = new Matrix(dataResult);
        return result;
    }

    /**
     * Chức năng (Làm gì): Nhân ma trận hiện tại với một ma trận khác.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra số cột của ma trận này có bằng số hàng của ma trận kia hay không, nếu không ném ngoại lệ.
     * 2. Khởi tạo mảng kết quả với số hàng của ma trận này và số cột của ma trận kia.
     * 3. Thực hiện 3 vòng lặp để nhân các phần tử tương ứng.
     * 4. Trả về đối tượng Matrix mới chứa kết quả.
     *
     * @param other Ma trận cần nhân
     * @return Đối tượng Matrix là tích của hai ma trận
     * @throws Exception Nếu kích thước ma trận không hợp lệ để nhân
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
        Matrix result = new Matrix(dataResult);
        return result;
    }

    /**
     * Chức năng (Làm gì): Chuyển đổi ma trận thành chuỗi để hiển thị.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo chuỗi kết quả.
     * 2. Duyệt từng phần tử và định dạng in theo mẫu [giá trị].
     * 3. Thêm ký tự xuống dòng sau mỗi hàng.
     * 4. Trả về chuỗi hiển thị.
     *
     * @return Chuỗi đại diện cho ma trận
     */
    public String toString() {
        String str = "";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                str += String.format("[%d]", data[i][j]);
            }
            str += "\n";
        }
        return str;
    }
}
