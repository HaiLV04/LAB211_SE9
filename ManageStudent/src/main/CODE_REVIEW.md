# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.L.P0021 (STUDENT MANAGEMENT)

---

## 1. TỔNG QUAN BÀI TOÁN & QUY TẮC NGHIỆP VỤ
- **Mã bài lab:** J1.L.P0021 (Manage Student).
- **Yêu cầu cốt lõi:**
  1. Quản lý danh sách sinh viên gồm các thông tin: `id`, `studentName`, `semester`, `courseName`.
  2. Các môn học hợp lệ cố định gồm: `Java`, `.Net`, `C/C++` (sử dụng `enum Course`).
  3. **Chức năng 1 - Create Student:**
     - Phải nhập tối thiểu 5 sinh viên trước khi hỏi tiếp tục (Y/N).
     - Quy tắc đồng nhất tên: Nếu ID đã tồn tại, tự động lấy tên cũ của sinh viên đó gán cho bản ghi mới.
     - Quy tắc chống trùng: Không được phép tồn tại 2 bản ghi trùng cả 3 thông tin: `ID`, `Semester` và `CourseName`.
  4. **Chức năng 2 - Find and Sort:**
     - Tìm kiếm theo tên hoặc một phần tên (substring, không phân biệt hoa thường).
     - Kết quả tìm được phải được sắp xếp tăng dần theo tên sinh viên (`Collections.sort()`).
  5. **Chức năng 3 - Update/Delete:**
     - Tìm sinh viên theo `ID`, hiển thị danh sách các bản ghi của ID đó kèm số thứ tự.
     - Người dùng chọn bản ghi và chọn thao tác: `U` (Update) hoặc `D` (Delete).
     - Khi cập nhật, dữ liệu mới không được gây ra trùng lặp với các bản ghi đã có.
  6. **Chức năng 4 - Report:**
     - Thống kê tổng số lần học từng môn của mỗi sinh viên (`Student Name | Course | Total`).

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC ARCHITECTURE)

```
                       [Main] (Menu Loop & Exception Handler)
                         │
                         ▼
                    [Controller]
            ┌────────────┴─────────────┐
            ▼                          ▼
    [StudentInputer]           [ManageStudent] (BO)
     + [Validator]                     │
            │                          ▼
            └─────────────────> [Student] & [Course (Enum)]
```

- **Model Package:**
  - `Course`: Enum định nghĩa danh sách các môn học cố định.
  - `Student`: Entity class chứa thông tin sinh viên, triển khai `Comparable<Student>` để sắp xếp theo tên.
  - `ManageStudent`: Business Object (BO) chứa `List<Student> list`, thực hiện toàn bộ nghiệp vụ kiểm tra trùng, thêm, sửa, xóa, tìm kiếm, xuất báo cáo tổng hợp.
- **View Package:**
  - `Validator`: Lớp tiện ích kiểm tra dữ liệu đầu vào bằng Regex và Exception handling.
  - `StudentInputer`: Giao diện nhập liệu tương tác từng trường thuộc tính của sinh viên.
- **Controller Package:**
  - `Controller`: Điều phối luồng xử lý giữa `ManageStudent`, `StudentInputer` và `Validator`.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & LOGIC NGHIỆP VỤ

### 3.1. `model.Course` (Enum)
- Sử dụng `enum` thay vì String tự do để đảm bảo **Type-safety** (chỉ có 3 giá trị: `JAVA("JAVA")`, `DOT_NET(".NET")`, `C_CPP("C/C++")`).
- `getCourse(int type)`: Ánh xạ từ số lựa chọn (1, 2, 3) sang giá trị enum tương ứng.

### 3.2. `model.Student`
- Áp dụng triệt để tính **Encapsulation**: tất cả các trường dữ liệu `id`, `studentName`, `semester`, `courseName` đều để `private`, truy xuất qua Getter/Setter.
- Triển khai interface `Comparable<Student>`:
  ```java
  @Override
  public int compareTo(Student t) {
      return this.studentName.compareTo(t.studentName);
  }
  ```
  Giúp đối tượng `Student` có thể được sắp xếp tự nhiên theo tên thông qua hàm `Collections.sort(list)`.

