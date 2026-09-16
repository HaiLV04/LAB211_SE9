# I. Yêu cầu chương trình (Requirement)
- Người dùng nhập vào một số nguyên dương `n` (kích thước mảng).
- Chương trình tự động sinh ra một mảng gồm `n` phần tử với các giá trị nguyên ngẫu nhiên.
- Hiển thị mảng trước khi sắp xếp.
- Sử dụng thuật toán Bubble Sort (sắp xếp nổi bọt) để sắp xếp mảng theo thứ tự tăng dần.
- Hiển thị mảng sau khi đã sắp xếp.

# II. Giải thích thuật toán (Algorithm Explanation)
Thuật toán sắp xếp nổi bọt (Bubble Sort) hoạt động như sau:
1. Duyệt qua mảng nhiều lần (tối đa `n - 1` lần).
2. Tại mỗi lần duyệt, so sánh liên tiếp từng cặp phần tử kề nhau.
3. Nếu phần tử đứng trước lớn hơn phần tử đứng sau, thực hiện đổi chỗ (swap) chúng cho nhau.
4. Quá trình này giúp phần tử lớn nhất "nổi" dần về cuối mảng sau mỗi lần duyệt.
5. Lặp lại quá trình với các phần tử còn lại cho đến khi không còn cặp nào cần đổi chỗ (mảng đã được sắp xếp hoàn chỉnh).
*Tối ưu:* Dùng một cờ (flag) `swapped` để kiểm tra. Nếu trong một vòng duyệt không có sự đổi chỗ nào, thuật toán sẽ dừng sớm.

# III. Test Cases

## 1. Kiểm tra dữ liệu đầu vào (Input Validation)
| Input | Mô tả | Kết quả mong đợi |
|-------|-------|------------------|
| `8` | Số nguyên dương hợp lệ | Chương trình khởi tạo mảng kích thước 8 và tiến hành sắp xếp |
| `-5` | Số nguyên âm | Báo lỗi và bắt nhập lại |
| `0` | Số không | Báo lỗi và bắt nhập lại |
| `5.6` | Số thập phân | Báo lỗi định dạng và bắt nhập lại |
| `abc` | Ký tự chữ cái | Báo lỗi định dạng và bắt nhập lại |
| ` ` (Trống) | Không nhập gì | Báo lỗi định dạng và bắt nhập lại |

## 2. Kiểm tra thuật toán (Algorithm Test)
| Test Case | Kích thước mảng | Kết quả mong đợi |
|-----------|-----------------|------------------|
| Mảng 1 phần tử | `n = 1` | Mảng không thay đổi, in ra đúng phần tử đó. Không xảy ra lỗi |
| Mảng nhiều phần tử | `n > 1` | Mảng in ra trước sắp xếp lộn xộn. Mảng in ra sau sắp xếp có thứ tự tăng dần chính xác |
| Mảng đã sắp xếp | Mảng có thứ tự sẵn | Thuật toán dừng sớm nhờ biến `swapped`, kết quả in ra không đổi |
