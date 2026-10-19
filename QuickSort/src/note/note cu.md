Yeu cau:
Yêu cầu người dùng nhập một số nguyên dương n đại diện cho kích thước mảng.
Sinh ngẫu nhiên n phần tử trong khoảng từ -n đến n.
Hiển thị mảng trước khi sắp xếp.
Sử dụng thuật toán Quick Sort để sắp xếp mảng theo thứ tự tăng dần.
Hiển thị mảng sau khi sắp xếp.

Giải thích thuật toán

Thuật toán Quick Sort hoạt động theo tư tưởng Chia để trị (Divide and Conquer).
Thuật toán chọn một phần tử ở giữa mảng làm điểm chốt (Pivot) để chia mảng thành hai phần.
Sau đó, thuật toán thiết lập hai con trỏ:
Con trỏ i bắt đầu từ đầu mảng và di chuyển sang phải.
Con trỏ j bắt đầu từ cuối mảng và di chuyển sang trái.
Con trỏ i tiếp tục di chuyển cho đến khi gặp phần tử lớn hơn hoặc bằng Pivot.
Con trỏ j tiếp tục di chuyển cho đến khi gặp phần tử nhỏ hơn hoặc bằng Pivot.
Nếu i ≤ j, thuật toán hoán đổi hai phần tử tại vị trí i và j, sau đó tiếp tục tăng i và giảm j.
Quá trình này lặp lại cho đến khi hai con trỏ gặp nhau hoặc vượt qua nhau (i > j). Khi đó, mảng được chia thành hai phần:
- Các phần tử bên trái nhỏ hơn hoặc bằng Pivot.
- Các phần tử bên phải lớn hơn hoặc bằng Pivot.
Sau khi hoàn thành việc phân chia (Partition), thuật toán tiếp tục gọi đệ quy để sắp xếp hai phần của mảng.
Quá trình này được lặp lại cho đến khi mỗi mảng con chỉ còn một phần tử hoặc rỗng. Khi đó toàn bộ mảng đã được sắp xếp theo thứ tự tăng dần.

Kiểm tra dữ liệu nhập
Test Case	 Input	Kết quả mong đợi
Số nguyên dương	 8	Chương trình tạo mảng ngẫu nhiên và sắp xếp
Số âm	         -5	Báo lỗi và yêu cầu nhập lại
Số 0             0	Báo lỗi và yêu cầu nhập lại
Số thực          5.6	Báo lỗi và yêu cầu nhập lại
Khoảng trắng	 " "	Báo lỗi và yêu cầu nhập lại
Ký tự            abc	Báo lỗi và yêu cầu nhập lại

Test Case
Test Case	        Mô tả	Kết quả mong đợi
Mảng có 1 phần tử	n = 1	Mảng giữ nguyên vì chỉ có một phần tử, không cần phân chia hoặc hoán đổi
Mảng có nhiều phần tử	n > 1	Mảng được chia thành các phân vùng theo Pivot, thực hiện hoán đổi các phần tử cần thiết, sau đó tiếp tục sắp xếp đệ quy cho đến khi toàn bộ mảng được sắp xếp theo thứ tự tăng dần
