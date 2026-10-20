package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Chức năng: Quản lý danh sách sinh viên, thực hiện các thao tác thêm, sửa, xóa, tìm kiếm, sắp xếp và báo cáo.
 */
public class ManageStudent {

    private List<Student> list;

    /**
     * Khởi tạo đối tượng ManageStudent với danh sách rỗng.
     */
    public ManageStudent() {
        list = new ArrayList<>();
    }

    /**
     * Lấy danh sách sinh viên hiện tại.
     *
     * @return danh sách sinh viên
     */
    public List<Student> getList() {
        return list;
    }

    /**
     * Thiết lập danh sách sinh viên mới.
     *
     * @param list danh sách sinh viên mới
     */
    public void setList(List<Student> list) {
        this.list = list;
    }

    /**
     * Chức năng: Kiểm tra xem bản ghi sinh viên đã tồn tại trong danh sách chưa.
     * Luồng xử lý: Duyệt danh sách, nếu trùng cả ID, Semester và CourseName thì trả về true, ngược lại false.
     *
     * @param student sinh viên cần kiểm tra
     * @return true nếu đã tồn tại, ngược lại false
     */
    public boolean isExisted(Student student) {
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(student.getId())
                    && s.getSemester().equalsIgnoreCase(student.getSemester())
                    && s.getCourseName().equals(student.getCourseName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Chức năng: Thêm một sinh viên mới vào danh sách.
     * Luồng xử lý: Kiểm tra trùng lặp bằng isExisted(), nếu trùng ném Exception, ngược lại thêm vào danh sách.
     *
     * @param student sinh viên cần thêm
     * @return true nếu thêm thành công
     * @throws Exception nếu bản ghi đã tồn tại
     */
    public boolean add(Student student) throws Exception {
        if (isExisted(student)) {
            throw new Exception("This record is existed!");
        }
        return list.add(student);
    }

    /**
     * Chức năng: Xóa một sinh viên khỏi danh sách.
     * Luồng xử lý: Kiểm tra danh sách rỗng hoặc không tìm thấy bản ghi thì ném Exception, ngược lại xóa khỏi list.
     *
     * @param student sinh viên cần xóa
     * @return true nếu xóa thành công
     * @throws Exception nếu danh sách rỗng hoặc không tìm thấy bản ghi
     */
    public boolean delete(Student student) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List is empty, can not delete");
        }
        if (!isExisted(student)) {
            throw new Exception("This record can not found!");
        }
        return list.remove(student);
    }

    /**
     * Chức năng: Tìm vị trí (chỉ số) của bản ghi trong danh sách.
     * Luồng xử lý: Duyệt danh sách và trả về vị trí khớp equals(), không tìm thấy trả về -1.
     *
     * @param student sinh viên cần tìm vị trí
     * @return vị trí trong danh sách, hoặc -1 nếu không tìm thấy
     */
    private int getIndexRecord(Student student) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(student)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Chức năng: Cập nhật thông tin bản ghi sinh viên cũ bằng bản ghi mới.
     * Luồng xử lý: Kiểm tra bản ghi cũ tồn tại và bản ghi mới không bị trùng, sau đó thay thế tại vị trí cũ.
     *
     * @param oldStudentRecord bản ghi cũ
     * @param newStudentRecord bản ghi mới
     * @throws Exception nếu danh sách rỗng, không tìm thấy bản ghi cũ hoặc bản ghi mới bị trùng lặp
     */
    public void update(Student oldStudentRecord, Student newStudentRecord) throws Exception {
        if (list.isEmpty()) {
            throw new Exception("List is empty can not update");
        }
        if (!isExisted(oldStudentRecord)) {
            throw new Exception("This record can not found!");
        }
        if (isExisted(newStudentRecord)) {
            throw new Exception("New record be duplicate!!");
        }
        list.set(getIndexRecord(oldStudentRecord), newStudentRecord);
    }

    /**
     * Chức năng: Lọc danh sách sinh viên theo mã ID.
     * Luồng xử lý: Duyệt danh sách và lấy các sinh viên có ID trùng khớp (không phân biệt hoa thường).
     *
     * @param id mã ID cần tìm
     * @return danh sách sinh viên có cùng ID
     */
    public ArrayList<Student> getListStudentById(String id) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : list) {
            if (s.getId().equalsIgnoreCase(id)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Chức năng: Tìm danh sách sinh viên theo tên hoặc một phần của tên.
     * Luồng xử lý: Duyệt danh sách và lấy các sinh viên có tên chứa từ khóa tìm kiếm.
     *
     * @param name từ khóa tên cần tìm
     * @return danh sách sinh viên phù hợp
     */
    public ArrayList<Student> getListStudentByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : list) {
            if (s.getStudentName().toLowerCase().contains(name.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Chức năng: Sắp xếp danh sách sinh viên theo tên tăng dần.
     * Luồng xử lý: Sử dụng Collections.sort() dựa theo phương thức compareTo() của lớp Student.
     */
    public void sortStudentsByName() {
        Collections.sort(list);
    }

    /**
     * Chức năng: Định dạng danh sách sinh viên thành bảng hiển thị.
     * Luồng xử lý: Duyệt danh sách và nối các dòng thông tin thành bảng có tiêu đề.
     *
     * @return chuỗi biểu diễn bảng danh sách sinh viên
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%5s|%15s|%10s|%15s|\n", "No.", "Student Name", "Semester", "Course Name"));
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("|%5s|%15s|%10s|%15s|\n",
                    i + 1,
                    list.get(i).getStudentName(),
                    list.get(i).getSemester(),
                    list.get(i).getCourseName().getLanguage()));
        }
        return sb.toString();
    }

    /**
     * Chức năng: Báo cáo thống kê số lần học từng môn của mỗi sinh viên.
     * Luồng xử lý: Sắp xếp theo tên, dùng Map đếm số môn và gom các kỳ học, sau đó trả về bảng thống kê.
     *
     * @return chuỗi nội dung báo cáo thống kê
     */
    public String report() {
        if (list.isEmpty()) {
            return "List is empty!";
        }

        // Sắp xếp theo tên trước khi thống kê
        Collections.sort(list, (s1, s2) -> s1.getStudentName().compareToIgnoreCase(s2.getStudentName()));

        HashMap<String, Integer> countMap = new HashMap<>();
        HashMap<String, String> semesterMap = new HashMap<>();

        for (Student s : list) {
            String key = s.getId() + "|" + s.getCourseName().getLanguage();
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);

            String sem = s.getSemester();
            if (semesterMap.containsKey(key)) {
                if (!semesterMap.get(key).contains(sem)) {
                    semesterMap.put(key, semesterMap.get(key) + ", " + sem);
                }
            } else {
                semesterMap.put(key, sem);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%-5s|%-20s|%-15s|%-10s|%-20s|\n",
                "No.", "Student Name", "Course", "Total", "Semesters"));
        sb.append("--------------------------------------------------------------------------\n");

        int count = 1;
        List<String> printedKeys = new ArrayList<>();

        for (Student s : list) {
            String key = s.getId() + "|" + s.getCourseName().getLanguage();
            if (!printedKeys.contains(key)) {
                sb.append(String.format("|%-5d|%-20s|%-15s|%-10d|%-20s|\n",
                        count++,
                        s.getStudentName(),
                        s.getCourseName().getLanguage(),
                        countMap.get(key),
                        semesterMap.get(key)));
                printedKeys.add(key);
            }
        }
        return sb.toString();
    }
}
