package bingfabianchengshizhan;

import java.util.List;

/**
 * @author fandong
 * @create 2018/6/21
 */
public class ImprovedList<T>{

    private final List<T> list;

    public ImprovedList(List<T> list) {
        this.list = list;
    }

    public synchronized boolean putIfAbsent(T t){
        if (list.contains(t)){
            return false;
        }else {
            list.add(t);
            return true;
        }
    }
}
