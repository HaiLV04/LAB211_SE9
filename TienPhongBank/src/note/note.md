I. Yêu cầu chương trình (Requirement)
- Xây dựng hệ thống đăng nhập điện tử (Ebank - TPBank) hỗ trợ đa ngôn ngữ (Tiếng Việt và Tiếng Anh) qua ResourceBundle (package: java.util.ResourceBundle, java.util.Locale) và file thuộc tính (.properties đặt trong package resources: Language_vi.properties, Language_en.properties).
- Menu lựa chọn: 1. Vietnamese, 2. English, 3. Exit.
- Số tài khoản (Account Number): Chuỗi đúng 10 chữ số, không để trống.
- Mật khẩu (Password): Độ dài từ 8 đến 31 ký tự, bắt buộc chứa cả chữ cái và chữ số.
- Mã Captcha: Sinh ngẫu nhiên 5 ký tự (chữ hoa, chữ thường, số). Người dùng nhập ký tự hoặc cụm ký tự con tồn tại trong Captcha (dùng hàm contains()).
- Xác thực đăng nhập: Duyệt danh sách (Data.listAccount), dùng hàm equals() so sánh: nếu bằng thì báo thành công, không có thì ngược lại.
- Cấu trúc chương trình: Lớp `Main` hiển thị menu chọn ngôn ngữ; lớp `Ebank` (được tổ chức theo mô hình MVC: `TPBank`, `LoginService`, `Validate`, `Helper`) chứa các hàm xử lý bên dưới.

II. Giải thích các Function theo nghiệp vụ (Functions Explanation)
- Function 1: Đổi ngôn ngữ (Convert languages):
  + Sử dụng hàm: `void setLocate(Locale locate)`
    * Input: `locate` (đối tượng Locale / tên properties cần chuyển đổi; trong đề viết nhầm `Locate`).
  + Nghiệp vụ: Chuyển đổi ngôn ngữ hệ thống sang file thuộc tính tương ứng (`Language_vi.properties` hoặc `Language_en.properties`) qua `ResourceBundle` và `Locale` để toàn bộ nhãn nhập và câu báo lỗi hiển thị theo tiếng đó.
  + Vị trí trong code: `controller.LoginService.setLocate(Locale locale)` (được gọi từ `TPBank.start()`).
- Function 2: Kiểm tra số tài khoản (Check account number):
  + Sử dụng hàm: `String checkAccountNumber(String accountNumber)`
    * Input: `accountNumber` (chuỗi số tài khoản cần kiểm tra).
    * Return: `String` (message thông báo lỗi nếu không hợp lệ hoặc chuỗi rỗng khi hợp lệ).
  + Nghiệp vụ: Dùng Regex (`^\d{10}$`) kiểm tra số tài khoản phải là một số và có đúng 10 chữ số. Nếu sai (chứa chữ, ký tự lạ, thừa/thiếu số, rỗng), trả về thông báo lỗi `accountInvalid` và bắt nhập lại.
  + Vị trí trong code: `controller.LoginService.checkAccountNumber(String accountNumber)`.
- Function 3: Kiểm tra mật khẩu (Check password):
  + Sử dụng hàm: `String checkPassword(String password)`
    * Input: `password` (mật khẩu cần kiểm tra).
    * Return: `String` (message thông báo lỗi nếu không hợp lệ hoặc chuỗi rỗng khi hợp lệ).
  + Nghiệp vụ: Dùng Regex lookahead (`^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,31}$`) kiểm tra mật khẩu từ 8-31 ký tự, bắt buộc phải có cả chữ và số (alphanumeric). Nếu sai, trả về thông báo lỗi `passwordInvalid` và bắt nhập lại.
  + Vị trí trong code: `controller.LoginService.checkPassword(String password)`.
- Function 4: Sinh mã Captcha ngẫu nhiên (Generate random captcha):
  + Sử dụng hàm: `String generateCaptcha()`
    * Return: `String` (chuỗi mã Captcha ngẫu nhiên gồm 5 ký tự).
  + Nghiệp vụ: Tự động sinh ngẫu nhiên một mã Captcha gồm 5 ký tự (chữ hoa, chữ thường, số) hiển thị mỗi lần đăng nhập để chống bot/brute-force.
  + Vị trí trong code: `controller.LoginService.generateCaptcha()`.
