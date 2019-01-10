package StaticInitializeSingleton;

/* OO单件模式——确保一个类只有一个实例，
 * 并提供一个全局访问点来获得这个唯一的实例。 */

/* 在需要频繁获取类实例时，可以考虑静态初始化时创建类实例：
 * “以空间换时间”*/
public class Singleton {
    /* 静态成员变量，在虚拟机JVM加载该类时，就完成了类的创建
     *（只会加载一次，也就只有一个类实例），
     * 这样就增加了程序空间，但省去了创建和运行时的时间开销
     * 即：以空间换时间 */
    private static Singleton uniqueInstance = new Singleton();
//  类的其他成员变量

    /* 不对类外提供的构造器，可以避免“多次创建”类实例 */
    private Singleton() {}

    public static Singleton getIntance() {
        /* 因为加载程序时就创建了唯一的类实例，
         * 所以直接返回这个类实例即可
         * 省去了“创建”与“判断”的时间开销 */
        return uniqueInstance;
    }

//  类的其他成员方法
}
