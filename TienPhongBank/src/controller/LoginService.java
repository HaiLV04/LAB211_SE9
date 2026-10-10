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
     * Chức năng (Làm gì): Thực hiện quy trình đăng nhập hệ thống TPBank với đa ngôn ngữ.
     * Luồng xử lý (Làm như thế nào):
     * 1. Nhập và kiểm tra định dạng của tài khoản qua Validate.getString theo biểu thức chính quy ACCOUNT_NUMBER.
     * 2. Nhập và kiểm tra định dạng của mật khẩu qua Validate.getString theo biểu thức chính quy PASSWORD.
     * 3. Tạo mã Captcha ngẫu nhiên qua Helper.generateCaptcha và hiển thị ra màn hình.
     * 4. Nhập và xác thực mã Captcha qua Validate.verifyCaptcha.
     * 5. Gọi hàm authentication để kiểm tra tài khoản và mật khẩu, sau đó in thông báo đăng nhập thành công hoặc thất bại theo ngôn ngữ hiện tại.
     *
     * @param resourceBundle gói tài nguyên đa ngôn ngữ ResourceBundle
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
     * Chức năng (Làm gì): Xác thực thông tin tài khoản và mật khẩu người dùng với dữ liệu hệ thống.
     * Luồng xử lý (Làm như thế nào):
     * 1. Duyệt qua danh sách tài khoản hợp lệ trong Data.listAccount.
     * 2. So sánh thông tin tài khoản và mật khẩu nhập vào với từng tài khoản trong danh sách.
     * 3. Trả về true nếu trùng khớp, ngược lại trả về false khi duyệt hết danh sách mà không khớp.
     *
     * @param account số tài khoản cần xác thực
     * @param password mật khẩu cần xác thực
     * @return true nếu xác thực thành công, ngược lại false
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
