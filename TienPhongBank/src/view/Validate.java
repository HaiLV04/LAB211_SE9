package view;

import java.util.Scanner;

/**
 * Chức năng: Cung cấp các phương thức để kiểm tra và xác thực dữ liệu đầu vào.
 * Luồng tương tác: Được gọi từ các lớp khác để đảm bảo dữ liệu người dùng nhập vào là hợp lệ.
 */
public class Validate {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Chức năng (Làm gì): Hàm khởi tạo private ngăn chặn việc tạo đối tượng của lớp tiện ích Validate.
     * Luồng xử lý (Làm như thế nào): Không thực hiện logic nào nhằm đảm bảo không thể khởi tạo instance của Utility class.
     */
    private Validate() {
    }

    /**
     * Chức năng (Làm gì): Nhập và kiểm tra một số nguyên nằm trong khoảng [min, max].
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập số.
     * 2. Đọc dữ liệu đầu vào và ép kiểu về số nguyên (Integer) bằng Integer.parseInt.
     * 3. Kiểm tra xem số nguyên có nằm trong khoảng [min, max] hay không; nếu hợp lệ thì trả về giá trị đó.
     * 4. Nếu nhập sai định dạng hoặc ngoài khoảng cho phép, in ra thông báo lỗi và yêu cầu nhập lại.
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
     * Chức năng (Làm gì): Nhập và kiểm tra một chuỗi theo định dạng biểu thức chính quy (Regex).
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập chuỗi.
     * 2. Đọc chuỗi và loại bỏ khoảng trắng ở hai đầu bằng trim().
     * 3. Kiểm tra chuỗi nhập vào với biểu thức Regex; nếu khớp thì trả về chuỗi.
     * 4. Nếu không khớp Regex, in ra lỗi và yêu cầu nhập lại.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messageError thông báo lỗi khi không khớp định dạng
     * @param REGEX biểu thức chính quy kiểm tra
     * @return chuỗi hợp lệ
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
     * Chức năng (Làm gì): Nhập và xác thực mã captcha từ người dùng.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập captcha.
     * 2. Đọc chuỗi nhập từ người dùng.
     * 3. So sánh chuỗi vừa nhập với mã captcha ngẫu nhiên đã tạo ra. Nếu khớp hoàn toàn thì thoát vòng lặp và trả về true.
     * 4. Nếu nhập sai, in ra thông báo lỗi và tiếp tục lặp lại yêu cầu nhập.
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
