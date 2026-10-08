# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0071 (TASK MANAGEMENT)

---

## 1. TỔNG QUAN BÀI TOÁN & QUY TẮC NGHIỆP VỤ
- **Mã bài lab:** J1.S.P0071 (Task Management Program).
- **Mục tiêu:**
  1. Quản lý danh sách các công việc (Task) với cơ chế tự sinh mã ID tăng dần.
  2. Các thuộc tính của Task: `id`, `taskTypeID`, `requirementName`, `date`, `planFrom`, `planTo`, `assign`, `reviewer`.
  3. **Loại công việc (Task Type):** Cố định 4 loại: `1 - Code`, `2 - Test`, `3 - Design`, `4 - Review` (dùng `enum TaskType`).
  4. **Quy tắc thời gian (Time constraints):**
     - Giờ làm việc nằm trong khung từ 8.0 đến 17.5 (từ 8h00 sáng đến 17h30 chiều).
     - Giờ bắt đầu (`planFrom`) nằm trong khoảng $[8.0, 17.0]$.
     - Giờ kết thúc (`planTo`) phải lớn hơn `planFrom` và $\le 17.5$.
     - Bước nhảy thời gian là 0.5 (ví dụ 8.0, 8.5, 9.0...).
  5. **Quy tắc chống trùng lặp:** Không cho phép thêm task bị trùng toàn bộ thông tin (cùng loại, tên yêu cầu, ngày, thời gian bắt đầu, kết thúc, người được giao, người duyệt).
  6. **Chức năng Xóa:** Xóa task theo ID. Nếu không tìm thấy ID thì ném ngoại lệ thông báo lỗi.
  7. **Chức năng Hiển thị:** Xuất bảng danh sách task dạng cột chuẩn.

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC PATTERN)

```
                       [Main] (Console Menu 1-4)
                         │
                         ▼
                    [Controller]
            ┌────────────┴─────────────┐
            ▼                          ▼
      [TaskInputer]              [ManagerTask] (BO)
     + [Validator]                     │
            │                          ▼
            └─────────────────> [Task] & [TaskType (Enum)]
```

- **Model Package:**
  - `Task`: Entity lưu thông tin công việc.
  - `TaskType`: Enum đại diện cho các loại công việc (1-Code, 2-Test, 3-Design, 4-Review).
  - `ManagerTask`: Lớp xử lý nghiệp vụ trung tâm: tự động sinh ID (`lastID`), kiểm tra trùng lặp (`isDuplicate`), thêm task (`add`), xóa task (`deleteTaskByID`), xuất chuỗi định dạng bảng (`toString`).
- **View Package:**
  - `Validator`: Tiện ích nhập liệu kiểm tra số nguyên, số thực, chuỗi regex, định dạng ngày tháng `dd-MM-yyyy`.
  - `TaskInputer`: Giao diện nhập thông tin từng trường của một task.
- **Controller Package:**
  - `Controller`: Nhận yêu cầu từ Main, kết nối `TaskInputer` và `ManagerTask`.
- **Main (`main.Main`):** Menu tương tác chính.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & LOGIC NGHIỆP VỤ

### 3.1. `model.TaskType` (Enum)
- Khai báo 4 hằng số: `CODE(1, "Code")`, `TEST(2, "Test")`, `DESIGN(3, "Design")`, `REVIEW(4, "Review")`.
- Phương thức `public static TaskType getTaskType(int type)`: chuyển đổi từ số nguyên $1-4$ sang giá trị enum tương ứng, ném ngoại lệ nếu ngoài phạm vi.

### 3.2. `model.Task`
- Thuộc tính `private`: `id`, `taskTypeID`, `requirementName`, `date`, `planFrom`, `planTo`, `assign`, `reviewer`.
- Phương thức `getTime()`: Tính tổng số giờ thực hiện task: `return planTo - planFrom;`.
- Định dạng ngày tháng hiển thị: `new SimpleDateFormat("dd-MM-yyyy").format(date)`.

### 3.3. `model.ManagerTask` (Xử lý nghiệp vụ cốt lõi)
- **Thuộc tính:**
  ```java
  private ArrayList<Task> list;
  private int lastID; // Biến lưu ID lớn nhất đã phát sinh
  ```
- **`isDuplicate(...)`:**
  - Duyệt qua `list` kiểm tra nếu trùng tất cả các trường dữ liệu thì trả về `true`.
- **`add(...)`:**
  - Gọi `isDuplicate()`. Nếu trùng $\rightarrow$ ném ngoại lệ `throw new Exception("This task is existed!");`.
  - **Tự động sinh ID:** `newTask = new Task(++lastID, ...);` $\rightarrow$ ID luôn tăng dần đều và là duy nhất.
  - Thêm vào danh sách và trả về ID vừa thêm.
