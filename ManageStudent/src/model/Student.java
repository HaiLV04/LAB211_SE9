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
     * Chức năng (Làm gì): Khởi tạo đối tượng Student với đầy đủ thông tin.
     * Luồng xử lý (Làm như thế nào): Gán các giá trị tham số truyền vào cho các thuộc tính tương ứng của sinh viên.
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
     * Chức năng (Làm gì): Khởi tạo đối tượng Student không tham số.
     * Luồng xử lý (Làm như thế nào): Tạo một đối tượng Student mới với các thuộc tính mặc định.
     */
    public Student() {
    }

    /**
     * Chức năng (Làm gì): Lấy mã ID của sinh viên.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính id của sinh viên.
     *
     * @return mã ID của sinh viên
     */
    public String getId() {
        return id;
    }

    /**
     * Chức năng (Làm gì): Thiết lập mã ID của sinh viên.
     * Luồng xử lý (Làm như thế nào): Gán giá trị id mới cho thuộc tính id.
     *
     * @param id mã ID cần gán
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Chức năng (Làm gì): Lấy tên sinh viên.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính studentName của sinh viên.
     *
     * @return tên sinh viên
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Chức năng (Làm gì): Thiết lập tên sinh viên.
     * Luồng xử lý (Làm như thế nào): Gán giá trị studentName mới cho thuộc tính studentName.
     *
     * @param studentName tên sinh viên cần gán
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Chức năng (Làm gì): Lấy học kỳ của sinh viên.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính semester của sinh viên.
     *
     * @return học kỳ của sinh viên
     */
    public String getSemester() {
        return semester;
    }

    /**
     * Chức năng (Làm gì): Thiết lập học kỳ của sinh viên.
     * Luồng xử lý (Làm như thế nào): Gán giá trị semester mới cho thuộc tính semester.
     *
     * @param semester học kỳ cần gán
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Chức năng (Làm gì): Lấy tên môn học của sinh viên.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính courseName của sinh viên.
     *
     * @return enum môn học Course
     */
    public Course getCourseName() {
        return courseName;
    }

    /**
     * Chức năng (Làm gì): Thiết lập môn học của sinh viên.
     * Luồng xử lý (Làm như thế nào): Gán giá trị courseName mới cho thuộc tính courseName.
     *
     * @param courseName enum môn học Course cần gán
     */
    public void setCourseName(Course courseName) {
        this.courseName = courseName;
    }

    /**
     * Chức năng (Làm gì): So sánh hai đối tượng sinh viên theo tên để sắp xếp danh sách.
     * Luồng xử lý (Làm như thế nào):
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
     * Chức năng (Làm gì): Trả về chuỗi thông tin biểu diễn sinh viên.
     * Luồng xử lý (Làm như thế nào): Ghép các thuộc tính của sinh viên thành một chuỗi dễ đọc và trả về.
     *
     * @return chuỗi thông tin sinh viên
     */
    @Override
    public String toString() {
        return "Student{" + id + ", " + studentName + ", " + semester + ", " + courseName.getLanguage() + '}';
    }

}
