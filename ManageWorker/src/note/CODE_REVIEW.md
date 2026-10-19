# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0056 (WORKER MANAGEMENT)

---

## 1. TỔNG QUAN BÀI TOÁN & MỤC TIÊU
- **Mã bài lab:** J1.S.P0056 (Manage Worker and Salary History).
- **Mục tiêu:**
  1. **Add Worker:** Thêm công nhân mới với các thuộc tính: mã (`id`), tên (`name`), tuổi (`age` từ 18 đến 50), lương (`salary > 0`), nơi làm việc (`workLocation`). Mã công nhân định dạng `W` kèm số và không được trùng lặp.
  2. **Up Salary:** Tăng lương cho công nhân theo mã `id`. Số tiền tăng phải $> 0$. Ghi lại lịch sử điều chỉnh lương với trạng thái `UP`, ngày tháng hiện tại và mức lương mới sau khi tăng.
  3. **Down Salary:** Giảm lương cho công nhân theo mã `id`. Số tiền giảm phải $> 0$ và lương sau khi giảm phải $> 0$. Ghi lại lịch sử với trạng thái `DOWN`.
  4. **Display Information Salary:** Hiển thị toàn bộ lịch sử tăng/giảm lương của công nhân, được sắp xếp tăng dần theo mã công nhân `id`.
- **Kiến trúc áp dụng:** **MVC (Model - View - Controller)**.

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC PATTERN)

```
                       [Main] (Console Menu Loop)
                         │
                         ▼
                    [Controller]
            ┌────────────┴─────────────┐
            ▼                          ▼
       [Validator]          [ManagerWorker] & [ManagerSalaryHistory]
                                       │
                                       ▼
                       [Worker] & [SalaryHistory] & [SalaryStatus]
```

- **Model Package:**
  - `Worker`: Entity đại diện cho một công nhân, implements `Comparable<Worker>` để sắp xếp theo ID.
  - `SalaryStatus`: Enum xác định 2 trạng thái biến động lương: `UP` và `DOWN`.
  - `SalaryHistory`: Entity lưu lại một giao dịch điều chỉnh lương (chứa đối tượng `Worker`, `salary` sau biến động, `status`, và `date`).
  - `ManagerWorker`: Quản lý danh sách các công nhân, kiểm tra trùng ID, thực hiện tăng/giảm lương trên đối tượng `Worker`.
  - `ManagerSalaryHistory`: Quản lý danh sách lịch sử biến động lương, hỗ trợ sắp xếp và trả về danh sách lịch sử.
- **View Package:**
  - `Validator`: Xử lý nhập chuỗi có Regex, số nguyên trong khoảng, số thực `double` và xử lý ngoại lệ chống crash.
- **Controller Package:**
  - `Controller`: Tiếp nhận yêu cầu nghiệp vụ từ Menu, điều khiển các lớp Model và View.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & LOGIC NGHIỆP VỤ

### 3.1. `model.SalaryStatus` (Enum)
- Định nghĩa 2 giá trị cố định: `UP` và `DOWN`. Đảm bảo trạng thái biến động lương không bị gõ nhầm hay sai chính tả.

### 3.2. `model.Worker`
- Thuộc tính: `private String id;`, `private String name;`, `private int age;`, `private double salary;`, `private String workLocation;`.
- Triển khai `Comparable<Worker>`:
  ```java
  @Override
  public int compareTo(Worker o) {
      return this.id.compareToIgnoreCase(o.getId());
  }
  ```
  Giúp sắp xếp các bản ghi công nhân tăng dần theo mã ID không phân biệt hoa thường.

### 3.3. `model.SalaryHistory`
- Thuộc tính: `private Worker worker;`, `private double salary;`, `private SalaryStatus status;`, `private Date date;`.
- Triển khai `Comparable<SalaryHistory>`:
  ```java
  @Override
  public int compareTo(SalaryHistory o) {
      return this.worker.compareTo(o.getWorker());
  }
  ```
  Tận dụng trực tiếp phương thức `compareTo` của lớp `Worker` để sắp xếp lịch sử theo ID công nhân.
- Định dạng ngày tháng: Dùng `SimpleDateFormat("dd/MM/yyyy")` để in ngày biến động lương.

### 3.4. `model.ManagerWorker`
- **`isExist(String id)`:** Duyệt danh sách kiểm tra xem mã ID đã tồn tại chưa (`equalsIgnoreCase`).
- **`add(Worker worker)`:** Nếu `isExist(worker.getId())` $\rightarrow$ ném ngoại lệ `Worker with ID ... already exists.`. Ngược lại thêm vào danh sách.
- **`changeSalary(SalaryStatus status, String code, double amount)`:**
  - Kiểm tra tồn tại công nhân theo `code`.
  - Kiểm tra `if (amount <= 0)` ném ngoại lệ.
  - Xử lý trạng thái:
    - `case UP`: `worker.setSalary(worker.getSalary() + amount);`
    - `case DOWN`: Kiểm tra `if (worker.getSalary() - amount <= 0)` $\rightarrow$ ném ngoại lệ không cho phép lương sau giảm $\le 0$. Nếu hợp lệ: `worker.setSalary(worker.getSalary() - amount);`.
  - Trả về đối tượng `Worker` sau khi đã thay đổi lương.

