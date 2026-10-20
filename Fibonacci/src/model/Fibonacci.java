package model;

/**
 * Chức năng: Tính toán các số trong dãy Fibonacci bằng phương pháp đệ quy có nhớ (Memoization).
 */
public class Fibonacci {

    private int[] fibonacci;

    /**
     * Khởi tạo mảng lưu trữ các số Fibonacci tạm thời.
     * 
     * @param numberOfFibo số lượng phần tử Fibonacci cần tính
     */
    public Fibonacci(int numberOfFibo) {
        fibonacci = new int[numberOfFibo];
    }

    /**
     * Chức năng: Tính số Fibonacci tại vị trí index bằng đệ quy có nhớ.
     * Luồng xử lý: Nếu là vị trí 0 hoặc 1 trả về chính nó; nếu đã có trong mảng thì trả về ngay; nếu chưa thì tính F(n) = F(n-1) + F(n-2) và lưu lại.
     *
     * @param index vị trí cần tính trong dãy Fibonacci
     * @return giá trị Fibonacci tại vị trí index
     */
    public int getFibonacci(int index) {
        if (index == 0 || index == 1) {
            fibonacci[index] = index;
            return index;
        }
        if (fibonacci[index] != 0) {
            return fibonacci[index];
        }
        fibonacci[index] = getFibonacci(index - 1) + getFibonacci(index - 2);
        return fibonacci[index];
    }

    /**
     * Chức năng: Lấy toàn bộ dãy số Fibonacci đã tính toán.
     * Luồng xử lý: Duyệt qua toàn bộ mảng và gọi getFibonacci(i) cho từng phần tử.
     *
     * @return mảng số nguyên chứa toàn bộ dãy số Fibonacci
     */
    public int[] getSequence() {
        for (int i = 0; i < fibonacci.length; i++) {
            getFibonacci(i);
        }
        return fibonacci;
    }
}
