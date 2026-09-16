package view;

import java.util.Scanner;

/**
 * Chức năng: Cung cấp các tiện ích kiểm tra tính hợp lệ của dữ liệu đầu vào.
 * Luồng tương tác:
 * - Người dùng gọi các phương thức trong lớp này để nhận đầu vào từ bàn phím an toàn.
 */
public class Validator {
    private static final Scanner SCANNER = new Scanner(System.in);
    
    private Validator(){
    }
    
    /**
     * Chức năng: Yêu cầu người dùng nhập một số nguyên hợp lệ trong khoảng chỉ định.
     * Luồng xử lý:
     * 1. Hiển thị thông báo nhắc người dùng nhập dữ liệu.
     * 2. Nhận chuỗi nhập vào và ép kiểu thành số nguyên.
     * 3. Nếu số lượng nhập vào nằm ngoài khoảng min-max, in ra thông báo lỗi tương ứng.
     * 4. Nếu xảy ra lỗi định dạng số, in ra thông báo lỗi định dạng và yêu cầu nhập lại.
     * 5. Vòng lặp được thực hiện cho đến khi người dùng nhập số nguyên hợp lệ.
     *
     * @param messageInfo thông báo yêu cầu nhập
     * @param messageErrorOutOfRange thông báo lỗi nếu số vượt ngoài phạm vi min - max
     * @param messageErrorNumber thông báo lỗi nếu nhập sai định dạng (không phải số nguyên)
     * @param min giá trị nhỏ nhất được phép nhập
     * @param max giá trị lớn nhất được phép nhập
     * @return số nguyên hợp lệ đã được nhập
     */
    public static int getInt(String messageInfo, String messageErrorOutOfRange,
            String messageErrorNumber, int min, int max){
        do {            
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if(number >= min && number <= max){
                    return number;
                } else {
                    System.out.println(messageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }
    
//    public static double getDouble(String messageInfo,String messsageErrorOutOfRange,
//            String messageErrorNumber,double min,double max){
//        do {            
//            try {
//                System.out.print(messageInfo);
//                double number = Double.parseDouble(SCANNER.nextLine());
//                if(number>=min&&number<=max){
//                    return number;
//                }else{
//                    System.out.println(messsageErrorOutOfRange);
//                }
//            } catch (NumberFormatException e) {
//                System.out.println(messageErrorNumber);
//            }
//        } while (true);
//    }
//    
//    public static String getString(String messageInfo, String messageError, final String REGEX){
//        do {            
//            System.out.print(messageInfo);
//            String str = SCANNER.nextLine();
//            if(str.matches(REGEX)){
//                return str;
//            }
//            System.out.println(messageError);
//        } while (true);
//    }
//    
//    public static Date getDate(String messageInfo,String messsageErrorOutOfRange,
//            String messageErrorDate,final String REGEX,
//            Date min,Date max){
//        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
//        dateFormat.setLenient(false);
//        do {            
//            System.out.print(messageInfo);
//            try {
//                Date date = dateFormat.parse(SCANNER.nextLine());
//                if(date.compareTo(min)>=0&&date.compareTo(max)<=0){
//                    return date;
//                }
//                System.out.println(messsageErrorOutOfRange);
//            } catch (ParseException e) {
//                System.out.println(messageErrorDate);
//            }
//        } while (true);
//    }
    
}
