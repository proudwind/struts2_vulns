package cn.seaii.s2001.test;

import org.apache.commons.net.util.SubnetUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Arrays;
import java.util.regex.Pattern;

public class SsrfTest {
    private static int connectTime = 5*1000;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String url = "https://seaii-blog.com/";
        //ClassLoader.getSystemClassLoader().loadClass("java.lang.Runtime");
        //System.out.println(checkSsrf(url));
        if(!checkSsrf(url)) {
            System.out.println("ssrf!");
            return;
        }

        URL u = new URL(url);
        URLConnection urlConnection = u.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); //send request
        String inputLine;
        StringBuffer html = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            html.append(inputLine);
        }
        in.close();
        System.out.println(html.toString());
    }

    public static boolean checkSsrf(String url) {
        HttpURLConnection httpURLConnection;
        String finalUrl = url;
        try {
            do {
                if(!Pattern.matches("^https?://.*/.*$", finalUrl)) { //只允许http/https协议
                    return false;
                }
                if(isInnerIp(url)) { //判断是否为内网ip
                    return false;
                }

                httpURLConnection = (HttpURLConnection) new URL(finalUrl).openConnection();
                httpURLConnection.setInstanceFollowRedirects(false); //不跟随跳转
                httpURLConnection.setUseCaches(false); //不使用缓存
                httpURLConnection.setConnectTimeout(connectTime); //设置超时时间
                httpURLConnection.connect(); //send dns request

                int statusCode = httpURLConnection.getResponseCode();
                if (statusCode >= 300 && statusCode <=307 && statusCode != 304 && statusCode != 306) {
                    String redirectedUrl = httpURLConnection.getHeaderField("Location");
                    if (null == redirectedUrl)
                        break;
                    finalUrl = redirectedUrl; //获取到跳转之后的url，再次进行判断
                } else {
                    break;
                }
            } while (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK); //如果没有返回200，继续对跳转后的链接进行检查
            httpURLConnection.disconnect();
        } catch (Exception e) {
            return true;
        }
        return true;
    }

    private static boolean isInnerIp(String url) throws URISyntaxException, UnknownHostException {
        URI uri = new URI(url);
        String host = uri.getHost(); //url转host
        InetAddress inetAddress = InetAddress.getByName(host); //这一步会发送dns请求，host转ip，各种进制也会转化为常见的x.x.x.x的格式
        String ip = inetAddress.getHostAddress();

        String blackSubnetlist[] = {"10.0.0.0/8", "172.16.0.0/12", "192.168.0.0/16", "127.0.0.0/8"}; //内网ip段
//        <dependency>
//            <groupId>commons-net</groupId>
//            <artifactId>commons-net</artifactId>
//            <version>3.6</version>
//        </dependency>
        for(String subnet : blackSubnetlist) {
            SubnetUtils subnetUtils = new SubnetUtils(subnet);
            if(subnetUtils.getInfo().isInRange(ip)) {
                return true; //如果ip在内网段中，直接返回
            }
        }
        return false;
    }
}
