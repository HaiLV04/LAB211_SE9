package model;

import java.io.Serializable;

/**
 * Chức năng: Lớp lưu thông tin của một công nhân.
 * Luồng tương tác: Chứa các thuộc tính id, name, age, salary, workLocation và được quản lý bởi ManagerWorker.
 */
public class Worker implements Serializable {

    private String id;
    private String name;
    private int age;
    private double salary;
    private String workLocation;

    /**
     * Luồng xử lý 1: Khởi tạo đối tượng công nhân với các tham số.
     * Luồng xử lý 2: Dùng các phương thức setter để xác thực và gán dữ liệu.
     *
     * @param id mã công nhân
     * @param name tên công nhân
     * @param age tuổi công nhân
     * @param salary lương hiện tại
     * @param workLocation nơi làm việc
     * @throws Exception nếu dữ liệu không hợp lệ
     */
    public Worker(String id, String name, int age,
            double salary, String workLocation) throws Exception {
        setId(id);
        setName(name);
        setAge(age);
        setSalary(salary);
        setWorkLocation(workLocation);
    }

    public String getId() {
        return id;
    }

    /**
     * Luồng xử lý 1: Kiểm tra id theo định dạng bắt đầu bằng chữ W theo sau là chữ số.
     * Luồng xử lý 2: Gán id nếu hợp lệ, ngược lại ném ngoại lệ.
     *
     * @param id mã công nhân
     * @throws Exception nếu mã không đúng định dạng
     */
    private void setId(String id) throws Exception {
        if (id.matches("W\\d+")) {
            this.id = id;
        } else {
            throw new Exception("ID must be Wx (x is digit)");
        }
    }

    public String getName() {
        return name;
    }

    /**
     * Luồng xử lý 1: Kiểm tra tên chỉ chứa chữ cái và khoảng trắng.
     * Luồng xử lý 2: Thiết lập tên công nhân nếu hợp lệ, ngược lại ném ngoại lệ.
     *
     * @param name tên công nhân
     * @throws Exception nếu tên chứa ký tự không hợp lệ
     */
    public void setName(String name) throws Exception {
        if (name.matches("[A-Za-z\\s]+")) {
            this.name = name;
        } else {
            throw new Exception("Name must be alphabetic and space!");
        }
    }

    public int getAge() {
        return age;
    }

    /**
     * Luồng xử lý 1: Kiểm tra tuổi có nằm trong khoảng từ 18 đến 50 hay không.
     * Luồng xử lý 2: Thiết lập tuổi nếu hợp lệ, ngược lại ném ngoại lệ.
     *
     * @param age tuổi công nhân
     * @throws Exception nếu tuổi không hợp lệ
     */
    public void setAge(int age) throws Exception {
        if (age >= 18 && age <= 50) {
            this.age = age;
        } else {
            throw new Exception("Age must be 18->50");
        }
    }

    public double getSalary() {
        return salary;
    }

    /**
     * Luồng xử lý 1: Kiểm tra lương có lớn hơn hoặc bằng 0 hay không.
     * Luồng xử lý 2: Thiết lập lương nếu hợp lệ, ngược lại ném ngoại lệ.
     *
     * @param salary lương hiện tại
     * @throws Exception nếu lương nhỏ hơn 0
     */
    public void setSalary(double salary) throws Exception {
        if (salary >= 0) {
            this.salary = salary;
        } else {
            throw new Exception("Salary must be >=0");
        }
    }

    public String getWorkLocation() {
        return workLocation;
    }

    /**
     * Luồng xử lý 1: Kiểm tra chuỗi nơi làm việc chỉ chứa chữ cái, chữ số và khoảng trắng.
     * Luồng xử lý 2: Thiết lập nơi làm việc nếu hợp lệ, ngược lại ném ngoại lệ.
     *
     * @param workLocation nơi làm việc
     * @throws Exception nếu dữ liệu không hợp lệ
     */
    public void setWorkLocation(String workLocation) throws Exception {
        if (workLocation.matches("[A-Za-z0-9\\s]+")) {
            this.workLocation = workLocation;
        } else {
            throw new Exception("Location must be alphabet,digit or space!");
        }
    }

    /**
     * Luồng xử lý 1: Trả về chuỗi hiển thị thông tin công nhân.
     *
     * @return thông tin công nhân
     */
    @Override
    public String toString() {
        return "Worker{" + id + ", " + name + ", " + age + ", " + salary + ", " + workLocation + '}';
    }

}
