package controller;

import java.util.Locale;
import java.util.ResourceBundle;

import view.Helper;
import view.Validate;

/**
 * Chức năng: Điều khiển menu chính và lựa chọn ngôn ngữ của chương trình.
 * Luồng tương tác: Hiển thị menu, nhận lựa chọn từ người dùng, thiết lập Locale tương ứng và gọi đến LoginService.
 */
public class TPBank {
    /**
     * Chức năng: Khởi động chương trình, cho phép người dùng chọn ngôn ngữ.
     * Luồng xử lý 1: Hiển thị menu lựa chọn ngôn ngữ hoặc thoát.
     * Luồng xử lý 2: Nhận lựa chọn hợp lệ từ người dùng (1-3).
     * Luồng xử lý 3: Thiết lập Locale cho hệ thống dựa trên lựa chọn (Tiếng Việt hoặc Tiếng Anh) hoặc thoát nếu chọn 3.
     * Luồng xử lý 4: Tải ResourceBundle theo Locale đã thiết lập và chuyển quyền điều khiển sang LoginService để đăng nhập.
     */
    public static void start() {
        Helper.menu();
        int choice = Validate.getInt("Please enter 1-3: ","Just 1->3","Invalid!", 1, 3);
        
        switch(choice){
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
