import edu.princeton.cs.algs4.StdDraw;
import sun.misc.Unsafe;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fandong
 * @create 2018/8/8
 */
public class A {

    private String a;
    private String b;

    private Date createTime;

    public A() {
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "A{" +
                "a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public static void main(String[] args) throws Exception {
        String[] split = "^a".split("\\^");
    }

    public static ZoneId systemDefaultZone = ZoneId.systemDefault();

    public static Date localDateToDate(LocalDate localDate){
        ZonedDateTime zdt = localDate.atStartOfDay(systemDefaultZone);
        return Date.from(zdt.toInstant());
    }

    public final void a(){
        
    }

    public final void a(String a){

    }

}
