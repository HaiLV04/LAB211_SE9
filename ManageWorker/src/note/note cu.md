I. Mục tiêu chương trình

Xây dựng chương trình Worker Management để quản lý thông tin công nhân và lịch sử thay đổi lương.
Chương trình cho phép người dùng thực hiện các chức năng:

Thêm công nhân mới vào hệ thống.
Tăng lương cho công nhân.
Giảm lương cho công nhân.
Lưu lại lịch sử thay đổi lương.
Hiển thị thông tin lịch sử thay đổi lương.

- Thông tin của Worker cần quản lý
Điều kiện dữ liệu:
ID phải bắt đầu bằng ký tự W và theo sau là chữ số.
Name chỉ chứa chữ cái và khoảng trắng.
Age nằm trong khoảng từ 18 đến 50.
Salary phải lớn hơn 0.
Work Location chỉ chứa chữ cái, số và khoảng trắng.

- Các chức năng yêu cầu
Function 1: Add Worker
Cho phép thêm một công nhân mới.
Yêu cầu:
Nhập đầy đủ thông tin Worker.
Kiểm tra dữ liệu đầu vào.
Không cho phép Worker có ID bị trùng.
Nếu thêm thành công, Worker được lưu vào danh sách quản lý.

Function 2: Up Salary
Cho phép tăng lương cho Worker.
Yêu cầu:
Worker phải tồn tại trong hệ thống.
Amount phải lớn hơn 0.
Sau khi tăng lương:
Cập nhật salary mới.
Tạo một bản ghi SalaryHistory.

Function 3: Down Salary
Cho phép giảm lương cho Worker.
Yêu cầu:
Worker phải tồn tại.
Amount phải lớn hơn 0.
Salary sau khi giảm không được nhỏ hơn 0.
Lưu lại lịch sử giảm lương.

Function 4: Display Information Salary
Hiển thị lịch sử thay đổi lương.
Yêu cầu:
Hiển thị thông tin:
Code
Name
Age
Salary
Status
Date
Sắp xếp lịch sử theo ID Worker.

Function 5: Exit
Thoát chương trình.

II. Giải thích tổng quát chương trình
1. Ý tưởng chương trình

Chương trình quản lý Worker bằng cách sử dụng các lớp:

Worker: lưu thông tin công nhân.
SalaryHistory: lưu lịch sử thay đổi lương.
ManagerWorker: quản lý danh sách Worker.
ManagerSalaryHistory: quản lý danh sách lịch sử.
Controller: xử lý yêu cầu từ người dùng.
Main: hiển thị menu và chạy chương trình


III. Kiểm tra hợp lệ dữ liệu đầu vào
1. Worker ID (Dùng Regex [Ww]\d+):
Hợp lệ: Nhập chuỗi bắt đầu bằng chữ 'W' hoặc 'w' theo sau là các chữ số (Ví dụ: W1, w100). Chương trình sẽ tự động chuyển thành chữ hoa (W1).
Ngoại lệ: Nhập sai định dạng (Ví dụ: ABC, 123, W_1) → Hệ thống báo "Invalid!" và bắt nhập lại.
Test case:
- Input: W1
  Expected: Hợp lệ, tạo Worker với ID W1.
- Input: w100
  Expected: Hợp lệ, tự động chuyển thành W100.
- Input: ABC, 123, W_1, W@
  Expected: Báo "Invalid!" và yêu cầu nhập lại.
- Input: W0
  Expected: Hợp lệ vì Regex chỉ kiểm tra W + chữ số.

2. Name (Dùng Regex [A-Za-z\s]+):
Hợp lệ: Nhập chuỗi chỉ chứa chữ cái và khoảng trắng (Ví dụ: Nguyen Van A).
Ngoại lệ: Nhập chuỗi chứa số hoặc ký tự đặc biệt (Ví dụ: An123, Binh@) → Hệ thống báo "Invalid!" và bắt nhập lại.
Test case:
- Input: Nguyen Van A
  Expected: Hợp lệ.
- Input: John Smith
  Expected: Hợp lệ.
- Input: An123, Binh@, John1
  Expected: Báo "Invalid!" và nhập lại.
- Input: 12345
  Expected: Không hợp lệ.

3. Age (Dùng Range [18, 50]):
Hợp lệ: Nhập số nguyên trong phạm vi từ 18 đến 50.
Ngoại lệ: Nhập số < 18 hoặc > 50 hoặc không phải số → Hệ thống báo "age >= 18 and <=50 !" và bắt nhập lại.
Test case:
- Input: 18
  Expected: Hợp lệ.
- Input: 50
  Expected: Hợp lệ.
- Input: 17, 51
  Expected: Không hợp lệ.
- Input: abc
  Expected: Báo lỗi nhập số.

4. Salary / Amount (Dùng Range Double.MIN_VALUE -> MAX_VALUE và kiểm tra logic > 0):
Hợp lệ: Nhập số thực lớn hơn 0.
Ngoại lệ: Nhập số <= 0 hoặc không phải số → Hệ thống ném Exception/Báo lỗi: "salary must be > 0" và bắt nhập lại.
Test case:
- Input: 1000
  Expected: Hợp lệ.
