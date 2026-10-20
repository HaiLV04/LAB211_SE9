package model;

import java.io.Serializable;

/**
 * Chức năng: Lưu trữ thông tin một công nhân (mã ID, họ tên, tuổi, lương, nơi làm việc).
 */
public class Worker implements Serializable {

    private String id;
    private String name;
    private int age;
    private double salary;
    private String workLocation;

    /**
     * Khởi tạo đối tượng công nhân với đầy đủ thông tin.
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
     * Lấy mã công nhân.
     *
     * @return mã công nhân
     */
    public String getId() {
        return id;
    }

    /**
     * Thiết lập mã công nhân theo định dạng.
     *
     * @param id mã công nhân (bắt đầu bằng W và theo sau là số)
     * @throws Exception nếu mã sai định dạng
     */
    private void setId(String id) throws Exception {
        if (id.matches("W\\d+")) {
            this.id = id;
        } else {
            throw new Exception("ID must be Wx (x is digit)");
        }
    }

    /**
     * Lấy họ tên công nhân.
     *
     * @return tên công nhân
     */
    public String getName() {
        return name;
    }

    /**
     * Thiết lập họ tên công nhân.
     *
     * @param name họ tên (chỉ gồm chữ cái và khoảng trắng)
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
     * Lấy tuổi công nhân.
     *
     * @return tuổi công nhân
     */
    public int getAge() {
        return age;
    }

    /**
     * Thiết lập tuổi công nhân trong khoảng [18, 50].
     *
     * @param age tuổi công nhân
     * @throws Exception nếu tuổi ngoài khoảng 18 đến 50
     */
    public void setAge(int age) throws Exception {
        if (age >= 18 && age <= 50) {
            this.age = age;
        } else {
            throw new Exception("Age must be 18->50");
        }
    }

    /**
     * Lấy mức lương của công nhân.
     *
     * @return mức lương
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Thiết lập mức lương của công nhân.
     *
     * @param salary lương (>= 0)
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
     * Lấy nơi làm việc.
     *
     * @return nơi làm việc
     */
    public String getWorkLocation() {
        return workLocation;
    }

    /**
     * Thiết lập nơi làm việc của công nhân.
     *
     * @param workLocation nơi làm việc
     * @throws Exception nếu chứa ký tự đặc biệt
     */
    public void setWorkLocation(String workLocation) throws Exception {
        if (workLocation.matches("[A-Za-z0-9\\s]+")) {
            this.workLocation = workLocation;
        } else {
            throw new Exception("Location must be alphabet,digit or space!");
        }
    }

    /**
     * Chuyển đổi thông tin công nhân thành chuỗi hiển thị.
     *
     * @return chuỗi biểu diễn công nhân
     */
    @Override
    public String toString() {
        return "Worker{" + id + ", " + name + ", " + age + ", " + salary + ", " + workLocation + '}';
    }
}
