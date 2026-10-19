# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0070 (TIENPHONG BANK E-BANKING)

---

## 1. TỔNG QUAN BÀI TOÁN & QUY TẮC NGHIỆP VỤ
- **Mã bài lab:** J1.S.P0070 (Ebank system - TienPhong Bank).
- **Mục tiêu:**
  1. Xây dựng hệ thống đăng nhập điện tử hỗ trợ **Đa ngôn ngữ (Internationalization - i18n)**: Tiếng Việt và Tiếng Anh.
  2. Toàn bộ các câu thông báo, nhãn nhập liệu, thông báo lỗi được nạp động từ file tài nguyên cấu hình (`.properties`).
  3. **Quy tắc kiểm tra tài khoản (Account Number):** Chuỗi đúng 10 chữ số (`^\d{10}$`).
  4. **Quy tắc kiểm tra mật khẩu (Password):** Độ dài từ 8 đến 31 ký tự, bắt buộc phải chứa cả chữ cái và chữ số (`^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,31}$`).
  5. **Cơ chế Captcha bảo mật:** Tự động sinh mã Captcha ngẫu nhiên gồm 5 ký tự (chữ hoa, chữ thường, số). Người dùng phải nhập chính xác chuỗi Captcha này (hoặc chuỗi con nằm trong Captcha tùy cài đặt xác thực).
  6. **Xác thực người dùng:** Kiểm tra tài khoản và mật khẩu với danh sách tài khoản hợp lệ trong cơ sở dữ liệu giả lập (`model.Data`).
- **Kiến trúc:** Chuẩn **MVC (Model - View - Controller)** kết hợp cơ chế `ResourceBundle` và `Locale` của Java Core.

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC & i18n)

```
                            [Main]
                              │
                              ▼
                        [TPBank Controller]
                   (Chọn ngôn ngữ & nạp Locale)
                              │
                              ▼
                     [LoginService Controller]
             ┌────────────────┼────────────────┐
             ▼                ▼                ▼
     [view.Validate]   [ResourceBundle]  [model.Data & Account]
     + [view.Helper]    (.properties)       (Xác thực tài khoản)
```

- **Resources:**
  - `Language_vi.properties`: Bộ từ khóa thông điệp Tiếng Việt.
  - `Language_en.properties`: Bộ từ khóa thông điệp Tiếng Anh.
- **Model Package:**
  - `IConstant`: Interface lưu các hằng số cấu hình hệ thống (Regex số tài khoản, Regex mật khẩu, độ dài Captcha).
  - `Account`: Entity đại diện cho tài khoản khách hàng.
  - `Data`: Lớp dữ liệu mẫu (Mock database) chứa danh sách tài khoản đã đăng ký.
- **View Package:**
  - `Helper`: Hiển thị menu chọn ngôn ngữ và sinh chuỗi Captcha ngẫu nhiên.
  - `Validate`: Kiểm tra dữ liệu nhập theo Regex và xác minh mã Captcha.
- **Controller Package:**
  - `TPBank`: Thiết lập ngôn ngữ hệ thống (`Locale.setDefault()`) và nạp `ResourceBundle`.
  - `LoginService`: Xử lý nghiệp vụ đăng nhập tuần tự 5 bước.
- **Main (`main.Main`):** Điểm khởi chạy `TPBank.start()`.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & LOGIC BẢO MẬT

### 3.1. `resources/Language_xx.properties` & Cơ chế i18n
- Nạp file tài nguyên thông qua:
  ```java
  ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/Language");
  ```
  Java tự động tìm file `Language_vi.properties` hoặc `Language_en.properties` tương ứng với `Locale.getDefault()`.
- Mọi thông báo lỗi đều được lấy qua `resourceBundle.getString("key")`, giúp việc thay đổi ngôn ngữ không phải sửa code logic.

### 3.2. `model.IConstant` (Biểu thức chính quy Regex)
- **`ACCOUNT_NUMBER = "^\\d{10}$"`:**
  - `^`: Bắt đầu chuỗi.
  - `\\d{10}`: Đúng 10 chữ số liên tiếp từ 0 đến 9.
  - `$`: Kết thúc chuỗi.
- **`PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,31}$"`:**
  - `(?=.*[A-Za-z])`: Lookahead assertion - bắt buộc chuỗi phải chứa ít nhất một chữ cái.
  - `(?=.*\\d)`: Lookahead assertion - bắt buộc chuỗi phải chứa ít nhất một chữ số.
  - `[A-Za-z\\d]{8,31}`: Chỉ gồm các chữ cái và chữ số, độ dài trong khoảng từ 8 đến 31 ký tự.
- **`CAPTCHA_LENGTH = 5`:** Hằng số độ dài mã Captcha.

### 3.3. `view.Helper`
- **`generateCaptcha(int length)`:**
  - Dùng chuỗi nguồn `chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"`.
  - Dùng `Random` bốc ngẫu nhiên `length` ký tự và ghép bằng `StringBuilder` để sinh ra mã Captcha ngẫu nhiên cho mỗi phiên đăng nhập.

