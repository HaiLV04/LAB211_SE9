# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0005 (MERGE SORT)

---

## 1. TỔNG QUAN BÀI TOÁN & MỤC TIÊU
- **Mã bài lab:** J1.S.P0005 (Merge Sort algorithm).
- **Mục tiêu:**
  1. Người dùng nhập kích thước mảng $n$ (số nguyên dương $> 0$).
  2. Chương trình tự động sinh ngẫu nhiên $n$ phần tử trong khoảng $[-n, n]$.
  3. Hiển thị mảng ban đầu (Unsorted array).
  4. Thực hiện thuật toán sắp xếp trộn **Merge Sort** theo tư tưởng **Chia để trị (Divide and Conquer)**.
  5. Có in chi tiết từng bước chia mảng (Divide) và trộn mảng (Merge) ra console để minh họa rõ nét tiến trình thuật toán.
  6. Hiển thị mảng sau khi sắp xếp (Sorted array).
- **Kiến trúc:** Chuẩn **MVC (Model - View - Controller)**.

---

## 2. KIẾN TRÚC MÃ NGUỒN (MVC PATTERN)

```
[Main]
  │ (Entry point)
  ▼
[ArrayController]
  ├──> Gọi [ArrayView] để nhập n (> 0)
  ├──> Gọi [ArrayModel] để generateRandomArray(n)
  ├──> Gọi [ArrayView] để hiển thị Unsorted array
  ├──> Gọi [ArrayModel] để mergeSort() (in step-by-step trace)
  └──> Gọi [ArrayView] để hiển thị Sorted array
```

- **Model (`model.ArrayModel`):** Quản lý mảng `int[] array`, biến đếm bước `stepCounter`, logic đệ quy `mergeSort(left, right)` và logic trộn `merge(left, mid, right)`.
- **View (`view.ArrayView`):** Nhập số nguyên dương an toàn (bắt `NumberFormatException`, rỗng, $\le 0$) và định dạng mảng console dạng `[a, b, c]`.
- **Controller (`controller.ArrayController`):** Điều phối luồng làm việc giữa Model và View.
- **Main (`main.Main`):** Điểm khởi động chương trình.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & THUẬT TOÁN

### 3.1. `model.ArrayModel` (Trọng tâm thuật toán)
- **Thuộc tính:**
  ```java
  private int[] array;
  private int stepCounter; // Đếm số thứ tự các bước thực thi để trace
  ```
- **`generateRandomArray(int n)`:** Khởi tạo mảng và sinh giá trị ngẫu nhiên trong khoảng $[-n, n]$ bằng `random.nextInt(2 * n + 1) - n`.
- **`mergeSort()`:**
  - Kiểm tra điều kiện biên: `if (array == null || array.length < 2) return;`
  - Đặt lại `stepCounter = 1;` và kích hoạt hàm đệ quy: `mergeSort(0, array.length - 1);`.
- **`mergeSort(int left, int right)` (Hàm đệ quy chia mảng):**
  - **Điều kiện dừng (Base case):** `if (left >= right) return;` (khi mảng con chỉ còn 1 phần tử hoặc rỗng, nó mặc nhiên đã được sắp xếp).
  - **Chia (Divide):** Xác định vị trí giữa `int mid = left + (right - left) / 2;`
  - **Đệ quy nửa trái:** `mergeSort(left, mid);`
  - **Đệ quy nửa phải:** `mergeSort(mid + 1, right);`
  - **Trị & Trộn (Conquer & Merge):** `merge(left, mid, right);`
- **`merge(int left, int mid, int right)` (Hàm trộn hai mảng con đã có thứ tự):**
  - **Tạo mảng tạm:**
    - Nửa trái kích thước $n_1 = mid - left + 1$: `int[] leftArray = new int[n1];`
    - Nửa phải kích thước $n_2 = right - mid$: `int[] rightArray = new int[n2];`
  - **Chép dữ liệu:** Sao chép các phần tử tương ứng từ `array` gốc vào 2 mảng tạm `leftArray` và `rightArray`.
  - **Trộn (2 con trỏ $i$ và $j$):**
    ```java
    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (leftArray[i] <= rightArray[j]) {
            array[k] = leftArray[i];
            i++;
        } else {
            array[k] = rightArray[j];
            j++;
        }
        k++;
    }
    ```
    *Lưu ý quan trọng:* Điều kiện so sánh `leftArray[i] <= rightArray[j]` (có dấu $\le$) giúp thuật toán đảm bảo **Tính ổn định (Stability)**.
  - **Sao chép phần tử còn dư:** Nếu một trong hai mảng con duyệt hết trước, chép toàn bộ các phần tử còn lại của mảng kia vào mảng chính.

