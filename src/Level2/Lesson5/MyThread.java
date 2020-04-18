package Level2.Lesson5;

public class MyThread extends Thread {

    int numThread;

    public MyThread(int numThread) {
        this.numThread = numThread;
        start();
    }

    @Override
    public void run() {

    }
}
