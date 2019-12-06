package bingfabianchengshizhan.carlocation3;

/**
 * @author fandong
 * @create 2018/6/21
 */
public class NewLocation {

    private int x,y;

    public NewLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public synchronized void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public synchronized NewLocation getLocation(){
        return new NewLocation(x, y);
    }
}
