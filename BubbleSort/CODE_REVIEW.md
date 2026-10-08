# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0001 (BUBBLE SORT)

---

## 1. TỔNG QUAN BÀI TOÁN & MỤC TIÊU
- **Mã bài lab:** J1.S.P0001 (Bubble Sort algorithm).
- **Mục tiêu:**
  1. Người dùng nhập kích thước mảng $n$ (số nguyên dương $> 0$).
  2. Chương trình tự động sinh ngẫu nhiên mảng gồm $n$ phần tử trong khoảng $[-n, n]$.
  3. Hiển thị mảng ban đầu (Unsorted array).
  4. Áp dụng thuật toán sắp xếp nổi bọt (Bubble Sort) có tối ưu hóa cờ `swapped` để sắp xếp mảng tăng dần.
  5. Hiển thị mảng sau khi sắp xếp (Sorted array).
- **Kiến trúc áp dụng:** Chuẩn **MVC (Model - View - Controller)**.

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC PATTERN)

```
[Main]
  │ (Entry point)
  ▼
[ArrayController]
  ├──> Gọi [ArrayView] để nhập kích thước mảng n
  ├──> Gọi [ArrayModel] để generateRandomArray(n)
  ├──> Gọi [ArrayView] để hiển thị Unsorted array
  ├──> Gọi [ArrayModel] để thực thi bubbleSort()
  └──> Gọi [ArrayView] để hiển thị Sorted array
```

- **Model (`model.ArrayModel`):** Quản lý mảng số nguyên `int[] array`, sinh mảng ngẫu nhiên và thực hiện thuật toán sắp xếp nổi bọt.
- **View (`view.ArrayView`):** Giao diện tương tác người dùng: nhập số nguyên dương kèm bẫy lỗi toàn diện và định dạng mảng console dạng `[a, b, c]`.
- **Controller (`controller.ArrayController`):** Nhận chỉ thị từ Main, kết nối luồng công việc giữa Model và View.
- **Main (`main.Main`):** Khởi tạo `ArrayController` và gọi phương thức `run()`.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & PHƯƠNG THỨC

### 3.1. `main.Main`
- Chứa phương thức `public static void main(String[] args)` để khởi tạo `ArrayController` và chạy chương trình. Đảm bảo lớp Main chỉ đóng vai trò kích hoạt.

### 3.2. `view.ArrayView`
- **Thuộc tính:** `private final Scanner scanner;` được gán cố định `new Scanner(System.in)`.
- **`inputPositiveInteger(String message)`:**
  - Nhập vào số nguyên dương ($n > 0$).
  - Sử dụng vòng lặp `while(true)` và `scanner.nextLine().trim()`.
  - Bẫy lỗi:
    - Rỗng / khoảng trắng (`input.isEmpty()`): Yêu cầu nhập lại.
    - Chữ cái / ký tự đặc biệt: Khối `try-catch(NumberFormatException)` thông báo lỗi và lặp lại.
    - Giá trị $\le 0$: Báo lỗi `Number must be greater than 0.` và yêu cầu nhập lại.
- **`displayArray(String message, int[] array)`:**
  - Dùng `StringBuilder` để ghép chuỗi: mở ngoặc `[`, duyệt qua từng phần tử, thêm dấu phẩy `, ` giữa các phần tử và đóng ngoặc `]`. Hiển thị kết quả ra console bằng `System.out.println()`.

### 3.3. `model.ArrayModel`
- **Thuộc tính:** `private int[] array;` bảo vệ tính toàn vẹn dữ liệu.
- **`generateRandomArray(int n)`:**
  - Tạo mảng với kích thước $n$.
  - Dùng `Random.nextInt(2 * n + 1) - n` để sinh ngẫu nhiên các số nguyên trong phạm vi $[-n, n]$.
- **`bubbleSort()` (Thuật toán cốt lõi):**
  - *Kiểm tra biên:* `if (array == null || array.length < 2) return;` (Mảng rỗng hoặc 1 phần tử không cần sắp xếp).
  - Khởi tạo biến cờ `boolean swapped;`
  - Vòng lặp ngoài `for (int i = 0; i < length - 1; i++)`: Chạy tối đa $n - 1$ lượt.
  - Mỗi lượt lặp ngoài, đặt `swapped = false`.
  - Vòng lặp trong `for (int j = 0; j < length - i - 1; j++)`:
    - Duyệt qua các cặp kề nhau. Giới hạn `length - i - 1` vì sau mỗi lượt $i$, phần tử lớn nhất đã "nổi" về vị trí cuối mảng, không cần so sánh lại.
    - So sánh: `if (array[j] > array[j + 1])`:
      - Gọi hàm `swap(j, j + 1)` để đổi chỗ hai phần tử.
      - Đánh dấu `swapped = true;`
  - **Tối ưu hóa:** `if (!swapped) break;` $\rightarrow$ Nếu trong cả vòng lặp trong không có lần hoán đổi nào, nghĩa là mảng đã được sắp xếp hoàn toàn, lập tức ngắt vòng lặp ngoài và kết thúc thuật toán.
