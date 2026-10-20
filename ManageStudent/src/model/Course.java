package model;

/**
 * Chức năng: Enum định nghĩa danh sách các môn học được hỗ trợ (Java, .Net, C/C++).
 */
public enum Course {
    JAVA("JAVA"),
    DOT_NET(".NET"),
    C_CPP("C/C++");

    private String language;

    /**
     * Khởi tạo môn học với tên tương ứng.
     *
     * @param language tên môn học
     */
    Course(String language) {
        this.language = language;
    }

    /**
     * Chức năng: Lấy môn học theo số thứ tự lựa chọn (1: JAVA, 2: .NET, 3: C/C++).
     * Luồng xử lý: Sử dụng switch-case kiểm tra số thứ tự và trả về enum tương ứng.
     *
     * @param type số thứ tự môn học (1, 2, 3)
     * @return đối tượng Course tương ứng
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
     * Lấy tên hiển thị của môn học.
     *
     * @return tên môn học
     */
    public String getLanguage() {
        return language;
    }
}
