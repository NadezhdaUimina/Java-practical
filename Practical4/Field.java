package Practical4;

import java.util.LinkedList;
import java.util.Queue;


public class Field {
    // готовое поле
    public static String[][] map() {
        String[][] mapArr = {
                { "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#" },
                { "#", "-", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "#" },
                { "#", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "-", "-", "#", "#", "-", "-", "-", "-", "#", "#", "-", "#" },
                { "#", "-", "-", "-", "-", "-", "-", "-", "#", "#", "-", "-", "#" },
                { "#", "-", "#", "-", "-", "-", "-", "#", "-", "-", "-", "-", "#" },
                { "#", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "-", "-", "-", "-", "#", "-", "-", "-", "-", "-", "#", "#" },
                { "#", "-", "#", "-", "-", "-", "-", "-", "-", "-", "-", "-", "#" },
                { "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#", "#" }
        };
        return mapArr;
    }


    // печать поля
    public static void printArr(String[][] arr, int xS, int yS, int xF, int yF) {
        String[][] newArr = new String[arr.length][arr[1].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                // if (arr[i][j] == "#")
                //     newArr[i][j] = "#"; // преграда
                // if (arr[i][j] == -2)
                //     newArr[i][j] = "@"; // путь
                // if (arr[i][j] == "-")
                //     newArr[i][j] = "-"; // пустое поле
                if (i == xS && j == yS)
                    newArr[i][j] = "S"; // точка старта
                if (i == xF && j == yF)
                    newArr[i][j] = "F"; // точка финеша
                System.out.print(newArr[i][j]);
            }
            System.out.println();
        }
    }

    // реализация волнового алгоритма
    public static int[][] waveAlgorithm(String [][] map, int xS, int yS, int xF, int yF) {
        Queue<int[]> queue = new LinkedList<int[]>();
        int[][] mapInt = new int[map[0].length][map[1].length];
        mapInt[xS][yS] = 1;
        int[] num = { xS, yS };
        queue.add(num);
        while (queue.size() != 0) {
            num = queue.remove();
            if (map[num[0] + 1][num[1]] == "-") {
                queue.add(new int[] { num[0] + 1, num[1] });
                mapInt[num[0] + 1][num[1]] = mapInt[num[0]][num[1]] + 1;
            }
            if (map[num[0]][num[1] + 1] == "-") {
                queue.add(new int[] { num[0], num[1] + 1 });
                mapInt[num[0]][num[1] + 1] = mapInt[num[0]][num[1]] + 1;
            }
            if (map[num[0] - 1][num[1]] == "-") {
                queue.add(new int[] { num[0] - 1, num[1] });
                mapInt[num[0] - 1][num[1]] = mapInt[num[0]][num[1]] + 1;
            }
            if (map[num[0]][num[1] - 1] == "-") {
                queue.add(new int[] { num[0], num[1] - 1 });
                mapInt[num[0]][num[1] - 1] = mapInt[num[0]][num[1]] + 1;
            }
        }
        return mapInt;
    }

    public static String[][] name(String[][] map, int[][] mapInt, int xS, int yS, int xF, int yF) {
        int num = mapInt[xF][yF];
        while (num != 1) {
            if (mapInt[xF - 1][yF] == num - 1) {
                map[xF - 1][yF] = "@";
                xF--;
            }
            if (mapInt[xF][yF - 1] == num - 1) {
                map[xF][yF - 1] = "@";
                yF--;
            }
            if (mapInt[xF + 1][yF] == num - 1) {
                map[xF + 1][yF] = "@";
                xF++;
            }
            if (mapInt[xF][yF + 1] == num - 1) {
                map[xF][yF + 1] = "@";
                yF++;
            }
            num--;
        }
        return map;
    }

    public static void main(String[] args) {
        int xS = 2; // точка старта
        int yS = 5;
        int xF = 8; // точка финиша
        int yF = 9;

        String[][] map = map();
        int[][] mapInt = waveAlgorithm(map, xS, yS, xF, yF);
        String[][] mapRes = name(map, mapInt, xS, yS, xF, yF);
        printArr(mapRes, xS, yS, xF, yF);
    }
}