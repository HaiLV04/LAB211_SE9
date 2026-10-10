package view;

import java.util.Random;

/**
 * Chức năng: Chứa các phương thức tiện ích hỗ trợ trong chương trình.
 * Luồng tương tác: Được gọi bởi các lớp khác để hiển thị menu hoặc tạo mã captcha.
 */
public class Helper {

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp tiện ích Helper.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng Helper mới.
     */
    public Helper() {
    }

    /**
     * Chức năng (Làm gì): Hiển thị menu chính của chương trình.
     * Luồng xử lý (Làm như thế nào): In ra màn hình console các lựa chọn ngôn ngữ (1. Vietnamese, 2. English) và thoát (3. Exit).
     */
    public static void menu() {
        System.out.println("\n-------Login Program--------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
    }
    
    /**
     * Chức năng (Làm gì): Tạo chuỗi chứa các ký tự chữ cái (A-Z, a-z) và số (0-9).
     * Luồng xử lý (Làm như thế nào):
     * 1. Nối các chữ cái in hoa từ A đến Z bằng vòng lặp.
     * 2. Nối với các chữ cái in thường và các chữ số 0-9 để tạo bảng ký tự hoàn chỉnh.
     * 3. Trả về chuỗi tập hợp ký tự đã ghép.
     *
     * @return chuỗi gồm chữ hoa, chữ thường và chữ số
     */
    private static String genAlphaNumeric() {
        String alpha = "";
        String number = "0123456789";
        String alphaNumeric = "";
        char c = 'A';
        while (c <= 'Z') {
            alpha += c;
            c++;
        }
        alphaNumeric = alpha + alpha.toLowerCase() + number;
        return alphaNumeric;
    }

    /**
     * Chức năng (Làm gì): Khởi tạo mã captcha ngẫu nhiên với độ dài cho trước.
     * Luồng xử lý (Làm như thế nào):
     * 1. Lấy chuỗi ký tự nguồn từ hàm genAlphaNumeric().
     * 2. Sử dụng Random để chọn ngẫu nhiên từng ký tự từ chuỗi nguồn cho đến khi đạt độ dài chỉ định.
     * 3. Trả về chuỗi mã Captcha đã tạo.
     *
     * @param length độ dài của chuỗi captcha
     * @return chuỗi captcha ngẫu nhiên
     */
    public static String generateCaptcha(int length) {
        String alphaNumeric = genAlphaNumeric();
        String captchaGen = "";

        for (int i = 0; i < length; i++) {
            captchaGen += alphaNumeric.charAt(new Random().nextInt(alphaNumeric.length()));
        }

        return captchaGen;
    }
}
