# I. Yêu Cầu Chương Trình (Requirement)

Xây dựng hệ thống đăng nhập điện tử (Ebank System - TienPhong Bank) hỗ trợ đa ngôn ngữ (Internationalization - i18n) gồm Tiếng Việt và Tiếng Anh. Toàn bộ các câu nhãn nhập liệu và thông báo lỗi được nạp động từ file tài nguyên cấu hình (.properties).

### 1. Quy định kiểu dữ liệu và các kiểu dữ liệu bắt buộc:
- Số tài khoản (Account Number):
  + Kiểu dữ liệu: String.
  + Ràng buộc: Bắt buộc không để trống, đúng 10 chữ số, định dạng regex `^[0-9]{10}$`.
  + Lỗi: Chứa chữ cái, ký tự đặc biệt, ít hơn hoặc nhiều hơn 10 số -> Hiển thị thông báo lỗi tương ứng theo ngôn ngữ và bắt nhập lại.
- Mật khẩu (Password):
  + Kiểu dữ liệu: String.
  + Ràng buộc: Bắt buộc không để trống, độ dài từ 8 đến 31 ký tự, bắt buộc phải là chuỗi alphanumeric chứa cả chữ cái và chữ số, định dạng regex `^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,31}$`.
  + Lỗi: Chỉ toàn số, chỉ toàn chữ, độ dài < 8 hoặc > 31 ký tự, chứa ký tự đặc biệt -> Hiển thị lỗi tương ứng và bắt nhập lại.
- Mã Captcha:
  + Kiểu dữ liệu: String.
  + Độ dài sinh mã: 5 ký tự ngẫu nhiên gồm chữ hoa, chữ thường và chữ số (A-Z, a-z, 0-9).
  + Ràng buộc xác thực: Chuỗi người dùng nhập bắt buộc không được để trống và phải tồn tại bên trong chuỗi Captcha đã sinh (sử dụng hàm `contains()`).
- Lựa chọn menu (Menu Choice):
  + Kiểu dữ liệu: int.
  + Ràng buộc: Số nguyên từ 1 đến 3 (1: Tiếng Việt, 2: Tiếng Anh, 3: Thoát).
- Gói tài nguyên ngôn ngữ:
  + Đối tượng Locale: `Locale("vi", "VN")` hoặc `Locale("en", "US")`.
  + Đối tượng ResourceBundle: `ResourceBundle.getBundle("resources/Language")`.

---

# II. Kiến Trúc Đa Ngôn Ngữ (i18n) & Phân Tích Đầy Đủ Các Function

Chương trình được thiết kế theo chuẩn MVC kết hợp cơ chế nạp động `ResourceBundle` của Java Core:
- resources/Language_vi.properties: Chứa các nhãn và thông báo bằng Tiếng Việt.
- resources/Language_en.properties: Chứa các nhãn và thông báo bằng Tiếng Anh.

### Chi tiết các Function trong từng class:

#### 1. Function 1: Chuyển đổi ngôn ngữ và hiển thị Menu (Class `controller.TPBank` & `view.Helper`)
- Hàm `Helper.menu()`:
  + Mục đích: Hiển thị giao diện menu 3 lựa chọn: 1. Vietnamese, 2. English, 3. Exit.
- Hàm `TPBank.start()`:
  + Mục đích: Khởi động chương trình, nhận lựa chọn từ người dùng và thiết lập ngôn ngữ.
  + Luồng xử lý:
    * Bước 1: Gọi `Helper.menu()` và nhận lựa chọn từ 1 đến 3 qua `Validate.getInt()`.
    * Bước 2: Dựa vào lựa chọn, đặt Locale mặc định qua `Locale.setDefault()`:
      - Nhập 1: Đặt `new Locale("vi", "VN")` (Tiếng Việt).
      - Nhập 2: Đặt `new Locale("en", "US")` (Tiếng Anh).
      - Nhập 3: Gọi `System.exit(0)` để thoát chương trình.
    * Bước 3: Nạp gói tài nguyên tương ứng: `ResourceBundle.getBundle("resources/Language")`.
    * Bước 4: Chuyển sang thực hiện đăng nhập: `new LoginService().login(resourceBundle)`.

