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


    public static void main (String [] args) {
        float[] arr = new float[size];
        long a = 0;


        fillingInUnits(arr);
        complexFilling(arr);

        }

    private static void complexFilling(float[] arr) {
        long a;
        a = System.currentTimeMillis();
        for (int i = 0; i < size; i ++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println(System.currentTimeMillis()-a);
    }

    private static void fillingInUnits(float[] arr) {
        long a;
        a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {

            arr[i] = 1;
        }
        System.out.println(System.currentTimeMillis()-a);
    }
}