- **`deleteTaskByID(int id)`:**
  - Kiểm tra nếu `list.isEmpty()` $\rightarrow$ ném ngoại lệ.
  - Duyệt danh sách tìm task có `task.getId() == id`:
    - Nếu tìm thấy: lưu đối tượng, gọi `list.remove(task)` và trả về đối tượng đã xóa.
  - Nếu duyệt hết không thấy: ném ngoại lệ `throw new Exception("ID not exist in data!");`.
- **`toString()`:**
  - Tạo bảng định dạng cột dạng `String.format("|%-5s|%-15s|%-10s|%-15s|%-10s|%-15s|%-15s|\n", "ID", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer")`.

### 3.4. `view.TaskInputer`
- **Kiểm tra giờ bắt đầu (`planFrom`):**
  - Giới hạn trong khoảng $[8.0, 17.0]$.
  - Kiểm tra chia hết cho 0.5: `if (from % 0.5 != 0) { ... bắt nhập lại }`.
- **Kiểm tra giờ kết thúc (`planTo`):**
  - Giới hạn từ $(planFrom, 17.5]$.
  - Kiểm tra chia hết cho 0.5: `if (to % 0.5 != 0) { ... bắt nhập lại }`.
- **Nhập ngày tháng (`date`):**
  - Dùng `Validator.getDate(..., "dd-MM-yyyy", minDate, maxDate)`.
  - Đặt `dateFormat.setLenient(false)` để ngăn chặn việc tự làm tròn ngày không tồn tại (như 30/02).

---

## 4. CÁC NGUYÊN LÝ OOP & CLEAN CODE ÁP DỤNG

1. **Information Hiding & Encapsulation:** Mọi trường dữ liệu của `Task` đều được che giấu (`private`). Mã ID không thể bị sửa đổi tùy tiện từ bên ngoài mà chỉ được quản lý bởi `ManagerTask`.
2. **Robust Validation:** Sử dụng kết hợp Regex, `SimpleDateFormat` vô hiệu hóa tính dễ dãi (`setLenient(false)`), và bẫy lỗi toán học `% 0.5` cho thời gian thực hiện task.
3. **High Cohesion, Low Coupling:** Tách biệt hoàn toàn giữa việc nhận dữ liệu bàn phím (`TaskInputer`) và xử lý danh sách bộ nhớ (`ManagerTask`).

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Cơ chế tự động tăng ID trong bài này hoạt động như thế nào? Nếu xóa một Task đi thì Task tiếp theo có bị trùng ID không?
> **Trả lời:** Em sử dụng biến `private int lastID` trong lớp `ManagerTask`, khởi tạo ban đầu là 0. Khi thêm một Task mới, em dùng toán tử tiền tố `++lastID` để sinh mã ID tiếp theo. Kể cả khi một Task bị xóa khỏi danh sách, biến `lastID` vẫn giữ nguyên mốc giá trị cao nhất và tiếp tục tăng lên cho các task về sau. Điều này đảm bảo ID là duy nhất tuyệt đối (Unique ID) tương tự như cơ chế `AUTO_INCREMENT` trong cơ sở dữ liệu.

### Q2: Tại sao khi kiểm tra ngày tháng bằng `SimpleDateFormat` em lại cần thiết lập `dateFormat.setLenient(false)`?
> **Trả lời:** Mặc định trong Java, `SimpleDateFormat` có tính năng `lenient = true` (tự động suy luận ngày). Nếu người dùng nhập ngày không tồn tại như `31/02/2024`, Java sẽ tự động cộng dồn ngày sang `02/03/2024` mà không báo lỗi. Việc thiết lập `setLenient(false)` buộc Java phải kiểm tra nghiêm ngặt lịch thực tế, phát hiện ngày không hợp lệ và ném `ParseException` để yêu cầu nhập lại.

### Q3: Nếu người dùng nhập `planFrom = 10.0` và `planTo = 9.0` thì chương trình xử lý thế nào?
> **Trả lời:** Trong hàm `input()` của lớp `TaskInputer`, giá trị tối thiểu của `planTo` được thiết lập phụ thuộc vào `planFrom`:
> `double to = Validator.getDouble(..., from + 0.5, 17.5);`
> Người dùng nhập `planTo` nhỏ hơn hoặc bằng `planFrom` sẽ bị chặn ngay lập tức và bắt nhập lại.

### Q4: Thuộc tính `Time` hiển thị trên bảng danh sách Task được lấy từ đâu?
> **Trả lời:** Thuộc tính `Time` là một thuộc tính suy dẫn (Derived attribute), được tính động bằng phương thức `task.getTime()` với công thức `planTo - planFrom`. Em không lưu cứng `time` vào biến thuộc tính để tránh dư thừa dữ liệu (Data Redundancy) và đảm bảo tính nhất quán nếu giờ bắt đầu/kết thúc thay đổi.
