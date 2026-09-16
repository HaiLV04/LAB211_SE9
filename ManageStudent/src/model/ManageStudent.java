package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Chức năng: Lớp ManageStudent chuyên quản lý bộ nhớ dữ liệu các sinh viên.
 * Luồng tương tác: Bị gọi từ Controller để thi hành các tác vụ xử lý trên danh sách dữ liệu (như thêm, sửa, xóa, tìm kiếm, xuất báo cáo).
 */
public class ManageStudent {

    /**
     * Danh sách lưu trữ thông tin các sinh viên.
     */
    private List<Student> list;

    /**
     * Chức năng: Khởi tạo đối tượng ManageStudent với danh sách sinh viên rỗng.
     * Luồng xử lý:
     * 1. Tạo mới một ArrayList trống cho thuộc tính list.
     */
    public ManageStudent() {
        list = new ArrayList<>();
    }

    /**
     * Chức năng: Trả về danh sách sinh viên hiện tại.
     * Luồng xử lý:
     * 1. Trả về thuộc tính list chứa thông tin các sinh viên.
     *
     * @return danh sách sinh viên
     */
    public List<Student> getList() {
        return list;
    }

    /**
     * Chức năng: Cập nhật danh sách sinh viên.
     * Luồng xử lý:
     * 1. Gán mảng đầu vào thay thế cho thuộc tính list hiện tại.
     *
     * @param list danh sách sinh viên mới
     */
    public void setList(List<Student> list) {
        this.list = list;
    }

    /**
     * Chức năng: Kiểm tra xem bản ghi sinh viên đã tồn tại trong danh sách hay chưa.
     * Luồng xử lý:
     * 1. Duyệt qua toàn bộ danh sách sinh viên hiện tại.
     * 2. Nếu tìm thấy sinh viên nào trùng Id, trùng Semester và trùng CourseName với đầu vào thì trả về true.
     * 3. Nếu duyệt hết không tìm thấy thì trả về false.
     *
     * @param student sinh viên cần kiểm tra
     * @return true nếu đã tồn tại, ngược lại false
     */
    public boolean isExisted(Student student) {
        // Duyệt toàn bộ danh sách sinh viên

        for (Student students : list) {
            // Kiểm tra trùng ID, Semester và Course
            if (students.getId().equals(student.getId())
                    && students.getSemester().equals(student.getSemester())
                    && students.getCourseName().equals(student.getCourseName())) {
                return true;
            }
        }
        // Không tìm thấy bản ghi trùng

        return false;
    }

    /**
     * Chức năng: Thêm một sinh viên vào danh sách.
     * Luồng xử lý:
     * 1. Kiểm tra xem bản ghi sinh viên đã tồn tại chưa bằng isExisted. Nếu có ném Exception.
     * 2. Nếu không trùng, thêm sinh viên vào danh sách list và trả về true.
     *
     * @param student sinh viên cần thêm
     * @return true nếu thêm thành công
     * @throws Exception nếu bản ghi đã tồn tại
     */
    public boolean add(Student student) throws Exception {
        // Không cho phép thêm bản ghi trùng lặp
        if (isExisted(student)) {
            throw new Exception("This record is existed!");
        }
        // Thêm sinh viên vào danh sách
        return list.add(student);
    }

    /**
     * Chức năng: Xóa một sinh viên khỏi danh sách.
     * Luồng xử lý:
     * 1. Kiểm tra list, nếu trống thì ném Exception.
     * 2. Kiểm tra bản ghi sinh viên bằng isExisted, nếu không tồn tại thì ném Exception.
     * 3. Nếu hợp lệ, tiến hành gọi phương thức remove của ArrayList để xóa sinh viên và trả về kết quả.
     *
     * @param student sinh viên cần xóa
     * @return true nếu xóa thành công
     * @throws Exception nếu danh sách rỗng hoặc không tìm thấy bản ghi
     */
    public boolean delete(Student student) throws Exception {
        // Kiểm tra danh sách rỗng
        if (list.isEmpty()) {
            throw new Exception("List is empty, can not delete");
        }
        // Kiểm tra bản ghi có tồn tại hay không
        if (!isExisted(student)) {
            throw new Exception("This record can not found!");
        }
        // Xóa sinh viên khỏi danh sách
        return list.remove(student);
    }

