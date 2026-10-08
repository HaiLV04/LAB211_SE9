package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Chức năng: Đại diện cho thực thể tác vụ (Task) trong hệ thống.
 * Luồng tương tác: Lưu trữ các thông tin cơ bản của một task cùng các phương thức getter và setter.
 */
public class Task {
    private int id;
    private int taskTypeID;
    private String requirementName;
    private Date date;
    private double planFrom;
    private double planTo;
    private String assign;
    private String reviewer;

    /**
     * Chức năng: Khởi tạo đối tượng Task với đầy đủ các tham số.
     * Luồng xử lý:
     * 1. Gán giá trị các tham số truyền vào cho các thuộc tính tương ứng của lớp.
     * 
     * @param id mã của task
     * @param taskTypeID mã loại task
     * @param requirementName tên yêu cầu
     * @param date ngày thực hiện
     * @param planFrom thời gian bắt đầu
     * @param planTo thời gian kết thúc
     * @param assign người được giao
     * @param reviewer người đánh giá
     */
    public Task(int id, int taskTypeID, String requirementName, Date date, 
            double planFrom, double planTo, String assign,
            String reviewer) {
        this.id = id;
        this.taskTypeID = taskTypeID;
        this.requirementName = requirementName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.assign = assign;
        this.reviewer = reviewer;
    }
    
    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    public String getRequirementName() {
        return requirementName;
    }

    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPlanFrom() {
        return planFrom;
    }

    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    public double getPlanTo() {
        return planTo;
    }

    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    /**
     * Chức năng: Ghi đè phương thức toString để trả về chuỗi định dạng của Task.
     * Luồng xử lý:
     * 1. Khởi tạo SimpleDateFormat để định dạng ngày theo mẫu "dd-MM-yyyy".
     * 2. Sử dụng String.format để căn chỉnh các cột thông tin của Task (ID, tên, loại task, ngày, thời lượng, người làm, người duyệt).
     * 3. Trả về chuỗi kết quả đã định dạng.
     * 
     * @return chuỗi định dạng chứa đầy đủ thông tin Task để hiển thị bảng.
     */
    @Override
    public String toString() { 
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("%-5d%-15s%-15s%-15s%-15.1f%-15s%-15s\n", getId(),getRequirementName(),
                TaskType.getTaskTypeByID(taskTypeID).getName(),
                dateFormat.format(date),getPlanTo()-getPlanFrom(),getAssign(),getReviewer());
    }
}
