package main;

import controller.Controller;
import model.Worker;
import view.Validator;

/**
 * Chức năng: Lớp chạy chương trình Worker Management chứa hàm main.
 * Luồng tương tác: Hiển thị menu vòng lặp cho người dùng tương tác, gọi tới lớp Controller để xử lý từng chức năng, xử lý in các ngoại lệ.
 */
public class Main {

    /**
     * Luồng xử lý 1: Khởi tạo Controller.
     * Luồng xử lý 2: Vòng lặp do-while in ra menu và nhận sự lựa chọn thông qua Validator.
     * Luồng xử lý 3: Thực hiện Switch-Case theo chức năng đã chọn, in ra kết quả hoặc Exception tương ứng.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller control = new Controller();
        do {
            int choice = Validator.getInt("======== Worker Management =========\n"
                    + "1.	Add Worker\n"
                    + "2.	Up salary\n"
                    + "3.	Down salary\n"
                    + "4.	Display Information salary\n"
                    + "5.	Exit\nEnter your choice: ", "Just be 1-> 5", "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                    System.out.println("--------- Add Worker ----------");
                    Worker worker = control.addWorker();
                    System.out.println("Add success: " + worker);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 2:
                    try {
                    System.out.println("------- Up/Down Salary --------");
                    Worker workerUp = control.upSalary();
                    System.out.println("Up salary success:");
                    System.out.println(workerUp);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 3:
                    try {
                    System.out.println("------- Up/Down Salary --------");
                    Worker workerDown = control.downSalary();
                    System.out.println("Down salary success:");
                    System.out.println(workerDown);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                break;
                case 4:
                    control.showHistorySalary();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

}
