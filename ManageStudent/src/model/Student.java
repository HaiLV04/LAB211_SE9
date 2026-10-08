package model;

/**
 * Chức năng: Lớp Student lưu trữ thông tin của một sinh viên (ID, tên, học kỳ, khóa học).
 * Luồng tương tác: Các đối tượng của lớp này được tạo trong StudentInputer và được quản lý bởi ManageStudent để lưu trữ, hiển thị, cập nhật và thống kê báo cáo.
 */
public class Student implements Comparable<Student> {

    private String id;
    private String studentName;
    private String semester;
    private Course courseName;

    /**
     * Chức năng: Khởi tạo đối tượng Student với đầy đủ thông tin.
     * Luồng xử lý:
     * 1. Gán các giá trị tham số truyền vào cho các thuộc tính tương ứng của sinh viên.
     *
     * @param id mã số sinh viên
     * @param studentName họ và tên sinh viên
     * @param semester học kỳ
     * @param courseName khóa học
     */
    public Student(String id, String studentName, String semester, Course courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    /**
     * Chức năng: Khởi tạo đối tượng Student không tham số.
     * Luồng xử lý:
     * 1. Khởi tạo mặc định cho các thuộc tính của đối tượng.
     */
    public Student() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Course getCourseName() {
        return courseName;
    }

    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    /**
     * Chức năng: So sánh hai đối tượng sinh viên theo tên để sắp xếp danh sách.
     * Luồng xử lý:
     * 1. Sử dụng phương thức compareTo của String trên thuộc tính studentName.
     * 2. Trả về kết quả so sánh để phục vụ việc sắp xếp.
     *
     * @param t sinh viên cần so sánh
     * @return giá trị so sánh theo tên
     */
    @Override
    public int compareTo(Student t) {
        return this.studentName.compareTo(t.studentName);
    }

    /**
     * Chức năng: Trả về chuỗi thông tin biểu diễn sinh viên.
     * Luồng xử lý:
     * 1. Ghép các thuộc tính của sinh viên thành một chuỗi dễ đọc và trả về.
     *
     * @return chuỗi thông tin sinh viên
     */
    @Override
    public String toString() {
        return "Student{" + id + ", " + studentName + ", " + semester + ", " + courseName.getLanguage() + '}';
    }

}
