package view;

import java.util.Random;

/**
 * Chức năng: Chứa các phương thức tiện ích hỗ trợ trong chương trình.
 * Luồng tương tác: Được gọi bởi các lớp khác để hiển thị menu hoặc tạo mã captcha.
 */
public class Helper {

    /**
     * Chức năng: Hiển thị menu chính của chương trình.
     * Luồng xử lý 1: In ra màn hình các lựa chọn ngôn ngữ và thoát.
     */
    public static void menu() {
        System.out.println("\n-------Login Program--------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
    }
    
    /**
     * Chức năng: Tạo chuỗi chứa các ký tự chữ cái (A-Z, a-z) và số (0-9).
     * Luồng xử lý 1: Nối các chữ cái in hoa từ A đến Z.
     * Luồng xử lý 2: Nối với các chữ cái in thường và các chữ số 0-9.
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
     * Chức năng: Khởi tạo mã captcha ngẫu nhiên với độ dài cho trước.
     * Luồng xử lý 1: Lấy chuỗi ký tự tổng hợp từ hàm genAlphaNumeric().
     * Luồng xử lý 2: Chọn ngẫu nhiên các ký tự từ chuỗi trên để tạo captcha có độ dài tương ứng.
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
