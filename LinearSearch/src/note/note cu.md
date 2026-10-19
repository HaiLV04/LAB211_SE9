yêu cầu người dùng nhập vào bàn phím 2 số: số phần tử của mảng và số cần tìm kiếm
 - input1: nhập số phần tử mảng (n) (n > 0)
 - input2: nhập giá trị cần tìm kiếm (search number)
 -> sau đó sẽ tạo 1 mảng với n phần tử, các phần tử là số nguyên ngẫu nhiên từ 0 đến n
 - gọi hàm tìm phần tử cần tìm kiếm (search number) trong mảng vừa tạo
 - in ra màn hình tất cả các chỉ số (index) của giá trị cần tìm kiếm (nếu tìm thấy)
 người dùng đã nhập ở bước trước đó.

 -Hàm tìm kiếm tuần tự (linear search):
 tìm kiếm bắt đầu từ phần tử đầu tiên của mảng, so sánh phần tử đó với giá trị
 cần tìm kiếm. Nếu khớp, ghi lại vị trí (index) của phần tử đó, nếu không khớp thì bỏ qua. Quá trình tiếp tục đến khi
 hết mảng. Kết quả trả về mảng ghi lại tất cả vị trí của search number, nếu không tìm thấy thì trả về mảng rỗng.

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
 + nếu trong mảng có nhiều phần tử cần tìm thì in ra tất cả vị trí đó
 + nếu phần tử cần tìm ở vị trí đầu mảng in ra 0
 + nếu phần tử cần tìm ở vị trí cuối cùng thì in ra (số lượng mảng -1)
