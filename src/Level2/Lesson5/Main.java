package Level2.Lesson5;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;


    public static void main(String[] args) {
        float[] arr = new float[size];

        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {

            arr[i] = 1;
        }
        System.out.println("Время заполнения массива единицами :" + " " + (long) (System.currentTimeMillis() - a) + " ms");

        complexFilling(arr);
        fillTimeTwoArraysTwoStreams(arr);
    }

    private static void complexFilling(float[] arr) {
        long a = System.currentTimeMillis();

        for (int i = 0; i < h; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время заполнения сложного массива :" + " " + (long) (System.currentTimeMillis() - a) + " ms");

    }

    private static void fillTimeTwoArraysTwoStreams(float[] arr) {
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        long a = 0;
        long b = 0;
        long c = 0;
        MyThread t0 = new MyThread(1);
        MyThread t1 = new MyThread(2);
        t0.run();
        t1.run();
        try {
            c = System.currentTimeMillis();
            t0.join();
            a = System.currentTimeMillis();
            System.arraycopy(arr, 0, arr1, 0, h);

            for (int i = 0; i < h; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

            System.arraycopy(arr1, 0, arr, 0, h);

            System.out.println("Время выполнения первого потока :" + (long) (System.currentTimeMillis() - a) + " ms");

            t1.join();
            b = System.currentTimeMillis();
            System.arraycopy(arr, h, arr2, 0, h);

            for (int i = 0; i < h; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }

            System.arraycopy(arr2, 0, arr, h, h);

            System.out.println("Время выполнения второго потока :" + (long) (System.currentTimeMillis() - b) + " ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Общее время заполнения массива двумя потоками :" + (long) (System.currentTimeMillis() - c) + " ms");
    }

}


