# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0074 (MATRIX OOP)

---

## 1. TỔNG QUAN BÀI TOÁN & QUY TẮC TOÁN HỌC
- **Mã bài lab:** J1.S.P0074 (Matrix Calculator Program using OOP).
- **Mục tiêu:**
  1. Xây dựng chương trình tính toán ma trận với các phép toán cơ bản: **Cộng (Addition)**, **Trừ (Subtraction)**, **Nhân (Multiplication)**.
  2. Áp dụng chặt chẽ các điều kiện toán học:
     - **Phép cộng và trừ:** Hai ma trận bắt buộc phải có cùng kích thước ($rows_1 = rows_2$ và $cols_1 = cols_2$).
     - **Phép nhân:** Số cột của ma trận thứ nhất bắt buộc phải bằng số hàng của ma trận thứ hai ($cols_1 = rows_2$). Ma trận kết quả có kích thước $rows_1 \times cols_2$.
  3. Định dạng hiển thị ma trận chuẩn: từng dòng có dạng `[v1][v2]...`.
- **Mô hình triển khai:** **MVC (Model - View - Controller)** chuẩn mực, đồng bộ hoàn toàn với các bài Sort.

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC PATTERN)

```
                       [Main] (Entry point)
                         │
                         ▼
                [MatrixController]
            ┌────────────┴─────────────┐
            ▼                          ▼
      [MatrixView]                 [model.Matrix]
     + [Validator]              (add, subtract, multiply)
```

- **Model (`model.Matrix`):** Đại diện cho một ma trận toán học, lưu trữ kích thước `rows`, `cols` và mảng 2 chiều `data[][]`. Chứa các phép toán `add()`, `subtract()`, `multiply()`. Hoàn toàn độc lập, không in ra console.
- **View (`view.MatrixView` & `view.Validator`):** Quản lý toàn bộ giao diện: hiển thị menu, nhận lựa chọn, nhập kích thước hàng/cột, nhập từng phần tử ma trận, định dạng hiển thị biểu thức kết quả và hiển thị thông báo lỗi.
- **Controller (`controller.MatrixController`):** Điều phối toàn bộ luồng chương trình qua phương thức `run()`, xử lý vòng lặp menu và các hàm `handleAddition()`, `handleSubtraction()`, `handleMultiplication()`.
- **Main (`main.Main`):** Điểm khởi chạy tối giản, gọi `new MatrixController().run()`.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & THUẬT TOÁN

### 3.1. `main.Main`
- Chứa hàm `public static void main(String[] args)` khởi tạo `MatrixController` và kích hoạt `run()`.

### 3.2. `model.Matrix` (Cốt lõi hướng đối tượng)
- **Thuộc tính:**
  ```java
  private int rows;
  private int cols;
  private int[][] data;
  ```
  Tất cả đều là `private`, đảm bảo tính bao đóng.
- **Constructor 1 `Matrix(int rows, int cols)`:** Khởi tạo ma trận kích thước $rows \times cols$. Ném ngoại lệ nếu $rows \le 0$ hoặc $cols \le 0$.
- **Constructor 2 `Matrix(int[][] data)`:** Khởi tạo từ mảng 2 chiều có sẵn. Tự động xác định `rows = data.length` và `cols = data[0].length`.
- **Phương thức `add(Matrix other)`:**
  - Kiểm tra điều kiện: `if (rows != other.rows || cols != other.cols) throw new Exception("Rows and cols two matrix must be same");`
  - Khởi tạo mảng kết quả `int dataResult[][] = new int[rows][cols];`
  - Hai vòng lặp lồng nhau duyệt qua từng phần tử:
    `dataResult[i][j] = this.data[i][j] + other.data[i][j];`
  - Trả về đối tượng `new Matrix(dataResult)`.
- **Phương thức `subtract(Matrix other)`:**
  - Tương tự phép cộng, tính hiệu: `dataResult[i][j] = this.data[i][j] - other.data[i][j];`
- **Phương thức `multiply(Matrix other)` (Nhân ma trận):**
  - Kiểm tra điều kiện: `if (cols != other.rows) throw new Exception("Cols of matrix 1 must be equal rows of matrix 2");`
  - Ma trận kết quả có kích thước $rows \times other.cols$: `int dataResult[][] = new int[rows][other.cols];`
  - Thuật toán nhân ma trận dùng **3 vòng lặp lồng nhau**:
    ```java
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < other.cols; j++) {
            for (int k = 0; k < cols; k++) {
                dataResult[i][j] += this.data[i][k] * other.data[k][j];
            }
        }
    }
    ```
  - Trả về đối tượng `new Matrix(dataResult)`.
- **Phương thức `toString()`:**
  - Duyệt từng hàng, in các phần tử theo định dạng `[%d]`, xuống dòng sau mỗi hàng.

