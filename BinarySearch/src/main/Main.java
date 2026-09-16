package main;

import controller.Controller;

/**
 * Chức năng: Lớp chứa hàm main để chạy chương trình.
 * Luồng tương tác:
 * - Yêu cầu người dùng nhập số lượng phần tử của mảng.
 * - Khởi tạo đối tượng mảng và hiển thị mảng đã sắp xếp.
 * - Yêu cầu người dùng nhập giá trị cần tìm kiếm và in ra kết quả.
 */
public class Main {

    /**
     * Chức năng: Điểm bắt đầu của ứng dụng, thực thi các bước của chương trình tìm kiếm nhị phân.
     * Luồng xử lý:
     * 1. Yêu cầu nhập số lượng phần tử mảng (lớn hơn 0).
     * 2. Khởi tạo đối tượng BinarySearch và truyền vào số lượng.
     * 3. Hiển thị mảng đã được tạo và sắp xếp.
     * 4. Yêu cầu người dùng nhập số cần tìm kiếm trong mảng.
     * 5. Gọi hàm tìm kiếm tất cả các vị trí và in ra màn hình nếu tìm thấy, hoặc thông báo không tìm thấy.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.execute();
    }
}
