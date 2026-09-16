package view;

import java.util.Scanner;

/**
 * Chức năng: Xử lý đầu vào và đầu ra cho chương trình Bubble Sort.
 */
public class ArrayView {

    private final Scanner scanner;

    /**
     * Chức năng: Khởi tạo scanner để nhận dữ liệu nhập từ người dùng.
     */
    public ArrayView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Chức năng: Nhập một số nguyên dương từ người dùng.
     * Luồng tương tác:
     * 1. Hiển thị thông báo yêu cầu người dùng nhập.
     * 2. Kiểm tra nếu dữ liệu nhập không phải là số nguyên, thông báo lỗi và lặp lại.
     * 3. Đọc số nguyên.
     * 4. Kiểm tra nếu số lớn hơn 0 thì trả về giá trị đó.
     * 5. Nếu không, thông báo lỗi và lặp lại.
     *
     * @param message thông báo hiển thị cho người dùng
     * @return giá trị số nguyên dương
     */
    public int inputPositiveInteger(String message) {
        int number;

        while (true) {
            System.out.print(message);

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.nextLine();
                continue;
            }

            number = scanner.nextInt();
            scanner.nextLine();

            if (number > 0) {
                return number;
            }

            System.out.println("Invalid input. Number must be greater than 0.");
        }
    }

    /**
     * Chức năng: Hiển thị một mảng số nguyên.
     * Luồng xử lý:
     * 1. Khởi tạo StringBuilder để tạo chuỗi kết quả.
     * 2. Thêm thông báo và dấu ngoặc vuông mở.
     * 3. Duyệt qua từng phần tử của mảng và thêm vào chuỗi.
     * 4. Thêm dấu phẩy giữa các phần tử (nếu chưa phải phần tử cuối).
     * 5. Thêm dấu ngoặc vuông đóng và hiển thị kết quả.
     *
     * @param message thông báo hiển thị trước mảng
     * @param array mảng số nguyên cần hiển thị
     */
    public void displayArray(String message, int[] array) {
        StringBuilder result = new StringBuilder();

        result.append(message);
        result.append("[");

        for (int i = 0; i < array.length; i++) {
            result.append(array[i]);

            if (i < array.length - 1) {
                result.append(", ");
            }
        }

        result.append("]");

        System.out.println(result);
    }
}
