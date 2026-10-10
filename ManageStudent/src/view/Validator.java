package view;

import java.util.Scanner;

/**
 * Chức năng: Lớp Validator chứa các phương thức tiện ích hỗ trợ kiểm tra và lấy dữ liệu nhập vào từ bàn phím.
 * Luồng tương tác: Được sử dụng ở khắp các nơi trong chương trình (như Controller, StudentInputer, Main) nhằm đảm bảo dữ liệu nhập hợp lệ.
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Chức năng (Làm gì): Hàm khởi tạo private ngăn chặn việc tạo đối tượng của lớp tiện ích.
     * Luồng xử lý (Làm như thế nào): Không thực hiện logic nào nhằm đảm bảo không thể khởi tạo instance của Utility class.
     */
    private Validator() {
    }

    /**
     * Chức năng (Làm gì): Nhập và trả về một số nguyên hợp lệ trong khoảng cho phép.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập dữ liệu.
     * 2. Nhận dữ liệu nhập, cố gắng ép kiểu về số nguyên bằng Integer.parseInt.
     * 3. Kiểm tra nếu giá trị nằm trong khoảng [min, max] thì trả về số đó.
     * 4. Nếu nằm ngoài khoảng, in thông báo lỗi ngoài khoảng. Nếu không phải số, in thông báo lỗi số không hợp lệ. Lặp lại việc nhập.
     *
     * @param messageInfo thông báo yêu cầu nhập dữ liệu
     * @param messageErrorOutOfRange thông báo lỗi khi giá trị ngoài phạm vi
     * @param messageErrorInvalidNumber thông báo lỗi khi dữ liệu không phải số nguyên
     * @param min giá trị nhỏ nhất
     * @param max giá trị lớn nhất
     * @return số nguyên hợp lệ
     */
    public static int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Chức năng (Làm gì): Nhập và trả về một số thực hợp lệ trong khoảng cho phép.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập dữ liệu.
     * 2. Nhận dữ liệu nhập, cố gắng ép kiểu về số thực bằng Double.parseDouble.
     * 3. Kiểm tra nếu giá trị nằm trong khoảng [min, max] thì trả về số đó.
     * 4. Nếu nằm ngoài khoảng, in thông báo lỗi ngoài khoảng. Nếu không phải số, in thông báo lỗi số không hợp lệ. Lặp lại việc nhập.
     *
     * @param messageInfo thông báo yêu cầu nhập dữ liệu
     * @param messageErrorOutOfRange thông báo lỗi khi giá trị ngoài phạm vi
     * @param messageErrorInvalidNumber thông báo lỗi khi dữ liệu không phải số thực
     * @param min giá trị nhỏ nhất
     * @param max giá trị lớn nhất
     * @return số thực hợp lệ
     */
    public static double getDouble(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            double min, double max) {
        do {
            try {
                System.out.println(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Chức năng (Làm gì): Nhập và trả về một chuỗi hợp lệ theo định dạng biểu thức chính quy.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập dữ liệu.
     * 2. Nhận dữ liệu chuỗi nhập vào từ người dùng.
     * 3. Dùng biểu thức chính quy (REGEX) kiểm tra chuỗi. Nếu khớp thì trả về chuỗi đó.
     * 4. Nếu không khớp, in thông báo lỗi và yêu cầu nhập lại.
     *
     * @param messageInfo thông báo yêu cầu nhập dữ liệu
     * @param messageError thông báo lỗi khi dữ liệu không hợp lệ
     * @param REGEX biểu thức chính quy dùng để kiểm tra dữ liệu
     * @return chuỗi hợp lệ
     */
    public static String getString(String messageInfo, String messageError,
            final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }
}
