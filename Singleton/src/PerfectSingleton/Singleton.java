package PerfectSingleton;

/* OO单件模式——确保一个类只有一个实例，
 * 并提供一个全局访问点来获得这个唯一的实例。 */

public class Singleton {
    /* volatile关键字：提醒编译器在创建类实例时，
     * 多个线程要正确处理这个成员变量 */
    private volatile static Singleton uniqueInstance;

//  类的其他成员变量

    /* 私有的构造器，避免了类外部利用类的构造器
     * 创建多个类实例的可能 */
    private Singleton() {}

    /* 这里依然在程序运行时，调用这个方法时才会创建类实例，
     * 进而避免了“JVM加载程序时，急切地创建类实例”而浪费程序空间的可能 */
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            /* 只有在第一次创建类实例时，才会进入同步区块，
             * 如此避免了“每次获取类实例时都要同步排队”进而导致效率大幅降低的可能 */
            synchronized (Singleton.class) {
                /* 为了防止第一个if之后CPU时间片分配给其他创建该类实例的线程
                 * 切恰巧那个线程也执行到了创建类实例这一条语句，进而导致两个类实例，
                 * 所以需要再一次进行if判断。
                 * 如果：本线程进入同步区块之前（第一个if判断之后），
                 * 其他线程获取CPU时间片“插队”创建了该类的实例，
                 * 那么本线程的第二个if就会检测到该类实例已经存在，
                 * 进而不会重复创建类实例 */
                if (uniqueInstance == null) {
                    /* 这里是在“同步”区块之内，
                     * 因而不会出现：两个线程恰好利用CPU时间片接踵执行以下创建类实例 */
                    uniqueInstance = new Singleton();
                }
            }
        }

        return uniqueInstance;
    }

//  类的其他成员方法
}
