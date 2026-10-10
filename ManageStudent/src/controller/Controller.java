package controller;

import model.ManageStudent;
import view.StudentInputer;
import model.Course;
import model.Student;
import java.util.ArrayList;
import view.Validator;

/**
 * Chức năng: Lớp Controller điều khiển luồng thực thi của chương trình quản lý sinh viên bao gồm: tạo mới, tìm kiếm, cập nhật, xóa và thống kê.
 * Luồng tương tác: Được Main gọi để phản hồi lựa chọn menu. Giao tiếp với ManageStudent để thao tác dữ liệu, và StudentInputer để nhập thông tin từ người dùng.
 */
public class Controller {

    /**
     * Đối tượng quản lý danh sách sinh viên.
     */
    private ManageStudent studentManager;
    /**
     * Đối tượng hỗ trợ nhập dữ liệu sinh viên.
     */
    private StudentInputer inputer;

    /**
     * Chức năng (Làm gì): Khởi tạo Controller.
     * Luồng xử lý (Làm như thế nào): Khởi tạo đối tượng ManageStudent để quản lý danh sách sinh viên.
     */
    public Controller() {
        studentManager = new ManageStudent();
    }

    /**
     * Chức năng (Làm gì): Tạo mới sinh viên.
     * Luồng xử lý (Làm như thế nào):
     * 1. Lặp việc khởi tạo StudentInputer để bắt đầu nhập.
     * 2. Nhập ID sinh viên.
     * 3. Kiểm tra xem ID đã tồn tại hay chưa. Nếu chưa tồn tại, yêu cầu nhập tên mới. Nếu đã tồn tại, tự động điền tên cũ.
     * 4. Nhập học kỳ và môn học.
     * 5. Gọi studentManager để thêm sinh viên. Nếu không thành công thì ném Exception.
     * 6. Nếu số lượng sinh viên > 5, hỏi người dùng xem có muốn tiếp tục thêm không, nếu nhập N thì dừng lặp.
     *
     * @throws Exception nếu thêm sinh viên thất bại (trùng lặp bản ghi)
     */
    public void createStudent() throws Exception {
        while (true) {
            // Tạo đối tượng nhập dữ liệu mới
            inputer = new StudentInputer();
            // Nhập ID sinh viên
            inputer.inputID();
            Student student = inputer.getStudent();
            // Nếu ID chưa tồn tại thì nhập tên mới
            if (studentManager.getListStudentById(student.getId()).isEmpty()) {
                inputer.inputStudentName();
            } else {
                // Tự động lấy tên từ bản ghi đã có
                String name = studentManager.getListStudentById(student.getId()).get(0).getStudentName();
                student.setStudentName(name);
                System.out.println("Name: " + name);
            }
            // Nhập học kỳ và môn học 
            inputer.inputSemester();
            // Thêm sinh viên vào danh sách
            inputer.inputCourseName();
            // Khi số lượng sinh viên lớn hơn 5 thì hỏi tiếp tục hay không
            if (!studentManager.add(student)) {
                throw new Exception("Can not create Student!");
            }
            if (studentManager.getList().size() > 5) {
                String choice = Validator.getString("Do you continue ( Y or N)? ", "Just Y or N", "[YNyn]");
                if (choice.equalsIgnoreCase("N")) {
                    break;
                }
            }
        }
    }

    /**
     * Chức năng (Làm gì): Tìm kiếm sinh viên theo tên (hoặc một phần) và sắp xếp kết quả.
     * Luồng xử lý (Làm như thế nào):
     * 1. Yêu cầu người dùng nhập tên (chỉ gồm chữ cái và khoảng trắng).
     * 2. Gọi studentManager để lấy danh sách sinh viên phù hợp.
     * 3. Nếu danh sách rỗng, ném Exception.
     * 4. Khởi tạo một đối tượng ManageStudent mới để giữ kết quả, sau đó gọi hàm sortStudentsByName() để sắp xếp.
     * 5. In kết quả đã được sắp xếp ra màn hình.
     *
     * @throws Exception nếu không tìm thấy sinh viên
     */
    public void findAndSort() throws Exception {
        // Nhập tên cần tìm
        String name = Validator.getString("Enter name student: ", "Invalid!", "[A-Za-z\\s]+");
        // Lấy danh sách sinh viên phù hợp
        ArrayList<Student> list = studentManager.getListStudentByName(name);
        // Kiểm tra kết quả tìm kiếm
        if (list.isEmpty()) {
            throw new Exception("Can not found name!");
        }
        // Sắp xếp danh sách theo tên
        ManageStudent result = new ManageStudent();
        result.setList(list);
        result.sortStudentsByName();
        // Hiển thị kết quả
        System.out.println(result.toString());
    }

