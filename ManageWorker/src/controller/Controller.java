package controller;

import java.util.Date;
import model.ManagerSalaryHistory;
import model.ManagerWorker;
import model.SalaryHistory;
import model.SalaryStatus;
import model.Worker;
import view.Validator;

/**
 * Chức năng: Điều khiển luồng chương trình quản lý công nhân và lịch sử lương.
 */
public class Controller {

    private ManagerSalaryHistory salaryHistory;
    private ManagerWorker workers;

    /**
     * Khởi tạo Controller với các đối tượng quản lý công nhân và lịch sử lương.
     */
    public Controller() {
        this.salaryHistory = new ManagerSalaryHistory();
        this.workers = new ManagerWorker();
    }

    /**
     * Chức năng: Thêm một công nhân mới vào hệ thống.
     * Luồng xử lý: Nhập và kiểm tra trùng ID, nhập các thông tin (tên, tuổi, lương, nơi làm việc) và thêm vào danh sách quản lý.
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
     * Chức năng: Tăng lương cho công nhân và ghi lại lịch sử.
     * Luồng xử lý: Nhập mã công nhân và số tiền muốn tăng, gọi model cập nhật và thêm bản ghi trạng thái UP vào lịch sử.
     *
     * @return công nhân sau khi tăng lương
     * @throws Exception nếu danh sách rỗng, mã không tồn tại hoặc dữ liệu không hợp lệ
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
     * Chức năng: Giảm lương cho công nhân và ghi lại lịch sử.
     * Luồng xử lý: Nhập mã công nhân và số tiền giảm (kiểm tra không vượt quá lương hiện tại), cập nhật lương và thêm bản ghi DOWN vào lịch sử.
     *
     * @return công nhân sau khi giảm lương
     * @throws Exception nếu danh sách rỗng, mã không tồn tại hoặc giảm lương thất bại
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
     * Chức năng: Hiển thị bảng lịch sử thay đổi lương của toàn bộ công nhân.
     * Luồng xử lý: Lấy chuỗi định dạng từ ManagerSalaryHistory và in ra màn hình.
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
     * Chức năng: Điều khiển luồng thực thi chính của chương trình qua menu.
     * Luồng xử lý: Lặp hiển thị menu và gọi xử lý các chức năng: thêm công nhân, tăng lương, giảm lương, xem lịch sử.
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
