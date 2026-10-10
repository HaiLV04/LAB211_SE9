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
     * Chức năng (Làm gì): Khởi tạo đối tượng Task với đầy đủ các tham số.
     * Luồng xử lý (Làm như thế nào): Gán giá trị các tham số truyền vào cho các thuộc tính tương ứng của lớp.
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
     * Chức năng (Làm gì): Khởi tạo đối tượng Task không tham số.
     * Luồng xử lý (Làm như thế nào): Tạo mới một đối tượng Task với các thuộc tính mặc định.
     */
    public Task() {
    }

    /**
     * Chức năng (Làm gì): Lấy ID của task.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính id của task.
     *
     * @return ID của task
     */
    public int getId() {
        return id;
    }

    /**
     * Chức năng (Làm gì): Thiết lập ID của task.
     * Luồng xử lý (Làm như thế nào): Gán giá trị mới cho thuộc tính id.
     *
     * @param id ID cần gán
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Chức năng (Làm gì): Lấy mã loại task (taskTypeID).
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính taskTypeID của task.
     *
     * @return mã loại task
     */
    public int getTaskTypeID() {
        return taskTypeID;
    }

    /**
     * Chức năng (Làm gì): Thiết lập mã loại task.
     * Luồng xử lý (Làm như thế nào): Gán giá trị mới cho thuộc tính taskTypeID.
     *
     * @param taskTypeID mã loại task cần gán
     */
    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    /**
     * Chức năng (Làm gì): Lấy tên yêu cầu của task.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính requirementName của task.
     *
     * @return tên yêu cầu của task
     */
    public String getRequirementName() {
        return requirementName;
    }

    /**
     * Chức năng (Làm gì): Thiết lập tên yêu cầu của task.
     * Luồng xử lý (Làm như thế nào): Gán giá trị mới cho thuộc tính requirementName.
     *
     * @param requirementName tên yêu cầu cần gán
     */
    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    /**
     * Chức năng (Làm gì): Lấy ngày thực hiện task.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính date của task.
     *
     * @return ngày thực hiện
     */
    public Date getDate() {
        return date;
    }

    /**
     * Chức năng (Làm gì): Thiết lập ngày thực hiện task.
     * Luồng xử lý (Làm như thế nào): Gán giá trị mới cho thuộc tính date.
     *
     * @param date ngày thực hiện cần gán
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Chức năng (Làm gì): Lấy thời gian bắt đầu dự kiến của task.
     * Luồng xử lý (Làm như thế nào): Trả về giá trị thuộc tính planFrom.
     *
     * @return thời gian bắt đầu
     */
    public double getPlanFrom() {
        return planFrom;
    }

    /**
     * Chức năng (Làm gì): Thiết lập thời gian bắt đầu dự kiến của task.
     * Luồng xử lý (Làm như thế nào): Gán giá trị mới cho thuộc tính planFrom.
     *
     * @param planFrom thời gian bắt đầu cần gán
     */
    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    /**
     * Chức năng (Làm gì): Lấy thời gian kết thúc dự kiến của task.
     * Luồng xử lý (Làm như thế nào): Trả về giá trị thuộc tính planTo.
     *
     * @return thời gian kết thúc
     */
    public double getPlanTo() {
        return planTo;
    }

    /**
     * Chức năng (Làm gì): Thiết lập thời gian kết thúc dự kiến của task.
     * Luồng xử lý (Làm như thế nào): Gán giá trị mới cho thuộc tính planTo.
     *
     * @param planTo thời gian kết thúc cần gán
     */
    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    /**
     * Chức năng (Làm gì): Lấy tên người được phân công (Assignee).
     * Luồng xử lý (Làm như thế nào): Trả về giá trị thuộc tính assign.
     *
     * @return tên người được phân công
     */
    public String getAssign() {
        return assign;
    }

    /**
     * Chức năng (Làm gì): Thiết lập người được phân công thực hiện task.
     * Luồng xử lý (Làm như thế nào): Gán giá trị mới cho thuộc tính assign.
     *
     * @param assign tên người được phân công
     */
    public void setAssign(String assign) {
        this.assign = assign;
    }

    /**
     * Chức năng (Làm gì): Lấy tên người duyệt/đánh giá (Reviewer).
     * Luồng xử lý (Làm như thế nào): Trả về giá trị thuộc tính reviewer.
     *
     * @return tên người đánh giá
     */
    public String getReviewer() {
        return reviewer;
    }

    /**
     * Chức năng (Làm gì): Thiết lập người đánh giá task.
     * Luồng xử lý (Làm như thế nào): Gán giá trị mới cho thuộc tính reviewer.
     *
     * @param reviewer tên người đánh giá
     */
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    /**
     * Chức năng (Làm gì): Ghi đè phương thức toString để trả về chuỗi định dạng của Task.
     * Luồng xử lý (Làm như thế nào):
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
