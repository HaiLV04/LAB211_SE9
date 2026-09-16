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
     * Luồng xử lý 1: Khởi tạo danh sách lịch sử rỗng dưới dạng ArrayList.
     */
    public ManagerSalaryHistory() {
        this.list = new ArrayList<>();
    }

    /**
     * Luồng xử lý 1: Duyệt danh sách lịch sử hiện có.
     * Luồng xử lý 2: Kiểm tra bản ghi trùng bằng cách so sánh mã công nhân, trạng thái, ngày và mức lương.
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
     * Luồng xử lý 1: Kiểm tra bản ghi có tồn tại chưa.
     * Luồng xử lý 2: Thêm bản ghi vào danh sách nếu chưa có, nếu không ném ra ngoại lệ.
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
     * Luồng xử lý 1: Sắp xếp danh sách lịch sử sử dụng Collections.sort (theo ID công nhân dựa trên compareTo).
     */
    private void sortByID() {
        Collections.sort(list);
    }

    /**
     * Luồng xử lý 1: Nếu danh sách rỗng, trả về null.
     * Luồng xử lý 2: Nếu có dữ liệu, sắp xếp danh sách theo ID công nhân.
     * Luồng xử lý 3: Định dạng các thông tin lịch sử (ngày tháng năm) và xây dựng chuỗi kết quả.
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
