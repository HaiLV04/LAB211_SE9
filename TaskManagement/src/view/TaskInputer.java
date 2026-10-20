package view;

import model.Task;

/**
 * Chức năng: Thu thập thông tin nhập liệu cho một công việc (Task) từ bàn phím.
 */
public class TaskInputer {

    private Task task;

    /**
     * Khởi tạo đối tượng TaskInputer với một Task rỗng.
     */
    public TaskInputer() {
        task = new Task();
    }

    /**
     * Chức năng: Hướng dẫn người dùng nhập các thông tin của Task từ bàn phím.
     * Luồng xử lý: Lần lượt nhập và validate requirementName, taskTypeID, date, planFrom, planTo, assign, reviewer và trả về đối tượng Task.
     *
     * @return đối tượng Task chứa thông tin vừa nhập
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
