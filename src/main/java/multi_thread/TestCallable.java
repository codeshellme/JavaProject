package multi_thread;

import java.util.concurrent.*;

// 创建线程的第三种方式：实现 Callable 接口，该方式可以获取线程的执行结果
//      继承 Thread 与实现 Runnable接口的方式都无法获取线程的执行结果

// 要重写 call 方法
// call 方法有返回值，返回值的类型是 Callable<> 的泛型

// 一般与 ExecutorService 配合来使用

// Future 是一个接口，是对于具体的 Runnable 或者 Callable 任务的执行结果进行[取消]、[查询是否完成]、[获取结果]
// Future 接口中声明了5个方法：
//      isCancelled 方法表示任务是否被取消成功，如果在任务正常完成前被取消成功，则返回 true
//      isDone 方法表示任务是否已经完成，若任务完成，则返回true
//      get() 方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
//      get(long timeout, TimeUnit unit)用来获取执行结果
//          如果在指定时间内，还没获取到结果，就直接返回 null
//      cancel 方法用来取消任务，如果取消任务成功则返回true，如果取消任务失败则返回false
//          参数 mayInterruptIfRunning 表示是否允许取消正在执行却没有执行完毕的任务
//              如果设置true，则表示可以取消正在执行过程中的任务。
//              如果任务已经完成，则无论mayInterruptIfRunning为true还是false，此方法肯定返回false
//                  即如果取消已经完成的任务会返回false
//              如果任务正在执行
//                  若 mayInterruptIfRunning设置为true，则返回true
//                  若mayInterruptIfRunning设置为false，则返回false
//              如果任务还没有执行，则无论mayInterruptIfRunning为true还是false，肯定返回true

// FutureTask 是 Future 接口的一个唯一实现类

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
        boolean rs1 = r1.get();         // get 方法会阻塞直到任务返回结果
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        // 关闭服务
        service.shutdownNow();
    }
}
