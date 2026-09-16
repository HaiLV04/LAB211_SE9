package main;

import controller.Controller;
import model.Task;
import view.Validator;

/**
 * Chức năng: Lớp Main chứa phương thức khởi chạy ứng dụng.
 * Luồng tương tác: Hiển thị menu, nhận lựa chọn của người dùng, gọi các chức năng tương ứng trong Controller và in kết quả hoặc lỗi ra màn hình.
 */
public class Main {

    /**
     * Chức năng: Khởi chạy ứng dụng Java Console.
     * Luồng xử lý:
     * 1. Khởi tạo đối tượng Controller.
     * 2. Bắt đầu vòng lặp while(true) hiển thị menu chức năng (1-4).
     * 3. Sử dụng Validator lấy lựa chọn hợp lệ từ người dùng.
     * 4. Gọi chức năng tương ứng trong Controller dựa trên lựa chọn (Add, Delete, Show, Exit).
     * 5. Hiển thị kết quả thành công hoặc bắt ngoại lệ và in ra lỗi nếu có.
     * 
     * @param args các đối số dòng lệnh.
     */
    public static void main(String[] args) {
        Controller control = new Controller();
        while (true) {
            int choice = Validator.getInt("========= Task program =========\n"
                    + "1.	Add Task\n"
                    + "2.	Delete task\n"
                    + "3.	Display Task\n"
                    + "4.	exit\n"
                    + "Enter your choice: ", "Just 1-> 4", "Invalid!", 1, 4);
            switch (choice) {
                case 1:
                    System.out.println("------------Add Task------------");
                    try {
                        int IDTask = control.add();
                        System.out.println("Add success task id: " + IDTask);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("------------Del Task------------");
                    try {
                        Task task= control.delete();
                        System.out.println("Delete success:");
                        System.out.println(task);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("------------Show Task------------");
                    try {
                        control.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
            }
        }
    }
}
