package view;

/**
 * Lớp View xử lý hiển thị đầu ra.
 */
public class View {
    public void displayTitle() {
        System.out.println("The 45 sequence fibonacci:");
    }

    public void displayFibonacci(int value, boolean isLast) {
        System.out.print(value);
        if (isLast) {
            System.out.println(".");
        } else {
            System.out.print(", ");
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
