
// Lamda 表达式是 Java 8 提供的语法
public class LamdaTest {

    public static void main(String[] args) {
        // 例子 1 使用 Lamda 表达式 开启线程
        new Thread(()-> {
            System.out.println("我是一个线程。。。" + Thread.currentThread().getName());
        }).start();

        // 测试带参数的 lamda
        test();
    }

    // 测试带参数的 lamda
    public static void test() {
        // 带参数的 Lamda
        ITest t = (String s) -> {
            System.out.println(s);
        };
        t.test("哈哈");

        // 带参数的 Lamda 简化，去掉参数类型
        // 注意多个参数，也可以去掉参数类型
        ITest t2 = (s) -> {
            System.out.println(s);
        };
        t2.test("啦啦");

        // 带参数的 Lamda 简化，去掉参数小括号
        // 注意多个参数，就不能去掉参数小括号了
        ITest t3 = s -> {
            System.out.println(s);
        };
        t3.test("啦啦哈哈");

        // 带参数的 Lamda 简化，去掉函数体大括号
        // 注意，如果函数体是多句代码，就不能去掉大括号了
        ITest t4 = s -> System.out.println(s);
        t4.test("啦啦哈哈喽");
    }
}

interface ITest {
    void test(String s);
}

// 函数式接口是 Lamda 表达式的核心

// 什么是函数式接口？
// 任何接口，如果只包含唯一一个抽象方法，那么它就是一个函数式接口

// 函数式接口的作用：
// 对于函数式接口，我们可以通过 lamda 表达式来创建该接口的对象

// 比如 Runnable 接口

/*
public interface Runnable {
    public abstract void run();
}
*/


