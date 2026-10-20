package model;

/**
 * Chức năng: Enum định nghĩa các loại công việc (1: Code, 2: Test, 3: Design, 4: Review).
 */
public enum TaskType {
    CODE(1, "Code"),
    TEST(2, "Test"),
    DESIGN(3, "Design"),
    REVIEW(4, "Review");

    private int id;
    private String name;

    /**
     * Khởi tạo hằng số enum TaskType.
     *
     * @param id mã loại công việc
     * @param name tên loại công việc
     */
    private TaskType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Chức năng: Lấy loại công việc tương ứng theo mã ID (1 đến 4).
     * Luồng xử lý: Dùng switch-case kiểm tra mã và trả về enum tương ứng.
     * 
     * @param ID số nguyên từ 1 đến 4
     * @return đối tượng TaskType tương ứng
     * @throws AssertionError nếu ID không nằm trong khoảng 1-4
     */
    public static TaskType getTaskTypeByID(int ID) {
        switch (ID) {
            case 1:
                return CODE;
            case 2:
                return TEST;
            case 3:
                return DESIGN;
            case 4:
                return REVIEW;
            default:
                throw new AssertionError();
        }
    }

    /**
     * Lấy mã ID của loại task.
     *
     * @return mã ID
     */
    public int getId() {
        return id;
    }

    /**
     * Lấy tên của loại task.
     *
     * @return tên loại task
     */
    public String getName() {
        return name;
    }
}
