package concurrentart;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author fandong
 * @create 2018/7/20
 */
public class Test {

    public static void main(String[] args){

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before = LocalDateTime.of(2019,7,1,0,0,0);

        Duration duration = Duration.between(before, now);
        System.out.println(duration.toDays());
    }


}
