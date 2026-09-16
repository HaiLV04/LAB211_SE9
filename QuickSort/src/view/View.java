package view;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class View {
    private static final Scanner scanner = new Scanner(System.in);
    
    public static int inputInteger(String message, boolean isSizeCheck) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(input);
                if (isSizeCheck && number <= 0) {
                    System.out.println("Please enter a positive number > 0!");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer number!");
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
