# Yêu Cầu Bài Toán
1. **Input 1**: Số lượng phần tử của mảng $n$ ($n > 0$).
2. Hệ thống tự động sinh ngẫu nhiên một mảng gồm $n$ số nguyên từ $0$ đến $n - 1$.
3. Hiển thị mảng vừa tạo ra màn hình console: `The array: [v1, v2, ...]`.
   *(Lưu ý: Thuật toán Linear Search tìm kiếm tuần tự trên mảng ngẫu nhiên gốc, **hoàn toàn không cần sắp xếp** mảng trước khi tìm).*
4. **Input 2**: Giá trị cần tìm kiếm (`Enter search value:`).
5. Gọi hàm tìm kiếm tuần tự để tìm và in ra màn hình tất cả các vị trí (index) của giá trị đó nếu tìm thấy, ngược lại in thông báo `Can not found`.

# Giải Thích Thuật Toán
**Tìm kiếm tuần tự (Linear Search):**
Thuật toán duyệt bắt đầu từ phần tử đầu tiên của mảng ($i = 0$) đến phần tử cuối cùng ($i = n - 1$). Ở mỗi bước, so sánh phần tử hiện tại với giá trị cần tìm kiếm:
- Nếu khớp: ghi nhận chỉ số $i$ vào danh sách kết quả.
- Nếu không khớp: tiếp tục duyệt phần tử tiếp theo.

Quá trình lặp lại cho đến khi duyệt hết toàn bộ mảng. Kết quả trả về là một mảng ghi lại tất cả các vị trí xuất hiện của giá trị cần tìm, hoặc mảng rỗng nếu không tìm thấy.

**Độ phức tạp thời gian:** $O(n)$ vì luôn duyệt qua từng phần tử để tìm tất cả các vị trí trùng lặp.
**Độ phức tạp không gian:** $O(k)$ với $k$ là số lượng phần tử tìm thấy ($k \le n$).

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
- **Tìm thấy 1 phần tử:** Nếu mảng chỉ có đúng một phần tử khớp -> In ra `Found {searchValue} at index: [index]`.
- **Tìm thấy nhiều phần tử:** Nếu mảng chứa nhiều phần tử khớp -> In ra tất cả các vị trí tìm thấy `Found {searchValue} at index: [index1, index2, ...]`.
- **Biên đầu mảng:** Nếu phần tử cần tìm nằm ở vị trí đầu tiên -> Mảng kết quả chứa vị trí `0`.
- **Biên cuối mảng:** Nếu phần tử cần tìm nằm ở vị trí cuối cùng -> Mảng kết quả chứa vị trí `n - 1`.
