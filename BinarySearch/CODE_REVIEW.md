# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0006 (BINARY SEARCH)

---

## 1. TỔNG QUAN BÀI TOÁN & MỤC TIÊU
- **Mã bài lab:** J1.S.P0006 (Binary Search algorithm).
- **Mục tiêu & Luồng nghiệp vụ chuẩn:**
  1. Người dùng nhập kích thước mảng $n$ (số nguyên dương $> 0$).
  2. Hệ thống sinh mảng gồm $n$ phần tử ngẫu nhiên trong khoảng $[-n, n]$.
  3. **Hiển thị mảng ban đầu (Unsorted array)** ra màn hình.
  4. Sắp xếp mảng tăng dần bằng Bubble Sort (bắt buộc vì **Binary Search chỉ hoạt động trên tập dữ liệu đã có thứ tự**).
  5. **Hiển thị mảng đã sắp xếp (Sorted array)** ra màn hình.
  6. **Người dùng quan sát mảng xong mới nhập giá trị cần tìm kiếm (`searchValue`)** $\rightarrow$ giúp việc kiểm thử trực quan và thuận tiện cho cả giảng viên lẫn sinh viên.
  7. Thực hiện Binary Search để tìm vị trí (index) của phần tử. In kết quả tìm thấy index hoặc thông báo `Can not found`.
- **Mô hình triển khai:** Chuẩn kiến trúc **MVC (Model - View - Controller)** kết hợp hướng đối tượng (OOP).

---

## 2. KIẾN TRÚC HỆ THỐNG (MVC PATTERN)

```
[Main]
  │ (Khởi chạy)
  ▼
[ArrayController]
  ├──> Gọi [ArrayView] để nhập kích thước n (> 0)
  ├──> Gọi [ArrayModel] để generateRandomArray(n)
  ├──> Gọi [ArrayView] để hiển thị Unsorted array
  ├──> Gọi [ArrayModel] để bubbleSort()
  ├──> Gọi [ArrayView] để hiển thị Sorted array
  ├──> Gọi [ArrayView] để nhập searchValue
  ├──> Gọi [ArrayModel] để binarySearch(searchValue) -> trả về foundIndex
  └──> Gọi [ArrayView] để displaySearchResult(searchValue, foundIndex)
```

- **Model (`model.ArrayModel`):** Chứa dữ liệu mảng (`int[] array`), logic sinh mảng ngẫu nhiên, logic sắp xếp Bubble Sort và thuật toán tìm kiếm nhị phân Binary Search. Tách biệt hoàn toàn khỏi việc nhập/xuất console.
- **View (`view.ArrayView`):** Chuyên trách giao diện console: đọc input từ bàn phím, kiểm tra tính hợp lệ dữ liệu (số nguyên, số dương), in mảng và in kết quả tìm kiếm.
- **Controller (`controller.ArrayController`):** Nhận tương tác, điều phối luồng chạy giữa Model và View qua phương thức `run()`.
- **Main (`main.Main`):** Điểm khởi động chương trình (Entry point).

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & PHƯƠNG THỨC

### 3.1. `main.Main`
- Khởi tạo `ArrayController` và kích hoạt hàm `run()`. Giữ phương thức `main` tối giản nhất (2 dòng), tuân thủ nguyên lý Single Responsibility.

### 3.2. `view.ArrayView`
- **Thuộc tính:** `private final Scanner scanner;` được khởi tạo 1 lần duy nhất trong constructor để tránh việc tạo quá nhiều đối tượng Scanner gây rò rỉ tài nguyên.
- **`inputPositiveInteger(String message)`:**
  - Nhập số nguyên dương ($n > 0$) làm kích thước mảng.
  - Vòng lặp `while(true)`. Đọc chuỗi bằng `scanner.nextLine().trim()`.
  - Bẫy lỗi:
    - Rỗng/khoảng trắng (`input.isEmpty()`): Yêu cầu nhập lại.
    - Chữ cái, ký tự đặc biệt, số thực: Bắt `NumberFormatException` và lặp lại.
    - Điều kiện nghiệp vụ `number > 0`: Báo lỗi nếu $\le 0$.
- **`inputInteger(String message)`:**
  - Nhập giá trị cần tìm kiếm (chấp nhận cả số âm, 0, số dương).
- **`displayArray(String message, int[] array)`:**
  - Định dạng và in mảng ra console dạng `[v1, v2, v3]`.
  - Sử dụng `StringBuilder` để nối chuỗi thay vì dùng toán tử `+` trong vòng lặp nhằm tối ưu bộ nhớ Heap.
- **`displaySearchResult(int searchValue, int foundIndex)`:**
  - In `Found {value} at index: {index}` nếu `foundIndex != -1`.
  - In `Can not found` nếu `foundIndex == -1`.

### 3.3. `model.ArrayModel`
- **Thuộc tính:** `private int[] array;` (áp dụng tính bao đóng Encapsulation).
- **`generateRandomArray(int n)`:**
  - Khởi tạo `array = new int[n]`.
  - Sinh số ngẫu nhiên trong khoảng $[-n, n]$: `random.nextInt(2 * n + 1) - n`.
- **`bubbleSort()`:**
  - Sắp xếp tăng dần để thỏa mãn điều kiện tiên quyết của Binary Search.
  - Tối ưu với cờ `boolean swapped`: Dừng sớm nếu mảng đã có thứ tự.
