package multi_thread;

// 线程通信问题
// Java 中提供了几个方法来解决线程之间的通信问题：
//      wait()              线程（释放掉锁，处于等待状态）一直等待（阻塞），直到其它线程通知(notify)
//      wait(long timeout)  可指定等待时长
//      notify()            唤醒一个处于等待状态的线程
//      notifyAll()         唤醒同一个对象上所有调用 wait() 方法的线程，优先级高的线程优先调度
// 注意：以上这些方法都是 Object 类中的方法，都只能在同步方法或者同步代码块中使用，否则会抛异常
public class TestCommunication {
}
