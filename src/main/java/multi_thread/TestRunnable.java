package multi_thread;

// 创建线程的第二种方式：
// 实现 Runnable 接口，重写 run 方法，调用 start
public class TestRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("我是线程。。。" + Thread.currentThread().getName());
}

    public static void main(String[] args) {
        TestRunnable r = new TestRunnable();
        Thread t = new Thread(r, "我是第一个线程");
        t.start();

        new Thread(r, "我是第二个线程").start();
    }
}