### 3.3. `model.ManageStudent` (Trọng tâm nghiệp vụ)
- **`isExisted(Student student)`:**
  - Duyệt danh sách kiểm tra xem có phần tử nào trùng cả 3 trường `id`, `semester` và `courseName` hay không:
    ```java
    if (students.getId().equals(student.getId())
            && students.getSemester().equals(student.getSemester())
            && students.getCourseName().equals(student.getCourseName())) {
        return true;
    }
    ```
- **`add(Student student)`:** Nếu `isExisted(student) == true` $\rightarrow$ ném ngoại lệ `throw new Exception("This record is existed!");`. Ngược lại thêm vào danh sách.
- **`update(Student oldRecord, Student newRecord)`:**
  - Kiểm tra nếu `isExisted(newRecord)` $\rightarrow$ ném ngoại lệ `New record be duplicate!!`.
  - Dùng `list.set(index, newRecord)` để thay thế bản ghi cũ.
- **`getListStudentByName(String name)`:**
  - Dùng `getStudentName().toLowerCase().contains(name.toLowerCase())` $\rightarrow$ hỗ trợ tìm kiếm không phân biệt hoa thường và tìm theo một phần của tên.
- **`report()` (Thuật toán báo cáo thống kê):**
  - **Bước 1:** Sắp xếp danh sách theo tên sinh viên:
    `Collections.sort(list, (s1, s2) -> s1.getStudentName().compareToIgnoreCase(s2.getStudentName()));`
  - **Bước 2:** Sử dụng 2 cấu trúc `HashMap`:
    - `countMap`: Key = `ID + "|" + CourseName`, Value = số lần học (dùng `countMap.getOrDefault(key, 0) + 1`).
    - `semesterMap`: Lưu danh sách các kỳ học gộp lại (ví dụ `"Fall2024, Spring2025"`).
  - **Bước 3:** Duyệt lại danh sách theo thứ tự đã sort, dùng `List<String> printedKeys` để tránh in trùng các dòng đã hiển thị, xuất ra bảng báo cáo dạng cột chuẩn.

### 3.4. `view.StudentInputer` & `view.Validator`
- **`inputID()`:** Kiểm tra Regex `[Ss]\\d+` (bắt đầu bằng S hoặc s, theo sau là các chữ số, ví dụ `S123`).
- **`inputStudentName()`:** Kiểm tra Regex `[A-Za-z\\s]+` (chỉ gồm chữ cái và khoảng trắng, không chứa số hay ký tự đặc biệt).
- **`inputSemester()`:** Kiểm tra Regex `[A-Za-z0-9\\s]+`.
- **`inputCourseName()`:** Bắt người dùng chọn 1 đến 3 qua `Validator.getInt(..., 1, 3)`.

### 3.5. `controller.Controller`
- **`createStudent()`:**
  - Nếu `getListStudentById(student.getId()).isEmpty()` $\rightarrow$ ID mới $\rightarrow$ yêu cầu nhập tên.
  - Ngược lại $\rightarrow$ ID đã tồn tại $\rightarrow$ tự động gán tên cũ:
    ```java
    String name = studentManager.getListStudentById(student.getId()).get(0).getStudentName();
    student.setStudentName(name);
    System.out.println("Name: " + name);
    ```
  - Chỉ khi số lượng sinh viên $> 5$ mới hỏi `Do you continue (Y/N)?`.

---

## 4. CÁC NGUYÊN LÝ OOP ĐƯỢC ÁP DỤNG TRONG BÀI

