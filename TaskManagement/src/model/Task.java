package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Chức năng: Lưu trữ thông tin một tác vụ (Task) trong hệ thống.
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
     * Khởi tạo đối tượng Task với đầy đủ thông tin.
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
    
    /**
     * Khởi tạo đối tượng Task mặc định.
     */
    public Task() {
    }

    /**
     * Lấy ID của task.
     *
     * @return ID của task
     */
    public int getId() {
        return id;
    }

    /**
     * Thiết lập ID của task.
     *
     * @param id ID cần gán
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Lấy mã loại task.
     *
     * @return mã loại task
     */
    public int getTaskTypeID() {
        return taskTypeID;
    }

    /**
     * Thiết lập mã loại task.
     *
     * @param taskTypeID mã loại task
     */
    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    /**
     * Lấy tên yêu cầu của task.
     *
     * @return tên yêu cầu
     */
    public String getRequirementName() {
        return requirementName;
    }

    /**
     * Thiết lập tên yêu cầu của task.
     *
     * @param requirementName tên yêu cầu
     */
    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    /**
     * Lấy ngày thực hiện task.
     *
     * @return ngày thực hiện
     */
    public Date getDate() {
        return date;
    }

    /**
     * Thiết lập ngày thực hiện task.
     *
     * @param date ngày thực hiện
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Lấy thời gian bắt đầu dự kiến.
     *
     * @return thời gian bắt đầu
     */
    public double getPlanFrom() {
        return planFrom;
    }

    /**
     * Thiết lập thời gian bắt đầu dự kiến.
     *
     * @param planFrom thời gian bắt đầu
     */
    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    /**
     * Lấy thời gian kết thúc dự kiến.
     *
     * @return thời gian kết thúc
     */
    public double getPlanTo() {
        return planTo;
    }

    /**
     * Thiết lập thời gian kết thúc dự kiến.
     *
     * @param planTo thời gian kết thúc
     */
    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    /**
     * Lấy người được phân công (Assignee).
     *
     * @return người được phân công
     */
    public String getAssign() {
        return assign;
    }

    /**
     * Thiết lập người được phân công thực hiện task.
     *
     * @param assign người được phân công
     */
    public void setAssign(String assign) {
        this.assign = assign;
    }

    /**
     * Lấy người đánh giá (Reviewer).
     *
     * @return người đánh giá
     */
    public String getReviewer() {
        return reviewer;
    }

    /**
     * Thiết lập người đánh giá task.
     *
     * @param reviewer người đánh giá
     */
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    /**
     * Định dạng thông tin Task thành một dòng hiển thị trong bảng.
     * 
     * @return chuỗi định dạng đầy đủ thông tin Task
     */
    @Override
    public String toString() { 
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("%-5d%-15s%-15s%-15s%-15.1f%-15s%-15s\n", getId(), getRequirementName(),
                TaskType.getTaskTypeByID(taskTypeID).getName(),
                dateFormat.format(date), getPlanTo() - getPlanFrom(), getAssign(), getReviewer());
    }
}
