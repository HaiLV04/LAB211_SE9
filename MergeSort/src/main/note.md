# I. Yêu cầu chương trình (Requirement)
- Người dùng nhập vào một số nguyên dương `n` (kích thước mảng).
- Chương trình tự động sinh ra một mảng gồm `n` phần tử với các giá trị nguyên ngẫu nhiên.
- Hiển thị mảng trước khi sắp xếp.
- Sử dụng thuật toán Merge Sort (sắp xếp trộn) để sắp xếp mảng theo thứ tự tăng dần.
- Hiển thị mảng sau khi đã sắp xếp.

# II. Giải thích thuật toán (Algorithm Explanation)
Thuật toán Merge Sort hoạt động theo tư tưởng Chia để trị (Divide and Conquer):
1. **Chia (Divide):** Liên tục chia mảng thành 2 nửa (dựa vào vị trí giữa `middle`) cho đến khi mỗi mảng con chỉ còn 1 phần tử hoặc rỗng. Lúc này mảng con được coi là đã sắp xếp.
2. **Trị (Conquer) - Trộn (Merge):** Bắt đầu gộp (merge) các mảng con lại với nhau:
   - Tạo 2 mảng tạm chứa dữ liệu của nửa bên trái và nửa bên phải.
   - Dùng 2 con trỏ duyệt qua 2 mảng tạm. So sánh phần tử: nếu phần tử mảng trái nhỏ hơn hoặc bằng mảng phải thì đưa phần tử mảng trái vào mảng gốc, ngược lại đưa phần tử mảng phải vào.
   - Khi một trong 2 mảng tạm đã duyệt hết, tiến hành chép toàn bộ các phần tử còn lại của mảng kia vào mảng gốc.
3. Quá trình trộn lặp lại đệ quy từ dưới lên trên cho đến khi toàn bộ mảng ban đầu được trộn và sắp xếp hoàn chỉnh.

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
| Mảng 1 phần tử | `n = 1` | Mảng giữ nguyên, thuật toán không cần chia hay trộn. Không xảy ra lỗi |
| Mảng nhiều phần tử | `n > 1` | Mảng được chia thành các mảng con, trộn lại theo thứ tự tăng dần và in ra kết quả chính xác |
| Mảng chẵn phần tử | `n = 6` | Chia đều 2 nửa (3-3), quá trình trộn diễn ra bình thường |
| Mảng lẻ phần tử | `n = 7` | Chia lệch (VD: 3-4), quá trình đệ quy và trộn xử lý chính xác |
