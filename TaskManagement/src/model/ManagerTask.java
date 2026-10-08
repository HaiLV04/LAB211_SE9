package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Chức năng: Lớp ManagerTask đóng vai trò Business Object (BO), chịu trách nhiệm quản lý danh sách Task và thực thi các logic nghiệp vụ.
 * Luồng tương tác: Cung cấp các phương thức thêm, xóa, kiểm tra trùng lặp và định dạng dữ liệu hiển thị; tương tác với Controller để trả về kết quả hoặc ném ngoại lệ.
 */
public class ManagerTask {
    private ArrayList<Task> list;
    private int lastID;

    /**
     * Chức năng: Khởi tạo đối tượng ManagerTask.
     * Luồng xử lý:
     * 1. Khởi tạo danh sách ArrayList rỗng để lưu trữ Task.
     * 2. Thiết lập ID ban đầu (lastID) bằng 0.
     */
    public ManagerTask() {
        list = new ArrayList<>();
        lastID = 0;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    public int getLastID() {
        return lastID;
    }

    public void setLastID(int lastID) {
        this.lastID = lastID;
    }

    /**
     * Chức năng: Kiểm tra xem Task có bị trùng lặp với Task đã có trong danh sách hay không.
     * Luồng xử lý:
     * 1. Duyệt qua toàn bộ danh sách Task hiện tại.
     * 2. So sánh từng thuộc tính: loại task, tên yêu cầu, ngày thực hiện, thời gian bắt đầu, thời gian kết thúc, người được giao và người đánh giá.
     * 3. Nếu tìm thấy Task trùng khớp toàn bộ, trả về true; ngược lại trả về false.
     *
     * @param taskTypeID mã loại task
     * @param requirementName tên yêu cầu
     * @param date ngày thực hiện
     * @param planFrom thời gian bắt đầu
     * @param planTo thời gian kết thúc
     * @param assign người được giao
     * @param reviewer người đánh giá
     * @return true nếu tìm thấy Task trùng lặp, false nếu không trùng.
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
     * Luồng xử lý:
     * 1. Gọi isDuplicate() để kiểm tra trùng lặp. Nếu trùng, ném ngoại lệ.
     * 2. Tự động tăng lastID để tạo ID mới.
     * 3. Khởi tạo đối tượng Task mới với ID vừa sinh và các tham số truyền vào.
     * 4. Thêm Task vào danh sách và trả về ID vừa cấp.
     *
     * @param taskTypeID mã loại task
     * @param requirementName tên yêu cầu
     * @param date ngày thực hiện
     * @param planFrom thời gian bắt đầu
     * @param planTo thời gian kết thúc
     * @param assign người được giao
     * @param reviewer người đánh giá
     * @return ID của Task mới được thêm.
     * @throws Exception nếu Task đã tồn tại hoặc không thể thêm.
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
     * Chức năng: Tìm chỉ số của Task trong danh sách dựa theo ID.
     * Luồng xử lý:
     * 1. Duyệt qua danh sách Task.
     * 2. So sánh ID của từng Task với ID cần tìm.
     * 3. Trả về chỉ số nếu tìm thấy, ngược lại trả về -1.
     *
     * @param id ID cần tìm kiếm.
     * @return chỉ số của phần tử trong danh sách, hoặc -1 nếu không tìm thấy.
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
     * Chức năng: Xóa một Task dựa theo ID.
     * Luồng xử lý:
     * 1. Gọi getIndexByID() để tìm chỉ số của Task cần xóa.
     * 2. Nếu chỉ số là -1 (không tìm thấy), ném ngoại lệ.
     * 3. Nếu tìm thấy, xóa Task khỏi danh sách và trả về đối tượng vừa xóa.
     *
     * @param id ID của Task cần xóa.
     * @return đối tượng Task vừa bị xóa.
     * @throws Exception nếu không tìm thấy ID.
     */
    public Task deleteTaskByID(int id) throws Exception {
        int index = getIndexByID(id);
        if (index == -1) {
            throw new Exception("Task ID does not exist!");
        }
        return list.remove(index);
    }

    /**
     * Chức năng: Trả về chuỗi hiển thị toàn bộ danh sách Task có định dạng bảng.
     * Luồng xử lý:
     * 1. Kiểm tra danh sách rỗng; trả về null nếu rỗng.
     * 2. Tạo dòng tiêu đề cho bảng hiển thị.
     * 3. Duyệt qua danh sách, gọi toString() của từng Task để ghép chuỗi.
     * 4. Trả về chuỗi kết quả.
     *
     * @return chuỗi định dạng danh sách, hoặc null nếu danh sách rỗng.
     */
    @Override
    public String toString() {
        if (list.isEmpty()) {
            return null;
        }
        String str = String.format("%-5s%-15s%-15s%-15s%-15s%-15s%-15s\n", "ID", "Name", "Task Type", "Date",
                "Time", "Assignee", "Reviewer");
        for (Task task : list) {
            str += task.toString();
        }
        return str;
    }
}
