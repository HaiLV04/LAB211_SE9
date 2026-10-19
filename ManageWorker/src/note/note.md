# Worker Management
## 1. Requirement (Mục tiêu chương trình)
Xây dựng chương trình quản lý thông tin công nhân và lịch sử thay đổi lương (Worker Management). Chương trình cho phép người dùng thực hiện các chức năng sau:
- Thêm công nhân mới vào hệ thống.
- Tăng lương cho công nhân.
- Giảm lương cho công nhân.
- Hiển thị thông tin lịch sử thay đổi lương.
- Thoát chương trình.

### Điều kiện dữ liệu:
- **Worker ID**: Phải bắt đầu bằng ký tự W (hoặc w, sẽ được tự động chuyển thành W) theo sau là chữ số. Không cho phép trùng lặp ID.
- **Name**: Chỉ chứa chữ cái và khoảng trắng.
- **Age**: Nằm trong khoảng từ 18 đến 50.
- **Salary / Amount**: Phải là số thực lớn hơn 0. Khi giảm lương, lương còn lại không được nhỏ hơn 0.
- **Work Location**: Chỉ chứa chữ cái, chữ số và khoảng trắng.

## 2. Flow Explanation (Luồng giải thích)
- **Kiến trúc**:
  - `entity.Worker`, `entity.SalaryHistory`, `entity.SalaryStatus`: Đại diện cho dữ liệu trong chương trình.
  - `bo.ManagerWorker`: Xử lý thêm mới, tìm kiếm và thay đổi lương của công nhân (tăng/giảm). 
  - `bo.ManagerSalaryHistory`: Xử lý việc lưu trữ và lấy danh sách lịch sử khi lương thay đổi, sắp xếp danh sách theo ID công nhân.
  - `utils.Validator`: Lớp kiểm tra dữ liệu đầu vào (chuỗi bằng RegEx, số nguyên, số thực với giới hạn biên) thông qua Scanner.
  - `controller.Controller`: Gọi các phương thức ở tầng `bo` và tương tác với người dùng ở Console (thông qua `Validator`), điều hướng luồng xử lý.
  - `ui.Main`: Hiển thị menu cho người dùng lựa chọn và bắt Exception để in ra.

- **Luồng thêm Worker (Option 1)**: Gọi `Controller.addWorker()`. Nhập lần lượt ID (kiểm tra tồn tại), Tên, Tuổi, Lương, Địa điểm làm việc qua lớp `Validator`. Nếu không lỗi, thêm vào `ManagerWorker`.
- **Luồng tăng lương (Option 2)**: Gọi `Controller.upSalary()`. Nhập ID công nhân, nhập mức tăng. Tìm công nhân trong danh sách, cộng thêm lương hiện tại với mức tăng. Lưu vào danh sách `SalaryHistory`.
- **Luồng giảm lương (Option 3)**: Gọi `Controller.downSalary()`. Nhập ID công nhân, nhập mức giảm. Đảm bảo mức giảm nhỏ hơn hoặc bằng lương hiện tại. Trừ lương và lưu vào danh sách `SalaryHistory`.
- **Luồng hiển thị lịch sử (Option 4)**: Gọi `Controller.showHistorySalary()`. Gọi danh sách `SalaryHistory`, sắp xếp bằng Collections.sort (theo ID) và in ra theo định dạng được thiết lập sẵn.

## 3. Test Cases (Các trường hợp kiểm thử)
### 3.1. Input Validation (Kiểm tra đầu vào)
| Dữ liệu | Hợp lệ | Ngoại lệ | Expected Result |
|---|---|---|---|
| ID | `W1`, `w100` | `ABC`, `123`, `W_1` | Báo Invalid, nếu trùng ID báo ID already exists |
| Name | `Nguyen Van A` | `An123`, `Binh@` | Báo Invalid, chỉ nhận chữ và khoảng trắng |
| Age | `18`, `25`, `50` | `17`, `51`, `abc` | Yêu cầu `age >= 18 and <= 50` |
| Salary / Amount | `1000`, `0.5` | `0`, `-100`, `abc` | Yêu cầu `salary must be > 0` |
| Location | `Hanoi Shop 1` | `Hanoi#1` | Báo Invalid |

### 3.2. Logic Test Cases (Kiểm tra logic)
**Test Case 1: Thêm mới công nhân**
- Input: `W01`, `John`, `25`, `1000`, `Hanoi`
- Expected: Trả về "Add success", lưu công nhân với mã W01.

**Test Case 2: Tăng lương hợp lệ**
- Precondition: Tồn tại worker `W01` với lương 1000.
- Input: Code = `W01`, Amount = `500`
- Expected: Lương worker W01 thành 1500. Thêm lịch sử UP.

**Test Case 3: Giảm lương không hợp lệ (vượt mức lương hiện tại)**
- Precondition: Tồn tại worker `W01` với lương 1000.
- Input: Code = `W01`, Amount = `1500`
- Expected: Báo lỗi "Amount must be smaller than current salary (1000.0)!" và yêu cầu nhập lại lượng giảm.

**Test Case 4: Hiển thị lịch sử lương**
- Precondition: Lịch sử có W01 UP 1500, W02 DOWN 2500, W01 DOWN 1200.
- Expected: In ra danh sách sắp xếp theo ID (W01 lên đầu, hiển thị Code, Name, Age, Salary, Status, Date).
