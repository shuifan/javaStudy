package other;

import java.io.*;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author fandong
 * @create 2018/4/9
 */
public class TextFileTest {

   static class A{
        private String name;
        private String value;

        public A() {
        }

        public A(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

       @Override
       public String toString() {
           return "A{" +
                   "name='" + name + '\'' +
                   ", value='" + value + '\'' +
                   '}';
       }
   }

    public static void writeToFile(PrintWriter printWriter, A a){
        printWriter.println(a.getName() + "|" + a.getValue());
    }

    public static A readFromFile(Scanner in){
        String s = in.nextLine();
        String[] split = s.split("\\|");
        return new A(split[0], split[1]);
    }

    public static void main(String... args) throws IOException, NoSuchAlgorithmException {

//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        System.out.println("a".split("\\.")[0]);
    }
}
