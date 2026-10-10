package controller;

import model.ManagerSalaryHistory;
import model.ManagerWorker;
import model.SalaryHistory;
import model.SalaryStatus;
import model.Worker;
import java.util.Date;
import view.Validator;

/**
 * Chức năng: Lớp điều khiển chính của chương trình.
 * Luồng tương tác: Giao tiếp với Validator để lấy dữ liệu, gọi ManagerWorker và ManagerSalaryHistory để xử lý logic, hiển thị lại cho ui.Main.
 */
public class Controller {

    private ManagerSalaryHistory salaryHistory;
    private ManagerWorker workers;

    /**
     * Chức năng (Làm gì): Khởi tạo đối tượng Controller.
     * Luồng xử lý (Làm như thế nào): Khởi tạo các đối tượng quản lý (ManagerSalaryHistory, ManagerWorker).
     */
    public Controller() {
        this.salaryHistory = new ManagerSalaryHistory();
        this.workers = new ManagerWorker();
    }

    /**
     * Chức năng (Làm gì): Thêm một công nhân mới vào hệ thống.
     * Luồng xử lý (Làm như thế nào):
     * 1. Nhập và kiểm tra tính duy nhất của ID công nhân (chuyển chữ hoa).
     * 2. Nhập các thông tin còn lại như tên, tuổi, lương, nơi làm việc từ Validator.
     * 3. Gọi ManagerWorker để thêm vào hệ thống và trả về đối tượng nếu thành công.
     *
     * @return công nhân vừa được thêm
     * @throws Exception nếu thêm thất bại hoặc dữ liệu không hợp lệ
     */
    public Worker addWorker() throws Exception {
        String id;
        do {
            id = Validator.getString(
                    "Enter id: ",
                    "Invalid!",
                    "[Ww]\\d+").toUpperCase();

            if (!workers.isExist(id)) {
                break;
            }
            System.out.println("Worker with ID " + id + " already exists. Please input again");
        } while (true);
        
        String name = Validator.getString("Enter Name: ", "Invalid!", "[A-Za-z\\s]+");
        int age = Validator.getInt("Enter age: ", "age >= 18 and <=50 !", "Invalid!", 18, 50);
        double salary = Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
        String workLocation = Validator.getString("Enter work location: ", "Invalid!", "[A-Za-z0-9\\s]+");
        
        Worker worker = new Worker(id, name, age, salary, workLocation);
        
        if (workers.add(worker)) {
            return worker;
        }
        throw new Exception("Add fail");
    }

    /**
     * Chức năng (Làm gì): Tăng lương cho một công nhân và ghi lại lịch sử.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra xem danh sách công nhân có trống không.
     * 2. Yêu cầu nhập ID công nhân và số tiền muốn tăng.
     * 3. Thực hiện gọi ManagerWorker để tăng lương.
     * 4. Ghi lại lịch sử (UP) bằng ManagerSalaryHistory.
     *
     * @return công nhân sau khi tăng lương
     * @throws Exception nếu danh sách rỗng, dữ liệu không hợp lệ hoặc id không tồn tại
     */
    public Worker upSalary() throws Exception {
        if (workers.getList().isEmpty()) {
            throw new Exception("List is empty!");
        }
        String code = Validator.getString("Enter id: ", "Invalid!", "[Ww]\\d+");
        double amount = Validator.getDouble("Enter Salary: ", "salary must be > 0", "Invalid!", Double.MIN_VALUE, Double.MAX_VALUE);
        
        Worker worker = workers.changeSalary(SalaryStatus.UP, code, amount);
        
        if (salaryHistory.addSalaryHistory(new SalaryHistory(worker, worker.getSalary(), SalaryStatus.UP, new Date()))) {
            return worker;
        }
        throw new Exception("Can not up salary");
    }

