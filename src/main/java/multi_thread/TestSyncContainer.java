package multi_thread;

import java.util.Collections;

// =============================================== Java 非线程安全容器 ===================================================
// ArrayList、LinkedList、HashMap 这些容器都是非线程安全的，如果有多个线程并发地访问这些容器时，就会出现问题

// =============================================== Java 线程安全容器分两大类 ==============================================
// =============================================== Java 同步容器 ========================================================
// Vector、Stack、HashTable 中的方法都进行了 synchronized 同步，是线程安全的
//      因为有了 synchronized，所以性能较低
// 另外 Collections 类中的几个以 synchronizedXXX 开头的静态方法，也可以创建同步容器

// =============================================== Java 并发容器 ========================================================
// 是指 java.util.concurrent 包下的东西，这些比同步容器性能更好，更加安全
//      ConcurrentHashMap           并发的 HashMap
//      CopyOnWriteArrayList        并发的 ArrayList   写时复制的容器
//      CopyOnWriteArraySet         并发的 ArraySet    写时复制的容器
//      ConcurrentLinkedQueue       并发的 LinkedQueue
//      ConcurrentSkipListMap       并发的 SkipListMap 可以在高效并发中替代 SoredMap（用Collections.synchronzedMap包装的TreeMap）
//      ConcurrentSkipListSet       并发的 SkipListSet 可以在高效并发中替代 SoredSet（用Collections.synchronzedSet包装的TreeMap）

// =============================================== CopyOnWrite 写时复制 =================================================
// 当往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行 Copy 复制出一个新的容器
// 然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器

// 这样做的好处是我们可以对 CopyOnWrite 容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素
// 所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器

// 应用场景：
// 　CopyOnWrite 并发容器用于读多写少的并发场景；比如白名单，黑名单，商品类目的访问和更新场景

// CopyOnWrite 的缺点：
//   CopyOnWrite容器有很多优点，但是同时也存在两个问题，即内存占用问题和数据一致性问题
//     内存问题：会很占内存
//   　数据一致性问题：CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性
//                  所以如果你希望写入的的数据，马上能读到，则不要使用CopyOnWrite容器

// =============================================== Java 阻塞队列与非阻塞队列 ==============================================
// =============================================== Java 非阻塞队列 ============================================
// PriorityQueue
// LinkedList
// =================== 非阻塞队列中的方法 ============================================
// 　　对于非阻塞队列，一般情况下建议使用offer、poll和peek三个方法，不建议使用add和remove方法
//      因为使用offer、poll和peek三个方法可以通过返回值判断操作成功与否，而使用add和remove方法却不能达到这样的效果
//      注意，非阻塞队列中的方法都没有进行同步措施

// 　    add(E e):将元素e插入到队列末尾
//          如果插入成功，则返回true；
//          如果插入失败（即队列已满），则会抛出异常
//　　   remove()：移除队首元素
//          若移除成功，则返回true；
//          如果移除失败（队列为空），则会抛出异常
//　　   offer(E e)：将元素e插入到队列末尾
//          如果插入成功，则返回true
//          如果插入失败（即队列已满），则返回false
//　　   poll()：移除并获取队首元素，
//          若成功，则返回队首元素
//          否则返回null
//　　   peek()：获取队首元素
//          若成功，则返回队首元素
//          否则返回null

// =============================================== Java 阻塞队列 ==============================================
// 阻塞队列会对当前线程产生阻塞
//      比如一个线程从一个空的阻塞队列中取元素，此时线程会被阻塞直到阻塞队列中有了元素。
//      当队列中有元素后，被阻塞的线程会自动被唤醒（不需要我们编写代码去唤醒），这样提供了极大的方便性
//
// =================== 阻塞队列中的方法 ============================================
//  阻塞队列包括了非阻塞队列中的大部分方法，但是要注意这些方法在阻塞队列中都进行了同步措施
//  除此之外，阻塞队列提供了另外4个非常有用的方法：
//      　put(E e)   向队尾存入元素，如果队列满，则等待
//　　    take()     从队首取元素，如果队列为空，则等待
//　　    offer(E e,long timeout, TimeUnit unit)
//                  向队尾存入元素，如果队列满，则等待一定的时间，
//                  当时间期限达到时，如果还没有插入成功，则返回false；否则返回true
//　　    poll(long timeout, TimeUnit unit)
//　　               从队首取元素，如果队列空，则等待一定的时间，
//                  当时间期限达到时，如果取到，则返回null；否则返回取得的元素

//  在 java.util.concurrent 包下提供了若干个阻塞队列，主要有以下几个：
//　　    ArrayBlockingQueue：基于数组实现的一个阻塞队列
//              在创建ArrayBlockingQueue对象时必须制定容量大小
//              并且可以指定公平性与非公平性，默认情况下为非公平的（即不保证等待时间最长的队列最优先能够访问队列）

//　　    LinkedBlockingQueue：基于链表实现的一个阻塞队列
//              在创建LinkedBlockingQueue对象时如果不指定容量大小，则默认大小为 Integer.MAX_VALUE
//
//　　    PriorityBlockingQueue：以上2种队列都是先进先出队列，而 PriorityBlockingQueue却不是
//              它会按照元素的优先级对元素进行排序，按照优先级顺序出队，每次出队的元素都是优先级最高的元素
//              注意，此阻塞队列为无界阻塞队列，即容量没有上限，前面2种都是有界队列
//
//　　    DelayQueue：基于PriorityQueue，一种是延时阻塞队列，
//              DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。
//              DelayQueue也是一个无界队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞。

public class TestSyncContainer {
    public static void main(String[] args) {

    }
}
