package model;

/**
 * Chức năng: Lưu trữ thông tin sinh viên (ID, tên, học kỳ, môn học).
 */
public class Student implements Comparable<Student> {

    private String id;
    private String studentName;
    private String semester;
    private Course courseName;

    /**
     * Khởi tạo đối tượng Student với đầy đủ thông tin.
     *
     * @param id mã số sinh viên
     * @param studentName họ và tên sinh viên
     * @param semester học kỳ
     * @param courseName môn học
     */
    public Student(String id, String studentName, String semester, Course courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    /**
     * Khởi tạo đối tượng Student mặc định.
     */
    public Student() {
    }

    /**
     * Lấy mã sinh viên.
     *
     * @return mã ID sinh viên
     */
    public String getId() {
        return id;
    }

    /**
     * Thiết lập mã sinh viên.
     *
     * @param id mã ID cần gán
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Lấy họ tên sinh viên.
     *
     * @return tên sinh viên
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Thiết lập họ tên sinh viên.
     *
     * @param studentName tên sinh viên
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Lấy học kỳ.
     *
     * @return học kỳ
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Thiết lập học kỳ.
     *
     * @param semester học kỳ
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Lấy môn học.
     *
     * @return môn học
     */
    public Course getCourseName() {
        return courseName;
    }

    /**
     * Thiết lập môn học.
     *
     * @param courseName môn học
     */
    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    /**
     * Chức năng: So sánh hai sinh viên theo tên để sắp xếp tăng dần.
     * Luồng xử lý: Sử dụng compareTo của String để so sánh thuộc tính studentName.
     *
     * @param t sinh viên cần so sánh
     * @return giá trị âm, 0 hoặc dương theo thứ tự từ điển
     */
    @Override
    public int compareTo(Student t) {
        return this.studentName.compareTo(t.studentName);
    }

    /**
     * Chức năng: Trả về chuỗi thông tin biểu diễn sinh viên.
     *
     * @return chuỗi thông tin sinh viên
     */
    @Override
    public String toString() {
        return "Student{" + id + ", " + studentName + ", " + semester + ", " + courseName.getLanguage() + '}';
    }
}