- Function 5: Kiểm tra mã Captcha (Check captcha code):
  + Sử dụng hàm: `String checkCaptcha(String captchaInput, String captchaGenerate)`
    * Input: `captchaInput` (chuỗi người dùng nhập), `captchaGenerate` (mã Captcha gốc đã sinh).
    * Return: `String` (message thông báo về kết quả kiểm tra Captcha, báo lỗi nếu sai hoặc chuỗi rỗng khi đúng).
  + Nghiệp vụ: Dùng hàm `contains()` kiểm tra ký tự hoặc chuỗi con người dùng nhập có tồn tại trong `captchaGenerate` hay không. Nếu rỗng hoặc không tồn tại, trả về lỗi `captchaInvalid` và bắt nhập lại.
  + Vị trí trong code: `controller.LoginService.checkCaptcha(String captchaInput, String captchaGenerate)`.
- Function 6: Điều phối đăng nhập (Login) - void login():
  + Nghiệp vụ: Kết nối toàn bộ quy trình đăng nhập thành chuỗi logic chặt chẽ (lần lượt gọi `checkAccountNumber` -> `checkPassword` -> `generateCaptcha` -> `checkCaptcha` -> xác thực danh sách `Data.listAccount`).
  + Vị trí trong code: `controller.LoginService.login()`.

III. Test Cases

1. Kiểm tra tính hợp lệ đầu vào (Input Validation)
- Account Number (Đúng 10 chữ số):
  + Hợp lệ: 1029817261 (chuỗi đúng 10 chữ số).
  + Không hợp lệ: Bỏ trống; chứa chữ cái hoặc ký tự đặc biệt (12345abcde); ít hơn 10 số (12345); nhiều hơn 10 số (12345678901) -> Báo lỗi và bắt nhập lại:
    * Tiếng Việt (accountInvalid): "So tai khoan la 1 so va co 10 chu so"
    * Tiếng Anh (accountInvalid): "Account number must is a number and must have 10 digits"
- Password (8 - 31 ký tự, gồm cả chữ và số):
  + Hợp lệ: tuan26062002, 123456ab (chuỗi có cả chữ và số, độ dài từ 8 đến 31 ký tự).
  + Không hợp lệ: Bỏ trống; chỉ có chữ (aaaaaaaa); chỉ có số (12345678); ngắn hơn 8 ký tự (12345a); dài hơn 31 ký tự; chứa ký tự đặc biệt (12345678@a) -> Báo lỗi và bắt nhập lại:
    * Tiếng Việt (passwordInvalid): "Mat khau phai trong khoang 8-31 ki tu va phai chua ki tu chu va so"
    * Tiếng Anh (passwordInvalid): "Password must be between 8 and 31 characters and must be alphanumeric"
- Captcha (Kiểm tra qua hàm contains()):
  + Hợp lệ: Nhập một ký tự hoặc chuỗi con có trong Captcha (Ví dụ Captcha là H9MOA: nhập H, 9, MOA, hoặc H9MOA).
  + Không hợp lệ: Bỏ trống; ký tự không tồn tại trong Captcha (Ví dụ: XYZ); sai chữ hoa/thường (h) -> Báo lỗi và bắt nhập lại:
    * Tiếng Việt (captchaInvalid): "Captcha sai"
    * Tiếng Anh (captchaInvalid): "Captcha incorrect"

2. Kiểm tra luồng nghiệp vụ (Business Logic)
- Chọn 1 (Vietnamese): Giao diện và thông báo chuyển sang Tiếng Việt ("So tai khoan:", "Mat khau:", "Nhap captcha:").
- Chọn 2 (English): Giao diện và thông báo chuyển sang Tiếng Anh ("Account number:", "Password:", "Enter captcha character:").
- Chọn 3 (Exit): Dừng và thoát chương trình an toàn.
- Đăng nhập thành công: Nhập tài khoản và mật khẩu bằng với dữ liệu trong hệ thống (Ví dụ: Account 1029817261, Password tuan26062002), xác thực đúng Captcha -> Hiển thị thông báo:
  + Tiếng Việt (loginSuccess): "Dang nhap thanh cong!"
  + Tiếng Anh (loginSuccess): "Login successfully!"
