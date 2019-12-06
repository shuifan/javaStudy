package other;

import com.sun.deploy.util.StringUtils;
import com.sun.org.apache.xml.internal.utils.res.IntArrayWrapper;

import javax.xml.crypto.Data;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main implements Comparable{

    private final Integer a = null;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);


    public static void main(String[] args) throws IOException, ParseException {
//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16);
//        int i = Runtime.getRuntime().availableProcessors();
//        System.out.println(i);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        CompletionService completionService = new ExecutorCompletionService(Executors.newFixedThreadPool(100));
    }

    public static void test(String... args){
        for (String s : args){
            System.out.println(s);
        }
    }

    public static String secondsToString(Integer seconds){
        int hour = seconds / 3600;
        int hourSeconds = hour * 3600;
        int min = (seconds - hourSeconds) / 60;
        int second = seconds - hourSeconds - min * 60;
        StringBuilder stringBuilder = new StringBuilder();
        if ( hour != 0){
            stringBuilder.append(hour);
            stringBuilder.append(":");
            if (min < 10){
                stringBuilder.append("0");
            }
            stringBuilder.append(min);
            stringBuilder.append(":");
            if (second < 10){
                stringBuilder.append("0");
            }
            stringBuilder.append(second);
            return stringBuilder.toString();
        }else {
            stringBuilder.append(min);
            stringBuilder.append(":");
            if (second < 10){
                stringBuilder.append("0");
            }
            stringBuilder.append(second);
            return stringBuilder.toString();
        }
    }

    private static long daysBetween(Date one, Date two) {
        long difference =  (one.getTime()-two.getTime())/86400000;
        return Math.abs(difference);
    }

    public static InfoFromIdCard getAgeByIdCard(String idCard){
        int idCardLength = 32;
        if ((idCardLength != idCard.length())){
            return new InfoFromIdCard();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String nowYear = sdf.format(new Date());
        String birthYear = idCard.substring(6, 10);
        int age = Integer.valueOf(nowYear) - Integer.valueOf(birthYear);
        int gender = (int) idCard.charAt(30) % 2;
        return new InfoFromIdCard(gender, age);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static class InfoFromIdCard{

        private int gender;
        private int age;

        public InfoFromIdCard() {
        }

        public InfoFromIdCard(int gender, int age) {
            this.gender = gender;
            this.age = age;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
