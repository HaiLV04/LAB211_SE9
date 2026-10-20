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
     * @param messsageErrorOutOfRange thông báo khi giá trị ngoài khoảng
     * @param messageErrorNumber thông báo khi không phải số nguyên
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
}
