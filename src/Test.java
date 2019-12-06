//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.lang.reflect.Proxy;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.time.LocalTime;
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @author fandong
// * @create 2018/6/29
// */
//public class Test {
//
//    private static void takeData(String date, int length) throws ParseException {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        calendar.setTime(simpleDateFormat.parse(date));
//        Map<String, String> params = new HashMap<String, String>();
//        for (int i = 0; i < length; ++i) {
//            date = simpleDateFormat.format(calendar.getTime());
//            params.put("date", date);
//            System.out.println(date + " : " + post("http://apieee.tianxiabuyi.com:18088/enterprise/1048/database/operation.jsp", params, "UTF-8", null));
//            calendar.add(Calendar.DAY_OF_MONTH, 1);
//        }
//    }
//
//
//    public static void main(String[] args) throws ParseException {
//
//    }
//
//
//
//
//    /*
//     * 发送post请求 params 请求参数 encode 编码
//     */
//    public static  String post(String url, Map<String, String> params,
//                              String encode, String cookie) {
//        List<NameValuePair> list = new ArrayList<NameValuePair>();
//        if (params != null && !params.isEmpty()) {
//            for (Map.Entry<String, String> entry : params.entrySet()) {
//                String key = entry.getKey();
//                String value = entry.getValue();
//                list.add(new BasicNameValuePair(key, value));
//            }
//        }
//
//        CloseableHttpClient client = null;
//        HttpPost post = new HttpPost(url);
//        try {
//            client = HttpClientBuilder.create().build();
//            if (cookie != null) {
//                post.addHeader("Cookie", cookie);
//            }
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encode);
//            post.setEntity(entity);
//            HttpResponse response = client.execute(post);
//            if (response.getStatusLine().getStatusCode() == 200) {
//                return streamToString(response.getEntity().getContent(), encode);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (post != null) {
//                post.releaseConnection();
//            }
//            if (client != null) {
//                try {
//                    client.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return "";
//    }
//
//    // 输入流转字符串
//    private static String streamToString(InputStream inputStream, String encode) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        byte[] data = new byte[1024];
//        int len = 0;
//        String result = "";
//        if (inputStream != null) {
//            try {
//                while ((len = inputStream.read(data)) != -1) {
//                    outputStream.write(data, 0, len);
//                }
//                result = new String(outputStream.toByteArray(), encode);
//
//                outputStream.close();
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return result;
//    }
//}
