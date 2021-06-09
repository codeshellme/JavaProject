package multi_thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

// ================================================== 线程同步/锁 ========================================================

// ================================================= synchronized ======================================================
// synchronized 关键字，有两种使用方式
//      1，synchronized 方法
//      2，synchronized 块： synchronized(obj) {}
//                          在 synchronized 方法中，obj 默认为 this
//          注意：obj 应该是变化的对象，即是需要增删改的对象，否则会不起作用
//               obj 的选择是关键

// synchronized 的原理
//      每个 Java 对象都有一把锁，每个 synchronized 方法都必须获得调用该方法的对象的锁才能执行，
//      否则线程就会阻塞，synchronized 方法一旦执行，就会独占该锁，其它想获取该锁的线程就会阻塞

// synchronized 锁的释放，有两种情况：
//      1, 获取锁的线程执行完了该代码块，然后线程释放对锁的占有
//      2, 线程执行发生异常，此时JVM会让线程自动释放锁

// ================================================== Lock =============================================================
// Java 中的锁 Lock 接口，比 synchronized 拥有更多，更细致的功能
// java.util.concurrent.locks.Lock 接口 中的方法
//      void lock();                                    获取锁，如果锁已被其他线程获取，则进行等待
//      void lockInterruptibly();                       获取锁，与中断有关
//      boolean tryLock();                              获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
//                                                      个方法无论如何都会立即返回，在拿不到锁时不会一直在那等待
//      boolean tryLock(long time, TimeUnit unit);      在拿不到锁时会等待一定的时间
//                                                      在时间期限内如果还拿不到锁，就返回false。如果一开始拿到锁或者在等待期间内拿到了锁，则返回true
//      void unlock();                                  释放锁
//      Condition newCondition();                       条件变量，用于线程协作

// ============================================== ReentrantLock ========================================================
// ReentrantLock（可重入锁） 类，是唯一实现了Lock接口的类
// 一些方法：
//      isLocked()                  //判断锁是否被任何线程获取了
//      isHeldByCurrentThread()     //判断锁是否被当前线程获取了
//      hasQueuedThreads()          //判断是否有线程在等待该锁
// 使用模板：
//       lock.lock()        获取锁      一般放在 try 块中
//       doSomeThing()      业务逻辑    一般放在 try 块中
//       lock.unlock()      释放锁      一般放在 finally 块中，以保证锁的释放，防止死锁的发生

// synchronized 与 Lock 对比
//      synchronized 是隐式的锁，使用方便
//      synchronized 不需要手动释放锁（不会出现死锁现象），而 Lock 需要开发者负责锁的释放，否则会导致死锁
//      Lock 是显示的锁，性能更好
// 性能高低： Lock > synchronized 块 > synchronized 方法

// ============================================= ReadWriteLock =========================================================
// ReadWriteLock Java 中的读写锁接口，将文件的读写操作分开，分成2个锁来分配给线程，从而使得多个线程可以同时进行读操作
//      当一个线程已经占用了读锁：
//          此时其他线程如果要申请读锁，则可以申请到
//          此时其他线程如果要申请写锁，则申请写锁的线程会一直等待释放读锁
//      当一个线程已经占用了写锁：
//          此时其他线程如果申请写锁或者读锁，则申请的线程会一直等待释放写锁
// 其中的两个方法：
//      Lock readLock();        获取读锁
//      Lock writeLock();       获取写锁

// ReentrantReadWriteLock 类实现了 ReadWriteLock 接口

// =================================================== 锁的分类 =========================================================
// =================================================== 可重入锁 =========================================================
// 可重入性表明了锁的分配机制：基于线程的分配，而不是基于方法调用的分配。像 synchronized 和 ReentrantLock 都是可重入锁
// 举个简单的例子，当一个线程执行到某个 synchronized 方法时，比如说method1，而在method1中会调用另外一个synchronized方法method2
// 此时线程不必重新去申请锁，而是可以直接执行方法method2
// (假如synchronized不具备可重入性，此时线程A需要重新申请锁，这样就会线程A一直等待永远不会获取到)
/*
    class MyClass {
        public synchronized void method1() {
            method2();
        }

        public synchronized void method2() {}
    }
*/

// =================================================== 可中断锁 =========================================================
// 可中断锁，是指可以被中断的锁
// 在Java中，synchronized 不是可中断锁，而Lock是可中断锁
//　如果某一线程A正在执行锁中的代码，另一线程B正在等待获取该锁，可能由于等待时间过长，线程B不想等待了，想先处理其他事情，
//  我们可以让它中断自己或者在别的线程中中断它，这种就是可中断锁。
//  lockInterruptibly()的用法体现了Lock的可中断性。

// =================================================== 读写锁   =========================================================
// 　读写锁将对一个资源（比如文件）的访问分成了2个锁，一个读锁和一个写锁。
//　     使得多个线程之间的读操作不会发生冲突。

// =================================================== 公平锁   =========================================================
// 　　公平锁尽量以请求锁的顺序来获取锁
//          比如同时有多个线程在等待一个锁，当这个锁被释放时，等待时间最久的线程（最先请求的线程）会获得该所，这种就是公平锁
//　　非公平锁即无法保证锁的获取是按照请求锁的顺序进行的，这样就可能导致某个或者一些线程永远获取不到锁
//
//　　在Java中，synchronized 就是非公平锁，它无法保证等待的线程获取锁的顺序
//　　而对于ReentrantLock和ReentrantReadWriteLock，它默认情况下是非公平锁，但是可以设置为公平锁
//          比如 ReentrantLock lock = new ReentrantLock(true);
//          isFair() 方法判断锁是否是公平锁

public class TestSynchronizedAndLock {
    Lock l;             // 锁
    ReadWriteLock rwk;  // 读写锁

    // Lock lock 方法使用模板：
    /*
        lock.lock();
        try{
            //处理任务
        }catch(Exception ex){

        }finally{
            lock.unlock();   //释放锁
        }
     */

    // tryLock 方法使用模板：
    /*
        if(lock.tryLock()) {
             try{
                 //处理任务
             }catch(Exception ex){

             }finally{
                 lock.unlock();   //释放锁
             }
        }else {
            //如果不能获取锁，则直接做其他事情
        }
     */
}
