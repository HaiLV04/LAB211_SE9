package model;

/**
 * Chức năng: Lớp Fibonacci dùng để tính các số Fibonacci bằng phương pháp đệ quy có nhớ.
 *
 * Luồng tương tác:
 * Các giá trị Fibonacci đã tính sẽ được lưu vào mảng để tránh tính toán lặp lại nhiều lần (Memoization).
 */
public class Fibonacci {

    /**
     * Mảng lưu các số Fibonacci đã được tính. fibonacci[i] lưu giá trị F(i).
     */
    private int[] fibonacci;

    /**
     * Chức năng: Khởi tạo mảng lưu các số Fibonacci.
     *
     * Luồng xử lý:
     * 1. Khởi tạo mảng số nguyên có kích thước bằng numberOfFibo để lưu các kết quả.
     * 
     * @param numberOfFibo số lượng phần tử Fibonacci cần lưu
     */
    public Fibonacci(int numberOfFibo) {
        fibonacci = new int[numberOfFibo];
    }

    /**
     * Chức năng: Tính số Fibonacci tại vị trí index bằng đệ quy.
     *
     * Luồng xử lý:
     * 1. Kiểm tra trường hợp cơ sở: Nếu index là 0 hoặc 1, trả về chính index đó.
     * 2. Kiểm tra bộ nhớ tạm (Memoization): Nếu fibonacci[index] khác 0 (đã được tính), trả về kết quả ngay lập tức.
     * 3. Tính toán đệ quy: Nếu chưa tính, tính F(n) = F(n-1) + F(n-2), lưu vào mảng và trả về kết quả.
     *
     * @param index vị trí cần tính trong dãy Fibonacci
     * @return giá trị Fibonacci tại vị trí index
     */
    public int getFibonacci(int index) {
        // Trường hợp cơ sở: F(0) = 0, F(1) = 1
        if (index == 0 || index == 1) {
            fibonacci[index] = index;
            return index;
        }
        // Kiểm tra xem F[n] đã được tính hay chưa? F[n]==0 thì là chưa được tính, != 0 là đã tính
        if (fibonacci[index] != 0) {
            return fibonacci[index];
        }
        // Nếu F[n] chưa được tính thì đi tính nó và lưu lại
        fibonacci[index] = getFibonacci(index - 1) + getFibonacci(index - 2);
        return fibonacci[index];
    }

    /**
     * Chức năng: Lấy toàn bộ dãy Fibonacci đã được tính toán.
     *
     * Luồng xử lý:
     * 1. Duyệt qua toàn bộ các vị trí từ 0 đến kích thước mảng và gọi getFibonacci(i).
     * 2. Trả về mảng chứa toàn bộ dãy số Fibonacci.
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