    /**
     * Chức năng (Làm gì): Giảm lương của một công nhân và lưu lịch sử.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra xem danh sách công nhân có rỗng không.
     * 2. Yêu cầu nhập ID, tìm kiếm công nhân xem có tồn tại không.
     * 3. Yêu cầu nhập số tiền, gọi ManagerWorker để trừ lương.
     * 4. Bắt các ngoại lệ vi phạm giới hạn giảm và yêu cầu nhập lại nếu lượng giảm lớn hơn lương hiện tại.
     * 5. Ghi lại lịch sử giảm lương (DOWN) bằng ManagerSalaryHistory.
     *
     * @return công nhân sau khi giảm lương
     * @throws Exception nếu danh sách rỗng hoặc dữ liệu không hợp lệ
     */
    public Worker downSalary() throws Exception {
        if (workers.getList().isEmpty()) {
            throw new Exception("List is empty!");
        }
        String code = Validator.getString(
                "Enter id: ",
                "Invalid!",
                "[Ww]\\d+");

        Worker currentWorker = null;

        for (Worker w : workers.getList()) {
            if (w.getId().equalsIgnoreCase(code)) {
                currentWorker = w;
                break;
            }
        }

        if (currentWorker == null) {
            throw new Exception("Can not found code!");
        }

        Worker worker;

        while (true) {
            double amount = Validator.getDouble(
                    "Enter Salary: ",
                    "salary must be > 0",
                    "Invalid!",
                    Double.MIN_VALUE,
                    Double.MAX_VALUE);

            try {
                worker = workers.changeSalary(
                        SalaryStatus.DOWN,
                        code,
                        amount);
                break;
            } catch (Exception ex) {
                if (ex.getMessage().startsWith("Can not down")) {
                    System.out.println(
                            "Amount must be smaller than current salary ("
                            + currentWorker.getSalary() + ")!"
                    );
                } else {
                    throw ex;
                }
            }
        }
        
        if (salaryHistory.addSalaryHistory(new SalaryHistory(worker, worker.getSalary(), SalaryStatus.DOWN, new Date()))) {
            return worker;
        }
        throw new Exception("Can not down salary");
    }

    /**
     * Chức năng (Làm gì): Hiển thị bảng lịch sử thay đổi lương của toàn bộ công nhân.
     * Luồng xử lý (Làm như thế nào):
     * 1. Lấy kết quả chuỗi định dạng từ ManagerSalaryHistory.
     * 2. In chuỗi ra màn hình, nếu null thì in thông báo "History Salary is empty".
     */
    public void showHistorySalary() {
        String result = salaryHistory.toString();
        if (result == null) {
            System.out.println("History Salary is empty");
        } else {
            System.out.println(result);
        }
    }

    /**
     * Chức năng (Làm gì): Chạy luồng chương trình chính với vòng lặp menu.
     * Luồng xử lý (Làm như thế nào):
     * 1. Hiển thị menu cho đến khi người dùng chọn Exit (5).
     * 2. Điều hướng thực thi các chức năng 1-4 (Add Worker, Up salary, Down salary, Display Information salary).
     */
    public void run() {
        int choice;
        do {
            choice = Validator.getInt("======== Worker Management =========\n"
                    + "1.\tAdd Worker\n"
                    + "2.\tUp salary\n"
                    + "3.\tDown salary\n"
                    + "4.\tDisplay Information salary\n"
                    + "5.\tExit\nEnter your choice: ", "Just be 1-> 5", "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        System.out.println("--------- Add Worker ----------");
                        Worker worker = addWorker();
                        System.out.println("Add success: " + worker);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.println("------- Up/Down Salary --------");
                        Worker workerUp = upSalary();
                        System.out.println("Up salary success:");
                        System.out.println(workerUp);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.println("------- Up/Down Salary --------");
                        Worker workerDown = downSalary();
                        System.out.println("Down salary success:");
                        System.out.println(workerDown);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    showHistorySalary();
                    break;
                case 5:
                    return;
            }
        } while (choice != 5);
    }
}
