# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0009 (FIBONACCI)

---

## 1. TỔNG QUAN BÀI TOÁN & MỤC TIÊU
- **Mã bài lab:** J1.S.P0009 (Find 45 sequence Fibonacci numbers).
- **Mục tiêu:**
  1. Tính toán và hiển thị dãy 45 số Fibonacci đầu tiên (từ $F_0$ đến $F_{44}$).
  2. Bắt buộc sử dụng phương pháp **đệ quy (Recursion)** theo định nghĩa toán học: $F_0 = 0$, $F_1 = 1$, $F_n = F_{n-1} + F_{n-2}$.
  3. Áp dụng kỹ thuật tối ưu hóa bộ nhớ đệm **Memoization (Quy hoạch động)** để khắc phục nhược điểm bùng nổ hàm mũ thời gian tính toán của đệ quy thuần túy.
- **Kiến trúc áp dụng:** **MVC (Model - View - Controller)** chuẩn mực, phân tách độc lập giữa xử lý dữ liệu và giao diện hiển thị.

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC PATTERN)

```
[Main]
  │ (Khởi tạo Controller với sequenceLength = 45)
  ▼
[Controller]
  ├──> Gọi [View] để in tiêu đề ("The 45 sequence fibonacci:")
  ├──> Gọi [Fibonacci Model] để getSequence() -> trả về int[] sequence
  ├──> Gọi [View] để displayFibonacciSequence(sequence) (chuẩn format đề bài)
  └──> Gọi [View] để hiển thị các thông tin kiểm thử chi tiết (positions, format mảng, testcases)
```

- **Model (`model.Fibonacci`):** Chứa mảng đệm `private int[] fibonacci;`, thuật toán đệ quy có nhớ `getFibonacci(int index)` và phương thức cung cấp toàn bộ dãy `getSequence()`. Hoàn toàn không chứa câu lệnh xuất console.
- **View (`view.View`):** Đảm nhiệm toàn bộ việc hiển thị ra console, định dạng chuỗi, dấu phẩy, dấu chấm, nhãn kiểm thử `Index i: ...`, `F(i) = ...`.
- **Controller (`controller.Controller`):** "Nhạc trưởng" điều phối luồng: lấy dữ liệu từ Model và truyền trực tiếp dữ liệu thô sang View để hiển thị. Hỗ trợ Dependency Injection.
- **Main (`main.Main`):** Điểm khởi chạy chương trình tối giản.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & PHƯƠNG THỨC

### 3.1. `main.Main`
- Chứa hàm `main(String[] args)` khởi tạo `Controller` và gọi phương thức `run()`.

### 3.2. `model.Fibonacci` (Cốt lõi bài toán)
- **Thuộc tính:** `private int[] fibonacci;`
  - Mảng này dùng để lưu trữ các giá trị $F_i$ đã được tính. Khi khởi tạo mảng, mặc định các phần tử trong Java đều mang giá trị `0`.
- **Constructor `Fibonacci(int numberOfFibo)`:**
  - Cấp phát bộ nhớ: `fibonacci = new int[numberOfFibo];` (độ dài 45).
- **Phương thức `public int getFibonacci(int index)`:**
  - *Bước 1: Điều kiện dừng cơ sở (Base cases):*
    ```java
    if (index == 0 || index == 1) {
        fibonacci[index] = index;
        return index;
    }
    ```
    Nếu $index = 0 \rightarrow$ lưu và trả về 0; nếu $index = 1 \rightarrow$ lưu và trả về 1.
  - *Bước 2: Kiểm tra bộ nhớ đệm (Memoization Lookup):*
    ```java
    if (fibonacci[index] != 0) {
        return fibonacci[index];
    }
    ```
    Nếu `fibonacci[index]` khác 0, nghĩa là số Fibonacci tại vị trí này **đã từng được tính toán trước đó**, chương trình trả về ngay lập tức với thời gian $O(1)$ mà không cần gọi lại đệ quy.
  - *Bước 3: Tính toán đệ quy và lưu vết (Compute and Cache):*
    ```java
    fibonacci[index] = getFibonacci(index - 1) + getFibonacci(index - 2);
    return fibonacci[index];
    ```
    Nếu chưa tính, gọi đệ quy 2 nhánh con, cộng lại, **gán vào mảng `fibonacci[index]`** rồi mới trả về giá trị.
- **Phương thức `public int[] getSequence()`:**
  - Duyệt từ $0$ đến hết mảng, gọi `getFibonacci(i)` để đảm bảo mảng được tính đủ toàn bộ dãy số và trả về mảng kết quả.

### 3.3. `view.View`
- **`displayTitle()`:** In tiêu đề thông báo `The 45 sequence fibonacci:`.
- **`displayFibonacciSequence(int[] sequence)`:**
  - In dãy 45 số Fibonacci theo chuẩn đề bài: các số ngăn cách bởi `, ` và số cuối kết thúc bằng `.`.
- **`displayPositions(int[] sequence)`:**
  - In chi tiết từng vị trí `Index 0: 0`, `Index 1: 1`, ..., `Index 44: 701408733`.
- **`displayArrayFormat(int[] values)`:**
  - In định dạng mảng `[F(0)=0, F(1)=1, ...]`.
