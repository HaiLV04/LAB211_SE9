I. Yêu cầu chương trình (Requirement)
- Xây dựng hệ thống đăng nhập điện tử (Ebank - TPBank) hỗ trợ đa ngôn ngữ (Tiếng Việt và Tiếng Anh) qua ResourceBundle và file thuộc tính (.properties).
- Menu lựa chọn: 1. Vietnamese, 2. English, 3. Exit.
- Số tài khoản (Account Number): Chuỗi đúng 10 chữ số, không để trống.
- Mật khẩu (Password): Độ dài từ 8 đến 31 ký tự, bắt buộc chứa cả chữ cái và chữ số.
- Mã Captcha: Sinh ngẫu nhiên 5 ký tự (chữ hoa, chữ thường, số). Người dùng nhập ký tự hoặc cụm ký tự con tồn tại trong Captcha (dùng hàm contains()).
- Xác thực đăng nhập: Duyệt danh sách tài khoản lưu sẵn (Data.listAccount), dùng hàm equals() kiểm tra xem tài khoản và mật khẩu vừa nhập có bằng với một tài khoản nào trong danh sách hay không.

II. Giải thích các Function theo nghiệp vụ (Functions Explanation)
- Function 1: Đổi ngôn ngữ (Change language):
  + Hiển thị menu cho người dùng chọn Tiếng Việt, Tiếng Anh hoặc Thoát.
  + Người dùng chọn tiếng nào thì chương trình mở đúng file ngôn ngữ đó để toàn bộ câu nhắc nhập và câu báo lỗi hiển thị theo tiếng đó.
- Function 2: Kiểm tra số tài khoản (Checking account number):
  + Yêu cầu người dùng nhập số tài khoản và dùng mẫu định dạng (Regex) để kiểm tra.
  + Số tài khoản bắt buộc phải đủ 10 chữ số. Nếu nhập sai (nhập chữ, ký tự lạ, thiếu hoặc thừa số), hệ thống chặn lại, hiển thị câu báo lỗi theo ngôn ngữ đã chọn và yêu cầu nhập lại cho đến khi đúng.
- Function 3: Kiểm tra mật khẩu (Check the password):
  + Kiểm tra độ mạnh và tính an toàn của mật khẩu người dùng nhập vào bằng mẫu định dạng (Regex).
  + Mật khẩu hợp lệ phải có độ dài từ 8 đến 31 ký tự, đồng thời phải có cả chữ cái và chữ số. Nếu vi phạm (quá ngắn, quá dài, chỉ toàn số hoặc toàn chữ), hệ thống thông báo lỗi và bắt nhập lại.
- Function 4: Sinh mã Captcha ngẫu nhiên (Generate a random captcha code):
  + Tự động sinh ngẫu nhiên một mã Captcha gồm 5 ký tự (chữ hoa, chữ thường và số) hiển thị lên màn hình mỗi lần đăng nhập.
- Function 5: Kiểm tra mã Captcha (Check the captcha code):
  + Kiểm tra mã người dùng nhập bằng cơ chế chuỗi con (hàm contains()): chỉ cần nhập đúng một ký tự hoặc cụm ký tự con có trong mã Captcha là hợp lệ. Nếu bỏ trống hoặc nhập ký tự không có trong Captcha, hệ thống báo lỗi và bắt nhập lại.
- Function 6: Điều phối đăng nhập (Login):
  + Kết nối toàn bộ quy trình đăng nhập thành một chuỗi logic chặt chẽ:
    1. Yêu cầu nhập và kiểm tra Số tài khoản (đúng chuẩn 10 số mới cho đi tiếp).
    2. Yêu cầu nhập và kiểm tra Mật khẩu (đủ chữ và số mới cho đi tiếp).
    3. Sinh mã Captcha ngẫu nhiên và yêu cầu xác thực.
    4. Khi cả 3 thông tin đều hợp lệ, hệ thống duyệt danh sách Data.listAccount, dùng hàm equals() kiểm tra xem tài khoản và mật khẩu vừa nhập có bằng với một tài khoản nào trong danh sách hay không để thông báo đăng nhập thành công hoặc thất bại.

III. Test Cases

1. Kiểm tra tính hợp lệ đầu vào (Input Validation)
- Account Number (Đúng 10 chữ số):
  + Hợp lệ: 1029817261 (chuỗi đúng 10 chữ số).
  + Không hợp lệ: Bỏ trống; chứa chữ cái hoặc ký tự đặc biệt (12345abcde); ít hơn 10 số (12345); nhiều hơn 10 số (12345678901) -> Báo lỗi và bắt nhập lại.
- Password (8 - 31 ký tự, gồm cả chữ và số):
  + Hợp lệ: tuan26062002, 123456ab (chuỗi có cả chữ và số, độ dài từ 8 đến 31 ký tự).
  + Không hợp lệ: Bỏ trống; chỉ có chữ (aaaaaaaa); chỉ có số (12345678); ngắn hơn 8 ký tự (12345a); dài hơn 31 ký tự; chứa ký tự đặc biệt (12345678@a) -> Báo lỗi và bắt nhập lại.
- Captcha (Kiểm tra qua hàm contains()):
  + Hợp lệ: Nhập một ký tự hoặc chuỗi con có trong Captcha (Ví dụ Captcha là H9MOA: nhập H, 9, MOA, hoặc H9MOA).
  + Không hợp lệ: Bỏ trống; ký tự không tồn tại trong Captcha (Ví dụ: XYZ); sai chữ hoa/thường (h) -> Báo lỗi và bắt nhập lại.

2. Kiểm tra luồng nghiệp vụ (Business Logic)
- Chọn 1 (Vietnamese): Giao diện và thông báo chuyển sang Tiếng Việt ("So tai khoan:", "Mat khau:", "Nhap captcha:").
- Chọn 2 (English): Giao diện và thông báo chuyển sang Tiếng Anh ("Account number:", "Password:", "Enter captcha character:").
- Chọn 3 (Exit): Dừng và thoát chương trình an toàn.
- Đăng nhập thành công: Nhập tài khoản và mật khẩu bằng với một tài khoản có sẵn trong hệ thống (Ví dụ: Account 1029817261, Password tuan26062002), xác thực đúng Captcha -> Hiển thị "Dang nhap thanh cong!" (hoặc "Login successfully!").
- Đăng nhập thất bại: Nhập tài khoản hoặc mật khẩu không bằng với bất kỳ tài khoản nào trong hệ thống (Ví dụ: Account 0123456789, Password password123), xác thực đúng Captcha -> Hiển thị "Dang nhap that bai!" (hoặc "Login failed!").
