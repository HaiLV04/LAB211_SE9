package main;

import controller.MatrixInputer;
import model.Matrix;
import view.Validator;

/**
 * Chức năng: Lớp chính chứa điểm vào của chương trình, quản lý luồng thực thi của máy tính ma trận.
 * Luồng tương tác: Hiển thị menu cho người dùng chọn chức năng, gọi MatrixInputer để nhập liệu và các phương thức của Matrix để tính toán.
 */
public class Main {

    /**
     * Chức năng: Phương thức main để chạy chương trình.
     * Luồng xử lý:
     * 1. Hiển thị menu chức năng (Cộng, Trừ, Nhân, Thoát).
     * 2. Lấy lựa chọn hợp lệ từ người dùng qua Validator.
     * 3. Thực hiện chức năng tương ứng:
     *    - Nếu chọn 1 (Cộng): Nhập hai ma trận có cùng kích thước, gọi hàm add() và in kết quả.
     *    - Nếu chọn 2 (Trừ): Nhập hai ma trận có cùng kích thước, gọi hàm subtract() và in kết quả.
     *    - Nếu chọn 3 (Nhân): Nhập ma trận 1, sau đó nhập ma trận 2 sao cho số cột ma trận 1 bằng số hàng ma trận 2, gọi hàm multiply() và in kết quả.
     *    - Nếu chọn 4 (Thoát): Kết thúc chương trình.
     * 4. Bắt và in ra các ngoại lệ nếu có lỗi trong quá trình tính toán.
     * 5. Lặp lại bước 1 cho đến khi chọn Thoát.
     * @param args Tham số dòng lệnh
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MatrixInputer inputer1 = new MatrixInputer();
        MatrixInputer inputer2 = new MatrixInputer();
        Matrix matrix1, matrix2, result;
        while (true) {
            int choice = Validator.getInt("==========Calculator program============\n"
                    + "1. Addition Matrix\n"
                    + "2. Subtraction Matrix\n"
                    + "3. Multiplication Matrix\n"
                    + "4. Quit\n"
                    + "Enter your choice: ", "Just be 1 -> 4", "Please enter integer number", 1, 4);
            switch (choice) {
                case 1:
                    System.out.println("---------Addition---------");
                    int rows1 = Validator.getInt("Enter Row Matrix 1: ", "Just be >0 ",
                            "Please enter integer number!", 1, Integer.MAX_VALUE);
                    int cols1 = Validator.getInt("Enter Column Matrix 1: ", "Just be >0 ",
                            "Please enter integer number!", 1, Integer.MAX_VALUE);
                    matrix1 = inputer1.input("Enter matrix1", rows1, cols1);
                    int rows2 = Validator.getInt("Enter Row Matrix 2: ", "Just be = " + rows1,
                            "Please enter integer number!", rows1, rows1);
                    int cols2 = Validator.getInt("Enter Column Matrix 2: ", "Just be = " + cols1,
                            "Please enter integer number!", cols1, cols1);
                    matrix2 = inputer2.input("Enter matrix2", rows2, cols2);
                    System.out.println("-------------Result-------------");
                    System.out.print(matrix1.toString());
                    System.out.println("+");
                    System.out.print(matrix2.toString());
                    System.out.println("=");
                    try {
                        result = matrix1.add(matrix2);
                        System.out.println(result.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("---------Subtraction---------");
                    rows1 = Validator.getInt("Enter Row Matrix 1: ", "Just be >0 ",
                            "Please enter integer number!", 1, Integer.MAX_VALUE);
                    cols1 = Validator.getInt("Enter Column Matrix 1: ", "Just be >0 ",
                            "Please enter integer number!", 1, Integer.MAX_VALUE);
                    matrix1 = inputer1.input("Enter matrix1", rows1, cols1);
                    rows2 = Validator.getInt("Enter Row Matrix 2: ", "Just be = " + rows1,
                            "Please enter integer number!", rows1, rows1);
                    cols2 = Validator.getInt("Enter Column Matrix 2: ", "Just be = " + cols1,
                            "Please enter integer number!", cols1, cols1);
                    matrix2 = inputer2.input("Enter matrix2", rows2, cols2);
                    System.out.println("-------------Result-------------");
                    System.out.print(matrix1.toString());
                    System.out.println("-");
                    System.out.print(matrix2.toString());
                    System.out.println("=");
                    try {
                        result = matrix1.subtract(matrix2);
                        System.out.println(result.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("---------Multiplication---------");
                    rows1 = Validator.getInt("Enter Row Matrix 1: ", "Just be >0 ",
                            "Please enter integer number!", 1, Integer.MAX_VALUE);
                    cols1 = Validator.getInt("Enter Column Matrix 1: ", "Just be >0 ",
                            "Please enter integer number!", 1, Integer.MAX_VALUE);
                    matrix1 = inputer1.input("Enter matrix1", rows1, cols1);
                    rows2 = Validator.getInt("Enter Row Matrix 2: ", "Just be = " + cols1,
                            "Please enter integer number!", cols1, cols1);
                    cols2 = Validator.getInt("Enter Column Matrix 2: ", "Just be >0 ",
                            "Please enter integer number!", 1, Integer.MAX_VALUE);
                    matrix2 = inputer2.input("Enter matrix2", rows2, cols2);
                    System.out.println("-------------Result-------------");
                    System.out.print(matrix1.toString());
                    System.out.println("*");
                    System.out.print(matrix2.toString());
                    System.out.println("=");
                    try {
                        result = matrix1.multiply(matrix2);
                        System.out.println(result.toString());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }

}

