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
     * Chức năng (Làm gì): Lấy bản sao của danh sách công nhân hiện tại.
     * Luồng xử lý (Làm như thế nào): Tạo mới và trả về một đối tượng ArrayList chứa toàn bộ công nhân trong list.
     *
     * @return danh sách công nhân
     */
    public List<Worker> getList() {
        return new ArrayList<>(list);
    }

    /**
     * Chức năng (Làm gì): Khởi tạo đối tượng ManagerWorker.
     * Luồng xử lý (Làm như thế nào): Khởi tạo thuộc tính list dưới dạng một ArrayList rỗng.
     */
    public ManagerWorker() {
        this.list = new ArrayList<>();
    }

    /**
     * Chức năng (Làm gì): Tìm kiếm đối tượng công nhân theo mã ID.
     * Luồng xử lý (Làm như thế nào):
     * 1. Duyệt qua từng công nhân trong danh sách.
     * 2. So sánh mã ID của công nhân với mã cần tìm (không phân biệt chữ hoa, chữ thường).
     * 3. Trả về Worker nếu tìm thấy, ngược lại trả về null khi duyệt hết danh sách.
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
     * Chức năng (Làm gì): Kiểm tra sự tồn tại của mã công nhân trong hệ thống.
     * Luồng xử lý (Làm như thế nào):
     * 1. Duyệt qua toàn bộ danh sách công nhân.
     * 2. So sánh mã ID không phân biệt chữ hoa thường.
     * 3. Trả về true nếu mã đã tồn tại, ngược lại trả về false.
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
     * Chức năng (Làm gì): Thêm một công nhân mới vào danh sách.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra mã công nhân đã tồn tại trong danh sách hay chưa bằng isExist.
     * 2. Nếu chưa tồn tại, thêm vào danh sách và trả về true. Nếu đã có, ném ra ngoại lệ.
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
     * Chức năng (Làm gì): Thay đổi mức lương của một công nhân (tăng hoặc giảm).
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra mã công nhân có tồn tại trong hệ thống hay không bằng isExist.
     * 2. Kiểm tra số tiền thay đổi phải lớn hơn 0, nếu không ném ngoại lệ.
     * 3. Lấy đối tượng công nhân qua getWorker(code).
     * 4. Dựa vào trạng thái:
     *    - UP: Cộng thêm số tiền vào lương hiện tại.
     *    - DOWN: Kiểm tra lương sau khi giảm không được âm, sau đó trừ bớt số tiền.
     * 5. Trả về đối tượng công nhân sau khi cập nhật lương.
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
