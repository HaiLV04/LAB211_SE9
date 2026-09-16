package model;


import java.util.ArrayList;
import java.util.List;

/**
 * Chức năng: Lưu trữ dữ liệu giả lập của hệ thống ngân hàng (danh sách tài khoản).
 * Luồng tương tác: Được truy xuất bởi các lớp Service để kiểm tra thông tin đăng nhập.
 */
public class Data {

    /**
     * Chức năng: Khởi tạo danh sách tài khoản hợp lệ trong hệ thống.
     * Luồng xử lý 1: Thêm các đối tượng Account với số tài khoản và mật khẩu định sẵn.
     */
    public static List<Account> listAccount = new ArrayList<Account>() {
        {
            add(new Account("1029817261", "tuan26062002"));
            add(new Account("1039817261", "minh12345677"));
            add(new Account("1049817261", "chung3245677"));
            add(new Account("1059817261", "dhdi129YIUQWIE"));
        }
    };
}
