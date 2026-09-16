package view;

import model.Task;

/**
 * Chức năng: Lớp TaskInputer chịu trách nhiệm tương tác với người dùng để nhập thông tin cho một công việc.
 * Luồng tương tác: Tập hợp các dữ liệu hợp lệ thông qua lớp Validator, lưu vào đối tượng Task tạm thời trước khi trả về cho Controller.
 */
public class TaskInputer {

    private Task task;

    /**
     * Chức năng: Khởi tạo đối tượng TaskInputer.
     * Luồng xử lý:
     * 1. Cấp phát bộ nhớ cho một đối tượng Task rỗng để sẵn sàng hứng dữ liệu.
     */
    public TaskInputer() {
        task = new Task();
    }

    /**
     * Chức năng: Yêu cầu người dùng nhập từng trường thông tin của Task và kiểm tra tính hợp lệ.
     * Luồng xử lý:
     * 1. Nhập Requirement Name với điều kiện không chứa ký tự đặc biệt.
     * 2. Nhập Task Type với giới hạn từ 1 đến 4.
     * 3. Nhập Date với định dạng dd-MM-yyyy.
     * 4. Nhập Plan From từ 8.0 đến 17.0, kiểm tra phần thập phân bắt buộc là .0 hoặc .5.
     * 5. Nhập Plan To lớn hơn Plan From ít nhất 0.5 giờ và tối đa 17.5, kiểm tra phần thập phân là .0 hoặc .5.
     * 6. Nhập Assignee và Reviewer không chứa ký tự đặc biệt.
     * 7. Trả về đối tượng Task chứa đầy đủ thông tin hợp lệ (chưa có ID).
     *
     * @return Đối tượng Task chứa đầy đủ thông tin hợp lệ.
     */
    public Task input() {
        task.setRequirementName(Validator.getString("Requirement Name: ",
                "Invalid!", "[A-Za-z0-9\\s]+"));
        task.setTaskTypeID(Validator.getInt("1. Code\n"
                + "2. Test\n"
                + "3. Design\n"
                + "4. Review\n"
                + "Enter your choice: ", "Just be 1 -> 4",
                "Invalid!", 1, 4));
        task.setDate(Validator.getDate("Enter Date: ",
                "Invalid! Please enter format: dd-MM-yyyy", "dd-MM-yyyy"));
        while (true) {
            task.setPlanFrom(Validator.getDouble("From: ", "Just be 8 -> 17",
                    "Invalid!", 8, 17));
            String from = task.getPlanFrom() + "";
            if (from.split("\\.")[1].equals("0") || from.split("\\.")[1].equals("5")) {
                break;
            } else {
                System.out.println("Must be x.0 or x.5");
            }
        }
        while (true) {
            task.setPlanTo(Validator.getDouble("To: ",
                    "Just be " + (task.getPlanFrom() + 0.5) + "-> 17.5",
                    "Invalid!", (task.getPlanFrom() + 0.5), 17.5));
            String to = task.getPlanTo() + "";
            if (to.split("\\.")[1].equals("0") || to.split("\\.")[1].equals("5")) {
                break;
            } else {
                System.out.println("Must be x.0 or x.5");
            }
        }
        task.setAssign(Validator.getString("Assignee: ",
                "Invalid!", "[A-Za-z0-9\\s]+"));
        task.setReviewer(Validator.getString("Reviewer: ",
                "Invalid!", "[A-Za-z0-9\\s]+"));
        return task;
    }
}
