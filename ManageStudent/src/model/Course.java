package model;

/**
 * Chức năng: Enum lưu trữ các khóa học được hỗ trợ trong chương trình.
 * Luồng tương tác: Được sử dụng bởi lớp Student để gán khóa học; cung cấp các hằng số khóa học.
 */
public enum Course {
    JAVA("JAVA"),
    DOT_NET(".NET"),
    C_CPP("C/C++");
    private String language;

    /**
     * Chức năng: Khởi tạo khóa học với tên tương ứng.
     * Luồng xử lý:
     * 1. Gán giá trị tên khóa học vào thuộc tính language.
     *
     * @param language tên khóa học
     */
    Course(String language) {
        this.language = language;
    }

    /**
     * Chức năng: Trả về enum Course tương ứng với lựa chọn của người dùng.
     * Luồng xử lý:
     * 1. Nhận giá trị số nguyên type.
     * 2. Kiểm tra type và trả về enum Course tương ứng (1 -> JAVA, 2 -> .NET, 3 -> C/C++).
     * 3. Ném ra ngoại lệ AssertionError nếu type không hợp lệ.
     *
     * @param type lựa chọn khóa học
     * @return khóa học tương ứng
     */
    public static Course getCourse(int type) {
        switch (type) {
            case 1:
                return JAVA;
            case 2:
                return DOT_NET;
            case 3:
                return C_CPP;
            default:
                throw new AssertionError();

        }
    }

    /**
     * Chức năng: Lấy tên khóa học.
     * Luồng xử lý:
     * 1. Trả về thuộc tính language của enum.
     *
     * @return tên khóa học
     */
    public String getLanguage() {
        return language;
    }
}
