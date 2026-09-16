package view;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Lớp View chứa các phương thức hỗ trợ cho chương trình, bao gồm nhập dữ
 * liệu, tạo mảng ngẫu nhiên và hiển thị mảng.
 */
public class View {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputPositiveNumber() {
        while (true) {
            try {
                System.out.print("Enter an integer number: ");
                String input = scanner.nextLine().trim();
                int number = Integer.parseInt(input);
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Please input positive number > 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input positive number!");
            }
        }
    }

    public static int[] generateRandom(int n) {
        int[] array = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(2 * n + 1) - n;
        }
        return array;
    }

    public static void displayArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }
}
