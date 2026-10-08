package view;

/**
 * Chức năng: Xử lý hiển thị kết quả đầu ra cho chương trình Fibonacci.
 */
public class View {

    /**
     * Chức năng: Khởi tạo mặc định cho lớp View.
     */
    public View() {
    }

    /**
     * Chức năng: Hiển thị tiêu đề thông báo số lượng số Fibonacci cần tìm.
     * Luồng xử lý:
     * 1. In tiêu đề "The 45 sequence fibonacci:" ra console.
     */
    public void displayTitle() {
        System.out.println("The 45 sequence fibonacci:");
    }

    /**
     * Chức năng: Hiển thị toàn bộ dãy số Fibonacci theo đúng đặc tả đề bài.
     * Luồng xử lý:
     * 1. Duyệt qua từng phần tử trong dãy số.
     * 2. In giá trị số, ngăn cách bằng dấu phẩy và khoảng trắng.
     * 3. Kết thúc bằng dấu chấm và xuống dòng.
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
     * Chức năng: Hiển thị một số Fibonacci đơn lẻ với định dạng dấu phân cách.
     * Luồng xử lý:
     * 1. In giá trị số Fibonacci.
     * 2. Nếu là số cuối cùng, in dấu chấm "." và xuống dòng.
     * 3. Nếu chưa phải số cuối cùng, in dấu phẩy ", ".
     *
     * @param value giá trị Fibonacci cần in
     * @param isLast true nếu đây là phần tử cuối cùng trong dãy
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
     * Luồng xử lý:
     * 1. In tiêu đề "Position of each element:".
     * 2. Lặp qua từng phần tử và in theo định dạng "Index i: value".
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
     * Chức năng: Hiển thị danh sách các giá trị Fibonacci theo định dạng mảng F(i).
     * Luồng xử lý:
     * 1. In dấu mở ngoặc vuông "[".
     * 2. Duyệt qua từng phần tử, in dạng "F(i)=giá_trị".
     * 3. Thêm dấu phẩy giữa các phần tử và đóng ngoặc vuông "]".
     *
     * @param values mảng chứa các giá trị Fibonacci đã tính
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
     * Luồng xử lý:
     * 1. In giá trị F(0).
     * 2. In giá trị F(1).
     * 3. In giá trị F tại vị trí cuối cùng.
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
     * Chức năng: Hiển thị một thông điệp chuỗi ra màn hình console.
     * Luồng xử lý:
     * 1. In chuỗi thông điệp kèm ký tự xuống dòng.
     *
     * @param message thông điệp cần hiển thị
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
