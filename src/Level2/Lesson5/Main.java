package Level2.Lesson5;
/*

1. Необходимо написать два метода, которые делают следующее:

1) Создают одномерный длинный массив, например:

static final int size = 10000000;
static final int h = size / 2;
float[] arr = new float[size];

2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();

 */

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;


    public static void main (String [] args){

        float[] arr = new float[size];

        long a = System.currentTimeMillis();

        for (float fl : arr) {

            System.out.println(fl);
        }

    }

}
