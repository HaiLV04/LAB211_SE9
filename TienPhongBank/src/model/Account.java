package model;

/**
 * Chức năng: Biểu diễn thực thể Tài khoản người dùng trong hệ thống.
 * Luồng tương tác: Được sử dụng để tạo đối tượng lưu trữ thông tin đăng nhập và so sánh dữ liệu.
 */
public class Account {
    private String account;
    private String password;

    /**
     * Chức năng (Làm gì): Khởi tạo đối tượng Tài khoản với số tài khoản và mật khẩu.
     * Luồng xử lý (Làm như thế nào):
     * 1. Gán tham số account cho thuộc tính account.
     * 2. Gán tham số password cho thuộc tính password.
     *
     * @param account số tài khoản
     * @param password mật khẩu
     */
    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    /**
     * Chức năng (Làm gì): Lấy số tài khoản.
     * Luồng xử lý (Làm như thế nào): Trả về giá trị của thuộc tính account.
     *
     * @return số tài khoản
     */
    public String getAccount() {
        return account;
    }

    /**
     * Chức năng (Làm gì): Lấy mật khẩu của tài khoản.
     * Luồng xử lý (Làm như thế nào): Trả về giá trị của thuộc tính password.
     *
     * @return mật khẩu
     */
    public String getPassword() {
        return password;
    }
}
