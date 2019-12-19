import okhttp3.*;
import sun.awt.image.ImageWatched;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Paper {

    public static List<String> urlList = new LinkedList<>();
    static {
        urlList.add("register_item");
        urlList.add("register_area");
        urlList.add("bed_rate");
        urlList.add("avg");
        urlList.add("bed_avg_day");
        urlList.add("in");
        urlList.add("in_area");
        urlList.add("out");
        urlList.add("out_area");
        urlList.add("operate");
        urlList.add("clinic_income");
        urlList.add("clinic_income_medical");
        urlList.add("in_income");
        urlList.add("in_income_medical");
        urlList.add("register");
    }

    public static void main(String[] args) throws IOException {
        String url = "http://172.17.10.238:15270/%s?time=%s";
        LocalDate startDate  = LocalDate.of(2019,12,1);
        LocalDate endDate  = LocalDate.of(2019,12,18);
        DateTimeFormatter drfYmd = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .build();
        Request request;
        Call call;
        while (startDate.isBefore(endDate)){
            for (String s : urlList){
                String format = String.format(url, s, startDate.format(drfYmd));
                System.out.println(format + " start");
                request = new Request.Builder()
                        .url(format)
                        .method("GET", null)
                        .build();
                call = okHttpClient.newCall(request);
                call.execute();
                System.out.println(format + " succeed");
            }
            startDate = startDate.plusDays(1);
        }
    }
}
