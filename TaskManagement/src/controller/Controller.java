package controller;

import model.ManagerTask;
import view.TaskInputer;
import model.Task;
import view.Validator;

/**
 * Chức năng: Lớp Controller điều phối luồng hoạt động của chương trình quản lý công việc.
 * Luồng tương tác: Nhận lệnh từ lớp Main (UI), gọi TaskInputer để lấy dữ liệu, truyền xuống ManagerTask để xử lý nghiệp vụ và trả lại kết quả cho màn hình.
 */
public class Controller {

    private ManagerTask managerTask;
    private TaskInputer inputer;

    /**
     * Chức năng: Khởi tạo đối tượng Controller.
     * Luồng xử lý:
     * 1. Cấp phát bộ nhớ cho managerTask ngay khi Controller được tạo ra để sẵn sàng quản lý danh sách công việc.
     */
    public Controller() {
        managerTask = new ManagerTask();
    }

    public ManagerTask getManagerTask() {
        return managerTask;
    }

    public void setManagerTask(ManagerTask managerTask) {
        this.managerTask = managerTask;
    }

    /**
     * Chức năng: Xử lý thêm mới một công việc (Add Task).
     * Luồng xử lý:
     * 1. Khởi tạo đối tượng TaskInputer.
     * 2. Gọi hàm input() để người dùng nhập toàn bộ thông tin hợp lệ, lưu vào đối tượng Task tạm thời.
     * 3. Gọi ManagerTask truyền thông tin xuống để kiểm tra trùng lặp và thêm vào danh sách.
     * 4. Trả về ID tự sinh từ ManagerTask.
     *
     * @return ID của Task vừa được thêm thành công.
     * @throws Exception nếu có lỗi xảy ra (ví dụ: Task bị trùng lặp dữ liệu).
     */
    public int add() throws Exception {
        inputer = new TaskInputer();
        Task task = inputer.input();
        return managerTask.add(task.getTaskTypeID(), task.getRequirementName(),
                task.getDate(), task.getPlanFrom(), task.getPlanTo(),
                task.getAssign(), task.getReviewer());
    }

    /**
     * Chức năng: Xử lý xóa một công việc (Delete Task).
     * Luồng xử lý:
     * 1. Sử dụng Validator để yêu cầu người dùng nhập một số nguyên (ID của Task cần xóa).
     * 2. Gọi ManagerTask truyền ID vừa nhập để tiến hành tìm và xóa Task.
     * 3. Trả về đối tượng Task đã xóa để UI hiển thị.
     *
     * @return Đối tượng Task vừa bị xóa khỏi danh sách.
     * @throws Exception nếu không tìm thấy ID trong danh sách hoặc danh sách trống.
     */
    public Task delete() throws Exception {
        int ID = Validator.getInt("Task ID: ", "Error range!", "Invalid!",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        return managerTask.deleteTaskByID(ID);
    }

    /**
     * Chức năng: Xử lý hiển thị danh sách công việc (Show Task).
     * Luồng xử lý:
     * 1. Gọi hàm toString() của ManagerTask để lấy toàn bộ chuỗi dữ liệu danh sách đã được định dạng.
     * 2. Nếu chuỗi trả về là null (danh sách rỗng), ném ra ngoại lệ.
     * 3. In chuỗi danh sách công việc ra màn hình Console.
     *
     * @throws Exception nếu danh sách hiện tại đang trống rỗng.
     */
    public void show() throws Exception {
        String str = managerTask.toString();
        if (str == null) {
            throw new Exception("This list is empty!");
        }
        System.out.println(str);
    }
}
