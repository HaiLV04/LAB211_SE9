# I. Yêu Cầu Chương Trình
Xây dựng chương trình quản lý công việc (Task) bằng Java Console, áp dụng lập trình hướng đối tượng (OOP) và sử dụng ArrayList để lưu trữ dữ liệu. Chương trình cung cấp menu chính gồm 4 chức năng:
1. Add Task
2. Delete task
3. Display Task
4. Exit

**Chi tiết các chức năng:**
*   **Chức năng 1 - Add Task (Thêm công việc):**
    *   Cho phép người dùng nhập các thông tin: Requirement Name, Task Type, Date, Plan From, Plan To, Assignee, Reviewer.
    *   ID được sinh tự động: ID đầu tiên luôn là 1. Mỗi lần thêm thành công, ID tăng thêm 1. ID đã xóa sẽ không được sử dụng lại.
    *   Requirement Name, Assignee, Reviewer: Chỉ cho phép nhập chữ cái, số và khoảng trắng (không chứa ký tự đặc biệt).
    *   Task Type: Người dùng chọn từ 1 đến 4 (1. Code, 2. Test, 3. Design, 4. Review).
    *   Date: Phải đúng định dạng dd-MM-yyyy và là một ngày hợp lệ.
    *   Plan From: Nằm trong khoảng từ 8.0 đến 17.0. Phần thập phân bắt buộc phải là .0 hoặc .5.
    *   Plan To: Nằm trong khoảng từ (Plan From + 0.5) đến 17.5. Phần thập phân bắt buộc phải là .0 hoặc .5.
    *   Kiểm tra trùng lặp: Không cho phép thêm một Task trùng lặp toàn bộ thông tin với một Task đã có trong danh sách. Nếu trùng, thông báo lỗi.
*   **Chức năng 2 - Delete Task (Xóa công việc):**
    *   Yêu cầu nhập ID của công việc cần xóa.
    *   Nếu ID tồn tại, xóa công việc đó khỏi danh sách và hiển thị thông tin công việc vừa xóa.
    *   Nếu ID không tồn tại, hiển thị thông báo lỗi.
*   **Chức năng 3 - Display Task (Hiển thị danh sách):**
    *   Hiển thị toàn bộ công việc trong danh sách dưới dạng bảng.
    *   Nếu danh sách trống, hiển thị thông báo danh sách trống.
    *   Các cột hiển thị: ID, Name, Task Type, Date, Time, Assignee, Reviewer.
    *   Cột Time được tính toán tự động bằng công thức: Plan To - Plan From.

# II. Giải Thích Luồng (Flow Explanation)
*   **Cấu trúc OOP:** Chương trình chia thành các package: `entity` (chứa `Task`, `TaskType`), `bo` (`ManagerTask`, `TaskInputer`), `controller` (`Controller`), `ui` (`Main`), và `utils` (`Validator`).
*   **Enum TaskType:** Quản lý 4 loại công việc cố định, dễ dàng ánh xạ từ ID người dùng nhập (1-4) sang chuỗi tên loại công việc.
*   **Lớp Validator (Kiểm tra dữ liệu):** Sử dụng vòng lặp `while(true)` và khối `try-catch` kết hợp biểu thức chính quy (Regex) để đảm bảo người dùng nhập đúng định dạng. Cụ thể:
    *   Chuỗi ký tự: Dùng regex `[A-Za-z0-9\s]+` chặn ký tự đặc biệt.
    *   Ngày tháng: Dùng `SimpleDateFormat` với `setLenient(false)` để bắt các ngày không tồn tại.
    *   Thời gian (Plan): Ép buộc nhập dạng `x.0` hoặc `x.5` bằng cách cắt chuỗi và kiểm tra phần thập phân. Ràng buộc `Plan To > Plan From`.
*   **Logic Thêm (Add):** Hệ thống quét danh sách để kiểm tra tính trùng lặp. Nếu không trùng, một đối tượng `Task` mới được tạo với `id` tự động tăng (`lastID++`) và thêm vào `ArrayList`.
*   **Logic Xóa (Delete):** Tìm kiếm `Task` theo `id`. Nếu tìm thấy, gọi phương thức `remove()` của `ArrayList` và hiển thị thông tin đã xóa; ngược lại thông báo lỗi không tìm thấy.
*   **Logic Hiển thị (Display):** Tính toán thời gian thực hiện (`Time = Plan To - Plan From`) và in danh sách ra giao diện Console có căn lề chuẩn (định dạng `String.format`).

