# I. Yêu cầu chương trình

Xây dựng chương trình quản lý sinh viên bằng Java Console, áp dụng lập trình hướng đối tượng (OOP) và sử dụng `ArrayList` để lưu trữ danh sách sinh viên.

Mỗi sinh viên gồm các thông tin:
- ID
- Student Name
- Semester
- Course Name

Chỉ có 3 môn học được phép lựa chọn:
- Java
- .Net
- C/C++

Chương trình hiển thị menu gồm các chức năng:
1. Create
2. Find and Sort
3. Update/Delete
4. Report
5. Exit

### Chức năng Create:
- Cho phép người dùng nhập thông tin sinh viên từ bàn phím.
- Phải tạo ít nhất 5 sinh viên.
- Khi số lượng sinh viên lớn hơn 5, chương trình hỏi người dùng có muốn tiếp tục nhập hay không (Y/N).
- Nếu ID chưa tồn tại, người dùng phải nhập đầy đủ thông tin sinh viên.
- Nếu ID đã tồn tại, chương trình tự động sử dụng **Student Name** đã có của ID đó.
- Không cho phép tồn tại hai bản ghi có cùng ID, Semester và Course Name.
- Nếu bản ghi bị trùng lặp, chương trình thông báo lỗi và yêu cầu nhập lại.

### Chức năng Find and Sort:
- Tìm sinh viên theo tên hoặc một phần tên.
- Hiển thị Student Name, Semester và Course Name.
- Sắp xếp kết quả theo tên sinh viên theo thứ tự tăng dần.

### Chức năng Update/Delete:
- Tìm sinh viên theo ID.
- Hiển thị các bản ghi có cùng ID để người dùng lựa chọn.
- Cho phép cập nhật (U) hoặc xóa (D) bản ghi được chọn.
- Khi cập nhật, không được tạo ra bản ghi trùng lặp với dữ liệu đã tồn tại.

### Chức năng Report:
- Thống kê số lần học từng môn của mỗi sinh viên.
- Hiển thị kết quả theo định dạng: `Student Name | Course | Total`.

### Yêu cầu kỹ thuật:
- Thiết kế lớp `Student` gồm các thuộc tính: `id`, `studentName`, `semester` và `courseName`.
- Sử dụng `Collections.sort()` để sắp xếp danh sách sinh viên theo tên.
- Cài đặt `Comparable` hoặc `Comparator` để hỗ trợ sắp xếp.
- Chỉ sử dụng các lớp và chức năng có sẵn của Java Core.

---

# II. Giải thích chương trình

- **Java Console** là chương trình chạy trên cửa sổ dòng lệnh, cho phép người dùng nhập dữ liệu từ bàn phím và xem kết quả dưới dạng văn bản.
- **ArrayList** là danh sách động có thể tự động thay đổi kích thước khi thêm hoặc xóa dữ liệu, giúp quản lý danh sách sinh viên thuận tiện hơn.
- **OOP (Lập trình hướng đối tượng)** là phương pháp chia nhỏ chương trình thành các đối tượng giống như trong thế giới thực để quản lý. Mỗi đối tượng gồm 2 thành phần:
  - Thuộc tính: Thông tin của đối tượng.
  - Phương thức: Hành động của đối tượng.
- Chương trình được xây dựng theo hướng đối tượng (OOP), trong đó mỗi sinh viên được quản lý thông qua các thông tin: ID, tên sinh viên, học kỳ và môn học.
- Chương trình cung cấp các chức năng tạo mới, tìm kiếm, sắp xếp, cập nhật và xóa thông tin sinh viên.
- Chức năng **Find and Sort** cho phép tìm sinh viên theo tên hoặc một phần tên, sau đó sắp xếp kết quả theo tên bằng `Collections.sort()`.
- Chức năng **Update/Delete** cho phép tìm sinh viên theo ID để cập nhật hoặc xóa bản ghi tương ứng.
- Chức năng **Report** thống kê số lần học từng môn của mỗi sinh viên và hiển thị kết quả dưới dạng `Student Name | Course | Total`.
- Dữ liệu nhập vào được kiểm tra để đảm bảo hợp lệ, không được để trống và không tạo ra các bản ghi trùng lặp.
- Một bản ghi được xem là trùng lặp khi có cùng ID, Semester và Course Name.

---

## 1. Kiểm tra Nhập Liệu Thuộc Tính (Validator/Inputer)
- **ID Hợp lệ:** Nhập ID bắt đầu bằng S/s theo sau là số (ví dụ: `S123`) => Hợp lệ.
- **ID Ngoại lệ:** Nhập ID thiếu S/s hoặc chứa ký tự khác số (ví dụ: `1234`, `S12A`) => Bắt nhập lại.
- **ID đã tồn tại:** Nhập ID đã có trong hệ thống và cố gắng nhập tên khác => Chương trình tự động sử dụng tên cũ của ID đó.
- **Tên Hợp lệ:** Nhập tên chỉ chứa chữ cái và khoảng trắng => Hợp lệ.
- **Tên Ngoại lệ:** Nhập tên chứa số hoặc ký tự đặc biệt (ví dụ: `Nguyễn 123`) => Bắt nhập lại.
- **Học kỳ Hợp lệ:** Nhập học kỳ hợp lệ (ví dụ: `Fall2024`) => Hợp lệ.
- **Học kỳ Ngoại lệ:** Để trống hoặc nhập sai định dạng => Bắt nhập lại.
- **Môn học Hợp lệ:** Chọn `1`, `2` hoặc `3` => Hợp lệ.
- **Môn học Ngoại lệ:** Chọn ngoài khoảng 1-3 hoặc nhập ký tự => Bắt nhập lại.

