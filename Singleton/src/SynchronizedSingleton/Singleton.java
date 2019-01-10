package SynchronizedSingleton;

/* OO单件模式——确保一个类只有一个实例，
 * 并提供一个全局访问点来获得这个唯一的实例。 */

/* 在获取类实例“使用不频繁”的情况下，可以考虑：
 * “同步获取实例的方法”以确保“线程安全”。
 * 但它的缺点很明显：牺牲了效率。
 * 俗话说：“同步一个方法可能造成程序执行效率下降100倍”*/
public class Singleton {
    private static Singleton uniqueInstance;
//  可以有其他成员变量

    /* 私有的构造方法，只有类内部方法可以调用 */
    private Singleton() {}

    /* synchronized：同步操作，
     * 使得getInstance()方法整体成为一个“原子操作”：
     * 在一个县城调用这个方法时，不允许其它线程调用它，
     * 进而避免了“两个线程都在没有类实例前完成了if判断，
     * 又都在if判断后一次创建了各自的类实例”，进而导致“非单件”的发生 */
    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }

//  单件工厂可以有其他需要的方法
}
