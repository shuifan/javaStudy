package other;

import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LinkedListTest {

    public static void main(String[] args) throws NoSuchAlgorithmException, ParseException {
//        List<String> a = new LinkedList<>();
//        a.add("a");
//        a.add("b");
//        a.add("c");
//
//        List<String> b = new LinkedList<>();
//        b.add("1");
//        b.add("2");
//        b.add("3");
//
//        ListIterator<String> aIterator = a.listIterator();
//        ListIterator<String> bIterator = b.listIterator();
//        while (bIterator.hasNext()){
//            if (aIterator.hasNext()){
//                aIterator.next();
//            }
//            aIterator.add(bIterator.next());
//        }
//        System.out.println(a);
//
//        aIterator = a.listIterator();
//        while (aIterator.hasNext()){
//            aIterator.next();
//            if (aIterator.hasNext()){
//                aIterator.next();
//            }
//            aIterator.remove();
//        }
//        System.out.println(a);
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
//        m
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("createTime", "创建时间");
//        map.put("orderNo", "订单号");
//        map.put("isPaid", "支付状态");
//        map.put("payChannel", "支付渠道");
//        map.put("isRefund", "退款情况");
//        map.put("amount", "实际总金额（元）");
//        map.put("type", "订单类型");
//
//        for (String a : map.keySet()){
//            System.out.println(a);
//        }
//        for (String a : map.values()){
//            System.out.println(a);
//        }
//        System.out.println("a|b|c".split("\\|")[0]);
//        List<String> a = new ArrayList<>();
//        a.add("a");
//        a.add("b");
//        System.out.println(a.toString());
//        Map<String, String> fatherDeptMap = new TreeMap<>(Comparator.comparingInt(Integer::valueOf));
//        fatherDeptMap.put("1", "z");
//        fatherDeptMap.put("0", "a");
//        fatherDeptMap.put("0", "b");
//        for (String s : fatherDeptMap.values()){
//            System.out.println(s);
//        }
//        Map<String, String> map = new HashMap<>(16);
//        Class<? extends Map> mapClass = map.getClass();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        Date parse = sdf.parse("2018/9/11");
//        System.out.println(sdf.format(parse));
//        Map<String, String> map = new HashMap<>();
//        String a = map.get("a");
//        System.out.println(a);
//        System.out.println(new Date());
//        String idCard = "320621199504181416";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        String nowYear = sdf.format(new Date());
//        String birthYear = idCard.substring(6, 10);
//        System.out.println(Integer.valueOf(nowYear) - Integer.valueOf(birthYear));
        System.out.println("a/b".split("/").length);
    }
}
