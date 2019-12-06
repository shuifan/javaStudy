package bingfabianchengshizhan.carlocation;

import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fandong
 * @create 2018/6/21
 */
public class MonitorCarLocation {

    private final Map<String, Location> locationMap;

    public MonitorCarLocation(Map<String, Location> locationMap) {
        this.locationMap = deepCopy(locationMap);
    }

    public synchronized Map<String, Location> getLocations(){
        return deepCopy(locationMap);
    }

    public synchronized Location getLocation(String id){
        return locationMap.get(id);
    }

    public synchronized boolean setLocation(String id, int x, int y){
        locationMap.put(id, new Location(x, y));
        return true;
    }




    private Map<String, Location> deepCopy(Map<String, Location> oldMap){
        Map<String, Location> newMap = new HashMap<>(16);
        for (String k : oldMap.keySet()){
            newMap.put(k, oldMap.get(k));
        }
        return newMap;
    }
}