- Đăng nhập thất bại: Nhập tài khoản hoặc mật khẩu không có trong hệ thống (Ví dụ: Account 0123456789, Password password123), xác thực đúng Captcha -> Hiển thị thông báo:
  + Tiếng Việt (loginFailed): "Dang nhap that bai!"
  + Tiếng Anh (loginFailed): "Login failed!"

3. Kiểm tra Menu (Menu Validation)
- Nhập ngoài khoảng 1-3 (Ví dụ: 0, 4) -> Báo lỗi: "Just 1->3"
- Nhập ký tự không phải số (Ví dụ: abc, để trống) -> Báo lỗi: "Invalid!"

4. Kiểm tra thông báo song ngữ (Message Test)
- Trường hợp 1: Nhắc người dùng nhập số tài khoản (Prompt nhập Account Number)
  + Key: account
  + Tiếng Việt: "So tai khoan:"
  + Tiếng Anh: "Account number:"
  + Mục đích: Hiển thị nhãn yêu cầu người dùng nhập số tài khoản từ bàn phím.
- Trường hợp 2: Báo lỗi khi số tài khoản không hợp lệ (Invalid Account Number)
  + Key: accountInvalid
  + Tiếng Việt: "So tai khoan la 1 so va co 10 chu so"
  + Tiếng Anh: "Account number must is a number and must have 10 digits"
  + Tình huống hiển thị: Người dùng để trống, nhập chữ cái, ký tự đặc biệt, ít hơn 10 số hoặc nhiều hơn 10 số.
- Trường hợp 3: Nhắc người dùng nhập mật khẩu (Prompt nhập Password)
  + Key: password
  + Tiếng Việt: "Mat khau:"
  + Tiếng Anh: "Password:"
  + Mục đích: Hiển thị nhãn yêu cầu người dùng nhập mật khẩu từ bàn phím.
- Trường hợp 4: Báo lỗi khi mật khẩu không hợp lệ (Invalid Password)
  + Key: passwordInvalid
  + Tiếng Việt: "Mat khau phai trong khoang 8-31 ki tu va phai chua ki tu chu va so"
  + Tiếng Anh: "Password must be between 8 and 31 characters and must be alphanumeric"
  + Tình huống hiển thị: Người dùng để trống, mật khẩu ngắn hơn 8 ký tự, dài hơn 31 ký tự, chỉ chứa toàn chữ, chỉ chứa toàn số hoặc chứa ký tự đặc biệt.
- Trường hợp 5: Hiển thị chuỗi mã Captcha ngẫu nhiên được sinh ra (Display Generated Captcha)
  + Key: captcha
  + Tiếng Việt: "Captcha:"
  + Tiếng Anh: "Captcha:"
  + Mục đích: Thông báo mã Captcha ngẫu nhiên vừa được tạo (ví dụ: Captcha: H9MOA).
- Trường hợp 6: Nhắc người dùng nhập ký tự Captcha (Prompt nhập Captcha)
  + Key: inputCaptcha
  + Tiếng Việt: "Nhap captcha:"
  + Tiếng Anh: "Enter captcha character:"
  + Mục đích: Hiển thị nhãn yêu cầu người dùng nhập ký tự hoặc chuỗi con có trong Captcha.
- Trường hợp 7: Báo lỗi khi Captcha không hợp lệ (Invalid Captcha)
  + Key: captchaInvalid
  + Tiếng Việt: "Captcha sai"
  + Tiếng Anh: "Captcha incorrect"
  + Tình huống hiển thị: Người dùng để trống hoặc nhập các ký tự không tồn tại trong chuỗi Captcha đã sinh ra.
- Trường hợp 8: Thông báo đăng nhập thành công (Login Success)
  + Key: loginSuccess
  + Tiếng Việt: "Dang nhap thanh cong!"
  + Tiếng Anh: "Login successfully!"
  + Tình huống hiển thị: Khi cả tài khoản, mật khẩu (khớp Data.listAccount) và Captcha đều đúng.
- Trường hợp 9: Thông báo đăng nhập thất bại (Login Failed)
  + Key: loginFailed
  + Tiếng Việt: "Dang nhap that bai!"
  + Tiếng Anh: "Login failed!"
  + Tình huống hiển thị: Khi Captcha đúng nhưng tài khoản hoặc mật khẩu không có trong hệ thống.
