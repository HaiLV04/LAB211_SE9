package model;

/**
 * Chức năng: Enum biểu diễn trạng thái thay đổi lương của công nhân.
 * Luồng tương tác: Sử dụng trong lớp SalaryHistory để ghi nhận lịch sử tăng hoặc giảm lương.
 */
public enum SalaryStatus {
    /**
     * Trạng thái tăng lương (UP), trạng thái giảm lương (DOWN).
     */
    UP, DOWN;

    /**
     * Chức năng (Làm gì): Lấy trạng thái tăng lương (UP).
     * Luồng xử lý (Làm như thế nào): Trả về giá trị enum UP.
     *
     * @return trạng thái tăng lương UP
     */
    public static SalaryStatus getUP() {
        return UP;
    }

    /**
     * Chức năng (Làm gì): Lấy trạng thái giảm lương (DOWN).
     * Luồng xử lý (Làm như thế nào): Trả về giá trị enum DOWN.
     *
     * @return trạng thái giảm lương DOWN
     */
    public static SalaryStatus getDOWN() {
        return DOWN;
    }
}