#### 2. Function 2: Nhập và kiểm tra Số tài khoản (Class `view.Validate`)
- Hàm `Validate.getString(String messageInfo, String messageError, final String REGEX)`:
  + Mục đích: Nhập chuỗi từ bàn phím và kiểm tra tính hợp lệ bằng biểu thức chính quy (Regex).
  + Luồng xử lý: Vòng lặp `do-while` yêu cầu người dùng nhập. Sử dụng `str.matches(REGEX)` với regex `^[0-9]{10}$`. Nếu khớp thì trả về chuỗi tài khoản hợp lệ; nếu sai in thông báo lỗi lấy từ `resourceBundle.getString("accountInvalid")` và bắt nhập lại.

#### 3. Function 3: Nhập và kiểm tra Mật khẩu (Class `view.Validate`)
- Hàm `Validate.getString(String messageInfo, String messageError, final String REGEX)`:
  + Mục đích: Tái sử dụng hàm `getString` để kiểm tra mật khẩu.
  + Luồng xử lý: So khớp với regex `^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,31}$`. Nếu sai định dạng (thiếu chữ, thiếu số hoặc sai độ dài), in thông báo lỗi từ `resourceBundle.getString("passwordInvalid")` và yêu cầu nhập lại.

#### 4. Function 4: Sinh mã Captcha ngẫu nhiên (Class `view.Helper`)
- Hàm `Helper.genAlphaNumeric()`:
  + Mục đích: Tạo tập ký tự nguồn gồm toàn bộ chữ hoa (A-Z), chữ thường (a-z) và chữ số (0-9).
- Hàm `Helper.generateCaptcha(int length)`:
  + Mục đích: Sinh chuỗi Captcha ngẫu nhiên có độ dài `length` (5 ký tự).
  + Luồng xử lý: Sử dụng đối tượng `Random` để bốc ngẫu nhiên từng ký tự từ tập ký tự nguồn, nối bằng `StringBuilder` và trả về chuỗi Captcha hoàn chỉnh (ví dụ: `H9MOA`).

#### 5. Function 5: Kiểm tra mã Captcha sử dụng hàm `contains()` (Class `view.Validate`)
- Hàm `Validate.verifyCaptcha(String message, String messageError, String captchaGenerate)`:
  + Mục đích: Xác thực chuỗi Captcha do người dùng nhập vào.
  + Luồng xử lý: Vòng lặp `while(true)` yêu cầu người dùng nhập chuỗi. Kiểm tra điều kiện bằng hàm `contains()`:
    `if (!captchaInput.isEmpty() && captchaGenerate.contains(captchaInput))`
    Nếu chuỗi nhập vào không rỗng VÀ nằm trong chuỗi Captcha đã sinh thì thoát vòng lặp (hợp lệ); ngược lại in thông báo lỗi `resourceBundle.getString("captchaInvalid")` và bắt nhập lại.

#### 6. Function 6: Điều phối quy trình đăng nhập & Xác thực tài khoản (Class `controller.LoginService`)
- Hàm `LoginService.login(ResourceBundle resourceBundle)`:
  + Mục đích: Thực thi tuần tự toàn bộ 5 bước của tiến trình đăng nhập:
    * Bước 1: Yêu cầu nhập và kiểm tra Số tài khoản (Function 2).
    * Bước 2: Yêu cầu nhập và kiểm tra Mật khẩu (Function 3).
    * Bước 3: Sinh và hiển thị mã Captcha ngẫu nhiên (Function 4).
    * Bước 4: Yêu cầu nhập và xác thực Captcha bằng `contains()` (Function 5).
    * Bước 5: Gọi hàm `authentication(account, password)` đối chiếu với database giả lập (`Data.listAccount`).
