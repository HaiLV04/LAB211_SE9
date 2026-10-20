package view;

import java.util.Random;

/**
 * Chức năng: Tiện ích hỗ trợ hiển thị menu và sinh mã Captcha ngẫu nhiên.
 */
public class Helper {

    /**
     * Hàm khởi tạo mặc định.
     */
    public Helper() {
    }

    /**
     * Chức năng: Hiển thị menu chọn ngôn ngữ đăng nhập.
     * Luồng xử lý: In danh sách lựa chọn ngôn ngữ (1. Tiếng Việt, 2. Tiếng Anh, 3. Thoát).
     */
    public static void menu() {
        System.out.println("\n-------Login Program--------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
    }
    
    /**
     * Chức năng: Tạo chuỗi chứa các ký tự chữ cái (A-Z, a-z) và chữ số (0-9).
     * Luồng xử lý: Ghép bảng chữ cái hoa, thường và chữ số thành chuỗi nguồn.
     *
     * @return chuỗi gồm chữ hoa, chữ thường và chữ số
     */
    private static String genAlphaNumeric() {
        String alpha = "";
        String number = "0123456789";
        char c = 'A';
        while (c <= 'Z') {
            alpha += c;
            c++;
        }
        return alpha + alpha.toLowerCase() + number;
    }

    /**
     * Chức năng: Sinh mã Captcha ngẫu nhiên theo độ dài chỉ định.
     * Luồng xử lý: Chọn ngẫu nhiên từng ký tự từ bảng chữ-số cho đến khi đủ độ dài.
     *
     * @param length độ dài chuỗi captcha
     * @return chuỗi captcha ngẫu nhiên
     */
    public static String generateCaptcha(int length) {
        String alphaNumeric = genAlphaNumeric();
        StringBuilder captchaGen = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            captchaGen.append(alphaNumeric.charAt(random.nextInt(alphaNumeric.length())));
        }

        return captchaGen.toString();
    }
}