- **`swap(int firstIndex, int secondIndex)`:**
  - Phương thức `private` trợ giúp: dùng biến tạm `int temporaryValue = array[firstIndex]` để hoán vị giá trị giữa 2 vị trí.
- **`getArray()`:** Trả về tham chiếu mảng hiện tại.

### 3.4. `controller.ArrayController`
- **`run()`:** Điều khiển toàn bộ kịch bản:
  1. Yêu cầu View nhập số phần tử `size`.
  2. Yêu cầu Model sinh mảng ngẫu nhiên theo `size`.
  3. Yêu cầu View hiển thị `Unsorted array`.
  4. Yêu cầu Model thực thi `bubbleSort()`.
  5. Yêu cầu View hiển thị `Sorted array`.

---

## 4. PHÂN TÍCH THUẬT TOÁN & ĐỘ PHỨC TẠP

| Trường hợp | Độ phức tạp thời gian (Time Complexity) | Điều kiện xảy ra & Giải thích |
|---|---|---|
| **Best Case (Tốt nhất)** | $O(n)$ | Mảng ban đầu đã được sắp xếp tăng dần. Thuật toán chỉ cần duyệt qua 1 lượt vòng lặp trong ($n - 1$ phép so sánh), biến `swapped` vẫn là `false` nên dừng ngay lập tức. |
| **Average Case (Trung bình)** | $O(n^2)$ | Các phần tử phân bố ngẫu nhiên. Số phép so sánh và đổi chỗ xấp xỉ $\frac{n(n-1)}{4}$. |
| **Worst Case (Xấu nhất)** | $O(n^2)$ | Mảng ban đầu bị đảo ngược hoàn toàn (giảm dần). Cần duyệt đủ $n - 1$ lượt và thực hiện tối đa $\frac{n(n-1)}{2}$ lần swap. |
| **Space Complexity (Bộ nhớ)** | $O(1)$ | Thuật toán sắp xếp tại chỗ (In-place sorting), chỉ dùng thêm 1 biến tạm khi hoán vị. |
| **Tính ổn định (Stability)** | **Stable (Ổn định)** | Vì điều kiện hoán đổi là so sánh nghiêm ngặt `array[j] > array[j + 1]`. Hai phần tử bằng nhau sẽ không đổi chỗ, giữ nguyên thứ tự ban đầu. |

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Thuật toán Bubble Sort hoạt động theo nguyên lý nào? Tại sao lại gọi là "nổi bọt"?
> **Trả lời:** Thuật toán hoạt động bằng cách so sánh liên tiếp các cặp phần tử kề nhau. Nếu phần tử trước lớn hơn phần tử sau thì hoán đổi vị trí. Qua mỗi vòng lặp, phần tử lớn nhất trong đoạn chưa sắp xếp sẽ "nổi" dần về phía cuối mảng, tương tự như các bọt khí nổi lên mặt nước.

### Q2: Tại sao vòng lặp trong của em lại chạy đến `length - i - 1` mà không phải `length - 1`?
> **Trả lời:** Vì sau mỗi lượt lặp thứ $i$ của vòng lặp ngoài, đã có chắc chắn $i$ phần tử lớn nhất được đưa về đúng vị trí cuối mảng. Do đó, việc duyệt đến `length - i - 1` giúp bỏ qua các phần tử đã nằm đúng vị trí, giảm bớt số phép so sánh dư thừa.

### Q3: Biến cờ `swapped` có tác dụng gì? Nếu bỏ nó đi thì thuật toán có sai không?
> **Trả lời:** Biến `swapped` dùng để tối ưu thời gian chạy. Nếu bỏ biến này thì thuật toán vẫn cho ra kết quả đúng, nhưng độ phức tạp trong trường hợp tốt nhất (Best case) sẽ bị tăng từ $O(n)$ lên $O(n^2)$ vì chương trình phải chạy đủ $n - 1$ vòng lặp ngoài bất kể mảng đã có thứ tự từ trước hay chưa.

### Q4: Thuật toán Bubble Sort của em có ổn định (Stable) không?
> **Trả lời:** Có, Bubble Sort là thuật toán ổn định (Stable Sort). Vì điều kiện hoán đổi của em là `array[j] > array[j + 1]` (lớn hơn nghiêm ngặt). Nếu hai phần tử có giá trị bằng nhau, chúng sẽ không bị tráo đổi vị trí, do đó giữ nguyên thứ tự tương đối ban đầu của các phần tử.

### Q5: Em bẫy lỗi nhập kích thước mảng như thế nào để người dùng không làm chương trình bị dừng đột ngột?
> **Trả lời:** Trong hàm `inputPositiveInteger` của lớp `ArrayView`, em đọc dữ liệu vào dưới dạng chuỗi bằng `scanner.nextLine().trim()`. Em kiểm tra rỗng, sau đó đưa vào khối `try-catch` để chuyển đổi bằng `Integer.parseInt()`. Bất kỳ ký tự chữ cái, số thực hay ký tự đặc biệt nào đều kích hoạt `NumberFormatException` và được bắt lại để hiển thị cảnh báo, không làm gián đoạn chương trình. Cuối cùng em kiểm tra điều kiện nghiệp vụ $number > 0$.
