package Level2.Lesson5.Original;

import java.util.Arrays;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;
    static final float[] arr1 = new float[size];
    static final float[] arr2 = new float[size];


    public static void main(String[] args) {

       // float[] arr = new float[size];


        arrayFillingUnits(arr1);
        complexFilling(arr1);
        long beginTime = System.currentTimeMillis();
        fillTimeTwoArraysTwoStreams(arr2);
        long deltaTime = System.currentTimeMillis() - beginTime;
        System.out.println("Двойной thread time: " + deltaTime);

        if (Arrays.equals(arr1, arr2)) {
            System.out.println("Arrays are equals 1");
        } else {
            System.out.println("Arrays are not equals 1");
        }


        Arrays.fill(arr1, 1f);
        Arrays.fill(arr2, 1f);

       // long beginTime = System.nanoTime();
         beginTime = System.currentTimeMillis();
        calculateArray(arr1);
      //  long deltaTime = System.nanoTime() - beginTime;
         deltaTime = System.currentTimeMillis() - beginTime;
        System.out.println("One thread time: " + deltaTime);
//        System.out.println("One thread time: " + deltaTime * 1e-9f);

//        beginTime = System.nanoTime();
        beginTime = System.currentTimeMillis();
        calculateArrayTwoThread(arr2);
//        deltaTime = System.nanoTime() - beginTime;
        deltaTime = System.currentTimeMillis() - beginTime;
        System.out.println("Two thread time: " + deltaTime);
//        System.out.println("Two thread time: " + deltaTime * 1e-9f);

        if (Arrays.equals(arr1, arr2)) {
            System.out.println("Arrays are equals");
        } else {
            System.out.println("Arrays are not equals");
        }

    }

    private static void arrayFillingUnits(float[] arr) {

        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        System.out.println("Время заполнения массива единицами :" + " " + (long) (System.currentTimeMillis() - a) + " ms");
    }

    private static void complexFilling(float[] arr) {
        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время заполнения сложного массива :" + " " + (long) (System.currentTimeMillis() - a) + " ms");

    }

    private static void calculateArray (float [] arr) {
        int numThread = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] *
                    Math.sin(0.2f + (i + numThread)/5) *
                    Math.cos(0.2f + (i + numThread)/5) *
                    Math.cos(0.4f + (i + numThread)/2));
        }
    }

    private static void calculateArrayTwoThread (float [] arr) {
        final float[] a1 = new float[h];
        final float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        CalcThread threadOne = new CalcThread(a1, 0);
        CalcThread threadTwo = new CalcThread(a2, h);

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);

        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);
    }


    private static void fillTimeTwoArraysTwoStreams(float[] arr) {
        final float[] a1 = new float[h];
        final float[] a2 = new float[h];

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        MyThread t0 = new MyThread(a1, 0);
        MyThread t1 = new MyThread(a2, h);

        try {

            t0.join();
            t1.join();
} catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);


    }

}




