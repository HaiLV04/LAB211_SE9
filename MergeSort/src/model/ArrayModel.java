package model;

import java.util.Random;

/**
 * Chức năng: Xử lý dữ liệu mảng và thuật toán Merge Sort.
 */
public class ArrayModel {

    private int[] array;

    private int stepCounter;

    /**
     * Chức năng: Khởi tạo mặc định cho lớp ArrayModel.
     */
    public ArrayModel() {
        stepCounter = 1;
    }

    /**
     * Chức năng: Tạo một mảng số nguyên ngẫu nhiên dựa trên kích thước đầu vào.
     * Luồng xử lý: 
     * 1. Khởi tạo đối tượng Random. 
     * 2. Khởi tạo mảng với kích thước chỉ định. 
     * 3. Tạo các giá trị ngẫu nhiên cho từng phần tử của mảng.
     *
     * @param n số lượng phần tử trong mảng
     */
    public void generateRandomArray(int n) {
        Random random = new Random();
        array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(2 * n + 1) - n;
        }
    }

    /**
     * Chức năng: Bắt đầu sắp xếp mảng theo thứ tự tăng dần bằng thuật toán Merge Sort.
     * Luồng xử lý:
     * 1. Kiểm tra nếu mảng null hoặc có ít hơn 2 phần tử ({@code n <= 1}) thì dừng lại, mảng giữ nguyên.
     * 2. Đặt lại biến đếm bước về 1.
     * 3. Gọi hàm đệ quy mergeSort với khoảng từ chỉ số 0 đến array.length - 1.
     */
    public void mergeSort() {
        if (array == null || array.length < 2) {
            return;
        }
        stepCounter = 1;
        mergeSort(0, array.length - 1);
    }

    /**
     * Chức năng: Hàm đệ quy chia đôi mảng để sắp xếp từng nửa.
     * Luồng xử lý:
     * 1. Kiểm tra điều kiện dừng: nếu {@code left >= right} (mảng chỉ còn 1 phần tử hoặc rỗng) thì dừng lại.
     * 2. Chia (Divide): Xác định vị trí giữa (middle) để chia mảng thành 2 nửa và hiển thị bước chia.
     * 3. Đệ quy sắp xếp nửa bên trái [left, mid].
     * 4. Đệ quy sắp xếp nửa bên phải [mid + 1, right].
     * 5. Trị (Conquer) - Trộn (Merge): Gộp hai mảng con đã sắp xếp lại với nhau.
     *
     * @param left chỉ số bắt đầu
     * @param right chỉ số kết thúc
     */
    private void mergeSort(int left, int right) {
        // Dừng đệ quy khi mảng con chỉ còn 1 phần tử hoặc rỗng (được coi là đã sắp xếp)
        if (left >= right) {
            return;
        }

        // Chia (Divide): Liên tục chia mảng thành 2 nửa (dựa vào vị trí giữa middle)
        int mid = left + (right - left) / 2;

        System.out.printf("Bước %d [CHIA MẢNG] Phân đoạn từ chỉ số %d đến %d | Chia đôi tại vị trí giữa = %d\n",
                stepCounter++, left, right, mid);
        System.out.println("   -> Phân vùng con bên Trái: chỉ số " + left + " đến " + mid);
        System.out.println("   -> Phân vùng con bên Phải: chỉ số " + (mid + 1) + " đến " + right);

        // Đệ quy sắp xếp nửa bên trái
        mergeSort(left, mid);

        // Đệ quy sắp xếp nửa bên phải
        mergeSort(mid + 1, right);

        // Trị (Conquer) - Trộn (Merge): Bắt đầu gộp các mảng con lại với nhau
        merge(left, mid, right);
    }

    /**
     * Chức năng: Trộn hai mảng con đã sắp xếp [left, mid] và [mid + 1, right] vào mảng gốc.
     * Luồng xử lý:
     * 1. Tạo 2 mảng tạm chứa dữ liệu của nửa bên trái và nửa bên phải.
     * 2. Sao chép dữ liệu tương ứng từ mảng gốc sang 2 mảng tạm.
     * 3. Hiển thị thông tin bước trộn mảng và các mảng con tạm.
     * 4. Dùng 2 con trỏ i và j duyệt qua 2 mảng tạm, so sánh phần tử: nếu phần tử mảng trái
     *    nhỏ hơn hoặc bằng mảng phải thì đưa vào mảng gốc, ngược lại đưa phần tử mảng phải vào.
     * 5. Khi một trong 2 mảng tạm duyệt hết, chép toàn bộ các phần tử còn lại của mảng kia vào mảng gốc.
     * 6. Hiển thị trạng thái mảng sau khi trộn đoạn này.
     *
     * @param left chỉ số bắt đầu phân đoạn trái
     * @param mid chỉ số kết thúc phân đoạn trái
     * @param right chỉ số kết thúc phân đoạn phải
     */
    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Tạo 2 mảng tạm chứa dữ liệu của nửa bên trái và nửa bên phải
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Sao chép dữ liệu từ mảng gốc sang 2 mảng tạm
        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        System.out.printf("Bước %d [TRỘN MẢNG] Đang gộp hai vùng con đã sắp xếp vào mảng gốc từ %d đến %d\n",
                stepCounter++, left, right);
        System.out.println("   * Mảng con tạm TRÁI L[] : " + java.util.Arrays.toString(leftArray));
        System.out.println("   * Mảng con tạm PHẢI R[] : " + java.util.Arrays.toString(rightArray));

        int i = 0;
        int j = 0;
        int k = left;

        // Dùng 2 con trỏ duyệt qua 2 mảng tạm. So sánh phần tử: nếu phần tử mảng trái nhỏ hơn
        // hoặc bằng mảng phải thì đưa vào mảng gốc, ngược lại đưa phần tử mảng phải vào
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

        // Khi một trong 2 mảng tạm đã duyệt hết, tiến hành chép toàn bộ các phần tử còn lại vào mảng gốc
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }

        System.out.println("   => Trạng thái mảng gốc hiện tại: " + java.util.Arrays.toString(array));
        System.out.println("----------------------------------------------------------------");
    }

    /**
     * Chức năng: Lấy mảng hiện tại.
     *
     * @return mảng số nguyên hiện tại
     */
    public int[] getArray() {
        return array;
    }
}
