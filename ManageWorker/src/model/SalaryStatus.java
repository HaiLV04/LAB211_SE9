package model;

/**
 * Chức năng: Enum định nghĩa trạng thái điều chỉnh lương của công nhân (UP: Tăng lương, DOWN: Giảm lương).
 */
public enum SalaryStatus {
    UP,
    DOWN;

    /**
     * Lấy trạng thái tăng lương (UP).
     *
     * @return trạng thái UP
     */
    public static SalaryStatus getUP() {
        return UP;
    }

    /**
     * Lấy trạng thái giảm lương (DOWN).
     *
     * @return trạng thái DOWN
     */
    public static SalaryStatus getDOWN() {
        return DOWN;
    }
}
