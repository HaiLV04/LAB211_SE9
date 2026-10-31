# I. Yêu cầu chương trình (Requirement)
- Xây dựng hệ thống đăng nhập điện tử (Ebank - TPBank) hỗ trợ đa ngôn ngữ (Tiếng Việt và Tiếng Anh) qua `Locale` và `ResourceBundle`.
- **Lựa chọn ngôn ngữ:** 1. Vietnamese, 2. English, 3. Exit.
- **Số tài khoản (Account Number):** Chuỗi 10 chữ số (`^[0-9]{10}$`), không để trống.
- **Mật khẩu (Password):** Độ dài 8 - 31 ký tự, bắt buộc chứa cả chữ cái và chữ số (`^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,31}$`).
- **Mã Captcha:** Chuỗi 5 ký tự ngẫu nhiên (chữ và số). Người dùng nhập ký tự hoặc chuỗi con tồn tại trong Captcha đã sinh (`contains()`).
- **Xác thực tài khoản:** So khớp Account và Password với danh sách tài khoản lưu trữ (`Data.listAccount`).

# II. Giải thích các Function (Functions Explanation)
- `TPBank.start()`: Điểm khởi chạy, nhận lựa chọn từ menu để thiết lập `Locale` và nạp gói ngôn ngữ `ResourceBundle` tương ứng.
- `Helper.menu()`: Hiển thị giao diện menu 3 lựa chọn (1. Vietnamese, 2. English, 3. Exit).
- `Helper.generateCaptcha()`: Sinh chuỗi ngẫu nhiên 5 ký tự gồm chữ hoa, chữ thường và chữ số.
- `Validate.getInt()`: Kiểm tra và nhận giá trị số nguyên hợp lệ trong phạm vi menu (từ 1 đến 3).
- `Validate.getString()`: Kiểm tra chuỗi nhập khớp với biểu thức chính quy (Regex) cho Account (10 số) và Password (8-31 ký tự gồm chữ và số).
- `Validate.verifyCaptcha()`: Xác thực chuỗi người dùng nhập không rỗng và tồn tại trong mã Captcha gốc bằng hàm `contains()`.
- `LoginService.login()`: Điều phối luồng đăng nhập tuần tự: nhập Account -> nhập Password -> sinh Captcha -> verify Captcha -> xác thực thông tin.
- `LoginService.authentication()`: So sánh tài khoản và mật khẩu vừa nhập với `Data.listAccount` để thông báo đăng nhập thành công hoặc thất bại.

# III. Test Cases

## 1. Kiểm tra tính hợp lệ đầu vào (Input Validation)
- **Account Number (10 chữ số):**
  - Hợp lệ: Nhập chuỗi đúng 10 chữ số (VD: `1234567890`).
  - Không hợp lệ: Bỏ trống; chứa chữ cái hoặc ký tự đặc biệt (VD: `12345abcde`); ít hơn hoặc nhiều hơn 10 chữ số (VD: `12345`, `12345678901`) -> Báo lỗi và bắt nhập lại.
- **Password (8 - 31 ký tự, gồm cả chữ và số):**
  - Hợp lệ: Chuỗi chứa cả chữ và số trong dải 8-31 ký tự (VD: `123456ab`).
  - Không hợp lệ: Bỏ trống; chỉ chứa chữ (VD: `abcdefgh`); chỉ chứa số (VD: `12345678`); ngắn hơn 8 hoặc dài hơn 31 ký tự; chứa ký tự đặc biệt -> Báo lỗi và bắt nhập lại.
- **Captcha (Xác thực qua hàm `contains()`):**
  - Hợp lệ: Nhập một ký tự hoặc chuỗi ký tự con có trong Captcha (VD: Captcha là `H9MOA`, nhập `H`, `9`, `MOA`, hoặc `H9MOA`).
  - Không hợp lệ: Bỏ trống; ký tự không tồn tại trong Captcha (VD: `XYZ`); nhập sai chữ hoa/thường (VD: `h`) -> Báo lỗi và bắt nhập lại.

## 2. Kiểm tra luồng nghiệp vụ (Business Logic)
- **Chọn 1 (Vietnamese):** Giao diện và toàn bộ thông báo chuyển sang Tiếng Việt, sau đó tiến hành đăng nhập.
- **Chọn 2 (English):** Giao diện và toàn bộ thông báo chuyển sang Tiếng Anh, sau đó tiến hành đăng nhập.
- **Chọn 3 (Exit):** Dừng và thoát chương trình an toàn (`System.exit(0)`).
- **Đăng nhập thành công:** Nhập đúng Account và Password có trong `Data.listAccount`, xác thực đúng Captcha -> In thông báo "Dang nhap thanh cong!".
- **Đăng nhập thất bại:** Nhập sai Account hoặc Password (đúng định dạng), xác thực đúng Captcha -> In thông báo "Dang nhap that bai!".
