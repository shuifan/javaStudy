public enum MyEnum {

    /**
     * 测试
     */
    SINGLE(1);

    MyEnum(int a){
        this.a = a;
    }

    private final int a;

    public int getA() {
        return a;
    }

}
