package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class getZBPrice {
    public static String getzbprice(String excoin,String channl)
    {
        String remoURL = "http://47.52.137.185:8088/test/zb/zbapi";
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("hiBitch8","skjdf8234j2l34uj8dfu79283423rkj");
        paraMap.put("changKey",excoin+"_"+channl);
        try
        {
            JSONObject jsonObjectMain = JSONObject.parseObject(httpUtil.doPost(remoURL,paraMap));
            JSONObject jsonObject = JSONObject.parseObject(jsonObjectMain.get("ticker").toString());
            String closeETH = jsonObject.get("last").toString();
            String low =jsonObject.get("low").toString();
            String high =jsonObject.get("high").toString();
//            return getHuobiPriceWithEth(excoin,ethdec);
            String returnStr = excoin + "(ZB)现价: " + get2PointUtil(closeETH) +"¥\n"+
                    "24小时最高价: " + get2PointUtil(high) + "¥\n"+
                    "24小时最低价: " + get2PointUtil(low) + "¥";
            return returnStr;

        }catch (Exception e)
        {
            return "error";
        }
    }

    private static String get2PointUtil(String str)
    {
        double d = Double.parseDouble(str);
        return String.format("%.4f",d);
    }

}
