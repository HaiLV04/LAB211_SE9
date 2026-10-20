package controller;

import java.util.ArrayList;
import model.Course;
import model.ManageStudent;
import model.Student;
import view.StudentView;

/**
 * Chức năng: Điều khiển luồng thực thi chương trình quản lý sinh viên (tạo mới, tìm kiếm, cập nhật, xóa, thống kê).
 */
public class Controller {

    private ManageStudent studentManager;
    private StudentView view;

    /**
     * Khởi tạo Controller với đối tượng quản lý dữ liệu (Model) và hiển thị (View).
     */
    public Controller() {
        studentManager = new ManageStudent();
        view = new StudentView();
    }

    /**
     * Chức năng: Tạo mới sinh viên và thêm vào danh sách.
     * Luồng xử lý: Nhập thông tin qua view (tự điền tên nếu ID đã có), kiểm tra trùng bản ghi và hỏi tiếp tục khi danh sách > 5.
     *
     * @throws Exception nếu trùng lặp bản ghi
     */
    public void createStudent() throws Exception {
        while (true) {
            String id = view.inputId();
            String name;

            // Nếu ID đã tồn tại thì dùng lại tên cũ, ngược lại nhập tên mới
            if (studentManager.getListStudentById(id).isEmpty()) {
                name = view.inputStudentName();
            } else {
                name = studentManager.getListStudentById(id).get(0).getStudentName();
                view.displayName(name);
            }

            String semester = view.inputSemester();
            Course course = view.inputCourseName();
            Student student = new Student(id, name, semester, course);

            if (!studentManager.add(student)) {
                throw new Exception("Can not create Student!");
            }

            // Hỏi tiếp tục nếu danh sách đã có trên 5 sinh viên
            if (studentManager.getList().size() > 5) {
                if (!view.getContinueChoice()) {
                    break;
                }
            }
        }
    }

    /**
     * Chức năng: Tìm kiếm sinh viên theo tên và sắp xếp kết quả.
     * Luồng xử lý: Nhập từ khóa tên qua view, lấy danh sách phù hợp từ model, sắp xếp tăng dần và hiển thị qua view.
     *
     * @throws Exception nếu không tìm thấy sinh viên
     */
    public void findAndSort() throws Exception {
        String name = view.inputSearchName();
        ArrayList<Student> list = studentManager.getListStudentByName(name);

        if (list.isEmpty()) {
            throw new Exception("Can not found name!");
        }

        ManageStudent result = new ManageStudent();
        result.setList(list);
        result.sortStudentsByName();
        view.displayStudentList(result);
    }

    /**
     * Chức năng: Cập nhật hoặc xóa thông tin sinh viên theo ID.
     * Luồng xử lý: Tìm các bản ghi theo ID, cho người dùng chọn bản ghi và lựa chọn Cập nhật (U) hoặc Xóa (D).
     *
     * @throws Exception nếu không tìm thấy ID hoặc thông tin cập nhật không hợp lệ
     */
    public void updateOrDelete() throws Exception {
        String id = view.inputSearchId();
        ArrayList<Student> list = studentManager.getListStudentById(id);

        if (list.isEmpty()) {
            throw new Exception("Can not found id");
        }

        ManageStudent result = new ManageStudent();
        result.setList(list);
        view.displayStudentList(result);

        int choice = view.getRecordChoice(list.size());
        Student student = list.get(choice - 1);
        view.displayStudent(student);

        String choose = view.getUpdateOrDeleteChoice();
        if (choose.equalsIgnoreCase("U")) {
            String newId = view.inputId();
            String name;

            if (studentManager.getListStudentById(newId).isEmpty()) {
                name = view.inputStudentName();
            } else {
                name = studentManager.getListStudentById(newId).get(0).getStudentName();
                view.displayName(name);
            }

            String semester = view.inputSemester();
            Course course = view.inputCourseName();
            Student newStudent = new Student(newId, name, semester, course);

            studentManager.update(student, newStudent);
        } else {
            studentManager.delete(student);
        }
    }

    /**
     * Chức năng: Hiển thị báo cáo thống kê môn học của sinh viên.
     * Luồng xử lý: Lấy chuỗi thống kê từ model và hiển thị ra màn hình qua view.
     *
     * @throws Exception nếu danh sách sinh viên rỗng
     */
    public void report() throws Exception {
        String result = studentManager.report();
        if (result == null) {
            throw new Exception("List is empty!");
        }
        view.displayReport(result);
    }

    /**
     * Chức năng: Khởi tạo dữ liệu sinh viên mẫu để kiểm thử.
     * Luồng xử lý: Thêm một số sinh viên mẫu vào danh sách quản lý.
     *
     * @throws Exception nếu dữ liệu thêm bị trùng lặp
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
     * Chức năng: Điều khiển luồng thực thi chính của chương trình.
     * Luồng xử lý: Sinh dữ liệu mẫu, lặp hiển thị menu và điều hướng thực thi các chức năng 1-5 theo lựa chọn.
     */
    public void run() {
        try {
            generateStudent();
        } catch (Exception ex) {
            view.displayMessage(ex.getMessage());
        }
        while (true) {
            int choice = view.getMenuChoice();
            switch (choice) {
                case 1:
                    try {
                        createStudent();
                        view.displayMessage("Add success!");
                    } catch (Exception ex) {
                        view.displayMessage(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        findAndSort();
                        view.displayMessage("Find and sort success!");
                    } catch (Exception ex) {
                        view.displayMessage(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        updateOrDelete();
                    } catch (Exception ex) {
                        view.displayMessage(ex.getMessage());
                    }
                    break;
                case 4:
                    try {
                        report();
                    } catch (Exception ex) {
                        view.displayMessage(ex.getMessage());
                    }
                    break;
                case 5:
                    return;
            }
        }
    }
}
