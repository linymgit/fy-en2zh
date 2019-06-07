package com.lym.util.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.List;

public class HttpUtils {

    private static CloseableHttpClient httpclient = HttpClients.createDefault();

    public static void get(String url, HttpEventListener listener){
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpclient.execute(httpGet);){

            HttpEntity entity = response.getEntity();

            listener.success(entity);

        }  catch (IOException e){

        }

    }

    public static <T> T post(String url, List<NameValuePair> nvps, HttpEventListener<T> listener){
        HttpPost httpPost = new HttpPost(url);

        try(CloseableHttpResponse response = httpclient.execute(httpPost);) {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
            HttpEntity entity = response.getEntity();
            return listener.success(entity);
        } catch (IOException e){

        }
        return null;
    }


//    public static void main(String[] args) {
////        get("http://www.baidu.com", in -> {
////            InputStream content = in.getContent();
////            byte[] message = new byte[1024];
////            int read = content.read(message);
////            while (read > 0){
////                System.err.println(new String(message));
////                read = content.read(message);
////            }
////        });
//
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        nvps.add(new BasicNameValuePair("q", "apple"));
//        nvps.add(new BasicNameValuePair("from", "auto"));
//        nvps.add(new BasicNameValuePair("to", "auto"));
//        post("https://aidemo.youdao.com/trans?q=i like apple&from=auto&to=auto", nvps, in -> {
//            YoudaoFanyiRootBean o = JSON.parseObject(in.getContent(), YoudaoFanyiRootBean.class);
//            List<Web> web = o.getWeb();
//            web.forEach(web1 -> System.err.println(web1.getValue()));
//
//            return  null;
//        });
//    }


}