    /**
     * Chức năng: Tìm vị trí (index) của một bản ghi trong danh sách.
     * Luồng xử lý:
     * 1. Duyệt qua mảng sinh viên với chỉ số i từ 0 tới size.
     * 2. Trả về i nếu đối tượng tại vị trí i trùng khớp (equals) với đối tượng đầu vào.
     * 3. Trả về -1 nếu duyệt hết mà không tìm thấy.
     *
     * @param student sinh viên cần tìm
     * @return vị trí của bản ghi, -1 nếu không tìm thấy
     */
    private int getIndexRecord(Student student) {
        // Duyệt danh sách để tìm vị trí của bản ghi
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(student)) {
                return i;
            }
        }
        // Không tìm thấy bản ghi
        return -1;
    }

    /**
     * Chức năng: Thay thế thông tin một bản ghi sinh viên cũ bằng bản ghi sinh viên mới.
     * Luồng xử lý:
     * 1. Nếu danh sách rỗng, ném Exception không thể cập nhật.
     * 2. Nếu bản ghi cũ không tồn tại, ném Exception.
     * 3. Nếu bản ghi mới bị trùng lặp với một bản ghi đã có (theo Id, khóa học, học kỳ), ném Exception.
     * 4. Gọi getIndexRecord lấy vị trí của bản ghi cũ, sau đó sử dụng list.set(index, mới) để thay đổi.
     *
     * @param oldStudentRecord bản ghi cũ
     * @param newStudentRecord bản ghi mới
     * @throws Exception nếu danh sách rỗng, không tìm thấy bản ghi hoặc bản ghi mới bị trùng lặp
     */
    public void update(Student oldStudentRecord, Student newStudentRecord) throws Exception {
        // Kiểm tra danh sách rỗng
        if (list.isEmpty()) {
            throw new Exception("List is empty can not update");
        }
        // Kiểm tra bản ghi cũ có tồn tại hay không
        if (!isExisted(oldStudentRecord)) {
            throw new Exception("This record can not found!");
        } else {
            // Kiểm tra bản ghi mới có bị trùng không
            if (isExisted(newStudentRecord)) {
                throw new Exception("New record be duplicate!!");
            }
            // Thay thế bản ghi cũ bằng bản ghi mới
            list.set(getIndexRecord(oldStudentRecord), newStudentRecord);
        }
    }

    /**
     * Chức năng: Tìm và lọc ra danh sách sinh viên theo một mã ID cụ thể.
     * Luồng xử lý:
     * 1. Khởi tạo một ArrayList kết quả trống.
     * 2. Duyệt list hiện tại, nếu Id đối tượng trùng với Id đầu vào (không phân biệt hoa thường), add vào danh sách kết quả.
     * 3. Trả về danh sách kết quả tìm được.
     *
     * @param id mã sinh viên cần tìm
     * @return danh sách sinh viên có cùng ID
     */
    public ArrayList<Student> getListStudentById(String id) {
        ArrayList<Student> result = new ArrayList<>();
        // Tìm tất cả sinh viên có cùng ID
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    /**
     * Chức năng: Tìm và lọc danh sách sinh viên dựa theo tên hoặc một phần của tên.
     * Luồng xử lý:
     * 1. Khởi tạo danh sách kết quả trống.
     * 2. Duyệt qua mảng sinh viên hiện tại, dùng contains kết hợp toLowerCase để so khớp tên không phân biệt hoa thường.
     * 3. Nếu khớp thì add vào kết quả, sau khi duyệt xong thì trả về kết quả đó.
     *
     * @param name tên hoặc một phần tên sinh viên
     * @return danh sách sinh viên phù hợp
     */
    public ArrayList<Student> getListStudentByName(String name) {
        ArrayList<Student> result = new ArrayList<>();
        // Tìm sinh viên theo tên (không phân biệt hoa thường)
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStudentName().toLowerCase().contains(name.toLowerCase())) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    /**
     * Chức năng: Sắp xếp danh sách sinh viên theo tên với thứ tự tăng dần.
     * Luồng xử lý:
     * 1. Sử dụng thuật toán sắp xếp của Collections.sort() trên thuộc tính list. 
     * 2. Collections.sort() sẽ dựa vào phương thức compareTo() đã override trong lớp Student để sắp xếp tên.
     */
    public void sortStudentsByName() {
        // Sử dụng compareTo() của lớp Student để sắp xếp
        Collections.sort(list);
    }

    /**
     * Chức năng: Tạo chuỗi biểu diễn danh sách sinh viên dưới dạng bảng theo các tiêu chí (Số thứ tự, tên, kỳ học, môn học).
     * Luồng xử lý:
     * 1. Nếu list trống, trả về null.
     * 2. Tạo phần tiêu đề bảng (header).
     * 3. Duyệt mảng sinh viên và định dạng thành từng dòng (format bảng), sau đó nối tiếp vào chuỗi.
     * 4. Trả về toàn bộ chuỗi bảng.
     *
     * @return thông tin danh sách sinh viên đã định dạng
     */
    @Override
    public String toString() {
        // Kiểm tra danh sách rỗng
        if (list.isEmpty()) {
            return null;
        }
        // Tạo tiêu đề bảng
        
//neu muon hien thi ca ID
//        String str = String.format("|%5s|%10s|%20s|%10s|%15s|\n",
//        "No.", "ID", "Student Name", "Semester", "Course Name");
//for (int i = 0; i < list.size(); i++) {
//    str += String.format("|%5s|%10s|%20s|%10s|%15s|\n",
//            i + 1,
//            list.get(i).getId(),
//            list.get(i).getStudentName(),
//            list.get(i).getSemester(),
//            list.get(i).getCourseName().getLanguage());
//}
        String str = String.format("|%5s|%15s|%10s|%15s|\n", "No.", "Student Name",
                "Semester", "Course Name");

        // Thêm thông tin từng sinh viên vào bảng
        for (int i = 0; i < list.size(); i++) {
            str += String.format("|%5s|%15s|%10s|%15s|\n", i + 1,
                    list.get(i).getStudentName(),
                    list.get(i).getSemester(),
                    list.get(i).getCourseName().getLanguage());
        }
        return str;
    }

    /**
     * Chức năng: Báo cáo danh sách thống kê khóa học (đếm số lần học cùng một môn của một sinh viên).
     * Luồng xử lý:
     * 1. Trả về cảnh báo nếu danh sách rỗng.
     * 2. Sắp xếp danh sách ưu tiên theo tên bằng hàm Collections.sort(list, comparator).
     * 3. Duyệt danh sách, gộp các khóa có cùng Id và Môn học thông qua 2 đối tượng HashMap để đếm số lần (countMap) và cộng dồn các kỳ (semesterMap).
     * 4. Khởi tạo một danh sách phụ (printedKeys) để tránh in trùng các dòng.
     * 5. Duyệt lại mảng đã sắp xếp, với mỗi sinh viên nếu khóa chưa xuất hiện trong printedKeys thì in ra và thêm vào khóa đã xử lý.
     * 6. Trả về toàn bộ bảng báo cáo.
     *
     * @return Chuỗi nội dung báo cáo thống kê
     */
    public String report() {
        if (list.isEmpty()) {
            return "List is empty!";
        }

        // Bước 1: Sắp xếp danh sách gốc theo tên trước khi làm report
        Collections.sort(list, (s1, s2) -> s1.getStudentName().compareToIgnoreCase(s2.getStudentName()));

        // Bước 2: Dùng một Map để lưu trữ kết quả thống kê
        // Key: ID + Course Name (để phân biệt cùng 1 người học nhiều môn khác nhau)
        // Value: Một đối tượng hoặc chuỗi chứa thông tin tổng hợp
        HashMap<String, Integer> countMap = new HashMap<>();
        HashMap<String, String> semesterMap = new HashMap<>();

        for (Student s : list) {
            String key = s.getId() + "|" + s.getCourseName().getLanguage();

            // Đếm số lần học
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);

            // Cộng dồn các kỳ học (ví dụ: "Spring, Summer")
            String sem = s.getSemester();
            if (semesterMap.containsKey(key)) {
                if (!semesterMap.get(key).contains(sem)) {
                    semesterMap.put(key, semesterMap.get(key) + ", " + sem);
                }
            } else {
                semesterMap.put(key, sem);
            }
        }

        // Bước 3: Build chuỗi hiển thị
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("|%-5s|%-20s|%-15s|%-10s|%-20s|\n",
                "No.", "Student Name", "Course", "Total", "Semesters"));
        sb.append("--------------------------------------------------------------------------\n");
        
        //neu muon them ID
//        sb.append(String.format("|%-5s|%-10s|%-20s|%-15s|%-10s|%-20s|\n",
//        "No.", "ID", "Student Name", "Course", "Total", "Semesters"));
//sb.append("--------------------------------------------------------------------------------------\n");

        int count = 1;
        // Để in đúng thứ tự đã sắp xếp, ta nên duyệt qua list đã sort thay vì duyệt qua Map
        // Nhưng để tránh trùng lặp dòng khi in, ta dùng một Set để đánh dấu
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
//neu muon them id
//sb.append(String.format("|%-5d|%-10s|%-20s|%-15s|%-10d|%-20s|\n",
//        count++,
//        s.getId(),
//        s.getStudentName(),
//        s.getCourseName().getLanguage(),
//        countMap.get(key),
//        semesterMap.get(key)));
                printedKeys.add(key);
            }
        }
        return sb.toString();
    }
}
