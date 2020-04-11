package Level2.Lesson2;

public class Main {




    /*
    Написать метод, на вход которого подаётся такая строка, метод должен преобразовать строку в двумерный массив типа String[][];
2. Преобразовать все элементы массива в числа типа int, просуммировать, поделить полученную сумму на 2, и вернуть результат;
3. Ваши методы должны бросить исключения в случаях:
Если размер матрицы, полученной из строки, не равен 4x4;                                // Решение: посмотреть в отладчике где данная ошибка выскакивает
Если в одной из ячеек полученной матрицы не число; (например символ или слово)          // Решение: посмотреть в отладчике где данная ошибка выскакивает
4. В методе main необходимо вызвать полученные методы, обработать возможные исключения и вывести результат расчета.
5. * Написать собственные классы исключений для каждого из случаев
     */

    public static void main(String[] args) {

        // String str = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";

        stringsNum("500 3 1000 2\n2 38c88 2 2\n5 6 7 1\n300 3 1 0 15");

    }

    public static void stringsNum(String str) {

        String[] tempStr = str.split("\n");

        String[][] strings = new String[tempStr.length][];
        int r = 0;
        for (String string : tempStr) {
            strings[r++] = string.split(" ");
        }

        String[][] numStrTemp = {
                tempStr[0].split(" "),
                tempStr[1].split(" "),
                tempStr[2].split(" "),
                tempStr[3].split(" "),
        };

        int[][] numTemp = new int[4][4];
        int summ = 0;
        try {
            for (int i = 0; i < numStrTemp.length; i++) {
                for (int j = 0; j < numStrTemp[i].length; j++) {

                    numTemp[i][j] = Integer.parseInt(numStrTemp[i][j]);

                    System.out.print(numTemp[i][j]);
                    summ += numTemp[i][j];
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("\n не верное количество элементов в массиве \n " + e);
        } catch (NumberFormatException e) {
            System.out.println("\n в массиве присутствует не число, проверьте \n" + e);
        }

        int summTemp = summ / 2;
        System.out.println();
        System.out.println(summTemp);
    }

}