    /**
     * Chức năng (Làm gì): Cập nhật hoặc xóa thông tin sinh viên theo ID.
     * Luồng xử lý (Làm như thế nào):
     * 1. Yêu cầu nhập ID cần tìm.
     * 2. Tìm danh sách sinh viên có cùng ID. Nếu trống ném Exception.
     * 3. Hiển thị danh sách các bản ghi tìm được và yêu cầu chọn một bản ghi cụ thể.
     * 4. Hỏi người dùng muốn Update (U) hay Delete (D).
     * 5. Nếu Update: Nhập ID mới, nếu ID mới đã tồn tại thì lấy tên cũ, nếu chưa thì nhập tên mới. Nhập kỳ học, môn học mới và tiến hành update thông qua studentManager.
     * 6. Nếu Delete: Tiến hành xóa bản ghi đã chọn thông qua studentManager.
     *
     * @throws Exception nếu không tìm thấy ID, chọn vượt khoảng hoặc thông tin cập nhật không hợp lệ
     */
    public void updateOrDelete() throws Exception {
        inputer = new StudentInputer();
        // Nhập ID cần tìm
        String id = Validator.getString("Enter id: ", "Invalid!", "[Ss]\\d+");
        // Tìm danh sách sinh viên có cùng ID
        ArrayList<Student> list = studentManager.getListStudentById(id);
        if (list.isEmpty()) {
            throw new Exception("Can not found id");
        }
        // Hiển thị các bản ghi tìm được
        ManageStudent result = new ManageStudent();
        result.setList(list);
        System.out.println(result.toString());
        // Chọn bản ghi cần xử lý
        int choice = Validator.getInt("Enter record your choice: ", "Just be 1-> " + list.size(),
                "Invalid!", 1, list.size());
        Student student = list.get(choice - 1);
        System.out.println(student);

        // Chọn cập nhật hoặc xóa
        String choose = Validator.getString("Do you want Update(U) or Delete(D): ",
                "Just U or D", "[UDud]");
        if (choose.equalsIgnoreCase("U")) {
            // Nhập thông tin mới
            inputer.inputID();
            Student newStudent = inputer.getStudent();
            // Nếu ID đã tồn tại thì sử dụng tên cũ
            if (studentManager.getListStudentById(newStudent.getId()).isEmpty()) {
                inputer.inputStudentName();
            } else {
                String name = studentManager.getListStudentById(newStudent.getId()).get(0).getStudentName();
                newStudent.setStudentName(name);
                System.out.println("Name: " + name);
            }
            // Nhập học kỳ và môn học mới
            inputer.inputSemester();
            inputer.inputCourseName();
            studentManager.update(student, newStudent);
            // Cập nhật dữ liệu
        } else {
            // Xóa bản ghi đã chọn
            studentManager.delete(student);
        }
    }

    /**
     * Chức năng (Làm gì): Hiển thị báo cáo thống kê số lần học từng môn của các sinh viên.
     * Luồng xử lý (Làm như thế nào):
     * 1. Gọi studentManager.report() để tạo chuỗi báo cáo.
     * 2. Nếu trả về null (hoặc không hợp lệ) thì ném Exception.
     * 3. In kết quả chuỗi thống kê ra màn hình.
     *
     * @throws Exception nếu danh sách sinh viên rỗng
     */
    public void report() throws Exception {
        // Tạo báo cáo
        String result = studentManager.report();
        if (result == null) {
            throw new Exception("List is empty!");
        }
        // Hiển thị báo cáo
        System.out.println(result);
    }

    /**
     * Chức năng (Làm gì): Tạo dữ liệu mẫu để kiểm thử chương trình nhanh chóng.
     * Luồng xử lý (Làm như thế nào): Lần lượt khởi tạo và thêm một vài sinh viên mẫu vào danh sách qua studentManager.
     *
     * @throws Exception nếu dữ liệu thêm bị trùng
     */
    public void generateStudent() throws Exception {
        studentManager.add(new Student("s1", "Nguyen Quan", "Fall2023", Course.JAVA));
        studentManager.add(new Student("s1", "Nguyen Quan", "Fall2024", Course.JAVA));
        studentManager.add(new Student("s1", "Nguyen Quan", "Fall2023", Course.C_CPP));
        studentManager.add(new Student("s1", "Nguyen Quan", "Fall2023", Course.DOT_NET));
        studentManager.add(new Student("s8", "Vu Quan", "Fall2023", Course.DOT_NET));
        studentManager.add(new Student("s2", "Tran Linh", "Sum2024", Course.JAVA));
        studentManager.add(new Student("s2", "Tran Linh", "Sum2024", Course.DOT_NET));
        studentManager.add(new Student("s3", "Le Thu Thao", "Sum2024", Course.JAVA));
        studentManager.add(new Student("s4", "Le Phuong Minh", "Sum2024", Course.DOT_NET));
        studentManager.add(new Student("s5", "Minh Vu", "Spring2023", Course.JAVA));
        studentManager.add(new Student("s6", "Tuan Minh", "Spring2023", Course.C_CPP));
        studentManager.add(new Student("s7", "Quang Vu", "Fall2023", Course.DOT_NET));
        studentManager.add(new Student("s8", "Vu Quan", "Fall2024", Course.DOT_NET));
    }

    /**
     * Chức năng (Làm gì): Chạy luồng chương trình chính với vòng lặp menu.
     * Luồng xử lý (Làm như thế nào):
     * 1. Sinh dữ liệu mẫu ban đầu.
     * 2. Hiển thị menu cho đến khi người dùng chọn Exit (5).
     * 3. Điều hướng thực thi các chức năng 1-4 (Create, Find and Sort, Update/Delete, Report).
     */
    public void run() {
        try {
            generateStudent();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        while (true) {
            int choice = Validator.getInt("WELCOME TO STUDENT MANAGEMENT\n"
                    + "1.\tCreate\n"
                    + "2.\tFind and Sort\n"
                    + "3.\tUpdate/Delete\n"
                    + "4.\tReport\n"
                    + "5.\tExit\n"
                    + "Enter your choice: ", "Just be 1->5", "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        createStudent();
                        System.out.println("Add success!");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        findAndSort();
                        System.out.println("Find and sort success!");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        updateOrDelete();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try {
                        report();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    return;
            }
        }
    }
}