- Hàm `LoginService.authentication(String account, String password)`:
  + Mục đích: Xác thực tài khoản và mật khẩu người dùng nhập vào với danh sách `Data.listAccount`.
  + Luồng xử lý: Duyệt danh sách, nếu tìm thấy tài khoản có cả `account` và `password` trùng khớp thì trả về `true` (in thông báo `loginSuccess`), ngược lại trả về `false` (in thông báo `loginFailed`).

---

# III. Phân Tích Chuyên Sâu: Sử Dụng Hàm `contains()` Trong Việc Verify Captcha

### 1. Tại sao đề bài yêu cầu dùng hàm `contains()` thay vì `equals()`?
- Theo đặc tả đề bài của môn LAB211 (J1.S.P0070):
  *"Use the function contains() to check the captcha characters input exists in the captcha string was born at first did not."*
- Ý đồ của bài toán: Không ép buộc người dùng phải gõ lại toàn bộ cả 5 ký tự của Captcha một cách máy móc, mà cho phép người dùng nhập **một ký tự hoặc một chuỗi ký tự con** nằm trong chuỗi Captcha đã được sinh ra.

### 2. Cú pháp và cách hoạt động trong code:
```java
public static boolean verifyCaptcha(String message, String messageError, String captchaGenerate) {
    String captchaInput;
    while (true) {
        System.out.printf(message);
        captchaInput = SCANNER.nextLine().trim();
        // Kiểm tra: Chuỗi nhập không được để trống VÀ phải tồn tại trong Captcha gốc
        if (!captchaInput.isEmpty() && captchaGenerate.contains(captchaInput)) {
            break;
        }
        System.out.println(messageError);
    }
    return true;
}
```

### 3. Phân tích các trường hợp cụ thể:
Giả sử hệ thống vừa sinh ra mã Captcha ngẫu nhiên là: **`H9MOA`**

- Trường hợp HỢP LỆ (Thành công):
  + Người dùng nhập 1 ký tự có trong Captcha: Nhập `H` -> `H9MOA`.contains("H") trả về `true` -> Hợp lệ.
  + Người dùng nhập 1 ký tự khác: Nhập `9` hoặc `M` hoặc `O` hoặc `A` -> đều trả về `true` -> Hợp lệ.
  + Người dùng nhập cụm ký tự liền kề: Nhập `9M` hoặc `MOA` -> đều tồn tại trong chuỗi -> Hợp lệ.
  + Người dùng nhập đủ toàn bộ: Nhập `H9MOA` -> trả về `true` -> Hợp lệ.

- Trường hợp KHÔNG HỢP LỆ (Báo lỗi và bắt nhập lại):
  + Người dùng nhập ký tự không có trong Captcha: Nhập `Adafda` -> `H9MOA`.contains("Adafda") trả về `false` -> Báo lỗi "Captcha sai" / "Captcha incorrect".
  + Người dùng nhập ký tự sai hoa/thường: Nhập `h` (chữ thường) trong khi chuỗi gốc là `H` (chữ hoa) -> trả về `false` -> Báo lỗi.
  + Người dùng nhấn Enter bỏ trống chuỗi: Nhập `""` -> điều kiện `!captchaInput.isEmpty()` chặn lại -> Báo lỗi.

---

# IV. Các Ca Kiểm Thử (Test Cases)

## 1. Kiểm tra Hợp lệ Đầu vào (Input Validation)

### A. Account Number
- Điều kiện hợp lệ: Là chuỗi số VÀ có đúng 10 chữ số (`^[0-9]{10}$`).
- Các trường hợp lỗi:
  + Nhập chữ cái hoặc ký tự đặc biệt (Ví dụ: `a`, `12345abcde`) -> Hiển thị lỗi, bắt nhập lại.
  + Ít hơn 10 chữ số (Ví dụ: `1`, `123456789`) -> Hiển thị lỗi, bắt nhập lại.
  + Nhiều hơn 10 chữ số (Ví dụ: `12345678901`) -> Hiển thị lỗi, bắt nhập lại.
  + Bỏ trống chuỗi -> Báo lỗi, bắt nhập lại.

