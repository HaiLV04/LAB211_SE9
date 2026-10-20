package controller;

import java.util.ResourceBundle;
import model.Account;
import model.Data;
import model.IConstant;
import view.Helper;
import view.Validate;

/**
 * Chức năng: Xử lý logic đăng nhập và xác thực tài khoản đa ngôn ngữ.
 */
public class LoginService {

    /**
     * Chức năng: Thực hiện quy trình đăng nhập hệ thống TPBank theo ngôn ngữ được chọn.
     * Luồng xử lý: Lần lượt nhập và kiểm tra tài khoản, mật khẩu, sinh và xác thực mã Captcha, sau đó thông báo kết quả đăng nhập.
     *
     * @param resourceBundle gói tài nguyên ngôn ngữ
     */
    public void login(ResourceBundle resourceBundle) {
        String account = Validate.getString(
                resourceBundle.getString("account"),
                resourceBundle.getString("accountInvalid"),
                IConstant.ACCOUNT_NUMBER);

        String password = Validate.getString(
                resourceBundle.getString("password"),
                resourceBundle.getString("passwordInvalid"),
                IConstant.PASSWORD);

        String captchaGenerate = Helper.generateCaptcha(IConstant.CAPTCHA_LENGTH);
        System.out.println(resourceBundle.getString("captcha") + captchaGenerate);

        Validate.verifyCaptcha(
                resourceBundle.getString("inputCaptcha"),
                resourceBundle.getString("captchaInvalid"),
                captchaGenerate
        );

        if (authentication(account, password)) {
            System.out.println(resourceBundle.getString("loginSuccess"));
        } else {
            System.out.println(resourceBundle.getString("loginFailed"));
        }
    }

    /**
     * Chức năng: Xác thực số tài khoản và mật khẩu với dữ liệu hệ thống.
     * Luồng xử lý: Duyệt danh sách Data.listAccount, trả về true nếu trùng cả số tài khoản và mật khẩu, ngược lại false.
     *
     * @param account số tài khoản cần xác thực
     * @param password mật khẩu cần xác thực
     * @return true nếu thông tin chính xác, ngược lại false
     */
    private boolean authentication(String account, String password) {
        for (Account a : Data.listAccount) {
            if (account.equals(a.getAccount()) && password.equals(a.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
