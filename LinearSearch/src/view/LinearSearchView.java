package view;

import java.util.Arrays;

/**
 * Chức năng: Kế thừa ArrayView từ thư viện BubbleSort và mở rộng hiển thị/nhập liệu cho Linear Search.
 */
public class LinearSearchView extends ArrayView {

    /**
     * Khởi tạo đối tượng LinearSearchView.
     */
    public LinearSearchView() {
        super();
    }

    /**
     * Chức năng: Nhập một số nguyên bất kỳ từ người dùng.
     * Luồng xử lý: Hiển thị thông báo, ép kiểu qua Integer.parseInt, lặp lại nếu không hợp lệ.
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
     * Chức năng: Hiển thị kết quả tìm kiếm với danh sách các vị trí tìm được.
     * Luồng xử lý: Nếu không tìm thấy in thông báo "Can not found", ngược lại in mảng các chỉ số tìm được.
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
     * Chức năng: Hiển thị kết quả tìm kiếm cho một vị trí.
     * Luồng xử lý: In vị trí tìm thấy nếu foundIndex != -1, ngược lại in "Can not found".
     *
     * @param searchValue giá trị cần tìm
     * @param foundIndex vị trí tìm thấy (-1 nếu không thấy)
     */
    public void displaySearchResult(int searchValue, int foundIndex) {
        if (foundIndex != -1) {
            System.out.println("Found " + searchValue + " at index: " + foundIndex);
        } else {
            System.out.println("Can not found");
        }
    }
}
