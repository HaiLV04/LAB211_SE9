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
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp TPBank.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng TPBank mới.
     */
    public TPBank() {
    }

    /**
     * Chức năng (Làm gì): Kích hoạt chương trình TPBank qua phương thức run().
     * Luồng xử lý (Làm như thế nào): Gọi phương thức start() để bắt đầu hiển thị menu và xử lý đăng nhập.
     */
    public void run() {
        start();
    }

    /**
     * Chức năng (Làm gì): Khởi động chương trình, cho phép người dùng chọn ngôn ngữ và thực hiện đăng nhập.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị menu lựa chọn ngôn ngữ (Vietnamese, English) hoặc thoát qua Helper.menu().
     * 2. Nhận lựa chọn hợp lệ từ người dùng trong khoảng 1 đến 3 qua Validate.getInt().
     * 3. Thiết lập Locale cho hệ thống dựa trên lựa chọn (Tiếng Việt: "vi_VN", Tiếng Anh: "en_US") hoặc thoát (chọn 3).
     * 4. Tải ResourceBundle theo Locale đã thiết lập và chuyển quyền điều khiển sang LoginService để đăng nhập.
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
