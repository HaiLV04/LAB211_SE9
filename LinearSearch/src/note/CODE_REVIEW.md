# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0010 (LINEAR SEARCH)

---

## 1. TỔNG QUAN BÀI TOÁN & MỤC TIÊU
- **Mã bài lab:** J1.S.P0010 (Linear Search algorithm).
- **Mục tiêu:**
  1. Người dùng nhập số lượng phần tử của mảng $n$ ($n > 0$).
  2. Chương trình tự động sinh ngẫu nhiên mảng gồm $n$ số nguyên từ $0$ đến $n - 1$.
  3. **Hiển thị mảng ngẫu nhiên ban đầu:** `The array: [v1, v2, ...]`.
     *(Linear Search tìm kiếm trực tiếp trên mảng gốc chưa sắp xếp, **hoàn toàn không cần và không được sort mảng**).*
  4. Sau khi người dùng đã quan sát các phần tử trong mảng, yêu cầu người dùng nhập giá trị cần tìm kiếm (`searchValue`).
  5. Áp dụng thuật toán **tìm kiếm tuần tự (Linear Search)** để tìm và trả về **tất cả các chỉ số (indexes)** xuất hiện giá trị cần tìm trong mảng.
  6. Nếu không tìm thấy, thông báo `Can not found`. Nếu tìm thấy, in ra danh sách chỉ số dạng mảng `Found {searchValue} at index: [index1, index2, ...]`.
- **Kiến trúc áp dụng:** **MVC (Model - View - Controller)** chuẩn mực, đồng bộ với toàn bộ các dự án trong LAB211.

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC PATTERN)

```
[Main]
  │ (Entry point)
  ▼
[ArrayController]
  ├──> Gọi [ArrayView] để nhập kích thước mảng n (> 0)
  ├──> Gọi [ArrayModel] để generateRandomArray(n)
  ├──> Gọi [ArrayView] để displayArray ("The array: ", array)
  ├──> Gọi [ArrayView] để nhập giá trị cần tìm searchValue
  ├──> Gọi [ArrayModel] để findAllIndex(searchValue) -> trả về int[] foundIndices
  └──> Gọi [ArrayView] để displaySearchResult(searchValue, foundIndices)
```

- **Model (`model.ArrayModel`):** Quản lý mảng dữ liệu `private int[] array;`, các hàm khởi tạo sinh số ngẫu nhiên, phương thức `linearSearch(int key)`, và phương thức `findAllIndex(int key)`. Hoàn toàn tách biệt khỏi việc in ấn console. Không chứa các phương thức sắp xếp (Bubble Sort) để giữ đúng nguyên lý Single Responsibility và bản chất bài toán.
- **View (`view.ArrayView`):** Giao diện Console chuyên trách: nhập số nguyên dương, nhập số nguyên tìm kiếm (bắt `NumberFormatException`, rỗng, số $\le 0$), định dạng hiển thị mảng `displayArray()` và hiển thị kết quả tìm kiếm `displaySearchResult()`.
- **Controller (`controller.ArrayController`):** Điều khiển luồng chương trình qua phương thức `run()`, kết nối chặt chẽ giữa Model và View.
- **Main (`main.Main`):** Điểm khởi động chương trình tối giản (Entry point).

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & PHƯƠNG THỨC

### 3.1. `main.Main`
- Điểm khởi chạy tối giản, chứa hàm `main(String[] args)` khởi tạo `ArrayController` và gọi `run()`.

### 3.2. `view.ArrayView`
- **Thuộc tính:** `private final Scanner scanner;` được khởi tạo 1 lần duy nhất trong constructor.
- **`inputPositiveInteger(String message)`:**
  - Nhập kích thước mảng $n > 0$.
  - Bắt lỗi chuỗi rỗng/khoảng trắng bằng `.trim().isEmpty()`.
  - Bắt lỗi không phải số bằng khối `try-catch(NumberFormatException)`.
  - Kiểm tra điều kiện $number > 0$.
- **`inputInteger(String message)`:**
  - Nhập giá trị cần tìm (cho phép cả số âm, 0, số dương).
- **`displayArray(String message, int[] array)`:**
  - Dùng `StringBuilder` ghép chuỗi `[v1, v2, ...]` tối ưu bộ nhớ heap và in ra màn hình.
- **`displaySearchResult(int searchValue, int[] foundIndices)`:**
  - Nếu `foundIndices` rỗng $\rightarrow$ in thông báo `Can not found`.
  - Nếu tìm thấy $\rightarrow$ in `Found {searchValue} at index: ` kèm mảng các chỉ số `Arrays.toString(foundIndices)`.

### 3.3. `model.ArrayModel`
- **Thuộc tính:** `private int[] array;` (đảm bảo tính đóng gói Encapsulation).
- **`generateRandomArray(int n)`:**
  - Cấp phát mảng: `array = new int[n];`
  - Sinh số ngẫu nhiên từ $0$ đến $n - 1$: `array[i] = random.nextInt(n);`
- **`linearSearch(int key)`:**
  - Duyệt tuần tự từ đầu mảng đến cuối mảng: `for (int i = 0; i < array.length; i++)`.
  - Nếu `array[i] == key` $\rightarrow$ trả về chỉ số `i` đầu tiên tìm thấy. Duyệt hết mà không thấy $\rightarrow$ trả về `-1`.
- **`findAllIndex(int key)` (Phương thức tìm tất cả vị trí):**
  - Khởi tạo danh sách động `ArrayList<Integer> indexList = new ArrayList<>();` vì ta chưa biết trước có bao nhiêu phần tử trùng khớp với `key`.
  - Duyệt qua từng phần tử của mảng:
    ```java
    for (int i = 0; i < array.length; i++) {
        if (array[i] == key) {
            indexList.add(i);
        }
    }
    ```
  - Chuyển từ `ArrayList<Integer>` sang mảng nguyên thủy `int[]`:
    ```java
    int[] indices = new int[indexList.size()];
    for (int i = 0; i < indexList.size(); i++) {
        indices[i] = indexList.get(i);
    }
    return indices;
    ```
