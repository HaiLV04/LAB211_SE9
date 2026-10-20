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
     * @param messageInfo lời nhắc nhập
     * @param messsageErrorOutOfRange lỗi khi ngoài khoảng [min, max]
     * @param messageErrorNumber lỗi khi không phải số nguyên
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
     * Chức năng: Nhập và kiểm tra một số thực trong khoảng [min, max].
     * Luồng xử lý: Yêu cầu nhập, kiểm tra ép kiểu số thực và khoảng giá trị cho phép, lặp lại nếu không hợp lệ.
     * 
     * @param messageInfo lời nhắc nhập
     * @param messsageErrorOutOfRange lỗi khi ngoài khoảng [min, max]
     * @param messageErrorNumber lỗi khi không phải số thực
     * @param min giá trị nhỏ nhất
     * @param max giá trị lớn nhất
     * @return số thực hợp lệ
     */
    public static double getDouble(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, double min, double max) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine().trim());
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
     * @param messageInfo lời nhắc nhập
     * @param messageError lỗi khi không khớp Regex
     * @param REGEX biểu thức chính quy để kiểm tra
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
     * Chức năng: Nhập và kiểm tra ngày tháng theo định dạng.
     * Luồng xử lý: Sử dụng SimpleDateFormat với setLenient(false) để parse ngày, lặp lại nếu sai định dạng.
     * 
     * @param messageInfo lời nhắc nhập
     * @param messageErrorDate lỗi khi sai định dạng ngày
     * @param REGEX định dạng chuỗi ngày (ví dụ "dd-MM-yyyy")
     * @return đối tượng Date hợp lệ
     */
    public static Date getDate(String messageInfo, String messageErrorDate, final String REGEX) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        do {
            System.out.print(messageInfo);
            try {
                Date date = dateFormat.parse(SCANNER.nextLine().trim());
                return date;
            } catch (ParseException e) {
                System.out.println(messageErrorDate);
            }
        } while (true);
    }
}
