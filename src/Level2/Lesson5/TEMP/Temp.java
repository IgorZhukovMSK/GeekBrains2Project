package Level2.Lesson5.TEMP;

import java.util.Date;

public class Temp {

    static final int size = 10000000;
    static final int h = size / 2;


    private static void complexFilling() {
        float[] arr = new float[size];
        long a = System.currentTimeMillis();

        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println("Время заполнения сложного массива :" + " " + (long) (System.currentTimeMillis() - a) + " ms");

    }


    static class MyThread extends Thread  {
        float[] arr = new float[size];

               @Override
        public void run(){
                   Date date = new Date();
                   System.out.println("начало потока 1 " + date.toString());

            long t0 = System.currentTimeMillis();

            for (int i = 0; i < h; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

            System.out.println(System.currentTimeMillis() - t0);
                   Date dateFinich = new Date();
                   System.out.println("Окончание потока 1 " + dateFinich.toString());
            System.out.println("Новый поток " + 1);
        }
        }

        static class MyThread1 extends Thread  {
        float[] arr = new float[size];

        @Override
        public void run(){
            Date date = new Date();
            System.out.println("начало потока 2 " + date.toString());
            long t0 = System.currentTimeMillis();

            for (int i = 0; i < h; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
            try {
                MyThread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(System.currentTimeMillis() - t0);
            Date dateFinich = new Date();
            System.out.println("Окончание потока 2 " + dateFinich.toString());
            System.out.println("Новый поток " + 2);
        }
        }



    public static void main(String[] args) {

        new MyThread().start();
        new MyThread1().start();
        complexFilling();
    }

}
