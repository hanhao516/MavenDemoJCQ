package com.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class httpUtil {

    /**
     * Http post
     * @param url post请求url
     * @param params 参数
     * @return
     * @throws Exception
     */
    public static String doPost(String url,Map<String, String> params) throws Exception {
        HttpPost httpPost=new HttpPost(url);
        HttpClient httpclient=new DefaultHttpClient();
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(params!=null){
            BasicNameValuePair bnvp=null;
            for (Map.Entry<String, String> p : params.entrySet()) {
                bnvp=new BasicNameValuePair(p.getKey(), p.getValue());
                nvps.add(bnvp);
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        HttpResponse response = httpclient.execute(httpPost);
        HttpEntity respEntity = response.getEntity();//获得返回数据
        String text = EntityUtils.toString(respEntity, "UTF-8");
        JSONObject obj = (JSONObject) JSONObject.parse(text);
        httpclient.getConnectionManager().shutdown();
        return obj.toJSONString();
    }
}
