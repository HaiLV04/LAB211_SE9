package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Chức năng: Quản lý danh sách lịch sử thay đổi lương của công nhân.
 */
public class ManagerSalaryHistory {

    private List<SalaryHistory> list;

    /**
     * Khởi tạo đối tượng ManagerSalaryHistory với danh sách rỗng.
     */
    public ManagerSalaryHistory() {
        this.list = new ArrayList<>();
    }

    /**
     * Chức năng: Kiểm tra xem bản ghi lịch sử lương đã tồn tại hay chưa.
     * Luồng xử lý: Duyệt danh sách, trả về true nếu trùng cả ID, trạng thái, ngày và mức lương, ngược lại false.
     *
     * @param history bản ghi cần kiểm tra
     * @return true nếu đã tồn tại, ngược lại false
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
     * Chức năng: Thêm một bản ghi lịch sử thay đổi lương vào danh sách.
     * Luồng xử lý: Kiểm tra trùng lặp bằng isExisted(), nếu trùng ném Exception, ngược lại thêm vào list.
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
     * Chức năng: Sắp xếp danh sách lịch sử thay đổi lương theo mã công nhân tăng dần.
     * Luồng xử lý: Sử dụng Collections.sort() dựa theo phương thức compareTo() của lớp SalaryHistory.
     */
    private void sortByID() {
        Collections.sort(list);
    }

    /**
     * Chức năng: Định dạng danh sách lịch sử lương thành chuỗi bảng hiển thị.
     * Luồng xử lý: Sắp xếp theo ID, duyệt danh sách và nối các thông tin (Code, Name, Age, Salary, Status, Date) thành bảng.
     *
     * @return chuỗi biểu diễn bảng lịch sử lương, hoặc null nếu danh sách rỗng
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        sortByID();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%7s%10s%10s%10s%10s%15s\n", "Code", "Name", "Age", "Salary", "Status", "Date"));
        for (int i = 0; i < list.size(); i++) {
            String formattedDate = dateFormat.format(list.get(i).getDate());
            sb.append(String.format("%7s%10s%10d%10.0f%10s%15s\n", list.get(i).getWorker().getId(),
                    list.get(i).getWorker().getName(), list.get(i).getWorker().getAge(),
                    list.get(i).getSalaryUpdate(), list.get(i).getStatus(), formattedDate));
        }
        return sb.toString();
    }
}
