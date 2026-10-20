package controller;

import model.ManagerTask;
import model.Task;
import view.TaskInputer;
import view.Validator;

/**
 * Chức năng: Điều khiển luồng chương trình quản lý tác vụ (thêm, xóa, hiển thị task).
 */
public class Controller {

    private ManagerTask managerTask;
    private TaskInputer inputer;

    /**
     * Khởi tạo Controller với đối tượng quản lý tác vụ ManagerTask.
     */
    public Controller() {
        managerTask = new ManagerTask();
    }

    /**
     * Lấy đối tượng quản lý tác vụ.
     *
     * @return đối tượng ManagerTask
     */
    public ManagerTask getManagerTask() {
        return managerTask;
    }

    /**
     * Thiết lập đối tượng quản lý tác vụ.
     *
     * @param managerTask đối tượng ManagerTask mới
     */
    public void setManagerTask(ManagerTask managerTask) {
        this.managerTask = managerTask;
    }

    /**
     * Chức năng: Xử lý thêm một tác vụ mới vào hệ thống.
     * Luồng xử lý: Sử dụng TaskInputer để nhập thông tin, gọi ManagerTask kiểm tra trùng và thêm vào danh sách, trả về ID mới tạo.
     *
     * @return ID của Task được thêm thành công
     * @throws Exception nếu xảy ra lỗi hoặc trùng lặp dữ liệu
     */
    public int add() throws Exception {
        inputer = new TaskInputer();
        Task task = inputer.input();
        return managerTask.add(task.getTaskTypeID(), task.getRequirementName(),
                task.getDate(), task.getPlanFrom(), task.getPlanTo(),
                task.getAssign(), task.getReviewer());
    }

    /**
     * Chức năng: Xử lý xóa một tác vụ theo ID.
     * Luồng xử lý: Yêu cầu người dùng nhập ID qua Validator, gọi ManagerTask xóa và trả về Task đã bị xóa.
     *
     * @return đối tượng Task vừa bị xóa
     * @throws Exception nếu không tìm thấy ID hoặc danh sách rỗng
     */
    public Task delete() throws Exception {
        int ID = Validator.getInt("Task ID: ", "Error range!", "Invalid!",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        return managerTask.deleteTaskByID(ID);
    }

    /**
     * Chức năng: Xử lý hiển thị danh sách tác vụ ra màn hình console.
     * Luồng xử lý: Lấy chuỗi định dạng từ ManagerTask, nếu null thì ném Exception, ngược lại in ra console.
     *
     * @throws Exception nếu danh sách tác vụ rỗng
     */
    public void show() throws Exception {
        String str = managerTask.toString();
        if (str == null) {
            throw new Exception("This list is empty!");
        }
        System.out.println(str);
    }

    /**
     * Chức năng: Điều khiển luồng thực thi chính qua menu chương trình.
     * Luồng xử lý: Lặp hiển thị menu cho đến khi chọn Exit (4), điều hướng thực thi Thêm, Xóa hoặc Hiển thị task.
     */
    public void run() {
        while (true) {
            int choice = Validator.getInt("========= Task program =========\n"
                    + "1.\tAdd Task\n"
                    + "2.\tDelete task\n"
                    + "3.\tDisplay Task\n"
                    + "4.\texit\n"
                    + "Enter your choice: ", "Just 1-> 4", "Invalid!", 1, 4);
            switch (choice) {
                case 1:
                    System.out.println("------------Add Task------------");
                    try {
                        int IDTask = add();
                        System.out.println("Add success task id: " + IDTask);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("------------Del Task------------");
                    try {
                        Task task = delete();
                        System.out.println("Delete success:");
                        System.out.println(task);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("------------Show Task------------");
                    try {
                        show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    return;
            }
        }
    }
}
