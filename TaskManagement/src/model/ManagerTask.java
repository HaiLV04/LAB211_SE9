package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Chức năng: Lớp ManagerTask đóng vai trò Business Object (BO), chịu trách nhiệm quản lý danh sách các Task, thực hiện các logic nghiệp vụ.
 * Luồng tương tác: Cung cấp các phương thức để thêm, xóa, kiểm tra trùng lặp và định dạng dữ liệu hiển thị, tương tác với Controller để trả về kết quả hoặc ném ngoại lệ.
 */
public class ManagerTask {
    private ArrayList<Task> list;
    private int lastID;

    /**
     * Chức năng: Khởi tạo đối tượng ManagerTask.
     * Luồng xử lý:
     * 1. Khởi tạo danh sách ArrayList rỗng để lưu trữ Task.
     * 2. Đặt ID ban đầu (lastID) là 0.
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
     * Chức năng: Kiểm tra xem một Task có bị trùng lặp với bất kỳ Task nào đã có trong danh sách không.
     * Luồng xử lý:
     * 1. Duyệt qua toàn bộ danh sách Task hiện tại.
     * 2. So sánh từng thuộc tính: Loại công việc, tên yêu cầu, ngày, thời gian bắt đầu, kết thúc, người giao, người duyệt.
     * 3. Nếu tìm thấy Task giống hệt, trả về true. Ngược lại trả về false.
     *
     * @param taskTypeID Loại công việc
     * @param requirementName Tên yêu cầu
     * @param date Ngày thực hiện
     * @param planFrom Thời gian bắt đầu
     * @param planTo Thời gian kết thúc
     * @param assign Người được giao
     * @param reviewer Người kiểm duyệt
     * @return true nếu tìm thấy Task giống hệt, false nếu không.
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
     * 1. Gọi hàm isDuplicate() để kiểm tra trùng lặp. Nếu trùng, ném ngoại lệ.
     * 2. Tự động sinh ID mới bằng cách tăng biến lastID.
     * 3. Khởi tạo đối tượng Task mới với ID vừa sinh và các thông tin đầu vào.
     * 4. Thêm Task vào danh sách và trả về ID vừa thêm.
     *
     * @param taskTypeID Loại công việc
     * @param requirementName Tên yêu cầu
     * @param date Ngày thực hiện
     * @param planFrom Thời gian bắt đầu
     * @param planTo Thời gian kết thúc
     * @param assign Người được giao
     * @param reviewer Người kiểm duyệt
     * @return ID của Task vừa được thêm.
     * @throws Exception nếu Task đã tồn tại hoặc không thêm được vào danh sách.
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
     * Chức năng: Tìm kiếm chỉ số (index) của Task trong danh sách dựa trên ID.
     * Luồng xử lý:
     * 1. Duyệt qua danh sách Task bằng vòng lặp.
     * 2. So sánh ID của từng Task với ID cần tìm.
     * 3. Trả về vị trí (index) nếu tìm thấy, ngược lại trả về -1.
     *
     * @param id ID cần tìm.
     * @return index của phần tử trong list, hoặc -1 nếu không tìm thấy.
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
     * Chức năng: Xóa một Task dựa trên ID.
     * Luồng xử lý:
     * 1. Gọi hàm getIndexByID() để lấy vị trí của Task cần xóa.
     * 2. Nếu vị trí là -1 (không tìm thấy), ném ngoại lệ thông báo lỗi.
     * 3. Nếu tìm thấy, thực hiện xóa Task khỏi danh sách và trả về đối tượng vừa xóa.
     *
     * @param id ID của Task cần xóa.
     * @return Đối tượng Task đã bị xóa.
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
     * Chức năng: Trả về chuỗi định dạng (String format) của toàn bộ danh sách Task.
     * Luồng xử lý:
     * 1. Kiểm tra nếu danh sách trống thì trả về null.
     * 2. Tạo dòng tiêu đề cho bảng hiển thị.
     * 3. Duyệt danh sách, gọi phương thức toString() của từng Task và nối vào chuỗi kết quả.
     * 4. Trả về chuỗi danh sách.
     *
     * @return Chuỗi danh sách hoặc null nếu danh sách rỗng.
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
