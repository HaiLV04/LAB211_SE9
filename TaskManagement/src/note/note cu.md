Dưới đây là bản cập nhật toàn bộ tài liệu, trong đó tất cả các phần hiển thị danh sách công việc (Output của chức năng Display và Delete) đã được chuyển sang định dạng bảng Markdown (chứa các dấu |) như bạn yêu cầu để dễ nhìn và đồng bộ hơn.
Bạn có thể copy toàn bộ nội dung dưới đây:
I. YÊU CẦU CHƯƠNG TRÌNH
Xây dựng chương trình quản lý công việc (Task) bằng Java Console, áp dụng lập trình hướng đối tượng (OOP) và sử dụng ArrayList để lưu trữ dữ liệu.
Chương trình cung cấp menu chính gồm 4 chức năng:
1.	Add Task
2.	Delete task
3.	Display Task
4.	Exit
Chi tiết các chức năng:
•	Chức năng 1 - Add Task (Thêm công việc):
•	Cho phép người dùng nhập các thông tin: Requirement Name, Task Type, Date, Plan From, Plan To, Assignee, Reviewer.
• làm tăng giá trị trước khi gán cho Task mới.
ID đầu tiên luôn là 1.
Mỗi lần Add thành công, ID tăng thêm 1.
ID đã xóa sẽ không được sử dụng lại.
ID mới luôn bằng ID lớn nhất đã từng tạo + 1.
•	Requirement Name, Assignee, Reviewer: Chỉ cho phép nhập chữ cái, số và khoảng trắng (không chứa ký tự đặc biệt).
•	Task Type: Người dùng chọn từ 1 đến 4 (1. Code, 2. Test, 3. Design, 4. Review).
•	Date: Phải đúng định dạng dd-MM-yyyy và là một ngày có thực.
•	Plan From: Nằm trong khoảng từ 8.0 đến 17.0. Phần thập phân bắt buộc phải là .0 hoặc .5.
•	Plan To: Nằm trong khoảng từ (Plan From + 0.5) đến 17.5. Phần thập phân bắt buộc phải là .0 hoặc .5.
•	Không cho phép thêm một Task trùng lặp toàn bộ thông tin với một Task đã có trong danh sách. Nếu trùng, thông báo lỗi.
•	Chức năng 2 - Delete Task (Xóa công việc):
•	Yêu cầu nhập ID của công việc cần xóa.
•	Nếu ID tồn tại, xóa công việc đó khỏi danh sách và hiển thị thông tin công việc vừa xóa.
•	Nếu ID không tồn tại, ném ngoại lệ thông báo lỗi.
•	Chức năng 3 - Display Task (Hiển thị danh sách):
•	Hiển thị toàn bộ công việc trong danh sách.
•	Nếu danh sách trống, ném ngoại lệ thông báo danh sách trống.
•	Hiển thị theo định dạng cột (cách nhau bằng khoảng trắng): ID, Name, Task Type, Date, Time, Assignee, Reviewer.
•	Cột Time được tính toán tự động bằng công thức: Plan To - Plan From.
II. GIẢI THÍCH CHƯƠNG TRÌNH
•	Cấu trúc OOP: Chương trình chia thành các package rõ ràng: entity (chứa class Task, enum TaskType), bo (Business Object - xử lý logic danh sách và nhập liệu), controller (điều phối luồng), ui (giao diện Main), và utils (tiện ích kiểm tra dữ liệu).
•	Enum TaskType: Sử dụng Enum để cố định 4 loại công việc (Code, Test, Design, Review), giúp dễ dàng lấy ra tên chuỗi của TaskType dựa vào ID (1-4) lúc in ra màn hình.
•	Lớp Validator: Tập trung mọi hàm bắt lỗi nhập liệu (getInt, getDouble, getString, getDate). Sử dụng try-catch (NumberFormatException, ParseException) kết hợp vòng lặp while(true) để liên tục bắt người dùng nhập lại nếu nhập sai định dạng.
•	Xử lý Regex (Biểu thức chính quy): Sử dụng [A-Za-z0-9\s]+ để chặn người dùng nhập ký tự đặc biệt cho tên requirement, người giao, người nhận.
•	Logic Plan From/To: Code ép buộc người dùng phải nhập .0 hoặc .5 bằng cách cắt chuỗi theo dấu chấm split("\.") và kiểm tra phần tử thứ 1. Đồng thời khoảng giá trị của Plan To luôn phụ thuộc vào Plan From (min của To = From + 0.5) để đảm bảo thời gian bắt đầu luôn nhỏ hơn thời gian kết thúc.
•	Logic trùng lặp (isDuplicate): Quét qua toàn bộ danh sách, so sánh khớp 100% tất cả các thuộc tính: taskTypeID, requirementName, date, planFrom, planTo, assign, reviewer. Nếu khớp toàn bộ, hàm ném Exception("This task is existed!").
III. DỮ LIỆU MẪU
ID	Name	Task Type	Date	Time	Assignee	Reviewer
1	Dev Program	Code	26-06-2015	8.0	Dev	Lead
2	Test Login	Test	27-06-2015	4.0	Tester	QA
3	DB Design	Design	28-06-2015	3.5	Arch	Manager
4	Review Source	Review	29-06-2015	2.5	Leader	PM
5	Payment API	Code	30-06-2015	5.0	Nam	Hoa
6	UI Testing	Test	01-07-2015	3.0	Minh	An
7	Database Backup	Review	02-07-2015	1.5	Admin	Manager
8	API Refactor	Design	03-07-2015	6.0	Dev2	Lead2

