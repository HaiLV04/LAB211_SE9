package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Chức năng: Lưu trữ thông tin lịch sử thay đổi lương của công nhân (công nhân, mức lương mới, trạng thái UP/DOWN, ngày thực hiện).
 */
public class SalaryHistory implements Comparable<SalaryHistory>, Serializable {

    private Worker worker;
    private double salaryUpdate;
    private SalaryStatus status;
    private Date date;

    /**
     * Khởi tạo bản ghi lịch sử thay đổi lương.
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

    /**
     * Lấy đối tượng công nhân.
     *
     * @return công nhân
     */
    public Worker getWorker() {
        return worker;
    }

    /**
     * Thiết lập đối tượng công nhân cho bản ghi.
     *
     * @param worker đối tượng công nhân
     * @throws Exception nếu worker là null
     */
    public void setWorker(Worker worker) throws Exception {
        if (worker != null) {
            this.worker = worker;
        } else {
            throw new Exception("Worker can not null!");
        }
    }

    /**
     * Lấy trạng thái thay đổi lương (UP hoặc DOWN).
     *
     * @return trạng thái UP hoặc DOWN
     */
    public SalaryStatus getStatus() {
        return status;
    }

    /**
     * Thiết lập trạng thái thay đổi lương.
     *
     * @param status trạng thái UP hoặc DOWN
     * @throws Exception nếu status là null
     */
    public void setStatus(SalaryStatus status) throws Exception {
        if (status != null) {
            this.status = status;
        } else {
            throw new Exception("Status can not null!");
        }
    }

    /**
     * Lấy ngày thực hiện thay đổi lương.
     *
     * @return ngày thực hiện
     */
    public Date getDate() {
        return date;
    }

    /**
     * Thiết lập ngày thay đổi lương.
     *
     * @param date ngày thực hiện
     * @throws Exception nếu date là null
     */
    public void setDate(Date date) throws Exception {
        if (date != null) {
            this.date = date;
        } else {
            throw new Exception("Date can not null!");
        }
    }

    /**
     * Lấy mức lương sau khi cập nhật.
     *
     * @return mức lương mới
     */
    public double getSalaryUpdate() {
        return salaryUpdate;
    }

    /**
     * Thiết lập mức lương sau khi cập nhật.
     *
     * @param salaryUpdate mức lương mới (>= 0)
     * @throws Exception nếu lương < 0
     */
    public void setSalaryUpdate(double salaryUpdate) throws Exception {
        if (salaryUpdate >= 0) {
            this.salaryUpdate = salaryUpdate;
        } else {
            throw new Exception("salaryUpdate must be >=0");
        }
    }

    /**
     * Chuyển đổi bản ghi lịch sử lương thành chuỗi hiển thị.
     *
     * @return chuỗi biểu diễn bản ghi
     */
    @Override
    public String toString() {
        return "SalaryHistory{" + worker.getId() + ", " + salaryUpdate + ", " + status + ", " + date + '}';
    }

    /**
     * Chức năng: So sánh hai bản ghi lịch sử lương theo mã công nhân để sắp xếp tăng dần.
     * Luồng xử lý: Sử dụng compareTo của String trên mã ID công nhân.
     *
     * @param o bản ghi cần so sánh
     * @return giá trị so sánh
     */
    @Override
    public int compareTo(SalaryHistory o) {
        return worker.getId().compareTo(o.worker.getId());
    }
}
