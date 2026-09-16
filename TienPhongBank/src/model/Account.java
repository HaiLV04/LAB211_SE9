package model;

/**
 * Chức năng: Biểu diễn thực thể Tài khoản người dùng trong hệ thống.
 * Luồng tương tác: Được sử dụng để tạo đối tượng lưu trữ thông tin đăng nhập và so sánh dữ liệu.
 */
public class Account {
    private String account;
    private String password;

    /**
     * Chức năng: Khởi tạo đối tượng Tài khoản với số tài khoản và mật khẩu.
     * Luồng xử lý 1: Gán tham số account cho thuộc tính account.
     * Luồng xử lý 2: Gán tham số password cho thuộc tính password.
     */
    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    /**
     * Chức năng: Lấy số tài khoản.
     * Luồng xử lý 1: Trả về giá trị của thuộc tính account.
     */
    public String getAccount() {
        return account;
    }

    /**
     * Chức năng: Lấy mật khẩu.
     * Luồng xử lý 1: Trả về giá trị của thuộc tính password.
     */
    public String getPassword() {
        return password;
    }
}
