package view;

import model.Course;
import model.Student;

/**
 * Chức năng: Lớp StudentInputer dùng để quản lý việc nhập thông tin sinh viên từ bàn phím.
 * Luồng tương tác: Được gọi từ Controller để tạo ra một đối tượng Student với dữ liệu hợp lệ từ phía người dùng.
 */
public class StudentInputer {

    /**
     * Đối tượng sinh viên được sử dụng để lưu thông tin nhập vào.
     */
    private Student student;

    /**
     * Chức năng (Làm gì): Khởi tạo đối tượng StudentInputer.
     * Luồng xử lý (Làm như thế nào): Tạo mới một đối tượng Student rỗng.
     */
    public StudentInputer() {
        student = new Student();
    }

    /**
     * Chức năng (Làm gì): Nhập mã sinh viên.
     * Luồng xử lý (Làm như thế nào):
     * 1. Yêu cầu người dùng nhập mã, kiểm tra định dạng bắt đầu bằng S hoặc s, theo sau là các chữ số.
     * 2. Gán giá trị hợp lệ vừa nhập vào thuộc tính id của sinh viên.
     */
    public void inputID() {
        student.setId(Validator.getString("Enter id: ", "Invalid!", "[Ss]\\d+"));
    }

    /**
     * Chức năng (Làm gì): Nhập tên sinh viên.
     * Luồng xử lý (Làm như thế nào):
     * 1. Yêu cầu người dùng nhập tên, kiểm tra chỉ được chứa chữ cái và khoảng trắng.
     * 2. Gán giá trị hợp lệ vừa nhập vào thuộc tính studentName của sinh viên.
     */
    public void inputStudentName() {
        student.setStudentName(Validator.getString("Enter name student: ", "Invalid!", "[A-Za-z\\s]+"));
    }

    /**
     * Chức năng (Làm gì): Nhập học kỳ của sinh viên.
     * Luồng xử lý (Làm như thế nào):
     * 1. Yêu cầu người dùng nhập học kỳ (chứa chữ cái và số).
     * 2. Gán giá trị hợp lệ vừa nhập vào thuộc tính semester của sinh viên.
     */
    public void inputSemester() {
        student.setSemester(Validator.getString("Enter Semester: ", "Invalid!", "[A-Za-z\\d]+"));
    }
    
    /**
     * Chức năng (Làm gì): Nhập môn học bằng cách chọn từ danh sách các môn được hỗ trợ.
     * Luồng xử lý (Làm như thế nào):
     * 1. In ra 3 lựa chọn môn học (1-Java, 2-.Net, 3-C/C++).
     * 2. Nhận lựa chọn là số nguyên từ 1 đến 3 qua Validator.
     * 3. Lấy ra đối tượng Course tương ứng và gán vào thuộc tính courseName của sinh viên.
     */
    public void inputCourseName() {
        int choice = Validator.getInt("Only three courses:\n"
                + "1-Java\n"
                + "2-.Net\n"
                + "3-C/C++\n"
                + "Enter your choice:",
                "Please enter number 1->3", "Invalid", 1, 3);
        student.setCourseName(Course.getCourse(choice));
    }

    /**
     * Chức năng (Làm gì): Trả về thông tin sinh viên đã được nhập.
     * Luồng xử lý (Làm như thế nào): Trả về tham chiếu đến đối tượng student hiện tại.
     *
     * @return đối tượng sinh viên
     */
    public Student getStudent() {
        return student;
    }

}
