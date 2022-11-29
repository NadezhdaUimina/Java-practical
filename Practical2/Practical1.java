// Реализовать алгоритм сортировки массива слиянием

package Practical2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Practical1 {

    public static int[] array(int size, int min, int max) {
        Random random = new Random();
        int[] array = random.ints(size, min, max).toArray();
        System.out.println("Исходный массив:");
        System.out.println(Arrays.toString(array));
        return array;
    }

    // деление массива
    public static void mergeSort(int[] array, int size) {
        if (size < 2) { // выход из рекурсии
            return;
        }
        int middle = size / 2;
        int[] leftArr = new int[middle]; // левая половина массива
        System.arraycopy(array, 0, leftArr, 0, middle); // присваиваем значения из массива array
        int[] rightArr = new int[size - middle]; // правая половина массива
        System.arraycopy(array, middle, rightArr, 0, size - middle); // присваиваем значения из массива array

        mergeSort(leftArr, middle); // левая часть возврат из рекурсии строкой return;
        mergeSort(rightArr, size - middle); // правая часть возврат из рекурсии строкой return;
        merge(array, leftArr, rightArr); // вызов функции слияние
    }

    // слияние и сортировка
    public static void merge(int[] array, int[] leftArr, int[] rightArr) {
        int left = leftArr.length;
        int right = rightArr.length;
        int i = 0; // индексы массива left
        int j = 0; // индексы массива right
        int k = 0; // индексы нового отсортированного массива
        while (i < left && j < right) { // сортируем
            if (leftArr[i] <= rightArr[j]) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
        }
        while (i < left) { // при достижении конца одного из подмассивов, остальные элементы из другого
                           // массива копируются в array
            array[k++] = leftArr[i++];
        }
        while (j < right) {
            array[k++] = rightArr[j++];
        }
    }

    public static void check(int size, int min, int max) {
        if (size > 1) {
            int[] arr = array(size, min, max);
            System.out.println("Отсортированный массив:");
            mergeSort(arr, size);
            System.out.println(Arrays.toString(arr));
        } else if (size == 1) {
            int[] arr = array(size, min, max);
            System.out.println("Отсортированный массив:");
            System.out.println(Arrays.toString(arr));
        } else if (size < 1) {
            System.out.println("Не верно введена длина массива, попробуйте снова!");
            main(null);
        }
    }

    public static void main(String[] args) {
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите min значение элементов массива (целое число): ");
        int min = iScanner.nextInt();
        System.out.printf("Введите max значение элементов массива (целое число): ");
        int max = iScanner.nextInt();
        System.out.printf("Введите длину массива (целое число): ");
        int size = iScanner.nextInt();
        check(size, min, max);
    }
}
