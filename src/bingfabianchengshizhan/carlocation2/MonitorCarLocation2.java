package bingfabianchengshizhan.carlocation2;

import bingfabianchengshizhan.carlocation.Location;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author fandong
 * @create 2018/6/21
 */
public class MonitorCarLocation2 {

    private final ConcurrentMap<String, Location> locationConcurrentMap;
    private final Map<String, Location> unmodifiableMap;

    public MonitorCarLocation2(Map<String, Location> map) {
        this.locationConcurrentMap = new ConcurrentHashMap<>(map);
        this.unmodifiableMap = Collections.unmodifiableMap(locationConcurrentMap);
    }

    public Map<String, Location> getLocations(){
        return unmodifiableMap;
    }

    public Location getLocation(String id){
        return locationConcurrentMap.get(id);
    }

    public Location setLocation(String id, int x, int y){
        return locationConcurrentMap.replace(id, new Location(x, y));
    }


}