### 3.4. `view.Validate`
- **`verifyCaptcha(String msg, String err, String captchaGen)`:**
  - Vòng lặp yêu cầu người dùng nhập Captcha.
  - Kiểm tra điều kiện:
    ```java
    if (!input.isEmpty() && captchaGen.contains(input)) {
        return;
    }
    ```
    Nếu rỗng hoặc không khớp với chuỗi Captcha đã sinh thì in thông báo lỗi `err` và bắt nhập lại.

### 3.5. `controller.LoginService`
- **Quy trình đăng nhập 5 bước:**
  1. Nhập và kiểm tra định dạng Số tài khoản.
  2. Nhập và kiểm tra định dạng Mật khẩu.
  3. Sinh và hiển thị mã Captcha qua `Helper.generateCaptcha()`.
  4. Yêu cầu nhập và xác thực Captcha qua `Validate.verifyCaptcha()`.
  5. Gọi hàm `authentication(account, password)`: Duyệt `Data.listAccount`, nếu trùng cả tài khoản và mật khẩu thì thông báo `loginSuccess`, ngược lại thông báo `loginFailed`.

---

## 4. CÁC NGUYÊN LÝ THIẾT KẾ & BẢO MẬT ÁP DỤNG

1. **Internationalization (i18n):** Tách toàn bộ chuỗi giao diện ra khỏi mã nguồn Java bằng các tệp Resource Bundle. Đạt tính linh hoạt tối đa khi muốn mở rộng thêm ngôn ngữ mới (ví dụ tiếng Nhật, tiếng Hàn) chỉ cần thêm file `.properties` mà không phải biên dịch lại mã nguồn.
2. **Defensive Validation:** Dùng Regular Expression (Regex) với Lookahead để xác thực độ an toàn của mật khẩu, ngăn chặn hoàn toàn các chuỗi rác hoặc tấn công tiêm mã.
3. **Interface Constant Pattern:** Gom nhóm các hằng số quy chuẩn vào `IConstant`, loại bỏ hoàn toàn các "Magic Strings" và "Magic Numbers" trong toàn bộ project.

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Cơ chế Đa ngôn ngữ (i18n) trong Java hoạt động như thế nào trong bài này?
> **Trả lời:** Em sử dụng hai lớp cốt lõi của Java Core là `java.util.Locale` và `java.util.ResourceBundle`:
> 1. Khi người dùng chọn ngôn ngữ 1 (Tiếng Việt) hoặc 2 (Tiếng Anh), em gán `Locale.setDefault(new Locale("vi", "VN"))` hoặc `new Locale("en", "US")`.
> 2. Gọi `ResourceBundle.getBundle("resources/Language")`. Java sẽ dựa vào `Locale` hiện tại để tự động nạp đúng file `Language_vi.properties` hoặc `Language_en.properties`.
> 3. Tầng View chỉ việc gọi `resourceBundle.getString("key")` để lấy thông điệp tương ứng.

### Q2: Giải thích ý nghĩa của `(?=.*[A-Za-z])` và `(?=.*\\d)` trong Regex mật khẩu?
> **Trả lời:** Đây là kỹ thuật **Positive Lookahead (Khẳng định nhìn về phía trước)** trong Biểu thức chính quy:
> - `(?=.*[A-Za-z])`: Kiểm tra từ vị trí bắt đầu, trong chuỗi phải tồn tại ít nhất một ký tự chữ cái (không phân biệt hoa thường).
> - `(?=.*\\d)`: Kiểm tra từ vị trí bắt đầu, trong chuỗi phải tồn tại ít nhất một chữ số.
> Nhờ 2 biểu thức lookahead này kết hợp với `[A-Za-z\\d]{8,31}`, mật khẩu bắt buộc phải thỏa mãn đồng thời cả 3 điều kiện: có chữ, có số, và dài từ 8 đến 31 ký tự mà không cần duyệt chuỗi thủ công nhiều lần.

### Q3: Mã Captcha được tạo ra để làm gì? Chương trình của em tạo Captcha như thế nào?
> **Trả lời:** Captcha dùng để ngăn chặn các cuộc tấn công tự động (Bot / Brute-force attacks) cố tình dò mật khẩu. Trong hàm `Helper.generateCaptcha(int length)`:
> - Em chuẩn bị một tập hợp 62 ký tự gồm chữ số `0-9`, chữ hoa `A-Z`, và chữ thường `a-z`.
> - Dùng vòng lặp và đối tượng `Random` để bốc ngẫu nhiên 5 ký tự ghép lại thành chuỗi Captcha hiển thị cho người dùng mỗi lần đăng nhập.

### Q4: Nếu sau này ngân hàng muốn thêm hỗ trợ Tiếng Nhật (Japanese) thì em sẽ thay đổi mã nguồn như thế nào?
> **Trả lời:** Nhờ thiết kế theo chuẩn i18n, việc mở rộng ngôn ngữ mới cực kỳ đơn giản:
> 1. Em chỉ cần tạo thêm một file `Language_ja.properties` trong thư mục `resources` chứa các bản dịch tiếng Nhật tương ứng với các key hiện tại.
> 2. Ở menu `TPBank.start()`, em thêm lựa chọn 3: `Locale.setDefault(new Locale("ja", "JP"))`.
> Toàn bộ logic kiểm tra tài khoản, mật khẩu, captcha và đăng nhập trong `LoginService` không cần phải sửa đổi bất kỳ một dòng code nào.
