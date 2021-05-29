package multi_thread;

import java.util.concurrent.*;

// 创建线程的第三种方式：实现 Callable 接口
// 要重写 call 方法
// call 方法有返回值，返回值的类型是 Callable<> 的泛型
public class TestCallable implements Callable<Boolean> {

    @Override
    public Boolean call() {
        System.out.println("我是线程。。。" + Thread.currentThread().getName());
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable c1 = new TestCallable();
        TestCallable c2 = new TestCallable();
        TestCallable c3 = new TestCallable();

        // 创建执行服务  线程池
        ExecutorService service = Executors.newFixedThreadPool(3);

        // 提交执行 r1 r2 r3 是 call 方法的返回值
        Future<Boolean> r1 = service.submit(c1);
        Future<Boolean> r2 = service.submit(c2);
        Future<Boolean> r3 = service.submit(c3);

        // 获取返回结果 call 方法的返回值
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        // 关闭服务
        service.shutdownNow();
    }
}
