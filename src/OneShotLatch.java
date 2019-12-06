import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author fandong
 * @create 2018/6/27
 */
public class OneShotLatch {

    private final Sync sync = new Sync();

    public void signal(){
        sync.releaseShared(0);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(0);
    }


    private class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args){
//        Calendar calendar = new GregorianCalendar();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(simpleDateFormat.format(calendar.getTime()));
//        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 6);
//        System.out.println(simpleDateFormat.format(calendar.getTime()));
//        List<String> list = new ArrayList<>(30);
//        System.out.println(list.set(1, ""));
        String s = "main";
        System.out.println(s.intern() == s);


    }
}
