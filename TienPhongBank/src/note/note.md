# Yêu cầu (Requirement)
Chương trình Login Ebank, hỗ trợ đa ngôn ngữ (Tiếng Việt, Tiếng Anh). Yêu cầu người dùng nhập Account Number, Password, và Captcha. Nếu thông tin nhập vào hợp lệ và khớp với dữ liệu hệ thống, cho phép đăng nhập thành công.

# Giải thích Logic (Logic Explanation)
- Nhập `1` (Vietnamese): Đặt Locale thành Tiếng Việt, hiển thị menu Tiếng Việt, và chuyển sang hàm `login()`.
- Nhập `2` (English): Giữ Locale là Tiếng Anh, hiển thị menu Tiếng Anh, và chuyển sang hàm `login()`.
- Trong hàm `login()`:
  - Hệ thống yêu cầu nhập và xác thực:
    - Account Number
    - Password
    - Captcha
  - Hệ thống so sánh Account Number và Password đã nhập với dữ liệu lưu trữ (`Data.listAccount`).
  - Kết quả:
    - **Thành công**: Nếu khớp → In thông báo đăng nhập thành công.
    - **Thất bại**: Nếu không khớp → In thông báo đăng nhập thất bại.

# Các ca kiểm thử (Test Cases)

## Kiểm tra Hợp lệ Đầu vào

### 1. Account Number
- **Điều kiện hợp lệ**: Là số VÀ có đúng 10 chữ số.
- **Các trường hợp lỗi**:
  - Là chữ cái/ký tự đặc biệt (Ví dụ: `a`) → Hiển thị lỗi, bắt nhập lại.
  - Ít hơn 10 chữ số (Ví dụ: `123456789`) → Hiển thị lỗi, bắt nhập lại.
  - Là số thập phân/chuỗi không phải số → Hiển thị lỗi, bắt nhập lại.
  - Bỏ trống → Bắt nhập lại.

### 2. Password
- **Điều kiện hợp lệ**: Phải là chuỗi alphanumeric (chứa ký tự và số) VÀ có độ dài hợp lệ (từ 8 đến 31 ký tự).
- **Các trường hợp lỗi**:
  - Chỉ chứa số (Ví dụ: `12345678`) → Hiển thị lỗi, bắt nhập lại.
  - Chỉ chứa chữ cái (Ví dụ: `aaaaaaaa`) → Hiển thị lỗi, bắt nhập lại.
  - Độ dài ngoài phạm vi 8-31 ký tự (quá ngắn hoặc quá dài) → Hiển thị lỗi, bắt nhập lại.
  - Bỏ trống → Hiển thị lỗi, bắt nhập lại.

### 3. Captcha
- **Điều kiện hợp lệ**: Những ký tự nhập vào phải tồn tại trong chuỗi Captcha ngẫu nhiên đã tạo (chỉ chứa các ký tự có trong Captcha gốc).
- **Các trường hợp lỗi**:
  - Bỏ trống → Hiển thị lỗi, bắt nhập lại.
  - Ký tự nhập vào không tồn tại trong chuỗi Captcha đã tạo (Ví dụ: Nhập `AX` khi Captcha là `H9MOA`) → Hiển thị lỗi, bắt nhập lại.
