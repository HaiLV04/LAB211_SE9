yêu cầu người dùng nhập vào bàn phím 2 số: số phần tử của mảng và số cần tìm kiếm
 - input1: nhập số phần tử mảng (n) (n > 0)
 - input2: nhập giá trị cần tìm kiếm (search number)
 -> sau đó sẽ tạo 1 mảng với n phần tử, các phần tử là số nguyên ngẫu nhiên từ 0 đến n
 - gọi hàm tìm phần tử cần tìm kiếm (search number) trong mảng vừa tạo
 - in ra màn hình chỉ số (index) của giá trị cần tìm kiếm (nếu tìm thấy)
 người dùng đã nhập ở bước trước đó.

 -Hàm tìm kiếm nhị phân (Binary search):
Lưu ý quan trọng: Binary Search chỉ hoạt động trên mảng đã sắp xếp (ở đây chọn sắp xếp tăng dần)
Tìm kiếm nhị phân bắt đầu bằng cách so sánh phần tử ở giữa (mid) của mảng với giá trị cần tìm.
 1. Nếu giá trị cần tìm khớp với phần tử giữa, trả về chỉ mục (index) của nó.
 2. Nếu giá trị cần tìm nhỏ hơn phần tử giữa, thuật toán tiếp tục tìm kiếm ở nửa bên trái của mảng.
 3. Nếu giá trị cần tìm lớn hơn phần tử giữa, thuật toán tiếp tục tìm kiếm ở nửa bên phải của mảng.
 Quá trình này lặp lại cho đến khi tìm thấy phần tử hoặc phạm vi tìm kiếm trở nên rỗng (left > right).
 Nếu không tìm thấy, trả về -1
 - in ra màn hình vị trí (index) của giá trị tìm kiếm được tìm thấy hoặc
 thông báo không tìm thấy

 2. test case
 - nhập n sai
 + nhập số nguyên âm (bắt nhập lại)
 + nhập số 0 (bắt nhập lại)
 + nhập chữ cái (bắt nhập lại)
 + nhập số thập phân (bắt nhập lại)
 + nhập số không phải là số nguyên (bắt nhập lại)
 - Tìm kiếm (search):
 + nếu trong mảng không tìm thấy phần tử nào in ra Can not found
 + nếu trong mảng chỉ có 1 phần tử cần tìm, in ra 1 vị trí đó
 + nếu trong mảng có nhiều phần tử cần tìm thì chỉ in ra được 1 vị trí theo binary search thôi.
 + nếu phần tử cần tìm ở vị trí đầu mảng in ra 0
 + nếu phần tử cần tìm ở vị trí cuối cùng thì in ra (số lượng mảng -1)

