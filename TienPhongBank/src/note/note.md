# I. Yêu cầu chương trình (Requirement)
- Xây dựng hệ thống đăng nhập điện tử (Ebank - TPBank) hỗ trợ đa ngôn ngữ (Tiếng Việt và Tiếng Anh).
- **Lựa chọn ngôn ngữ:** 1. Vietnamese, 2. English, 3. Exit.
- **Số tài khoản (Account Number):** Chuỗi đúng 10 chữ số, không để trống.
- **Mật khẩu (Password):** Độ dài từ 8 đến 31 ký tự, bắt buộc phải có cả chữ cái và chữ số.
- **Mã Captcha:** Chuỗi 5 ký tự ngẫu nhiên (chữ và số). Người dùng nhập ký tự hoặc cụm ký tự con nằm trong mã Captcha đã sinh (`contains()`).
- **Xác thực tài khoản:** Đối chiếu tài khoản và mật khẩu vừa nhập với dữ liệu lưu trữ để thông báo đăng nhập thành công hay thất bại.

# II. Giải thích các Function theo nghiệp vụ (Function Explanation)
- **Function 1: Đổi ngôn ngữ (Change language):**
  - Giúp chương trình giao tiếp linh hoạt bằng cả Tiếng Việt và Tiếng Anh.
  - Toàn bộ câu chữ, nhãn nhập liệu và thông báo lỗi được tách riêng vào 2 file cấu hình ngôn ngữ (`Vi.properties` và `En.properties`). Khi người dùng chọn ngôn ngữ nào, hệ thống tự động tra cứu các câu từ trong file tương ứng để hiển thị, không cần viết cứng câu chữ vào code.
- **Function 2: Kiểm tra số tài khoản (Checking account number):**
  - Đảm bảo số tài khoản nhập vào đúng chuẩn ngân hàng: bắt buộc đủ 10 chữ số, không chứa chữ cái hay ký tự đặc biệt.
  - Dùng mẫu quy tắc (Regex) để kiểm tra. Nếu nhập sai, hệ thống lập tức chặn lại, in thông báo lỗi bằng ngôn ngữ đã chọn và yêu cầu nhập lại cho đến khi đúng.
- **Function 3: Kiểm tra mật khẩu (Check the password):**
  - Đảm bảo tính an toàn bảo mật, yêu cầu mật khẩu phải đủ mạnh: độ dài từ 8 đến 31 ký tự và bắt buộc phải chứa cả chữ cái lẫn chữ số.
  - Nếu người dùng nhập mật khẩu quá ngắn, quá dài hoặc chỉ toàn số/toàn chữ, hệ thống báo lỗi tương ứng và yêu cầu nhập lại.
- **Function 4: Sinh mã Captcha ngẫu nhiên (Generate a random captcha code):**
  - Tạo ra mã bảo mật ngẫu nhiên nhằm phân biệt người dùng thật với các phần mềm tự động (bot) dò mật khẩu.
  - Tự động bốc ngẫu nhiên 5 ký tự từ tập hợp chữ hoa, chữ thường và chữ số để ghép thành một mã Captcha và hiển thị lên màn hình.
- **Function 5: Kiểm tra mã Captcha (Check the captcha code):**
  - Xác nhận thao tác đăng nhập được thực hiện bởi con người.
  - Sử dụng cơ chế kiểm tra sự tồn tại (`contains()`): người dùng chỉ cần nhập một ký tự hoặc chuỗi ký tự con có xuất hiện trong mã Captcha gốc là hợp lệ. Nếu nhập ký tự không có trong Captcha hoặc bỏ trống, hệ thống báo lỗi và bắt nhập lại.
- **Function 6: Điều phối đăng nhập (Login):**
  - Đóng vai trò là luồng xử lý chính, kết nối các bước đăng nhập tuần tự theo thứ tự logic:
    1. Nhập và kiểm tra Số tài khoản (hợp lệ mới cho đi tiếp).
    2. Nhập và kiểm tra Mật khẩu (hợp lệ mới cho đi tiếp).
    3. Hiển thị Captcha và yêu cầu người dùng xác nhận.
    4. Sau khi vượt qua cả 3 lớp kiểm tra, hệ thống mới đối chiếu thông tin với danh sách tài khoản để đưa ra kết quả cuối cùng: đăng nhập thành công hay thất bại.

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
- **Đăng nhập thành công:** Nhập đúng Account và Password có trong dữ liệu, xác thực đúng Captcha -> In thông báo "Dang nhap thanh cong!".
- **Đăng nhập thất bại:** Nhập sai Account hoặc Password (dù đúng định dạng), xác thực đúng Captcha -> In thông báo "Dang nhap that bai!".
