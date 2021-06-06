package multi_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 线程池
public class TestThreadPool {
    public static void main(String[] args) {
        // 1 创建服务，创建了一个拥有 10 个线程的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);

        // 2 执行线程
        service.execute(new MyThread());
        service.execute(new MyThread());
        service.execute(new MyThread());

        // 3 关闭线程
        service.shutdown();
    }
}

class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}