package controller;

import model.ManagerTask;
import view.TaskInputer;
import model.Task;
import view.Validator;

/**
 * Chức năng: Lớp Controller điều phối luồng thực thi của chương trình quản lý tác vụ.
 * Luồng tương tác: Nhận lệnh từ Main, gọi TaskInputer để lấy dữ liệu nhập, chuyển dữ liệu cho ManagerTask xử lý logic nghiệp vụ và hiển thị kết quả ra màn hình.
 */
public class Controller {

    private ManagerTask managerTask;
    private TaskInputer inputer;

    /**
     * Chức năng (Làm gì): Khởi tạo đối tượng Controller.
     * Luồng xử lý (Làm như thế nào): Cấp phát bộ nhớ cho managerTask khi Controller được tạo để sẵn sàng quản lý danh sách task.
     */
    public Controller() {
        managerTask = new ManagerTask();
    }

    /**
     * Chức năng (Làm gì): Lấy đối tượng quản lý tác vụ (ManagerTask).
     * Luồng xử lý (Làm như thế nào): Trả về đối tượng managerTask hiện tại.
     *
     * @return đối tượng ManagerTask
     */
    public ManagerTask getManagerTask() {
        return managerTask;
    }

    /**
     * Chức năng (Làm gì): Thiết lập đối tượng quản lý tác vụ (ManagerTask).
     * Luồng xử lý (Làm như thế nào): Gán đối tượng managerTask mới cho thuộc tính của Controller.
     *
     * @param managerTask đối tượng ManagerTask cần gán
     */
    public void setManagerTask(ManagerTask managerTask) {
        this.managerTask = managerTask;
    }

    /**
     * Chức năng (Làm gì): Xử lý thêm một tác vụ mới (Add Task).
     * Luồng xử lý (Làm như thế nào):
     * 1. Khởi tạo đối tượng TaskInputer.
     * 2. Gọi phương thức input() để người dùng nhập thông tin tác vụ hợp lệ, lưu vào đối tượng Task tạm thời.
     * 3. Gọi ManagerTask kiểm tra trùng lặp và thêm vào danh sách.
     * 4. Trả về ID tự động tăng từ ManagerTask.
     *
     * @return ID của Task được thêm thành công.
     * @throws Exception nếu xảy ra lỗi (ví dụ trùng lặp dữ liệu tác vụ).
     */
    public int add() throws Exception {
        inputer = new TaskInputer();
        Task task = inputer.input();
        return managerTask.add(task.getTaskTypeID(), task.getRequirementName(),
                task.getDate(), task.getPlanFrom(), task.getPlanTo(),
                task.getAssign(), task.getReviewer());
    }

    /**
     * Chức năng (Làm gì): Xử lý xóa một tác vụ (Delete Task).
     * Luồng xử lý (Làm như thế nào):
     * 1. Sử dụng Validator để yêu cầu người dùng nhập số nguyên (ID của Task cần xóa).
     * 2. Gọi ManagerTask với ID để tìm và xóa Task.
     * 3. Trả về đối tượng Task đã bị xóa để hiển thị ra console.
     *
     * @return Đối tượng Task đã bị xóa khỏi danh sách.
     * @throws Exception nếu không tìm thấy ID hoặc danh sách rỗng.
     */
    public Task delete() throws Exception {
        int ID = Validator.getInt("Task ID: ", "Error range!", "Invalid!",
                Integer.MIN_VALUE, Integer.MAX_VALUE);
        return managerTask.deleteTaskByID(ID);
    }

    /**
     * Chức năng (Làm gì): Xử lý hiển thị danh sách tác vụ (Show Task).
     * Luồng xử lý (Làm như thế nào):
     * 1. Gọi toString() của ManagerTask để lấy chuỗi định dạng danh sách.
     * 2. Ném ngoại lệ nếu chuỗi trả về là null (danh sách rỗng).
     * 3. In chuỗi danh sách tác vụ ra màn hình console.
     *
     * @throws Exception nếu danh sách hiện tại rỗng.
     */
    public void show() throws Exception {
        String str = managerTask.toString();
        if (str == null) {
            throw new Exception("This list is empty!");
        }
        System.out.println(str);
    }

    /**
     * Chức năng (Làm gì): Chạy luồng chương trình chính với vòng lặp menu.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị menu lặp lại cho đến khi người dùng chọn Thoát (4).
     * 2. Điều phối và thực thi các chức năng 1-3 tương ứng (Add Task, Delete task, Display Task).
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