- **`binarySearch(int key)` (Thuật toán cốt lõi):**
  - Kiểm tra biên: `if (array == null || array.length == 0) return -1;`
  - Khởi tạo 2 con trỏ chỉ số: `left = 0`, `right = array.length - 1`.
  - Vòng lặp `while (left <= right)`:
    - Tính điểm giữa an toàn: `int mid = left + (right - left) / 2;`
    - So sánh:
      1. `if (array[mid] == key)`: Trả về `mid` (tìm thấy ngay).
      2. `else if (key < array[mid])`: Thu hẹp sang nửa trái: `right = mid - 1`.
      3. `else`: Thu hẹp sang nửa phải: `left = mid + 1`.
  - Khi `left > right` mà không tìm thấy: Trả về `-1`.
- **`getArray()`:** Trả về mảng hiện tại.

### 3.4. `controller.ArrayController`
- **`run()`:** Xâu chuỗi toàn bộ nghiệp vụ theo đúng thứ tự logic mới:
  1. `int size = arrayView.inputPositiveInteger("Enter number of array: ");`
  2. `arrayModel.generateRandomArray(size);`
  3. `arrayView.displayArray("Unsorted array: ", arrayModel.getArray());`
  4. `arrayModel.bubbleSort();`
  5. `arrayView.displayArray("Sorted array: ", arrayModel.getArray());`
  6. `int searchValue = arrayView.inputInteger("Enter search value: ");`
  7. `int foundIndex = arrayModel.binarySearch(searchValue);`
  8. `arrayView.displaySearchResult(searchValue, foundIndex);`

---

## 4. PHÂN TÍCH THUẬT TOÁN & ĐỘ PHỨC TẠP

| Tiêu chí | Bubble Sort (Sắp xếp phụ trợ) | Binary Search (Tìm kiếm nhị phân) |
|---|---|---|
| **Thời gian tốt nhất (Best Case)** | $O(n)$ (mảng đã có thứ tự sẵn nhờ cờ `swapped`) | $O(1)$ (phần tử cần tìm nằm ngay tại `mid` đầu tiên) |
| **Thời gian trung bình (Average Case)** | $O(n^2)$ | $O(\log n)$ |
| **Thời gian xấu nhất (Worst Case)** | $O(n^2)$ (mảng bị đảo ngược) | $O(\log n)$ (phần tử ở cuối phạm vi hoặc không tồn tại) |
| **Không gian bộ nhớ (Space Complexity)** | $O(1)$ (in-place) | $O(1)$ (sử dụng biến lặp, không tốn thêm bộ nhớ call-stack) |

---

## 5. CÂU HỎI VẤN ĐÁP THẦY CÔ HAY HỎI (VÀ CÁCH TRẢ LỜI)

### Q1: Tại sao em lại hiển thị mảng chưa sắp xếp và đã sắp xếp rồi mới cho người dùng nhập giá trị cần tìm?
> **Trả lời:** Em thiết kế thứ tự này để tối ưu trải nghiệm người dùng (UX) và phục vụ kiểm thử:
> 1. Nếu bắt người dùng nhập giá trị cần tìm trước khi nhìn thấy mảng, người dùng hoàn toàn không biết mảng sinh ra những số nào, dẫn đến việc thử nghiệm tìm kiếm mang tính "đoán mò".
> 2. Bằng cách in mảng chưa sắp xếp $\rightarrow$ mảng đã sắp xếp $\rightarrow$ rồi mới nhập `searchValue`, giảng viên và người dùng có thể trực tiếp quan sát các giá trị hiện có trong mảng để chủ động chọn test case tìm thấy (có trong mảng) hoặc không tìm thấy (ngoài mảng) một cách trực quan, rõ ràng nhất.

### Q2: Tại sao trước khi gọi Binary Search em bắt buộc phải sắp xếp mảng?
> **Trả lời:** Vì thuật toán Binary Search dựa trên nguyên lý loại trừ một nửa không gian tìm kiếm ở mỗi bước so sánh. Nếu mảng chưa có thứ tự, việc so sánh `key` với `array[mid]` sẽ không thể kết luận được `key` nằm bên trái hay bên phải, dẫn đến tìm kiếm sai hoàn toàn.

### Q3: Tại sao em lại viết `mid = left + (right - left) / 2` mà không viết `mid = (left + right) / 2`?
> **Trả lời:** Viết `(left + right) / 2` có nguy cơ bị **Integer Overflow (tràn số nguyên 32-bit)** nếu `left` và `right` là các số rất lớn (tổng vượt quá `Integer.MAX_VALUE = 2,147,483,647` dẫn đến giá trị âm). Cách viết `left + (right - left) / 2` tương đương về mặt toán học nhưng hoàn toàn an toàn trước hiện tượng tràn số.

### Q4: Nếu trong mảng có nhiều phần tử trùng với `searchValue`, hàm Binary Search của em sẽ trả về vị trí nào?
> **Trả lời:** Thuật toán Binary Search cơ bản sẽ trả về chỉ số của phần tử đầu tiên mà con trỏ `mid` chạm tới (có thể là bất kỳ vị trí nào trong nhóm các phần tử trùng). Nếu muốn tìm vị trí xuất hiện đầu tiên nhất (first occurrence) hoặc cuối cùng nhất (last occurrence), ta cần điều chỉnh khi gặp `array[mid] == key` thì tiếp tục thu hẹp về bên trái (`right = mid - 1`) hoặc bên phải (`left = mid + 1`).

### Q5: Tại sao em lại tách riêng `ArrayView` và `ArrayModel` mà không viết gộp vào `Main`?
> **Trả lời:** Em áp dụng mô hình MVC và nguyên lý Single Responsibility (SRP trong SOLID). `ArrayModel` chỉ thuần túy xử lý thuật toán và dữ liệu (thuần Java logic, dễ tái sử dụng hoặc viết Unit Test), còn `ArrayView` phụ trách giao diện Console. Nếu sau này bài toán đổi giao diện sang GUI Swing hoặc JavaFX, em chỉ cần thay đổi tầng View mà không phải sửa đổi dù chỉ một dòng code trong Model.
