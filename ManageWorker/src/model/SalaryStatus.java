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
     * Luồng xử lý 1: Trả về trạng thái UP.
     *
     * @return trạng thái tăng lương UP
     */
    public static SalaryStatus getUP() {
        return UP;
    }

    /**
     * Luồng xử lý 1: Trả về trạng thái DOWN.
     *
     * @return trạng thái giảm lương DOWN
     */
    public static SalaryStatus getDOWN() {
        return DOWN;
    }
}