---

## 4. PHÂN TÍCH THUẬT TOÁN & ĐỘ PHỨC TẠP

| Tiêu chí | Đánh giá | Giải thích toán học |
|---|---|---|
| **Best Case (Tốt nhất)** | **$O(n \log n)$** | Cây đệ quy luôn có chiều cao $\log_2 n$, mỗi tầng đều cần trộn tổng cộng $n$ phần tử. |
| **Average Case (Trung bình)** | **$O(n \log n)$** | Luôn chia đôi mảng đều đặn bất chấp phân bố dữ liệu ban đầu. |
| **Worst Case (Xấu nhất)** | **$O(n \log n)$** | Không có trường hợp suy biến nào làm Merge Sort chạy chậm hơn $O(n \log n)$. |
| **Space Complexity (Bộ nhớ)** | **$O(n)$** | Cần cấp phát bộ nhớ bổ trợ cho các mảng tạm `leftArray` và `rightArray` khi trộn. |
| **Tính ổn định (Stability)** | **Stable (Ổn định)** | Bảo toàn tuyệt đối thứ tự ban đầu của các phần tử có giá trị bằng nhau. |

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Merge Sort áp dụng mô hình thiết kế thuật toán nào? Giải thích 3 bước của nó?
> **Trả lời:** Merge Sort áp dụng mô hình **Chia để trị (Divide and Conquer)** gồm 3 bước:
> 1. **Divide (Chia):** Chia mảng hiện tại thành 2 nửa bằng nhau tại vị trí `mid = left + (right - left) / 2`.
> 2. **Conquer (Trị):** Gọi đệ quy giải quyết sắp xếp độc lập từng nửa mảng con.
> 3. **Combine/Merge (Kết hợp):** Trộn 2 nửa mảng con đã được sắp xếp thành một mảng hoàn chỉnh duy nhất.

### Q2: So sánh Merge Sort với Quick Sort: Khi nào nên dùng thuật toán nào?
> **Trả lời:**
> - **Merge Sort:**
>   - *Ưu điểm:* Thời gian chạy ổn định tuyệt đối $O(n \log n)$ ở mọi trường hợp; là thuật toán ổn định (Stable). Rất tốt khi sắp xếp danh sách liên kết (Linked List) hoặc dữ liệu lớn lưu ở bộ nhớ ngoài (External Sorting).
>   - *Nhược điểm:* Tốn thêm bộ nhớ phụ $O(n)$.
> - **Quick Sort:**
>   - *Ưu điểm:* Sắp xếp tại chỗ ($O(1)$ phụ trợ), tốc độ thực tế nhanh hơn Merge Sort do cache-locality tốt hơn.
>   - *Nhược điểm:* Không ổn định (Unstable) và Worst case có thể bị suy biến thành $O(n^2)$.

### Q3: Tại sao Merge Sort lại đảm bảo được tính ổn định (Stable Sort)?
> **Trả lời:** Tính ổn định được quyết định ở bước so sánh trong hàm `merge`:
> `if (leftArray[i] <= rightArray[j])`
> Khi hai phần tử ở mảng trái và mảng phải bằng nhau, thuật toán ưu tiên lấy phần tử bên `leftArray` (phần tử đứng trước ban đầu) đưa vào mảng kết quả trước. Nhờ vậy thứ tự ban đầu giữa hai phần tử bằng nhau được bảo toàn nguyên vẹn.

### Q4: Nếu kích thước mảng là 1 phần tử hoặc rỗng thì đệ quy dừng thế nào?
> **Trả lời:** Điều kiện dừng cơ sở của hàm là `if (left >= right) return;`. Khi mảng chỉ có 1 phần tử thì `left == right`, hàm sẽ lập tức `return` mà không thực hiện chia hay trộn tiếp, đảm bảo không bị lỗi đệ quy vô tận (`StackOverflowError`).
