package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Chức năng: Lớp lưu thông tin lịch sử thay đổi lương của công nhân.
 * Luồng tương tác: Quản lý bởi lớp ManagerSalaryHistory, lưu thông tin worker, mức lương cập nhật, trạng thái, và ngày.
 */
public class SalaryHistory implements Comparable<SalaryHistory>, Serializable {

    private Worker worker;
    private double salaryUpdate;
    private SalaryStatus status;
    private Date date;

    /**
     * Luồng xử lý 1: Khởi tạo một bản ghi lịch sử lương với các tham số đầu vào.
     * Luồng xử lý 2: Gọi các phương thức setter để thiết lập giá trị.
     *
     * @param worker công nhân được cập nhật
     * @param salaryUpdate mức lương sau khi cập nhật
     * @param status trạng thái UP hoặc DOWN
     * @param date ngày thực hiện
     * @throws Exception nếu dữ liệu không hợp lệ
     */
    public SalaryHistory(Worker worker, double salaryUpdate, SalaryStatus status, Date date) throws Exception {
        setWorker(worker);
        setSalaryUpdate(salaryUpdate);
        setStatus(status);
        setDate(date);
    }

    public Worker getWorker() {
        return worker;
    }

    /**
     * Luồng xử lý 1: Kiểm tra worker có null hay không.
     * Luồng xử lý 2: Thiết lập công nhân cho bản ghi nếu hợp lệ, ngược lại ném ngoại lệ.
     *
     * @param worker công nhân cần lưu
     * @throws Exception nếu worker bằng null
     */
    public void setWorker(Worker worker) throws Exception {
        if (worker != null) {
            this.worker = worker;
        } else {
            throw new Exception("Worker can not null!");
        }
    }

    public SalaryStatus getStatus() {
        return status;
    }

    /**
     * Luồng xử lý 1: Kiểm tra trạng thái có null hay không.
     * Luồng xử lý 2: Thiết lập trạng thái thay đổi lương nếu hợp lệ, ngược lại ném ngoại lệ.
     *
     * @param status trạng thái UP hoặc DOWN
     * @throws Exception nếu status bằng null
     */
    public void setStatus(SalaryStatus status) throws Exception {
        if (status != null) {
            this.status = status;
        } else {
            throw new Exception("Status can not null!");
        }
    }

    public Date getDate() {
        return date;
    }

    /**
     * Luồng xử lý 1: Kiểm tra ngày cập nhật có null hay không.
     * Luồng xử lý 2: Thiết lập ngày thay đổi lương nếu hợp lệ, ngược lại ném ngoại lệ.
     *
     * @param date ngày thực hiện
     * @throws Exception nếu date bằng null
     */
    public void setDate(Date date) throws Exception {
        if (date != null) {
            this.date = date;
        } else {
            throw new Exception("Date can not null!");
        }
    }

    public double getSalaryUpdate() {
        return salaryUpdate;
    }

    /**
     * Luồng xử lý 1: Kiểm tra mức lương mới lớn hơn hoặc bằng 0.
     * Luồng xử lý 2: Gán mức lương nếu hợp lệ, nếu không ném ngoại lệ.
     *
     * @param salaryUpdate mức lương mới
     * @throws Exception nếu lương nhỏ hơn 0
     */
    public void setSalaryUpdate(double salaryUpdate) throws Exception {
        if (salaryUpdate >= 0) {
            this.salaryUpdate = salaryUpdate;
        } else {
            throw new Exception("salaryUpdate must be >=0");
        }
    }

    /**
     * Luồng xử lý 1: Trả về chuỗi đại diện cho đối tượng lịch sử lương.
     *
     * @return thông tin bản ghi
     */
    @Override
    public String toString() {
        return "SalaryHistory{" + worker.getId() + ", " + salaryUpdate + ", " + status + ", " + date + '}';
    }

    /**
     * Luồng xử lý 1: So sánh mã công nhân của hai bản ghi lịch sử.
     *
     * @param o bản ghi lịch sử cần so sánh
     * @return giá trị so sánh theo mã công nhân
     */
    @Override
    public int compareTo(SalaryHistory o) {
        return worker.getId().compareTo(o.worker.getId());
    }

}
