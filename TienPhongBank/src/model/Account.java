package model;

/**
 * Chức năng: Lưu trữ thông tin tài khoản người dùng (số tài khoản, mật khẩu).
 */
public class Account {
    private String account;
    private String password;

    /**
     * Khởi tạo đối tượng Tài khoản với số tài khoản và mật khẩu.
     *
     * @param account số tài khoản
     * @param password mật khẩu
     */
    public Account(String account, String password) {
        this.account = account;
        this.password = password;
    }

    /**
     * Lấy số tài khoản.
     *
     * @return số tài khoản
     */
    public String getAccount() {
        return account;
    }

    /**
     * Lấy mật khẩu của tài khoản.
     *
     * @return mật khẩu
     */
    public String getPassword() {
        return password;
    }
}
