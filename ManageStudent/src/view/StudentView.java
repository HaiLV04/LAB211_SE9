package view;

import model.Course;
import model.ManageStudent;
import model.Student;

/**
 * Chức năng: Quản lý giao diện hiển thị và nhập liệu cho chương trình quản lý sinh viên.
 */
public class StudentView {

    /**
     * Khởi tạo đối tượng StudentView.
     */
    public StudentView() {
    }

    /**
     * Chức năng: Hiển thị menu chính và nhận lựa chọn của người dùng.
     * Luồng xử lý: In menu các chức năng và sử dụng Validator để lấy số nguyên từ 1 đến 5.
     *
     * @return lựa chọn từ 1 đến 5
     */
    public int getMenuChoice() {
        return Validator.getInt("WELCOME TO STUDENT MANAGEMENT\n"
                + "1.\tCreate\n"
                + "2.\tFind and Sort\n"
                + "3.\tUpdate/Delete\n"
                + "4.\tReport\n"
                + "5.\tExit\n"
                + "Enter your choice: ", "Just be 1->5", "Invalid!", 1, 5);
    }

    /**
     * Chức năng: Nhập mã sinh viên từ bàn phím.
     * Luồng xử lý: Yêu cầu người dùng nhập và kiểm tra định dạng ID (bắt đầu bằng S/s theo sau là số).
     *
     * @return mã sinh viên hợp lệ
     */
    public String inputId() {
        return Validator.getString("Enter id: ", "Invalid!", "[Ss]\\d+");
    }

    /**
     * Chức năng: Nhập tên sinh viên từ bàn phím.
     * Luồng xử lý: Yêu cầu người dùng nhập và kiểm tra định dạng tên (chỉ gồm chữ cái và khoảng trắng).
     *
     * @return tên sinh viên hợp lệ
     */
    public String inputStudentName() {
        return Validator.getString("Enter name student: ", "Invalid!", "[A-Za-z\\s]+");
    }

    /**
     * Chức năng: Nhập học kỳ của sinh viên.
     * Luồng xử lý: Yêu cầu người dùng nhập và kiểm tra chuỗi học kỳ (chữ và số).
     *
     * @return học kỳ hợp lệ
     */
    public String inputSemester() {
        return Validator.getString("Enter Semester: ", "Invalid!", "[A-Za-z\\d]+");
    }

    /**
     * Chức năng: Nhập môn học bằng cách chọn từ danh sách.
     * Luồng xử lý: Hiển thị 3 môn học (1-Java, 2-.Net, 3-C/C++), nhận số từ 1 đến 3 và trả về enum Course.
     *
     * @return đối tượng Course tương ứng
     */
    public Course inputCourseName() {
        int choice = Validator.getInt("Only three courses:\n"
                + "1-Java\n"
                + "2-.Net\n"
                + "3-C/C++\n"
                + "Enter your choice: ",
                "Please enter number 1->3", "Invalid", 1, 3);
        return Course.getCourse(choice);
    }

    /**
     * Chức năng: Nhập từ khóa tên sinh viên cần tìm kiếm.
     * Luồng xử lý: Sử dụng Validator để lấy chuỗi chữ cái và khoảng trắng.
     *
     * @return từ khóa tên cần tìm
     */
    public String inputSearchName() {
        return Validator.getString("Enter name student: ", "Invalid!", "[A-Za-z\\s]+");
    }

    /**
     * Chức năng: Nhập mã ID sinh viên cần tìm kiếm.
     * Luồng xử lý: Gọi hàm inputId() để đảm bảo đúng định dạng ID.
     *
     * @return mã ID sinh viên
     */
    public String inputSearchId() {
        return inputId();
    }

    /**
     * Chức năng: Nhận lựa chọn bản ghi từ danh sách kết quả.
     * Luồng xử lý: Sử dụng Validator để lấy số nguyên trong khoảng từ 1 đến max.
     *
     * @param max số lượng bản ghi tối đa
     * @return số thứ tự bản ghi được chọn
     */
    public int getRecordChoice(int max) {
        return Validator.getInt("Enter record your choice: ", "Just be 1-> " + max,
                "Invalid!", 1, max);
    }

    /**
     * Chức năng: Nhận lựa chọn Cập nhật (U) hoặc Xóa (D).
     * Luồng xử lý: Sử dụng Validator để lấy ký tự 'U' hoặc 'D'.
     *
     * @return chuỗi "U" hoặc "D"
     */
    public String getUpdateOrDeleteChoice() {
        return Validator.getString("Do you want Update(U) or Delete(D): ",
                "Just U or D", "[UDud]");
    }

    /**
     * Chức năng: Xác nhận người dùng có muốn tiếp tục hay không (Y/N).
     * Luồng xử lý: Nhận ký tự Y/N qua Validator và trả về true nếu là 'Y'/'y', ngược lại false.
     *
     * @return true nếu tiếp tục, false nếu dừng
     */
    public boolean getContinueChoice() {
        String choice = Validator.getString("Do you continue ( Y or N)? ", "Just Y or N", "[YNyn]");
        return choice.equalsIgnoreCase("Y");
    }

    /**
     * Chức năng: Hiển thị bảng danh sách sinh viên.
     * Luồng xử lý: Lấy chuỗi định dạng từ manageStudent.toString() và in ra màn hình.
     *
     * @param manageStudent đối tượng quản lý chứa danh sách sinh viên
     */
    public void displayStudentList(ManageStudent manageStudent) {
        if (manageStudent != null && manageStudent.toString() != null) {
            System.out.println(manageStudent.toString());
        }
    }

    /**
     * Chức năng: Hiển thị thông tin một sinh viên.
     * Luồng xử lý: In trực tiếp đối tượng student ra màn hình.
     *
     * @param student sinh viên cần hiển thị
     */
    public void displayStudent(Student student) {
        if (student != null) {
            System.out.println(student);
        }
    }

    /**
     * Chức năng: Hiển thị tên sinh viên khi tự động điền theo ID.
     * Luồng xử lý: In ra màn hình tên sinh viên kèm tiền tố "Name: ".
     *
     * @param name tên sinh viên
     */
    public void displayName(String name) {
        System.out.println("Name: " + name);
    }

    /**
     * Chức năng: Hiển thị bảng báo cáo thống kê môn học.
     * Luồng xử lý: In chuỗi kết quả báo cáo ra màn hình.
     *
     * @param report chuỗi báo cáo thống kê
     */
    public void displayReport(String report) {
        System.out.println(report);
    }

    /**
     * Chức năng: Hiển thị thông báo hoặc thông điệp ra màn hình.
     * Luồng xử lý: In chuỗi thông điệp ra console bằng System.out.println.
     *
     * @param message thông điệp cần hiển thị
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
