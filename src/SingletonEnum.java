public enum SingletonEnum {

    /**
     * 某单例
     */
    SOME_INSTANCE;

    private Singleton singleton;

    /**
     * 此方法由JVM在第一次使用枚举类实例 的时候调用
     * 可以看成是 private 外部无法调用
     */
    SingletonEnum(){
        singleton = new Singleton();
    }

    public Singleton getInstance(){
        return this.singleton;
    }


    private class Singleton{
        private Singleton(){
        }

    }
}
