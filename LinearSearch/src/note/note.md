# Yêu Cầu Bài Toán
Yêu cầu người dùng nhập vào bàn phím 2 số: số lượng phần tử của mảng và giá trị cần tìm kiếm.
- **Input 1**: Số lượng phần tử mảng n (n > 0).
- **Input 2**: Giá trị cần tìm kiếm (search number).

Sau khi nhận input, hệ thống sẽ tự động khởi tạo một mảng gồm n phần tử, với các phần tử là số nguyên ngẫu nhiên từ 0 đến n.
Tiếp theo, gọi hàm tìm kiếm giá trị cần tìm trong mảng vừa tạo và in ra màn hình tất cả các chỉ số (index) của giá trị đó nếu tìm thấy.

# Giải Thích Thuật Toán
**Tìm kiếm tuần tự (Linear Search):**
Thuật toán duyệt bắt đầu từ phần tử đầu tiên của mảng đến phần tử cuối cùng. Ở mỗi bước, so sánh phần tử hiện tại với giá trị cần tìm kiếm. 
- Nếu khớp, ghi lại vị trí (index) của phần tử đó.
- Nếu không khớp, tiếp tục kiểm tra phần tử tiếp theo.

Quá trình lặp lại cho đến khi duyệt qua toàn bộ mảng. Kết quả trả về là một mảng ghi lại tất cả các vị trí tìm thấy, hoặc một mảng rỗng nếu giá trị không xuất hiện.

# Test Cases
## 1. Input Validation (Kiểm tra đầu vào)
- **Nhập n sai:**
  - Nhập số nguyên âm: Hệ thống báo lỗi và yêu cầu nhập lại.
  - Nhập số 0: Hệ thống báo lỗi và yêu cầu nhập lại.
  - Nhập chữ cái hoặc kí tự đặc biệt: Hệ thống báo lỗi và yêu cầu nhập lại.
  - Nhập số thập phân: Hệ thống báo lỗi và yêu cầu nhập lại.
  - Nhập chuỗi rỗng hoặc vượt khoảng giới hạn: Báo lỗi và bắt nhập lại.

## 2. Algorithm Logic (Logic thuật toán tìm kiếm)
- **Không tìm thấy:** Nếu trong mảng không có phần tử nào khớp với giá trị cần tìm -> In ra thông báo `Can not found`.
- **Tìm thấy 1 phần tử:** Nếu mảng chỉ có đúng một phần tử khớp -> In ra duy nhất 1 vị trí đó.
- **Tìm thấy nhiều phần tử:** Nếu mảng chứa nhiều phần tử khớp -> In ra tất cả các vị trí tìm thấy.
- **Biên đầu mảng:** Nếu phần tử cần tìm nằm ở vị trí đầu tiên -> In ra vị trí `0`.
- **Biên cuối mảng:** Nếu phần tử cần tìm nằm ở vị trí cuối cùng -> In ra vị trí `n - 1`.
