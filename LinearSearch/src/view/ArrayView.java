package view;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Chức năng: Xử lý đầu vào và đầu ra cho chương trình Linear Search.
 */
public class ArrayView {

    private final Scanner scanner;

    /**
     * Chức năng (Làm gì): Khởi tạo scanner để nhận dữ liệu nhập từ người dùng.
     * Luồng xử lý (Làm như thế nào): Gán thuộc tính scanner bằng một đối tượng Scanner mới đọc từ System.in.
     */
    public ArrayView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Chức năng (Làm gì): Nhập một số nguyên dương từ người dùng.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu người dùng nhập.
     * 2. Kiểm tra nếu dữ liệu nhập rỗng, thông báo lỗi và lặp lại.
     * 3. Chuyển đổi dữ liệu sang số nguyên; nếu có NumberFormatException, thông báo lỗi và lặp lại.
     * 4. Kiểm tra nếu số lớn hơn 0 thì trả về giá trị đó.
     * 5. Nếu số <= 0, thông báo lỗi và tiếp tục lặp lại.
     *
     * @param message thông báo hiển thị cho người dùng
     * @return giá trị số nguyên dương
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
     * Chức năng (Làm gì): Nhập một số nguyên bất kỳ từ người dùng (giá trị tìm kiếm).
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu người dùng nhập.
     * 2. Kiểm tra nếu dữ liệu nhập rỗng, thông báo lỗi và lặp lại.
     * 3. Chuyển đổi dữ liệu nhập sang số nguyên bằng Integer.parseInt.
     * 4. Bắt lỗi NumberFormatException nếu không phải số, thông báo lỗi và lặp lại.
     * 5. Trả về giá trị số nguyên hợp lệ.
     *
     * @param message thông báo hiển thị cho người dùng
     * @return giá trị số nguyên hợp lệ
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
     * Chức năng (Làm gì): Hiển thị một mảng số nguyên.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo StringBuilder để tạo chuỗi kết quả.
     * 2. Thêm thông báo và dấu ngoặc vuông mở.
     * 3. Duyệt qua từng phần tử của mảng và thêm vào chuỗi.
     * 4. Thêm dấu phẩy giữa các phần tử (nếu chưa phải phần tử cuối).
     * 5. Thêm dấu ngoặc vuông đóng và hiển thị kết quả ra màn hình.
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
     * Chức năng (Làm gì): Hiển thị kết quả tìm kiếm tuyến tính (danh sách các chỉ số).
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra mảng chỉ số kết quả foundIndices.
     * 2. Nếu không tìm thấy (foundIndices null hoặc rỗng): In "Can not found".
     * 3. Nếu tìm thấy: In "Found {searchValue} at index: {Arrays.toString(foundIndices)}".
     *
     * @param searchValue giá trị cần tìm kiếm
     * @param foundIndices mảng các vị trí tìm thấy trong mảng
     */
    public void displaySearchResult(int searchValue, int[] foundIndices) {
        if (foundIndices == null || foundIndices.length == 0) {
            System.out.println("Can not found");
        } else {
            System.out.println("Found " + searchValue + " at index: " + Arrays.toString(foundIndices));
        }
    }
}
