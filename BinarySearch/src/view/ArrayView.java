package view;

import java.util.Scanner;

/**
 * Chức năng: Xử lý nhập và xuất dữ liệu cho chương trình Binary Search.
 */
public class ArrayView {

    private final Scanner scanner;

    /**
     * Chức năng (Làm gì): Khởi tạo scanner để đọc dữ liệu từ người dùng.
     * Luồng xử lý (Làm như thế nào): Gán thuộc tính scanner bằng một đối tượng Scanner mới đọc từ System.in.
     */
    public ArrayView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Chức năng (Làm gì): Nhập một số nguyên dương từ người dùng.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập.
     * 2. Kiểm tra chuỗi rỗng: thông báo lỗi và yêu cầu nhập lại.
     * 3. Bắt lỗi không phải số: thông báo lỗi và yêu cầu nhập lại nếu có NumberFormatException.
     * 4. Ép kiểu sang số nguyên và kiểm tra lớn hơn 0.
     * 5. Trả về giá trị nếu hợp lệ, ngược lại thông báo lỗi và tiếp tục lặp.
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
     * Chức năng (Làm gì): Nhập một số nguyên bất kỳ từ người dùng (giá trị cần tìm kiếm).
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập.
     * 2. Kiểm tra chuỗi rỗng: thông báo lỗi và yêu cầu nhập lại.
     * 3. Chuyển đổi chuỗi sang số nguyên bằng Integer.parseInt.
     * 4. Bắt lỗi NumberFormatException nếu không phải số nguyên, thông báo lỗi và yêu cầu nhập lại.
     * 5. Trả về số nguyên hợp lệ.
     *
     * @param message thông báo hiển thị cho người dùng
     * @return số nguyên hợp lệ
     */
    public int inputInteger(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Invalid input. Please enter an integer.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    /**
     * Chức năng (Làm gì): Hiển thị một mảng số nguyên ra màn hình console.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo StringBuilder để ghép chuỗi.
     * 2. Thêm tiền tố thông báo và dấu mở ngoặc vuông.
     * 3. Duyệt qua các phần tử của mảng và thêm vào chuỗi.
     * 4. Thêm dấu phẩy giữa các phần tử (ngoại trừ phần tử cuối).
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

    /**
     * Chức năng (Làm gì): Hiển thị kết quả tìm kiếm nhị phân ra màn hình console.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra chỉ số tìm kiếm foundIndex.
     * 2. Nếu tìm thấy (foundIndex != -1): In "Found {searchValue} at index: {foundIndex}".
     * 3. Nếu không tìm thấy: In "Can not found".
     *
     * @param searchValue giá trị cần tìm kiếm
     * @param foundIndex chỉ số tìm thấy trong mảng (-1 nếu không tìm thấy)
     */
    public void displaySearchResult(int searchValue, int foundIndex) {
        if (foundIndex != -1) {
            System.out.println("Found " + searchValue + " at index: " + foundIndex);
        } else {
            System.out.println("Can not found");
        }
    }
}
