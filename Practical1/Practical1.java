// Реализовать функцию возведения числа а в степень b. a, b ∈ Z. Сводя количество выполняемых действий к минимуму.
// Пример 1: а = 3, b = 2, ответ: 9
// Пример 2: а = 2, b = -2, ответ: 0.25
// Пример 3: а = 3, b = 0, ответ: 1
// Пример 4: а = 0, b = 0, ответ: не определено
// Пример 5
// входные данные находятся в файле input.txt в виде
// b 3
// a 10
// Результат нужно сохранить в файле output.txt
// 1000
import java.io.*;

/**
 * Practical1
 */
public class Practical1 {

    public static int exponentiation(int a, int b) {
        int res = 1;
        for (int i = 0; i < b; i++) {
            res *= a;
        }
        return res;
    }

    public static int[] inputFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("Practical1/input.txt"));
        String str;
        String numA = "";
        String numB = "";
        while ((str = br.readLine()) != null) {
            if (str.contains("a")) {
                for (int i = 0; i < str.length(); i++) {
                    char chrs = str.charAt(i);
                    if (Character.isDigit(chrs))
                        numA = numA + chrs;
                }
                if (str.contains("-")) {
                    numA = "-" + numA;
                }
            } else if (str.contains("b")) {
                for (int i = 0; i < str.length(); i++) {
                    char chrs = str.charAt(i);
                    if (Character.isDigit(chrs))
                        numB = numB + chrs;
                }
                if (str.contains("-")) {
                    numB = "-" + numB;
                }
            }
        }
        int a = Integer.parseInt(numA);
        int b = Integer.parseInt(numB);
        int[] arrNum = { a, b };

        return arrNum;
    }

    public static String check(int[] array) {
        int a = array[0];
        int b = array[1];
        String strRes = "";
        if (a != 0 && b == 0) {
            strRes = String.format("%d ^ %d = 1", a, b);
        }
        if (b > 0) {
            int res = exponentiation(a, b);
            strRes = String.format("%d ^ %d = %d", a, b, res);
        }
        if (a != 0 && b < 0) {
            b *= (-1);
            double res = 1.0 / exponentiation(a, b);
            strRes = String.format("%d ^ (-%d) = %.2f", a, b, res);
        }
        if (a == 0 && b < 0) {
            strRes = "не определено";
        }
        if (a == 0 && b == 0) {
            strRes = "не определено";
        }
        return strRes;
    }

    public static void main(String[] args) throws Exception {
        int[] arrNum = inputFile();
        String str = check(arrNum);
        System.out.println(str);

        try (FileWriter fw = new FileWriter("Practical1/output.txt", false)) {
            fw.write(str);
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}