package com.example.admisistrator.demo;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class HttpUtil {

    public static String doGet(String url) {
        /*将username和password传给Tomcat服务器*/
//        url = url + "?account=" + account + "&password=" + password;
//        this.url = url;
        try {
            URL httpUrl = new URL(url);
            /*获取网络连接*/
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            /*设置请求方法为GET方法*/
            conn.setRequestMethod("GET");
            /*设置访问超时时间*/
            conn.setReadTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            StringBuffer sb = new StringBuffer();
            // 读取服务器返回信息
            while((str=reader.readLine()) != null){
                sb.append(str);
            }
            // 把服务器返回的数据打印出来
            System.out.println(url);
            System.out.println("result" + sb.toString());

            return sb.toString();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

}