### B. Password
- Điều kiện hợp lệ: Độ dài từ 8 đến 31 ký tự, bắt buộc phải chứa cả ký tự chữ cái và chữ số (`^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,31}$`).
- Các trường hợp lỗi:
  + Chỉ chứa chữ số (Ví dụ: `12345678`) -> Báo lỗi, bắt nhập lại.
  + Chỉ chứa chữ cái (Ví dụ: `aaaaaaaa`) -> Báo lỗi, bắt nhập lại.
  + Quá ngắn dưới 8 ký tự (Ví dụ: `123456a`) -> Báo lỗi, bắt nhập lại.
  + Quá dài trên 31 ký tự -> Báo lỗi, bắt nhập lại.
  + Chứa ký tự đặc biệt (Ví dụ: `12345678@a`) -> Báo lỗi, bắt nhập lại.
  + Bỏ trống chuỗi -> Báo lỗi, bắt nhập lại.

### C. Captcha (Xác thực qua `contains()`)
- Điều kiện hợp lệ: Chuỗi ký tự nhập vào không rỗng và tồn tại trong chuỗi Captcha đã sinh ra.
- Các trường hợp lỗi:
  + Bỏ trống (nhấn Enter không nhập gì) -> Báo lỗi, bắt nhập lại.
  + Ký tự nhập vào không tồn tại trong chuỗi Captcha đã tạo (Ví dụ: Captcha là `H9MOA`, nhập `AX` hoặc `Adafda`) -> Báo lỗi, bắt nhập lại.
  + Nhập sai chữ hoa / chữ thường so với Captcha -> Báo lỗi, bắt nhập lại.

---

## 2. Kiểm tra Luồng Nghiệp Vụ (Business Logic Test Cases)

### TC01 - Chọn giao diện Tiếng Việt
- Thao tác: Chọn menu option `1`.
- Kết quả mong đợi: Toàn bộ tiêu đề, nhãn nhập liệu và câu thông báo lỗi chuyển sang Tiếng Việt (So tai khoan, Mat khau, Nhap captcha, Dang nhap thanh cong/that bai).

### TC02 - Chọn giao diện Tiếng Anh
- Thao tác: Chọn menu option `2`.
- Kết quả mong đợi: Toàn bộ tiêu đề, nhãn nhập liệu và thông báo lỗi hiển thị bằng Tiếng Anh (Account number, Password, Enter captcha character, Login successfully/failed).

### TC03 - Thoát chương trình
- Thao tác: Chọn menu option `3`.
- Kết quả mong đợi: Chương trình dừng thực thi an toàn (`System.exit(0)`).

### TC04 - Đăng nhập thành công
- Điều kiện: Sử dụng tài khoản có sẵn trong hệ thống (Ví dụ: Account = `0123456789`, Password = `123456ab`).
- Thao tác: Nhập đúng tài khoản, đúng mật khẩu, nhập đúng ký tự con của Captcha.
- Kết quả mong đợi: Hiển thị thông báo "Dang nhap thanh cong!" (Tiếng Việt) hoặc "Login successfully!" (Tiếng Anh).

### TC05 - Đăng nhập thất bại do sai thông tin
- Điều kiện: Nhập số tài khoản hoặc mật khẩu không có trong `Data.listAccount`.
- Thao tác: Nhập Account = `0987654321` (đúng format 10 số), Password = `password123` (đúng format), nhập đúng Captcha.
- Kết quả mong đợi: Hệ thống cho phép hoàn tất xác thực Captcha nhưng in thông báo "Dang nhap that bai!" (Tiếng Việt) hoặc "Login failed!" (Tiếng Anh).
