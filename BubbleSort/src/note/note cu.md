yêu cầu người dùng nhập vào bàn phím số phần tử của mảng 
 - input: nhập số phần tử mảng (n) (n > 0)
 -> sau đó sẽ tạo 1 mảng với n phần tử, các phần tử là số nguyên ngẫu nhiên từ -n đến n
 - in ra màn hình mảng trước khi sắp xếp
 - gọi hàm sắp xếp tăng dần theo thuật toán bubble sort
 - in ra màn hình mảng sau khi sắp xếp

- Thuật toán sắp xếp nổi bọt (Bubble Sort): Duyệt qua mảng nhiều lần, 
tại mỗi lần duyệt sẽ so sánh cặp phần tử liền kề nhau. 
Nếu phần tử đứng trước lớn hơn phần tử đứng sau, thực hiện đổi chỗ (swap) chúng. 
Quá trình này khiến phần tử lớn nhất "nổi" dần về cuối mảng. 
Lặp lại quá trình với các phần tử còn lại cho đến khi không còn cặp nào cần đổi chỗ, 
mảng sẽ được sắp xếp hoàn chỉnh.

 2. test case
 - nhập n sai
 + bỏ trống: (in ra lỗi và bắt nhập lại)
 + nhập số nguyên âm (in ra lỗi và bắt nhập lại)
 + nhập số 0 (in ra lỗi và bắt nhập lại)
 + nhập chữ cái (in ra lỗi và bắt nhập lại)
 + nhập số thập phân (in ra lỗi và bắt nhập lại)
 + nhập số không phải là số nguyên (in ra lỗi và bắt nhập lại)
 + nhập n hợp lệ -> in ra mảng trước khi sắp xếp và sau khi sắp xếp bằng bubble sort.
