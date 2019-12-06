package bingfabianchengshizhan.carlocation3;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author fandong
 * @create 2018/6/21
 */
public class MonitorCarLocation3 {

    private final ConcurrentMap<String, NewLocation> locationConcurrentMap;
    private final Map<String, NewLocation> unmodifiableMap;

    public MonitorCarLocation3(Map<String, NewLocation> map) {
        this.locationConcurrentMap = new ConcurrentHashMap<>(map);
        this.unmodifiableMap = Collections.unmodifiableMap(locationConcurrentMap);
    }

    public Map<String, NewLocation> getLocations(){
        return unmodifiableMap;
    }

    public NewLocation getLocation(String id){
        return locationConcurrentMap.get(id);
    }

    public boolean setLocation(String id, int x, int y){
        if (locationConcurrentMap.containsKey(id)){
            locationConcurrentMap.get(id).setLocation(x, y);
            return true;
        }else {
            return false;
        }
    }
}
