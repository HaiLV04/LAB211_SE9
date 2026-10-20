package view;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Chức năng: Xử lý nhập và xuất dữ liệu cho chương trình Binary Search.
 */
public class ArrayView {

    private final Scanner scanner;

    /**
     * Khởi tạo Scanner để đọc dữ liệu từ bàn phím.
     */
    public ArrayView() {
        scanner = new Scanner(System.in);
    }

    /**
     * Chức năng: Nhập một số nguyên dương từ người dùng.
     * Luồng xử lý: Hiển thị thông báo, kiểm tra rỗng và số nguyên > 0, lặp lại nếu không hợp lệ.
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
     * Chức năng: Nhập một số nguyên bất kỳ từ người dùng.
     * Luồng xử lý: Hiển thị thông báo, ép kiểu số nguyên qua Integer.parseInt, lặp lại nếu không hợp lệ.
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
     * Chức năng: Hiển thị mảng số nguyên ra màn hình console.
     * Luồng xử lý: Ghép các phần tử của mảng theo định dạng [e1, e2, ...] và in ra màn hình.
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
     * Chức năng: Hiển thị kết quả tìm kiếm nhị phân với tất cả vị trí tìm được.
     * Luồng xử lý: Nếu mảng rỗng thì in "Can not found", ngược lại in danh sách các chỉ số tìm thấy.
     *
     * @param searchValue giá trị cần tìm
     * @param foundIndices mảng các vị trí tìm thấy
     */
    public void displaySearchResult(int searchValue, int[] foundIndices) {
        if (foundIndices == null || foundIndices.length == 0) {
            System.out.println("Can not found");
        } else {
            System.out.println("Found " + searchValue + " at index: " + Arrays.toString(foundIndices));
        }
    }

    /**
     * Chức năng: Hiển thị kết quả tìm kiếm nhị phân cho một vị trí.
     * Luồng xử lý: In vị trí tìm thấy nếu foundIndex != -1, ngược lại in "Can not found".
     *
     * @param searchValue giá trị cần tìm
     * @param foundIndex chỉ số tìm thấy (-1 nếu không thấy)
     */
    public void displaySearchResult(int searchValue, int foundIndex) {
        if (foundIndex != -1) {
            System.out.println("Found " + searchValue + " at index: " + foundIndex);
        } else {
            System.out.println("Can not found");
        }
    }
}
