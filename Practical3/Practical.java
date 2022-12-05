/* На вход некоторому исполнителю
 подаётся два числа (a, b).
 У исполнителя есть две команды
 - команда 1 (к1): увеличить в с раз (xC), а умножается на c
 - команда 2 (к2): увеличить на d ( +d ), к a прибавляется d
 написать программу, которая выдаёт общее количество
 возможных преобразований a в b.
 a < b; a, b, c, d - натуральные
 Пример 1: а = 2, b = 7, c = 2, d = 1 ответ 3
 Подумать как можно показать хотя бы один маршрут преобразования
 ответ: (+1) (x2) (+1) или (х2) (+1) (+1) (+1)
 Пример 2: а = 11, b = 7, c = 2, d = 1
 ответ: нет решения. */

package Practical3;

import java.util.Scanner;

public class Practical {
    public static long[] name(int a, int b, int c, int d) {
        long[] arr = new long[b + 1];
        arr[a] = 1;
        for (int i = 0; i <= b; i++) {
            if (i % c == 0 && i / c > 0) {
                arr[i] += arr[i / c];
            }
            if (i > a && i - d > 0) {
                arr[i] += arr[i - d];
            }
        }
        return arr;
    }

    public static void check(int a, int b, int c, int d) {
        if (a > b) {
            System.out.println("Неверно введены данные, начальное число не может быть больше конечного!");
        } else {
            long[] arr = name(a, b, c, d);
            long res = arr[b];
            System.out.printf("Общее количество возможных преобразований %d в %d равно %d", a, b, res);
            minPath(res, a, b, c, d);
        }
    }

    public static void minPath(long res, int a, int b, int c, int d) {
        String str = "";
        System.out.println();
        System.out.println("Маршрут минимального преобразования:");
        if (res == 0) {
            System.out.println("Нет решения!");
        } else {
            while (b > a) {
                if (b % c == 0) {
                    str += "(x" + c + ") ";
                    b = b / c;
                } else {
                    str += "(+" + d + ") ";
                    b = b - d;
                }
            }
            System.out.println(str);
        }
    }

    public static void dataEntry() {
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите начальное целое число: ");
        int a = iScanner.nextInt();
        System.out.printf("Введите конечное целое число: ");
        int b = iScanner.nextInt();
        System.out.printf("Введите множитель, целое число: ");
        int c = iScanner.nextInt();
        System.out.printf("Введите слагаемое, целое число: ");
        int d = iScanner.nextInt();
        iScanner.close();
        check(a, b, c, d);
    }

    public static void main(String[] args) {
        dataEntry();
    }
}

// Тестовые данные
// a: 2 b: 7 c: 2 d: 1 -> 3
// a: 3 b: 27 c: 3 d: 2 -> 6
// a: 30 b: 345 c: 5 d: 6 -> 0
// a: 30 b: 345 c: 2 d: 1 -> 7047
// a: 22 b: 333 c: 3 d: 1 -> 467
// a: 55 b: 555 c: 5 d: 2 -> 30
// a: 22 b: 2022 c: 11 d: 56 -> 0
// a: 22 b: 2022 c: 11 d: 10 -> 18
// a: 22 b: 2022 c: 3 d: 1 -> 763827
// a: 22 b: 20220 c: 3 d: 1 -> 535173226980
// a: 1 b: 1111 c: 2 d: 1 -> 3990330794
// a: 1 b: 11111 c: 2 d: 1 -> 606408167570737286