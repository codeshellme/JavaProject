package multi_thread;

// 模拟抢票
public class TestRunnableTickets implements Runnable {

    // 总票数
    private int ticketsNums = 10;

    @Override
    public void run() {
        while (true) {
            if (ticketsNums <= 0) {
                break;
            }

            try { Thread.sleep(10);    // 延时
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("%s --> 拿到了第 %s 张票",
                    Thread.currentThread().getName(), ticketsNums--));
        }
    }

    public static void main(String[] args) {

        TestRunnableTickets ticket = new TestRunnableTickets();

        // TODO 多个线程操作同一个资源(ticketsNums)的情况下，出现数据安全问题，需要线程同步
        new Thread(ticket, "线程A").start();
        new Thread(ticket, "线程B").start();
        new Thread(ticket, "线程C").start();
    }
}
