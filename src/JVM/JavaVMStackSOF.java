package JVM;

/**
 * @author fandong
 * @create 2018/7/23
 */
public class JavaVMStackSOF {

    private int stackLength = 0;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public int getStackLength() {
        return stackLength;
    }

    public void setStackLength(int stackLength) {
        this.stackLength = stackLength;
    }

    public static void main(String[] args){
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        }catch (Throwable throwable){
            System.out.println(javaVMStackSOF.getStackLength());
            throw throwable;
        }
    }
}