- **`displayTestCases(int f0, int f1, int fLast, int lastIndex)`:**
  - In các giá trị chốt chặn để kiểm chứng cơ sở: $F(0)$, $F(1)$, $F(44)$.

### 3.4. `controller.Controller`
- Thuộc tính: `private final Fibonacci model;`, `private final View view;`, `private final int sequenceLength;`.
- **`run()`:**
  ```java
  view.displayTitle();
  int[] sequence = model.getSequence();

  // 1. Hiển thị dãy 45 số theo đúng yêu cầu đề bài
  view.displayFibonacciSequence(sequence);

  // 2. Hiển thị thông tin kiểm chứng chi tiết qua View
  view.displayPositions(sequence);
  view.displayArrayFormat(sequence);
  view.displayTestCases(sequence[0], sequence[1], sequence[sequenceLength - 1], sequenceLength - 1);
  ```

---

## 4. PHÂN TÍCH THUẬT TOÁN & SO SÁNH HIỆU NĂNG

### So sánh Đệ quy thường vs Đệ quy có Memoization:

| Tiêu chí | Đệ quy thường (Naive Recursion) | Đệ quy Memoization (Code của bài) |
|---|---|---|
| **Công thức** | `return fib(n-1) + fib(n-2);` | Lưu kết quả vào mảng `fibonacci[]` |
| **Độ phức tạp thời gian (Time Complexity)** | **$O(2^n)$ (Hàm mũ)** | **$O(n)$ (Tuyến tính)** |
| **Số lần gọi hàm tính $F_{45}$** | Hơn **$2.2 \times 10^{12}$** lần gọi (sẽ bị đơ máy / Timeout) | Chỉ đúng **$45$** lần tính mới! Chạy trong chưa đầy 1 mili-giây. |
| **Độ phức tạp không gian (Space Complexity)** | $O(n)$ do độ sâu call-stack | $O(n)$ (bao gồm call-stack và mảng lưu `fibonacci`) |

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Tại sao không dùng vòng lặp `for` để tính Fibonacci cho nhanh mà phải dùng đệ quy?
> **Trả lời:**
> Vòng lặp `for` tính Fibonacci rất nhanh và không tốn bộ nhớ stack. Tuy nhiên, **đặc tả của đề bài lab J1.S.P0009 quy định rõ: "Use recursion method to find 45 sequence Fibonacci"**. Do đó, bắt buộc phải dùng đệ quy để đáp ứng đúng yêu cầu của đề bài.

### Q2: Tại sao đệ quy thông thường lại bị chậm (chạy lâu) khi tính đến số thứ 45? Em giải quyết bằng cách nào?
> **Trả lời:**
> - Đệ quy thông thường có cây nhị phân gọi hàm lặp lại rất nhiều lần những giá trị giống nhau (ví dụ: để tính $F_5$ phải tính $F_3$ hai lần, $F_2$ ba lần...). Với $n = 45$, số lần gọi hàm lên tới $2^{45} \approx 3.5 \times 10^{13}$ lần, dẫn tới thời gian chạy cực lâu hoặc tràn bộ nhớ stack.
> - Em giải quyết bằng kỹ thuật **Memoization (Quy hoạch động có nhớ)**: Dùng mảng `fibonacci[]` để lưu lại kết quả của mỗi số Fibonacci ngay sau khi tính xong. Trước khi gọi đệ quy nhánh con, hàm kiểm tra nếu phần tử đã có giá trị thì lấy ra dùng ngay với độ phức tạp $O(1)$, giảm tổng thời gian chạy xuống còn $O(n)$ (chưa tới 1ms).

### Q3: Tại sao kiểu dữ liệu trả về của bài này dùng `int` mà không dùng `long` hay `BigInteger`?
> **Trả lời:**
> Giá trị Fibonacci thứ 45 ($F_{44}$) là `701,408,733`. Giá trị lớn nhất của kiểu `int` 32-bit trong Java là `2,147,483,647`. Vì $701,408,733 < 2,147,483,647$ nên hoàn toàn không bị tràn số (overflow). Do đó, sử dụng kiểu `int` vừa tối ưu bộ nhớ vừa hoàn toàn an toàn và chính xác. (Nếu đề bài yêu cầu từ số thứ 47 trở lên thì bắt buộc phải chuyển sang kiểu `long` hoặc `BigInteger`).

### Q4: Kiến trúc MVC trong bài Fibonacci này được phân chia nhiệm vụ như thế nào?
> **Trả lời:**
> - `Model (model.Fibonacci)`: Quản lý mảng lưu trữ, thuật toán đệ quy có nhớ `getFibonacci()` và cung cấp toàn bộ dãy số qua `getSequence()`. Hoàn toàn không chứa lệnh in ấn nào.
> - `View (view.View)`: Chuyên trách 100% việc hiển thị ra console, định dạng chuỗi số, dấu phẩy, dấu chấm, nhãn vị trí `Index i: ...` và kết quả kiểm thử.
> - `Controller (controller.Controller)`: Nhạc trưởng điều phối: lấy dữ liệu từ Model rồi chuyển cho View hiển thị. Controller không trực tiếp ghép chuỗi hiển thị, giúp mã nguồn phân tách độc lập (Separation of Concerns).
