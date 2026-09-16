package model;

/**
 * Chức năng: Enum TaskType định nghĩa các loại công việc cố định trong hệ thống (Code, Test, Design, Review).
 * Luồng tương tác: Hệ thống sử dụng Enum này để giới hạn tập giá trị hợp lệ, kiểm soát dữ liệu chặt chẽ hơn và tránh việc người dùng gán sai loại công việc.
 */
public enum TaskType {
    CODE(1, "Code"),
    TEST(2, "Test"),
    DESIGN(3, "Design"),
    REVIEW(4, "Review");
    
    private int id;
    private String name;

    private TaskType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Chức năng: Lấy ra đối tượng TaskType dựa vào ID đầu vào.
     * Luồng xử lý:
     * 1. Kiểm tra ID truyền vào bằng cấu trúc switch-case.
     * 2. Trả về Enum tương ứng (1: CODE, 2: TEST, 3: DESIGN, 4: REVIEW).
     * 3. Ném lỗi AssertionError nếu ID không nằm trong khoảng 1-4.
     * 
     * @param ID số nguyên (từ 1 đến 4) đại diện cho loại công việc
     * @return Đối tượng TaskType tương ứng (CODE, TEST, DESIGN, hoặc REVIEW)
     * @throws AssertionError nếu truyền vào một ID không hợp lệ (không thuộc 1-4)
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
