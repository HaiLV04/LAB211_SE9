package model;

/**
 * Chức năng: Lưu trữ các hằng số và biểu thức chính quy (Regex) dùng để kiểm tra tính hợp lệ của dữ liệu.
 * Luồng tương tác: Cung cấp hằng số cho lớp Validate và LoginService.
 */
public class IConstant {

    /** Chức năng: Quy định độ dài của chuỗi captcha. */
    public static final int CAPTCHA_LENGTH = 5;
    /** Chức năng: Biểu thức chính quy kiểm tra số tài khoản (phải là 10 chữ số). */
    public static final String ACCOUNT_NUMBER = "^[0-9]{10}$";
    /** Chức năng: Biểu thức chính quy kiểm tra mật khẩu (chứa cả chữ và số, độ dài 8-31 ký tự). */
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,31}$";
    /** Chức năng: Biểu thức chính quy kiểm tra văn bản thông thường. */
    public static final String TEXT = "^[A-Za-z0-9 ,\\.]+$";
    /** Chức năng: Biểu thức chính quy kiểm tra định dạng captcha nhập vào. */
    public static final String CAPTCHA = "^[A-Za-z0-9]{5}$";
}