- Input: 0.5
  Expected: Hợp lệ.
- Input: 0, -100, abc
  Expected: Báo "salary must be > 0".
Boundary test:
- Input: 0
  Expected: Fail.
- Input: Double.MIN_VALUE
  Expected: Pass.

5. Work Location (Dùng Regex [A-Za-z0-9\s]+):
Hợp lệ: Nhập chuỗi chứa chữ cái, số và khoảng trắng (Ví dụ: Hanoi Shop 1).
Ngoi lệ: Nhập ký tự đặc biệt (Ví dụ: Hanoi#1) → Hệ thống báo "Invalid!" và bắt nhập lại.
Test case:
- Input: Hanoi
  Expected: Hợp lệ.
- Input: Hanoi Shop 1
  Expected: Hợp lệ.
- Input: Hanoi#1, Factory@
  Expected: Báo "Invalid!".


Các giá trị biên cần kiểm tra:
Age:
- 17 -> Fail
- 18 -> Pass
- 50 -> Pass
- 51 -> Fail
Salary:
- -1 -> Fail
- 0 -> Fail
- >0 -> Pass
Amount:
- -1 -> Fail
- 0 -> Fail
- >0 -> Pass

IV. Chức năng Add Worker (Option 1)
Test Case 1: Add Worker thành công
Input:
W01 | John | 25 | 1000 | Hanoi
Expected:
Add success
Worker:
W01 | John | 25 | 1000 | Hanoi

Test Case 2: ID chữ thường
Input:
w02 | David | 30 | 2000 | HCM
Expected:
W02 | David | 30 | 2000 | HCM
(ID được chuyển thành chữ hoa)

Test Case 3: Trùng ID
Danh sách hiện có:
W01 | John | 25 | 1000 | Hanoi
Input:
W01
Expected:
Worker with ID W01 already exists. Please input again

Test Case 4: Age không hợp lệ
Input:
W03 | Tom | 17 | 1500 | Da Nang
Expected:
age >= 18 and <=50 !

Test Case 5: Salary không hợp lệ
Input:
W04 | Alex | 25 | 0 | Hanoi
Expected:
salary must be > 0

V. Chức năng Up Salary (Option 2)
Test Case 1: Tăng lương thành công
Worker hiện tại:
W01 | John | 25 | 1000 | Hanoi
Input:
Code = W01
Amount = 500
Expected:
Salary mới:
W01 | John | 25 | 1500 | Hanoi
History:
W01 | John | 25 | 1500 | UP

Test Case 2: Danh sách rỗng
Input:
Option 2
Expected:
List is empty!

Test Case 3: ID không tồn tại
Input:
Code = W99
Expected:
Can not found code!

Test Case 4: Amount = 0
Input:
Amount = 0
Expected:
salary must be > 0

Test Case 5: Amount âm
Input:
Amount = -100
Expected:
salary must be > 0

VI. Chức năng Down Salary (Option 3)
Test Case 1: Giảm lương thành công
Worker hiện tại:
W01 | John | 25 | 5000 | Hanoi
Input:
Amount = 2000
Expected:
W01 | John | 25 | 3000 | Hanoi
History:
W01 | John | 25 | 3000 | DOWN

Test Case 2: Giảm bằng lương hiện tại
Worker hiện tại:
W01 | John | 25 | 1000 | Hanoi
Input:
Amount = 1000
Expected:
W01 | John | 25 | 0 | Hanoi
History:
W01 | John | 25 | 0 | DOWN

Test Case 3: Amount lớn hơn lương hiện tại
Worker hiện tại:
W01 | John | 25 | 1000 | Hanoi
Input:
Amount = 1500
Expected:
Amount must be smaller than current salary (1000.0)!
Yêu cầu nhập lại.

Test Case 4: ID không tồn tại
Input:
Code = W99
Expected:
Can not found code!

Test Case 5: Danh sách rỗng
Expected:
List is empty!

VII. Chức năng Display Information Salary (Option 4)
Test Case 1: Hiển thị lịch sử
History:
W01 | John | 25 | 1500 | UP
W02 | David | 30 | 2500 | DOWN
Expected:
Code Name Age Salary Status Date
W01 John 25 1500 UP
W02 David 30 2500 DOWN

Test Case 2: Kiểm tra sắp xếp theo ID
History:
W03 | James | 40 | 2200 | DOWN
W01 | John | 25 | 1500 | UP
W02 | David | 30 | 2500 | DOWN

Expected:
W01 | John | 25 | 1500 | UP
W02 | David | 30 | 2500 | DOWN
W03 | James | 40 | 2200 | DOWN


Test Case 3: History rỗng
Expected:
History Salary is empty
Test Case 4: Trùng lịch sử
Input:
Thêm lại record đã tồn tại.

Expected:

This record history is existed!!!

VIII. Boundary Value Analysis
Age
Value	Result
17	Fail
18	Pass
19	Pass
49	Pass
50	Pass
51	Fail
Salary
Value	Result
-1	Fail
0	Fail
0.1	Pass
1000	Pass
Amount
Value	Result
-100	Fail
0	Fail
0.1	Pass
Current Salary	Pass
Current Salary + 1	Fail (Down Salary)
