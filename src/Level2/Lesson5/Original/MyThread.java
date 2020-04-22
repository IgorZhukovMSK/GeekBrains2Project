package Level2.Lesson5.Original;

public class MyThread extends Thread {

    private final float [] arr;
    int numThread;


    public MyThread(float[] arr, int numThread) {
        this.arr = arr;
        this.numThread = numThread;
        start();
    }

    @Override
    public void run() {

        System.out.println(getName() + " started...");
        for (int i =0; i < arr.length; i++)
            arr[i] = (float) (arr[i] *
                    Math.sin(0.2f + (i + numThread)/5) *
                    Math.cos(0.2f + (i + numThread)/5) *
                    Math.cos(0.4f + (i + numThread)/2));

        System.out.println(getName() + " stopprd.");

    }
}
