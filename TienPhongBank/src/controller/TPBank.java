package controller;

import java.util.Locale;
import view.Helper;
import view.Validate;

/**
 * Chức năng: Điều khiển menu chính và lựa chọn ngôn ngữ của chương trình TPBank.
 */
public class TPBank {

    /**
     * Khởi tạo đối tượng TPBank.
     */
    public TPBank() {
    }

    /**
     * Chức năng: Kích hoạt chương trình TPBank.
     * Luồng xử lý: Gọi hàm start() để hiển thị menu và xử lý đăng nhập.
     */
    public void run() {
        start();
    }

    /**
     * Chỉnh sửa
     * Chức năng: Khởi động chương trình, cho phép người dùng chọn ngôn ngữ và thực hiện đăng nhập.
     * Luồng xử lý: Hiển thị menu, nhận lựa chọn (1: Tiếng Việt, 2: Tiếng Anh, 3: Thoát), gọi setLocate và thực hiện login.
     */
    public static void start() {
        Helper.menu();
        int choice = Validate.getInt("Please enter 1-3: ", "Just 1->3", "Invalid!", 1, 3);
        
        LoginService loginService = new LoginService();
        switch (choice) {
            case 1:
                loginService.setLocate(new Locale("vi", "VN"));
                loginService.login();
                break;
            case 2:
                loginService.setLocate(new Locale("en", "US"));
                loginService.login();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }
}
