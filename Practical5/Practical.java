// Написать программу вычисления n-ого треугольного числа.

package Practical5;

import java.util.Scanner;

public class Practical {
    public static void name(int n) {
        if (n == (int) n && n > 0) {
            int t = (n + 1) * n / 2;
            System.out.printf("%d-e треугольное число: %d", n, t);
        } else {
            System.out.println("Вы ввели неверное значениу, допустимы только целые, положительные числа.");
        }
    }

    public static void main(String[] args) {
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите натуральное число, для вычисления треугольного числа: ");
        int n = iScanner.nextInt();
        iScanner.close();
        name(n);
    }
}
