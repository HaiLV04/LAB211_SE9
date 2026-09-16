package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Chức năng: Lớp hỗ trợ kiểm tra dữ liệu đầu vào.
 * Luồng tương tác: Được gọi từ Controller và Main để nhận dữ liệu từ người dùng, kiểm tra bằng RegEx hoặc giá trị min/max.
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {
    }

    /**
     * Luồng xử lý 1: Lặp lại quá trình yêu cầu nhập số nguyên từ người dùng.
     * Luồng xử lý 2: Kiểm tra định dạng số, nếu lỗi ném thông báo.
     * Luồng xử lý 3: Kiểm tra giá trị nằm trong khoảng [min, max], trả về nếu hợp lệ.
     *
     * @param messageInfo thông báo nhập dữ liệu
     * @param messageErrorOutOfRange thông báo khi ngoài phạm vi
     * @param messageErrorInvalidNumber thông báo khi nhập sai định dạng
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
     * Luồng xử lý 1: Lặp lại quá trình yêu cầu nhập số thực từ người dùng.
     * Luồng xử lý 2: Kiểm tra định dạng số, nếu lỗi ném thông báo.
     * Luồng xử lý 3: Kiểm tra giá trị nằm trong khoảng [min, max], trả về nếu hợp lệ.
     *
     * @param messageInfo thông báo nhập dữ liệu
     * @param messageErrorOutOfRange thông báo khi ngoài phạm vi
     * @param messageErrorInvalidNumber thông báo khi nhập sai định dạng
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
     * Luồng xử lý 1: Lặp lại quá trình yêu cầu nhập chuỗi từ người dùng.
     * Luồng xử lý 2: Dùng biểu thức chính quy (REGEX) để kiểm tra tính hợp lệ, trả về nếu khớp.
     *
     * @param messageInfo thông báo nhập dữ liệu
     * @param messageError thông báo lỗi
     * @param REGEX biểu thức chính quy
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

    /**
     * Luồng xử lý 1: Lặp lại quá trình yêu cầu nhập ngày tháng từ người dùng.
     * Luồng xử lý 2: Cố gắng phân tích chuỗi nhập vào theo SimpleDateFormat xác định.
     * Luồng xử lý 3: Kiểm tra nếu thời gian nằm trong khoảng cho phép, trả về hợp lệ.
     *
     * @param messageInfo thông báo nhập dữ liệu
     * @param messageErrorOutOfRange thông báo khi ngoài phạm vi
     * @param messageErrorInvalidDate thông báo khi sai định dạng ngày
     * @param REGEX định dạng ngày
     * @param min ngày nhỏ nhất
     * @param max ngày lớn nhất
     * @return ngày hợp lệ
     */
    public static Date getDate(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidDate,
            final String REGEX,
            Date min, Date max) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        while (true) {
            System.out.print(messageInfo);
            try {
                Date date = dateFormat.parse(SCANNER.nextLine());
                if (date.compareTo(min) >= 0 && date.compareTo(max) <= 0) {
                    return date;
                }
                System.err.println(messageErrorOutOfRange);
            } catch (ParseException e) {
                System.err.println(messageErrorInvalidDate);
            }
        }
    }
}
