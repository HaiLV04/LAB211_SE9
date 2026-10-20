package view;

/**
 * Chức năng: Xử lý hiển thị kết quả đầu ra cho chương trình Fibonacci.
 */
public class View {

    /**
     * Khởi tạo đối tượng View.
     */
    public View() {
    }

    /**
     * Chức năng: Hiển thị tiêu đề thông báo số lượng số Fibonacci cần tìm.
     * Luồng xử lý: In chuỗi tiêu đề ra console.
     */
    public void displayTitle() {
        System.out.println("The 45 sequence fibonacci:");
    }

    /**
     * Chức năng: Hiển thị toàn bộ dãy số Fibonacci theo định dạng ngăn cách bởi dấu phẩy và kết thúc bằng dấu chấm.
     * Luồng xử lý: Duyệt mảng, in từng phần tử kèm dấu phẩy hoặc dấu chấm ở cuối.
     *
     * @param sequence mảng chứa các số Fibonacci
     */
    public void displayFibonacciSequence(int[] sequence) {
        for (int i = 0; i < sequence.length; i++) {
            System.out.print(sequence[i]);
            if (i == sequence.length - 1) {
                System.out.println(".");
            } else {
                System.out.print(", ");
            }
        }
    }

    /**
     * Chức năng: Hiển thị một số Fibonacci kèm ký tự phân cách.
     * Luồng xử lý: In giá trị, nếu là số cuối in dấu chấm, ngược lại in dấu phẩy.
     *
     * @param value giá trị Fibonacci cần in
     * @param isLast true nếu đây là phần tử cuối cùng
     */
    public void displayFibonacci(int value, boolean isLast) {
        System.out.print(value);
        if (isLast) {
            System.out.println(".");
        } else {
            System.out.print(", ");
        }
    }

    /**
     * Chức năng: Hiển thị danh sách vị trí chi tiết của từng phần tử trong dãy.
     * Luồng xử lý: Lặp qua từng phần tử và in theo định dạng "Index i: value".
     *
     * @param sequence mảng chứa dãy Fibonacci
     */
    public void displayPositions(int[] sequence) {
        System.out.println("\nPosition of each element:");
        for (int i = 0; i < sequence.length; i++) {
            System.out.println("Index " + i + ": " + sequence[i]);
        }
    }

    /**
     * Chức năng: Hiển thị danh sách giá trị Fibonacci theo định dạng F(i).
     * Luồng xử lý: Ghép và in chuỗi theo định dạng [F(0)=..., F(1)=...].
     *
     * @param values mảng chứa các giá trị Fibonacci
     */
    public void displayArrayFormat(int[] values) {
        System.out.print("[");
        for (int i = 0; i < values.length; i++) {
            System.out.print("F(" + i + ")=" + values[i]);
            if (i < values.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Chức năng: Hiển thị kết quả kiểm chứng các mốc giá trị cơ sở.
     * Luồng xử lý: In giá trị F(0), F(1) và F ở vị trí cuối cùng ra màn hình.
     *
     * @param f0 giá trị F(0)
     * @param f1 giá trị F(1)
     * @param fLast giá trị F cuối cùng
     * @param lastIndex chỉ số cuối cùng
     */
    public void displayTestCases(int f0, int f1, int fLast, int lastIndex) {
        System.out.println("F(0) = " + f0);
        System.out.println("F(1) = " + f1);
        System.out.println("F(" + lastIndex + ") = " + fLast);
    }

    /**
     * Chức năng: Hiển thị một thông điệp ra console.
     * Luồng xử lý: In chuỗi thông điệp bằng System.out.println.
     *
     * @param message thông điệp cần hiển thị
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
