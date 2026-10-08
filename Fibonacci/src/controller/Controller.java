package controller;

import model.Fibonacci;
import view.View;

/**
 * Chức năng: Điều khiển luồng chương trình tính dãy số Fibonacci.
 */
public class Controller {

    private final Fibonacci model;
    private final View view;
    private final int sequenceLength;

    /**
     * Chức năng: Khởi tạo mặc định cho Controller với độ dài mặc định là 45.
     * Luồng xử lý:
     * 1. Gán sequenceLength = 45.
     * 2. Khởi tạo đối tượng Fibonacci với kích thước 45.
     * 3. Khởi tạo đối tượng View.
     */
    public Controller() {
        this.sequenceLength = 45;
        this.model = new Fibonacci(sequenceLength);
        this.view = new View();
    }

    /**
     * Chức năng: Khởi tạo Controller với các tham số tùy chọn (hỗ trợ Dependency Injection).
     * Luồng xử lý:
     * 1. Gán model, view và sequenceLength từ tham số truyền vào.
     *
     * @param model đối tượng model Fibonacci
     * @param view đối tượng view View
     * @param sequenceLength số lượng phần tử cần tính
     */
    public Controller(Fibonacci model, View view, int sequenceLength) {
        this.model = model;
        this.view = view;
        this.sequenceLength = sequenceLength;
    }

    /**
     * Chức năng: Chạy luồng chương trình chính.
     * Luồng tương tác:
     * 1. Gọi View hiển thị tiêu đề dãy số Fibonacci.
     * 2. Lấy toàn bộ dãy số từ Model (Model thực hiện tính toán đệ quy có nhớ).
     * 3. Chuyển dữ liệu dãy số sang View để hiển thị theo định dạng chuẩn đề bài.
     * 4. Gọi View hiển thị chi tiết các ca kiểm thử phục vụ review với giảng viên.
     */
    public void run() {
        view.displayTitle();

        int[] sequence = model.getSequence();

        // 1. Hiển thị dãy 45 số theo đúng yêu cầu đề bài
        view.displayFibonacciSequence(sequence);

        // 2. Hiển thị thông tin kiểm chứng chi tiết qua View
        view.displayPositions(sequence);
        view.displayArrayFormat(sequence);
        view.displayTestCases(sequence[0], sequence[1], sequence[sequenceLength - 1], sequenceLength - 1);
    }
}
