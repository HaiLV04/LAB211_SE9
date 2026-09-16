package model;

/**
 * Chức năng: Enum lưu trữ các môn học được hỗ trợ trong chương trình.
 * Luồng tương tác: Sử dụng bởi lớp Student để gán môn học, lớp Course cung cấp các hằng số môn học tĩnh.
 */
public enum Course {
    JAVA("JAVA"),
    DOT_NET(".NET"),
    C_CPP("C/C++");
    private String language;

    /**
     * Chức năng: Khởi tạo môn học với tên tương ứng.
     * Luồng xử lý:
     * 1. Gán giá trị tên môn học cho thuộc tính language.
     *
     * @param language tên môn học
     */
    Course(String language) {
        this.language = language;
    }

    /**
     * Chức năng: Trả về đối tượng Course tương ứng với lựa chọn của người dùng.
     * Luồng xử lý:
     * 1. Nhận giá trị type nguyên đầu vào.
     * 2. Kiểm tra type và trả về enum Course tương ứng (1 -> JAVA, 2 -> .NET, 3 -> C/C++).
     * 3. Ném ngoại lệ AssertionError nếu type không hợp lệ.
     *
     * @param type lựa chọn môn học
     * @return môn học tương ứng
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
     * Chức năng: Trả về tên môn học.
     * Luồng xử lý:
     * 1. Trả về thuộc tính language của enum.
     *
     * @return tên môn học
     */
    public String getLanguage() {
        return language;
    }
}