### 3.5. `model.ManagerSalaryHistory`
- **`addSalaryHistory(SalaryHistory history)`:** Thêm một bản ghi lịch sử vào `list`.
- **`getSalaryHistory()`:**
  - Tạo bản sao `ArrayList<SalaryHistory> result = new ArrayList<>(list);`.
  - Gọi `Collections.sort(result);` để sắp xếp tăng dần theo ID công nhân trước khi trả về cho tầng hiển thị.

### 3.6. `controller.Controller`
- **`addWorker()`:**
  - Kiểm tra ID với regex `[Ww]\\d+` (ví dụ `W1`, `w10`).
  - Kiểm tra `age` từ 18 đến 50 qua `Validator.getInt(..., 18, 50)`.
  - Kiểm tra `salary > 0` qua `Validator.getDouble(..., Double.MIN_VALUE, Double.MAX_VALUE)`.
- **`upSalary()` & `downSalary()`:**
  - Kiểm tra nếu danh sách công nhân rỗng thì ném ngoại lệ `List is empty!`.
  - Gọi `workers.changeSalary(status, code, amount)`.
  - Tạo bản ghi `SalaryHistory` với `new Date()` (thời gian thực) và lưu vào `salaryHistory`.

---

## 4. CÁC NGUYÊN LÝ OOP & CLEAN CODE ÁP DỤNG

1. **Encapsulation:** Tất cả các thuộc tính của `Worker` và `SalaryHistory` đều ở mức truy cập `private`. Thuộc tính lương chỉ được thay đổi thông qua nghiệp vụ có kiểm duyệt `changeSalary`.
2. **Defensive Programming:** Không cho phép giảm lương về $\le 0$. Sử dụng `new ArrayList<>(list)` khi trả về danh sách để tránh việc mã nguồn bên ngoài can thiệp làm thay đổi trực tiếp cấu trúc danh sách nội bộ.
3. **Reusability (Tái sử dụng):** `SalaryHistory` chứa một tham chiếu tới đối tượng `Worker` (quan hệ Association), tránh trùng lặp thông tin và tận dụng hàm so sánh của `Worker`.

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Tại sao trong `SalaryHistory` em lại lưu cả đối tượng `Worker` mà không chỉ lưu mỗi `workerId`?
> **Trả lời:** Em áp dụng quan hệ liên kết đối tượng (Association/Aggregation) trong OOP. Khi `SalaryHistory` chứa tham chiếu tới `Worker`, nó có thể truy xuất trực tiếp tên, tuổi, nơi làm việc của công nhân đó khi hiển thị báo cáo mà không cần phải thực hiện thêm thao tác tìm kiếm phụ trong danh sách công nhân.

### Q2: Nếu người dùng thực hiện giảm lương lớn hơn mức lương hiện có thì sao?
> **Trả lời:** Trong phương thức `changeSalary()` của lớp `ManagerWorker`, em đã cài đặt điều kiện kiểm tra:
> `if (worker.getSalary() - amount <= 0) throw new Exception("Salary after down must be > 0");`
> Do đó hệ thống sẽ từ chối giao dịch, ném ngoại lệ thông báo và giữ nguyên mức lương cũ.

### Q3: Bảng lịch sử thay đổi lương được sắp xếp theo tiêu chí nào? Code thực hiện việc đó ở đâu?
> **Trả lời:** Bảng lịch sử được sắp xếp tăng dần theo **mã công nhân (ID)**. Việc sắp xếp diễn ra trong phương thức `getSalaryHistory()` của lớp `ManagerSalaryHistory` bằng câu lệnh `Collections.sort(result);`. Nhờ `SalaryHistory` implements `Comparable<SalaryHistory>` và ủy quyền so sánh cho `worker.compareTo(o.getWorker())`, các bản ghi lịch sử được tự động gom nhóm và sắp xếp theo mã công nhân một cách tự nhiên.

### Q4: Giải thích biểu thức Regex `[Ww]\\d+` mà em sử dụng khi nhập ID công nhân?
> **Trả lời:**
> - `[Ww]`: Ký tự đầu tiên phải là chữ `W` viết hoa hoặc `w` viết thường (đại diện cho Worker).
> - `\\d+`: Theo sau phải có ít nhất một hoặc nhiều chữ số liên tiếp từ 0 đến 9.
> Biểu thức này từ chối các mã không có tiền tố chữ W (như `123`), mã chứa ký tự lạ (như `W#1`), hoặc chỉ có mỗi chữ W mà không có số (`W`).

### Q5: Tại sao trong `ManagerWorker.getList()` em lại dùng `return new ArrayList<>(list)`?
> **Trả lời:** Đây là kỹ thuật **Defensive Copying (Sao chép phòng thủ)**. Nếu em trả về trực tiếp `this.list`, bên ngoài có thể gọi `clear()` hoặc `remove()` làm thay đổi cấu trúc dữ liệu nội bộ của `ManagerWorker`. Việc trả về một bản sao giúp bảo vệ dữ liệu gốc được an toàn tuyệt đối.
