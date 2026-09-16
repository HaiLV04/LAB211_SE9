package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Chức năng: Cung cấp các phương thức tiện ích để kiểm tra tính hợp lệ của dữ liệu đầu vào.
 * Luồng tương tác:
 * 1. Nhận yêu cầu từ Main hoặc các lớp khác để lấy dữ liệu nhập vào từ bàn phím.
 * 2. Hiển thị thông báo yêu cầu nhập, thông báo lỗi nếu dữ liệu không hợp lệ.
 * 3. Trả về dữ liệu đã được kiểm chứng và định dạng đúng.
 */
public class Validator {
    private static final Scanner SCANNER = new Scanner(System.in);
    
    private Validator(){
        
    }
    
    /**
     * Chức năng: Hỗ trợ người dùng nhập một số nguyên trong khoảng cho trước một cách hợp lệ.
     * Luồng xử lý:
     * 1. Vòng lặp yêu cầu nhập liên tục cho đến khi dữ liệu hợp lệ.
     * 2. Hiển thị thông báo hướng dẫn.
     * 3. Đọc dữ liệu nhập vào và chuyển kiểu thành số nguyên.
     * 4. Kiểm tra số vừa nhập có nằm trong giới hạn [min, max] hay không.
     * 5. Nếu nằm trong giới hạn, trả về số nguyên đó. Ngược lại, in ra lỗi ngoài khoảng.
     * 6. Bắt lỗi định dạng số và in ra thông báo lỗi nếu dữ liệu nhập không phải là số.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messsageErrorOutOfRange thông báo lỗi khi số nhập vào nằm ngoài khoảng
     * @param messageErrorNumber thông báo lỗi khi nhập không phải định dạng số
     * @param min giới hạn nhỏ nhất
     * @param max giới hạn lớn nhất
     * @return số nguyên hợp lệ
     */
    public static int getInt(String messageInfo,String messsageErrorOutOfRange,
            String messageErrorNumber,int min,int max){
        do {            
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if(number>=min&&number<=max){
                    return number;
                }else{
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }
    
    /**
     * Chức năng: Hỗ trợ người dùng nhập một số thực (double) trong khoảng cho trước một cách hợp lệ.
     * Luồng xử lý:
     * 1. Vòng lặp yêu cầu nhập liên tục cho đến khi dữ liệu hợp lệ.
     * 2. Hiển thị thông báo hướng dẫn.
     * 3. Đọc dữ liệu nhập vào và chuyển kiểu thành số double.
     * 4. Kiểm tra số vừa nhập có nằm trong giới hạn [min, max] hay không.
     * 5. Nếu nằm trong giới hạn, trả về số thực đó. Ngược lại, in ra lỗi ngoài khoảng.
     * 6. Bắt lỗi định dạng số và in ra thông báo lỗi nếu dữ liệu nhập không phải là số.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messsageErrorOutOfRange thông báo lỗi khi số nhập vào nằm ngoài khoảng
     * @param messageErrorNumber thông báo lỗi khi nhập không phải định dạng số
     * @param min giới hạn nhỏ nhất
     * @param max giới hạn lớn nhất
     * @return số thực hợp lệ
     */
    public static double getDouble(String messageInfo,String messsageErrorOutOfRange,
            String messageErrorNumber,double min,double max){
        do {            
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if(number>=min&&number<=max){
                    return number;
                }else{
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }
    
    /**
     * Chức năng: Hỗ trợ người dùng nhập một chuỗi thỏa mãn định dạng (regex) cho trước.
     * Luồng xử lý:
     * 1. Vòng lặp yêu cầu nhập liên tục cho đến khi dữ liệu hợp lệ.
     * 2. Hiển thị thông báo hướng dẫn.
     * 3. Đọc dữ liệu chuỗi nhập vào từ bàn phím.
     * 4. Kiểm tra chuỗi vừa nhập có khớp với biểu thức chính quy (REGEX) hay không.
     * 5. Nếu khớp, trả về chuỗi đó. Ngược lại, in ra thông báo lỗi.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messageError thông báo lỗi khi chuỗi nhập vào sai định dạng
     * @param REGEX biểu thức chính quy để kiểm tra chuỗi
     * @return chuỗi hợp lệ
     */
    public static String getString(String messageInfo, String messageError, final String REGEX){
        do {            
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if(str.matches(REGEX)){
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }
    
    /**
     * Chức năng: Hỗ trợ người dùng nhập một ngày tháng hợp lệ trong khoảng thời gian cho trước.
     * Luồng xử lý:
     * 1. Khởi tạo đối tượng SimpleDateFormat với định dạng REGEX và vô hiệu hóa tính năng tự động suy luận ngày (setLenient(false)).
     * 2. Vòng lặp yêu cầu nhập liên tục cho đến khi dữ liệu hợp lệ.
     * 3. Hiển thị thông báo hướng dẫn và yêu cầu nhập ngày.
     * 4. Chuyển đổi chuỗi nhập vào thành đối tượng Date.
     * 5. Kiểm tra Date vừa chuyển đổi có nằm trong khoảng [min, max] hay không.
     * 6. Nếu hợp lệ, trả về đối tượng Date. Ngược lại, in ra lỗi khoảng thời gian.
     * 7. Bắt lỗi ParseException và in ra thông báo lỗi nếu dữ liệu nhập sai định dạng ngày.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messsageErrorOutOfRange thông báo lỗi khi ngày nhập vào ngoài khoảng giới hạn
     * @param messageErrorDate thông báo lỗi khi sai định dạng ngày
     * @param REGEX định dạng chuỗi của ngày tháng
     * @param min ngày giới hạn nhỏ nhất
     * @param max ngày giới hạn lớn nhất
     * @return đối tượng Date hợp lệ
     */
    public static Date getDate(String messageInfo,String messsageErrorOutOfRange,
            String messageErrorDate,final String REGEX,
            Date min,Date max){
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        do {            
            System.out.print(messageInfo);
            try {
                Date date = dateFormat.parse(SCANNER.nextLine());
                if(date.compareTo(min)>=0&&date.compareTo(max)<=0){
                    return date;
                }
                System.out.println(messsageErrorOutOfRange);
            } catch (ParseException e) {
                System.out.println(messageErrorDate);
            }
        } while (true);
    }
    
}
