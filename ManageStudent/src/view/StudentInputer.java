package view;

import model.Course;
import model.Student;

/**
 * Chức năng: Lớp StudentInputer dùng để quản lý việc nhập thông tin sinh viên từ bàn phím.
 * Luồng tương tác: Được gọi từ Controller để tạo ra một đối tượng Student với dữ liệu hợp lệ từ phía người dùng.
 *
 * @author win
 */
public class StudentInputer {

    /**
     * Đối tượng sinh viên được sử dụng để lưu thông tin nhập vào.
     */
    private Student student;

    /**
     * Chức năng: Khởi tạo đối tượng StudentInputer.
     * Luồng xử lý:
     * 1. Khởi tạo một đối tượng Student rỗng mới.
     */
    public StudentInputer() {
        student = new Student();
    }

    /**
     * Chức năng: Nhập mã sinh viên.
     * Luồng xử lý:
     * 1. Yêu cầu nhập mã, kiểm tra phải bắt đầu bằng S hoặc s, theo sau là các chữ số.
     * 2. Gán giá trị hợp lệ vừa nhập vào ID của sinh viên.
     */
    public void inputID() {
        student.setId(Validator.getString("Enter id: ", "Invalid!", "[Ss]\\d+"));
    }

    /**
     * Chức năng: Nhập tên sinh viên.
     * Luồng xử lý:
     * 1. Yêu cầu nhập tên, kiểm tra chỉ được chứa chữ cái và khoảng trắng.
     * 2. Gán giá trị hợp lệ vừa nhập vào StudentName của sinh viên.
     */
    public void inputStudentName() {
        student.setStudentName(Validator.getString("Enter name student: ", "Invalid!", "[A-Za-z\\s]+"));
    }

    /**
     * Chức năng: Nhập học kỳ của sinh viên.
     * Luồng xử lý:
     * 1. Yêu cầu nhập học kỳ (chứa chữ cái và số).
     * 2. Gán giá trị hợp lệ vừa nhập vào Semester của sinh viên.
     */
    public void inputSemester() {
        student.setSemester(Validator.getString("Enter Semester: ", "Invalid!", "[A-Za-z\\d]+"));
    }
    
    /**
     * Chức năng: Nhập môn học bằng cách chọn từ danh sách các môn được hỗ trợ.
     * Luồng xử lý:
     * 1. In ra 3 lựa chọn (1-Java, 2-.Net, 3-C/C++).
     * 2. Nhận lựa chọn là số nguyên từ 1 đến 3.
     * 3. Lấy ra đối tượng Course tương ứng và gán vào CourseName của sinh viên.
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
     * Chức năng: Trả về thông tin sinh viên đã được nhập.
     * Luồng xử lý:
     * 1. Trả về đối tượng student hiện tại.
     *
     * @return đối tượng sinh viên
     */
    public Student getStudent() {
        return student;
    }

}
