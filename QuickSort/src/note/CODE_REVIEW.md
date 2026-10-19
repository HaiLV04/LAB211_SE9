# TÀI LIỆU REVIEW CODE VỚI GIẢNG VIÊN - J1.S.P0004 (QUICK SORT)

---

## 1. TỔNG QUAN BÀI TOÁN & MỤC TIÊU
- **Mã bài lab:** J1.S.P0004 (Quick Sort algorithm).
- **Mục tiêu:**
  1. Người dùng nhập kích thước mảng $n$ (số nguyên dương $> 0$).
  2. Hệ thống sinh mảng gồm $n$ phần tử ngẫu nhiên trong khoảng $[-n, n]$.
  3. Hiển thị mảng ban đầu (Unsorted array).
  4. Thực hiện thuật toán sắp xếp nhanh **Quick Sort** sử dụng kỹ thuật phân hoạch **Hoare Partition** với chốt (Pivot) ở giữa mảng.
  5. Có in step-by-step trace chi tiết: giá trị Pivot, phân đoạn mảng, vị trí 2 con trỏ và các bước hoán đổi (swap).
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
  ├──> Gọi [ArrayModel] để quickSort() (in step-by-step trace)
  └──> Gọi [ArrayView] để hiển thị Sorted array
```

- **Model (`model.ArrayModel`):** Quản lý mảng dữ liệu `int[] array`, biến đếm bước `stepCounter`, logic đệ quy `quickSort(left, right)`, phân hoạch `partition(left, right)` và hàm phụ trợ `swap`.
- **View (`view.ArrayView`):** Giao diện nhập xuất console, kiểm tra tính hợp lệ dữ liệu.
- **Controller (`controller.ArrayController`):** Kết nối luồng giữa Model và View.
- **Main (`main.Main`):** Điểm khởi động ứng dụng.

---

## 3. GIẢI THÍCH CHI TIẾT TỪNG CLASS & THUẬT TOÁN

### 3.1. `model.ArrayModel` (Trọng tâm thuật toán)
- **Thuộc tính:** `private int[] array;`, `private int stepCounter;`.
- **`generateRandomArray(int n)`:** Sinh mảng ngẫu nhiên trong dải $[-n, n]$ bằng `random.nextInt(2 * n + 1) - n`.
- **`quickSort()`:**
  - Kiểm tra điều kiện biên: `if (array == null || array.length < 2) return;`
  - Đặt lại `stepCounter = 1;` và gọi `quickSort(0, array.length - 1);`.
- **`quickSort(int left, int right)` (Hàm đệ quy):**
  ```java
  int index = partition(left, right);
  if (left < index - 1) {
      quickSort(left, index - 1);
  }
  if (index < right) {
      quickSort(index, right);
  }
  ```
  Sau khi phân hoạch xong quanh Pivot, đệ quy tiếp tục giải quyết nửa bên trái `[left, index - 1]` và nửa bên phải `[index, right]`.
- **`partition(left, right)` (Phân hoạch Hoare Partition):**
  - **Chọn chốt (Pivot):** Chọn phần tử ở chính giữa phân đoạn:
    `int pivot = array[left + (right - left) / 2];`
    *Ưu điểm:* Chọn phần tử giữa giúp hạn chế tối đa nguy cơ rơi vào Worst case khi mảng đã có thứ tự sẵn.
  - **Thiết lập 2 con trỏ:** `int i = left;`, `int j = right;`.
  - **Vòng lặp tiến/lùi:**
    - Con trỏ $i$ chạy từ trái sang phải: `while (array[i] < pivot) i++;` (tìm phần tử $\ge$ pivot đứng sai vị trí).
    - Con trỏ $j$ chạy từ phải sang trái: `while (array[j] > pivot) j--;` (tìm phần tử $\le$ pivot đứng sai vị trí).
    - Nếu hai con trỏ chưa vượt qua nhau (`if (i <= j)`):
      - Hoán đổi: `swap(i, j);`
      - Tăng con trỏ trái: `i++;`
      - Giảm con trỏ phải: `j--;`
  - Vòng lặp dừng khi `i > j`. Trả về chỉ số `i` làm ranh giới chia 2 nửa đệ quy.

---

## 4. PHÂN TÍCH THUẬT TOÁN & ĐỘ PHỨC TẠP

| Tiêu chí | Đánh giá | Điều kiện xảy ra & Giải thích |
|---|---|---|
| **Best Case (Tốt nhất)** | **$O(n \log n)$** | Khi Pivot luôn chia mảng thành 2 nửa có kích thước tương đương nhau ở mọi bước đệ quy. Chiều cao cây đệ quy là $\log_2 n$. |
| **Average Case (Trung bình)** | **$O(n \log n)$** | Phân bố dữ liệu ngẫu nhiên, số phép so sánh trung bình là $1.39 n \log_2 n$. Tốc độ thực tế nhanh nhất trong các thuật toán so sánh $O(n \log n)$. |
| **Worst Case (Xấu nhất)** | **$O(n^2)$** | Khi Pivot liên tục rơi vào phần tử nhỏ nhất hoặc lớn nhất của mảng (ví dụ mảng đã sắp xếp mà lại chọn Pivot ở đầu hoặc cuối). Cây đệ quy bị lệch thành danh sách liên kết chiều cao $n$. |
| **Space Complexity (Bộ nhớ)** | **$O(\log n)$** | Quick Sort là sắp xếp tại chỗ (**In-place**), chỉ tốn bộ nhớ Call-Stack cho các tầng đệ quy ($O(\log n)$ trung bình, $O(n)$ xấu nhất). |
| **Tính ổn định (Stability)** | **Unstable (Không ổn định)** | Các phép hoán đổi từ xa giữa $i$ và $j$ có thể làm thay đổi thứ tự ban đầu của các phần tử có giá trị bằng nhau. |

---

## 5. BỘ CÂU HỎI VẤN ĐÁP VỚI GIẢNG VIÊN (REVIEW DEFENSE)

### Q1: Tại sao em lại chọn Pivot ở giữa mảng `array[left + (right - left) / 2]` thay vì chọn phần tử đầu tiên `array[left]`?
> **Trả lời:** Nếu chọn Pivot là phần tử đầu tiên (`array[left]`), trong trường hợp mảng ban đầu đã được sắp xếp tăng dần hoặc giảm dần, thuật toán sẽ bị rơi vào **trường hợp xấu nhất (Worst Case) $O(n^2)$**. Việc chọn Pivot ở vị trí giữa mảng giúp thuật toán hoạt động cực kỳ hiệu quả ngay cả khi mảng đã có thứ tự sẵn, đảm bảo thời gian chạy trung bình luôn xấp xỉ $O(n \log n)$.

### Q2: Sự khác nhau cơ bản giữa phân hoạch Hoare (code của bài) và phân hoạch Lomuto là gì?
> **Trả lời:**
> - **Lomuto Partition:** Luôn chọn phần tử cuối mảng làm Pivot, dùng 1 con trỏ chậm và 1 con trỏ nhanh chạy cùng chiều từ trái sang phải. Dễ cài đặt hơn nhưng thực hiện số lần swap nhiều hơn gấp 3 lần.
> - **Hoare Partition:** Dùng 2 con trỏ $i$ và $j$ chạy ngược chiều nhau từ 2 đầu mảng vào giữa. Thuật toán này tối ưu hơn Lomuto rất nhiều vì thực hiện ít phép swap hơn và hoạt động tốt hơn khi mảng có nhiều phần tử trùng giá trị.

### Q3: Quick Sort có phải là thuật toán sắp xếp tại chỗ (In-place) không?
> **Trả lời:** Có, Quick Sort là thuật toán sắp xếp tại chỗ vì việc phân hoạch và hoán vị phần tử diễn ra trực tiếp ngay trên mảng gốc, không cần cấp phát thêm các mảng tạm bổ trợ như Merge Sort. Bộ nhớ phụ trợ duy nhất chỉ là ngăn xếp đệ quy (Call-stack) với kích thước $O(\log n)$.

### Q4: Thuật toán Quick Sort của em có ổn định (Stable) không?
> **Trả lời:** Quick Sort **không phải là thuật toán ổn định (Unstable)**. Do các phép hoán vị diễn ra nhảy cóc giữa 2 con trỏ $i$ và $j$ ở hai phía khác nhau của Pivot, hai phần tử có giá trị bằng nhau có thể bị đảo lộn vị trí tương đối ban đầu sau các lần hoán đổi.
