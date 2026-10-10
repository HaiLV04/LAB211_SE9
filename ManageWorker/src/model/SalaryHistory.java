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
     * Chức năng (Làm gì): Khởi tạo một bản ghi lịch sử lương với các tham số đầu vào.
     * Luồng xử lý (Làm như thế nào):
     * 1. Nhận các tham số worker, salaryUpdate, status, date.
     * 2. Gọi các phương thức setter tương ứng để xác thực và thiết lập giá trị.
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
     * Chức năng (Làm gì): Lấy thông tin đối tượng công nhân của bản ghi.
     * Luồng xử lý (Làm như thế nào): Trả về đối tượng worker trong bản ghi lịch sử.
     *
     * @return đối tượng công nhân
     */
    public Worker getWorker() {
        return worker;
    }

    /**
     * Chức năng (Làm gì): Thiết lập đối tượng công nhân cho bản ghi lịch sử.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra worker có null hay không.
     * 2. Thiết lập công nhân cho bản ghi nếu hợp lệ, ngược lại ném ngoại lệ.
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

    /**
     * Chức năng (Làm gì): Lấy trạng thái thay đổi lương (UP hoặc DOWN).
     * Luồng xử lý (Làm như thế nào): Trả về giá trị enum status của bản ghi.
     *
     * @return trạng thái UP hoặc DOWN
     */
    public SalaryStatus getStatus() {
        return status;
    }

    /**
     * Chức năng (Làm gì): Thiết lập trạng thái thay đổi lương cho bản ghi.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra trạng thái có null hay không.
     * 2. Thiết lập trạng thái nếu hợp lệ, ngược lại ném ngoại lệ.
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

    /**
     * Chức năng (Làm gì): Lấy ngày thực hiện thay đổi lương.
     * Luồng xử lý (Làm như thế nào): Trả về đối tượng Date của bản ghi.
     *
     * @return ngày thực hiện thay đổi lương
     */
    public Date getDate() {
        return date;
    }

    /**
     * Chức năng (Làm gì): Thiết lập ngày thay đổi lương cho bản ghi.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra ngày cập nhật có null hay không.
     * 2. Thiết lập ngày thay đổi lương nếu hợp lệ, ngược lại ném ngoại lệ.
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

    /**
     * Chức năng (Làm gì): Lấy mức lương sau khi cập nhật.
     * Luồng xử lý (Làm như thế nào): Trả về giá trị salaryUpdate của bản ghi.
     *
     * @return mức lương mới
     */
    public double getSalaryUpdate() {
        return salaryUpdate;
    }

    /**
     * Chức năng (Làm gì): Thiết lập mức lương sau khi cập nhật cho bản ghi.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra mức lương mới lớn hơn hoặc bằng 0.
     * 2. Gán mức lương nếu hợp lệ, nếu nhỏ hơn 0 ném ngoại lệ.
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
     * Chức năng (Làm gì): Trả về chuỗi biểu diễn đối tượng lịch sử lương.
     * Luồng xử lý (Làm như thế nào): Ghép mã công nhân, mức lương cập nhật, trạng thái và ngày thành một chuỗi đại diện.
     *
     * @return thông tin bản ghi
     */
    @Override
    public String toString() {
        return "SalaryHistory{" + worker.getId() + ", " + salaryUpdate + ", " + status + ", " + date + '}';
    }

    /**
     * Chức năng (Làm gì): So sánh hai bản ghi lịch sử lương theo mã công nhân để phục vụ sắp xếp.
     * Luồng xử lý (Làm như thế nào): Gọi phương thức compareTo trên mã ID của hai công nhân.
     *
     * @param o bản ghi lịch sử cần so sánh
     * @return giá trị so sánh theo mã công nhân
     */
    @Override
    public int compareTo(SalaryHistory o) {
        return worker.getId().compareTo(o.worker.getId());
    }

}
