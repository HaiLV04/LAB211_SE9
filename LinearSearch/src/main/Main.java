package main;

import controller.Controller;

/**
 * Chức năng: Chạy chương trình chính, gọi Controller.
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.execute();
    }
}
