# I. Yêu cầu chương trình (Requirement)
- Xây dựng hệ thống đăng nhập điện tử (Ebank System - TPBank) hỗ trợ đa ngôn ngữ (Tiếng Việt và Tiếng Anh) qua `Locale` và `ResourceBundle`.
- **Lựa chọn ngôn ngữ:** 1. Vietnamese, 2. English, 3. Exit.
- **Số tài khoản (Account Number):** Chuỗi 10 chữ số (`^[0-9]{10}$`), không để trống.
- **Mật khẩu (Password):** Độ dài 8 - 31 ký tự, bắt buộc chứa cả chữ cái và chữ số (`^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,31}$`).
- **Mã Captcha:** Sinh ngẫu nhiên 5 ký tự (chữ và số). Người dùng nhập ký tự hoặc chuỗi con tồn tại trong Captcha đã sinh (`contains()`).
- **Xác thực tài khoản:** So khớp Account và Password với danh sách tài khoản lưu trữ (`Data.listAccount`).

# II. Cơ chế hoạt động (Program Explanation)
1. **Đa ngôn ngữ (i18n):**
   - Đặt `Locale` tương ứng: `Locale("vi", "VN")` hoặc `Locale("en", "US")`.
   - Nạp chuỗi thông báo động từ file cấu hình qua `ResourceBundle.getBundle("resources/Language")`.
2. **Xác thực Captcha:**
   - Dùng hàm `captchaGenerate.contains(captchaInput)` để kiểm tra chuỗi nhập vào có nằm trong mã Captcha gốc hay không.

# III. Test Cases

## 1. Kiểm tra tính hợp lệ dữ liệu (Input Validation)
| Dữ liệu | Input mẫu | Mô tả lỗi | Kết quả mong đợi |
|---------|-----------|-----------|------------------|
| **Account** | `1234567890` | Đúng 10 chữ số | Hợp lệ |
| | `12345abcde` | Chứa chữ cái/ký tự đặc biệt | Báo lỗi định dạng, bắt nhập lại |
| | `12345` | Ít hơn 10 số | Báo lỗi độ dài, bắt nhập lại |
| | ` ` (Trống) | Bỏ trống chuỗi | Báo lỗi, bắt nhập lại |
| **Password** | `123456ab` | Đủ chữ và số (8-31 ký tự) | Hợp lệ |
| | `12345678` | Chỉ có chữ số | Báo lỗi thiếu chữ, bắt nhập lại |
| | `abcdefgh` | Chỉ có chữ cái | Báo lỗi thiếu số, bắt nhập lại |
| | `12345a` | Ngắn hơn 8 ký tự | Báo lỗi độ dài, bắt nhập lại |
| | `12345678@a` | Chứa ký tự đặc biệt | Báo lỗi định dạng, bắt nhập lại |
| **Captcha** | `H` hoặc `H9M` | Tồn tại trong Captcha (VD: `H9MOA`) | Hợp lệ |
| | `XYZ` | Không tồn tại trong Captcha | Báo lỗi sai Captcha, bắt nhập lại |
| | `h` | Sai chữ hoa/thường | Báo lỗi, bắt nhập lại |
| | ` ` (Trống) | Bỏ trống chuỗi | Báo lỗi, bắt nhập lại |

## 2. Kiểm tra luồng nghiệp vụ (Business Logic)
| Ca kiểm thử | Thao tác thực hiện | Kết quả mong đợi |
|-------------|-------------------|------------------|
| **Chọn Tiếng Việt** | Nhập `1` | Giao diện và thông báo chuyển sang Tiếng Việt |
| **Chọn Tiếng Anh** | Nhập `2` | Giao diện và thông báo chuyển sang Tiếng Anh |
| **Thoát chương trình** | Nhập `3` | Kết thúc chương trình (`System.exit(0)`) |
| **Đăng nhập thành công** | Nhập đúng Account, Password trong database, nhập đúng Captcha | Thông báo: "Dang nhap thanh cong!" |
| **Đăng nhập thất bại** | Nhập sai Account hoặc Password, nhập đúng Captcha | Thông báo: "Dang nhap that bai!" |
