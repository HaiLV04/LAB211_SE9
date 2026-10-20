package view;

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
     * @param messageInfo thông báo yêu cầu nhập
     * @param messageErrorOutOfRange thông báo khi giá trị ngoài khoảng
     * @param messageErrorInvalidNumber thông báo khi không phải số nguyên
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
     * Luồng xử lý: Yêu cầu nhập, kiểm tra ép kiểu số thực và khoảng giá trị cho phép, lặp lại nếu không hợp lệ.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messageErrorOutOfRange thông báo khi giá trị ngoài khoảng
     * @param messageErrorInvalidNumber thông báo khi không phải số thực
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
     * Chức năng: Nhập và kiểm tra chuỗi ký tự theo biểu thức chính quy (Regex).
     * Luồng xử lý: Yêu cầu nhập chuỗi và kiểm tra bằng matches(REGEX), lặp lại nếu không khớp định dạng.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messageError thông báo khi không hợp lệ
     * @param REGEX biểu thức chính quy để kiểm tra
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
}
