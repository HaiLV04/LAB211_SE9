package controller;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import model.Account;
import model.Data;
import model.IConstant;
import view.Helper;

/**
 * Chức năng: Xử lý logic đăng nhập và xác thực tài khoản đa ngôn ngữ (Ebank).
 */
public class LoginService {

    private static final Scanner SCANNER = new Scanner(System.in);
    private ResourceBundle resourceBundle;

    /**
     * Khởi tạo đối tượng LoginService.
     */
    public LoginService() {
    }

    /**
     * Thêm mới
     * Chức năng: Chuyển đổi ngôn ngữ hệ thống và nạp tệp tài nguyên tương ứng (Function 1).
     * Luồng xử lý: Thiết lập Locale mặc định và nạp ResourceBundle theo ngôn ngữ chỉ định.
     *
     * @param locale đối tượng Locale ngôn ngữ cần thiết lập
     */
    public void setLocate(Locale locale) {
        Locale.setDefault(locale);
        this.resourceBundle = ResourceBundle.getBundle("resources/Language", locale);
    }

    /**
     * Thêm mới
     * Chức năng: Kiểm tra tính hợp lệ của số tài khoản (Function 2).
     * Luồng xử lý: So khớp accountNumber với REGEX 10 chữ số; nếu không khớp trả về thông báo lỗi accountInvalid, nếu hợp lệ trả về chuỗi rỗng.
     *
     * @param accountNumber số tài khoản cần kiểm tra
     * @return chuỗi thông báo lỗi hoặc rỗng nếu hợp lệ
     */
    public String checkAccountNumber(String accountNumber) {
        if (accountNumber != null && accountNumber.matches(IConstant.ACCOUNT_NUMBER)) {
            return "";
        }
        return resourceBundle.getString("accountInvalid");
    }

    /**
     * Thêm mới
     * Chức năng: Kiểm tra tính hợp lệ và độ an toàn của mật khẩu (Function 3).
     * Luồng xử lý: So khớp password với REGEX (8-31 ký tự, gồm cả chữ và số); nếu vi phạm trả về thông báo lỗi passwordInvalid, nếu hợp lệ trả về chuỗi rỗng.
     *
     * @param password mật khẩu cần kiểm tra
     * @return chuỗi thông báo lỗi hoặc rỗng nếu hợp lệ
     */
    public String checkPassword(String password) {
        if (password != null && password.matches(IConstant.PASSWORD)) {
            return "";
        }
        return resourceBundle.getString("passwordInvalid");
    }

    /**
     * Thêm mới
     * Chức năng: Sinh mã Captcha ngẫu nhiên gồm 5 ký tự (Function 4).
     * Luồng xử lý: Gọi Helper.generateCaptcha() với hằng số độ dài CAPTCHA_LENGTH.
     *
     * @return chuỗi mã Captcha ngẫu nhiên
     */
    public String generateCaptcha() {
        return Helper.generateCaptcha(IConstant.CAPTCHA_LENGTH);
    }

    /**
     * Thêm mới
     * Chức năng: Kiểm tra tính hợp lệ của chuỗi Captcha người dùng nhập (Function 5).
     * Luồng xử lý: Sử dụng hàm contains() kiểm tra ký tự hoặc chuỗi con nhập vào có nằm trong mã Captcha đã sinh không.
     *
     * @param captchaInput chuỗi ký tự Captcha người dùng nhập
     * @param captchaGenerate chuỗi mã Captcha ngẫu nhiên đã sinh
     * @return chuỗi thông báo lỗi captchaInvalid hoặc chuỗi rỗng nếu đúng
     */
    public String checkCaptcha(String captchaInput, String captchaGenerate) {
        if (captchaInput != null && !captchaInput.isEmpty() && captchaGenerate != null && captchaGenerate.contains(captchaInput)) {
            return "";
        }
        return resourceBundle.getString("captchaInvalid");
    }

    /**
     * Chỉnh sửa
     * Chức năng: Thực hiện quy trình đăng nhập hệ thống TPBank theo ngôn ngữ được chọn (Function 6).
     * Luồng xử lý: Lần lượt gọi checkAccountNumber, checkPassword, generateCaptcha, checkCaptcha, sau đó thông báo kết quả xác thực.
     */
    public void login() {
        String account;
        while (true) {
            System.out.print(resourceBundle.getString("account"));
            account = SCANNER.nextLine().trim();
            String checkMsg = checkAccountNumber(account);
            if (checkMsg.isEmpty()) {
                break;
            }
            System.out.println(checkMsg);
        }

        String password;
        while (true) {
            System.out.print(resourceBundle.getString("password"));
            password = SCANNER.nextLine().trim();
            String checkMsg = checkPassword(password);
            if (checkMsg.isEmpty()) {
                break;
            }
            System.out.println(checkMsg);
        }

        String captchaGenerate = generateCaptcha();
        System.out.println(resourceBundle.getString("captcha") + captchaGenerate);

        while (true) {
            System.out.print(resourceBundle.getString("inputCaptcha"));
            String captchaInput = SCANNER.nextLine().trim();
            String checkMsg = checkCaptcha(captchaInput, captchaGenerate);
            if (checkMsg.isEmpty()) {
                break;
            }
            System.out.println(checkMsg);
        }

        if (authentication(account, password)) {
            System.out.println(resourceBundle.getString("loginSuccess"));
        } else {
            System.out.println(resourceBundle.getString("loginFailed"));
        }
    }

    /**
     * Chức năng: Thực hiện đăng nhập với ResourceBundle được truyền vào trực tiếp.
     * Luồng xử lý: Gán resourceBundle và gọi hàm login().
     *
     * @param resourceBundle gói tài nguyên ngôn ngữ
     */
    public void login(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        login();
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