# III. Test Cases

## 1. Input Validation Test Cases (Kiểm tra đầu vào)
*   **TC01 - Lỗi Tên Requirement chứa ký tự đặc biệt:**
    *   Input: Requirement Name = `Dev @Program`
    *   Expected: Thông báo lỗi `Invalid!` và yêu cầu nhập lại.
*   **TC02 - Lỗi Task Type ngoài phạm vi 1-4:**
    *   Input: Task Type = `5`
    *   Expected: Thông báo lỗi `Just be 1 -> 4` và yêu cầu nhập lại.
*   **TC03 - Lỗi định dạng Date sai:**
    *   Input: Date = `2025/05/10` hoặc `32-01-2025`
    *   Expected: Thông báo lỗi định dạng `dd-MM-yyyy` và yêu cầu nhập lại.
*   **TC04 - Lỗi Plan From ngoài phạm vi:**
    *   Input: Plan From = `7.0` hoặc `18.0`
    *   Expected: Thông báo lỗi `Just be 8 -> 17` và yêu cầu nhập lại.
*   **TC05 - Lỗi Plan sai phần thập phân:**
    *   Input: Plan From = `8.3`
    *   Expected: Thông báo lỗi `Must be x.0 or x.5` và yêu cầu nhập lại.
*   **TC06 - Lỗi Plan To <= Plan From:**
    *   Điều kiện: Đã nhập Plan From = `9.0`
    *   Input: Plan To = `8.0`
    *   Expected: Thông báo lỗi `Just be 9.5 -> 17.5` và yêu cầu nhập lại.
*   **TC07 - Lỗi ID xóa không phải là số hợp lệ:**
    *   Input: ID = `abc`
    *   Expected: Thông báo lỗi `Invalid!` và yêu cầu nhập lại ID.

## 2. Logic Test Cases (Kiểm tra luồng xử lý)
*   **TC08 - Thêm Task thành công:**
    *   Input: Thông tin hợp lệ (Name = `Dev`, Type = `1`, Date = `26-06-2025`, From = `8.0`, To = `12.0`, Assignee = `A`, Reviewer = `B`)
    *   Expected: Thông báo `Add success task id: 1` và Task được thêm vào hệ thống.
*   **TC09 - Bắt lỗi trùng lặp Task (Duplicate):**
    *   Điều kiện: Đã thêm Task như TC08.
    *   Input: Nhập lại y hệt thông tin ở TC08 cho một Task mới.
    *   Expected: Thông báo lỗi `This task is existed!` và quay về menu.
*   **TC10 - Xóa Task thành công:**
    *   Điều kiện: Tồn tại Task có ID = 1.
    *   Input: Xóa Task ID = 1.
    *   Expected: Hiển thị thông báo xóa thành công cùng toàn bộ chi tiết Task vừa xóa. Task không còn trong danh sách.
*   **TC11 - Xóa Task không tồn tại:**
    *   Input: Nhập ID = `99` (chưa từng được tạo).
    *   Expected: Báo lỗi `Task ID does not exist!`.
*   **TC12 - Kiểm tra ID tự động tăng và không sử dụng lại:**
    *   Thao tác: Thêm Task A (ID=1), thêm Task B (ID=2), xóa Task ID=2, thêm Task C.
    *   Expected: Task C có ID là `3` (ID 2 đã bị xóa không được tái sử dụng).
*   **TC13 - Hiển thị danh sách rỗng:**
    *   Thao tác: Vừa mở chương trình (hoặc xóa sạch danh sách) và chọn Display.
    *   Expected: Thông báo `This list is empty!`.
*   **TC14 - Hiển thị danh sách Task (Tính Time):**
    *   Thao tác: Thêm Task hợp lệ với From = `8.0`, To = `11.5`, sau đó chọn Display.
    *   Expected: Danh sách hiển thị đúng cột `Time = 3.5`.
