package view;

import java.util.Scanner;

/**
 * Chức năng: Tiện ích nhập và kiểm tra tính hợp lệ của dữ liệu từ bàn phím.
 */
public class Validate {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Ngăn chặn khởi tạo đối tượng từ lớp tiện ích.
     */
    private Validate() {
    }

    /**
     * Chức năng: Nhập và kiểm tra một số nguyên trong khoảng [min, max].
     * Luồng xử lý: Yêu cầu nhập, kiểm tra ép kiểu số nguyên và khoảng giá trị cho phép, lặp lại nếu không hợp lệ.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messsageErrorOutOfRange thông báo khi số nằm ngoài khoảng
     * @param messageErrorNumber thông báo khi không phải là số
     * @param min giá trị nhỏ nhất
     * @param max giá trị lớn nhất
     * @return số nguyên hợp lệ
     */
    public static int getInt(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    /**
     * Chức năng: Nhập và kiểm tra chuỗi theo biểu thức chính quy (Regex).
     * Luồng xử lý: Yêu cầu nhập chuỗi và kiểm tra bằng matches(REGEX), lặp lại nếu không khớp định dạng.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messageError thông báo lỗi khi không khớp định dạng
     * @param REGEX biểu thức chính quy kiểm tra
     * @return chuỗi hợp lệ
     */
    public static String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine().trim();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    /**
     * Chức năng: Nhập và xác thực mã Captcha từ người dùng.
     * Luồng xử lý: Yêu cầu nhập và so khớp với mã Captcha đã sinh, lặp lại nếu nhập sai.
     *
     * @param message thông báo yêu cầu nhập
     * @param messageError thông báo lỗi khi captcha sai
     * @param captchaGenerate chuỗi captcha ngẫu nhiên cần đối chiếu
     * @return true khi nhập đúng captcha
     */
    public static boolean verifyCaptcha(String message, String messageError, String captchaGenerate) {
        String captchaInput;
        while (true) {
            System.out.printf(message);
            captchaInput = SCANNER.nextLine().trim();
            if (captchaGenerate.equals(captchaInput)) {
                break;
            }
            System.out.println(messageError);
        }
        return true;
    }
}
