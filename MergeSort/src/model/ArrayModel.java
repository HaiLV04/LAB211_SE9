package model;

import java.util.Random;

/**
 * Chức năng: Xử lý dữ liệu mảng và thuật toán sắp xếp trộn (Merge Sort).
 */
public class ArrayModel {

    private int[] array;

    private int stepCounter;

    /**
     * Chức năng (Làm gì): Khởi tạo mặc định cho lớp ArrayModel.
     * Luồng xử lý (Làm như thế nào): Khởi tạo bộ đếm bước (stepCounter = 1) và để mảng chưa cấp phát.
     */
    public ArrayModel() {
        stepCounter = 1;
    }

    /**
     * Chức năng (Làm gì): Tạo một mảng số nguyên ngẫu nhiên dựa trên kích thước đầu vào.
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo đối tượng Random.
     * 2. Cấp phát mảng với kích thước chỉ định n.
     * 3. Sinh các giá trị ngẫu nhiên trong khoảng [-n, n] cho từng phần tử của mảng.
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
     * Chức năng (Làm gì): Bắt đầu sắp xếp mảng theo thứ tự tăng dần bằng Merge Sort.
     * Luồng xử lý (Làm như thế nào):
     * 1. Trả về ngay nếu mảng null hoặc có ít hơn 2 phần tử (n <= 1).
     * 2. Thiết lập lại bộ đếm bước về 1.
     * 3. Gọi đệ quy hàm mergeSort từ chỉ số 0 đến array.length - 1.
     */
    public void mergeSort() {
        if (array == null || array.length < 2) {
            return;
        }
        stepCounter = 1;
        mergeSort(0, array.length - 1);
    }

    /**
     * Chức năng (Làm gì): Hàm đệ quy chia đôi mảng để sắp xếp từng nửa mảng con.
     * Luồng xử lý (Làm như thế nào):
     * 1. Trường hợp cơ sở: nếu left >= right (mảng có 1 phần tử hoặc rỗng), dừng lại.
     * 2. Phân chia (Divide): Tính chỉ số ở giữa để chia đôi mảng và in thông tin bước chia.
     * 3. Gọi đệ quy sắp xếp nửa bên trái [left, mid].
     * 4. Gọi đệ quy sắp xếp nửa bên phải [mid + 1, right].
     * 5. Hợp nhất (Conquer / Merge): Trộn hai nửa mảng con đã sắp xếp lại với nhau.
     *
     * @param left chỉ số bắt đầu
     * @param right chỉ số kết thúc
     */
    private void mergeSort(int left, int right) {
        // Trường hợp cơ sở: Dừng đệ quy khi mảng con có tối đa 1 phần tử (đã có thứ tự)
        if (left >= right) {
            return;
        }

        // Phân chia: Chia đôi mảng tại chỉ số giữa
        int mid = left + (right - left) / 2;

        System.out.printf("Bước %d [CHIA MẢNG] Phân đoạn từ chỉ số %d đến %d | Chia đôi tại vị trí giữa = %d\n",
                stepCounter++, left, right, mid);
        System.out.println("   -> Phân vùng con bên Trái: chỉ số " + left + " đến " + mid);
        System.out.println("   -> Phân vùng con bên Phải: chỉ số " + (mid + 1) + " đến " + right);

        // Gọi đệ quy sắp xếp nửa bên trái
        mergeSort(left, mid);

        // Gọi đệ quy sắp xếp nửa bên phải
        mergeSort(mid + 1, right);

        // Hợp nhất: Trộn hai mảng con đã sắp xếp lại với nhau
        merge(left, mid, right);
    }

    /**
     * Chức năng (Làm gì): Trộn hai mảng con đã sắp xếp [left, mid] và [mid + 1, right] vào mảng gốc.
     * Luồng xử lý (Làm như thế nào):
     * 1. Tạo hai mảng tạm thời cho nửa bên trái và nửa bên phải.
     * 2. Sao chép dữ liệu tương ứng từ mảng gốc vào các mảng tạm.
     * 3. Hiển thị thông tin bước trộn và dữ liệu mảng con tạm thời.
     * 4. Dùng hai con trỏ i và j duyệt qua các mảng tạm, so sánh và đặt phần tử nhỏ hơn vào mảng gốc.
     * 5. Sao chép các phần tử còn lại của mảng tạm (nếu có) vào mảng gốc.
     * 6. Hiển thị trạng thái mảng gốc hiện tại sau khi trộn.
     *
     * @param left chỉ số bắt đầu của vùng phân đoạn trái
     * @param mid chỉ số kết thúc của vùng phân đoạn trái
     * @param right chỉ số kết thúc của vùng phân đoạn phải
     */
    private void merge(int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Tạo hai mảng tạm thời cho nửa trái và nửa phải
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Sao chép dữ liệu từ mảng gốc vào mảng tạm
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

        // Duyệt hai mảng tạm bằng hai con trỏ, so sánh và đưa phần tử nhỏ hơn vào mảng gốc
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

        // Sao chép các phần tử còn lại từ mảng tạm nếu có
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
     * Chức năng (Làm gì): Lấy mảng hiện tại.
     * Luồng xử lý (Làm như thế nào): Trả về tham chiếu đến mảng số nguyên của đối tượng.
     *
     * @return mảng số nguyên hiện tại
     */
    public int[] getArray() {
        return array;
    }
}
