package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Chức năng: Lớp tiện ích cung cấp các phương thức để kiểm tra tính hợp lệ của dữ liệu đầu vào.
 * Luồng tương tác: Cung cấp phương thức static được gọi từ các lớp khác (như MatrixInputer, Main) để lấy đầu vào hợp lệ từ console.
 */
public class Validator {
    private static final Scanner SCANNER = new Scanner(System.in);
    
    /**
     * Chức năng: Hàm khởi tạo private ngăn chặn việc tạo đối tượng của lớp tiện ích.
     * Luồng xử lý: 
     * 1. Khởi tạo đối tượng rỗng.
     */
    private Validator(){
        
    }
    
    /**
     * Chức năng: Lấy một số nguyên hợp lệ từ người dùng trong khoảng cho trước.
     * Luồng xử lý:
     * 1. Hiển thị thông báo nhập liệu.
     * 2. Đọc giá trị người dùng nhập vào.
     * 3. Chuyển đổi chuỗi sang số nguyên. Nếu sai định dạng, in ra thông báo lỗi và quay lại bước 1.
     * 4. Kiểm tra số nguyên có nằm trong khoảng [min, max] hay không.
     * 5. Nếu hợp lệ, trả về số nguyên đó. Nếu không, in ra thông báo lỗi ngoài khoảng và quay lại bước 1.
     * @param messageInfo Thông báo yêu cầu nhập liệu
     * @param messsageErrorOutOfRange Thông báo lỗi khi giá trị ngoài khoảng cho phép
     * @param messageErrorNumber Thông báo lỗi khi nhập không phải là số nguyên
     * @param min Giá trị tối thiểu cho phép
     * @param max Giá trị tối đa cho phép
     * @return Số nguyên hợp lệ được nhập từ người dùng
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
    
}