1. **Encapsulation (Đóng gói):** Ẩn giấu dữ liệu trong `Student` bằng `private`, truy xuất qua các phương thức getter/setter có kiểm soát.
2. **Abstraction (Trừu tượng):** Triển khai interface chuẩn `Comparable<T>` trong Java Core để định nghĩa quy tắc so sánh hai sinh viên. Sử dụng `enum Course` trừu tượng hóa danh mục môn học.
3. **Polymorphism (Đa hình):** Ghi đè (Override) phương thức `compareTo()` của interface `Comparable` và phương thức `toString()` của lớp `Object`.
4. **Single Responsibility Principle (SRP):** Tách bạch rõ rệt giữa giao diện nhập liệu (`StudentInputer`), kiểm tra tính hợp lệ (`Validator`), lưu trữ dữ liệu nghiệp vụ (`ManageStudent`) và điều phối luồng (`Controller`).

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Tại sao trong hàm Report em lại dùng `HashMap` mà không dùng 2 vòng lặp lồng nhau?
> **Trả lời:** Nếu dùng 2 vòng lặp lồng nhau duyệt danh sách để đếm số lần học, độ phức tạp thời gian sẽ là $O(n^2)$. Bằng cách sử dụng `HashMap`, độ phức tạp chèn và tra cứu chỉ là $O(1)$ trung bình. Do đó toàn bộ quá trình thống kê Report đạt độ phức tạp tuyến tính $O(n)$, tối ưu vượt trội về hiệu năng khi danh sách sinh viên lớn.

### Q2: Sự khác nhau giữa `Comparable` và `Comparator` là gì? Bài này em dùng cái nào?
> **Trả lời:**
> - `Comparable` nằm trong package `java.lang`, định nghĩa thứ tự sắp xếp tự nhiên bên trong chính lớp đối tượng bằng cách override phương thức `compareTo(T o)`.
> - `Comparator` nằm trong `java.util`, dùng để định nghĩa các tiêu chí sắp xếp linh hoạt bên ngoài lớp bằng phương thức `compare(T o1, T o2)`.
> - Trong bài này, lớp `Student` implements `Comparable<Student>` để sắp xếp mặc định theo tên. Ngoài ra trong hàm `report()`, em còn kết hợp dùng `Comparator` (dưới dạng Lambda expression) để sắp xếp không phân biệt chữ hoa, chữ thường (`compareToIgnoreCase`).

### Q3: Nếu một sinh viên đổi tên ở chức năng Update thì các bản ghi khác của cùng sinh viên đó có bị ảnh hưởng không?
> **Trả lời:** Trong nghiệp vụ bài lab, một sinh viên được định danh duy nhất bởi `ID`. Khi thực hiện Update, nếu cập nhật đổi tên thì logic nên cập nhật đồng bộ tên mới cho tất cả các bản ghi có cùng ID đó trong hệ thống để đảm bảo tính nhất quán dữ liệu (Data Consistency).

### Q4: Regex kiểm tra ID và Tên sinh viên của em hoạt động thế nào?
> **Trả lời:** 
> - Mã ID dùng regex `[Ss]\\d+`: `[Ss]` nghĩa là ký tự đầu tiên bắt buộc phải là 'S' hoặc 's'; `\\d+` nghĩa là theo sau phải có ít nhất một hoặc nhiều chữ số (0-9).
> - Tên dùng regex `[A-Za-z\\s]+`: chỉ cho phép các chữ cái hoa, chữ cái thường và khoảng trắng (`\\s`), từ chối toàn bộ số và ký tự đặc biệt.

### Q5: Tại sao khi cập nhật môn học em lại dùng `Enum` thay vì cho người dùng nhập chuỗi văn bản tự do?
> **Trả lời:** Sử dụng `Enum` mang lại tính **Type-safety** cao. Đề bài chỉ cho phép đúng 3 môn: Java, .Net, C/C++. Nếu dùng chuỗi tự do, người dùng có thể nhập sai chính tả (ví dụ "jaava", "C#"), làm phức tạp quá trình chuẩn hóa. Dùng Enum kết hợp Menu chọn (1, 2, 3) triệt tiêu hoàn toàn khả năng nhập sai môn học.
