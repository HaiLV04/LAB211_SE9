package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Chức năng: Lớp Validator (Tiện ích kiểm tra dữ liệu đầu vào).
 * Luồng tương tác: Cung cấp các phương thức static dùng để nhập và kiểm tra tính hợp lệ của dữ liệu từ bàn phím. Sử dụng vòng lặp để ép người dùng nhập lại cho đến khi đúng định dạng.
 */
public class Validator {
    private static final Scanner SCANNER = new Scanner(System.in);
    
    /**
     * Chức năng (Làm gì): Hàm khởi tạo private ngăn chặn việc tạo đối tượng của lớp tiện ích.
     * Luồng xử lý (Làm như thế nào): Không thực hiện logic nào nhằm đảm bảo không thể khởi tạo instance của Utility class.
     */
    private Validator(){
    }

    /**
     * Chức năng (Làm gì): Yêu cầu người dùng nhập vào một số nguyên (int) hợp lệ nằm trong khoảng cho trước.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập (messageInfo).
     * 2. Đọc chuỗi đầu vào và cố gắng parse sang kiểu int bằng Integer.parseInt.
     * 3. Kiểm tra xem số nguyên có nằm trong khoảng [min, max] hay không.
     * 4. Bắt lỗi NumberFormatException nếu nhập sai định dạng và in thông báo lỗi.
     * 5. Lặp lại quá trình nếu dữ liệu không hợp lệ.
     * 
     * @param messageInfo Lời nhắc hiển thị yêu cầu nhập
     * @param messsageErrorOutOfRange Lỗi hiển thị khi số nhập vào nằm ngoài [min, max]
     * @param messageErrorNumber Lỗi hiển thị khi nhập chữ hoặc ký tự không phải số nguyên
     * @param min Giá trị tối thiểu cho phép
     * @param max Giá trị tối đa cho phép
     * @return Số nguyên hợp lệ do người dùng nhập
     */
    public static int getInt(String messageInfo,String messsageErrorOutOfRange,
            String messageErrorNumber,int min,int max){
        do {            
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
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
     * Chức năng (Làm gì): Yêu cầu người dùng nhập vào một số thực (double) hợp lệ nằm trong khoảng cho trước.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập (messageInfo).
     * 2. Đọc chuỗi đầu vào và cố gắng parse sang kiểu double bằng Double.parseDouble.
     * 3. Kiểm tra xem số thực có nằm trong khoảng [min, max] hay không.
     * 4. Bắt lỗi NumberFormatException nếu nhập sai định dạng và in thông báo lỗi.
     * 5. Lặp lại quá trình nếu dữ liệu không hợp lệ.
     * 
     * @param messageInfo Lời nhắc hiển thị yêu cầu nhập
     * @param messsageErrorOutOfRange Lỗi khi giá trị ngoài khoảng [min, max]
     * @param messageErrorNumber Lỗi khi nhập sai định dạng số
     * @param min Giá trị tối thiểu
     * @param max Giá trị tối đa
     * @return Số thực hợp lệ
     */
    public static double getDouble(String messageInfo,String messsageErrorOutOfRange,
            String messageErrorNumber,double min,double max){
        do {            
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
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
     * Chức năng (Làm gì): Yêu cầu người dùng nhập vào một chuỗi (String) thỏa mãn biểu thức chính quy (Regex).
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị thông báo yêu cầu nhập (messageInfo).
     * 2. Đọc chuỗi đầu vào.
     * 3. So khớp chuỗi với biểu thức chính quy (REGEX) bằng String.matches.
     * 4. Trả về chuỗi nếu hợp lệ, nếu không in thông báo lỗi và yêu cầu nhập lại.
     * 
     * @param messageInfo Lời nhắc hiển thị yêu cầu nhập
     * @param messageError Lỗi hiển thị khi chuỗi không khớp với Regex
     * @param REGEX Biểu thức chính quy dùng để kiểm tra
     * @return Chuỗi hợp lệ
     */
    public static String getString(String messageInfo, String messageError, final String REGEX){
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
     * Chức năng (Làm gì): Yêu cầu người dùng nhập vào một ngày tháng (Date) theo đúng định dạng.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo SimpleDateFormat với định dạng REGEX và tắt tính năng lenient (setLenient(false)) để kiểm tra ngày nghiêm ngặt.
     * 2. Hiển thị thông báo yêu cầu nhập (messageInfo).
     * 3. Đọc chuỗi và parse thành đối tượng Date.
     * 4. Bắt lỗi ParseException nếu ngày không hợp lệ và in thông báo lỗi.
     * 5. Lặp lại quá trình nếu có lỗi.
     * 
     * @param messageInfo Lời nhắc hiển thị yêu cầu nhập
     * @param messageErrorDate Lỗi khi nhập sai định dạng ngày hoặc ngày không tồn tại
     * @param REGEX Định dạng chuỗi ngày (VD: "dd-MM-yyyy")
     * @return Đối tượng Date hợp lệ
     */
    public static Date getDate(String messageInfo,String messageErrorDate,final String REGEX){
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        do {            
            System.out.print(messageInfo);
            try {
                Date date = dateFormat.parse(SCANNER.nextLine());
                return date;
            } catch (ParseException e) {
                System.out.println(messageErrorDate);
            }
        } while (true);
    }
}