- **`getArray()`:** Trả về mảng số nguyên hiện tại.

### 3.4. `controller.ArrayController`
- **`run()`:** Luồng thực thi chuẩn theo đúng đề bài:
  ```java
  int size = arrayView.inputPositiveInteger("Enter number of array: ");
  arrayModel.generateRandomArray(size);

  arrayView.displayArray("The array: ", arrayModel.getArray());

  int searchValue = arrayView.inputInteger("Enter search value: ");
  int[] foundIndices = arrayModel.findAllIndex(searchValue);
  arrayView.displaySearchResult(searchValue, foundIndices);
  ```

---

## 4. PHÂN TÍCH THUẬT TOÁN LINEAR SEARCH

| Tiêu chí | Đánh giá | Giải thích chi tiết |
|---|---|---|
| **Thời gian tốt nhất (Best Case)** | $O(1)$ (với hàm tìm vị trí đầu tiên) | Phần tử cần tìm nằm ngay ở chỉ số đầu tiên `0`. |
| **Thời gian trung bình (Average Case)** | $O(n)$ | Cần duyệt trung bình $\frac{n}{2}$ phần tử. |
| **Thời gian xấu nhất (Worst Case)** | $O(n)$ | Phần tử nằm ở cuối mảng hoặc không tồn tại trong mảng. |
| **Hàm `findAllIndex`** | Luôn luôn là **$O(n)$** | Vì bắt buộc phải duyệt hết $100\%$ các phần tử từ $0$ đến $n-1$ để tìm tất cả các vị trí trùng lặp. |
| **Không gian bộ nhớ (Space Complexity)** | $O(k)$ | Với $k$ là số lượng phần tử tìm thấy ($k \le n$). Bộ nhớ mảng `indexList`. |

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Tại sao bài Linear Search lại KHÔNG cần sắp xếp mảng trước khi tìm kiếm?
> **Trả lời:**
> 1. **Bản chất thuật toán:** Linear Search duyệt tuần tự từng phần tử từ đầu đến cuối mảng ($O(n)$), nên nó hoạt động chính xác trên **mọi tập dữ liệu bất kỳ mà không đòi hỏi mảng phải có thứ tự**.
> 2. **Chi phí thuật toán:** Nếu ta sắp xếp mảng trước, ta phải tốn thêm chi phí $O(n^2)$ (Bubble Sort) hoặc $O(n \log n)$, làm mất đi ý nghĩa của Linear Search. Hơn nữa, nếu mảng đã được sắp xếp thì ta nên dùng Binary Search ($O(\log n)$) chứ không ai dùng Linear Search ($O(n)$) trên mảng đã có thứ tự.
> 3. **Tính chính xác của vị trí (Index):** Khi sắp xếp mảng, các phần tử sẽ bị đổi chỗ, khiến các chỉ số `index` tìm được không còn phản ánh đúng vị trí thực tế ban đầu trong mảng ngẫu nhiên mà chương trình đã sinh ra.

### Q2: So sánh ưu điểm và nhược điểm của Linear Search so với Binary Search?
> **Trả lời:**
> - **Ưu điểm của Linear Search:** Thuật toán rất đơn giản, cài đặt dễ dàng, và **hoạt động tốt trên cả dữ liệu chưa sắp xếp**.
> - **Nhược điểm:** Tốc độ chậm với mảng lớn vì độ phức tạp thời gian là $O(n)$. Binary Search đạt $O(\log n)$ nhanh hơn rất nhiều, nhưng bắt buộc mảng phải có thứ tự đã sắp xếp trước.

### Q3: Tại sao trong hàm `findAllIndex` em lại dùng `ArrayList` trung gian rồi mới đổi sang `int[]`?
> **Trả lời:** Mảng thông thường (`int[]`) trong Java có kích thước cố định khi khởi tạo. Tại thời điểm bắt đầu tìm kiếm, ta không thể biết trước có bao nhiêu phần tử bằng `key` (có thể là 0, 1 hoặc nhiều). Vì vậy, em dùng `ArrayList<Integer>` là danh sách động tự co giãn kích thước để lưu các index tìm được, sau đó mới convert sang mảng `int[]` đúng kích thước thực tế để trả về.

### Q4: Nếu mảng có kích thước 1 triệu phần tử thì Linear Search có hiệu quả không? Có cách nào tối ưu hơn không?
> **Trả lời:** Với 1 triệu phần tử, Linear Search sẽ phải duyệt qua tới 1 triệu phép so sánh trong trường hợp xấu nhất, hiệu năng sẽ kém. Nếu dữ liệu được tìm kiếm nhiều lần, ta nên sắp xếp mảng rồi dùng Binary Search ($O(\log n) \approx 20$ phép so sánh) hoặc nạp dữ liệu vào cấu trúc bảng băm `HashMap` với độ phức tạp tìm kiếm trung bình là $O(1)$.

### Q5: Mô hình MVC được áp dụng như thế nào trong bài này?
> **Trả lời:**
> - `Model (ArrayModel)`: Chỉ chứa mảng dữ liệu và các logic xử lý (sinh số ngẫu nhiên, tìm kiếm tuần tự Linear Search), không in ấn ra console.
> - `View (ArrayView)`: Chuyên trách tương tác console, nhập liệu an toàn và định dạng hiển thị.
> - `Controller (ArrayController)`: Điều phối kết nối giữa Model và View qua phương thức `run()`.
> - `Main`: Điểm vào tối giản kích hoạt Controller.
