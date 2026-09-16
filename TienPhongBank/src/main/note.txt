Tóm Tắt Test Cases cho Chương trình Login Ebank
-Luồng Chương Trình Chính:
+	Nhập 1 (Vietnamese): Đặt Locale thành Tiếng Việt , hiển thị menu Tiếng Việt, và chuyển sang login()
+	Nhập 2 (English): Giữ Locale là Tiếng Anh, hiển thị menu Tiếng Anh, và chuyển sang login().
+	Hệ thống yêu cầu nhập va xac thuc Account Number, password, capcha.
+	Hệ thống so sánh Account Number và Password đã nhập với dữ liệu lưu trữ (Data.listAccount).
Thành công: Nếu khớp → In thông báo đăng nhập thành công 
Thất bại: Nếu không khớp → In thông báo đăng nhập thất bại

- Kiểm tra Hợp lệ Đầu vào:
Account Number: Là số VÀ có đúng 10 chữ số -> Hop le
+	Là chữ cái/ký tự đặc biệt (Ví dụ: 'a') → Hiển thị lỗi , bắt nhập lại.
+	Ít hơn 10 chữ số (Ví dụ: '123456789') → Hiển thị lỗi , bắt nhập lại.
+	Là số thập phân/chuỗi không phải số. → Hiển thị lỗi , bắt nhập lại.
+	Bo trong → Bắt nhập lại.
Password: Phải là chuỗi alphanumeric (chứa ký tự và số) VÀ có độ dài hợp lệ (8-31 ký tự).
+	Chỉ chứa số (Ví dụ: '12345678') .→ Hiển thị lỗi , bắt nhập lại.
+	Chỉ chứa chữ cái (Ví dụ: 'aaaaaaaa') → Hiển thị lỗi , bắt nhập lại.
+	Độ dài ngoài phạm vi 8-31 ký tự (quá ngắn/quá dài).→ Hiển thị lỗi , bắt nhập lại
+	Bo trong → Hiển thị lỗi , bắt nhập lại.
Captcha: Nhung Ký tự nhập phai tồn tại trong chuỗi Captcha ngẫu nhiên đã tạo.
+	Bo trong →Hiển thị lỗi , bắt nhập lại.
+	Ký tự nhập vào không tồn tại trong chuỗi Captcha đã tạo (Ví dụ: Nhập 'AX' khi Captcha là 'H9MOA'). →Hiển thị lỗi , bắt nhập lại.

