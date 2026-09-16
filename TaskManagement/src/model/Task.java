package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Chức năng: Lớp Task đại diện cho một đối tượng công việc trong hệ thống.
 * Luồng tương tác: Chứa các thông tin cơ bản của một công việc và các phương thức truy xuất/cập nhật dữ liệu (Getter/Setter).
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
     * Chức năng: Khởi tạo một đối tượng Task với đầy đủ thông tin truyền vào.
     * Luồng xử lý:
     * 1. Gán các giá trị tham số đầu vào cho các thuộc tính tương ứng của lớp.
     * 
     * @param id ID của công việc
     * @param taskTypeID ID loại công việc
     * @param requirementName Tên yêu cầu
     * @param date Ngày thực hiện
     * @param planFrom Thời gian bắt đầu
     * @param planTo Thời gian kết thúc
     * @param assign Người được giao
     * @param reviewer Người kiểm duyệt
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
     * Chức năng: Ghi đè phương thức toString để trả về chuỗi thông tin của Task đã được định dạng.
     * Luồng xử lý:
     * 1. Khởi tạo SimpleDateFormat để định dạng đối tượng Date về dạng chuỗi "dd-MM-yyyy".
     * 2. Sử dụng String.format để căn lề và định dạng các thông tin của Task (ID, Name, Task Type, Date, Time, Assignee, Reviewer).
     * 3. Trả về chuỗi kết quả.
     * 
     * @return Chuỗi định dạng chứa đầy đủ thông tin của Task, dùng để in ra màn hình dạng bảng.
     */
    @Override
    public String toString() { 
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return String.format("%-5d%-15s%-15s%-15s%-15.1f%-15s%-15s\n", getId(),getRequirementName(),
                TaskType.getTaskTypeByID(taskTypeID).getName(),
                dateFormat.format(date),getPlanTo()-getPlanFrom(),getAssign(),getReviewer());
    }
}
