package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Chức năng: Quản lý danh sách tác vụ (Task), thực hiện thêm, xóa và định dạng hiển thị.
 */
public class ManagerTask {
    private ArrayList<Task> list;
    private int lastID;

    /**
     * Khởi tạo đối tượng ManagerTask với danh sách rỗng và lastID = 0.
     */
    public ManagerTask() {
        list = new ArrayList<>();
        lastID = 0;
    }

    /**
     * Lấy danh sách tác vụ hiện tại.
     *
     * @return danh sách tác vụ
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Thiết lập danh sách tác vụ.
     *
     * @param list danh sách tác vụ mới
     */
    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Lấy giá trị ID cuối cùng.
     *
     * @return giá trị lastID
     */
    public int getLastID() {
        return lastID;
    }

    /**
     * Thiết lập giá trị ID cuối cùng.
     *
     * @param lastID giá trị ID cần gán
     */
    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    /**
     * Chức năng: Kiểm tra xem Task mới có bị trùng lặp với Task đã tồn tại hay không.
     * Luồng xử lý: Duyệt danh sách, so sánh toàn bộ các trường thông tin, trả về true nếu trùng khớp hoàn toàn, ngược lại false.
     *
     * @param taskTypeID mã loại task
     * @param requirementName tên yêu cầu
     * @param date ngày thực hiện
     * @param planFrom thời gian bắt đầu
     * @param planTo thời gian kết thúc
     * @param assign người được giao
     * @param reviewer người đánh giá
     * @return true nếu trùng lặp, ngược lại false
     */
    private boolean isDuplicate(int taskTypeID, String requirementName, Date date,
            double planFrom, double planTo, String assign,
            String reviewer) {
        for (Task task : list) {
            if (task.getTaskTypeID() == taskTypeID
                    && task.getRequirementName().equalsIgnoreCase(requirementName)
                    && task.getDate().compareTo(date) == 0
                    && task.getPlanFrom() == planFrom
                    && task.getPlanTo() == planTo
                    && task.getAssign().equalsIgnoreCase(assign)
                    && task.getReviewer().equalsIgnoreCase(reviewer)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Chức năng: Thêm một Task mới vào danh sách.
     * Luồng xử lý: Kiểm tra trùng lặp bằng isDuplicate(), tự tăng lastID, khởi tạo Task và thêm vào list.
     *
     * @param taskTypeID mã loại task
     * @param requirementName tên yêu cầu
     * @param date ngày thực hiện
     * @param planFrom thời gian bắt đầu
     * @param planTo thời gian kết thúc
     * @param assign người được giao
     * @param reviewer người đánh giá
     * @return ID của Task mới được thêm
     * @throws Exception nếu Task đã tồn tại
     */
    public int add(int taskTypeID, String requirementName, Date date,
            double planFrom, double planTo, String assign,
            String reviewer) throws Exception {
        if (isDuplicate(taskTypeID, requirementName, date, planFrom, planTo, assign, reviewer)) {
            throw new Exception("This task is existed!");
        }
        Task newTask = new Task(++lastID, taskTypeID, requirementName, date, planFrom, planTo, assign, reviewer);
        if (list.add(newTask)) {
            return newTask.getId();
        }
        throw new Exception("Can not add!");
    }

    /**
     * Chức năng: Tìm vị trí (chỉ số) của Task trong danh sách theo ID.
     * Luồng xử lý: Duyệt danh sách và so sánh ID, trả về vị trí tìm thấy hoặc -1 nếu không có.
     *
     * @param id ID cần tìm kiếm
     * @return chỉ số trong danh sách, hoặc -1 nếu không tìm thấy
     */
    private int getIndexByID(int id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Chức năng: Xóa một Task khỏi danh sách theo ID.
     * Luồng xử lý: Tìm vị trí qua getIndexByID(), nếu không thấy ném Exception, ngược lại xóa khỏi list và trả về đối tượng vừa xóa.
     *
     * @param id ID của Task cần xóa
     * @return đối tượng Task vừa bị xóa
     * @throws Exception nếu không tìm thấy ID
     */
    public Task deleteTaskByID(int id) throws Exception {
        int index = getIndexByID(id);
        if (index == -1) {
            throw new Exception("Task ID does not exist!");
        }
        return list.remove(index);
    }

    /**
     * Chức năng: Định dạng danh sách tác vụ thành chuỗi bảng hiển thị.
     * Luồng xử lý: Tạo dòng tiêu đề và nối chuỗi toString() của từng Task trong danh sách.
     *
     * @return chuỗi biểu diễn bảng tác vụ, hoặc null nếu danh sách rỗng
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID", "Name", "Task Type", "Date",
                "Time", "Assignee", "Reviewer"));
        for (Task task : list) {
            sb.append(task.toString());
        }
        return sb.toString();
    }
}
