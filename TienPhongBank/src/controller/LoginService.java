package controller;

import model.Account;
import java.util.ResourceBundle;
import model.Data;
import view.Helper;
import model.IConstant;
import view.Validate;

/**
 * Chức năng: Xử lý logic đăng nhập vào hệ thống.
 * Luồng tương tác: Yêu cầu người dùng nhập thông tin (tài khoản, mật khẩu, captcha), kiểm tra tính hợp lệ và xác thực với dữ liệu từ lớp Data.
 */
public class LoginService {
    /**
     * Chức năng: Thực hiện quy trình đăng nhập.
     * Luồng xử lý 1: Nhập và kiểm tra định dạng của tài khoản.
     * Luồng xử lý 2: Nhập và kiểm tra định dạng của mật khẩu.
     * Luồng xử lý 3: Tạo và hiển thị Captcha ngẫu nhiên.
     * Luồng xử lý 4: Nhập và xác thực mã Captcha.
     * Luồng xử lý 5: Gọi hàm authentication để kiểm tra tài khoản và mật khẩu, sau đó in ra kết quả tương ứng.
     */
    public void login(ResourceBundle resourceBundle){
        String account = Validate.getString(
                resourceBundle.getString("account"),
                resourceBundle.getString("accountInvalid"),
                IConstant.ACCOUNT_NUMBER );
        
        String password = Validate.getString(
                resourceBundle.getString("password"),
                resourceBundle.getString("passwordInvalid"),
                IConstant.PASSWORD );
        
        String captchaGenerate = Helper.generateCaptcha(IConstant.CAPTCHA_LENGTH);
        System.out.println(resourceBundle.getString("captcha") + captchaGenerate);
        
        Validate.verifyCaptcha(
                resourceBundle.getString("inputCaptcha"), 
                resourceBundle.getString("captchaInvalid"),
                captchaGenerate
        );

        
        if(authentication(account, password)){
            System.out.println(resourceBundle.getString("loginSuccess"));
        } else {
            System.out.println(resourceBundle.getString("loginFailed"));
        }
        
    }
    
    /**
     * Chức năng: Xác thực thông tin đăng nhập của người dùng.
     * Luồng xử lý 1: Duyệt qua danh sách tài khoản hợp lệ trong Data.
     * Luồng xử lý 2: So sánh thông tin nhập vào với dữ liệu, nếu trùng khớp thì trả về true, ngược lại trả về false.
     */
    private boolean authentication(String account, String password){
        for(Account a : Data.listAccount){
            if(account.equals(a.getAccount()) && password.equals(a.getPassword())){
                return true;
            }
        }
        return false;
    }
}
