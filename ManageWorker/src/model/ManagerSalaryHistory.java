package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Chức năng: Lớp quản lý danh sách lịch sử thay đổi lương của công nhân.
 * Luồng tương tác: Nhận yêu cầu lưu lịch sử từ Controller, và chuẩn bị dữ liệu chuỗi để hiển thị lịch sử thay đổi lương.
 */
public class ManagerSalaryHistory {

    private List<SalaryHistory> list;

    /**
     * Chức năng (Làm gì): Khởi tạo đối tượng ManagerSalaryHistory.
     * Luồng xử lý (Làm như thế nào): Khởi tạo thuộc tính list dưới dạng một ArrayList rỗng.
     */
    public ManagerSalaryHistory() {
        this.list = new ArrayList<>();
    }

    /**
     * Chức năng (Làm gì): Kiểm tra bản ghi lịch sử lương đã tồn tại trong danh sách hay chưa.
     * Luồng xử lý (Làm như thế nào):
     * 1. Duyệt danh sách lịch sử hiện có.
     * 2. Kiểm tra bản ghi trùng bằng cách so sánh mã công nhân, trạng thái, ngày và mức lương.
     * 3. Trả về true nếu trùng lặp toàn bộ, ngược lại trả về false.
     *
     * @param history bản ghi cần kiểm tra
     * @return true nếu bản ghi đã tồn tại, ngược lại false
     */
    private boolean isExisted(SalaryHistory history) {
        for (SalaryHistory salaryHistory : list) {
            if (salaryHistory.getWorker().getId().equalsIgnoreCase(history.getWorker().getId())
                    && salaryHistory.getStatus().equals(history.getStatus())
                    && salaryHistory.getDate().equals(history.getDate())
                    && salaryHistory.getSalaryUpdate() == history.getSalaryUpdate()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Chức năng (Làm gì): Thêm một bản ghi lịch sử thay đổi lương vào danh sách.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra bản ghi đã tồn tại chưa bằng phương thức isExisted.
     * 2. Thêm bản ghi vào danh sách nếu chưa có, nếu đã tồn tại thì ném ra ngoại lệ.
     *
     * @param history bản ghi lịch sử cần thêm
     * @return true nếu thêm thành công
     * @throws Exception nếu bản ghi đã tồn tại
     */
    public boolean addSalaryHistory(SalaryHistory history) throws Exception {
        if (isExisted(history)) {
            throw new Exception("This record history is existed!!!");
        }
        return list.add(history);
    }

    /**
     * Chức năng (Làm gì): Sắp xếp danh sách lịch sử thay đổi lương.
     * Luồng xử lý (Làm như thế nào): Sử dụng Collections.sort() sắp xếp danh sách theo ID công nhân (dựa trên compareTo của SalaryHistory).
     */
    private void sortByID() {
        Collections.sort(list);
    }

    /**
     * Chức năng (Làm gì): Chuyển đổi danh sách lịch sử lương thành chuỗi hiển thị dạng bảng.
     * Luồng xử lý (Làm như thế nào):
     * 1. Nếu danh sách rỗng, trả về null.
     * 2. Sắp xếp danh sách theo ID công nhân bằng sortByID().
     * 3. Định dạng ngày tháng theo định dạng "dd/MM/yyyy".
     * 4. Tạo tiêu đề bảng và duyệt từng bản ghi để định dạng các cột (Code, Name, Age, Salary, Status, Date).
     * 5. Trả về chuỗi kết quả.
     *
     * @return chuỗi lịch sử lương hoặc null nếu danh sách rỗng
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        sortByID();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String str = String.format("%7s%10s%10s%10s%10s%15s\n", "Code", "Name", "Age", "Salary", "Status", "Date");
        for (int i = 0; i < list.size(); i++) {
            String formattedDate = dateFormat.format(list.get(i).getDate());
            str += String.format("%7s%10s%10d%10.0f%10s%15s\n", list.get(i).getWorker().getId(),
                    list.get(i).getWorker().getName(), list.get(i).getWorker().getAge(),
                    list.get(i).getSalaryUpdate(), list.get(i).getStatus(), formattedDate);
        }
        return str;
    }
}
