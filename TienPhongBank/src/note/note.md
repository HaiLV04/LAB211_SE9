# I. Yêu cầu chương trình (Requirement)
- Xây dựng hệ thống đăng nhập điện tử (Ebank - TPBank) hỗ trợ đa ngôn ngữ (Tiếng Việt và Tiếng Anh) qua `ResourceBundle` và file thuộc tính (`.properties`).
- **Menu lựa chọn:** 1. Vietnamese, 2. English, 3. Exit.
- **Số tài khoản (Account Number):** Chuỗi đúng 10 chữ số (`^[0-9]{10}$`), không để trống.
- **Mật khẩu (Password):** Độ dài từ 8 đến 31 ký tự, bắt buộc chứa cả chữ cái và chữ số (`^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,31}$`).
- **Mã Captcha:** Sinh ngẫu nhiên 5 ký tự (chữ hoa, chữ thường, số). Người dùng nhập ký tự hoặc cụm ký tự con tồn tại trong Captcha (`contains()`).
- **Xác thực đăng nhập:** Đối chiếu thông tin tài khoản và mật khẩu với danh sách tài khoản hệ thống lưu trữ sẵn (`Data.listAccount`).

# II. Giải thích các Function theo nghiệp vụ (Functions Explanation)
- **Function 1: Đổi ngôn ngữ (Change language):**
  - Hiển thị menu cho người dùng chọn ngôn ngữ (Tiếng Việt hoặc Tiếng Anh).
  - Tải gói tài nguyên tương ứng (`Language_vi.properties` hoặc `Language_en.properties`) để toàn bộ nhãn nhập liệu và câu thông báo lỗi được hiển thị linh hoạt theo đúng ngôn ngữ người dùng đã chọn.
- **Function 2: Kiểm tra số tài khoản (Checking account number):**
  - Yêu cầu người dùng nhập số tài khoản và dùng mẫu định dạng (Regex) để kiểm tra.
  - Số tài khoản bắt buộc phải đủ 10 chữ số. Nếu nhập sai (nhập chữ, ký tự lạ, thiếu hoặc thừa số), hệ thống chặn lại, hiển thị câu báo lỗi theo ngôn ngữ đã chọn và yêu cầu nhập lại cho đến khi đúng.
- **Function 3: Kiểm tra mật khẩu (Check the password):**
  - Kiểm tra độ mạnh và tính an toàn của mật khẩu người dùng nhập vào bằng mẫu định dạng (Regex).
  - Mật khẩu hợp lệ phải có độ dài từ 8 đến 31 ký tự, đồng thời phải có cả chữ cái và chữ số. Nếu vi phạm (quá ngắn, quá dài, chỉ toàn số hoặc toàn chữ), hệ thống thông báo lỗi và bắt nhập lại.
- **Function 4: Sinh mã Captcha ngẫu nhiên (Generate a random captcha code):**
  - Tạo ra mã bảo mật ngẫu nhiên để chống lại các phần mềm tự động (bot) dò mật khẩu.
  - Tự động bốc ngẫu nhiên 5 ký tự từ tập hợp gồm chữ hoa (A-Z), chữ thường (a-z) và chữ số (0-9) rồi ghép lại thành chuỗi mã Captcha hiển thị trên màn hình.
- **Function 5: Kiểm tra mã Captcha (Check the captcha code):**
  - Xác nhận thao tác đăng nhập được thực hiện bởi con người thật.
  - Áp dụng cơ chế kiểm tra chuỗi con (`contains()`): người dùng chỉ cần nhập một ký tự hoặc cụm ký tự con xuất hiện trong mã Captcha là hợp lệ. Nếu bỏ trống hoặc nhập ký tự không có trong Captcha, hệ thống báo lỗi và bắt nhập lại.
- **Function 6: Điều phối đăng nhập (Login):**
  - Kết nối toàn bộ quy trình đăng nhập thành một chuỗi logic chặt chẽ:
    1. Yêu cầu nhập và kiểm tra Số tài khoản (đúng chuẩn 10 số mới cho đi tiếp).
    2. Yêu cầu nhập và kiểm tra Mật khẩu (đủ chữ và số mới cho đi tiếp).
    3. Sinh mã Captcha ngẫu nhiên và yêu cầu xác thực.
    4. Khi cả 3 lớp bảo vệ đều hợp lệ, hệ thống mới tiến hành đối chiếu tài khoản và mật khẩu với dữ liệu lưu trữ để thông báo đăng nhập thành công hay thất bại.

# III. Test Cases

## 1. Kiểm tra tính hợp lệ đầu vào (Input Validation)
- **Account Number (Đúng 10 chữ số):**
  - Hợp lệ: `1029817261` (chuỗi đúng 10 chữ số).
  - Không hợp lệ: Bỏ trống; chứa chữ cái hoặc ký tự đặc biệt (`12345abcde`); ít hơn 10 số (`12345`); nhiều hơn 10 số (`12345678901`) -> Báo lỗi và bắt nhập lại.
- **Password (8 - 31 ký tự, gồm cả chữ và số):**
  - Hợp lệ: `tuan26062002`, `123456ab` (chuỗi có cả chữ và số, độ dài từ 8 đến 31 ký tự).
  - Không hợp lệ: Bỏ trống; chỉ có chữ (`aaaaaaaa`); chỉ có số (`12345678`); ngắn hơn 8 ký tự (`12345a`); dài hơn 31 ký tự; chứa ký tự đặc biệt (`12345678@a`) -> Báo lỗi và bắt nhập lại.
- **Captcha (Kiểm tra qua hàm `contains()`):**
  - Hợp lệ: Nhập một ký tự hoặc chuỗi con có trong Captcha (Ví dụ Captcha là `H9MOA`: nhập `H`, `9`, `MOA`, hoặc `H9MOA`).
  - Không hợp lệ: Bỏ trống; ký tự không tồn tại trong Captcha (Ví dụ: `XYZ`); sai chữ hoa/thường (`h`) -> Báo lỗi và bắt nhập lại.

## 2. Kiểm tra luồng nghiệp vụ (Business Logic)
- **Chọn 1 (Vietnamese):** Giao diện và thông báo chuyển sang Tiếng Việt ("So tai khoan:", "Mat khau:", "Nhap captcha:").
- **Chọn 2 (English):** Giao diện và thông báo chuyển sang Tiếng Anh ("Account number:", "Password:", "Enter captcha character:").
- **Chọn 3 (Exit):** Dừng và thoát chương trình an toàn.
- **Đăng nhập thành công:** Nhập tài khoản và mật khẩu có sẵn trong hệ thống (Ví dụ: Account `1029817261`, Password `tuan26062002`), xác thực đúng Captcha -> Hiển thị "Dang nhap thanh cong!" (hoặc "Login successfully!").
- **Đăng nhập thất bại:** Nhập tài khoản hoặc mật khẩu không tồn tại trong hệ thống (Ví dụ: Account `0123456789`, Password `password123`), xác thực đúng Captcha -> Hiển thị "Dang nhap that bai!" (hoặc "Login failed!").
