package model;

/**
 * Chức năng: Lưu trữ các hằng số và biểu thức chính quy (Regex) dùng để kiểm tra tính hợp lệ dữ liệu.
 */
public class IConstant {

    public static final int CAPTCHA_LENGTH = 5;
    public static final String ACCOUNT_NUMBER = "^[0-9]{10}$";
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,31}$";
    public static final String TEXT = "^[A-Za-z0-9 ,\\.]+$";
    public static final String CAPTCHA = "^[A-Za-z0-9]{5}$";
}
