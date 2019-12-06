package bingfabianchengshizhan;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author fandong
 * @create 2018/6/21
 */
public class NumberRange {

    private AtomicInteger upper;

    private AtomicInteger lower;

    public synchronized boolean setUpper(Integer upper){
        if (upper < lower.get()){
            return false;
        }
        this.upper = new AtomicInteger(upper);
        return true;
    }

    public synchronized boolean setLower(Integer lower){
        if (lower > upper.get()){
            return false;
        }
        this.lower = new AtomicInteger(lower);
        return true;
    }
}
