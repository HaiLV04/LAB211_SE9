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
     * Chức năng (Làm gì): Khởi tạo đối tượng công nhân với đầy đủ thông tin.
     * Luồng xử lý (Làm như thế nào):
     * 1. Nhận các tham số id, name, age, salary, workLocation.
     * 2. Gọi các phương thức setter tương ứng để kiểm tra tính hợp lệ và gán dữ liệu.
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

    /**
     * Chức năng (Làm gì): Lấy mã công nhân.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính id của công nhân.
     *
     * @return mã công nhân
     */
    public String getId() {
        return id;
    }

    /**
     * Chức năng (Làm gì): Thiết lập mã công nhân theo định dạng.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra id theo biểu thức chính quy "W\\d+" (bắt đầu bằng W và theo sau là các chữ số).
     * 2. Gán id nếu hợp lệ, ngược lại ném ngoại lệ.
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

    /**
     * Chức năng (Làm gì): Lấy tên công nhân.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính name của công nhân.
     *
     * @return tên công nhân
     */
    public String getName() {
        return name;
    }

    /**
     * Chức năng (Làm gì): Thiết lập tên công nhân.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra tên chỉ chứa chữ cái và khoảng trắng ("[A-Za-z\\s]+").
     * 2. Thiết lập tên công nhân nếu hợp lệ, ngược lại ném ngoại lệ.
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

    /**
     * Chức năng (Làm gì): Lấy tuổi của công nhân.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính age của công nhân.
     *
     * @return tuổi công nhân
     */
    public int getAge() {
        return age;
    }

    /**
     * Chức năng (Làm gì): Thiết lập tuổi của công nhân.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra tuổi có nằm trong khoảng từ 18 đến 50 hay không.
     * 2. Thiết lập tuổi nếu hợp lệ, ngược lại ném ngoại lệ.
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

    /**
     * Chức năng (Làm gì): Lấy mức lương của công nhân.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính salary của công nhân.
     *
     * @return mức lương của công nhân
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Chức năng (Làm gì): Thiết lập mức lương của công nhân.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra lương có lớn hơn hoặc bằng 0 hay không.
     * 2. Thiết lập lương nếu hợp lệ, ngược lại ném ngoại lệ.
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

    /**
     * Chức năng (Làm gì): Lấy nơi làm việc của công nhân.
     * Luồng xử lý (Làm như thế nào): Trả về thuộc tính workLocation của công nhân.
     *
     * @return nơi làm việc
     */
    public String getWorkLocation() {
        return workLocation;
    }

    /**
     * Chức năng (Làm gì): Thiết lập nơi làm việc của công nhân.
     * Luồng xử lý (Làm như thế nào):
     * 1. Kiểm tra chuỗi nơi làm việc chỉ chứa chữ cái, chữ số và khoảng trắng.
     * 2. Thiết lập nơi làm việc nếu hợp lệ, ngược lại ném ngoại lệ.
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
     * Chức năng (Làm gì): Trả về chuỗi hiển thị thông tin công nhân.
     * Luồng xử lý (Làm như thế nào): Ghép các thuộc tính id, name, age, salary, workLocation thành một chuỗi đại diện.
     *
     * @return thông tin công nhân
     */
    @Override
    public String toString() {
        return "Worker{" + id + ", " + name + ", " + age + ", " + salary + ", " + workLocation + '}';
    }
}
