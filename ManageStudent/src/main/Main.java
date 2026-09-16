package main;

import controller.Controller;
import view.Validator;

/**
 * Chức năng: Lớp Main dùng để chạy chương trình quản lý sinh viên, hiển thị menu và xử lý các lựa chọn của người dùng.
 * Luồng tương tác: Tương tác với người dùng qua console, điều hướng luồng xử lý tới các phương thức của lớp Controller.
 */
public class Main {

    /**
     * Chức năng: Phương thức chính (entry point) khởi chạy chương trình.
     * Luồng xử lý:
     * 1. Khởi tạo đối tượng Controller và sinh dữ liệu mẫu.
     * 2. Bắt đầu vòng lặp vô hạn hiển thị menu chương trình.
     * 3. Đọc lựa chọn của người dùng từ 1 tới 5.
     * 4. Gọi chức năng tương ứng trong Controller dựa trên lựa chọn. Bắt và in ra các ngoại lệ nếu có lỗi.
     * 5. Kết thúc chương trình nếu người dùng chọn 5 (Exit).
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Tạo đối tượng điều khiển chương trình
        Controller control = new Controller();
        try {
            control.generateStudent();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        // Hiển thị menu cho đến khi người dùng chọn Exit    
        while (true) {
            // Nhận lựa chọn từ menu
            int choice = Validator.getInt("WELCOME TO STUDENT MANAGEMENT\n"
                    + "1.	Create\n"
                    + "2.	Find and Sort\n"
                    + "3.	Update/Delete\n"
                    + "4.	Report\n"
                    + "5.	Exit\n"
                    + "Enter your choice: ", "Just be 1->5", "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                    // Thực hiện chức năng tạo sinh viên
                    control.createStudent();
                    System.out.println("Add success!");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 2:
                    try {
                    // Thực hiện chức năng tìm kiếm và sắp xếp
                    control.findAndSort();
                    System.out.println("Find and sort success!");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 3:
                    try {
                    // Thực hiện chức năng cập nhật hoặc xóa sinh viên
                    control.updateOrDelete();
                    System.out.println("Update or Delete success!");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 4:
                    try {
                    // Thực hiện chức năng báo cáo thống kê
                    control.report();
                    System.out.println("Report success!");
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 5:
                    // Kết thúc chương trình
                    System.exit(0);
            }
        }
    }

}