IV. TEST CASE - CHỨC NĂNG ADD TASK
TC01 - Thêm Task thành công (Nhập liệu hợp lệ)
•	Input: Các trường dữ liệu hợp lệ (Dòng 1 dữ liệu mẫu).
•	Console Output:
========= Task program =========
1.	Add Task
2.	Delete task
3.	Display Task
4.	exit
Enter your choice: 1
------------Add Task------------
Requirement Name: Dev Program
1.Code
2.Test
3.Design
4.Review
Enter your choice: 1
Enter Date: 26-06-2015
From: 9.5
To: 17.5
Assignee: Dev
Reviewer: Lead
Add success task id: 1
TC02 - Lỗi ký tự đặc biệt ở Tên Requirement
•	Input: Nhập "Dev @Program"
•	Console Output:
------------Add Task------------
Requirement Name: Dev @Program
Invalid!
Requirement Name:
TC03 - Lỗi Task Type ngoài phạm vi
•	Input: Nhập Task Type là 5
•	Console Output:
1.	Code
2.	Test
3.	Design
4.	Review
Enter your choice: 5
Just be 1 -> 4
Enter your choice:
TC04 - Lỗi định dạng Date
•	Input: Nhập 2026/05/10
•	Console Output:
Enter Date: 2026/05/10
Invalid! Please enter format: dd-MM-yyyy
Enter Date:
TC05 - Lỗi Plan From ngoài phạm vi
•	Input: Nhập From là 7.0
•	Console Output:
From: 7.0
Just be 8 -> 17
From:
TC06 - Lỗi sai định dạng phần thập phân của Plan
•	Input: Nhập From là 8.3
•	Console Output:
From: 8.3
Must be x.0 or x.5
From:
TC07 - Lỗi Plan To nhỏ hơn hoặc bằng Plan From
•	Điều kiện: From đã nhập là 9.0.
•	Input: Nhập To là 8.0.
•	Console Output:
To: 8.0
Just be 9.5-> 17.5
To:
TC08 - Lỗi trùng lặp dữ liệu (Duplicate)
•	Điều kiện: Đã tồn tại Task "Dev Program" trong List.
•	Input: Nhập lại chính xác 100% các thông số của TC01.
•	Console Output:
Assignee: Dev
Reviewer: Lead
This task is existed!
========= Task program =========
V. TEST CASE - CHỨC NĂNG DELETE TASK
TC09 - Xóa thành công
•	Điều kiện: Đang có Task mang ID = 1.
•	Input: Chọn Option 2, nhập ID là 1.
•	Console Output:
------------Del Task------------
Task ID: 1
Delete success:
| ID | Name | Task Type | Date | Time | Assignee | Reviewer |
| -- | ----------- | --------- | ---------- | ---- | -------- | -------- |
| 1 | Dev Program | Code | 26-06-2015 | 8.0 | Dev | Lead |
TC10 - Xóa thất bại do ID không tồn tại
•	Input: Nhập ID là 99.
•	Console Output:
------------Del Task------------
Task ID: 99
Task ID does not exist!
TC11 - Nhập ID không phải là số
•	Input: Nhập ID là abc.
•	Console Output:
------------Del Task------------
Task ID: abc
Invalid!
Task ID:
VI. TEST CASE - CHỨC NĂNG DISPLAY TASK
TC12 - Hiển thị danh sách rỗng
•	Điều kiện: Vừa khởi chạy chương trình, chưa Add công việc nào.
•	Input: Chọn Option 3.
•	Console Output:
------------Show Task------------
This list is empty!
TC13 - Hiển thị danh sách thành công
•	Điều kiện: Đã add thành công 2 task đầu tiên của dữ liệu mẫu.
•	Input: Chọn Option 3.
•	Console Output:
------------Show Task------------
| ID | Name | Task Type | Date | Time | Assignee | Reviewer |
| -- | ----------- | --------- | ---------- | ---- | -------- | -------- |
| 1 | Dev Program | Code | 26-06-2015 | 8.0 | Dev | Lead |
| 2 | Test Login | Test | 27-06-2015 | 4.0 | Tester | QA |
VII. TEST CASE - MENU CHÍNH
TC14 - Chọn Menu ngoài khoảng 1-4
•	Input: Nhập số 5.
•	Console Output:
========= Task program =========
1.	Add Task
2.	Delete task
3.	Display Task
4.	exit
Enter your choice: 5
Just 1-> 4
Enter your choice:
TC15 - Chọn chức năng Exit
•	Input: Chọn Option 4.
•	Console Output: Chương trình kết thúc (Process finished with exit code 0).
VIII. TEST CASE - LUỒNG HOẠT ĐỘNG (END-TO-END)
TC16 - Luồng cơ bản: Thêm nhiều Task và Hiển thị
•	Mô tả: Thêm liên tiếp 2 công việc và kiểm tra danh sách.
•	Console Output:
========= Task program =========
(Người dùng nhập liệu cho Task 1 và Task 2 thành công)
...
Enter your choice: 3
------------Show Task------------
| ID | Name | Task Type | Date | Time | Assignee | Reviewer |
| -- | ----------- | --------- | ---------- | ---- | -------- | -------- |
| 1 | Dev Program | Code | 26-06-2015 | 8.0 | Dev | Lead |
| 2 | Test Login | Test | 27-06-2015 | 4.0 | Tester | QA |
TC17 - Luồng kiểm tra cơ chế sinh ID tự động (lastID)
•	Mô tả: Đảm bảo hệ thống sinh ID liên tục (không dùng lại ID đã xóa).
•	Console Output:
------------Add Task------------
... (Nhập thông tin cho Task A)
Add success task id: 1
========= Task program =========
...
Enter your choice: 2
------------Del Task------------
Task ID: 1
Delete success:
| ID | Name | Task Type | Date | Time | Assignee | Reviewer |
| -- | ----------- | --------- | ---------- | ---- | -------- | -------- |
| 1 | Task A | Code | 26-06-2015 | 8.0 | Dev | Lead |
========= Task program =========
...
Enter your choice: 1
------------Add Task------------
... (Nhập thông tin cho Task B)
Add success task id: 2
TC18 - Luồng xóa toàn bộ danh sách (Empty List)
•	Mô tả: Xóa hết dữ liệu đang có và kiểm tra phản hồi.
•	Console Output:
------------Del Task------------
Task ID: 2
Delete success:
| ID | Name | Task Type | Date | Time | Assignee | Reviewer |
| -- | ----------- | --------- | ---------- | ---- | -------- | -------- |
| 2 | Task B | Code | 26-06-2015 | 8.0 | Dev | Lead |
========= Task program =========
...
Enter your choice: 3
------------Show Task------------
This list is empty!
IX. TEST CASE - THEO DỮ LIỆU MẪU
TC19 - Nhập dữ liệu dòng 1
•	Input: Dev Program / Type: 1 / Date: 26-06-2015 / From: 9.5 / To: 17.5 / Assignee: Dev / Reviewer: Lead
•	Console Output (Add & Show):
Add success task id: 1
...
| ID | Name | Task Type | Date | Time | Assignee | Reviewer |
| -- | ----------- | --------- | ---------- | ---- | -------- | -------- |
| 1 | Dev Program | Code | 26-06-2015 | 8.0 | Dev | Lead |
TC20 - Nhập dữ liệu dòng 2
•	Input: Test Login / Type: 2 / Date: 27-06-2015 / From: 8.0 / To: 12.0 / Assignee: Tester / Reviewer: QA
•	Console Output (Add & Show):
Add success task id: 2
...
| ID | Name | Task Type | Date | Time | Assignee | Reviewer |
| -- | ----------- | --------- | ---------- | ---- | -------- | -------- |
| 2 | Test Login | Test | 27-06-2015 | 4.0 | Tester | QA |
TC21 - Nhập dữ liệu dòng 3
•	Input: DB Design / Type: 3 / Date: 28-06-2015 / From: 14.0 / To: 17.5 / Assignee: Arch / Reviewer: Manager
•	Console Output (Add & Show):
Add success task id: 3
...
| ID | Name | Task Type | Date | Time | Assignee | Reviewer |
| -- | ----------- | --------- | ---------- | ---- | -------- | -------- |
| 3 | DB Design | Design | 28-06-2015 | 3.5 | Arch | Manager |