### 3.3. `view.MatrixView`
- **`getMenuChoice()`:** Hiển thị menu 4 mục và nhận lựa chọn từ 1 đến 4.
- **`inputDimension(String message, int min, int max)`:** Nhận số hàng/cột có bẫy lỗi.
- **`inputMatrix(String label, int rows, int cols)`:** Nhận từng phần tử `[i][j]` từ bàn phím và đóng gói thành `Matrix`.
- **`displayResult(Matrix m1, String operator, Matrix m2, Matrix result)`:** Định dạng hiển thị biểu thức ma trận chuẩn:
  ```
  -------------Result-------------
  [Ma trận 1]
  + (hoặc - hoặc *)
  [Ma trận 2]
  =
  [Ma trận kết quả]
  ```

### 3.4. `controller.MatrixController`
- **`run()`:** Vòng lặp `while(true)` điều phối:
  - Chọn 1: `handleAddition()`
  - Chọn 2: `handleSubtraction()`
  - Chọn 3: `handleMultiplication()`
  - Chọn 4: Thoát khỏi vòng lặp và kết thúc chương trình.

---

## 4. PHÂN TÍCH ĐỘ PHỨC TẠP THUẬT TOÁN

| Phép toán | Độ phức tạp thời gian (Time Complexity) | Độ phức tạp không gian (Space Complexity) |
|---|---|---|
| **Cộng (Addition)** | $O(rows \times cols)$ | $O(rows \times cols)$ (ma trận kết quả mới) |
| **Trừ (Subtraction)** | $O(rows \times cols)$ | $O(rows \times cols)$ (ma trận kết quả mới) |
| **Nhân (Multiplication)** | $O(rows_1 \times cols_1 \times cols_2)$ $\approx O(n^3)$ | $O(rows_1 \times cols_2)$ (ma trận kết quả mới) |

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Tại sao em lại thiết kế `Matrix` thành một class đối tượng riêng biệt thay vì dùng mảng 2 chiều `int[][]` trực tiếp trong Controller?
> **Trả lời:** Đây chính là tinh thần của **Lập trình hướng đối tượng (OOP)**. Nếu chỉ dùng mảng 2 chiều, mã nguồn sẽ mang tính thủ tục (Procedural Programming), khó tái sử dụng và dễ sai sót khi truyền kích thước mảng khắp nơi. Việc đóng gói ma trận thành lớp `Matrix` giúp:
> 1. Gom dữ liệu (`rows`, `cols`, `data`) và các hành vi toán học (`add`, `subtract`, `multiply`) vào một thể thống nhất (Encapsulation).
> 2. Đảm bảo tính toàn vẹn: ma trận tự kiểm tra kích thước của chính nó trước khi thực hiện phép toán, nếu không hợp lệ sẽ tự ném ngoại lệ bảo vệ dữ liệu.

### Q2: Chương trình của em xử lý thế nào để ngăn người dùng nhập ma trận sai kích thước trong phép nhân?
> **Trả lời:** Em xử lý bảo vệ ngay từ tầng View kết hợp với tầng nghiệp vụ trong Model:
> - Tại tầng View/Controller: Khi nhập ma trận 2 trong phép nhân, số hàng $rows_2$ được cố định bằng đúng $cols_1$ của ma trận 1 thông qua `view.inputDimension(..., cols1, cols1)`. Người dùng nhập số khác sẽ bị báo lỗi ngay lập tức.
> - Tại tầng Model `Matrix`: Trong hàm `multiply()`, em vẫn kiểm tra phòng vệ `if (cols != other.rows) throw new Exception(...)` để đảm bảo an toàn tuyệt đối.

### Q3: Giải thích ý nghĩa của 3 vòng lặp lồng nhau trong thuật toán nhân ma trận?
> **Trả lời:**
> - Vòng lặp ngoài cùng $i$ (chạy theo số hàng của ma trận 1): Duyệt qua từng hàng của ma trận 1.
> - Vòng lặp thứ hai $j$ (chạy theo số cột của ma trận 2): Duyệt qua từng cột của ma trận 2. Cặp $(i, j)$ xác định tọa độ của phần tử cần tính trong ma trận kết quả.
> - Vòng lặp trong cùng $k$ (chạy từ 0 đến $cols_1 - 1$): Thực hiện tích vô hướng giữa hàng $i$ của ma trận 1 và cột $j$ của ma trận 2: $\sum (A[i][k] \times B[k][j])$.

### Q4: Các phương thức `add`, `subtract`, `multiply` của em có làm thay đổi dữ liệu của ma trận gốc không?
> **Trả lời:** Không, tất cả các phương thức này đều tuân thủ nguyên lý **Tính bất biến (Immutability)** đối với dữ liệu đầu vào. Các phép toán chỉ đọc dữ liệu từ `this` và `other`, sau đó tính toán ra mảng mới và trả về một đối tượng `new Matrix(dataResult)`. Ma trận gốc hoàn toàn không bị biến đổi giá trị.
