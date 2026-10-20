package controller;

import java.util.Locale;
import java.util.ResourceBundle;
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
     * Chức năng: Khởi động chương trình, cho phép người dùng chọn ngôn ngữ và thực hiện đăng nhập.
     * Luồng xử lý: Hiển thị menu, nhận lựa chọn (1: Tiếng Việt, 2: Tiếng Anh, 3: Thoát), tải ResourceBundle tương ứng và gọi LoginService.
     */
    public static void start() {
        Helper.menu();
        int choice = Validate.getInt("Please enter 1-3: ", "Just 1->3", "Invalid!", 1, 3);
        
        switch (choice) {
            case 1:
                Locale.setDefault(new Locale("vi", "VN"));
                break;
            case 2:
                Locale.setDefault(new Locale("en", "US"));
                break;
            case 3:
                System.exit(0);
                break;
        }
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/Language");
        new LoginService().login(resourceBundle);
    }
}
