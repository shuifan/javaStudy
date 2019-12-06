
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;

/**
 * @author fandong
 * @create 2019/10/24
 */
public class Soap {

    public static String getWebServiceAndSoap(String url,String isClass,String isMethod,StringBuffer sendSoapString) throws IOException {
        String soap = sendSoapString.toString();
        if (soap == null) {
            return null;
        }
        URL soapUrl = new URL(url);
        URLConnection conn = soapUrl.openConnection();
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Length",
                Integer.toString(soap.length()));
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        // 调用的接口方法是
        conn.setRequestProperty(isClass,isMethod);
        OutputStream os = conn.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
        osw.write(soap);
        osw.flush();
        osw.close();
        // 获取webserivce返回的流
        InputStream is = conn.getInputStream();
        if (is!=null) {
            byte[] bytes = new byte[0];
            bytes = new byte[is.available()];
            is.read(bytes);
            String str = new String(bytes);
            return str;
        }else {
            return null;
        }
    }

}
