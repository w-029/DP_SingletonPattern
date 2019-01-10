package Classical_Defective_Singleton;

/* OO单件模式——确保一个类只有一个实例，
 * 并提供一个全局访问点来获得这个唯一的实例。 */

/* 经典的单件模式存在一个瑕疵：“线程不安全”*/
public class Singleton {
    private static Singleton uniqueInstance;
//  可以有其他成员变量

    /* 私有的构造方法，只有类内部方法可以调用 */
    private Singleton() {}

    /* getInstance()是一个静态的类方法，可以在任何地方直接调用，
     * 这个方法只创建一次类实例，之后每次都返回“同一个”实例，
     * 它确保了类实例的“单件”*/
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            /* 特殊情况下的漏洞：当第一个线程走到这里时（第一个线程即将创建一个类实例），
             * 碰巧该线程被阻塞，CPU时间片分给第二个线程，
             * 如果第二个线程也是走到了这里，那么第二个线程也即将创建一个类实例。*/
            /* 错误：两个线程在if判断时都没有类实例，接下来两个线程都将创建类实例，
             * 如果这个类是一个敏感类，其下有只能执行一次的敏感方法，
             * 那么两个类实例就会各自调用自己的敏感方法，使得“只能执行一次”失效，
             * 进而导致事故的发生。*/
            /* 注：更可怕的是，这种特殊情况下的漏洞并不一定总是发生，
             * 所以你很难通过实际运行效果来检测出漏洞，这样漏洞就会深埋在你的代码中，
             * 称为一颗“定时炸弹”*/
            uniqueInstance = new Singleton();
        }

        return uniqueInstance;
    }

//  单件工厂可以有其他需要的方法
}
