package view;

import java.util.Scanner;

/**
 * Chức năng: Xử lý nhập và xuất dữ liệu cho chương trình Merge Sort.
 */
public class ArrayView {

    private final Scanner scanner;

    /**
     * Chức năng: Khởi tạo scanner để nhận dữ liệu từ người dùng.
     */
    public ArrayView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Chức năng: Nhập một số nguyên dương từ người dùng.
     * Luồng tương tác:
     * 1. Hiển thị thông báo yêu cầu nhập.
     * 2. Bắt lỗi chuỗi rỗng: thông báo lỗi và yêu cầu nhập lại.
     * 3. Bắt lỗi không phải số nguyên: thông báo lỗi và yêu cầu nhập lại.
     * 4. Ép kiểu sang số nguyên.
     * 5. Kiểm tra nếu số lớn hơn 0 thì trả về giá trị.
     * 6. Nếu nhỏ hơn hoặc bằng 0, thông báo lỗi và yêu cầu nhập lại.
     *
     * @param message thông báo hiển thị cho người dùng
     * @return số nguyên dương hợp lệ
     */
    public int inputPositiveInteger(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter a positive integer.");
                continue;
            }

            try {
                int number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                }
                System.out.println("Invalid input. Number must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive integer.");
            }
        }
    }

    /**
     * Chức năng: Hiển thị một mảng số nguyên ra màn hình console.
     * Luồng xử lý:
     * 1. Khởi tạo StringBuilder để tạo chuỗi kết quả.
     * 2. Thêm thông báo tiền tố và dấu mở ngoặc vuông.
     * 3. Duyệt qua từng phần tử của mảng và thêm vào chuỗi.
     * 4. Thêm dấu phẩy giữa các phần tử.
     * 5. Thêm dấu đóng ngoặc vuông và in kết quả ra màn hình.
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