## 2. Chức năng `createStudent()` (Tạo Sinh viên)
- **Thêm mới Hợp lệ:** Nhập ID mới => Yêu cầu nhập Tên => Thêm thành công.
- **Tên tự động:** Nhập ID đã tồn tại (khác Học kỳ/Khóa học) => Tự động điền Tên cũ.
- **Ngoại lệ - Trùng lặp:** Nhập bản ghi (ID, Học kỳ, Khóa học) đã tồn tại => Ném Exception: `"This record is existed!"`.
- **Kiểm soát tiếp tục:** `|List| > 5` và chọn 'Y'/'y' => Tiếp tục vòng lặp.
- **Kiểm soát dừng:** `|List| > 5` và chọn 'N'/'n' => Vòng lặp kết thúc.

## 3. Chức năng `findAndSort()` (Tìm kiếm và Sắp xếp)
- **Tìm kiếm Thành công:** Nhập tên có trong danh sách => Trả về list, sắp xếp theo tên và in ra.
- **Tìm kiếm Thất bại:** Nhập tên không tồn tại => Ném Exception: `"Can not found name!"`.
- **Sắp xếp thành công:** Tìm thấy nhiều sinh viên => Kết quả được sắp xếp theo Student Name tăng dần.
- **Tìm kiếm theo một phần tên:** Nhập `"Quan"` => Trả về tất cả sinh viên có tên chứa "Quan".

## 4. Chức năng `updateOrDelete()` (Cập nhật hoặc Xóa)
- **Tìm ID Thất bại:** Nhập ID không tồn tại => Ném Exception: `"Can not found id"`.
- **Tìm ID Thành công:** Nhập ID tồn tại => Hiển thị bản ghi, yêu cầu chọn Record.
- **Logic Xóa:** Chọn Record, chọn 'D'/'d' => Bản ghi được xóa.
- **Logic Cập nhật Hợp lệ:** Chọn 'U'/'u', nhập thông tin mới không trùng lặp => Cập nhật thành công.
- **Cập nhật Trùng lặp:** Cập nhật thông tin bị trùng với Record khác => Ném Exception: `"New record be duplicate!!"`.
- **Tên tự động (Update):** Chọn 'U', nhập ID đã có => Tên được tự động điền.
- **Update với ID mới:** Chọn 'U', nhập ID chưa tồn tại => Yêu cầu nhập Student Name mới.
- **Cập nhật Semester/Course:** Chọn 'U' và thay đổi Semester hoặc Course => Thông tin được cập nhật thành công.

## 5. Chức năng `report()` và Logic Cốt lõi
- **Report Thành công:** Danh sách có dữ liệu => Tạo và in báo cáo thống kê.
- **Report nhiều môn học:** Một sinh viên học nhiều môn khác nhau => Thống kê riêng cho từng môn.
- **Report Đúng Kết Quả:** Có nhiều sinh viên học cùng môn => Hiển thị đúng `Student Name | Course | Total`.
- **Report Thất bại:** Danh sách rỗng => Ném Exception: `"List is empty!"`.
- **Delete/Update List rỗng:** Gọi delete() hoặc update() khi danh sách rỗng => Ném Exception: `"List is empty..."`.

---

# III. Test Cases

### 1. DỮ LIỆU MẪU

| ID | Student Name | Semester   | Course |
| -- | ------------ | ---------- | ------ |
| S1 | Nguyen Quan  | Fall2024   | Java   |
| S1 | Nguyen Quan  | Spring2025 | Java   |
| S1 | Nguyen Quan  | Fall2024   | .Net   |
| S2 | Tran Linh    | Fall2024   | .Net   |
| S3 | Le Thu Thao  | Summer2024 | C/C++  |
| S4 | Nguyen Minh  | Fall2024   | Java   |
| S5 | Vu Quang     | Summer2024 | C/C++  |
| S6 | Quan Vu      | Fall2024   | Java   |

### 2. TEST CASE CHỨC NĂNG CREATE

- **TC01 - Thêm sinh viên mới thành công**
  - **Input:** `S5`, `Minh Vu`, `Spring2025`, `Java`
  - **Expected Output:** Student added successfully.
- **TC02 - ID đã tồn tại**
  - **Input:** `S1`, `Fall2025`, `C/C++`
  - **Expected Output:** `Name: Nguyen Quan` (Tự động sử dụng tên cũ)
