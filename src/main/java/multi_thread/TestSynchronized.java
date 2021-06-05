package multi_thread;

// 线程同步
// synchronized 关键字，有两种使用方式
//      1，synchronized 方法
//      2，synchronized 块： synchronized(obj) {}
//                          在 synchronized 方法中，obj 默认为 this
//          注意：obj 应该是变化的对象，即是需要增删改的对象，否则会不起作用
//               obj 的选择是关键

// synchronized 的原理
//      每个 Java 对象都有一把锁，每个 synchronized 方法都必须获得调用该方法的对象的锁才能执行，
//      否则线程就会阻塞，synchronized 方法一旦执行，就会独占该锁，其它想获取该锁的线程就会阻塞

// Java 中的锁 Lock 接口
// java.util.concurrent.locks.Lock 接口
// ReentrantLock（可重入锁） 类实现了 Lock 接口，使用模板：
//       lock.lock()        获取锁      一般放在 try 块中
//       doSomeThing()      业务逻辑    一般放在 try 块中
//       lock.unlock()      释放锁      一般放在 finally 块中，以保证锁的释放

// synchronized 与 Lock 对比
//      synchronized 是隐式的锁，使用方便
//      Lock 是显示的锁，性能更好
// 性能高低： Lock > synchronized 块 > synchronized 方法
public class TestSynchronized {
}
