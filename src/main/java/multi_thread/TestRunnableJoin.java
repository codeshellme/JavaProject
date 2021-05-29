package multi_thread;

public class TestRunnableJoin implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程 vip 来了:" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // 启动线程
        TestRunnableJoin j = new TestRunnableJoin();
        Thread t = new Thread(j);
        t.start();

        // 主线程
        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                t.join(); // 让 t 线程执行完，再往下走，所有别的线程均阻塞
            }

            System.out.println("Main Thread:" + i);
        }
    }
}
