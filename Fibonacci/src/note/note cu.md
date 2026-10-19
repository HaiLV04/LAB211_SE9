yêu cầu người dùng nhập vào bàn phím số lượng phần tử trong dãy Fibonacci cần in
 (Tuy nhiên, Assignment đã cố định là 45 số)

 Hàm tính số Fibonacci bằng Đệ quy (Recursion)
 Dãy Fibonacci là dãy số bắt đầu bằng 0, 1.
 Số tiếp theo được tìm thấy bằng cách cộng hai số đứng trước nó.

 Hàm đệ quy được định nghĩa:
 - F(n) = 0, nếu n = 0.
 - F(n) = 1, nếu n = 1.
 - F(n) = F(n-1) + F(n-2), nếu n > 1.

 - Hàm sẽ được gọi tuần tự từ F(0) đến F(44) để in ra 45 số.
 - In ra màn hình các số Fibonacci theo thứ tự, cách nhau bởi dấu phẩy và khoảng trắng.

 2. test case
 - Trường hợp cơ sở (Base Cases):
 + Tính F(0) phải trả về 0.
 + Tính F(1) phải trả về 1.

 - Trường hợp đệ quy (Recursive Cases):
 + Tính F(2) = F(1) + F(0) = 1....
 + Đảm bảo kết quả in ra là 45 số. số thứ 45 phải là 701408733.
