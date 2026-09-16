package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Chức năng: Lớp quản lý danh sách công nhân.
 * Luồng tương tác: Nhận dữ liệu từ Controller, xử lý thêm, tìm kiếm công nhân và thay đổi mức lương.
 */
public class ManagerWorker {

    private List<Worker> list;

    /**
     * Luồng xử lý 1: Trả về bản sao của danh sách công nhân hiện tại.
     *
     * @return danh sách công nhân
     */
    public List<Worker> getList() {
        return new ArrayList<>(list);
    }

    /**
     * Luồng xử lý 1: Khởi tạo danh sách công nhân rỗng dưới dạng ArrayList.
     */
    public ManagerWorker() {
        this.list = new ArrayList<>();
    }

    /**
     * Luồng xử lý 1: Duyệt danh sách công nhân.
     * Luồng xử lý 2: So sánh không phân biệt chữ hoa, chữ thường với mã ID.
     * Luồng xử lý 3: Trả về Worker nếu tìm thấy, ngược lại trả về null.
     *
     * @param id mã công nhân cần tìm
     * @return đối tượng Worker nếu tìm thấy, ngược lại trả về null
     */
    private Worker getWorker(String id) {
        for (Worker workers : list) {
            if (workers.getId().equalsIgnoreCase(id)) {
                return workers;
            }
        }
        return null;
    }

    /**
     * Luồng xử lý 1: Duyệt danh sách để kiểm tra mã công nhân.
     * Luồng xử lý 2: Trả về true nếu đã tồn tại, ngược lại false.
     *
     * @param id mã công nhân cần kiểm tra
     * @return true nếu tồn tại, ngược lại false
     */
    public boolean isExist(String id) {
        for (Worker workers : list) {
            if (workers.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Luồng xử lý 1: Kiểm tra mã công nhân tồn tại hay không.
     * Luồng xử lý 2: Nếu chưa tồn tại, thêm vào danh sách. Nếu có, ném ra ngoại lệ.
     *
     * @param worker công nhân cần thêm
     * @return true nếu thêm thành công
     * @throws Exception nếu mã công nhân đã tồn tại
     */
    public boolean add(Worker worker) throws Exception {
        if (isExist(worker.getId())) {
            throw new Exception("Worker with ID " + worker.getId() + " already exists.");
        }
        return list.add(worker);
    }

    /**
     * Luồng xử lý 1: Kiểm tra mã công nhân có tồn tại hay không.
     * Luồng xử lý 2: Kiểm tra số tiền lượng thay đổi phải lớn hơn 0.
     * Luồng xử lý 3: Tuỳ vào trạng thái UP hay DOWN để thực hiện cộng thêm hoặc trừ lương.
     *
     * @param status trạng thái thay đổi lương (UP hoặc DOWN)
     * @param code mã công nhân
     * @param amount số tiền thay đổi
     * @return công nhân sau khi cập nhật lương
     * @throws Exception nếu không tìm thấy công nhân, số tiền không hợp lệ hoặc lương sau khi giảm < 0
     */
    public Worker changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        if (!isExist(code)) {
            throw new Exception("Can not found code!");
        }
        if (amount <= 0) {
            throw new Exception("§Amount of money must be > 0 ");
        }
        Worker worker = getWorker(code);
        switch (status) {
            case UP:
                worker.setSalary(worker.getSalary() + amount);
                break;
            case DOWN:
                if (worker.getSalary() - amount < 0) {
                    throw new Exception("Can not down " + amount);
                }
                worker.setSalary(worker.getSalary() - amount);
                break;
        }
        return worker;
    }

}
