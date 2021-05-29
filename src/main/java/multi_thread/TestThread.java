package multi_thread;

/*
Java 线程的三种创建方式：
        1，继承 Thread 类
        2，实现 Runnable 接口
        3，实现 Callable 接口
*/

// 创建线程的第一种方式：
// 继承 Thread 类，重写 run 方法，调用 start
public class TestThread extends Thread {

    // 用来停止线程
    private boolean isStop = true;

    @Override
    public void run() {
        if (!isStop) {
            System.out.println("我是一个线程。。。" + Thread.currentThread().getName());
        }
    }

    // 正常停止线程的方式，建议使用本方式
    public void stopThread() {
        isStop = true;
    }

    public static void main(String[] args) {
        TestThread t = new TestThread();
        t.start();  // 调用 start 方法

        new TestThread().start();

        // 使用 Lamda（Java 8 提供的语法） 表达式
        new Thread(()-> {
            System.out.println("我就是这个线程。。。" + Thread.currentThread().getName());
        }).start();
    }
}

// 线程重要的方法：
//  static void sleep   线程休眠
//      每个对象都有一把锁，sleep 不会释放锁
//  static void yield   线程礼让：
//      将当前线程的的 CPU 控制权让出来，让 CPU 重新选择要执行的线程（有可能礼让之后，CPU 还是选择了当前线程）
//  static Thread currentThread     获取当前线程

//  void setPriority    更改线程优先级11
//  boolean isAlive     测试线程是否存活
//  void join           等待线程终止：
//      让当前线程执行完之后，再去执行其它线程（其它线程处于阻塞状态）
//  getState    获取线程当前状态

// 线程停止：不推荐使用 stop destroy 方法（已经废弃）
//          建议使用一个标志位来终止线程

// 线程状态：Thread.State 类
//      其中定义了 6 个线程状态
//      线程停止之后就不能再次 start 了，一个线程只能 start 一次