BỔ SUNG TEST CASE RIÊNG CHO ID TỰ ĐỘNG
TC22 - ID đầu tiên tự động bằng 1
Điều kiện: Danh sách đang rỗng.
Input: Add một Task hợp lệ.
Expected Output:
TC23 - ID tăng liên tục khi thêm nhiều Task
Input: Add 3 Task liên tiếp.
Expected Output:
TC24 - ID không bị dùng lại sau khi xóa
Điều kiện: Đã có các ID 1, 2, 3.
Thao tác:
•	Delete ID = 2.
•	Add Task mới.
Expected Output:
TC25 - Kiểm tra Display sau khi xóa và thêm mới
Điều kiện: Sau TC24.
Input: Chọn Display.
Expected Output:
ID	Name	Task Type	Date	Time	Assignee	Reviewer
1	Dev Program	Code	26-06-2015	8.0	Dev	Lead
3	DB Design	Design	28-06-2015	3.5	Arch	Manager
4	New Task	Test	01-07-2015	2.0	Minh	QA
Giải thích: ID = 2 không xuất hiện nữa và ID mới là 4.
TC26 - Khởi động lại chương trình
Điều kiện: Chương trình chưa lưu dữ liệu ra file/database.
Thao tác:
•	Thoát chương trình.
•	Chạy lại chương trình.
•	Add Task mới.
Expected Output:
Giải thích: Vì dữ liệu chỉ lưu trong bộ nhớ nên khi chạy lại, lastID được reset về 0.
BỔ SUNG TEST CASE DỮ LIỆU LỚN
TC27 - Thêm 8 Task liên tiếp
Input: Nhập lần lượt 8 dòng dữ liệu mẫu ở bảng trên.
Expected Output:
TC28 - Hiển thị danh sách sau khi thêm 8 Task
Expected Output:
ID	Name	Task Type	Date	Time	Assignee	Reviewer
1	Dev Program	Code	26-06-2015	8.0	Dev	Lead
2	Test Login	Test	27-06-2015	4.0	Tester	QA
3	DB Design	Design	28-06-2015	3.5	Arch	Manager
4	Review Source	Review	29-06-2015	2.5	Leader	PM
5	Payment API	Code	30-06-2015	5.0	Nam	Hoa
6	UI Testing	Test	01-07-2015	3.0	Minh	An
7	Database Backup	Review	02-07-2015	1.5	Admin	Manager
8	API Refactor	Design	03-07-2015	6.0	Dev2	Lead2
BỔ SUNG TEST CASE STRESS NHỎ
TC29 - Thêm 10 Task liên tiếp
Mục đích: Kiểm tra biến lastID tăng ổn định khi thêm nhiều dữ liệu.
Expected Output cuối cùng:
TC30 - Xóa giữa danh sách rồi thêm tiếp
Điều kiện: Đã có ID từ 1 → 10.
Thao tác:
•	Delete ID = 5.
•	Add Task mới.
Expected Output:
Kết luận: Cơ chế sinh ID của chương trình hoạt động đúng theo biến lastID, luôn tăng liên tục và không tái sử dụng ID đã xóa.