- **TC03 - Trùng bản ghi**
  - **Input:** `S1`, `Fall2024`, `Java`
  - **Expected Output:** `This record is existed!`
- **TC04 - Nhập tiếp khi số lượng > 5**
  - **Input:** `Y`
  - **Expected Output:** Tiếp tục nhập sinh viên.
- **TC05 - Dừng nhập khi số lượng > 5**
  - **Input:** `N`
  - **Expected Output:** Quay lại menu chính.

### 3. TEST CASE CHỨC NĂNG FIND AND SORT

- **TC06 - Tìm kiếm thành công**
  - **Input:** `Quan`
  - **Expected Output:**
    ```
    Nguyen Quan | Fall2024 | Java
    Nguyen Quan | Spring2025 | Java
    Nguyen Quan | Fall2024 | .Net
    ```
- **TC07 - Tìm kiếm theo một phần tên**
  - **Input:** `Nguyen`
  - **Expected Output:**
    ```
    Nguyen Minh | Fall2024 | Java
    Nguyen Quan | Fall2024 | Java
    Nguyen Quan | Spring2025 | Java
    Nguyen Quan | Fall2024 | .Net
    ```
- **TC08 - Không phân biệt hoa thường**
  - **Input:** `quAN`
  - **Expected Output:**
    ```
    Nguyen Quan | Fall2024 | Java
    Nguyen Quan | Spring2025 | Java
    Nguyen Quan | Fall2024 | .Net
    ```
- **TC09 - Không tìm thấy sinh viên**
  - **Input:** `Hoang`
  - **Expected Output:** `Can not found name!`
- **TC10 - Kiểm tra sắp xếp theo tên sau khi nhập**
  - **Input:** `quan`
  - **Expected Output:** Kết quả hiển thị theo thứ tự tăng dần của Student Name.

### 4. TEST CASE CHỨC NĂNG UPDATE / DELETE

- **TC11 - Tìm ID thành công**
  - **Input:** `S1`
  - **Expected Output:** Hiển thị tất cả bản ghi của S1.
- **TC12 - Tìm ID thất bại**
  - **Input:** `S100`
  - **Expected Output:** `Can not found id`
- **TC13 - Xóa bản ghi**
  - **Input:** `S3`, `Record 1`, `D`
  - **Expected Output:** Bản ghi của Le Thu Thao bị xóa.
- **TC14 - Cập nhật thành công**
  - **Input:** `S2`, `Record 1`, `U` (ID: S2, Semester: Spring2025, Course: Java)
  - **Expected Output:** `Update success.`
- **TC15 - Update với ID mới**
  - **Input:** `U`, `S10`, `Tran Van B`, `Fall2025`, `Java`
  - **Expected Output:** Cập nhật thành công.
- **TC16 - Update với ID đã tồn tại**
  - **Input:** `U`, `S1`
  - **Expected Output:** `Name: Nguyen Quan` (Tự động sử dụng tên cũ)
- **TC17 - Update gây trùng dữ liệu**
  - **Input:** `U` (ID: S1, Semester: Fall2024, Course: Java)
  - **Expected Output:** `New record be duplicate!!`

### 5. TEST CASE CHỨC NĂNG REPORT

- **TC18 - Report thành công**
  - **Expected Output:**
    ```
    Le Thu Thao | C/C++ | 1
    Nguyen Quan | Java | 2
    Nguyen Quan | .Net | 1
    Nguyen Minh | Java | 1
    Tran Linh | .Net | 1
    ```
- **TC19 - Một sinh viên học cùng môn nhiều lần**
  - **Dữ liệu:** S1 học Java (Fall2024, Spring2025, Summer2025)
  - **Expected Output:** `Nguyen Quan | Java | 3`
- **TC20 - Một sinh viên học nhiều môn**
  - **Dữ liệu:** S1 học Java và .Net
  - **Expected Output:**
    ```
    Nguyen Quan | Java | 1
    Nguyen Quan | .Net | 1
    ```
- **TC21 - Danh sách rỗng**
  - **Input:** `Report`
  - **Expected Output:** `List is empty!`

### 6. TEST CASE VALIDATOR

- **TC22 - ID hợp lệ:** Input `S123` => Expected Output: `Accepted.`
- **TC23 - ID không hợp lệ:** Input `123` => Expected Output: `Invalid!`
- **TC24 - Tên hợp lệ:** Input `Nguyen Quan` => Expected Output: `Accepted.`
- **TC25 - Tên không hợp lệ:** Input `Nguyen123` => Expected Output: `Invalid!`
- **TC26 - Môn học hợp lệ:** Input `1` => Expected Output: `Java`
- **TC27 - Môn học không hợp lệ:** Input `5` => Expected Output: `Please enter number 1->3`
- **TC28 - Học kỳ hợp lệ:** Input `Fall2024` => Expected Output: `Accepted.`
- **TC29 - Học kỳ không hợp lệ:** Input `@` => Expected Output: `Invalid!`
