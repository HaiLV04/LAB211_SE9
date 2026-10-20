package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Chức năng: Tiện ích nhập và kiểm tra tính hợp lệ của dữ liệu từ bàn phím.
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Ngăn chặn khởi tạo đối tượng từ lớp tiện ích.
     */
    private Validator() {
    }

    /**
     * Chức năng: Nhập và kiểm tra một số nguyên trong khoảng [min, max].
     * Luồng xử lý: Yêu cầu nhập, kiểm tra ép kiểu số nguyên và khoảng giá trị cho phép, lặp lại nếu không hợp lệ.
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
                int number = Integer.parseInt(SCANNER.nextLine().trim());
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
     * Chức năng: Nhập và kiểm tra một số thực trong khoảng [min, max].
     * Luồng xử lý: Yêu cầu nhập, ép kiểu sang số thực và kiểm tra phạm vi, lặp lại nếu không hợp lệ.
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
                double number = Double.parseDouble(SCANNER.nextLine().trim());
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
     * Chức năng: Nhập và kiểm tra chuỗi theo biểu thức chính quy (Regex).
     * Luồng xử lý: Yêu cầu nhập chuỗi và kiểm tra bằng matches(REGEX), lặp lại nếu không khớp định dạng.
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
            String str = SCANNER.nextLine().trim();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    /**
     * Chức năng: Nhập và kiểm tra ngày tháng hợp lệ trong khoảng [min, max].
     * Luồng xử lý: Sử dụng SimpleDateFormat với setLenient(false) để parse ngày và kiểm tra nằm trong khoảng [min, max].
     *
     * @param messageInfo thông báo nhập dữ liệu
     * @param messageErrorOutOfRange thông báo khi ngoài phạm vi
     * @param messageErrorInvalidDate thông báo khi sai định dạng ngày
     * @param REGEX định dạng ngày (ví dụ dd/MM/yyyy)
     * @param min ngày nhỏ nhất
     * @param max ngày lớn nhất
     * @return đối tượng Date hợp lệ
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
                Date date = dateFormat.parse(SCANNER.nextLine().trim());
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
