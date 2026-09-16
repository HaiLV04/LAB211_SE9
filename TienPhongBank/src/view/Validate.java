package view;

import java.util.Scanner;

/**
 * Chức năng: Cung cấp các phương thức để kiểm tra và xác thực dữ liệu đầu vào.
 * Luồng tương tác: Được gọi từ các lớp khác để đảm bảo dữ liệu người dùng nhập vào là hợp lệ.
 */
public class Validate {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validate() {

    }

    /**
     * Chức năng: Nhập và kiểm tra một số nguyên nằm trong khoảng [min, max].
     * Luồng xử lý 1: Hiển thị thông báo yêu cầu nhập số.
     * Luồng xử lý 2: Đọc dữ liệu đầu vào và ép kiểu về số nguyên (Integer).
     * Luồng xử lý 3: Kiểm tra xem số nguyên có nằm trong khoảng [min, max] hay không, nếu hợp lệ thì trả về giá trị đó.
     * Luồng xử lý 4: Nếu nhập sai định dạng hoặc ngoài khoảng cho phép, in ra thông báo lỗi và yêu cầu nhập lại.
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
     * Chức năng: Nhập và kiểm tra một chuỗi theo định dạng Regex.
     * Luồng xử lý 1: Hiển thị thông báo yêu cầu nhập chuỗi.
     * Luồng xử lý 2: Đọc chuỗi và loại bỏ khoảng trắng ở hai đầu.
     * Luồng xử lý 3: Kiểm tra chuỗi nhập vào với biểu thức Regex, nếu khớp thì trả về chuỗi.
     * Luồng xử lý 4: Nếu không khớp Regex, in ra lỗi và yêu cầu nhập lại.
     */
    public static String getString(String messageInfo, String messageError, final String REGEX){
        do {            
            System.out.print(messageInfo);
            String str = SCANNER.nextLine().trim();
            if(str.matches(REGEX)){
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    /**
     * Chức năng: Nhập và xác thực mã captcha.
     * Luồng xử lý 1: Hiển thị thông báo yêu cầu nhập captcha.
     * Luồng xử lý 2: Đọc chuỗi nhập từ người dùng.
     * Luồng xử lý 3: So sánh chuỗi vừa nhập với mã captcha ngẫu nhiên đã tạo ra. Nếu giống nhau thì thoát vòng lặp.
     * Luồng xử lý 4: Nếu nhập sai, in ra thông báo lỗi và yêu cầu nhập lại.
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
