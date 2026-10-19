# I. Yêu cầu chương trình (Requirement)
- Người dùng nhập vào một số nguyên dương `n` (kích thước mảng).
- Chương trình tự động sinh ra một mảng gồm `n` phần tử với các giá trị nguyên ngẫu nhiên.
- Hiển thị mảng trước khi sắp xếp.
- Sử dụng thuật toán Quick Sort (sắp xếp nhanh) để sắp xếp mảng theo thứ tự tăng dần.
- Hiển thị mảng sau khi đã sắp xếp.

# II. Giải thích thuật toán (Algorithm Explanation)
Thuật toán Quick Sort hoạt động theo tư tưởng Chia để trị (Divide and Conquer):
1. **Chọn điểm chốt (Pivot):** Chọn một phần tử ở giữa mảng làm Pivot để chia mảng thành hai phần.
2. **Phân chia (Partition):**
   - Thiết lập 2 con trỏ: `i` bắt đầu từ đầu mảng (chạy sang phải), `j` bắt đầu từ cuối mảng (chạy sang trái).
   - Con trỏ `i` tiến lên cho đến khi gặp phần tử >= Pivot.
   - Con trỏ `j` lùi lại cho đến khi gặp phần tử <= Pivot.
   - Nếu `i <= j`, tiến hành hoán đổi (swap) vị trí của hai phần tử tại `i` và `j`, sau đó tăng `i` và giảm `j`.
   - Lặp lại đến khi hai con trỏ vượt qua nhau (`i > j`). Lúc này mảng được chia làm 2 nửa: nửa trái chứa các phần tử <= Pivot, nửa phải chứa các phần tử >= Pivot.
3. **Đệ quy (Recursion):** Gọi đệ quy Quick Sort để tiếp tục sắp xếp nửa bên trái và nửa bên phải. Quá trình lặp lại cho đến khi mảng con chỉ còn 1 phần tử.

# III. Test Cases

## 1. Kiểm tra dữ liệu đầu vào (Input Validation)
| Input | Mô tả | Kết quả mong đợi |
|-------|-------|------------------|
| `8` | Số nguyên dương hợp lệ | Chương trình khởi tạo mảng kích thước 8 và tiến hành sắp xếp |
| `-5` | Số nguyên âm | Báo lỗi (Invalid / Error range) và bắt nhập lại |
| `0` | Số không | Báo lỗi và bắt nhập lại |
| `5.6` | Số thập phân | Báo lỗi định dạng và bắt nhập lại |
| `abc` | Ký tự chữ cái | Báo lỗi định dạng và bắt nhập lại |
| ` ` (Trống) | Không nhập gì | Báo lỗi định dạng và bắt nhập lại |

## 2. Kiểm tra thuật toán (Algorithm Test)
| Test Case | Kích thước mảng | Kết quả mong đợi |
|-----------|-----------------|------------------|
| Mảng 1 phần tử | `n = 1` | Mảng giữ nguyên, không cần phân chia (Partition) hay hoán đổi. Không xảy ra lỗi |
| Mảng nhiều phần tử | `n > 1` | Mảng được chia thành các phân vùng quanh Pivot, thực hiện hoán đổi chính xác và đệ quy đến khi mảng tăng dần |
| Tất cả phần tử bằng nhau | (VD: mảng toàn số 2) | Con trỏ `i` và `j` di chuyển và hoán đổi liên tục, thuật toán chia đều mảng và không bị lặp vô hạn |
