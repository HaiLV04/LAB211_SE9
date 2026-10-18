# Yêu cầu bài toán (Requirement)
Yêu cầu người dùng nhập vào bàn phím 2 số: số lượng phần tử của mảng và giá trị cần tìm kiếm.
- **Input 1**: Số phần tử của mảng (`n`), với `n > 0`.
- **Input 2**: Giá trị cần tìm kiếm (search number).

Sau đó chương trình sẽ:
- Tạo một mảng gồm `n` phần tử, các phần tử là số nguyên được sinh ngẫu nhiên.
- Gọi hàm tìm kiếm nhị phân để tìm tất cả các vị trí (index) của giá trị yêu cầu trong mảng vừa tạo.
- In ra màn hình tất cả các vị trí (index) của giá trị cần tìm nếu thấy dạng danh sách mảng, ngược lại thông báo không tìm thấy.

# Giải thích thuật toán (Algorithm Explanation)
**Lưu ý quan trọng**: Tìm kiếm nhị phân (Binary Search) chỉ hoạt động trên mảng đã được sắp xếp (trong trường hợp này là sắp xếp tăng dần).
Thuật toán tìm kiếm nhị phân bắt đầu bằng cách so sánh phần tử ở giữa (`mid`) của mảng với giá trị cần tìm:
1. Nếu giá trị cần tìm khớp với phần tử giữa, trả về chỉ mục (index) của phần tử đó.
2. Nếu giá trị cần tìm nhỏ hơn phần tử giữa, thuật toán tiếp tục tìm kiếm ở nửa bên trái của mảng.
3. Nếu giá trị cần tìm lớn hơn phần tử giữa, thuật toán tiếp tục tìm kiếm ở nửa bên phải của mảng.

Quá trình này được lặp lại cho đến khi tìm thấy phần tử cần tìm hoặc phạm vi tìm kiếm trở nên rỗng (`left > right`). Nếu không tìm thấy, thuật toán trả về `-1`.

# Các trường hợp kiểm thử (Test Cases)

## 1. Kiểm thử đầu vào (Input validation)
- Nhập `n` sai:
  - Nhập số nguyên âm: yêu cầu nhập lại.
  - Nhập số `0`: yêu cầu nhập lại.
  - Nhập chữ cái: yêu cầu nhập lại.
  - Nhập số thập phân: yêu cầu nhập lại.
  - Nhập giá trị không phải là số nguyên: yêu cầu nhập lại.

## 2. Kiểm thử logic thuật toán (Algorithm logic)
- Nếu trong mảng không có phần tử nào khớp với giá trị cần tìm: in ra màn hình thông báo không tìm thấy (`Can not found`).
- Nếu trong mảng có duy nhất 1 phần tử khớp với giá trị cần tìm: in ra danh sách chứa đúng vị trí (index) đó, ví dụ `[2]`.
- Nếu trong mảng có nhiều phần tử khớp với giá trị cần tìm: in ra tất cả các vị trí tìm thấy theo thứ tự tăng dần, ví dụ `[2, 3, 4]`.
- Nếu phần tử cần tìm nằm ở vị trí đầu mảng: mảng kết quả chứa vị trí `0`.
- Nếu phần tử cần tìm nằm ở vị trí cuối mảng: mảng kết quả chứa vị trí `(số lượng phần tử - 1)`.
