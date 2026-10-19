Yêu cầu
Yêu cầu người dùng nhập vào số nguyên dương n đại diện cho kích thước mảng.
Random ngẫu nhiên các phần tử của mảng trong khoảng -n → n.
In ra mảng trước khi sắp xếp.
Sử dụng thuật toán Merge Sort (sắp xếp trộn ) để sắp xếp mảng theo thứ tự tăng dần.
In ra mảng sau khi sắp xếp.

Giải thích thuật toán
Thuật toán Merge Sort hoạt động theo tư tưởng Chia để trị (Divide and Conquer).
Thuật toán liên tục chia mảng thành hai nửa bằng cách xác định vị trí phần tử ở giữa (middle) cho đến khi mỗi mảng con chỉ còn 1 phần tử hoặc rỗng.
Sau khi quá trình chia hoàn tất, thuật toán bắt đầu trộn (Merge) các mảng con đã được sắp xếp.
Khi trộn, thuật toán tạo 2 mảng tạm chứa dữ liệu của nửa bên trái và nửa bên phải.
Sử dụng 2 con trỏ để lần lượt duyệt hai mảng tạm:
Nếu phần tử bên trái nhỏ hơn hoặc bằng phần tử bên phải thì đưa phần tử bên trái vào mảng gốc.
Ngược lại đưa phần tử bên phải vào mảng gốc.
Khi một trong hai mảng tạm đã duyệt hết, thuật toán sao chép toàn bộ các phần tử còn lại của mảng kia vào mảng gốc.
Sau khi hoàn thành việc trộn, hai mảng con sẽ trở thành một mảng đã được sắp xếp.
Thuật toán tiếp tục trộn từ các mảng nhỏ lên các mảng lớn hơn cho đến khi toàn bộ mảng ban đầu được sắp xếp.

Kiểm tra dữ liệu nhập
Test Case	 Input	Kết quả mong đợi
Số nguyên dương	 8	Chương trình tạo mảng và sắp xếp
Số âm            -5	Báo lỗi và yêu cầu nhập lại
Số 0             0	Báo lỗi và yêu cầu nhập lại
Số thực          5.6	Báo lỗi và yêu cầu nhập lại
Khoảng trắng	 " "	Báo lỗi và yêu cầu nhập lại
Ký tự            abc	Báo lỗi và yêu cầu nhập lại

Test Case	Mô tả	Kết quả mong đợi
Mảng có 1 phần tử	n = 1	Mảng giữ nguyên, không cần chia hoặc trộn
Mảng có nhiều phần tử	n > 1	Mảng được chia thành các mảng con, sau đó trộn lại theo thứ tự tăng dần và in ra kết quả chính xác