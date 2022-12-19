package Practical4;

import java.util.LinkedList;
import java.util.Queue;

public class Practical {
    // готовое поле
    public static int[][] map() {
        int[][] mapArr = {
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                { -1, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, -1 },
                { -1, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, -1 },
                { -1, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, -1 },
                { -1, 0, 0, -1, -1, 0, 0, 0, 0, -1, -1, 0, -1 },
                { -1, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, -1 },
                { -1, 0, -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, -1 },
                { -1, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, -1 },
                { -1, 0, 0, 0, 0, -1, 0, 0, -1, -1, -1, -1, -1 },
                { -1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1 },
                { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
        };
        return mapArr;
    }

    // печать поля
    public static void printArr(int[][] arr, int xS, int yS, int xF, int yF) {
        String[][] newArr = new String[arr.length][arr[1].length];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == -1) {
                    newArr[i][j] = "#"; // преграда
                } else {
                    newArr[i][j] = "-"; // пустое поле
                }
                if (arr[i][j] == -2)
                    newArr[i][j] = "@"; // путь
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
    public static int[][] waveAlgorithm(int[][] map, int xS, int yS, int xF, int yF) {
        Queue<int[]> queue = new LinkedList<int[]>();
        map[xS][yS] = 1;
        int[] num = { xS, yS };
        queue.add(num);
        while (queue.size() != 0) {
            num = queue.remove();
            if (map[num[0] + 1][num[1]] == 0) {
                queue.add(new int[] { num[0] + 1, num[1] });
                map[num[0] + 1][num[1]] = map[num[0]][num[1]] + 1;
            }
            if (map[num[0]][num[1] + 1] == 0) {
                queue.add(new int[] { num[0], num[1] + 1 });
                map[num[0]][num[1] + 1] = map[num[0]][num[1]] + 1;
            }
            if (map[num[0] - 1][num[1]] == 0) {
                queue.add(new int[] { num[0] - 1, num[1] });
                map[num[0] - 1][num[1]] = map[num[0]][num[1]] + 1;
            }
            if (map[num[0]][num[1] - 1] == 0) {
                queue.add(new int[] { num[0], num[1] - 1 });
                map[num[0]][num[1] - 1] = map[num[0]][num[1]] + 1;
            }
        }
        return map;
    }

    // поиск пути
    public static int[][] searchWay(int[][] map, int[][] newMap, int xS, int yS, int xF, int yF) {
        int[][] res = map;
        int num = newMap[xF][yF];
        if (newMap[xF][yF] == 0) {
            System.out.println("Путь не найден");
        } else {
            while (num != 1) {
                if (newMap[xF - 1][yF] == num - 1) {
                    res[xF - 1][yF] = -2;
                    xF--;
                }
                if (newMap[xF][yF - 1] == num - 1) {
                    res[xF][yF - 1] = -2;
                    yF--;
                }
                if (newMap[xF + 1][yF] == num - 1) {
                    res[xF + 1][yF] = -2;
                    xF++;
                }
                if (newMap[xF][yF + 1] == num - 1) {
                    res[xF][yF + 1] = -2;
                    yF++;
                }
                num--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int xS = 2; // точка старта
        int yS = 5;
        int xF = 9; // точка финиша
        int yF = 9;

        int[][] map = map();
        int[][] newMap = waveAlgorithm(map, xS, yS, xF, yF);
        int[][] res = searchWay(map, newMap, xS, yS, xF, yF);
        printArr(res, xS, yS, xF, yF);

    }
}
