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
     * Khởi tạo Controller với độ dài mặc định là 45 phần tử.
     */
    public Controller() {
        this.sequenceLength = 45;
        this.model = new Fibonacci(sequenceLength);
        this.view = new View();
    }

    /**
     * Khởi tạo Controller với các tham số tùy chọn.
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
     * Chức năng: Chạy luồng chương trình chính của bài toán Fibonacci.
     * Luồng xử lý: Hiển thị tiêu đề, lấy dãy số Fibonacci tính bằng đệ quy có nhớ từ model và hiển thị ra màn hình qua view.
     */
    public void run() {
        view.displayTitle();

        int[] sequence = model.getSequence();

        view.displayFibonacciSequence(sequence);
        view.displayPositions(sequence);
        view.displayArrayFormat(sequence);
        view.displayTestCases(sequence[0], sequence[1], sequence[sequenceLength - 1], sequenceLength - 1);
    }
}
