package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Chức năng: Quản lý danh sách công nhân, xử lý thêm mới, tìm kiếm và thay đổi lương.
 */
public class ManagerWorker {

    private List<Worker> list;

    /**
     * Khởi tạo đối tượng ManagerWorker với danh sách rỗng.
     */
    public ManagerWorker() {
        this.list = new ArrayList<>();
    }

    /**
     * Lấy bản sao danh sách công nhân hiện tại.
     *
     * @return danh sách công nhân
     */
    public List<Worker> getList() {
        return new ArrayList<>(list);
    }

    /**
     * Chức năng: Tìm kiếm công nhân theo mã ID.
     * Luồng xử lý: Duyệt danh sách và trả về Worker có mã ID khớp (không phân biệt hoa thường).
     *
     * @param id mã công nhân cần tìm
     * @return đối tượng Worker nếu tìm thấy, ngược lại null
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
     * Chức năng: Kiểm tra sự tồn tại của mã công nhân trong hệ thống.
     * Luồng xử lý: Duyệt danh sách và kiểm tra trùng mã ID (không phân biệt hoa thường).
     *
     * @param id mã công nhân cần kiểm tra
     * @return true nếu đã tồn tại, ngược lại false
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
     * Chức năng: Thêm một công nhân mới vào danh sách.
     * Luồng xử lý: Kiểm tra trùng ID qua isExist(), nếu trùng ném Exception, ngược lại thêm vào danh sách.
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
     * Chức năng: Điều chỉnh tăng hoặc giảm lương của công nhân.
     * Luồng xử lý: Kiểm tra mã tồn tại và số tiền > 0, tăng lương nếu status UP hoặc giảm lương (kiểm tra không âm) nếu DOWN.
     *
     * @param status trạng thái tăng (UP) hoặc giảm (DOWN)
     * @param code mã công nhân
     * @param amount số tiền thay đổi
     * @return công nhân sau khi cập nhật lương
     * @throws Exception nếu không tìm thấy mã, số tiền <= 0 hoặc lương sau giảm < 0
     */
    public Worker changeSalary(SalaryStatus status, String code, double amount) throws Exception {
        if (!isExist(code)) {
            throw new Exception("Can not found code!");
        }
        if (amount <= 0) {
            throw new Exception("Amount of money must be > 0");
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
